import java.awt.Color;

public class Bullet extends Sprite {

    public Bullet(int direction, int x, int y) {
        this.model.setBackground(Color.white);
        this.model.setSize(5, 20);
        this.speed = 3; // bullets are faster than the default sprite
        if (direction == 1) { // shooting up
            this.dy = -this.speed;
            this.setY(y - 50);
        } else if (direction == 0) { // shooting down
            this.dy = this.speed;
            this.setY(y + 50);
        }
        this.setX(x);

        // adds itself to the game's sprite list
        Game.g.space.jLayeredPane.add(this.model, new Integer(3));
        Game.g.space.sprites.add(this);
    }
}