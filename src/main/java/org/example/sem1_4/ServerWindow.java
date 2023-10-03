package org.example.sem1_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 250;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton buttonStart = new JButton("Start");
    private final JButton buttonStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private boolean isServerWorking;

    public static void main(String[] args) {
        new ServerWindow();
    }

    private ServerWindow() {
        isServerWorking = false;
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking) {
                    log.setText("");
                    log.append("Server not started" + "\n");
                } else {
                    isServerWorking = false;
                    log.setText("");
                    log.append("Server stopped " + isServerWorking + "\n");
                }
            }
        });
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    log.setText("");
                    log.append("Server is already working" + "\n");
                } else {
                    isServerWorking = true;
                    log.setText("");
                    log.append("Server started " + isServerWorking + "\n");
                }
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        add(log, BorderLayout.SOUTH);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        add(buttonPanel);
        setVisible(true);
    }
}
