package org.naivedb;

import java.net.*;
import java.io.*;
import org.apache.commons.cli.*;
import org.naivedb.utils.*;

public class Server extends Thread{
    private ServerSocket serverSocket;

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
            DataOutputStream out = new DataOutputStream(server.getOutputStream());

            // REPL start in here
            while(true) {
                String message = in.readUTF();
                if (message.equals("EXIT") || 
                    message.equals("SHUTDOWN;") || message.equals("SHUTDOWN")) {
                    break;
                }
                String response = new String(message);
                out.writeUTF(response);
            }

            System.out.println("Server shutting down...");
            server.close();
        }
        catch(Exception e){
            System.out.print("Server meet error:");
            System.out.println(e);
        }
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
