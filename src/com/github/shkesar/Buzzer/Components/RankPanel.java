package com.github.shkesar.Buzzer.Components;

import com.github.shkesar.Buzzer.DataObjects.ClientInfo;

import javax.swing.*;

public class RankPanel extends JPanel {
    private JList list;
    ClientInfo[] clients;
    public RankPanel(ClientInfo[] clients) {
        super();

        this.clients = clients;
        this.list = new JList();
    }

    public RankPanel() {
        this(null);
    }
}
