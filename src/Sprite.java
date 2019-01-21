import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Sprite {

    public int x = 0;
    public int y = 0;
    public int dx = 0;
    public int dy = 0;
    protected int speed = 1;
    protected JLabel model;
    protected ImageIcon playerIcon = new ImageIcon(
            "../images/168-1683647_space-invaders-free-icon-list-of-space-invaders-video-games.png");
    protected ImageIcon alienIcon = new ImageIcon(
            "../images/Space-Invaders-Alien-PNG-Image-with-Transparent-Background.png");
    protected boolean exists = true;

    public Sprite() {
        this.model = new JLabel();
        this.model.setOpaque(true);
        this.model.setBackground(Color.lightGray);
        this.model.setBounds(x, y, 50, 50);
    }

    protected void moveLeft() {
        this.dx -= speed;
    }

    protected void moveRight() {
        this.dx += speed;
    }

    protected void moveUp() {
        this.dy += speed;
    }

    protected void moveDown() {
        this.dy -= speed;
    }
}