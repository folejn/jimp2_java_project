import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.Scanner;


public class Gener {

    private int[][] matrixGen;
    private final int[][] next;
    private int r, c;   // wymiary macierzy będą pobierane z pliku przez readFromFile lub readStructFromFile

    public Gener(File inFile) throws Exception {
        //matrixGen = new int[r][c]; //- musi się znaleźć w funkcjach czytających z pliku, tutaj tymczasowo
        readFromFile(inFile);       // lub readFromFile, ale to implementujemy później
        next = new int[r][c];
    }
    public int getValue(int r, int c) throws IllegalArgumentException{
        if (r > matrixGen.length || c > matrixGen[0].length || r < 0 || c < 0)
            throw new IllegalArgumentException("Illegal coordinates");
        return matrixGen[r][c];
    }
    public void setValue(int r, int c, int value) throws IllegalArgumentException {
        if (r > matrixGen.length || c > matrixGen[0].length || r < 0 || c < 0)
            throw new IllegalArgumentException("Illegal coordinates");
        matrixGen[r][c] = value;
    }
    private void readFromFile(File inFile) throws Exception{
        r = c = 0;
        Scanner sc = new Scanner(new BufferedReader(new FileReader(inFile)));
        while (sc.hasNextLine()) {
            String tmp;
            r++;
            tmp = sc.nextLine().replaceAll(" ", "");
            c = tmp.length();
        }
        sc.close();
        matrixGen = new int[r][c];
        Scanner scn = new Scanner(new BufferedReader(new FileReader(inFile)));
        while(scn.hasNextLine()) {
            for (int i = 0; i < matrixGen.length; i++) {
                String[] line = scn.nextLine().trim().split(" ");
                for (int j = 0; j < line.length; j++) {
                    matrixGen[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        scn.close();
        for(int i = 0; i < r; i++)
            for(int j = 0; j < c; j++)
                System.out.println(matrixGen[i][j]);
    }

    private void readStructFromFile(File inFile) {
        /* pobiera dane w postaci opisanych struktur, przykład na isodzie (Diode(x,y) itp);
        na początku funkcja podstawia wartości pod r i c, następnie wypełnia macierz
         */
        //matrixGen = new int[r][c];
        //TODO
    }

}
