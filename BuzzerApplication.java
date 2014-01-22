import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

class BuzzerApplication extends JFrame implements ActionListener {
    static JFrame application;
    
    BuzzerApplication() {
        super("Select Mode");
        
        JButton clientButton, serverButton;
        clientButton = new JButton("Client");
        serverButton = new JButton("Server");
        
        clientButton.addActionListener(this);
        serverButton.addActionListener(this);
        
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.getContentPane().add(clientButton);
        this.getContentPane().add(serverButton);
        this.pack();
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Client":
                application = new ClientApplication();
                break;
            case "Server":
                application = new ServerApplication();
                break;
        }
        
        this.setVisible(false);
    }
}