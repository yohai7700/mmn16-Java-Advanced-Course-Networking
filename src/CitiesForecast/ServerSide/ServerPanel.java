package CitiesForecast.ServerSide;

import javax.swing.*;

public class ServerPanel extends JPanel {
    private static final int FIELD_WIDTH = 10;

    private final JTextField textField;
    private final JButton setFileButton;

    public ServerPanel(FileListener fileListener){
        textField = new JTextField(FIELD_WIDTH);
        setFileButton = new JButton("Set File");
        setFileButton.addActionListener(actionEvent -> fileListener.setFile(textField.getText()));
        add(textField);
        add(setFileButton);
    }
}
