package MessageBox.ServerSide.UI;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UsersPanel extends JPanel {
    private final List<UserItemPanel> userItemPanels;
    private final OnDeleteListener onDeleteListener;

    public UsersPanel(OnDeleteListener onDeleteListener){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        userItemPanels = new ArrayList<>();
        this.onDeleteListener = onDeleteListener;
    }

    public UsersPanel(List<String> users, OnDeleteListener onDeleteListener){
        this(onDeleteListener);
        for(String user: users)
            addUser(user);
    }

    public void addUser(String user){
        UserItemPanel userItemPanel = new UserItemPanel(user, onDeleteListener);
        userItemPanels.add(userItemPanel);
        add(userItemPanel);
        revalidate();
        repaint();
    }

    public void removeUser(String user){
        for(UserItemPanel panel: userItemPanels)
            if(panel.getUserName().equals(user))
                removeUserItemPanel(panel);
    }

    private void removeUserItemPanel(UserItemPanel panel){
        remove(panel);
        revalidate();
        repaint();
    }
}
