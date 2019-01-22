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

    Thread t = new Thread(new Runnable() {

        @Override
        public void run() {
            space.player.moveRight();
            while (true) {
                try {
                    if (space.player.getX() < 30) {
                        space.player.moveRight();
                    } else if (space.player.getX() > 725) {
                        space.player.moveLeft();
                    }
                    space.drawSprites();
                    frame.repaint(); // redraws everything on the frame
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
        t.start(); // starts the thread
    }

    public static void main(String[] args) {
        g = new Game();
    }
}