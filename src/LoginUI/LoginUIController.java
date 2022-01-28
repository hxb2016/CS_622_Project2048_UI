package LoginUI;

import Game2048_test.App;
import IO.SaveUsersData;
import Tool.OptionPane;
import Tool.CreateBlockArrayData;
import MainUI.MainUI;
import MainUI.MainUIBlocksArrayPaneUpdate;
import Users.RegisteredUser;
import Users.UnRegisteredUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * purpose of this class is to set action listener of buttons in LoginUI
 */
public class LoginUIController {
    public static void setController() {
        // Set controller for Sign in button
        LoginUI.signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = LoginUI.userNameBox.getText().trim().equals("") ? " " : LoginUI.userNameBox.getText();
                //If username in the local data, the user is a registered user
                if (App.usersData != null && App.usersData.containsKey(username)) {
                    //Judge that if the password is right
                    if (Arrays.equals(LoginUI.passwordBox.getPassword(), App.usersData.get(username).password)) {
                        // If the game has been ended
                        if (!App.ifEnd) {
                            App.currentUser = App.usersData.get(username);
                            CreateBlockArrayData.creatBlockArrayData(App.interfaceSize, App.currentUser);
                            MainUIBlocksArrayPaneUpdate.updateUI(MainUI.blocksArray, App.currentUser.currentBlocksArrayData, MainUI.blocksArrayPane);
                            App.loginUI.setVisible(false);
                            App.startTime = new Date().getTime();//init timer
                        } else {
                            RegisteredUser newCurrentUser = (RegisteredUser) (App.usersData.get(username));
                            newCurrentUser.dataExchange(App.currentUser);
                            newCurrentUser.setData();//set the data to prepare for saving
                            App.usersData.put(newCurrentUser.username, newCurrentUser);
                            App.currentUser = newCurrentUser;
                            try {
                                SaveUsersData.saveUsersData(App.usersData, App.userDataPath);
                                OptionPane.setJOptionPaneMessage(MainUI.f, "Successfully Registered and Save!", "Message", null);

                                MainUI.updateLastBestRecord();
                            } catch (IOException ex) {
                                System.out.println("Error happened when save data.");
                                ex.printStackTrace();
                            }
                            App.loginUI.setVisible(false);
                        }

                    } else {
                        OptionPane.setJOptionPaneMessage(MainUI.f, "Wrong Password!", "Message", null);
                    }
                } else {
                    OptionPane.setJOptionPaneMessage(MainUI.f, "Sorry, you have no account!", "Message", null);
                }
            }
        });


        // Set controller for signUp button
        LoginUI.signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (App.ifEnd) {
                    signUpAfterGame();
                } else {
                    signUpBeforeGame();
                }
            }
        });

        // Set controller for creatAccount button
        LoginUI.creatAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginUI.informationArea.remove(LoginUI.creatAccount);
                LoginUI.informationArea.remove(LoginUI.startAsGuest);
                LoginUI.informationArea.add(LoginUI.ageTitle);
                LoginUI.informationArea.add(LoginUI.ageBox);
                LoginUI.informationArea.add(LoginUI.genderTitle);
                LoginUI.informationArea.add(LoginUI.genderBox);

                LoginUI.signInAndUp.remove(LoginUI.signIn);
                LoginUI.signInAndUp.add(LoginUI.signUp);
                LoginUI.signInAndUp.add(LoginUI.cancelSignUp);

                LoginUI.signInAndUp.updateUI();
                LoginUI.informationArea.updateUI();
            }
        });

        // Set controller for cancelSignUp button
        LoginUI.cancelSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LoginUI.informationArea.remove(LoginUI.ageTitle);
                LoginUI.informationArea.remove(LoginUI.ageBox);
                LoginUI.informationArea.remove(LoginUI.genderTitle);
                LoginUI.informationArea.remove(LoginUI.genderBox);
                LoginUI.informationArea.add(LoginUI.creatAccount);
                LoginUI.informationArea.add(LoginUI.startAsGuest);

                LoginUI.signInAndUp.remove(LoginUI.signUp);
                LoginUI.signInAndUp.remove(LoginUI.cancelSignUp);
                LoginUI.signInAndUp.add(LoginUI.signIn);

                LoginUI.signInAndUp.updateUI();
                LoginUI.informationArea.updateUI();
            }
        });

        // Set controller for startAsGuest button
        LoginUI.startAsGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = LoginUI.userNameBox.getText().trim().equals("") ? " " : LoginUI.userNameBox.getText();
                App.currentUser = new UnRegisteredUser(username);

                CreateBlockArrayData.creatBlockArrayData(App.interfaceSize, App.currentUser);
                MainUIBlocksArrayPaneUpdate.updateUI(MainUI.blocksArray, App.currentUser.currentBlocksArrayData, MainUI.blocksArrayPane);
                App.loginUI.setVisible(false);

                App.startTime = new Date().getTime();//init timer
            }
        });


    }

    /**
     * Purpose of this method is to be call by controller of signUp button when the game has not been started
     */
    public static void signUpBeforeGame() {
        String username = LoginUI.userNameBox.getText().trim();
        char[] password = LoginUI.passwordBox.getPassword();
        if (password.length != 0 && !username.equals("")) {
            if (App.usersData == null || !App.usersData.containsKey(username)) {
                int age = Integer.parseInt(LoginUI.ageBox.getText().trim().equals("") ? "18" : LoginUI.ageBox.getText());
                String gender = LoginUI.genderBox.getText();
                App.currentUser = new RegisteredUser(username, password, age, gender);
                assert App.usersData != null;
                App.usersData.put(username, App.currentUser);

                try {
                    SaveUsersData.saveUsersData(App.usersData, App.userDataPath);
                    OptionPane.setJOptionPaneMessage(MainUI.f, "Good Job! Successfully registered!", "Message", null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                App.loginUI.setVisible(false);

                CreateBlockArrayData.creatBlockArrayData(App.interfaceSize, App.currentUser);
                MainUIBlocksArrayPaneUpdate.updateUI(MainUI.blocksArray, App.currentUser.currentBlocksArrayData, MainUI.blocksArrayPane);
                App.loginUI.setVisible(false);

                App.startTime = new Date().getTime();//init timer
            } else {
                OptionPane.setJOptionPaneMessage(MainUI.f, "User name already exists!", "Message", null);
            }
        } else {
            if (password.length == 0) {
                OptionPane.setJOptionPaneMessage(MainUI.f, "Password can't be empty!", "Message", null);
            }
            if (username.equals("")) {
                OptionPane.setJOptionPaneMessage(MainUI.f, "User name can't be empty!", "Message", null);
            }
        }

    }

    /**
     * Purpose of this method is to be call by controller of signUp button when the game has been ended
     */
    public static void signUpAfterGame() {
        String username = LoginUI.userNameBox.getText().trim();
        char[] password = LoginUI.passwordBox.getPassword();
        if (password.length != 0 && !username.equals("")) {
            if (App.usersData == null || !App.usersData.containsKey(username)) {
                int age = Integer.parseInt(LoginUI.ageBox.getText().trim().equals("") ? "18" : LoginUI.ageBox.getText());
                String gender = LoginUI.genderBox.getText();
                RegisteredUser newCurrentUser = new RegisteredUser(username, password, age, gender);
                newCurrentUser.dataExchange(App.currentUser);
                newCurrentUser.setData();//set the data to prepare for saving
                App.usersData.put(newCurrentUser.username, newCurrentUser);
                App.currentUser = newCurrentUser;
                try {
                    SaveUsersData.saveUsersData(App.usersData, App.userDataPath);
                    OptionPane.setJOptionPaneMessage(MainUI.f, "Successfully Registered and Save!", "Message", null);

                    MainUI.updateLastBestRecord();
                } catch (IOException e) {
                    System.out.println("Error happened when save data.");
                    e.printStackTrace();
                }
                App.loginUI.setVisible(false);
            } else {
                OptionPane.setJOptionPaneMessage(MainUI.f, "User name already exists!", "Message", null);
            }
        } else {
            if (password.length == 0) {
                OptionPane.setJOptionPaneMessage(MainUI.f, "Password can't be empty!", "Message", null);
            }
            if (username.equals("")) {
                OptionPane.setJOptionPaneMessage(MainUI.f, "User name can't be empty!", "Message", null);
            }
        }
    }
}
