package LoginUI;

import javax.swing.*;
import java.awt.*;
/**
 * Purpose of this class is to create label for user information title in LoginUI
 */
public class LoginUILabel extends JLabel {
    public LoginUILabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        this.setPreferredSize(new Dimension(200,30));
        this.setFont(new Font("Times New Roman",Font.BOLD,18));
    }
}
