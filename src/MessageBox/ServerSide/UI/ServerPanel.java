package MessageBox.ServerSide.UI;

import MessageBox.Title.TitlePanel;
import MessageBox.ServerSide.Server;

import javax.swing.*;
import java.awt.*;

public class ServerPanel extends JPanel{

    private final Server server;
    private final TitlePanel titlePanel;
    private final UsersPanel usersPanel;
    private final AddUserPanel addUserPanel;

    public ServerPanel(){
        server = new Server();
        server.start();
        titlePanel = new TitlePanel("Users");
        usersPanel = new UsersPanel(this::removeUser);
        addUserPanel = new AddUserPanel(this::addUser);
        setLayout(new BorderLayout());
        addComponents();
    }

    private void addComponents(){
        add(titlePanel, BorderLayout.NORTH);
        JScrollPane usersScrollPane = new JScrollPane(usersPanel);
        add(usersScrollPane, BorderLayout.CENTER);
        add(addUserPanel, BorderLayout.SOUTH);
    }

    public void addUser(String user){
        if(user.strip().isEmpty())
            return;
        if(server.containsUser(user)){
            JOptionPane.showMessageDialog(this, "User already exist.");
            return;
        }
        server.addUser(user);
        usersPanel.addUser(user);
    }

    public void removeUser(String user){
        server.removeUser(user);
        usersPanel.removeUser(user);
    }
}
