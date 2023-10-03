package org.example.lect_1;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_POSX = 675;
    private static final int WINDOW_POSY = 260;
    private int MIN_FIELD_SIZE = 3;
    private int MAX_FIELD_SIZE = 10;
    private int MIN_WIN_LENGTH = 3;
    private String FIELD_SIZE_PREFIX = "Установленный размер поля:";
    private String WIN_LENGTH_PREFIX = "Установленная длина:";

    private int MODE_HVA = 0;
    private int MODE_HVH = 1;
    ButtonGroup buttonGroup = new ButtonGroup();
    JRadioButton humanAgainstAI = new JRadioButton("Человек против компьютера");
    JRadioButton humanAgainstHuman = new JRadioButton("Человек против человека");
    JLabel labelFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);
    JLabel labelWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_FIELD_SIZE);
    JSlider sliderFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
    JSlider sliderWinLength = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_SIZE, MIN_FIELD_SIZE);
    JButton btnStart = new JButton("Start new game");


    SettingsWindow(GameWindow gameWindow) {
        setLocation(WINDOW_POSX, WINDOW_POSY);
        //setLocationRelativeTo(gameWindow.);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        setLayout(new GridLayout(10, 1));
        selectMode();
        selectSize();
        buttonHandler(gameWindow);
    }

    private void selectMode() {

        add(new JLabel("Выберите режим игры"));
        buttonGroup.add(humanAgainstAI);
        buttonGroup.add(humanAgainstHuman);
        add(humanAgainstAI);
        add(humanAgainstHuman);
    }

    private void selectSize() {
        sliderFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = sliderFieldSize.getValue();
                labelFieldSize.setText(FIELD_SIZE_PREFIX + currentValue);
                sliderWinLength.setMaximum(currentValue);
            }
        });
        sliderWinLength.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelWinLength.setText(WIN_LENGTH_PREFIX + sliderWinLength.getValue());
            }
        });

        add(new JLabel("Выберите размеры поля"));
        add(labelFieldSize);
        add(sliderFieldSize);
        add(new JLabel("Выберите длину для победы"));
        add(labelWinLength);
        add(sliderWinLength);
    }

    private void buttonHandler(GameWindow gameWindow) {
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int gameMode;
                int fieldSize = sliderFieldSize.getValue();
                int winLength = sliderWinLength.getValue();

                if (humanAgainstAI.isSelected()) gameMode = MODE_HVA;
                else if (humanAgainstHuman.isSelected()) gameMode = MODE_HVH;
                else gameMode = 0;
                gameWindow.startNewGame(gameMode, fieldSize, fieldSize, winLength);
                setVisible(false);
            }
        });
        add(btnStart);
    }
}
