package org.naivedb;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.antlr.v4.runtime.*;
import org.apache.commons.cli.*;
import org.naivedb.utils.*;
import org.naivedb.Statement.grammar.*;

public class Server extends Thread{
    private ServerSocket serverSocket;
    private static Logger logger = MyLogger.getLogger("Server");

    public Server(int port) throws IOException, NDException {
        if (port < 1000 || port > 65535) throw new NDException
            ("please bind a port between 1000 and 63335");
        serverSocket = new ServerSocket(port);
    }

    public void run()
    {
        try {
            System.out.println("listening at local port " + serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("accepted connect at address: " + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());

            // REPL start in here
            while(true) {
                String message = in.readUTF();
                if (message.equals("EXIT") || 
                    message.equals("SHUTDOWN;") || message.equals("SHUTDOWN"))
                    break;
                // String response = new String(message);
                ServerResult res = execSQL(message);
                
                // out.writeUTF(response);
                out.writeObject(res);
            }

            System.out.println("Server shutting down...");
            out.close();
            server.close();
        }
        catch(Exception e){
            logger.warning("Server meet error:");
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

    public static void showHelp() {
        System.out.println("Usage: `run class` [options]");
        System.out.println("Options:");
        System.out.println("  -h                            Show this help.");
        System.out.println("  -p PORT_NUMBER                Specify port number.");
    }

    public static void main(String[] args) {
        Options opts = new Options();
        opts.addOption(new Option("h", "help", false, "show this help"));
        opts.addOption(new Option("p", "port", true, "specify port number"));

        CommandLineParser parser = new DefaultParser();
        CommandLine cl;
        int port_num = 8899;

        try {
            cl = parser.parse(opts, args);
            
            if (cl.hasOption("h"))
                showHelp();
            else if (cl.hasOption("p"))
                port_num = Integer.parseInt(cl.getOptionValue("p"));
        } catch(Exception e) {
            showHelp();
            return;
        }

        try {
            System.out.println("Try initiate server.");
            Thread t = new Server(port_num);
            t.run();
        }
        catch(Exception e) {
            System.out.print("Meet error: ");
            System.out.println(e);
        }
    }
}
