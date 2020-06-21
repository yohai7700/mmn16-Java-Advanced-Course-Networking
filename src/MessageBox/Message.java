package MessageBox;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String receiverName, senderName, topic, body;
    private Date sendingDate;

    public Message(String receiverName, String senderName, String topic, String body, Date sendingDate) {
        this.receiverName = receiverName;
        this.senderName = senderName;
        this.topic = topic;
        this.body = body;
        this.sendingDate = sendingDate;
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

}
