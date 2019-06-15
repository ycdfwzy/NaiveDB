package org.naivedb;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.apache.commons.cli.*;
import org.naivedb.utils.*;

public class ClientSwing extends JFrame {

    // data
    private Vector<String> titles = new Vector<>();
    private Vector<Vector<String>> datas = new Vector<Vector<String>>();

    // socket
    Socket client;
    ObjectInputStream inStream;
    DataOutputStream outStream;

    // -------------------- UI vars ----------------------
    // table area
    private MyTable table;

    // input area
    private JTextArea textarea;

    // button components
    private JButton clearButton;
    private JButton execButton;

    // menu components
    private JMenuItem openScript;
    private JMenuItem saveScript;
    private JMenuItem execScript;
    private JMenuItem cmdSelect;
    private JMenuItem cmdUpdate;
    private JMenuItem cmdInsert;
    private JMenuItem cmdDelete;
    private JMenuItem cmdExec;
    private JMenuItem cmdCreateTable;
    private JMenuItem cmdDropTable;
    private JMenuItem cmdCreateDb;
    private JMenuItem cmdDropDb;
    private JMenuItem cmdUseDb;
    private JMenuItem cmdShowDbs;
    private JMenuItem cmdShowDb;
    private JMenuItem cmdShowTb;
    private JMenuItem cmdShutdown;
    private JMenuItem helpAbout;
    private JMenuItem helpHelp;

    // recent
    private JMenu recentMenu;

    // status
    private JLabel dbStatus;

