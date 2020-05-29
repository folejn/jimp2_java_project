package generation;

import static generation.CellProperties.*;

public class Struct {

    public static void drawNormalDiode(int x, int y, int c, int[][] matrixGen){
        int[][] nDiode = {{EMPTY,CONDUCTOR,EMPTY},
                        {CONDUCTOR,CONDUCTOR,CONDUCTOR},
                        {CONDUCTOR,EMPTY,CONDUCTOR},
                        {EMPTY,CONDUCTOR,EMPTY}};
        for(int i = y; i < y+3; i++)
            for(int j = x; j < x+4; j++)
                matrixGen[i-1][j] = nDiode[j-x][i-y];

            for(int i = 0; i < c; i++){
                if(matrixGen[y][i] == 0)
                    matrixGen[y][i] = CONDUCTOR;
            }

        for(int i = 0; i < c; i++){
            if(matrixGen[y+1][i] == 0)
                matrixGen[y+1][i] = EMPTY;
            if(matrixGen[y-1][i] == 0)
                matrixGen[y-1][i] = EMPTY;
        }
    }

    public static void drawReversedDiode(int x, int y, int c, int[][] matrixGen){
        int[][] rDiode = {{EMPTY,CONDUCTOR,EMPTY},
                        {CONDUCTOR,EMPTY,CONDUCTOR},
                        {CONDUCTOR,CONDUCTOR,CONDUCTOR},
                        {EMPTY,CONDUCTOR,EMPTY}};
        for(int i = y; i < y+3; i++)
            for(int j = x; j < x+4; j++)
                matrixGen[i-1][j] = rDiode[j-x][i-y];

        for(int i = 0; i < c; i++){
            if(matrixGen[y][i] == 0)
                matrixGen[y][i] = CONDUCTOR;
        }

        for(int i = 0; i < c; i++){
            if(matrixGen[y+1][i] == 0)
                matrixGen[y+1][i] = EMPTY;
            if(matrixGen[y-1][i] == 0)
                matrixGen[y-1][i] = EMPTY;
        }
    }

    public static void drawAND(int x, int y, int c, int[][] matrixGen) { //AND gate
        int[][] and = {{EMPTY, CONDUCTOR, EMPTY},
                    {CONDUCTOR, EMPTY, CONDUCTOR},
                    {CONDUCTOR, EMPTY, CONDUCTOR},
                    {CONDUCTOR, EMPTY, CONDUCTOR},
                    {CONDUCTOR, EMPTY, CONDUCTOR},
                    {CONDUCTOR, EMPTY, CONDUCTOR},
                    {EMPTY, CONDUCTOR, EMPTY}};

        for(int i = y; i < y+3; i++) {
            for (int j = x; j < x + 7; j++) {
                matrixGen[i-1][j] = and[j - x][i - y];
            }
        }

        for(int i = 0; i < c; i++){
            if(matrixGen[y][i] == 0)
                matrixGen[y][i] = CONDUCTOR;
        }

        for(int i = 0; i < c; i++){
            if(matrixGen[y+1][i] == 0)
                matrixGen[y+1][i] = EMPTY;
            if(matrixGen[y-1][i] == 0)
                matrixGen[y-1][i] = EMPTY;
        }
    }

    public static void drawNormalOR(int x, int y, int c, int[][] matrixGen){ //OR gate
        int[][] or = {{EMPTY, CONDUCTOR, EMPTY},
                    {CONDUCTOR, CONDUCTOR, CONDUCTOR},
                    {EMPTY, CONDUCTOR, EMPTY}};
        for(int i = y; i < y+3; i++)
            for(int j = x; j < x+3; j++)
                matrixGen[i-1][j] = or[j-x][i-y];

            for(int i = x; i < c; i++){ //linia z prawej strony
                if(matrixGen[y][i] == 0)
                    matrixGen[y][i] = CONDUCTOR;
            }

            for(int i = 0; i < x+1; i++){ //linii z lewej strony
                if(matrixGen[y+2][i] == 0)
                    matrixGen[y+2][i] = CONDUCTOR;
                if(matrixGen[y-2][i] == 0)
                    matrixGen[y-2][i] = CONDUCTOR;
            }

        for(int i = x+1; i < c; i++){
            if(matrixGen[y+2][i] == 0)
                matrixGen[y+2][i] = EMPTY;
            if(matrixGen[y+1][i] == 0)
                matrixGen[y+1][i] = EMPTY;
            if(matrixGen[y-2][i] == 0)
                matrixGen[y-2][i] = EMPTY;
            if(matrixGen[y-1][i] == 0)
                matrixGen[y-1][i] = EMPTY;
        }
    }

