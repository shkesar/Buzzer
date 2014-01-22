import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class BuzzerButton extends JButton {
    BuzzerApplication parent;
    BuzzerButton(BuzzerApplication parent) {
        super("Buzzer");
        
        this.parent = parent;
    }
    public void setBlocked(boolean blocked) {
        this.setEnabled(blocked);
    }
    public void setActive(boolean active) {
        if(active)
            this.requestFocus();
        else 
            parent.requestFocus();
    }
}