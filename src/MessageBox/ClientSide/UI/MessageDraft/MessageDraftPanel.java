package MessageBox.ClientSide.UI.MessageDraft;

import MessageBox.ClientSide.Client;
import MessageBox.ClientSide.UI.ClientStructure.ClientPanel;
import MessageBox.Message;

import javax.swing.*;
import java.awt.*;

/**
 * A panel that consists of UI to the user to send a message
 */
public class MessageDraftPanel extends ClientPanel {
    private static final String TITLE = "Send Message";

    private final DraftBodyPanel draftBodyPanel;
    private final JButton sendButton;

    public MessageDraftPanel(Client client) {
        super(TITLE, client);
        draftBodyPanel = new DraftBodyPanel();
        sendButton = new JButton();
        setSendButton();
        addComponents();
    }

    public Client getClient(){ return super.getClient(); }

    public void setClient(Client client){ super.setClient(client); }

    private void setSendButton(){
        sendButton.addActionListener(actionEvent -> {
            if(draftBodyPanel.validateInput())
                sendMessage(draftBodyPanel.getMessage());
        });
        sendButton.setText("Send Message");
    }

    private void addComponents(){
        add(draftBodyPanel, BorderLayout.CENTER);
        add(sendButton, BorderLayout.SOUTH);
    }

    public void reply(Message message){
        draftBodyPanel.reply(message);
    }
}
