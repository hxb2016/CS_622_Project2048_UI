package Tool;

import Game2048_test.App;
import Operation.Operate;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Purpose of this class is to return a handler for components to get keyboard action
 * In the game, you can press ⬆， ⬅， ⬇ and ➡ keys to operate the game interface, so it needs to get the keyboard's action.
 * because in the MainUI, there are different kind components, this class uses a generic class
 */
public class KeyBoardListener<Comp extends Component> {
    Comp comp;

    public KeyBoardListener(Comp comp) {
        this.comp = comp;
    }

    public void setListener() {
        this.comp.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    Thread changeBackGround = new Thread() {
                        @Override
                        public void run() {
                            switch (e.getKeyCode()) {
                                case 38 -> App.mainUI.up.setBackground(new Color(184,207,229));
                                case 37 -> App.mainUI.left.setBackground(new Color(184,207,229));
                                case 39 -> App.mainUI.right.setBackground(new Color(184,207,229));
                                case 40 -> App.mainUI.down.setBackground(new Color(184,207,229));
                            }
                        }
                    };
                    changeBackGround.start();
                    ///////////////////////////////////////////////////////////
                    if (!App.ifEnd) {
                        Operate.operation(e.getKeyCode(), App.currentUser);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 38 -> App.mainUI.up.setBackground(new Color(18, 150, 219));
                    case 37 -> App.mainUI.left.setBackground(new Color(18, 150, 219));
                    case 39 -> App.mainUI.right.setBackground(new Color(18, 150, 219));
                    case 40 -> App.mainUI.down.setBackground(new Color(18, 150, 219));
                }
            }
        });
    }
}
