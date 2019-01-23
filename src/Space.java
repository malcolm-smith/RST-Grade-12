import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Space {

    private final ImageIcon spaceIcon = new ImageIcon("../images/ScreenshotStarfield.png"); // background image
    private final JLabel spaceLabel = new JLabel(spaceIcon);
    private JPanel panel = new JPanel();

    // where all sprites in the game are kept track of, and are accessed from
    public ArrayList<Sprite> sprites = new ArrayList<Sprite>();

    // the player object, which is also added to the sprites list
    public Player player = new Player();

    // used to define in what order objects are shown on the frame
    public JLayeredPane jLayeredPane = new JLayeredPane();

    // the player's current score
    public int score = 0;

    // used to move alien in a random direction, go to line 97
    public static int counter = 0;

    // constructor
    public Space() {
        this.initPanel();
        this.drawSprites();
    }

    // creates and starts the game
    public void startGame() {
        this.loadLevel();
        this.drawSprites();
    }

    // called when the game ends (player dies)
    public void gameOver() {
        JOptionPane.showMessageDialog(null, "GAME OVER\nSCORE: " + score);
        Game.scoreSystem.addScore(score);
        System.exit(0);
    }

    // draws and refreshes all sprite's models on the screen
    public void drawSprites() {
        // used to remove objects from the list after iterating through it; you cannot
        // remove objects from list while iterating through it
        ArrayList<Sprite> removeList = new ArrayList<Sprite>();
        ArrayList<JLabel> modelremoveList = new ArrayList<JLabel>();

        // for each sprite in the game
        for (Sprite sprite : this.sprites) {
            // updates the sprite's position by adding its displacement values. if it is not
            // moving, dx/dy = 0, and the position will stay the same
            sprite.setX(sprite.getX() + sprite.getDX());
            sprite.setY(sprite.getY() + sprite.getDY());
            // updates the model's position on the screen
            sprite.getModel().setLocation(sprite.getX(), sprite.getY());

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
                                this.score++;
                                // remove alien and bullet from the game
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
                        removeList.add(sprite);
                        modelremoveList.add(sprite.model);
                    }
                }

            } else if (sprite.getClass().equals(Alien.class)) { // if the sprite is an Alien object
                if (Space.counter == 30) { // every once in awhile the Alien changes its direction randomly
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

                // see if Alien has run into the player, ending the game
                for (Sprite player : sprites) {
                    if (player.getClass().equals(Player.class)) {
                        if (sprite.model.getBounds().intersects(player.model.getBounds())) {
                            // call the game over method
                            this.gameOver();
                        }
                    }
                }
            }
        }

        // remove sprites from lists
        // this way, the program does not have to continue to constantly
        // update their position, saving memory.
        this.sprites.removeAll(removeList); // remove the sprite from sprites list
        for (JLabel label : modelremoveList) {
            this.jLayeredPane.remove(label); // remove the sprite's graphics from screen
        }
    }

    // initializes how the level appears to the user
    public void loadLevel() {
        this.sprites.add(this.player); // adds the player object into the game
        for (Sprite sprite : this.sprites) { // adds all sprites to the game
            this.jLayeredPane.add(sprite.model, new Integer(2));
        }
        // adds the player controls to the frame
        Game.frame.addKeyListener(this.player.getKeyListener());
        Game.frame.requestFocus();
    }

    // initialises the panel and how JComponents are ordered on the frame
    private void initPanel() {
        this.panel.setBackground(Color.BLUE);
        this.panel.setLayout(new BorderLayout());
        this.jLayeredPane.setOpaque(true);
        this.jLayeredPane.setBackground(Color.black);
        this.panel.add(this.jLayeredPane);
        this.spaceLabel.setBounds(0, 0, Game.frameLength, Game.frameWidth);
        this.jLayeredPane.add(this.spaceLabel, new Integer(1));
    }

    public JPanel getPanel() {
        return this.panel;
    }
}