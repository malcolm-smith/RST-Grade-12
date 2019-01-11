import javax.swing.JPanel;

public class Menu {
    
    private JPanel panel = new JPanel();

    public Menu() {
        this.initPanel();
    }

    private void initPanel() {
        panel.setBackground(color.red);
        panel.setOpaque(true);
        panel.setLayout(null);
    }
}