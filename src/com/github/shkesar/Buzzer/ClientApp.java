package com.github.shkesar.Buzzer;

import com.github.shkesar.Buzzer.Components.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ClientApp extends JFrame {
    static Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    Dimension fullscreenDimension;

    static int containerHGap = 5, containerVGap = 5;
    static int hGap = 5, vGap = 5;

    private static final int HORIZONTAL_SCORE_GAP = (int)screenBounds.getWidth() - 2 * hGap;

    // temp code
    String[] options = {"Shubham", "Rohit", "Vaibhav"};
    Question question = new Question("What is the name of the person who created this App?",
            options);

    ClientApp() {
        super();

        this.fullscreenDimension = new Dimension((int)screenBounds.getWidth(), (int)screenBounds.getHeight());

        this.setAlwaysOnTop(true);
        this.setMinimumSize(fullscreenDimension);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);

        this.drawComponents();
    }

    // Currently using no layout managers.
    // The widgets are positioned absolutely
    private void drawComponents() {
        this.setLayout(null);

        // Time and Score Panel
        JPanel timeNScorePanel = new JPanel();
        timeNScorePanel.setLayout(new BorderLayout());
        Dimension timeNScorePanelDimensions = new Dimension((int)ClientApp.screenBounds.getWidth()-2*containerHGap, 30);
        timeNScorePanel.setBounds(containerHGap, containerVGap,
                (int)timeNScorePanelDimensions.getWidth(), (int)timeNScorePanelDimensions.getHeight());
        timeNScorePanel.setBorder(new EtchedBorder());
        CountdownLabel cLabel = new CountdownLabel();
        cLabel.setTime(1000);
        timeNScorePanel.add(cLabel, BorderLayout.LINE_START);
        timeNScorePanel.add(new ScoreLabel(), BorderLayout.LINE_END);
        cLabel.startCountdown();

        // Question Panel
        QuestionPanel questionPanel = new QuestionPanel(question);
        Dimension questionBuzzerPanelDimensions = ClientApp.percentOfScreen(0.7, 0.7);
        questionPanel.setBounds(containerHGap, containerVGap + timeNScorePanel.getHeight()+ vGap,
                (int)questionBuzzerPanelDimensions.getWidth(), (int)questionBuzzerPanelDimensions.getHeight());
        questionPanel.setBorder(new EtchedBorder());

        // LogPanel
        LogPanel logPanel = new LogPanel();
        Dimension logPanelPos = new Dimension(containerHGap + questionPanel.getWidth() + hGap,
                containerVGap + timeNScorePanel.getHeight() + vGap);
        Dimension logPanelSize = new Dimension((int)ClientApp.screenBounds.getWidth() -
                (int)logPanelPos.getWidth() - containerHGap,
                (int)ClientApp.screenBounds.getHeight() - (int)logPanelPos.getHeight() - containerVGap);
        logPanel.setBounds((int) logPanelPos.getWidth(), (int) logPanelPos.getHeight(),
                (int) logPanelSize.getWidth(), (int) logPanelSize.getHeight());
        logPanel.setBorder(new EtchedBorder());

        // Buzzer button
        BuzzerButton buzzer = new BuzzerButton();
        Dimension buzzerPos = new Dimension(containerHGap,
                containerVGap + timeNScorePanel.getHeight() + vGap + questionPanel.getHeight() + vGap);
        Dimension buzzerSize = new Dimension((int)(ClientApp.screenBounds.getWidth() -
                containerHGap - vGap - logPanel.getWidth() - containerHGap),
                (int)(ClientApp.screenBounds.getHeight() - buzzerPos.getHeight() - containerVGap));
        buzzer.setBounds((int)buzzerPos.getWidth(), (int)buzzerPos.getHeight(),
                (int)buzzerSize.getWidth(), (int)buzzerSize.getHeight());

        Container container = this.getContentPane();
        container.add(timeNScorePanel);
        container.add(questionPanel);
        container.add(logPanel);
        container.add(buzzer);
    }

    public static Dimension percentOfScreen(double percentWidth, double percentHeight) {
        double width = ClientApp.screenBounds.getWidth() * percentWidth;
        double height = ClientApp.screenBounds.getHeight() * percentHeight;

        return new Dimension((int)width, (int)height);
    }
}
