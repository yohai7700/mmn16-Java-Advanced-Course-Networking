package MessageBox.ClientSide.UI;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Simple panel with a text field and a name for thet text field
 * so that user understands what it means.
 */
public class FieldPanel extends JPanel{
    private static final int FIELD_WIDTH = 20;

    private String fieldName;
    private final JLabel nameLabel;
    private final JTextField textField;
    private ActionListener fieldActionListener;

    public FieldPanel(String name){
        nameLabel = new JLabel();
        textField = new JTextField();
        setFieldName(name);
        add(nameLabel);
        add(textField);
        setMaximumSize(getPreferredSize());
    }

    public String getFieldName() {
        return fieldName;
    }

    public ActionListener getFieldActionListener() {
        return fieldActionListener;
    }

    public void setFieldActionListener(ActionListener fieldActionListener) {
        this.fieldActionListener = fieldActionListener;
        if(textField.getActionListeners().length > 0)
            textField.removeActionListener(getFieldActionListener());
        textField.addActionListener(fieldActionListener);
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
        nameLabel.setText(fieldName);
        updateSize();
        repaint();
    }

    private void updateSize(){
        textField.setColumns(FIELD_WIDTH);
    }

    public void setText(String text){ textField.setText(text);}

    public String getText(){
        return textField.getText();
    }
}
