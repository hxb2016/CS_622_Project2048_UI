package Operation;

import Block.Block;
import Block.RandomBlock;
import Game2048_test.App;
import Tool.OptionPane;
import MainUI.MainUI;
import MainUI.MainUIBlocksArrayPaneUpdate;
import IO.SaveUsersData;
import Users.RegisteredUser;
import Users.User;

import javax.swing.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
/**
 * The purpose of this class is to deal with moving and combining of block, when the game system receive a command
 */
public class Operate {
    public static boolean ifMoving = false;
    /**
     * The purpose of operation method is to deal with moving and combining of block, when the game system receive a command
     */
    public static void operation(int code, User currentUser) {
        if (code == 27) {
            System.exit(0);
        }
        if (!App.ifEnd) {
            if (code == 37 || code == 38) {
                for (int i = 0; i < currentUser.currentBlocksArrayData.length; i++) {
                    for (int j = 0; j < currentUser.currentBlocksArrayData[i].length; j++) {
                        switch (code) {
                            case 38 -> moveUp(currentUser.currentBlocksArrayData[i][j], currentUser);
                            case 37 -> moveLeft(currentUser.currentBlocksArrayData[i][j], currentUser);
                        }
                    }
                }
            } else {
                for (int i = currentUser.currentBlocksArrayData.length - 1; i >= 0; i--) {
                    for (int j = currentUser.currentBlocksArrayData[i].length - 1; j >= 0; j--) {
                        switch (code) {
                            case 40 -> moveDown(currentUser.currentBlocksArrayData[i][j], currentUser);
                            case 39 -> moveRight(currentUser.currentBlocksArrayData[i][j], currentUser);
                        }
                    }

                }
            }

            if (ifMoving) {//it will generate a random block, if any block move
                Block randomBlock = new RandomBlock(currentUser);
                currentUser.currentBlocksArrayData[randomBlock.location[0]][randomBlock.location[1]] = randomBlock;
                ifMoving = false;
            }
            MainUIBlocksArrayPaneUpdate.updateUI(MainUI.blocksArray, currentUser.currentBlocksArrayData, MainUI.blocksArrayPane);

            App.ifEnd = isEnd(currentUser);
        }

        Thread ifEndOrWin = new Thread() {
            @Override
            public void run() {
                if (App.ifEnd) {//if end
                    currentUser.currentTakeTime = new Date().getTime() - App.startTime;//set taken time

                    if (isWin(currentUser.currentBlocksArrayData)) {
                        currentUser.currentResult = "win";
                        OptionPane.setJOptionPaneMessage(MainUI.f, "YOU WIN!!! Taken " + currentUser.currentTakeTime / 1000 + "s", "Congratulations", null);
                    } else {
                        currentUser.currentResult = "fail";
                        OptionPane.setJOptionPaneMessage(MainUI.f, "GAME OVER! Taken " + currentUser.currentTakeTime / 1000 + "s", "Sorry", null);
                    }

                    int userOption = OptionPane.setJOptionPaneConfirm(MainUI.f, "Save the result?", "Message");

                    if (userOption == JOptionPane.YES_OPTION) {
                        if (App.usersData == null) {
                            App.usersData = new HashMap<>();
                        }
                        if (currentUser instanceof RegisteredUser) {
                            ((RegisteredUser) currentUser).setData();//set the data to prepare for saving
                            App.usersData.put(currentUser.username, currentUser);
                            try {
                                SaveUsersData.saveUsersData(App.usersData, App.userDataPath);//example data
                                OptionPane.setJOptionPaneMessage(MainUI.f, "Successfully save!", "Message", null);
                                MainUI.updateLastBestRecord();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            App.loginUI.setVisible(true);
                        }
                    }
                }
            }
        };

        ifEndOrWin.start();//Thread start

    }

    /**
     * The purpose of moveUp method is to deal with blocks which need move up
     */
    public static void moveUp(Block block, User currentUser) {
        boolean ifBreak = false;
        while (ifCanMoveUp(block, currentUser) && !ifBreak) {
            ifMoving = true;
            Block newBlock = new Block(0, block.location);
            if (currentUser.currentBlocksArrayData[block.location[0] - 1][block.location[1]].number != 0) {//judge if the first combining happen
                ifBreak = true;
                block.ifCombine = true;
            }
            block.number = currentUser.currentBlocksArrayData[block.location[0] - 1][block.location[1]].number + block.number;
            block.location = currentUser.currentBlocksArrayData[block.location[0] - 1][block.location[1]].location;
            currentUser.currentBlocksArrayData[block.location[0]][block.location[1]] = block;

            currentUser.currentBlocksArrayData[newBlock.location[0]][newBlock.location[1]] = newBlock;
        }
    }

    /**
     * The purpose of moveLeft method is to deal with blocks which need move left
     */
    public static void moveLeft(Block block, User currentUser) {

        boolean ifBreak = false;
        while (ifCanMoveLeft(block, currentUser) && !ifBreak) {
            ifMoving = true;
            Block newBlock = new Block(0, block.location);

            if (currentUser.currentBlocksArrayData[block.location[0]][block.location[1] - 1].number != 0) {//judge if the first combining happen
                ifBreak = true;
                block.ifCombine = true;
            }
            block.number = currentUser.currentBlocksArrayData[block.location[0]][block.location[1] - 1].number + block.number;
            block.location = currentUser.currentBlocksArrayData[block.location[0]][block.location[1] - 1].location;
            currentUser.currentBlocksArrayData[block.location[0]][block.location[1]] = block;
            currentUser.currentBlocksArrayData[newBlock.location[0]][newBlock.location[1]] = newBlock;

        }
    }

    /**
     * The purpose of moveDown method is to deal with blocks which need move down
     */
    public static void moveDown(Block block, User currentUser) {
        boolean ifBreak = false;
        while (ifCanMoveDown(block, currentUser) && !ifBreak) {
            ifMoving = true;
            Block newBlock = new Block(0, block.location);
            if (currentUser.currentBlocksArrayData[block.location[0] + 1][block.location[1]].number != 0) {//judge if the first combining happen
                ifBreak = true;
                block.ifCombine = true;
            }

            block.number = currentUser.currentBlocksArrayData[block.location[0] + 1][block.location[1]].number + block.number;
            block.location = currentUser.currentBlocksArrayData[block.location[0] + 1][block.location[1]].location;
            currentUser.currentBlocksArrayData[block.location[0]][block.location[1]] = block;

            currentUser.currentBlocksArrayData[newBlock.location[0]][newBlock.location[1]] = newBlock;

        }
    }

    /**
     * The purpose of moveRight method is to deal with blocks which need move right
     */
    public static void moveRight(Block block, User currentUser) {
        boolean ifBreak = false;
        while (ifCanMoveRight(block, currentUser) && !ifBreak) {
            ifMoving = true;
            Block newBlock = new Block(0, block.location);
            if (currentUser.currentBlocksArrayData[block.location[0]][block.location[1] + 1].number != 0) {//judge if the first combining happen
                ifBreak = true;
                block.ifCombine = true;
            }

            block.number = currentUser.currentBlocksArrayData[block.location[0]][block.location[1] + 1].number + block.number;
            block.location = currentUser.currentBlocksArrayData[block.location[0]][block.location[1] + 1].location;
            currentUser.currentBlocksArrayData[block.location[0]][block.location[1]] = block;

            currentUser.currentBlocksArrayData[newBlock.location[0]][newBlock.location[1]] = newBlock;
        }
    }

    //========================================================================================================
    /**
     * The purpose of ifCanMoveUp method is to judge that if a block can move up
     */
    public static boolean ifCanMoveUp(Block block, User currentUser) {//judge if the block can move up
        if (block.location[0] == 0) {
            return false;
        }

        if (block.number == 0) {
            return false;
        }

        if (currentUser.currentBlocksArrayData[block.location[0] - 1][block.location[1]].ifCombine) {
            return false;
        }

        return currentUser.currentBlocksArrayData[block.location[0] - 1][block.location[1]].number == 0
                || currentUser.currentBlocksArrayData[block.location[0] - 1][block.location[1]].number == block.number;
    }

    /**
     * The purpose of ifCanMoveLeft method is to judge that if a block can move left
     */
    public static boolean ifCanMoveLeft(Block block, User currentUser) {// judge the block can move to left
        if (block.location[1] == 0) {
            return false;
        }

        if (block.number == 0) {
            return false;
        }

        if (currentUser.currentBlocksArrayData[block.location[0]][block.location[1] - 1].ifCombine) {
            return false;
        }

        return currentUser.currentBlocksArrayData[block.location[0]][block.location[1] - 1].number == 0
                || currentUser.currentBlocksArrayData[block.location[0]][block.location[1] - 1].number == block.number;

    }

    /**
     * The purpose of ifCanMoveDown method is to judge that if a block can move down
     */
    public static boolean ifCanMoveDown(Block block, User currentUser) {// judge the block can move to down
        if (block.location[0] == currentUser.currentBlocksArrayData.length - 1) {
            return false;
        }

        if (block.number == 0) {
            return false;
        }

        if (currentUser.currentBlocksArrayData[block.location[0] + 1][block.location[1]].ifCombine) {
            return false;
        }

        return currentUser.currentBlocksArrayData[block.location[0] + 1][block.location[1]].number == 0
                || currentUser.currentBlocksArrayData[block.location[0] + 1][block.location[1]].number == block.number;
    }

    /**
     * The purpose of ifCanMoveRight method is to judge that if a block can move right
     */
    public static boolean ifCanMoveRight(Block block, User currentUser) {// judge the block can move to right
        if (block.location[1] == currentUser.currentBlocksArrayData.length - 1) {
            return false;
        }

        if (block.number == 0) {
            return false;
        }

        if (currentUser.currentBlocksArrayData[block.location[0]][block.location[1] + 1].ifCombine) {
            return false;
        }

        return currentUser.currentBlocksArrayData[block.location[0]][block.location[1] + 1].number == 0
                || currentUser.currentBlocksArrayData[block.location[0]][block.location[1] + 1].number == block.number;

    }

    /**
     * The purpose of isEnd method is to judge that if the game is ending
     */
    public static boolean isEnd(User currentUser) {
        for (Block[] blocks : currentUser.currentBlocksArrayData) {
            for (Block block : blocks) {
                if (ifCanMoveDown(block, currentUser) || ifCanMoveUp(block, currentUser) || ifCanMoveLeft(block, currentUser) || ifCanMoveRight(block, currentUser)) {
                    return isWin(currentUser.currentBlocksArrayData);
                }
            }
        }
        return true;
    }

    /**
     * The purpose of isWin method is to judge that if the game is winning
     */
    public static boolean isWin(Block[][] blocksArray) {
        int maxNum = 0;
        for (Block[] blocks : blocksArray) {
            for (Block block : blocks) {
                maxNum = Math.max(maxNum, block.number);
            }
        }
        return maxNum >= App.WinNum;
    }
}
