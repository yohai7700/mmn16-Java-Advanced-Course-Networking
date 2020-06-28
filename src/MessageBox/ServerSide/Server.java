package MessageBox.ServerSide;

import MessageBox.Data;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

public class Server extends Thread{
    public static final int PORT = 9876;

    MessageRepository messages;
    ServerSocket socket;
    Socket clientSocket;

    public Server(){
        messages = new MessageRepository();
    }

    @Override
    public void run() {
        try { //required catch block
            socket = new ServerSocket(PORT);
            while(true)
                try {
                    clientSocket = socket.accept();
                    handleConnection();
                } catch(Exception ignored){ socket.close(); }
        } catch (IOException exception){ exception.printStackTrace(); }
    }

    private void handleConnection() throws Exception {
        InputStream inputStream = clientSocket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        OutputStream outputStream = clientSocket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        handleData((Data)objectInputStream.readObject(), objectOutputStream);

        objectOutputStream.close();
        outputStream.close();
        objectInputStream.close();
        inputStream.close();
    }

    private void handleData(Data data, ObjectOutputStream outputStream){
        switch(data.getRequest()){
            case UPLOAD:
                messages.insertMessages(data.getMessages());
            case DOWNLOAD:
                sendUserMessages(data.getUserName(), outputStream);
        }
    }

    private void sendUserMessages(String userName, ObjectOutputStream outputStream){
    }

    public void removeUser(String user){
        messages.removeUser(user);
    }

    public void addUser(String user){
        messages.addUser(user);
    }

    public Set<String> getUsers() {
        return messages.getUsers();
    }
}
