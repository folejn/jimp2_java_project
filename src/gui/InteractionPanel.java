package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;

import static gui.Properties.*;

public class InteractionPanel extends JPanel implements ActionListener, AdjustmentListener {
    private JButton start, first;
    private TextField genNr, speedText;
    private JScrollBar speed;
    public InteractionPanel() {
        setPreferredSize(new Dimension(INTER_PANEL_WIDTH,INTER_PANEL_HEIGHT));
        //setLocation(100,100);
        setVisible(true);
        //setBackground(new Color(160,190,180));
        setBackground(new Color(200,255,230));
        //setLayout(new FlowLayout());
        //setLayout(new GridLayout(,1));
        setLayout(null);

        start = new JButton("Start");
        start.setBounds(10,0,INTER_PANEL_WIDTH-30,30);
        start.addActionListener(this);
        add(start);

        first = new JButton("Go to start");
        first.setBounds(10,50,INTER_PANEL_WIDTH-30,30);
        first.addActionListener(this);
        add(first);

        genNr = new TextField("gen="+SymulationWindow.currentStep);
        genNr.setBounds(0,90,INTER_PANEL_WIDTH-30,30);
        genNr.setEditable(false);
        genNr.setSize(INTER_PANEL_WIDTH,20);
        add(genNr);

        speed = new JScrollBar(0,5,5,1,10);
        speed.setBounds(0,150,INTER_PANEL_WIDTH-20,20);
        speed.addAdjustmentListener(this);
        add(speed);

        speedText = new TextField();
        speedText.setBounds(0,175,INTER_PANEL_WIDTH-30,30);
        speedText.setEditable(false);
        speedText.setSize(INTER_PANEL_WIDTH,20);
        speedText.setText(Integer.toString(SymulationWindow.sleepTime)+"ms");
        add(speedText);

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
            first.repaint();
        }
    }
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        Object source = e.getSource();
        if(source == speed) {
            final int a = 50;
            SymulationWindow.sleepTime = a * speed.getValue();
            speedText.setText(Integer.toString(SymulationWindow.sleepTime)+"ms");
        }
    }

}
