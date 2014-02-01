package com.github.shkesar.Buzzer.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountdownLabel extends JLabel {
    // Time in seconds
    private volatile int time;
    Timer timer;

    public CountdownLabel() {
        super("00:00");
    }

    // Starts the time countdown
    // Use setTime() to set the initial start time
    public void startCountdown() {
        this.setFont(new Font("Times New Roman", Font.BOLD, 18));
        ActionListener timeDelayer = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                time -= 1;
                setText(time/60 + ":" + time%60);
            }
        };

        timer = new Timer(1000, timeDelayer);
        timer.start();
    }

    // Stops the time countdown
    public void stopCountdown() {
        timer.stop();
    }

    public void setTime(int time) {
        this.time = time;
    }
}
