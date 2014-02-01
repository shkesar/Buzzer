package com.github.shkesar.Buzzer.Components;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class LogPanel extends JPanel {

    Vector<String> entries;
    private JList<String> list;

    public LogPanel() {
        super();

        this.entries = new Vector<String>();
        this.list = new JList<String>(entries);

        this.setLayout(new BorderLayout());
        JScrollPane scroll = new JScrollPane(list);
        this.add(scroll, BorderLayout.CENTER);
    }
    
    private void reinit() {
        list = new JList<String>(entries);
        repaint();
    }

    public void addEntry(String text) {
        entries.add(text);
    }
    
    public void removeEntry(String text) {
        entries.remove(text);
    }
}
