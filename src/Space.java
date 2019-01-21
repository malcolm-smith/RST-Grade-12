import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Space {

    private final ImageIcon spaceIcon = new ImageIcon("../images/ScreenshotStarfield.png");
    private final JLabel spaceLabel = new JLabel(spaceIcon);
    private JPanel panel = new JPanel();
    private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    // constructor
    public Space() {
        this.initPanel();
    }

    public void drawSprites() {
        for (Sprite sprite : sprites) {
            sprite.x += sprite.dx;
            sprite.y += sprite.dy;
        }
    }

    // initialises the panel
    private void initPanel() {
        this.panel.setBackground(Color.black);
        this.panel.add(this.spaceLabel);
    }

    // setters and getters
    public JPanel getPanel() {
        return this.panel;
    }
}