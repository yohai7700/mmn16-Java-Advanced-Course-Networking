package MessageBox.ClientSide.UI.Thread;

import java.io.*;
import java.net.Socket;

public abstract class ClientThread extends Thread{
    String ip;
    int serverPort;
    Socket socket;

    public ClientThread(String ip, int serverPort) throws IOException {
        this.ip = ip;
        this.serverPort = serverPort;
        socket = new Socket(ip, serverPort);
    }

    @Override
    public void run() {
        super.run();
        try{
            handleConnection();
        }catch (Exception ignored){}
    }

    private void handleConnection() throws Exception{
        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        handleStreams(objectInputStream, objectOutputStream);

        objectInputStream.close();
        inputStream.close();
        objectOutputStream.close();
        outputStream.close();
    }
    
    protected abstract void handleStreams(ObjectInputStream inputStream, ObjectOutputStream outputStream) throws IOException;
}
