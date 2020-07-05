package MessageBox.ClientSide;

import MessageBox.ClientSide.UI.ClientStructure.ClientFrame;

import javax.swing.*;

public class ClientProgram {
    private static final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 500;

    public static void main(String[] args) {
        JFrame frame = new ClientFrame();
        frame.setVisible(true);
    }
}
