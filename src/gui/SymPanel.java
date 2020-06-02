package gui;

import generation.Gener;

import javax.swing.*;
import java.awt.*;

import static gui.Properties.*;
import static generation.CellProperties.*;
public class SymPanel extends JPanel {

    Point v = new Point(center_x,center_y);
    public SymPanel() {
        setPreferredSize(new Dimension(WINDOW_WIDTH-INTER_PANEL_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.BLACK);
        setVisible(true);
    }
    public void paint(Graphics g) {
        Gener gen =SymulationWindow.gen;
        try {
            drawGener(gen, g);
        }catch(Exception e) {
            System.err.println("Incorect value provided for input");
            System.err.println("Search in the input file for invalid values");
            System.exit(1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        paint(g);
    }
    private void countCoordinates(int x, int y, Point p) {
        //return new Point(SCALE*x,SCALE*y);
        p.x = CELL_WIDTH*x;
        p.y = CELL_HEIGHT*y;
    }

    private void drawCell(int type, Point p, Graphics g) throws IllegalStateException{
        Color c;
        switch(type) {
            case EMPTY: c = Color.BLACK; break;
            case HEAD: c = Color.BLUE; break;
            case TAIL: c = Color.RED; break;
            case CONDUCTOR: c = Color.YELLOW; break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        g.setColor(c);
        g.fillRect(v.x+p.x,v.y+p.y,CELL_WIDTH,CELL_HEIGHT);
    }
    public void drawGener(Gener gen,Graphics g) throws IllegalStateException{
        Point p= new Point(0,0);
        int r=gen.getRows(), c=gen.getCols();
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++){
                countCoordinates(j,i,p);
                drawCell(gen.getValue(i,j),p,g);
            }
        }
    }





}
