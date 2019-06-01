package org.naivedb;

import java.net.*;
import java.io.*;
import org.apache.commons.cli.*;
import org.naivedb.utils.*;
import org.jline.terminal.*;
import org.jline.reader.*;
import org.jline.reader.impl.completer.*;
import org.jline.builtins.Completers;
import org.jline.builtins.Completers.RegexCompleter;
import org.jline.utils.InfoCmp.Capability;
import java.util.HashMap;
import java.util.Map;

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

    private static AggregateCompleter getCompleter() {
        Map<String, Completer> comp = new HashMap<>();
        // comp.put("NE", new StringsCompleter())

        Completer importCompleter = new ArgumentCompleter(
                new StringsCompleter("import"),
                new Completers.FileNameCompleter(),
                NullCompleter.INSTANCE
            );
        Completer useCompleter = new ArgumentCompleter(
                new StringsCompleter("use"),
                NullCompleter.INSTANCE
            );
        Completer metaCompleter = new ArgumentCompleter(
                new StringsCompleter("create", "drop"),
                new StringsCompleter("database", "table"),
                NullCompleter.INSTANCE
            );
        Completer showCompleter = new ArgumentCompleter(
                new StringsCompleter("show"),
                new StringsCompleter("databases;", "tables;", "table"),
                NullCompleter.INSTANCE
            );
        Completer insertCompleter = new ArgumentCompleter(
                new StringsCompleter("insert"),
                new StringsCompleter("into"),
                NullCompleter.INSTANCE,
                new StringsCompleter("values"),
                NullCompleter.INSTANCE
            );
        Completer delCompleter = new ArgumentCompleter(
            new StringsCompleter("delete"),
            NullCompleter.INSTANCE
        );
        Completer updCompleter = new ArgumentCompleter(
            new StringsCompleter("update"),
            NullCompleter.INSTANCE
        );
        Completer selCompleter = new ArgumentCompleter(
            new StringsCompleter("select"),
            new StringsCompleter("*"),
            new StringsCompleter("from"),
            new StringsCompleter("where"),
            NullCompleter.INSTANCE
        );
        Completer exitCompleter = new ArgumentCompleter(
            new StringsCompleter("exit", "shutdown"),
            NullCompleter.INSTANCE
        );
        Completer clcCompleter = new ArgumentCompleter(
            new StringsCompleter("clear"),
            NullCompleter.INSTANCE
        );
        return new AggregateCompleter(
            exitCompleter, clcCompleter, importCompleter, useCompleter,
            metaCompleter, showCompleter, insertCompleter, delCompleter,
            updCompleter, selCompleter
            );
    }

    private static void execFile(String filename, DataInputStream in, DataOutputStream out) {
        File sqls = new File(filename);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(sqls));
            String sql;
            String response = "";
            int line_num = 1;
            while ((sql = reader.readLine()) != null){
                out.writeUTF(sql);
                response = in.readUTF();
                // if (response.err == null) continue;
                // else {
                //     System.out.println("err meet when executing line " + line_num + ": ");
                //     System.out.println(reponse.err_message);
                // }
            }
            System.out.println("recived message: " + response);
            reader.close();
        } catch(IOException e){
            System.out.print("err meet: ");
            System.out.println(e);
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e){
                System.out.print("err meet: ");
                System.out.println(e);
            }
        }
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
            LineReader lineReader = LineReaderBuilder.builder().terminal(terminal)
                                                     .completer(getCompleter())
                                                     .build();
            String prompt = "naivedb> ";

            while(true) {
                String line;
                String upper;
                try {
                    line = lineReader.readLine(prompt).trim();
                    upper = line.toUpperCase();

                    if (upper.equals("CLEAR")) {
                        terminal.puts(Capability.clear_screen);
                        terminal.flush();
                    }
                    else if (upper.equals("EXIT") || 
                        upper.equals("SHUTDOWN") || upper.equals("SHUTDOWN;")) {
                        out.writeUTF(upper);
                        System.out.println("Bye.");
                        break;
                    }
                    else if (upper.startsWith("IMPORT")){
                        String file_name = line.substring(7).trim();
                        execFile(file_name, in, out);
                    }
                    else {
                        out.writeUTF(upper);
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