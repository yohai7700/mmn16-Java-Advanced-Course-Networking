package CitiesForecast.ServerSide;

import javax.swing.*;
import java.net.SocketException;

public class ServerProgram {
    public static final int FRAME_WIDTH = 400, FRAME_HEIGHT = 200;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Server");
        Server server;
        try { //setting up server, if unsuccessful, shows message and stops
            server = new Server();
        } catch (SocketException e) {
            JOptionPane.showMessageDialog(null, "Couldn't start server.");
            e.printStackTrace();
            return;
        }
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new ServerPanel(server::setFile));
        server.start();
        frame.setVisible(true);
    }
}
