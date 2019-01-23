import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Sprite {

    protected KeyListener keyListener = (new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
            move(e);
        }
    });

    public Player() {
        this.speed = 3;
        this.setX(50);
        this.setY(700);
        this.model.setIcon(this.playerIcon);
    }

    public KeyListener getKeyListener() {
        return this.keyListener;
    }

    public void move(KeyEvent e) {
        if (KeyEvent.getKeyText(e.getKeyCode()).equals("Right")) {
            moveRight();
        } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Left")) {
            moveLeft();
        } else if (KeyEvent.getKeyText(e.getKeyCode()).equals("Space")) {
            playerShoot();
        }
    }
}