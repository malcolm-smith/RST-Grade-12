import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Space {

    private final ImageIcon spaceIcon = new ImageIcon("../images/ScreenshotStarfield.png");
    private final JLabel spaceLabel = new JLabel(spaceIcon);
    private JPanel panel = new JPanel();
    public ArrayList<Sprite> sprites = new ArrayList<Sprite>();
    public Player player = new Player();
    public JLayeredPane jLayeredPane = new JLayeredPane();
    public int score = 0;
    public static int counter = 0;

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

    public void gameOver() {
        JOptionPane.showMessageDialog(null, "GAME OVER\nSCORE: " + score);
        Game.scoreSystem.addScore(score);
        System.exit(0);
    }

    public void drawSprites() {
        ArrayList<Sprite> removeList = new ArrayList<Sprite>();
        ArrayList<JLabel> modelremoveList = new ArrayList<JLabel>();
        boolean createBullet = false;
        int xx = 0;
        int yy = 0;

        for (Sprite sprite : this.sprites) {
            sprite.setX(sprite.getX() + sprite.getDX());
            sprite.setY(sprite.getY() + sprite.getDY());
            sprite.getModel().setLocation(sprite.getX(), sprite.getY());
            // System.out.print(sprite.getModel().getY());

            if (sprite.getClass().equals(Bullet.class)) { // if the sprite is a Bullet object
                // if the bullet touches the player, end the game
                if (sprite.model.getBounds().intersects(player.model.getBounds())) {
                    // call the game over method
                    this.gameOver();
                } else {
                    // if the bullet intersects an alien
                    for (Sprite alien : sprites) {
                        if (alien.getClass().equals(Alien.class)) { // if an alien
                            if (sprite.model.getBounds().intersects(alien.model.getBounds())) {
                                System.out.println("Alien shot!");
                                this.score++;
                                alien.model.setOpaque(false);
                                removeList.add(alien);
                                removeList.add(sprite);
                                modelremoveList.add(alien.model);
                                this.jLayeredPane.remove(alien.model);
                                modelremoveList.add(sprite.model);
                            }
                        }
                    }
                    // if the bullet moves out of bounds (top or bottom of screen)
                    if (sprite.getY() < 0 || sprite.getY() > 800) {
                        System.out.println("out of bounds");
                        removeList.add(sprite);
                        modelremoveList.add(sprite.model);
                    }
                }

            } else if (sprite.getClass().equals(Alien.class)) { // if the sprite is an Alien object
                if (Space.counter == 30) { // every once in awhile the Alien changes direction
                    int randomVal = Game.random.nextInt(4) + 1;
                    if (randomVal == 1) {
                        sprite.moveLeft();
                    } else if (randomVal == 2) {
                        sprite.moveRight();
                    } else {
                        sprite.moveDown();
                    }
                    Space.counter = 0;
                } else {
                    Space.counter++;
                }
                if (sprite.shooting == true) {
                    if (Game.random.nextInt(2) + 1 == 1) {
                        createBullet = true;
                        xx = sprite.getX();
                        yy = sprite.getY();
                    }
                    // new Bullet(sprite.getX(), sprite.getY(), 0);
                }

                // see if Alien has run into the player, ending the game
                for (Sprite player : sprites) {
                    if (player.getClass().equals(Player.class)) {
                        if (sprite.model.getBounds().intersects(player.model.getBounds())) {
                            System.out.println("Alien ran into player!");
                            // call the game over method
                            this.gameOver();
                        }
                    }
                }

            }

        }

        // remove sprites from lists once they exit the screen
        // this way, the program does not have to continue to
        // update their position, saving memory.
        this.sprites.removeAll(removeList); // remove from sprites list
        for (JLabel label : modelremoveList) {
            this.jLayeredPane.remove(label); // remove graphics from screen
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