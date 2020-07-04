package MessageBox.ClientSide;

import MessageBox.Request;
import MessageBox.Message;
import MessageBox.ServerSide.Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client{
    public static final String LOOPBACK_IP = "127.0.0.1";

    private String ip;
    private int serverPort;
    private Socket socket;

    public Client() throws IOException{
        this(LOOPBACK_IP, Server.PORT);
    }

    public Client(String ip, int port) throws IOException{
        this.ip = ip;
        serverPort = port;
        updateSocket();
    }

    public void sendMessage(Message message) throws IOException{
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        Request request = new Request(message);

        objectOutputStream.writeObject(request);
        objectOutputStream.close();
        outputStream.close();
    }

    public List<Message> downloadMessages(String user) throws IOException, ClassNotFoundException{
        requestDownloadMessages(user);
        return receiveMessages();
    }

    private void requestDownloadMessages(String user) throws IOException{
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        Request request = new Request(user);

        objectOutputStream.writeObject(request);
        objectOutputStream.close();
        outputStream.close();
    }

    private List<Message> receiveMessages() throws IOException, ClassNotFoundException{
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ArrayList<Message> messages = new ArrayList<>();

        Request request = (Request)objectInputStream.readObject();
        for(; request.getMessage() != null; request = (Request)objectInputStream.readObject())
            messages.add(request.getMessage());

        objectInputStream.close();
        inputStream.close();
        return messages;
    }

    private void updateSocket() throws IOException{
        socket = new Socket(ip, serverPort);
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
