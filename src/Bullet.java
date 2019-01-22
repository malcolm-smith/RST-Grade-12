import java.awt.Color;

public class Bullet extends Sprite {

    public Bullet(int direction, int x, int y) {
        this.model.setBackground(Color.white);
        this.model.setSize(5, 20);
        this.speed = 1;
        if (direction == 1) {
            this.dy = -this.speed;
        } else if (direction == 0) {
            this.dy = this.speed;
        }
        this.setX(x);
        this.setY(y);
        System.out.println("bullet fired at " + this.getX() + ", " + this.getY());
        Game.g.space.jLayeredPane.add(this.model, new Integer(3));
        Game.g.space.sprites.add(this);
    }
}