package MessageBox.ServerSide.UI;

import javax.swing.*;

public class AddUserPanel extends JPanel {
    private static final int textFieldWidth = 10;

    private final JTextField userNameTextField;
    private final JButton addUserButton;
    private final OnAddListener onAddListener;

    public AddUserPanel(OnAddListener onAddListener){
        userNameTextField = new JTextField();
        userNameTextField.setColumns(10
        );
        addUserButton = new JButton("Add");
        setAddUserButton();
        this.onAddListener = onAddListener;
        addComponents();
    }

    private void addComponents(){
        add(userNameTextField);
        add(addUserButton);
    }

    private void setAddUserButton(){
        addUserButton.addActionListener(actionEvent -> onAddListener.onAdd(userNameTextField.getText().strip()));
    }

    public interface OnAddListener{
        void onAdd(String user);
    }
}
