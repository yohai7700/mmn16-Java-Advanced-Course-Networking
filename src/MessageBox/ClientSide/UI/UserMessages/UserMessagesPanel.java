package MessageBox.ClientSide.UI.UserMessages;

import MessageBox.ClientSide.Client;
import MessageBox.ClientSide.OnReplyListener;
import MessageBox.ClientSide.UI.ClientStructure.ClientPanel;
import MessageBox.ClientSide.UI.FieldPanel;
import MessageBox.Message;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class UserMessagesPanel extends ClientPanel {
    private static final String TITLE = "User Messages";

    private final FieldPanel userField;
    private final MessagesPanel messagesPanel;
    private final JButton showMessagesButton;

    private OnReplyListener onReplyListener;

    public UserMessagesPanel(Client client, OnReplyListener onReplyListener){
        super(TITLE, client);
        setOnReplyListener(onReplyListener);
        userField = new FieldPanel("User");
        messagesPanel = new MessagesPanel(onReplyListener);
        showMessagesButton = new JButton("Show Messages");
        setShowMessagesButton();
        addComponents();
    }

    private void addComponents(){
        //setting base panel so that it will disable-enable child-components
        JPanel userFieldPanel = new JPanel(){
            @Override
            public void setEnabled(boolean enabled) {
                super.setEnabled(enabled);
                for(Component component: getComponents())
                    component.setEnabled(enabled);
            }
        };
        userFieldPanel.add(userField);
        userFieldPanel.add(showMessagesButton);
        add(userFieldPanel, BorderLayout.SOUTH);
    }

    public OnReplyListener getOnReplyListener() {
        return onReplyListener;
    }

    public void setOnReplyListener(OnReplyListener onReplyListener) {
        this.onReplyListener = onReplyListener;
    }

    private void setShowMessagesButton(){
        showMessagesButton.addActionListener(actionEvent ->{
            if(!userField.getText().isEmpty()) {
                try {
                    List<Message> messages = getClient().downloadMessages(userField.getText());
                    if(messages == null || messages.size() == 0 || messages.get(0).isEmpty())
                        throw new IOException();
                    messagesPanel.setMessages(getClient().downloadMessages(userField.getText()));
                } catch (IOException | ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(getParent(), "Couldn't show messages for this user.");
                }
            }
        });
    }

    @Override
    public void reply(Message message) {
        onReplyListener.onReply(message);
    }
}
