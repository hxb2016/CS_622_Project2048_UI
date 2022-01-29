package Game2048_test;

import LoginUI.LoginUI;
import LoginUI.LoginUIController;
import IO.GetUsersData;
import MainUI.MainUI;
import MainUI.MainUIController;
import Users.User;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The purpose of this class is to run the game
 * Notes: In order to save time for test, I change the winning number from 2048 to 16 (WinNum = 16).
 * Because it will very long time if the number is too large. which means that any number is larger than 16,
 * the game will judged that you win.
 */
public class App {
    public static User currentUser = null;
    public final static int interfaceSize = 4;// Purpose of this parameter is to decide the blockArray's size
    public final static int WinNum = 16;// You will win the game, if there is any number larger than WinNum
    public static Map<String, User> usersData = null;
    public static String userDataPath = "src" + File.separator + "UserData" + File.separator + "Data.txt";
    public static LoginUI loginUI = null;
    public static MainUI mainUI = null;
    public static boolean ifEnd = false;// Judge that the game end or not
    public static long startTime = 0;// Purpose of the parameter is to record time you have taken

    public static void main(String[] args) {
        try {
            usersData = GetUsersData.getUsersData(userDataPath);//Get users' data
            if (usersData == null) {
                usersData = new HashMap<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error happened when get data.");
            e.printStackTrace();
        }

        mainUI = new MainUI();//init JFrame
        MainUIController.setUIController();//Set controller for UI

        loginUI = new LoginUI(mainUI);//Get login pane and show on screen
        LoginUIController.setController();// Set actions of the buttons on login pane
        loginUI.setVisible(true);//Show login pane on screen
    }

}
