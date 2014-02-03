package com.github.shkesar.Buzzer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.github.shkesar.Buzzer.Components.LogPanel;
import com.github.shkesar.Buzzer.Components.QuestionPanel;
import com.github.shkesar.Buzzer.Components.RankPanel;
import com.github.shkesar.Buzzer.DataObjects.Question;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static com.github.shkesar.Buzzer.GUIHelper.*;

/**
 * Server GUI
 */
public class ServerApp extends JFrame {

    // Component variables
    private QuestionPanel questionPanel;
    private JButton prevButton, nextButton, pushQuestionButton;
    private LogPanel logPanel;
    private RankPanel rankPanel;
    private static Question[] questions;

    // Communication variables
    private Server server;

    // temp code
    String[] options = {"Shubham", "Rohit", "Vaibhav"};
    Question question = new Question("What is the name of the person who created this App?",
            options);

    public ServerApp() {
        super();

        this.addComponents();
        this.setUpNetworking();
        this.addListeners();
    }

    private void addListeners() {
        this.pushQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.sendToAllTCP(questionPanel.getQuestion());
            }
        });
    }

    private void setUpNetworking() {
        this.server = new Server();
        this.server.start();
        try {
            this.server.bind(54232, 54332);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.server.addListener(new Listener() {
            public void received(Connection connection, Object o) {
            }
        });
        Kryo kryo = server.getKryo();
        kryo.register(Question.class);
    }

    private void addComponents() {
        this.setLayout(null);

        // Question Panel
        questionPanel = new QuestionPanel(question);
        Dimension questionPanelPreferredSize = GUIHelper.percentOfScreen(0.6, 0.5);
        questionPanel.setBounds(containerHGap, containerVGap, (int)questionPanelPreferredSize.getWidth(),
                (int)questionPanelPreferredSize.getHeight());
        questionPanel.setBorder(new EtchedBorder());

        // Control Panel
        JPanel controlPanel = new JPanel();
        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");
        pushQuestionButton = new JButton("Push Question");
        //nextButton.setPreferredSize(prevButton.getSize());
        controlPanel.setBounds(containerHGap + 10,
                containerVGap + (int) questionPanelPreferredSize.getHeight() + vGap,
                (int) questionPanelPreferredSize.getWidth() - 20,
                40);
        controlPanel.setLayout(new BorderLayout());
        controlPanel.add(prevButton, BorderLayout.EAST);
        controlPanel.add(nextButton, BorderLayout.WEST);
        controlPanel.add(pushQuestionButton, BorderLayout.CENTER);

        // Log Panel
        logPanel = new LogPanel();
        logPanel.addEntry("Entry1");
        Dimension logPanelSize = new Dimension((int)questionPanelPreferredSize.getWidth(),
                (int)(GUIHelper.screenBounds.getHeight() -
                (2*containerVGap + questionPanelPreferredSize.getHeight() + controlPanel.getHeight() + 2*vGap)));
        logPanel.setBounds(containerHGap, containerVGap + questionPanel.getHeight() + vGap +
                (int)controlPanel.getHeight() + vGap, (int)logPanelSize.getWidth(), (int)logPanelSize.getHeight());
        logPanel.setBorder(new EtchedBorder());

        // Rank Panel
        rankPanel = new RankPanel();
        Dimension rankPanelPos = new Dimension(containerHGap + (int)questionPanelPreferredSize.getWidth() + vGap,
                (int)(GUIHelper.screenBounds.getHeight() - rankPanel.getHeight())/2);
        Dimension rankPanelSize = new Dimension((int)(GUIHelper.screenBounds.getWidth() -
                (2*containerHGap + questionPanel.getWidth() + hGap)), rankPanel.getHeight());
        rankPanel.setBounds((int)rankPanelPos.getWidth(), (int)rankPanelPos.getHeight(),
                (int)rankPanelSize.getWidth(), (int)rankPanelSize.getHeight());
        rankPanel.setBorder(new EtchedBorder());

        this.add(questionPanel);
        this.add(controlPanel);
        this.add(logPanel);
        this.add(rankPanel);

        // Screen initialisation
        Dimension fullScreenDimension = new Dimension((int)GUIHelper.screenBounds.getWidth(),
                (int)GUIHelper.screenBounds.getHeight());
        this.setMinimumSize(fullScreenDimension);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);
    }
}
