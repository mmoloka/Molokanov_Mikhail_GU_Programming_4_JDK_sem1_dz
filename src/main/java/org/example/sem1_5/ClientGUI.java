package org.example.sem1_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static java.awt.event.KeyEvent.VK_ENTER;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private JList usersList = new JList<>();

    private final JPanel topPanel = new JPanel(new GridLayout(3, 2));
    private final JPanel middlePanel = new JPanel(new GridLayout(1, 2));
    private final JPanel bottomPanel = new JPanel();

    private final JTextField loginField = new JTextField("login");
    private final JTextField passwordField = new JTextField("password");
    private final JTextField ipAddressField = new JTextField("127.0.0.1");
    private final JTextField portField = new JTextField("8081");
    private final JTextField messageField = new JTextField("                                                            " +
            "                                    ");
    private final JButton authorizationButton = new JButton("Login");
    private final JButton sendButton = new JButton("Send");

    private void logToFile() {
        try (FileWriter fileWriter = new FileWriter("log.txt")) {
            fileWriter.write(log.getText() + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String readLogFromFile() {
        StringBuffer result = new StringBuffer();
        try (FileReader fileReader = new FileReader("log.txt"); Scanner scanner = new Scanner(fileReader)) {
            while (scanner.hasNextLine()) result.append(scanner.nextLine() + "\n");
            return String.valueOf(result);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private ClientGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.append(messageField.getText() + "\n");
                logToFile();
            }
        });
        messageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == VK_ENTER) log.append(messageField.getText() + "\n");
                logToFile();
            }
        });

        topPanel.add(loginField);
        topPanel.add(passwordField);
        topPanel.add(ipAddressField);
        topPanel.add(portField);
        topPanel.add(authorizationButton);
        add(topPanel, BorderLayout.NORTH);

        log.setEditable(false);
        if (new File("log.txt").exists()) {
            log.append(readLogFromFile());
        }
        JScrollPane scrollLog = new JScrollPane(log);


        String users[] = {"Ivanov Ivan", "Petrov Petr", "Sidorov Sidor"};
        usersList.setListData(users);
        middlePanel.add(usersList);
        middlePanel.add(scrollLog);
        add(middlePanel);


        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);


        setVisible(true);


    }

    public static void main(String[] args) {

        new ClientGUI();

    }
}
