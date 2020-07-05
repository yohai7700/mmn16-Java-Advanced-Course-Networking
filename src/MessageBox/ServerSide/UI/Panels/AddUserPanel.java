package MessageBox.ServerSide.UI.Panels;

import javax.swing.*;
import java.awt.*;

public class AddUserPanel extends JPanel {
    private static final int TEXT_FIELD_WIDTH = 10;

    private final JTextField userNameTextField;
    private final JButton addUserButton;
    private final OnAddListener onAddListener;

    public AddUserPanel(OnAddListener onAddListener){
        setBackground(Color.LIGHT_GRAY);
        userNameTextField = new JTextField();
        addUserButton = new JButton("Add");
        this.onAddListener = onAddListener;
        setUserTextField();
        setAddUserButton();
        addComponents();
    }

    private void addComponents(){
        add(userNameTextField);
        add(addUserButton);
    }

    private void setAddUserButton(){
        addUserButton.addActionListener(actionEvent ->
                onAddListener.onAdd(userNameTextField.getText().strip()));
    }

    private void setUserTextField(){
        userNameTextField.setColumns(TEXT_FIELD_WIDTH);
        userNameTextField.addActionListener(actionEvent ->
                onAddListener.onAdd(userNameTextField.getText()));
    }

    public interface OnAddListener{
        void onAdd(String user);
    }
}
