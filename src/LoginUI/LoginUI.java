package LoginUI;

import Game2048_test.App;
import MainUI.MainUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * purpose of this class is to create a login ui interface
 */
public class LoginUI {
    public static JTextField userNameBox = null;
    public static JPasswordField passwordBox = null;
    public static JLabel ageTitle = null;
    public static JTextField ageBox = null;
    public static JLabel genderTitle = null;
    public static JTextField genderBox = null;
    public static LoginUIButton signIn = null;
    public static LoginUIButton signUp = null;
    public static LoginUIButton cancelSignUp = null;
    public static LoginUIButton creatAccount = null;
    public static LoginUIButton startAsGuest = null;
    public static JPanel signInAndUp = null;
    public static JPanel informationArea = null;

    public static JDialog getLoginUI() {
        JDialog loginPane = new JDialog(MainUI.f);
        loginPane.setResizable(false);
        loginPane.setTitle("LoginUI");
        ImageIcon logo = new ImageIcon("src" + File.separator + "Image" + File.separator + "2048.png");
        loginPane.setIconImage(logo.getImage());
        loginPane.setLayout(new BorderLayout());
        loginPane.setSize(300, 400); // 设置大小
        loginPane.setLocationRelativeTo(null); // 相对屏幕居中
        loginPane.setModal(true);
        loginPane.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        loginPane.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!App.ifEnd) {// If you have not started the game, the game will quit when you close the login pane.
                    System.exit(0);
                }
            }
        });

        //////////////////////////information area////////////////////////////////
        //this area includes username, password, age, gender, creatAccount and startAsGuest components
        informationArea = new JPanel();

        JLabel userNameTitle = new LoginUILabel("User name:", SwingConstants.CENTER);
        userNameBox = new LoginUITextField();

        JLabel passwordTitle = new LoginUILabel("Password:", SwingConstants.CENTER);
        passwordBox = new JPasswordField();
        passwordBox.setPreferredSize(new Dimension(200, 30));

        creatAccount = new LoginUIButton("Creat Account");
        startAsGuest = new LoginUIButton("Start as Guest");

        ageTitle = new LoginUILabel("Age:", SwingConstants.CENTER);
        ageBox = new LoginUITextField();

        genderTitle = new LoginUILabel("Gender:", SwingConstants.CENTER);
        genderBox = new LoginUITextField();


        informationArea.add(userNameTitle);
        informationArea.add(userNameBox);
        informationArea.add(passwordTitle);
        informationArea.add(passwordBox);
        informationArea.add(creatAccount);
        informationArea.add(startAsGuest);
        //////////////////////////information area end////////////////////////////

        signInAndUp = new JPanel();
        signInAndUp.setLayout(new GridLayout(1, 2));
        signInAndUp.setSize(loginPane.getWidth(), 50);

        signIn = new LoginUIButton("Sign in");// sign in button
        signInAndUp.add(signIn, BorderLayout.CENTER);

        signUp = new LoginUIButton("Sign up");// sign up button
        cancelSignUp = new LoginUIButton("Cancel");

        loginPane.add(signInAndUp, BorderLayout.SOUTH);
        loginPane.add(informationArea, BorderLayout.CENTER);

        return loginPane;
    }

}
