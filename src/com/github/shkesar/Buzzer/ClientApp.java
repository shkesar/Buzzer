package com.github.shkesar.Buzzer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.github.shkesar.Buzzer.Components.*;
import com.github.shkesar.Buzzer.DataObjects.Question;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.io.IOException;

import static com.github.shkesar.Buzzer.GUIHelper.*;

public class ClientApp extends JFrame {

    // Component members
    private QuestionPanel questionPanel;
    private CountdownLabel cLabel;
    private ScoreLabel scoreLabel;

    // Layout setup code
    Dimension fullscreenDimension = GUIHelper.screenBounds.getSize();

    // Communication code
    public static String ip = "127.0.0.1";
    private Client client;
    private Question question = new Question("", null);

    ClientApp() {
        super();

        this.setAlwaysOnTop(true);
        this.setMinimumSize(fullscreenDimension);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);

        this.drawComponents();

        this.setupUpNetworking();
        this.addListeners();
    }

    private void setupUpNetworking() {
        this.client = new Client();
        this.client.start();
        try {
            this.client.connect(5000, ClientApp.ip, 54232, 54332);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.client.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object o) {
                if(o instanceof Question){
                    questionPanel.setUpQuestion((Question)o);
                }
            }
        });

        Kryo kryo = client.getKryo();
        kryo.register(Question.class);
    }

    private void addListeners() {
    }

    // Currently using no layout managers.
    // The widgets are positioned absolutely
    private void drawComponents() {
        this.setLayout(null);

        // Time and Score Panel
        JPanel timeNScorePanel = new JPanel();
        this.cLabel = new CountdownLabel();
        this.scoreLabel = new ScoreLabel();
        timeNScorePanel.setLayout(new BorderLayout());
        Dimension timeNScorePanelDimensions = new Dimension((int)GUIHelper.screenBounds.getWidth()-2*containerHGap, 30);
        timeNScorePanel.setBounds(containerHGap, containerVGap,
                (int)timeNScorePanelDimensions.getWidth(), (int)timeNScorePanelDimensions.getHeight());
        timeNScorePanel.setBorder(new EtchedBorder());
        timeNScorePanel.add(this.cLabel, BorderLayout.LINE_START);
        timeNScorePanel.add(scoreLabel, BorderLayout.LINE_END);
        this.cLabel.startCountdown();

        // Question Panel
        questionPanel = new QuestionPanel(question);
        Dimension questionBuzzerPanelDimensions = GUIHelper.percentOfScreen(0.7, 0.7);
        questionPanel.setBounds(containerHGap, containerVGap + timeNScorePanel.getHeight()+ vGap,
                (int)questionBuzzerPanelDimensions.getWidth(), (int)questionBuzzerPanelDimensions.getHeight());
        questionPanel.setBorder(new EtchedBorder());

        // LogPanel
        LogPanel logPanel = new LogPanel();
        Dimension logPanelPos = new Dimension(containerHGap + questionPanel.getWidth() + hGap,
                containerVGap + timeNScorePanel.getHeight() + vGap);
        Dimension logPanelSize = new Dimension((int)screenBounds.getWidth() -
                (int)logPanelPos.getWidth() - containerHGap,
                (int)screenBounds.getHeight() - (int)logPanelPos.getHeight() - containerVGap);
        logPanel.setBounds((int) logPanelPos.getWidth(), (int) logPanelPos.getHeight(),
                (int) logPanelSize.getWidth(), (int) logPanelSize.getHeight());
        logPanel.setBorder(new EtchedBorder());

        // Buzzer button
        BuzzerButton buzzer = new BuzzerButton();
        Dimension buzzerPos = new Dimension(containerHGap,
                containerVGap + timeNScorePanel.getHeight() + vGap + questionPanel.getHeight() + vGap);
        Dimension buzzerSize = new Dimension((int)(screenBounds.getWidth() -
                containerHGap - vGap - logPanel.getWidth() - containerHGap),
                (int)(screenBounds.getHeight() - buzzerPos.getHeight() - containerVGap));
        buzzer.setBounds((int)buzzerPos.getWidth(), (int)buzzerPos.getHeight(),
                (int)buzzerSize.getWidth(), (int)buzzerSize.getHeight());

        Container container = this.getContentPane();
        container.add(timeNScorePanel);
        container.add(questionPanel);
        container.add(logPanel);
        container.add(buzzer);
    }
}
