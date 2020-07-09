package MessageBox.ClientSide;

import MessageBox.ClientSide.UI.Thread.DownloaderThread;
import MessageBox.ClientSide.UI.Thread.SenderThread;
import MessageBox.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Client{
    public static final String LOOPBACK_IP = "127.0.0.1";
    public static final int DEFAULT_SERVER_PORT = 9876;

    private String ip;
    private int serverPort;

    public Client() throws IOException{
        this(LOOPBACK_IP, DEFAULT_SERVER_PORT);
    }

    public Client(String ip, int port) throws IOException{
        this.ip = ip;
        serverPort = port;
    }

    public void sendMessage(Message message) throws IOException, UnsubscribedUserException{
        (new SenderThread(ip, serverPort, message)).start();
    }

    public List<Message> downloadMessages(String user) throws IOException, UnsubscribedUserException {
        List<Message> messages = new ArrayList<>();
        DownloaderThread downloaderThread = new DownloaderThread(ip, serverPort, user, messages::addAll);
        downloaderThread.start();
        try {
            //waiting to thread for finish downloading - dying
            downloaderThread.join();
        } catch (InterruptedException ignored){return null;}
        return messages;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
