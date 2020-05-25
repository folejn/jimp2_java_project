package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import static gui.Properties.*;

public class InteractionPanel extends JPanel implements ActionListener {
    private JButton start;
    public InteractionPanel() {
        setPreferredSize(new Dimension(INTER_PANEL_WIDTH,INTER_PANEL_HEIGHT));
        //setLocation(100,100);
        setVisible(true);
        //setBackground(Color.CYAN);
        setLayout(new FlowLayout());
        start = new JButton("Start");
        start.addActionListener(this);
        add(start);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == start) {
            SymulationWindow.sym();
        }
    }

}
