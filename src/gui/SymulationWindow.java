package gui;

import javax.swing.*;
import java.awt.*;
import generation.Gener;
import static gui.Properties.*;
import static java.awt.BorderLayout.EAST;
import static java.awt.BorderLayout.WEST;


public class SymulationWindow extends JFrame {
    static JPanel full, panel1, panel2;
    static Gener gen;
    Properties p;
    public SymulationWindow(Gener gen) {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gen = gen;
        p= new Properties(gen.getRows(),gen.getCols());
        paintComponent();
    }

    private void paintComponent() {
        panel1 = new SymPanel();
        panel2 = new InteractionPanel();

        full = new JPanel(new BorderLayout());
        full.add(panel1,WEST);
        full.add(panel2,EAST);
        full.setVisible(true);

        JSplitPane pane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT,
                panel1,  panel2);
        pane.setEnabled(false);
        pane.setDividerLocation(WINDOW_WIDTH-INTER_PANEL_WIDTH);
        pane.setDividerSize(0);
        full.add(pane);
        add(full);
    }
    static public void sym() {
        int iter = 3;
        Graphics g=panel1.getGraphics();
        for(int i=0;i<iter;i++) {
            gen.nextStep();
            panel1.paint(g);

        }

    }



}
