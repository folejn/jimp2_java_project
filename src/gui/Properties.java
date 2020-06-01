package gui;

public class Properties {
    public static int cellRows;
    public static int cellCols;

    public static int CELL_HEIGHT;
    public static int CELL_WIDTH;
    public static int center_x=0;
    public static int center_y=0;

    public Properties(int cellRows, int cellCols) {
        this.cellRows = cellRows;
        this.cellCols = cellCols;
        //this.CELL_HEIGHT= WINDOW_HEIGHT/cellRows;
        //this.CELL_WIDTH = (WINDOW_WIDTH-INTER_PANEL_WIDTH)/cellCols;
        if(cellCols>cellRows) {
            this.CELL_WIDTH=(WINDOW_WIDTH-INTER_PANEL_WIDTH)/cellCols;
            this.CELL_HEIGHT = this.CELL_WIDTH;
            center_y = (WINDOW_HEIGHT - CELL_WIDTH*cellRows)/2;
        }else{
            this.CELL_HEIGHT= WINDOW_HEIGHT/cellRows;
            this.CELL_WIDTH = this.CELL_HEIGHT;
            center_x = (WINDOW_WIDTH - INTER_PANEL_WIDTH - CELL_HEIGHT*cellCols)/2;
        }
    }

    public static final int WINDOW_WIDTH = 950;
    public static final int WINDOW_HEIGHT = 600;

    public static final int INTER_PANEL_WIDTH = 150;
    public static final int INTER_PANEL_HEIGHT = WINDOW_HEIGHT;

}
