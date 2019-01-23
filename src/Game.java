import java.util.Random;

import javax.swing.JFrame;

public class Game {

    public static int frameLength = 800;
    public static int frameWidth = 800;

    // how long it takes for the game to refresh all positions of objects
    // (lower -->more fps)
    public static final int delay = 10;

    public static Game g;
    public static JFrame frame = new JFrame();
    public Menu menu = new Menu();
    public Space space = new Space();
    public boolean gameOver = false;

    // tools used in the game loop
    public static Random random = new Random();
    public static int difficulty = 0;
    public static ScoreSystem scoreSystem = new ScoreSystem();

    // thread must be run in paralell to the rest of the program, otherwise graphics
    // will not refresh
    public Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            space.player.moveRight();
            int counter = 0;
            int randVal = 300;
            // space.sprites.add(new Alien(50, 50));
            // this while loop is essentially the game loop, and runs every 1000ms
            while (true) {
                // adds an alien at a random interval
                if (counter == randVal) {
                    // adds an alien to the game in a random location at the top of the screen
                    space.sprites.add(new Alien(random.nextInt(750) + 1, 50));

                    // increases spawn rate of aliens
                    difficulty += 2;
                    if (difficulty > 300) {
                        difficulty = 200;
                    }
                    counter = 0;
                    // the difficulty counter is used to increase the spawn rate of Aliens over
                    // time, making the game more difficult as it progresses
                    randVal = random.nextInt(100) + (200 - difficulty);
                }
                counter++;
                // prevent players from going out of bounds (offscreen) by turning them the
                // other direction when border is reached
                if (space.player.getX() < 30) {
                    space.player.moveRight();
                } else if (space.player.getX() > 725) {
                    space.player.moveLeft();
                }

                // redraws everything on the frame
                space.drawSprites();
                frame.repaint();

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

    // starts the game loop
    public void run() {
        thread.start(); // starts the thread
    }

    // main method
    public static void main(String[] args) {
        g = new Game();
    }
}