package Configuration;

import MessageBox.ClientSide.Client;
import MessageBox.ClientSide.UI.FieldPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
//A panel that lets user configure network setting, IP and port.
public class ConfigurationPanel extends JPanel{
    public static final int PORT_UNDEFINED = -1;

    private final FieldPanel ipField, portField;
    private final JButton configureButton;
    private final JLabel statusLabel;

    public ConfigurationPanel(){
        ipField = new FieldPanel("IP");
        portField = new FieldPanel("Port");
        configureButton = new JButton("Set Configuration");
        statusLabel = new JLabel();
        setStatusLabel();
        addComponents();
    }

    public ConfigurationPanel(ConfigurationListener configureListener){
        this();
        setConfigureListener(configureListener);
    }

    private void addComponents(){
        add(ipField);
        add(portField);
        add(configureButton);
        add(statusLabel);
    }

    public void setConfigureListener(ConfigurationListener configureListener){
        ActionListener[] actionListeners = configureButton.getActionListeners();
        //removing action listeners from buttons
        for(ActionListener actionListener: actionListeners)
            configureButton.removeActionListener(actionListener);
        configureButton.addActionListener(actionEvent ->{
            String ip = getIP();
            int port = getPort();
            if(port == PORT_UNDEFINED)
                JOptionPane.showMessageDialog(getParent(), "Port must be a number.");
            else //letting user decide how to configure
                configureListener.onConfigure(ip, port);
        });
    }

    public String getIP(){
        return ipField.getText().strip();
    }

    public int getPort(){
        try{
            return Integer.parseInt(portField.getText().strip());
        }catch (ArithmeticException exception){
            return PORT_UNDEFINED;
        }
    }

    private void setStatusLabel(){
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.LIGHT_GRAY);
    }

    public void updateStatus(Client client){
        if(client == null)
            statusLabel.setText("Not connected to client");
        else
            statusLabel.setText(
                    "Connected to " + client.getIp() + " on " + client.getServerPort());
    }
}
