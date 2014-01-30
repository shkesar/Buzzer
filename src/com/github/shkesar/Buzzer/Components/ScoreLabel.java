package com.github.shkesar.Buzzer.Components;

import javax.swing.*;

/**
 * Represents the Client Score
 *
 * TODO : Add all the Server side update code
 */
public class ScoreLabel extends JLabel {
    private int score = 0;

    public ScoreLabel() {
        super("Score : 0");
    }

    @Override
    public void repaint() {
        this.setText("Score : " + this.score);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
