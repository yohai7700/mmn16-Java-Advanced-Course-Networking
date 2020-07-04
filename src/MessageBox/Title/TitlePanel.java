package MessageBox.Title;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    private static final Color DISABLED_COLOR = Color.LIGHT_GRAY,
            DEFAULT_COLOR = new Color(0x8f9bff);

    private final TitleLabel titleLabel;
    //called theme color and background color, so that it won't class with super.backgroundColor
    private Color themeColor;

    public TitlePanel(){
        titleLabel = new TitleLabel();
        setTheme(DEFAULT_COLOR);
        add(titleLabel);
    }

    public TitlePanel(String title){
        this();
        setTitle(title);
    }

    public void setTitle(String title){
        titleLabel.setText(title);
        setMaximumSize(getPreferredSize());
    }

    public String getTitle(){
        return titleLabel.getText();
    }

    public void setTheme(Color theme) {
        setBackground(theme);
        themeColor = theme;
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if(titleLabel != null)
            titleLabel.setBackground(bg);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setBackground(enabled ? themeColor : DISABLED_COLOR);
    }
}
