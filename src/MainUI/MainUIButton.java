package MainUI;

import Tool.KeyBoardListener;

import javax.swing.*;
import java.awt.*;

/**
 * the purpose of this class is to create a button for MainUI
 */
public class MainUIButton extends JButton {
    public MainUIButton(String text, ImageIcon icon) {
        if (text != null) {
            this.setText(text);
            this.setFont(new Font("Times New Roman", Font.BOLD, 18));
            this.setForeground(Color.WHITE);
        } else {
            this.setIcon(icon);
        }
        this.setMargin(new Insets(5, 10, 5, 10));
        this.setFocusPainted(false);
        this.setBackground(new Color(18, 150, 219));

        KeyBoardListener<MainUIButton> keyBoardListener = new KeyBoardListener<>(this);
        keyBoardListener.setListener();
    }
}
