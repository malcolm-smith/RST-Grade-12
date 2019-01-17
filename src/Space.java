import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Space {

    private final ImageIcon spaceIcon = new ImageIcon("../images/ScreenshotStarfield.png");
    private final JLabel spaceLabel = new JLabel(spaceIcon);
    private JPanel panel = new JPanel();

    // constructor
    public Space() {
        this.initPanel();
    }

    // initialises the panel
    private void initPanel() {
        this.panel.setBackground(Color.black);
        this.panel.add(this.spaceLabel);
    }

    // setters and getters
    public JPanel getPanel() {
        return this.panel;
    }
}