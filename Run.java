import javax.swing.JFrame;

class Run {
    static JFrame application;
    public static void main(String args[]) {
        BuzzerApplication guiApp;
        
        if(args.length == 0)
            guiApp = new BuzzerApplication();
        else if(args[0] == "setServer")
            application = new ServerApplication();
        else if(args[0] == "setClient")
            application = new ClientApplication();
    }
}