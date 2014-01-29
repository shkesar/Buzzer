package com.github.shkesar.Buzzer;

import javax.swing.*;

public class Run {
    JFrame frame;
    public static void main(String args[]) {
        new Run(args);
    }

    Run(String args[]) {
        if(args.length == 0)
            frame = new ClientApp();
        else if(args[0] == "runServer")
            frame = new ServerApp();

        frame.setVisible(true);
    }
}
