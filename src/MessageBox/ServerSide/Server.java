package MessageBox.ServerSide;

import MessageBox.Request;
import MessageBox.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Set;

public class Server extends Thread {
    private static final int PORT = 9876;
    private static final String USER_DOESNT_EXIST_MESSAGE = "isn't subscribed to the server.\nCan't send message.\n";

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
                    handleConnection();
                }
            } catch (Exception e) {
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
            Request request;
            try {
                request = (Request) inputStream.readObject();
            } catch (ClassNotFoundException | IOException exception) {
                exception.printStackTrace();
                return;
            }
            handleRequest(request, outputStream);
        }

        private void handleRequest (Request request, ObjectOutputStream outputStream){
            switch (request.getType()) {
                case UPLOAD:
                    uploadMessage(request.getMessage(), outputStream);
                    break;
                case DOWNLOAD:
                    sendUserMessages(request.getUserName(), outputStream);
                    break;
            }
        }

        private void uploadMessage (Message message, ObjectOutputStream outputStream){
            if(messages.containsUser(message.getReceiverName())) {
                messages.insertMessage(message);
            }
            try{ //writing server message if user doesn't exit on server
                outputStream.writeObject(messages.containsUser(message.getReceiverName()) ?
                        null : message.getReceiverName() + " " + USER_DOESNT_EXIST_MESSAGE);
            }catch (IOException ignored){}
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
                    break;
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
