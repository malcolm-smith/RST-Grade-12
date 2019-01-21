import javax.swing.JFrame;

public class Game {

    public static Game g;
    public JFrame frame = new JFrame();
    public Menu menu = new Menu();
    public Space space = new Space();
    public boolean gameOver = false;

    // constructor
    public Game() {
        this.initGUI();
    }

    private void initGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.add(menu.getPanel());
    }

    public void clearFrame() {
        this.frame.getContentPane().removeAll();
    }

    // game loop
    public void run() {
        while (gameOver != true) {
            // move all sprites
            space.drawSprites();
        }
    }

    public static void main(String[] args) {
        g = new Game();
    }
}