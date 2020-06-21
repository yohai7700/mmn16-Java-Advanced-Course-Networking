package MessageBox.ServerSide;

import MessageBox.Message;
import javafx.scene.chart.PieChart;

import javax.xml.crypto.Data;
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

        messages.insertMessage((Message) objectInputStream.readObject());

        objectInputStream.close();
        inputStream.close();
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
