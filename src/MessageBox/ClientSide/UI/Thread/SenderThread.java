package MessageBox.ClientSide.UI.Thread;


import MessageBox.Message;
import MessageBox.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A thread that can send a message to the server
 */
public class SenderThread extends ClientThread {

    private final Message message;

    public SenderThread(String ip, int serverPort, Message message) throws IOException{
        super(ip, serverPort);
        this.message = message;
    }

    @Override
    protected void handleStreams(ObjectInputStream inputStream, ObjectOutputStream outputStream) throws IOException {
        Request request = new Request(message);
        outputStream.writeObject(request);
    }
}
