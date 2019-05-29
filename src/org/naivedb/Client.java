package org.naivedb;

import java.net.*;
import java.io.*;
import org.apache.commons.cli.*;
import org.naivedb.utils.*;
import org.jline.terminal.*;
import org.jline.reader.*;
import org.jline.utils.InfoCmp.Capability;

public class Client {

    private static Socket client;

    private static boolean ipValid(String ip_addr) {
        if (ip_addr == null || ip_addr.length() < 7 || ip_addr.length() > 15) return false;

        String[] arr = ip_addr.split(".");
        if (arr.length != 4) return false;

        try{
            for (int i = 0; i < 4; i++) {
                int num = Integer.parseInt(arr[i]);
                if (i == 0) { if (num < 1 || num > 255) return false; }
                else if (num < 0 || num > 255) return false;
            }
        } catch(Exception e) { return false; }

        return true;
    }

    public static void showHelp() {
        System.out.println("Usage: `run class` [options]");
        System.out.println("Options:");
        System.out.println("  -h                            Show this help.");
        System.out.println("  -a IP_ADDRESS                 Specify IP address of host.");
        System.out.println("  -p PORT_NUMBER                Specify port number.");
    }
    
    public static void main(String[] args) {
        Options opts = new Options();
        opts.addOption(new Option("h", "help", false, "show this help"));
        opts.addOption(new Option("a", "address", true, "specify IP address of host"));
        opts.addOption(new Option("p", "port", true, "specify port number"));

        CommandLineParser parser = new DefaultParser();
        CommandLine cl;

        String ip = "127.0.0.1";
        int port_num = 8899;

        try {
            cl = parser.parse(opts, args);
            
            if (cl.hasOption("h"))
                showHelp();
            else if (cl.hasOption("a")) {
                ip = cl.getOptionValue("a");
                if (!ipValid(ip)) throw new NDException("ip address not valid");
            }
            else if (cl.hasOption("p"))
                port_num = Integer.parseInt(cl.getOptionValue("p"));
            
        } catch(Exception e) {
            showHelp();
            return;
        }

        try {
            System.out.println("Try connect server.");
            client = new Socket(ip, port_num);

            System.out.println("Connect succeed, host address: " + client.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            // REPL

            Terminal terminal = TerminalBuilder.builder().system(true).build();
            LineReader lineReader = LineReaderBuilder.builder().terminal(terminal).build();
            String prompt = "naivedb> ";

            while(true) {
                String line;
                try {
                    line = lineReader.readLine(prompt).toUpperCase();

                    if (line.equals("CLEAR")) {
                        terminal.puts(Capability.clear_screen);
                        terminal.flush();
                    }
                    else if (line.equals("EXIT") || 
                        line.equals("SHUTDOWN") || line.equals("SHUTDOWN;")) {
                        out.writeUTF(line);
                        System.out.println("Bye.");
                        break;
                    }
                    else {
                        out.writeUTF(line);
                        String response = in.readUTF();
                        System.out.println("recived message: " + response);
                    }
                } catch (UserInterruptException e) {
                    // Do nothing
                } catch (EndOfFileException e) {
                    System.out.println("\nBye.");
                    break;
                }
                
            }

            client.close();
        }
        catch(Exception e) {
            System.out.print("Meet error: ");
            System.out.println(e);
        }
    }
}
