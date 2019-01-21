import java.awt.Color;

import javax.swing.JLabel;

public class Alien extends Sprite {

    public Alien() {
        this.model.setIcon(this.alienIcon);
        this.model.setBackground(Color.green);
    }
}