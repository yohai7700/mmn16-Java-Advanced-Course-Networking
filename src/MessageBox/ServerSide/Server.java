package MessageBox.ServerSide;

import MessageBox.Request;
import MessageBox.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Set;

public class Server extends Thread {
    public static final int PORT = 9876;

    MessageRepository messages;
    ServerSocket socket;
    Socket clientSocket;

    public Server() {
        messages = new MessageRepository();
    }

    @Override
    public void run() {
        try { //required catch block
            socket = new ServerSocket(PORT);
            try {
                while (true) {
                    clientSocket = socket.accept();
                    System.out.println("Handling Connections");
                    handleConnection();
                }
            } catch (Exception e) {
                System.out.println("Exception in handling Connections");
                e.printStackTrace();
                socket.close();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

        private void handleConnection () throws Exception {
            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            handleConnection(objectInputStream, objectOutputStream);

            objectOutputStream.close();
            outputStream.close();
            objectInputStream.close();
            inputStream.close();
        }

        private void handleConnection (ObjectInputStream inputStream, ObjectOutputStream outputStream){
            Request request = null;
            try {
                request = (Request) inputStream.readObject();
            } catch (ClassNotFoundException | IOException exception) {
                exception.printStackTrace();
                return;
            }
            System.out.println("Request" + request);
            handleRequest(request, outputStream);
        }

        private void handleRequest (Request request, ObjectOutputStream outputStream){
            System.out.println("Got request: " + request);
            switch (request.getType()) {
                case UPLOAD:
                    uploadMessage(request.getMessage());
                    break;
                case DOWNLOAD:
                    sendUserMessages(request.getUserName(), outputStream);
                    break;
            }
        }

        private void uploadMessage (Message message){
            if(messages.containsUser(message.getReceiverName()))
                messages.insertMessage(message);
        }

        private void sendUserMessages (String userName, ObjectOutputStream outputStream){
            List<Message> userMessages = messages.getMessages(userName);
            for (Message message : userMessages)
                try {
                    outputStream.writeObject(message);
                } catch (IOException ignored) { }

            do { //tries to send an empty message to signal all messages sent, until successful
                try {
                    outputStream.writeObject(Message.createEmptyMessage());
                    return;
                } catch (IOException ignored) { }
            } while (true);
        }

        public void removeUser (String user){
            messages.removeUser(user);
        }

        public void addUser (String user){
            messages.addUser(user);
        }

        public boolean containsUser (String user){
            return messages.containsUser(user);
        }

        public Set<String> getUsers () {
            return messages.getUsers();
        }
    }
