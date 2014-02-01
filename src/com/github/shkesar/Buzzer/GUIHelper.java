package com.github.shkesar.Buzzer;

import java.awt.*;

public class GUIHelper {

    static int containerHGap = 8, containerVGap = 10;
    static int hGap = 5, vGap = 5;

    static Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

    public static Dimension percentOfScreen(double percentWidth, double percentHeight) {
        double width = ClientApp.screenBounds.getWidth() * percentWidth;
        double height = ClientApp.screenBounds.getHeight() * percentHeight;

        return new Dimension((int)width, (int)height);
    }
}
