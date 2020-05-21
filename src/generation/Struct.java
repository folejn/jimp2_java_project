package generation;

import static generation.CellProperties.*;

public class Struct {

    public static void drawNormalDiode(int x, int y, int c, int[][] matrixGen){
        int[][] nDiode = {{EMPTY,CONDUCTOR,EMPTY},{CONDUCTOR,CONDUCTOR,CONDUCTOR},{CONDUCTOR,EMPTY,CONDUCTOR},{EMPTY,CONDUCTOR,EMPTY}};
        x = Math.round((float) c / 2 - 2);
        for(int i = y; i < y+3; i++)
            for(int j = x; j < x+4; j++)
                matrixGen[i-1][j] = nDiode[j-x][i-y];

            for(int i = 0; i < c; i++){
                if(matrixGen[y][i] == 0)
                    matrixGen[y][i] = CONDUCTOR;
            }
    }

    public static void drawReversedDiode(int x, int y, int c, int[][] matrixGen){
        int[][] rDiode = {{EMPTY,CONDUCTOR,EMPTY},{CONDUCTOR,EMPTY,CONDUCTOR},{CONDUCTOR,CONDUCTOR,CONDUCTOR},{EMPTY,CONDUCTOR,EMPTY}};
        x = Math.round((float) c / 2 - 2);
        for(int i = y; i < y+3; i++)
            for(int j = x; j < x+4; j++)
                matrixGen[i-1][j] = rDiode[j-x][i-y];

        for(int i = 0; i < c; i++){
            if(matrixGen[y][i] == 0)
                matrixGen[y][i] = CONDUCTOR;
        }
    }

    public static void fillTable(int r, int c, int[][] matrixGen){
        for(int i = 0; i < r; i++)
            for(int j = 0; j < c; j++)
                if(matrixGen[i][j] == 0)
                    matrixGen[i][j] = EMPTY;
    }
}