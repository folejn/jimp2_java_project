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
    private final int SCALE=CELL_SIZE;
    public SymPanel() {
        setPreferredSize(new Dimension(WINDOW_WIDTH-INTER_PANEL_WIDTH, WINDOW_HEIGHT));
        setVisible(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        int[][] m = new int[CELL_N][CELL_N];
        Random rand = new Random();
        for(int i=0; i<m.length; i++)
            for(int j=0; j<m[0].length; j++) {
                m[i][j] = rand.nextInt(4)+1;
            }
        drawGener(m,g);
    }
    private void countCoordinates(int x, int y, Point p) {
        //return new Point(SCALE*x,SCALE*y);
        p.x = SCALE*x;
        p.y = SCALE*y;
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
        g.fillRect(p.x,p.y,CELL_SIZE,CELL_SIZE);
    }
    public void drawGener(/*Gener gen*/int m[][],Graphics g) {
        Point p= new Point(0,0);
        //int r=gen.getRows(), c=gen.getCols();
        int r=m.length, c=m[0].length;

        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++){
                countCoordinates(i,j,p);
                drawCell(/*gen.getValue(i,j)*/ m[i][j],p,g);
            }
        }
    }





}
