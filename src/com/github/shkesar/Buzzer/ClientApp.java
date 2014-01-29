package com.github.shkesar.Buzzer;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ClientApp extends JFrame {
    static Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    Dimension fullscreenDimension;

    static int containerHGap = 5, containerVGap = 5;
    static int hGap = 5, vGap = 5;

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

    private void drawComponents() {
        this.setLayout(null);

        // Time and Score Panel
        JPanel timeNScorePanel = new JPanel();
        timeNScorePanel.setLayout(new FlowLayout());
        Dimension timeNScorePanelDimensions = new Dimension((int)ClientApp.screenBounds.getWidth()-2*containerHGap, 30);
        timeNScorePanel.setBounds(containerHGap, containerVGap,
                (int)timeNScorePanelDimensions.getWidth(), (int)timeNScorePanelDimensions.getHeight());
        timeNScorePanel.setBorder(new EtchedBorder());

        // Question Panel
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new FlowLayout());
        Dimension questionBuzzerPanelDimensions = ClientApp.percentOfScreen(0.7, 0.7);
        questionPanel.setBounds(containerHGap, containerVGap + timeNScorePanel.getHeight()+ vGap,
                (int)questionBuzzerPanelDimensions.getWidth(), (int)questionBuzzerPanelDimensions.getHeight());
        questionPanel.setBorder(new EtchedBorder());

        // LogPanel
        JPanel logPanel = new JPanel();
        Dimension logPanelPos = new Dimension(containerHGap + questionPanel.getWidth() + hGap,
                containerVGap + timeNScorePanel.getHeight() + vGap);
        Dimension logPanelSize = new Dimension((int)ClientApp.screenBounds.getWidth() - (int)logPanelPos.getWidth() - containerHGap,
                (int)ClientApp.screenBounds.getHeight() - (int)logPanelPos.getHeight() - containerVGap);
        logPanel.setLayout(new FlowLayout());
        logPanel.setBounds((int) logPanelPos.getWidth(), (int) logPanelPos.getHeight(),
                (int) logPanelSize.getWidth(), (int) logPanelSize.getHeight());
        logPanel.setBorder(new EtchedBorder());

        // Buzzer button
        BuzzerButton buzzer = new BuzzerButton();
        Dimension buzzerPos = new Dimension(containerHGap, containerVGap + timeNScorePanel.getHeight() + vGap + questionPanel.getHeight() + vGap);
        Dimension buzzerSize = new Dimension((int)(ClientApp.screenBounds.getWidth() - containerHGap - vGap - logPanel.getWidth() - containerHGap),
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