package com.github.shkesar.Buzzer.Components;

import com.github.shkesar.Buzzer.Question;

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

    private Question question;

    public QuestionPanel(Question question) {
        super();

        this.question = question;
        this.options = new JCheckBox[question.getOptions().length];
        this.questionTextLabel.setText(this.question.getText());

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        questionTextLabel.setFont(new Font("Courier", Font.PLAIN, 14));
        this.add(questionTextLabel);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.setButtons();
        for(JCheckBox checkBox : this.options)
            this.add(checkBox);
    }

    public void setButtons() {
        for(int i = 0; i < this.question.getOptions().length; i++) {
            String optionName = this.question.getOptions()[i];
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

    public String getAnswer() {
        for(JCheckBox option : this.options)
            if(option.isSelected())
                return option.getText();

        return null;
    }
}
