package MessageBox.ServerSide;

import MessageBox.Data;
import MessageBox.Message;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 9876;

    MessageRepository messages;
    ServerSocket socket;
    Socket clientSocket;

    public Server(){
        messages = new MessageRepository();
        try { //required catch block
            socket = new ServerSocket(PORT);
            while(true){
                try {
                    clientSocket = socket.accept();
                    handleConnection();
                } catch(Exception ignored){ socket.close(); }
            }
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

    public static void main(String[] args) {
        Server server = new Server();
    }
}
