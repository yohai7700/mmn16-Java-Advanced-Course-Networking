package MessageBox.ClientSide.UI.ClientStructure;

import MessageBox.ClientSide.Client;
import MessageBox.ClientSide.UnsubscribedUserException;
import MessageBox.Message;
import MessageBox.Title.TitlePanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Stack;

/**
 * Abstract panel that represents a panel in client UI menu.
 */
public abstract class ClientPanel extends JPanel{
    protected static final Color BORDER = Color.GRAY;
    protected static final int BORDER_THICKNESS = 2;

    private static final String SEND_MESSAGE_ERROR =
            "An error happened. Can't send message, please try again.",
            SEND_MESSAGE_SUCCESS = "Message has been sent successfully.";

    private String title;
    private Client client;

    protected final TitlePanel titlePanel;

    public ClientPanel(String title, Client client){
        setLayout(new BorderLayout());
        titlePanel = new TitlePanel(title);
        setTitle(title);
        addTitle();
        this.client = client;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        Component[] components = getComponents();
        for(Component component : components)
            component.setEnabled(enabled);
    }

    protected void addTitle(){
        add(titlePanel, BorderLayout.NORTH);
    }

    public void setTitle(String title){
        this.title = title;
        updateTitlePanel();
    }

    private void updateTitlePanel(){
        titlePanel.setTitle(this.title);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    protected void sendMessage(Message message){
        Stack<UnsubscribedUserException> unsubscribedUser = new Stack<>();
        try {
            client.sendMessage(message, unsubscribedUser::push);
            if(!unsubscribedUser.isEmpty())
                throw unsubscribedUser.pop();
            JOptionPane.showMessageDialog(this, SEND_MESSAGE_SUCCESS);
        } catch(IOException exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(this, SEND_MESSAGE_ERROR);
        } catch (UnsubscribedUserException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public abstract void reply(Message message);
}
