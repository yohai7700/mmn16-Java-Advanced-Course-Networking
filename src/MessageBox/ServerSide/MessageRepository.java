package MessageBox.ServerSide;

import MessageBox.Message;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageRepository {
    private final HashMap<String, ArrayList<Message>> messages;

    public MessageRepository(){
        messages = new HashMap<>();
    }

    public List<Message> getMessages(String userName){
        return messages.get(userName);
    }

    public void insertMessage(Message message){
        String userName = message.getReceiverName();
        if(messages.containsKey(userName))
            messages.get(userName).add(message);
        else{
            ArrayList<Message> userMessages = new ArrayList<>();
            userMessages.add(message);
            messages.put(userName, userMessages);
        }
    }

    public void insertMessages(List<Message> messages){
        for(Message message : messages)
            insertMessage(message);
    }
}
