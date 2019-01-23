import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Sprite {

    protected JLabel model = new JLabel();
    protected ImageIcon playerIcon = new ImageIcon(
            "../images/168-1683647_space-invaders-free-icon-list-of-space-invaders-video-games.png");
    protected ImageIcon alienIcon = new ImageIcon("../images/Space-Invaders-Free-PNG-Image.png");
    protected boolean exists = true;

    protected int x = 50;
    protected int y = 50;
    protected int l = 50;
    protected int w = 50;

    protected int dx = 0;
    protected int dy = 0;

    protected int speed = 2;
    protected String direction = "NONE";

    public Sprite() {
        createSprite();
    }

    protected void createSprite() {
        model.setOpaque(true);
        model.setBounds(x, y, l, w);
    }

    public void playerShoot() {
        new Bullet(1, this.getX(), this.getY());
    }

    public void alienShoot() {
        new Bullet(0, this.getX(), this.getY());
    }

    public void moveLeft() {
        allStop();
        this.dx = -this.speed;
        this.direction = "LEFT";
    }

    public void moveRight() {
        allStop();
        dx = speed;
        direction = "RIGHT";
    }

    public void moveUp() {
        allStop();
        dy = -speed;
        direction = "UP";
    }

    public void moveDown() {
        allStop();
        dy = speed;
        direction = "DOWN";
    }

    public void allStop() {
        dx = 0;
        dy = 0;
        direction = "NONE";
    }

    public void stopX() {
        dx = 0;
    }

    public void stopY() {
        dy = 0;
    }

    public JLabel getLabel() {
        return this.model;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLength() {
        return l;
    }

    public int getWidth() {
        return w;
    }

    public void setX(int i) {
        x = i;
    }

    public void setY(int i) {
        y = i;
    }

    public int getDX() {
        return dx;
    }

    public int getDY() {
        return dy;
    }

    public JLabel getModel() {
        return this.model;
    }

    // checks for collision between objects
    public boolean check(int a, int b, int c, int d) {
        if ((x <= a && a <= (x + l)) && (y <= b && b <= (y + w))
                || ((a <= x && x <= (a + c)) && (b <= y && y <= (b + d)))) {
            return true;
        } else if ((x <= (a + c) && (a + c) <= (x + l)) && (y <= b && b <= (y + w))
                || ((a <= (x + l) && (x + l) <= (a + c)) && (b <= y && y <= (b + d)))) {
            return true;
        } else if ((x <= (a + c) && (a + c) <= (x + l)) && (y <= (b + d) && (b + d) <= (y + w))
                || ((a <= (x + l) && (x + l) <= (a + c)) && (b <= (y + w) && (y + w) <= (b + d)))) {
            return true;
        } else if ((x <= a && a <= (x + l)) && (y <= (b + d) && (b + d) <= (y + w))
                || ((a <= x && x <= (a + c)) && (b <= (y + w) && (y + w) <= (b + d)))) {
            return true;
        }
        return false;
    }
}