    public static void drawReversedOR(int x, int y, int c, int[][] matrixGen){
        int rOR[][] = {{EMPTY, CONDUCTOR, EMPTY},
                {CONDUCTOR, CONDUCTOR, CONDUCTOR},
                {EMPTY, CONDUCTOR, EMPTY}};

        for(int i = y; i < y+3; i++) {
            for (int j = x; j < x + 3; j++) {
                matrixGen[i - 1][j] = rOR[j - x][i - y];
            }
        }

        for(int i = 0; i < x; i++){ //linia z lewej strony
            if(matrixGen[y][i] == 0)
                matrixGen[y][i] = CONDUCTOR;
        }

        for(int i = x+2; i < c; i++){ //linii z prawej strony
            if(matrixGen[y+2][i] == 0)
                matrixGen[y+2][i] = CONDUCTOR;
            if(matrixGen[y-2][i] == 0)
                matrixGen[y-2][i] = CONDUCTOR;
        }
    }

    public static void drawNormalXOR(int x, int y, int c, int[][] matrixGen) { //exclusive-OR gate
        int[][] xor = {{EMPTY, CONDUCTOR, CONDUCTOR, CONDUCTOR, EMPTY},
                    {CONDUCTOR, CONDUCTOR, EMPTY, CONDUCTOR, CONDUCTOR},
                    {EMPTY, CONDUCTOR, EMPTY, CONDUCTOR, EMPTY},
                    {EMPTY, CONDUCTOR, CONDUCTOR, CONDUCTOR, EMPTY}};

        for(int i = y; i < y+5; i++) {
            for (int j = x; j < x+4; j++) {
                matrixGen[i-2][j] = xor[j-x][i-y];
            }
        }

        for(int i = x+4; i < c; i++){ //linia z prawej strony
            if(matrixGen[y][i] == 0)
                matrixGen[y][i] = CONDUCTOR;
        }

        for(int i = 0; i < x+1; i++){ //linii z lewej strony
            if(matrixGen[y+3][i] == 0)
                matrixGen[y+3][i] = CONDUCTOR;
            if(matrixGen[y-3][i] == 0)
                matrixGen[y-3][i] = CONDUCTOR;
        }

        for(int i = x+1; i < c; i++){
            if(matrixGen[y+3][i] == 0)
                matrixGen[y+3][i] = EMPTY;
            if(matrixGen[y+2][i] == 0)
                matrixGen[y+2][i] = EMPTY;
            if(matrixGen[y+1][i] == 0)
                matrixGen[y+1][i] = EMPTY;
            if(matrixGen[y-2][i] == 0)
                matrixGen[y-2][i] = EMPTY;
            if(matrixGen[y-1][i] == 0)
                matrixGen[y-1][i] = EMPTY;
            if(matrixGen[y-3][i] == 0)
                matrixGen[y-3][i] = EMPTY;
        }
        matrixGen[y][x+1] = matrixGen[y][x+2] = EMPTY;
    }

    public static void drawReversedXOR(int x, int y, int c, int[][] matrixGen) {
        int[][] rXOR = {{EMPTY, CONDUCTOR, CONDUCTOR, CONDUCTOR, EMPTY},
                {EMPTY, CONDUCTOR, EMPTY, CONDUCTOR, EMPTY},
                {CONDUCTOR, CONDUCTOR, EMPTY, CONDUCTOR, CONDUCTOR},
                {EMPTY, CONDUCTOR, CONDUCTOR, CONDUCTOR, EMPTY}};

        for(int i = y; i < y+5; i++) {
            for (int j = x; j < x+4; j++) {
                matrixGen[i-2][j] = rXOR[j-x][i-y];
            }
        }

        for(int i = 0; i < x+4; i++){ //linia z lewej strony
            if(matrixGen[y][i] == 0)
                matrixGen[y][i] = CONDUCTOR;
        }

        for(int i = x+3; i < c; i++){ //linii z prawej strony
            if(matrixGen[y+3][i] == 0)
                matrixGen[y+3][i] = CONDUCTOR;
            if(matrixGen[y-3][i] == 0)
                matrixGen[y-3][i] = CONDUCTOR;
        }

        for(int i = 0; i < x+1; i++){
            if(matrixGen[y+3][i] == 0)
                matrixGen[y+3][i] = EMPTY;
            if(matrixGen[y+2][i] == 0)
                matrixGen[y+2][i] = EMPTY;
            if(matrixGen[y+1][i] == 0)
                matrixGen[y+1][i] = EMPTY;
            if(matrixGen[y-2][i] == 0)
                matrixGen[y-2][i] = EMPTY;
            if(matrixGen[y-1][i] == 0)
                matrixGen[y-1][i] = EMPTY;
            if(matrixGen[y-3][i] == 0)
                matrixGen[y-3][i] = EMPTY;
        }
        matrixGen[y][x+1] = matrixGen[y][x+2] = EMPTY;
    }

    public static void fillTable(int r, int c, int[][] matrixGen){
        for(int i = 0; i < r; i++)
            for(int j = 0; j < c; j++)
                if(matrixGen[i][j] == 0)
                    matrixGen[i][j] = EMPTY;
    }
}