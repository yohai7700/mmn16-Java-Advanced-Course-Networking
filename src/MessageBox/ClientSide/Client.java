package MessageBox.ClientSide;

import MessageBox.Data;
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

    public Client(){
        ip = LOOPBACK_IP;
        serverPort = Server.PORT;
        try {
            socket = new Socket(ip, serverPort);
        }catch (IOException ignored){}
    }

    public Client(String ip, int port){
        this.ip = ip;
        serverPort = port;
    }

    public void uploadMessage(Message message) throws IOException{
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        Data data = new Data(message);

        objectOutputStream.writeObject(data);
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

        Data data = new Data(user);

        objectOutputStream.writeObject(data);
        objectOutputStream.close();
        outputStream.close();
    }

    private List<Message> receiveMessages() throws IOException, ClassNotFoundException{
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ArrayList<Message> messages = new ArrayList<>();

        Data data = (Data)objectInputStream.readObject();
        for(; data.getMessage() != null; data = (Data)objectInputStream.readObject())
            messages.add(data.getMessage());

        objectInputStream.close();
        inputStream.close();
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
