import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
        this.model.setIcon(this.playerIcon);
        this.model.setBackground(Color.red);
    }

    public KeyListener getKeyListener() {
        return this.keyListener;
    }
}