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
                    if (!App.ifEnd) {
                        Operate.operation(e.getKeyCode(), App.currentUser);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
