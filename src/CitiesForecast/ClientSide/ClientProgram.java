package CitiesForecast.ClientSide;

import Configuration.ConfigurationPanel;

import javax.swing.*;
import java.awt.*;
import java.net.SocketException;

/**
 * holds the frame of the program.
 */
public class ClientProgram {
    public static final int FRAME_WIDTH = 800, FRAME_HEIGHT = 300;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cities Weather");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Client client = addClientPanel(frame);
        addConfigurationPanel(frame, client);
        frame.setVisible(true);
    }

    private static Client addClientPanel(JFrame frame){
        Client client;
        try {//setting up client, if can't, shows message to user
            client = new Client();
        } catch (SocketException e) {
            JOptionPane.showMessageDialog(null, "Couldn't connect to server.");
            e.printStackTrace();
            return null;
        }
        ClientPanel clientPanel = new ClientPanel();
        //when city is selected, receiving forecast from server and displaying it
        clientPanel.setCityListener(city -> clientPanel.setForecast(client.getForecast(city)));
        frame.add(clientPanel, BorderLayout.CENTER);
        return client;
    }
    //letting user configure networking settings: IP, port
    private static void addConfigurationPanel(JFrame frame, Client client){
        if(client == null) return;
        ConfigurationPanel panel = new ConfigurationPanel();
        panel.setConfigureListener((ip, port) -> {
            client.setServerName(ip);
            client.setServerPort(port);
        });
        frame.add(panel, BorderLayout.NORTH);
    }
}
