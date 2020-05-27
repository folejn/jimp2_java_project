package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import static gui.Properties.*;

public class InteractionPanel extends JPanel implements ActionListener {
    private JButton start, first;
    private TextField genNr;
    public InteractionPanel() {
        setPreferredSize(new Dimension(INTER_PANEL_WIDTH,INTER_PANEL_HEIGHT));
        //setLocation(100,100);
        setVisible(true);
        //setBackground(new Color(160,190,180));
        setBackground(new Color(200,255,230));
        setLayout(new FlowLayout());
        start = new JButton("Start");
        start.addActionListener(this);
        add(start);

        first = new JButton("Go back to start");
        first.addActionListener(this);
        add(first);
        genNr = new TextField("gen="+SymulationWindow.currentStep);
        genNr.setSize(INTER_PANEL_WIDTH,20);
        add(genNr);

    }
    public void changeText() {
        genNr.setText("gen="+SymulationWindow.currentStep);
        genNr.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == start) {
            try{
                SymulationWindow.sym();
            } catch(Exception ex){
                System.err.println("Symulation interrupted");
            }
        }
        if(source == first) {
            SymulationWindow.backToFirst();
        }
    }

}
