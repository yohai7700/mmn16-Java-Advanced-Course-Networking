package MessageBox.ServerSide;

import MessageBox.ServerSide.UI.ServerPanel;

import javax.swing.*;

public class ServerProgram {
    public static final int FRAME_WIDTH = 400,  FRAME_HEIGHT = 400;

    public ServerProgram(){

    }

    public static void main(String[] args) {
        JFrame serverFrame = new JFrame("Server");
        serverFrame.add(new ServerPanel());
        serverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        serverFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        serverFrame.setVisible(true);
    }
}
