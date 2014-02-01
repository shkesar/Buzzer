package com.github.shkesar.Buzzer;

public class ClientInfo {
    private String name;
    private int score = 0;

    public ClientInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public ClientInfo(String name) {
        this(name, 0);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int addedScore) {
        this.score += addedScore;
    }
}
