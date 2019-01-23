import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    private JPanel panel = new JPanel();
    private JButton[] buttons = new JButton[3];
    private String instructions = "CHANGE X DIRECTION:  LEFT AND RIGHT ARROW KEYS\nSHOOT:  SPACEBAR";

    // logic for what happens when a button is clicked
    private ActionListener aListener = (new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            // if the user selects the start game button
            if (((JButton) e.getSource()).getText().equals("START")) {
                startGame();
            } else if (((JButton) e.getSource()).getText().equals("HIGH SCORES")) {
                Game.scoreSystem.showHighScores();
            } else if (((JButton) e.getSource()).getText().equals("INSTRUCTIONS")) {
                JOptionPane.showMessageDialog(null, instructions);
            }
        }
    });

    private final int NUMBER_OF_ITEMS = 3; // number of buttons to click in the menu

    public Menu() {
        this.initPanel();
    }

    public void startGame() {
        Game.g.clearFrame();
        Game.frame.add(Game.g.space.getPanel());
        Game.frame.getContentPane().revalidate();
        Game.frame.getContentPane().repaint();
        Game.g.space.startGame();
        Game.g.run();
    }

    private void initPanel() {
        this.panel.setBackground(Color.black);
        this.panel.setOpaque(true);
        this.panel.setLayout(new GridLayout(this.NUMBER_OF_ITEMS, 1));

        for (int i = 0; i < this.buttons.length; i++) {
            this.buttons[i] = new JButton("default text");
            this.buttons[i].setBackground(Color.black);
            this.buttons[i].setForeground(Color.white);
            this.buttons[i].addActionListener(this.aListener);
            this.panel.add(this.buttons[i]);
        }

        this.buttons[0].setText("START");
        this.buttons[1].setText("HIGH SCORES");
        this.buttons[2].setText("INSTRUCTIONS");
    }

    public JPanel getPanel() {
        return this.panel;
    }
}