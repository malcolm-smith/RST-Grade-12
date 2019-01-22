import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Space {

    private final ImageIcon spaceIcon = new ImageIcon("../images/ScreenshotStarfield.png");
    private final JLabel spaceLabel = new JLabel(spaceIcon);
    private JPanel panel = new JPanel();
    public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    public Player player = new Player();
    public JLayeredPane jLayeredPane = new JLayeredPane();

    // constructor
    public Space() {
        this.initPanel();
        this.drawSprites();
    }

    public void startGame() {
        this.loadLevel();
        this.drawSprites();
        // try { // wait before redrawing all sprites
        // Thread.sleep(1000);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
    }

    public void drawSprites() {
        ArrayList<Sprite> removeList = new ArrayList<Sprite>();
        ArrayList<JLabel> modelremoveList = new ArrayList<JLabel>();

        for (Sprite sprite : this.sprites) {
            sprite.setX(sprite.getX() + sprite.getDX());
            sprite.setY(sprite.getY() + sprite.getDY());
            sprite.getModel().setLocation(sprite.getX(), sprite.getY());
            // System.out.print(sprite.getModel().getY());

            if (sprite.getY() < 0 || sprite.getY() > 800) {
                System.out.println("out of bounds");
                removeList.add(sprite);
                modelremoveList.add(sprite.model);
            }
        }
        this.sprites.removeAll(removeList);
        for (JLabel label : modelremoveList) {
            this.jLayeredPane.remove(label);
        }
    }

    public void loadLevel() {
        // System.out.println("load");
        this.sprites.add(this.player);
        for (Sprite sprite : this.sprites) {
            this.jLayeredPane.add(sprite.model, new Integer(2));
        }
        Game.frame.addKeyListener(this.player.getKeyListener());
        Game.frame.requestFocus();
    }

    // initialises the panel
    private void initPanel() {
        this.panel.setBackground(Color.BLUE);
        this.panel.setLayout(new BorderLayout());
        this.jLayeredPane.setOpaque(true);
        this.jLayeredPane.setBackground(Color.black);
        this.panel.add(this.jLayeredPane);
        this.spaceLabel.setBounds(0, 0, Game.frameLength, Game.frameWidth);
        this.jLayeredPane.add(this.spaceLabel, new Integer(1));
    }

    // setters and getters
    public JPanel getPanel() {
        return this.panel;
    }
}