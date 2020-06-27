package MessageBox;

import java.util.List;

public class Data {
    public enum Request{DOWNLOAD, UPLOAD}

    private final Request request;
    private final List<Message> messages;
    private final String userName;

    public Data(List<Message> messages) {
        this.messages = messages;
        request = Request.UPLOAD;
        userName = null;
    }

    public Data(String userName){
        this.userName = userName;
        request = Request.DOWNLOAD;
        messages = null;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public Request getRequest() {
        return request;
    }

    public String getUserName(){
        return userName;
    }
}
