import java.util.Random;

import javax.swing.JFrame;

public class Game {

    public static int frameLength = 800;
    public static int frameWidth = 800;

    public static final int delay = 10;

    public static Game g;
    public static JFrame frame = new JFrame();
    public Menu menu = new Menu();
    public Space space = new Space();
    public boolean gameOver = false;
    public static Random random = new Random();
    public static int difficulty = 0;
    public static ScoreSystem scoreSystem = new ScoreSystem();

    public Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            space.player.moveRight();
            int counter = 0;
            int randVal = 300;
            // space.sprites.add(new Alien(50, 50));
            // this while loop is essentially the game loop, and runs every 1000ms
            while (true) {
                if (counter == randVal) {
                    if (random.nextInt(2) + 1 == 1) {
                        // 1/30 chance for the Alien to be able to shoot ^^
                        space.sprites.add(new Alien(random.nextInt(750) + 1, 50, true));
                    } else {
                        space.sprites.add(new Alien(random.nextInt(750) + 1, 50, true));
                    }

                    difficulty += 2;
                    if (difficulty > 200) {
                        difficulty = 100;
                    }
                    counter = 0;
                    // the difficulty counter is used to increase the spawn rate of Aliens over
                    // time, making the game more difficult as it progresses
                    randVal = random.nextInt(100) + (200 - difficulty);
                }
                counter++;
                // System.out.println(counter);
                // prevent players from going out of bounds by turning them the other direction
                if (space.player.getX() < 30) {
                    space.player.moveRight();
                } else if (space.player.getX() > 725) {
                    space.player.moveLeft();
                }

                space.drawSprites();
                frame.repaint(); // redraws everything on the frame

                try {
                    Thread.sleep(Game.delay);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    });

    // constructor
    public Game() {
        this.initGUI();
    }

    private void initGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Game.frameLength, Game.frameWidth);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.add(menu.getPanel());
    }

    public void clearFrame() {
        Game.frame.getContentPane().removeAll();
    }

    // game loop
    public void run() {
        thread.start(); // starts the thread
    }

    public static void main(String[] args) {
        g = new Game();
    }
}