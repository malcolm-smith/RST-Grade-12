import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Sprite {

    private KeyListener keyListener = (new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("Pressed" + e.getKeyChar());
        }
    });

    public Player() {

    }

}