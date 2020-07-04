package MessageBox.ClientSide.UI.UserMessages;

import MessageBox.ClientSide.OnDeleteMessageListener;
import MessageBox.ClientSide.OnReplyListener;
import MessageBox.Message;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {
    private static final int BORDER_THICKNESS = 1;

    private OnReplyListener onReplyListener;
    private OnDeleteMessageListener onDeleteListener;

    private final JLabel fromLabel = new JLabel();
    private final JLabel topicLabel = new JLabel();
    private final JTextArea bodyArea = new JTextArea();
    private final JButton replyButton = new JButton("Reply");
    private final JButton deleteButton = new JButton("Delete");

    private Message message;

    public MessagePanel(Message message){
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY, BORDER_THICKNESS));
        setBodyArea();
        setTopicLabel();
        setButtons();
        setMessage(message);
        addComponents();
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
        fromLabel.setText("From: " + message.getSenderName());
        topicLabel.setText("Topic: " + message.getTopic());
        bodyArea.setText(message.getBody());
    }

    private void setTopicLabel(){
        topicLabel.setOpaque(true);
        topicLabel.setBackground(Color.LIGHT_GRAY);
    }

    private void setBodyArea(){
        bodyArea.setEditable(false);
        bodyArea.setLineWrap(true);
        bodyArea.setWrapStyleWord(true);
        bodyArea.setCursor(null);
        bodyArea.setFocusable(false);
    }

    private void setButtons(){
        replyButton.addActionListener(actionEvent -> onReplyListener.onReply(message));
        deleteButton.addActionListener(actionEvent -> onDeleteListener.onDelete(message));
    }

    private void addComponents(){
        add(fromLabel, BorderLayout.WEST);
        add(topicLabel, BorderLayout.NORTH);
        JScrollPane bodyScrollPane = new JScrollPane(bodyArea);
        add(bodyScrollPane, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(replyButton);
        buttonsPanel.add(deleteButton);
        add(buttonsPanel);
    }

    public OnReplyListener getOnReplyListener() {
        return onReplyListener;
    }

    public void setOnReplyListener(OnReplyListener onReplyListener) {
        this.onReplyListener = onReplyListener;
    }

    public OnDeleteMessageListener getOnDeleteListener() {
        return onDeleteListener;
    }

    public void setOnDeleteListener(OnDeleteMessageListener onDeleteListener) {
        this.onDeleteListener = onDeleteListener;
    }
}
