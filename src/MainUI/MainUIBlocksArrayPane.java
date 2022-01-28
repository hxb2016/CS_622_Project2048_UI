package MainUI;

import Game2048_test.App;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 * the purpose of this class is to create a blocksArrayPane
 */
public class MainUIBlocksArrayPane extends JPanel {
    public MainUIBlocksArrayPane(JLabel[][] buttonList, int textSize, int gap) {
        this.setBorder(new EmptyBorder(gap, gap, gap, gap));
        this.setBackground(new Color(187, 173, 160));
        this.setLayout(new GridLayout(App.interfaceSize, App.interfaceSize, gap, gap));

        for (int i = 0; i < App.interfaceSize; i++) {
            for (int j = 0; j < App.interfaceSize; j++) {
                MainUIBlockLabel b = new MainUIBlockLabel(10,textSize);
                this.add(b);
                buttonList[i][j] = b;
            }
        }
    }
}
