package MessageBox.ServerSide.UI;

import MessageBox.OnDeleteListener;

import javax.swing.*;

public class UserItemPanel extends JPanel{

    private final JLabel userNameLabel;
    private final JButton removeButton;
    private OnDeleteListener onDeleteListener;

    public UserItemPanel(String userName, OnDeleteListener onDeleteListener){
        userNameLabel = new JLabel();
        setUserName(userName);
        removeButton = new JButton("Remove");
        this.onDeleteListener = onDeleteListener;
        removeButton.addActionListener(actionEvent -> onDeleteListener.onDelete(userNameLabel.getText()));
        add(userNameLabel);
        add(removeButton);
        setMaximumSize(getPreferredSize());
    }

    public String getUserName() {
        return userNameLabel.getText();
    }

    public void setUserName(String userName) {
        this.userNameLabel.setText(userName);
    }

    public OnDeleteListener getOnDeleteListener() {
        return onDeleteListener;
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener) {

        this.onDeleteListener = onDeleteListener;
        String user = userNameLabel.getText();
        removeButton.addActionListener(actionEvent -> onDeleteListener.onDelete(user));
    }
}
