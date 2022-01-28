package Tool;

import Block.*;
import Users.User;

/**
 * Purpose of this class is to set a blockArray to currentBlocksArrayData of current user
 */
public class CreateBlockArrayData {
    /**
     * Purpose of creatBlockArrayData is to set a blockArray to currentBlocksArrayData of current user
     */
    public static void creatBlockArrayData(int size, User currentUser) {
        currentUser.currentBlocksArrayData = new Block[size][size];
        for (int i = 0; i < currentUser.currentBlocksArrayData.length; i++) {
            for (int j = 0; j < currentUser.currentBlocksArrayData[i].length; j++) {
                currentUser.currentBlocksArrayData[i][j] = new Block(0, new int[]{i, j});
            }
        }

        // Insert first random block object
        Block randomBlock1 = new RandomBlock(currentUser);
        currentUser.currentBlocksArrayData[randomBlock1.location[0]][randomBlock1.location[1]] = randomBlock1;

        // Insert second random block object
        Block randomBlock2 = new RandomBlock(currentUser);
        currentUser.currentBlocksArrayData[randomBlock2.location[0]][randomBlock2.location[1]] = randomBlock2;
    }

}
