import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Space {

    private final ImageIcon spaceIcon = new ImageIcon("../images/ScreenshotStarfield.png");
    private final JLabel spaceLabel = new JLabel(spaceIcon);
    private JPanel panel = new JPanel();
    public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    public Player player = new Player();

    // constructor
    public Space() {
        this.initPanel();
        this.loadLevel();
        // this.drawSprites();
    }

    public void drawSprites() {
        for (Sprite sprite : sprites) {
            sprite.x += sprite.dx;
            sprite.y += sprite.dy;
        }
    }

    private void loadLevel() {
        this.sprites.add(this.player);
        for (Sprite sprite : this.sprites) {
            this.panel.add(sprite.model);
        }
        // Game.g.frame.addKeyListener(this.player.getKeyListener());
    }

    // initialises the panel
    private void initPanel() {
        this.panel.setBackground(Color.black);
        this.panel.setLayout(null);
        this.spaceLabel.setBounds(0, 0, 800, 800);
        this.panel.add(this.spaceLabel);
    }

    // setters and getters
    public JPanel getPanel() {
        return this.panel;
    }
}