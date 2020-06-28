package MessageBox.ServerSide.UI;

import javax.swing.*;

public class ServerFrame extends JFrame {
    public static final int WIDTH = 400,  HEIGHT = 400;

    public ServerFrame(){
        add(new ServerPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    public static void main(String[] args) {
        ServerFrame serverFrame = new ServerFrame();
    }
}
