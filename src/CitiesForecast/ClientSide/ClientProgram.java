package CitiesForecast.ClientSide;

import javax.swing.*;
import java.net.SocketException;

public class ClientProgram {
    public static final int FRAME_WIDTH = 200, FRAME_HEIGHT = 200;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cities Weather");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Client client;
        try {//setting up client, if can't, shows message to user
            client = new Client();
        } catch (SocketException e) {
            JOptionPane.showMessageDialog(null, "Couldn't connect to server.");
            e.printStackTrace();
            return;
        }
        ClientPanel clientPanel = new ClientPanel();
        //when city is selected, receiving forecast from server and displaying it
        clientPanel.setCityListener(city -> clientPanel.setForecast(client.getForecast(city)));
        frame.add(clientPanel);
        frame.setVisible(true);
    }
}
