package MessageBox.Title;

import javax.swing.*;
import java.awt.*;

public class TitleLabel extends JLabel{
    public static final Color DEFAULT_BACKGROUND = Color.BLACK;

    public TitleLabel(){
        super();
        setOpaque(true);
        setBackground(DEFAULT_BACKGROUND);
    }

    public TitleLabel(String title){
        this();
        setText(title);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        setMaximumSize(getPreferredSize());
    }
}
