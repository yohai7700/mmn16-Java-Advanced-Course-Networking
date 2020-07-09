package MessageBox;

import java.io.Serializable;

/**
 * Represents a request from client to server
 */
public class Request implements Serializable {
    public enum Type{DOWNLOAD, UPLOAD}

    private final Type name;
    private final Message message;
    private final String userName;

    public Request(Message message) {
        this.message = message;
        name = Type.UPLOAD;
        userName = null;
    }

    public Request(String userName){
        this.userName = userName;
        name = Type.DOWNLOAD;
        message = null;
    }

    public Message getMessage() {
        return message;
    }

    public Type getType() {
        return name;
    }

    public String getUserName(){
        return userName;
    }
}
