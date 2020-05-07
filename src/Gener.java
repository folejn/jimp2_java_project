import java.io.File;

public class Gener {

    private int[][] matrixGen;
    private final int[][] next;
    private int r, c;   // wymiary macierzy będą pobierane z pliku przez readFromFile lub readStructFromFile

    public Gener(File inFile) {
        //matrixGen = new int[r][c]; //- musi się znaleźć w funkcjach czytających z pliku, tutaj tymczasowo
        readStructFromFile(inFile);       // lub readFromFile, ale to implementujemy później
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
    private void readFromFile(File inFile) {
        /* pobiera dane tak jak w projekcie z C: w pliku jest macierz cyfr, która jest wczytywana;
        na początku funkcja podstawia wartości pod r i c, następnie wypełnia macierz
         */
        //TODO
    }

    private void readStructFromFile(File inFile) {
        /* pobiera dane w postaci opisanych struktur, przykład na isodzie (Diode(x,y) itp);
        na początku funkcja podstawia wartości pod r i c, następnie wypełnia macierz
         */
        matrixGen = new int[r][c];
        //TODO
    }

}
