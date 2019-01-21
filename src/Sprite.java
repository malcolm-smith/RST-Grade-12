import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Sprite {

    public int x = 0;
    public int y = 0;
    public int dx = 0;
    public int dy = 0;
    protected int speed = 1;
    protected JLabel model;
    protected ImageIcon imageIcon;
    protected boolean exists = true;

    public Sprite() {

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