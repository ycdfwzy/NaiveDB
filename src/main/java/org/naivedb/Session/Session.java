package org.naivedb.Session;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.antlr.v4.runtime.*;
import org.apache.commons.cli.*;
import org.naivedb.utils.*;
import org.naivedb.Statement.grammar.*;

public class Session extends Thread{
    private ServerSocket sessionSocket;
    private int sessionNum;
    private SocketAddress clientAddress;
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
                String message = in.readUTF();
                if (message.equals("EXIT") || 
                    message.equals("SHUTDOWN;") || message.equals("SHUTDOWN"))
                    break;
                
                ServerResult res = execSQL(message);

                out.writeObject(res);
            }

            System.out.println("Session " + this.sessionNum + " closing...");
            out.close();
            server.close();
        }
        catch(Exception e){
            logger.warning("Session " + this.sessionNum + " meet error:");
            System.out.println(e);
        }
    }

    private ServerResult execSQL(String sql) {
        ServerResult res = new ServerResult();

        // succeed
        // res.succ = true;
        // res.data = "this is data";

        // fail
        res.succ = false;
        res.err_msg = "this is err msg";
        
        // CharStream input = CharStreams.fromString(sql);
        // sqlLexer lexer = new sqlLexer(input);
        // lexer.removeErrorListeners();
        // lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
        // CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        // sqlParser parser = new sqlParser(tokenStream);
        // parser.removeErrorListeners();
        // parser.addErrorListener(ThrowingErrorListener.INSTANCE);

        // try {
        //     myVisitor visitor = new myVisitor();
        //     ArrayList res = (ArrayList) visitor.visit(parser.parse());
            
        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        //     e.printStackTrace();
        // }

        return res;
    }
    
}
