package gui;

public class Properties {
    public static int cellRows;
    public static int cellCols;

    public static int CELL_HEIGHT;
    public static int CELL_WIDTH;

    public Properties(int cellRows, int cellCols) {
        this.cellRows = cellRows;
        this.cellCols = cellCols;
        this.CELL_HEIGHT= WINDOW_HEIGHT/cellRows;
        this.CELL_WIDTH = (WINDOW_WIDTH-INTER_PANEL_WIDTH)/cellCols;
    }

    public static final int WINDOW_WIDTH = 900;
    public static final int WINDOW_HEIGHT = 600;

    public static final int INTER_PANEL_WIDTH = 100;
    public static final int INTER_PANEL_HEIGHT = WINDOW_HEIGHT;

}
