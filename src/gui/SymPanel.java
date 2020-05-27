package gui;

import generation.Gener;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static gui.Properties.*;
import static generation.CellProperties.*;
public class SymPanel extends JPanel {
    private class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
    public SymPanel() {
        setPreferredSize(new Dimension(WINDOW_WIDTH-INTER_PANEL_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.BLACK);
        setVisible(true);

    }
    public void paint(Graphics g) {
        Gener gen =SymulationWindow.gen;
        //Random rand = new Random();
        //int m[][] = new int[cellRows][cellCols];
        /*for(int i=0;i<cellRows;i++) {
            for (int j=0;j<cellCols;j++) {
                m[i][j] = rand.nextInt(4)+1;
            }
        }*/
        drawGener(gen,g);
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

    private void drawCell(int type, Point p, Graphics g) {
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
        g.fillRect(p.x,p.y,CELL_WIDTH,CELL_HEIGHT);
    }
    public void drawGener(Gener gen /*int m[][]*/,Graphics g) {
        Point p= new Point(0,0);
        int r=gen.getRows(), c=gen.getCols();
        //int r=cellRows;
        //int c=cellCols;

        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++){
                countCoordinates(j,i,p);
                //System.out.println("x= "+p.x+"y="+p.y);
                drawCell(gen.getValue(i,j) /*m[i][j]*/,p,g);
            }
        }
    }





}
