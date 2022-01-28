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
public class MainUI extends JFrame {
    public MainUIBlockLabel[][] blocksArray = new MainUIBlockLabel[App.interfaceSize][App.interfaceSize];
    public JPanel recordPane = null;
    public JPanel buttonPane = null;
    public JPanel blocksArrayPane = null;
    public JLabel lastTitleLabel = new JLabel("Last Record: Taken ...s", SwingConstants.CENTER);
    public MainUIBlockLabel[][] lastBlockArray = new MainUIBlockLabel[App.interfaceSize][App.interfaceSize];
    public JPanel lastRecord = new MainUIBlocksArrayPane(lastBlockArray, 10, 2);

    public JLabel bestTitleLabel = new JLabel("best Record: Taken ...s", SwingConstants.CENTER);
    public MainUIBlockLabel[][] bestBlockArray = new MainUIBlockLabel[App.interfaceSize][App.interfaceSize];
    public JPanel bestRecord = new MainUIBlocksArrayPane(bestBlockArray, 10, 2);

    public JButton up = null;
    public JButton left = null;
    public JButton down = null;
    public JButton right = null;
    public JButton newGame = null;

    /**
     * the purpose of this method is to init JFrame
     */
    public MainUI() {
        ImageIcon logo = new ImageIcon("src" + File.separator + "Image" + File.separator + "2048.png");//get logo image
        this.setTitle("CS_622_Game_2048");
        this.setIconImage(logo.getImage());//set logo image
        this.setSize(720, 720);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setAlwaysOnTop(true);

        this.recordPane = new JPanel();
        this.recordPane.setBorder(new EmptyBorder(0, 0, 10, 0));
        this.recordPane.setLayout(new GridLayout(1, 3, -20, 0));
        this.recordPane.setPreferredSize(new Dimension(this.getWidth(), 220));

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
        lastRecordOutPane.add(this.lastTitleLabel, BorderLayout.NORTH);
        lastRecordOutPane.add(this.lastRecord, BorderLayout.CENTER);

        JPanel bestRecordOutPane = new JPanel();
        bestRecordOutPane.setLayout(new BorderLayout());
        bestRecordOutPane.setBorder(new EmptyBorder(0, 20, 0, 20));
        this.bestTitleLabel.setForeground(new Color(18, 150, 219));
        bestRecordOutPane.add(this.bestTitleLabel, BorderLayout.NORTH);
        bestRecordOutPane.add(this.bestRecord, BorderLayout.CENTER);
//////////////////////////////////////////////////////////////////////////////////////////////
        this.recordPane.add(timerArea);
        this.recordPane.add(bestRecordOutPane);
        this.recordPane.add(lastRecordOutPane);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        this.buttonPane = new JPanel();
        this.buttonPane.setPreferredSize(new Dimension(220, this.getHeight()));
        this.buttonPane.setLayout(new BorderLayout());

        this.newGame = new MainUIButton("New Game", null);

        JPanel operationButtonArea = new JPanel();
        operationButtonArea.setBorder(new EmptyBorder(0, 0, 10, 0));
        operationButtonArea.setLayout(new GridLayout(2, 1));
        ImageIcon upArrow = new ImageIcon("src" + File.separator + "Image" + File.separator + "upArrow.png");
        ImageIcon leftArrow = new ImageIcon("src" + File.separator + "Image" + File.separator + "leftArrow.png");
        ImageIcon downArrow = new ImageIcon("src" + File.separator + "Image" + File.separator + "downArrow.png");
        ImageIcon rightArrow = new ImageIcon("src" + File.separator + "Image" + File.separator + "rightArrow.png");
        this.up = new MainUIButton(null, upArrow);
        JPanel upPane = new JPanel();
        upPane.add(this.up);
        this.left = new MainUIButton(null, leftArrow);
        this.down = new MainUIButton(null, downArrow);
        this.right = new MainUIButton(null, rightArrow);
        JPanel leftDownRightPane = new JPanel();
        leftDownRightPane.add(this.left);
        leftDownRightPane.add(this.down);
        leftDownRightPane.add(this.right);
        operationButtonArea.add(upPane);
        operationButtonArea.add(leftDownRightPane);

        this.buttonPane.add(operationButtonArea, BorderLayout.SOUTH);
        this.buttonPane.add(this.newGame, BorderLayout.NORTH);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        blocksArrayPane = new MainUIBlocksArrayPane(blocksArray, 25, 5);


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.add(this.blocksArrayPane, BorderLayout.CENTER);
        this.add(this.buttonPane, BorderLayout.EAST);
        this.add(this.recordPane, BorderLayout.NORTH);
        this.setVisible(true);//window is visible
    }
    /**
     * Purpose of this method is to update the lastBlockArray and bestBlockArray
     */
    public void updateLastBestRecord() {
        MainUIBlocksArrayPaneUpdate.updateUI(this.lastBlockArray, ((RegisteredUser) App.currentUser).lastBlocksArrayData, this.lastRecord);
        this.lastTitleLabel.setText("Last Record: Taken " + ((RegisteredUser) App.currentUser).lastTakeTime / 1000 + "s");
        MainUIBlocksArrayPaneUpdate.updateUI(this.bestBlockArray, ((RegisteredUser) App.currentUser).bestBlocksArrayData, this.bestRecord);
        this.bestTitleLabel.setText("Last Record: Taken " + ((RegisteredUser) App.currentUser).bestTakeTime / 1000 + "s");
    }


}
