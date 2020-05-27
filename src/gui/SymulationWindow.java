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
    static int steps;
    static int currentStep;
    static int sleepTime;
    public SymulationWindow(Gener gen,int steps) {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gen = gen;
        this.steps = steps;
        this.currentStep=0;
        this.sleepTime = 160;
        p= new Properties(gen.getRows(),gen.getCols());
        paintComponent();
    }

    private void paintComponent() {
        panel1 = new SymPanel();
        panel2 = new InteractionPanel();

        full = new JPanel(new BorderLayout());
        full.add(panel1);
        full.add(panel2);
        full.setVisible(true);

        JSplitPane pane = new JSplitPane( JSplitPane.HORIZONTAL_SPLIT,
                panel1,  panel2);
        pane.setEnabled(false);
        pane.setDividerLocation(WINDOW_WIDTH-INTER_PANEL_WIDTH);
        pane.setDividerSize(0);
        pane.setBackground(Color.lightGray);
        full.add(pane);
        add(full);
    }
    static public void sym() throws InterruptedException{
        Graphics g=panel1.getGraphics();
        while(steps>currentStep++) {
            gen.nextStep();
            panel1.paint(g);
            ((InteractionPanel) panel2).changeText();

            Thread.sleep(sleepTime);
        }

    }
    static public void backToFirst() {
        gen.backToStart();
        currentStep=0;
        ((InteractionPanel) panel2).changeText();
        panel1.paint(panel1.getGraphics());
    }


}
