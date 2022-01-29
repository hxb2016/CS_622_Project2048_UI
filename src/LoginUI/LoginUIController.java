package LoginUI;

import Game2048_test.App;
import IO.SaveUsersData;
import Tool.OptionPane;
import Tool.CreateBlockArrayData;
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
        App.loginUI.signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = App.loginUI.userNameBox.getText().trim().equals("") ? " " : App.loginUI.userNameBox.getText();
                //If username in the local data, the user is a registered user
                if (App.usersData != null && App.usersData.containsKey(username)) {
                    //Judge that if the password is right
                    if (Arrays.equals(App.loginUI.passwordBox.getPassword(), App.usersData.get(username).password)) {
                        // If the game has been ended
                        if (!App.ifEnd) {
                            App.currentUser = App.usersData.get(username);
                            CreateBlockArrayData.creatBlockArrayData(App.interfaceSize, App.currentUser);
                            MainUIBlocksArrayPaneUpdate.updateUI(App.mainUI.blocksArray, App.currentUser.currentBlocksArrayData, App.mainUI.blocksArrayPane);
                            App.loginUI.setVisible(false);
                            App.startTime = new Date().getTime();//init timer

                            App.mainUI.updateLastBestRecord();
                        } else {
                            RegisteredUser newCurrentUser = (RegisteredUser) (App.usersData.get(username));
                            newCurrentUser.dataExchange(App.currentUser);
                            newCurrentUser.setData();//set the data to prepare for saving
                            App.usersData.put(newCurrentUser.username, newCurrentUser);
                            App.currentUser = newCurrentUser;
                            try {
                                SaveUsersData.saveUsersData(App.usersData, App.userDataPath);
                                OptionPane.setJOptionPaneMessage(App.mainUI, "Successfully Registered and Save!", "Message", null);

                                App.mainUI.updateLastBestRecord();
                            } catch (IOException ex) {
                                System.out.println("Error happened when save data.");
                                ex.printStackTrace();
                            }
                            App.loginUI.setVisible(false);
                        }

                    } else {
                        OptionPane.setJOptionPaneMessage(App.mainUI, "Wrong Password!", "Message", null);
                    }
                } else {
                    OptionPane.setJOptionPaneMessage(App.mainUI, "Sorry, you have no account!", "Message", null);
                }
            }
        });


        // Set controller for signUp button
        App.loginUI.signUp.addActionListener(new ActionListener() {
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
        App.loginUI.creatAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.loginUI.informationArea.remove(App.loginUI.creatAccount);
                App.loginUI.informationArea.remove(App.loginUI.startAsGuest);
                App.loginUI.informationArea.add(App.loginUI.ageTitle);
                App.loginUI.informationArea.add(App.loginUI.ageBox);
                App.loginUI.informationArea.add(App.loginUI.genderTitle);
                App.loginUI.informationArea.add(App.loginUI.genderBox);

                App.loginUI.signInAndUp.remove(App.loginUI.signIn);
                App.loginUI.signInAndUp.add(App.loginUI.signUp);
                App.loginUI.signInAndUp.add(App.loginUI.cancelSignUp);

                App.loginUI.signInAndUp.updateUI();
                App.loginUI.informationArea.updateUI();
            }
        });

        // Set controller for cancelSignUp button
        App.loginUI.cancelSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                App.loginUI.informationArea.remove(App.loginUI.ageTitle);
                App.loginUI.informationArea.remove(App.loginUI.ageBox);
                App.loginUI.informationArea.remove(App.loginUI.genderTitle);
                App.loginUI.informationArea.remove(App.loginUI.genderBox);
                App.loginUI.informationArea.add(App.loginUI.creatAccount);
                App.loginUI.informationArea.add(App.loginUI.startAsGuest);

                App.loginUI.signInAndUp.remove(App.loginUI.signUp);
                App.loginUI.signInAndUp.remove(App.loginUI.cancelSignUp);
                App.loginUI.signInAndUp.add(App.loginUI.signIn);

                App.loginUI.signInAndUp.updateUI();
                App.loginUI.informationArea.updateUI();
            }
        });

        // Set controller for startAsGuest button
        App.loginUI.startAsGuest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = App.loginUI.userNameBox.getText().trim().equals("") ? " " : App.loginUI.userNameBox.getText();
                App.currentUser = new UnRegisteredUser(username);

                CreateBlockArrayData.creatBlockArrayData(App.interfaceSize, App.currentUser);
                MainUIBlocksArrayPaneUpdate.updateUI(App.mainUI.blocksArray, App.currentUser.currentBlocksArrayData, App.mainUI.blocksArrayPane);
                App.loginUI.setVisible(false);

                App.startTime = new Date().getTime();//init timer
            }
        });


    }

    /**
     * Purpose of this method is to be call by controller of signUp button when the game has not been started
     */
    public static void signUpBeforeGame() {
        String username = App.loginUI.userNameBox.getText().trim();
        char[] password = App.loginUI.passwordBox.getPassword();
        if (password.length != 0 && !username.equals("")) {
            if (App.usersData == null || !App.usersData.containsKey(username)) {
                int age = Integer.parseInt(App.loginUI.ageBox.getText().trim().equals("") ? "18" : App.loginUI.ageBox.getText());
                String gender = App.loginUI.genderBox.getText();
                App.currentUser = new RegisteredUser(username, password, age, gender);
                assert App.usersData != null;
                App.usersData.put(username, App.currentUser);

                try {
                    SaveUsersData.saveUsersData(App.usersData, App.userDataPath);
                    OptionPane.setJOptionPaneMessage(App.mainUI, "Good Job! Successfully registered!", "Message", null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                App.loginUI.setVisible(false);

                CreateBlockArrayData.creatBlockArrayData(App.interfaceSize, App.currentUser);
                MainUIBlocksArrayPaneUpdate.updateUI(App.mainUI.blocksArray, App.currentUser.currentBlocksArrayData, App.mainUI.blocksArrayPane);
                App.loginUI.setVisible(false);

                App.startTime = new Date().getTime();//init timer
            } else {
                OptionPane.setJOptionPaneMessage(App.mainUI, "User name already exists!", "Message", null);
            }
        } else {
            if (password.length == 0) {
                OptionPane.setJOptionPaneMessage(App.mainUI, "Password can't be empty!", "Message", null);
            }
            if (username.equals("")) {
                OptionPane.setJOptionPaneMessage(App.mainUI, "User name can't be empty!", "Message", null);
            }
        }

    }

    /**
     * Purpose of this method is to be call by controller of signUp button when the game has been ended
     */
    public static void signUpAfterGame() {
        String username = App.loginUI.userNameBox.getText().trim();
        char[] password = App.loginUI.passwordBox.getPassword();
        if (password.length != 0 && !username.equals("")) {
            if (App.usersData == null || !App.usersData.containsKey(username)) {
                int age = Integer.parseInt(App.loginUI.ageBox.getText().trim().equals("") ? "18" : App.loginUI.ageBox.getText());
                String gender = App.loginUI.genderBox.getText();
                RegisteredUser newCurrentUser = new RegisteredUser(username, password, age, gender);
                newCurrentUser.dataExchange(App.currentUser);
                newCurrentUser.setData();//set the data to prepare for saving
                App.usersData.put(newCurrentUser.username, newCurrentUser);
                App.currentUser = newCurrentUser;
                try {
                    SaveUsersData.saveUsersData(App.usersData, App.userDataPath);
                    OptionPane.setJOptionPaneMessage(App.mainUI, "Successfully Registered and Save!", "Message", null);

                    App.mainUI.updateLastBestRecord();
                } catch (IOException e) {
                    System.out.println("Error happened when save data.");
                    e.printStackTrace();
                }
                App.loginUI.setVisible(false);
            } else {
                OptionPane.setJOptionPaneMessage(App.mainUI, "User name already exists!", "Message", null);
            }
        } else {
            if (password.length == 0) {
                OptionPane.setJOptionPaneMessage(App.mainUI, "Password can't be empty!", "Message", null);
            }
            if (username.equals("")) {
                OptionPane.setJOptionPaneMessage(App.mainUI, "User name can't be empty!", "Message", null);
            }
        }
    }
}
