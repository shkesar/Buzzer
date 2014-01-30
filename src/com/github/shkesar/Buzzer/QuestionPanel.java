package com.github.shkesar.Buzzer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionPanel extends JPanel implements ActionListener {
    // answer is set to the JCheckBox string selected by the user
    private String answer;

    private JLabel questionTextLabel = new JLabel();

    private ButtonGroup optionsGroup = new ButtonGroup();

    // There are as many check boxes as the number of options in the Question
    // supplied to the Constructor
    private JCheckBox[] options;

    private JButton lockButton = new JButton("Lock");

    private Question question;

    QuestionPanel(Question question) {
        super();

        this.question = question;
        this.options = new JCheckBox[question.getOptions().length];

        this.questionTextLabel.setText(this.question.getText());
        this.setButtons();
        this.add(questionTextLabel);
        for(JCheckBox checkBox : this.options)
            this.add(checkBox);
        this.add(lockButton, BorderLayout.SOUTH);
        this.lockButton.addActionListener(this);
    }

    public void setButtons() {
        for(int i = 0; i < this.question.getOptions().length; i++) {
            String optionName = this.question.getOptions()[0];
            this.options[i] = new JCheckBox(optionName);
        }
        for(JCheckBox checkBox : options)
            optionsGroup.add(checkBox);
    }

    // Called only for the lock button of the QuestionPanel
    @Override
    public void actionPerformed(ActionEvent e) {
        for(JCheckBox checkBox : this.options)
            if(checkBox.isSelected())
                this.answer = e.getActionCommand();
    }
}
