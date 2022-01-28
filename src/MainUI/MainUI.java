package MainUI;

import Game2048_test.App;
import Users.RegisteredUser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * purpose of this class is to create the MainUI
 */
public class MainUI {
    public static MainUIBlockLabel[][] blocksArray = new MainUIBlockLabel[App.interfaceSize][App.interfaceSize];
    public static JPanel recordPane = null;
    public static JPanel buttonPane = null;
    public static JPanel blocksArrayPane = null;
    public static JLabel lastTitleLabel = new JLabel("Last Record: Taken ...s", SwingConstants.CENTER);
    public static MainUIBlockLabel[][] lastBlockArray = new MainUIBlockLabel[App.interfaceSize][App.interfaceSize];
    public static JPanel lastRecord = new MainUIBlocksArrayPane(lastBlockArray, 10, 2);

    public static JLabel bestTitleLabel = new JLabel("best Record: Taken ...s", SwingConstants.CENTER);
    public static MainUIBlockLabel[][] bestBlockArray = new MainUIBlockLabel[App.interfaceSize][App.interfaceSize];
    public static JPanel bestRecord = new MainUIBlocksArrayPane(bestBlockArray, 10, 2);
    public static JFrame f = null;

    public static JButton up = null;
    public static JButton left = null;
    public static JButton down = null;
    public static JButton right = null;
    public static JButton newGame = null;

    /**
     * the purpose of this method is to init JFrame
     */
    public static void initUI() {
        ImageIcon logo = new ImageIcon("src" + File.separator + "Image" + File.separator + "2048.png");//get logo image
        f = new JFrame("CS_622_Game_2048");
        f.setIconImage(logo.getImage());//set logo image
        f.setSize(720, 720);
        f.setLocationRelativeTo(null);
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        f.setResizable(false);
        f.setAlwaysOnTop(true);

        recordPane = new JPanel();
        recordPane.setBorder(new EmptyBorder(0, 0, 10, 0));
        recordPane.setLayout(new GridLayout(1, 3, -20, 0));
        recordPane.setPreferredSize(new Dimension(f.getWidth(), 220));

        JPanel timerArea = new JPanel();
        timerArea.setLayout(new BorderLayout());
        JLabel l = new JLabel("<html>This is timer area, but I have not completed in this version.");
        JLabel l1 = new JLabel("<html>Note: In order to save time in test, I change the win number " +
                "from 2048 to 16, which means that when any block.number equal to or larger than 16, you will win the game");
        l1.setForeground(Color.RED);
        timerArea.add(l, BorderLayout.NORTH);
        timerArea.add(l1, BorderLayout.SOUTH);
////////////////////////////////////////////////////////////////////////////////////
        JPanel lastRecordOutPane = new JPanel();
        lastRecordOutPane.setLayout(new BorderLayout());
        lastRecordOutPane.setBorder(new EmptyBorder(0, 20, 0, 20));
        lastRecordOutPane.add(lastTitleLabel, BorderLayout.NORTH);
        lastRecordOutPane.add(lastRecord, BorderLayout.CENTER);

        JPanel bestRecordOutPane = new JPanel();
        bestRecordOutPane.setLayout(new BorderLayout());
        bestRecordOutPane.setBorder(new EmptyBorder(0, 20, 0, 20));
        bestTitleLabel.setForeground(new Color(18, 150, 219));
        bestRecordOutPane.add(bestTitleLabel, BorderLayout.NORTH);
        bestRecordOutPane.add(bestRecord, BorderLayout.CENTER);
//////////////////////////////////////////////////////////////////////////////////////////////
        recordPane.add(timerArea);
        recordPane.add(bestRecordOutPane);
        recordPane.add(lastRecordOutPane);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        buttonPane = new JPanel();
        buttonPane.setPreferredSize(new Dimension(220, f.getHeight()));
        buttonPane.setLayout(new BorderLayout());

        newGame = new MainUIButton("New Game", null);

        JPanel operationButtonArea = new JPanel();
        operationButtonArea.setBorder(new EmptyBorder(0, 0, 10, 0));
        operationButtonArea.setLayout(new GridLayout(2, 1));
        ImageIcon upArrow = new ImageIcon("src" + File.separator + "Image" + File.separator + "upArrow.png");
        ImageIcon leftArrow = new ImageIcon("src" + File.separator + "Image" + File.separator + "leftArrow.png");
        ImageIcon downArrow = new ImageIcon("src" + File.separator + "Image" + File.separator + "downArrow.png");
        ImageIcon rightArrow = new ImageIcon("src" + File.separator + "Image" + File.separator + "rightArrow.png");
        up = new MainUIButton(null, upArrow);
        JPanel upPane = new JPanel();
        upPane.add(up);
        left = new MainUIButton(null, leftArrow);
        down = new MainUIButton(null, downArrow);
        right = new MainUIButton(null, rightArrow);
        JPanel leftDownRightPane = new JPanel();
        leftDownRightPane.add(left);
        leftDownRightPane.add(down);
        leftDownRightPane.add(right);
        operationButtonArea.add(upPane);
        operationButtonArea.add(leftDownRightPane);

        buttonPane.add(operationButtonArea, BorderLayout.SOUTH);
        buttonPane.add(newGame, BorderLayout.NORTH);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        blocksArrayPane = new MainUIBlocksArrayPane(blocksArray, 25, 5);


        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.add(blocksArrayPane, BorderLayout.CENTER);
        f.add(buttonPane, BorderLayout.EAST);
        f.add(recordPane, BorderLayout.NORTH);
        f.setVisible(true);//window is visible
    }
    /**
     * Purpose of this method is to update the lastBlockArray and bestBlockArray
     */
    public static void updateLastBestRecord() {
        MainUIBlocksArrayPaneUpdate.updateUI(MainUI.lastBlockArray, ((RegisteredUser) App.currentUser).lastBlocksArrayData, MainUI.lastRecord);
        MainUI.lastTitleLabel.setText("Last Record: Taken " + ((RegisteredUser) App.currentUser).lastTakeTime / 1000 + "s");
        MainUIBlocksArrayPaneUpdate.updateUI(MainUI.bestBlockArray, ((RegisteredUser) App.currentUser).bestBlocksArrayData, MainUI.bestRecord);
        MainUI.bestTitleLabel.setText("Last Record: Taken " + ((RegisteredUser) App.currentUser).bestTakeTime / 1000 + "s");
    }


}
