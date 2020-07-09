package MessageBox.ClientSide.UI.ClientStructure;

import Configuration.ConfigurationPanel;
import MessageBox.ClientSide.Client;
import MessageBox.ClientSide.UI.ClientMenu;

import javax.swing.*;
import java.awt.*;

/**
 * A frame that holds the client. Controls the client object and the configuration panel and
 * menu.
 */
public class ClientFrame extends JFrame {
    public static final int FRAME_WIDTH = 1000, FRAME_HEIGHT = 500;
    private static final String TITLE = "Messages";

    Client client;
    private final ClientMenu clientMenu;
    private final ConfigurationPanel configurationPanel;

    public ClientFrame(){
        setTitle(TITLE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        clientMenu = new ClientMenu(client);
        configurationPanel = new ConfigurationPanel(this::setClient);
        setClient(Client.LOOPBACK_IP, Client.DEFAULT_SERVER_PORT);
        addComponents();
    }

    private void addComponents(){
        add(configurationPanel, BorderLayout.NORTH);
        add(clientMenu, BorderLayout.CENTER);
    }

    public void setClient(Client client) {
        this.client = client;
        clientMenu.setClient(client);
        configurationPanel.updateStatus(client);
    }

    public void setClient(String ip, int port){
        Client client;
        client = new Client(ip, port);
        setClient(client);
    }
}
