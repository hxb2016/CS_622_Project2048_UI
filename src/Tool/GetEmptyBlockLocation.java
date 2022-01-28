package Tool;

import Block.Block;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose of this class is to get empty block locations of current blocksArray
 */
public class GetEmptyBlockLocation {
    /**
     * Purpose of getEmptyBlockLocation() is to get empty block locations of current blocksArray
     */
    public static List<int[]> getEmptyBlockLocation(Block[][] blocksArray) {
        List<int[]> enableLocation = new ArrayList<>();
        for (Block[] blocks : blocksArray) {
            for (Block block : blocks) {
                if (blockIsEmpty(block)) {
                    enableLocation.add(block.location);
                }
            }
        }
        return enableLocation;
    }

    /**
     * Purpose of blockIsEmpty() is to judge that if the block is empty
     */
    private static boolean blockIsEmpty(Block block) {
        return block.number == 0;
    }
}
