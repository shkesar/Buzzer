package com.github.shkesar.Buzzer.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuzzerButton extends JButton {
    public BuzzerButton() {
        super();

        this.setText("Buzzer");
        this.addActionListener(new BuzzerButtonHandler());
    }

    private class BuzzerButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton)e.getSource();
            source.setBackground(Color.YELLOW);
            source.setEnabled(false);
        }
    }

    public void activateButton() {
        this.setEnabled(true);
        this.setBackground(Color.WHITE);
    }
}
