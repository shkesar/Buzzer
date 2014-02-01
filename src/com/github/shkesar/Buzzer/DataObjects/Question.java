package com.github.shkesar.Buzzer.DataObjects;

public class Question {
    private String text;
    private String[] options;

    public Question(String text, String[] options) {
        this.text = text;
        this.options = options;
    }

    public Question() {
        this(null, null);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }
}
