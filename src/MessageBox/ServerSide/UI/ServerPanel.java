package MessageBox.ServerSide.UI;

import MessageBox.ServerSide.Server;

import javax.swing.*;

public class ServerPanel extends JPanel{
    private final Server server;
    private final UsersPanel usersPanel;
    private final AddUserPanel addUserPanel;

    public ServerPanel(){
        server = new Server();
        server.start();
        usersPanel = new UsersPanel(this::removeUser);
        addUserPanel = new AddUserPanel(this::addUser);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        addComponents();
    }

    private void addComponents(){
        add(usersPanel);
        add(addUserPanel);
    }

    public void addUser(String user){
        server.addUser(user);
        usersPanel.addUser(user);
    }

    public void removeUser(String user){
        server.removeUser(user);
        usersPanel.removeUser(user);
    }
}
