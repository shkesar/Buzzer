package com.github.shkesar.Buzzer;

import com.github.shkesar.Buzzer.Components.LogPanel;
import com.github.shkesar.Buzzer.Components.QuestionPanel;
import com.github.shkesar.Buzzer.DataObjects.Question;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

import static com.github.shkesar.Buzzer.GUIHelper.*;

/**
 * Server GUI
 */
public class ServerApp extends JFrame {

    private QuestionPanel questionPanel;
    private JButton prevButton, nextButton, pushQuestionButton;
    private LogPanel logPanel;

    // temp code
    String[] options = {"Shubham", "Rohit", "Vaibhav"};
    Question question = new Question("What is the name of the person who created this App?",
            options);

    public ServerApp() {
        super();

        this.addComponents();
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
        System.out.println(logPanel.getBounds());

        this.add(questionPanel);
        this.add(controlPanel);
        this.add(logPanel);

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
