package MainUI;

import Game2048_test.App;
import Operation.Operate;
import Tool.CreateBlockArrayData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * purpose of this class is to set action listener of buttons in MainUI
 */
public class MainUIController {
    /**
     * purpose of the method is to set action listener of buttons in MainUI
     */
    public static void setUIController() {
        // set action listener for up button in MainUI
        MainUI.up.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!App.ifEnd) {
                        Operate.operation(38, App.currentUser);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // set action listener for left button in MainUI
        MainUI.left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!App.ifEnd) {
                        Operate.operation(37, App.currentUser);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // set action listener for down button in MainUI
        MainUI.down.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!App.ifEnd) {
                        Operate.operation(40, App.currentUser);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // set action listener for right button in MainUI
        MainUI.right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!App.ifEnd) {
                        Operate.operation(39, App.currentUser);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // set action listener for New Game button in MainUI
        MainUI.newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CreateBlockArrayData.creatBlockArrayData(App.interfaceSize, App.currentUser);//init current user's block array data
                    MainUIBlocksArrayPaneUpdate.updateUI(MainUI.blocksArray, App.currentUser.currentBlocksArrayData, MainUI.blocksArrayPane);//update UI
                    App.startTime = new Date().getTime();//init timer
                    App.ifEnd = false;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });
    }
}
