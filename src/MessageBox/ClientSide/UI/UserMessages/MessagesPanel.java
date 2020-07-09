package MessageBox.ClientSide.UI.UserMessages;

import MessageBox.ClientSide.OnReplyListener;
import MessageBox.Message;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A panel that holds several message panels
 */
public class MessagesPanel extends JPanel{

    private List<MessagePanel> messagePanels;

    private OnReplyListener onReplyListener;

    public MessagesPanel(OnReplyListener onReplyListener){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.onReplyListener = onReplyListener;
        messagePanels = new ArrayList<>();
    }

    public MessagesPanel(List<Message> messagePanels, OnReplyListener onReplyListener){
        this(onReplyListener);
        for(Message message: messagePanels)
            add(message);
    }

    //adding message with adding a message panel as well
    public void add(Message message){
        MessagePanel messagePanel = new MessagePanel(message);
        messagePanel.setOnReplyListener(onReplyListener);
        messagePanel.setOnDeleteListener(this::delete);
        messagePanels.add(messagePanel);
        add(messagePanel);
        revalidate();
        repaint();
    }

    private void removeMessagePanel(MessagePanel messagePanel){
        remove(messagePanel);
        messagePanels.remove(messagePanel);
        revalidate();
        repaint();
    }

    //deleting message by finding the matching panels and deleting them
    public void delete(Message message){
        List<MessagePanel> toBeDeletedList = new ArrayList<>();
        for (MessagePanel messagePanel : messagePanels)
            if (messagePanel.getMessage().equals(message))
                toBeDeletedList.add(messagePanel);
        for(MessagePanel toBeDeleted: toBeDeletedList)
            removeMessagePanel(toBeDeleted);
    }
    //setting the messages on the panel
    public void setMessages(List<Message> messages){
        removeAll();
        messagePanels = new ArrayList<>();
        for(Message message: messages)
            add(message);
    }

    public OnReplyListener getOnReplyListener() {
        return onReplyListener;
    }

    public void setOnReplyListener(OnReplyListener onReplyListener) {
        this.onReplyListener = onReplyListener;
    }
}
