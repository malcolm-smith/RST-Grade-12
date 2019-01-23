import java.awt.Color;

public class Alien extends Sprite {

    public Alien(int x, int y) {
        this.speed = 1;
        this.setX(x);
        this.setY(y);
        this.moveDown();
        this.model.setIcon(this.alienIcon);
        this.model.setBackground(Color.green);
        Game.g.space.jLayeredPane.add(this.model, new Integer(2));
    }
}