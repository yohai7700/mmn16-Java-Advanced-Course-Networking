package MessageBox.ClientSide.UI.UserMessages;

import MessageBox.ClientSide.OnReplyListener;
import MessageBox.Message;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MessagesPanel extends JPanel{

    private final List<MessagePanel> messages;

    private OnReplyListener onReplyListener;

    public MessagesPanel(OnReplyListener onReplyListener){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.onReplyListener = onReplyListener;
        messages = new ArrayList<>();
    }

    public MessagesPanel(List<Message> messages, OnReplyListener onReplyListener){
        this(onReplyListener);
        for(Message message: messages)
            add(message);
    }

    public void add(Message message){
        MessagePanel messagePanel = new MessagePanel(message);
        messagePanel.setOnReplyListener(onReplyListener);
        messagePanel.setOnDeleteListener(this::delete);
        messages.add(messagePanel);
        revalidate();
    }

    private void removeMessagePanel(MessagePanel messagePanel){
        super.remove(messagePanel);
        messages.remove(messagePanel);
    }

    public void delete(Message message){
        for(MessagePanel messagePanel: messages)
            if(messagePanel.getMessage().equals(message))
                removeMessagePanel(messagePanel);
    }

    public void setMessages(List<Message> messages){
        List<MessagePanel> currentMessagePanels = this.messages;
        for(MessagePanel messagePanel: currentMessagePanels)
            removeMessagePanel(messagePanel);
        for(Message message: messages)
            add(message);
        revalidate();
    }

    public OnReplyListener getOnReplyListener() {
        return onReplyListener;
    }

    public void setOnReplyListener(OnReplyListener onReplyListener) {
        this.onReplyListener = onReplyListener;
    }
}
