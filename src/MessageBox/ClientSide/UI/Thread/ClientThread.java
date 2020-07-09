package MessageBox.ClientSide.UI.Thread;

import java.io.*;
import java.net.Socket;

/**
 * Abstract client thread to communicate with server
 */
public abstract class ClientThread extends Thread{
    protected String ip;
    protected int serverPort;
    protected Socket socket;
    protected InputStream inputStream;
    protected OutputStream outputStream;
    protected ObjectInputStream objectInputStream;
    protected ObjectOutputStream objectOutputStream;

    public ClientThread(String ip, int serverPort) throws IOException {
        this.ip = ip;
        this.serverPort = serverPort;
        socket = new Socket(ip, serverPort);
        outputStream = socket.getOutputStream();
        objectOutputStream = new ObjectOutputStream(outputStream);
        inputStream = socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
    }

    @Override
    public void run() {
        super.run();
        try{
            handleConnection();
        }catch (Exception ignored){}
    }

    private void handleConnection() throws IOException{
        handleStreams(objectInputStream, objectOutputStream);

        objectInputStream.close();
        inputStream.close();
        objectOutputStream.close();
        outputStream.close();
    }
    
    protected abstract void handleStreams(ObjectInputStream inputStream, ObjectOutputStream outputStream) throws IOException;
}
