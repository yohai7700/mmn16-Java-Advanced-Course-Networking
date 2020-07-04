package MessageBox.ClientSide.UI.MessageDraft;

import MessageBox.ClientSide.UI.FieldPanel;
import MessageBox.Message;

import javax.swing.*;
import java.util.Calendar;

public class DraftBodyPanel extends JPanel {
    enum FieldType{FROM, TO, TOPIC, BODY;

        @Override
        public String toString() {
            switch(this){
                case FROM: return "From";
                case TO: return "To";
                case TOPIC: return "Topic";
                case BODY: return "Body";
                default: return null;
            }
        }
    }

    private final FieldPanel fromPanel, toPanel, topicPanel;
    private final JLabel bodyLabel;
    private final JTextArea bodyArea;

    public DraftBodyPanel(){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        fromPanel = new FieldPanel(FieldType.FROM.toString());
        toPanel = new FieldPanel(FieldType.TO.toString());
        topicPanel = new FieldPanel(FieldType.TOPIC.toString());
        bodyLabel = new JLabel(FieldType.BODY.toString());
        bodyArea = new JTextArea();
        setBodyArea();
        addComponents();
    }

    public Message getMessage(){
        return new Message(
                fromPanel.getText(),
                toPanel.getText(),
                topicPanel.getText(),
                null,
                Calendar.getInstance().getTime()
        );
    }

    public void reply(Message message){
        fromPanel.setText(message.getReceiverName());
        toPanel.setText(message.getSenderName());
        topicPanel.setText("Re: " + message.getTopic());
        bodyArea.setText("");
    }

    public boolean validateInput(){
        if(fromPanel.getText().strip().isEmpty())
            JOptionPane.showMessageDialog(this,
                    "Must insert the sender name.");
        else if(toPanel.getText().strip().isEmpty())
            JOptionPane.showMessageDialog(this,
                    "Must insert the receiver name.");
        else if(topicPanel.getText().strip().isEmpty())
            JOptionPane.showMessageDialog(this,
                    "A message must have a topic.");
        else if(bodyArea.getText().strip().isEmpty())
            JOptionPane.showMessageDialog(this,
                    "A message must have a body.");
        else return true;
        return false;
    }

    private void setBodyArea(){
        bodyArea.setMaximumSize(bodyArea.getPreferredSize());
        bodyArea.setWrapStyleWord(true);
        bodyArea.setLineWrap(true);
    }

    private void addComponents(){
        add(fromPanel);
        add(toPanel);
        add(topicPanel);
        add(bodyLabel);
        JScrollPane scrollPane = new JScrollPane(bodyArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
    }
}
