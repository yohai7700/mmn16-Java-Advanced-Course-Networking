package MessageBox.ClientSide.UI.Thread;


import MessageBox.ClientSide.UI.MessageDraft.UnsubscribedUserHandler;
import MessageBox.ClientSide.UnsubscribedUserException;
import MessageBox.Message;
import MessageBox.Request;

import java.io.IOException;

/**
 * A thread that can send a message to the server
 */
public class SenderThread extends ClientThread {

    private final Message message;
    private final UnsubscribedUserHandler unsubscribedHandler;

    public SenderThread(String ip, int serverPort, Message message, UnsubscribedUserHandler handler) throws IOException{
        super(ip, serverPort);
        this.message = message;
        unsubscribedHandler = handler;
    }

    @Override
    protected void handleStreams() throws IOException {
        Request request = new Request(message);
        objectOutputStream.writeObject(request);
        String serverMessage = null;
        try{
            serverMessage = (String) objectInputStream.readObject();
        }catch (ClassNotFoundException ignored){}
        if(serverMessage != null) unsubscribedHandler.handle(new UnsubscribedUserException(serverMessage));
    }
}
