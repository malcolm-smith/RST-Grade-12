import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.GridLayout;

public class Menu {

    private JPanel panel = new JPanel();
    private JLabel[] labels = new JLabel[3];

    private int NUMBER_OF_ITEMS = 3;

    public Menu() {
        this.initPanel();
    }

    private void initPanel() {
        this.panel.setBackground(Color.red);
        this.panel.setOpaque(true);
        this.panel.setLayout(new GridLayout(this.NUMBER_OF_ITEMS, 1));

        for (JLabel l : labels) {
            l = new JLabel("", SwingConstants.CENTER);
            l.setForeground(Color.white);
            l.setText("penis");

            this.panel.add(l);

        }
    }

    public JPanel getPanel() {
        return this.panel;
    }
}