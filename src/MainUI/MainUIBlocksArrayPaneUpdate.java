package MainUI;

import Block.Block;

import javax.swing.*;
/**
 * the purpose of this class is to update blocksArrayPane
 */
public class MainUIBlocksArrayPaneUpdate {
    /**
     * the purpose of this method is to update blocksArrayPane
     */
    public static void updateUI(MainUIBlockLabel[][] blocksArray, Block[][] blocksArrayData, JPanel blocksArrayPane) {

        for (int i = 0; i < blocksArrayData.length; i++) {
            for (int j = 0; j < blocksArrayData[i].length; j++) {
                blocksArrayData[i][j].ifCombine = false;//clean change from last moving
                if (blocksArrayData[i][j].number == 0) {
                    blocksArray[i][j].setText("");
                } else {
                    blocksArray[i][j].setText(blocksArrayData[i][j].number + "");
                }
                blocksArray[i][j].setColor();
            }
        }
        // Refresh the blocksArrayPane
        blocksArrayPane.updateUI();
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
