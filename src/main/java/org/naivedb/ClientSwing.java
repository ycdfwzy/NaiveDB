package org.naivedb;

import javax.swing.*;

public class ClientSwing {
    private static void GUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Naive DB Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello World!");
        frame.getContentPane().add(label);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                GUI();
            }
        });
    }
}