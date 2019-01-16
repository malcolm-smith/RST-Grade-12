import javax.swing.JFrame;

public class Game {

    private JFrame frame = new JFrame();
    private Menu menu = new Menu();

    public Game() {
        this.initGUI();
    }

    private void initGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.add(menu.getPanel());
    }

    public static void main(String[] args) {
        Game g = new Game();
    }
}