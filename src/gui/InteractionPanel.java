package gui;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.Graphics2D;
import static gui.Properties.*;

public class InteractionPanel extends JPanel{
       public InteractionPanel() {
           setPreferredSize(new Dimension(INTER_PANEL_WIDTH, INTER_PANEL_HEIGHT));
        }

        @Override
        protected void paintComponent(Graphics g) {
           super.paintComponent(g);

        }
}
