package com.github.shkesar.Buzzer;

public class Question {
    private String text;
    private String[] options;

    Question(String text, String[] options) {
        this.text = text;
        this.options = options;
    }

    Question() {
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
