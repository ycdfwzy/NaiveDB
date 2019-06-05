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
import org.naivedb.Database.DatabaseManager;
import org.naivedb.Session.*;

public class Server{
    private ServerSocket serverSocket;
    private static Logger logger = MyLogger.getLogger("Server");

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
            System.out.println("Initiate Server...");
            DatabaseManager.initial();

            // currently only support single session
            Session session = new Session(1, port_num);
            session.run();
            
            System.out.println("Server shutdown...");
            DatabaseManager.close();
        }
        catch(Exception e) {
            System.out.print("Server Meet error: ");
            System.out.println(e);
        }
    }
}
