import java.awt.Color;

public class Bullet extends Sprite {

    public Bullet(int direction, int x, int y) {
        this.model.setBackground(Color.white);
        this.speed = 5;
        if (direction == 0) {
            this.dy = -this.speed;
        } else if (direction == 1) {
            this.dy = this.speed;
        }
        this.setX(x);
        this.setY(y);
        System.out.println("bullet");
    }
}