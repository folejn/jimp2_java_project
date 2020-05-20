package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static gui.Properties.*;

public class SymulationWindow extends JFrame {
    JPanel panel1;
    JPanel panel2;
    public SymulationWindow() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT+40);
        setResizable(true);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paintComponent();
    }

    private void paintComponent() {
        /*panel1 = new JPanel();
        //panel1.setSize(WINDOW_WIDTH - INTER_PANEL_WIDTH,WINDOW_HEIGHT);
        panel1.setLayout(new GridLayout(5,2));
        panel1.add(new Button("test"));
        panel1.add(new Button("test"));
        add(panel1);
        panel1.setVisible(true);*/

        panel1 = new SymPanel();
        //getContentPane().add(panel1);
        //pack();
        add(panel1);

        panel2 = new InteractionPanel();
        add(panel2);
        // create a splitpane
        JSplitPane sl = new JSplitPane(SwingConstants.VERTICAL, panel1, panel2);
        sl.setEnabled(false);
        sl.setDividerLocation(WINDOW_WIDTH-INTER_PANEL_WIDTH);
        add(sl);
    }
}
