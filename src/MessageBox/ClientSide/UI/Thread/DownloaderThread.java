package MessageBox.ClientSide.UI.Thread;

import MessageBox.Message;
import MessageBox.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DownloaderThread extends ClientThread{
    private final String user;
    private final DownloadListener downloadListener;

    public DownloaderThread(String ip, int serverPort, String user, DownloadListener downloadListener) throws IOException {
        super(ip, serverPort);
        this.user = user;
        this.downloadListener = downloadListener;
    }

    @Override
    protected void handleStreams(ObjectInputStream inputStream, ObjectOutputStream outputStream) throws IOException {
        requestDownloadMessages(outputStream);
        try {
            downloadListener.onDownload(receiveMessages(inputStream));
        }catch(ClassNotFoundException ignored){}
    }

    private void requestDownloadMessages(ObjectOutputStream outputStream) throws IOException{
        Request request = new Request(user);
        outputStream.writeObject(request);
    }

    private List<Message> receiveMessages(ObjectInputStream inputStream) throws IOException, ClassNotFoundException{
        ArrayList<Message> messages = new ArrayList<>();
        Message message = (Message)inputStream.readObject();
        for(; !message.isEmpty(); message = (Message) inputStream.readObject())
            messages.add(message);
        return messages;
    }
}