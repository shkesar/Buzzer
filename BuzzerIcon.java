import java.awt.*;
import javax.swing.*;

class BuzzerIcon implements Icon {
    private int width, height;
    
    BuzzerIcon(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(Color.GREEN);
        g.fillOval(0, 0, this.width-1, this.height-1);
    }
    
    public int getIconHeight() {
        return this.height;
    }
    public int getIconWidth() {
        return this.width;
    }
}