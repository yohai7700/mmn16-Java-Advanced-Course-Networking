package MessageBox;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable, Cloneable{
    private String receiverName, senderName, topic, body;
    private Date sendingDate;

    public Message(String receiverName, String senderName, String topic, String body, Date sendingDate) {
        this.receiverName = receiverName;
        this.senderName = senderName;
        this.topic = topic;
        this.body = body;
        this.sendingDate = sendingDate;
    }

    private Message(){}

    public static Message createEmptyMessage(){
        return new Message();
    }

    public boolean isEmpty(){
        return body == null;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Message)) return false;
        return ((Message) o).senderName.equals(senderName)&&
               ((Message) o).receiverName.equals(receiverName)&&
               ((Message) o).topic.equals(topic)&&
               ((Message) o).body.equals(body)&&
               ((Message) o).sendingDate.equals(sendingDate);
    }
}