    /**
     * construct a client swing
     * 
     * @param title
     */
    public ClientSwing(String title) {
        super(title);

        table = new MyTable(datas, titles);
        clearButton = new JButton("clear");
        execButton = new JButton("execute");
        dbStatus = new JLabel("* Ready");
        textarea = new JTextArea(8, 5);
        textarea.setLineWrap(true);

        JMenuBar menuBar = this.genMenuBar();

        JPanel quickPanel = new JPanel();
        quickPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
        quickPanel.add(clearButton);
        quickPanel.add(execButton);

        JScrollPane textPanel = new JScrollPane(textarea);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        tablePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        tablePanel.add(table.getTableHeader());
        tablePanel.add(new JScrollPane(table));

        JPanel main_container = new JPanel();
        main_container.setLayout(new BorderLayout());
        main_container.add(textPanel, BorderLayout.NORTH);
        main_container.add(tablePanel, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        footer.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
        footer.add(dbStatus);

        JPanel outter_container = new JPanel();
        outter_container.setLayout(new BorderLayout());
        outter_container.add(quickPanel, BorderLayout.NORTH);
        outter_container.add(main_container, BorderLayout.CENTER);
        outter_container.add(footer, BorderLayout.SOUTH);

        this.add(menuBar, BorderLayout.NORTH);
        this.add(outter_container, BorderLayout.CENTER);
        this.add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.WEST);
        this.add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.EAST);
        this.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.SOUTH);

        this.setActionListener();
    }

    // ---------------------------------- Private Methods ---------------------------------
    private JMenuBar genMenuBar() {

        openScript = new JMenuItem("Open Script...");
        saveScript = new JMenuItem("Save Script...");
        execScript = new JMenuItem("Exec Script...");
        cmdSelect = new JMenuItem("Select");
        cmdInsert = new JMenuItem("Insert");
        cmdUpdate = new JMenuItem("Update");
        cmdDelete = new JMenuItem("Delete");
        cmdExec = new JMenuItem("Execute");
        cmdCreateTable = new JMenuItem("Create Table");
        cmdDropTable = new JMenuItem("Drop Table");
        cmdCreateDb = new JMenuItem("Create Database");
        cmdDropDb = new JMenuItem("Drop Database");
        cmdUseDb = new JMenuItem("Use Database");
        cmdShutdown = new JMenuItem("Shutdown");
        cmdShowDbs = new JMenuItem("Show Databases");
        cmdShowDb = new JMenuItem("Show Database");
        cmdShowTb = new JMenuItem("Show Table");
        helpAbout = new JMenuItem("About");
        helpHelp = new JMenuItem("Help");

        JMenu file = new JMenu("File");
        file.add(openScript);
        file.add(saveScript);
        file.add(execScript);

        JMenu cmd = new JMenu("Command");
        cmd.add(cmdSelect);
        cmd.add(cmdInsert);
        cmd.add(cmdUpdate);
        cmd.add(cmdDelete);
        cmd.add(cmdExec);
        cmd.addSeparator();
        cmd.add(cmdCreateTable);
        cmd.add(cmdDropTable);
        cmd.add(cmdCreateDb);
        cmd.add(cmdDropDb);
        cmd.add(cmdUseDb);
        cmd.addSeparator();
        cmd.add(cmdShowDbs);
        cmd.add(cmdShowDb);
        cmd.add(cmdShowTb);
        cmd.addSeparator();
        cmd.add(cmdShutdown);

        recentMenu = new JMenu("Recent");

        JMenu help = new JMenu("Help");
        help.add(helpAbout);
        help.add(helpHelp);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        menuBar.add(cmd);
        menuBar.add(recentMenu);
        menuBar.add(help);

        return menuBar;
    }

    private void setActionListener() {

        // exec listener
        ActionListener execListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbStatus.setText("* Busy...");
                execSQL(textarea.getText());
            }
        };

        // add clear for clear btn
        clearButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText("");
            }
        });

        // add execute for exec btn
        execButton.addActionListener(execListener);

        // -------------------------- Menu Btn ---------------------------

        // open script
        openScript.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
                chooser.setFileFilter(new ScriptFilter());
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        textarea.setText(FileUtils.readFileMultiLine(chooser.getSelectedFile()));
                    }
                    catch(Exception e) {
                        try {
                            JOptionPane.showMessageDialog(null, e, "Error", 0);
                        }
                        catch (Exception e2) {
                            System.out.println("meet error: " + e);
                            System.out.println("and catch handless error:");
                            System.out.println(e2);
                        }
                    }
                }
            }
        });

        // save script
        saveScript.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("sql", "sql");
                chooser.setFileFilter(filter);
                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileUtils.writeToFile(textarea.getText(), chooser.getSelectedFile());
                    }
                    catch(Exception e) {
                        try {
                            JOptionPane.showMessageDialog(null, e, "Error", 0);
                        }
                        catch (Exception e2) {
                            System.out.println("meet error: " + e);
                            System.out.println("and catch handless error:");
                            System.out.println(e2);
                        }
                    }
                }
            }
        });

        // exec script
        execScript.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
                chooser.setFileFilter(new ScriptFilter());
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    dbStatus.setText("* Busy...");
                    try {
                        NetworkUtils.writeString(outStream, 
                            FileUtils.readFile(chooser.getSelectedFile()).trim());
                        ServerResult response = (ServerResult)inStream.readObject();
                        showTime(response);
                    }
                    catch (Exception e) {
                        try {
                            dbStatus.setText("* Ready");
                            JOptionPane.showMessageDialog(null, e, "Error", 0);
                        }
                        catch (Exception e2) {
                            System.out.println("meet error: " + e);
                            System.out.println("and catch handless error:");
                            System.out.println(e2);
                        }
                    }
                }
            }
        });

        // cmd select
        cmdSelect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText("SELECT * FROM [table_name] WHERE [Conditions];");
            }
        });

        // cmd update
        cmdUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText("UPDATE [table_name] SET ?=? WHERE [Conditions];");
            }
        });

        // cmd insert
        cmdInsert.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText("INSERT INTO [table_name] VALUES ();");
            }
        });

        // cmd delete
        cmdDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText("DELETE FROM [table_name] WHERE [Conditions];");
            }
        });

        // cmd exec
        cmdExec.addActionListener(execListener);

        // cmd Create Table
        cmdCreateTable.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText("CREATE TABLE [table_name] ([name] [type], ...);");
            }
        });

        // cmd Drop Table
        cmdDropTable.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText("DROP TABLE [table_name];");
            }
        });

        // cmd Create Db
        cmdCreateDb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText("CREATE DATABASE [database_name];");
            }
        });

        // cmd Drop Db
        cmdDropDb.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText("DROP DATABASE [database_name];");
            }
        });

        // cmd use db
        cmdUseDb.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText("USE [database_name];");
            }
        });

        // cmd show dbs
        cmdShowDbs.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText("SHOW DATABASES;");
            }
        });

        // cmd show db
        cmdShowDb.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText("SHOW DATABASE [db_Name];");
            }
        });

        // cmd show tb
        cmdShowTb.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText("SHOW TABLE [table_name];");
            }
        });

        // cmd Shutdown
        cmdShutdown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText("SHUTDOWN;");
            }
        });

        // help About
        helpAbout.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent event) {
                try{
                    JOptionPane.showMessageDialog(null, "This is Naive DB:  A simple Database Manage System\n  Copyright (c) 2019 Wang Zeyu, Na Xin, Li Shuai", "Info", 1);
                }
                catch (Exception e){
                    System.out.println("catch handless error:");
                    System.out.println(e);
                }
            }
        });

        // help Help
        helpHelp.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent event) {
                try{
                    JOptionPane.showMessageDialog(null, "You can get help at https://github.com/ycdfwzy/NaiveDB.", "Info", 1);
                }
                catch (Exception e){
                    System.out.println("catch handless error:");
                    System.out.println(e);
                }
            }
        });

        // -------------------------- Menu Btn Over ---------------------------
    }

    // connect with server
    private void connectDB(String ip, int port) throws IOException {
        System.out.println("Try connect server.");
        client = new Socket(ip, port);

        System.out.println("Connect succeed, host address: " + client.getRemoteSocketAddress());
        inStream = new ObjectInputStream(client.getInputStream());
        outStream = new DataOutputStream(client.getOutputStream());
    }

    // show time as table
    private void showTime(ServerResult res) throws NDException{
        if (!res.succ) {
            throw new NDException(res.err_msg);
        }
        
        datas.clear();
        titles.clear();
        titles.add("Exec Time");
        Vector<String> vec = new Vector<>();
        vec.add(res.time_used + " mill sec");
        datas.add(vec);
        table.setTo(titles, datas);
        dbStatus.setText("* Ready, " + datas.size() + " rows in set, finished in " + res.time_used + " mill sec");
    }

    // show result as table
    private void showResult(ServerResult res) throws NDException{
        if (!res.succ) {
            throw new NDException(res.err_msg);
        }

        datas.clear();
        boolean header = true;
        for (String line: res.data.split("\n")) {
            if (header){
                titles.clear();
                for (String data: line.split("\\|")) titles.add(data);
                header = false;
                continue;
            }
            Vector<String> vec = new Vector<>();
            for (String data: line.split("\\|")) vec.add(data);
            datas.add(vec);   
        }
        table.setTo(titles, datas);
        dbStatus.setText("* Ready, " + datas.size() + " rows in set, finished in " + res.time_used + " mill sec");
    }

    // keep recent 20 sqls
    private void updateRecent(String sql) {
        if (recentMenu.getMenuComponentCount() >= 20)
            recentMenu.remove(0);

        JMenuItem item;
        if (sql.length() >= 40)
            item = new JMenuItem(sql.substring(0, 40) + " ...");
        else item = new JMenuItem(sql);

        item.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                textarea.setText(sql);
            }
        });
        recentMenu.add(item);
    }

    private void execSQL(String line) {
        line = line.trim();
        String upper = line.toUpperCase();

        try {
            dbStatus.setText("* Busy...");
            if (upper.equals("EXIT") || 
                upper.equals("SHUTDOWN") || upper.equals("SHUTDOWN;")) {
                NetworkUtils.writeString(outStream, upper);
                JOptionPane.showMessageDialog(null, "Success, will exit now.", "Info", 1);
                System.exit(0);
            }
            else if (upper.startsWith("IMPORT")){
                String file_name = line.substring(7).trim();
                NetworkUtils.writeString(outStream, FileUtils.readFile(new File(file_name)).trim());
                ServerResult response = (ServerResult)inStream.readObject();
                showTime(response);
            }
            else {
                NetworkUtils.writeString(outStream, line);
                ServerResult response = (ServerResult)inStream.readObject();
                showResult(response);
                updateRecent(line);
            }
        }
        catch (Exception e) {
            try {
                dbStatus.setText("* Ready");
                JOptionPane.showMessageDialog(null, e, "Error", 0);
            }
            catch (Exception e2) {
                System.out.println("meet error: " + e);
                System.out.println("and catch handless error:");
                System.out.println(e2);
            }
        }
        return;
    }

    // --------------------------------- static methods ---------------------------------

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

            if (cl.hasOption("h")) {
                showHelp();
                return;
            }
            else if (cl.hasOption("a")) {
                ip = cl.getOptionValue("a");
                if (!NetworkUtils.ipValid(ip))
                    throw new NDException("ip address not valid");
            } else if (cl.hasOption("p"))
                port_num = Integer.parseInt(cl.getOptionValue("p"));

        } catch (Exception e) {
            showHelp();
            return;
        }

        ClientSwing swing = new ClientSwing("Naive DB Manager Swing");
        try {
            swing.connectDB(ip, port_num);
            swing.setMinimumSize(new Dimension(800, 600));
            swing.setLocation(new Point(400, 200));
            swing.setVisible(true);
            swing.setResizable(false);
            swing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (Exception e) {
            System.out.print("Meet error: ");
            System.out.println(e);
        }
    }
}

/**
 * 表格组件 重载了 JTable 的 isCellEditable 方法
 */
class MyTable extends JTable {
    public MyTable(Vector data, Vector title) {
        super(data, title);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void setTo(Vector<String> titles, Vector<Vector<String>> datas) {
        DefaultTableModel model = new DefaultTableModel(datas, titles);
        setModel(model);
    }
}

/**
 * 脚本过滤器 过滤所有不以sql和txt结尾的文件
 */
class ScriptFilter extends FileFilter {

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        String name = f.getName();
        if (name.endsWith(".sql") || name.endsWith(".txt"))
            return true;
        return false;
    }
}
