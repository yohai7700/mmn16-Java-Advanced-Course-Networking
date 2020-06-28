package MessageBox;

import java.io.Serializable;

public class Data implements Serializable {
    public enum Request{DOWNLOAD, UPLOAD}

    private final Request request;
    private final Message message;
    private final String userName;

    public Data(Message message) {
        this.message = message;
        request = Request.UPLOAD;
        userName = null;
    }

    public Data(String userName){
        this.userName = userName;
        request = Request.DOWNLOAD;
        message = null;
    }

    public Message getMessage() {
        return message;
    }

    public Request getRequest() {
        return request;
    }

    public String getUserName(){
        return userName;
    }
}
