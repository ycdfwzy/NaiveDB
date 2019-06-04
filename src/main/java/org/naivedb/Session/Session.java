package org.naivedb.Session;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.antlr.v4.runtime.*;
import org.apache.commons.cli.*;
import org.naivedb.utils.*;
import org.naivedb.Database.*;
import org.naivedb.Statement.grammar.*;
import org.naivedb.Statement.*;

public class Session extends Thread{
    private ServerSocket sessionSocket;
    private int sessionNum;
    private SocketAddress clientAddress;
    private Database curDatabase;
    private static Logger logger = MyLogger.getLogger("session");

    public Session(int number, int port) throws IOException, NDException {
        this.sessionNum = number;
        if (port < 1000 || port > 65535) throw new NDException
            ("please bind a port between 1000 and 63335");
        sessionSocket = new ServerSocket(port);
    }

    public void run()
    {
        try {
            System.out.println("Session " + this.sessionNum + " listening at local port "
                + sessionSocket.getLocalPort() + "...");
            Socket server = sessionSocket.accept();
            this.clientAddress = server.getRemoteSocketAddress();
            System.out.println("Session " + this.sessionNum + " accepted connect at address: "
                + this.clientAddress);
            
            DataInputStream in = new DataInputStream(server.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());

            // REPL start in here
            while(true) {
                String message = in.readUTF().trim().toUpperCase();
                if (message.equals("EXIT") || 
                    message.equals("SHUTDOWN;") || message.equals("SHUTDOWN"))
                    break;
                
                ServerResult res = execSQL(message);

                out.writeObject(res);
            }

            System.out.println("Session " + this.sessionNum + " closing...");
            if (this.curDatabase != null) this.curDatabase.close();
            out.close();
            server.close();
        }
        catch(Exception e){
            logger.warning("Session " + this.sessionNum + " meet error:");
            System.out.println(e);
        }
    }

    private ServerResult execSQL(String sql) {
        ServerResult result = new ServerResult();
        
        // initial anltr
        CharStream input = CharStreams.fromString(sql);
        sqlLexer lexer = new sqlLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        sqlParser parser = new sqlParser(tokenStream);
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);

        // compile and execute stage
        try {
            myVisitor visitor = new myVisitor();
            ArrayList exec_res = (ArrayList) visitor.visit(parser.parse());
            for (Object o: exec_res) {
                if (o instanceof StatementCreateDatabase) {
                    StatementCreateDatabase stm = (StatementCreateDatabase) o;
                    System.out.println(stm.exec());
                }
                // 可以drop当前db吗？
                if (o instanceof StatementDropDatabase) {
                    StatementDropDatabase stm = (StatementDropDatabase) o;
                    stm.exec();
                }
                if (o instanceof StatementUse) {
                    if (curDatabase != null)
                        curDatabase.close();
                    StatementUse stm = (StatementUse) o;
                    curDatabase = DatabaseManager.get(stm.getDBName());
                }
                if (o instanceof StatementCreateTable) {
                    StatementCreateTable stm = (StatementCreateTable) o;
                    System.out.println(stm.exec(curDatabase));
                }
                if (o instanceof StatementDropTable) {
                    StatementDropTable stm = (StatementDropTable) o;
                    System.out.println(stm.exec(curDatabase));
                }
                if (o instanceof StatementInsert) {
                    StatementInsert stm = (StatementInsert) o;
                    System.out.println(stm.exec(curDatabase));
                }
                if (o instanceof StatementDelete) {
                    StatementDelete stm = (StatementDelete) o;
                    System.out.println(stm.exec(curDatabase));
                }
                if (o instanceof StatementUpdate) {
                    StatementUpdate stm = (StatementUpdate) o;
                    System.out.println(stm.exec(curDatabase));
                }
                if (o instanceof StatementSelect) {
                    StatementSelect stm = (StatementSelect) o;
                    SelectResult selectResult = stm.exec(curDatabase);
                    selectResult.show();
                }
            }
            
        } catch (Exception e) {
            result.succ = false;
            result.err_msg = e.getMessage();
            logger.info("err meet: " + e.getMessage());
        }

        return result;
    }
    
}
