package generation;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.Arrays;
import java.util.Scanner;
import static generation.CellProperties.*;

public class Gener {

    private int[][] matrixGen;
    private final int[][] next;
    private int r, c;   // wymiary macierzy będą pobierane z pliku przez readFromFile lub readStructFromFile


    public Gener(File inFile) throws Exception {
        //matrixGen = new int[r][c]; //- musi się znaleźć w funkcjach czytających z pliku, tutaj tymczasowo
        readStructFromFile(inFile);       // lub readFromFile, ale to implementujemy później
        next = new int[r][c];
    }
    public int getRows() {return r;}
    public int getCols() {return c;}

    public int getValue(int x, int y) throws IllegalArgumentException{
        if (x > r || y > c || x < 0 || y < 0)
            throw new IllegalArgumentException("Illegal coordinates");
        return matrixGen[x][y];
    }
    public void setValue(int x, int y, int value) throws IllegalArgumentException {
        if (x > r || y > c || x < 0 || y < 0)
            throw new IllegalArgumentException("Illegal coordinates");
        matrixGen[y][x] = value;
    }
    public void printOnConsole() {
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++)
                System.out.print(getValue(i,j));
            System.out.println("");
        }
    }

    public void nextStep() {
        for(int i=0; i<r; i++) {
            for (int j = 0; j < c; j++) {
                whatCellType(i, j);
            }
        }
        copyArrayValues();
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

        Struct.fillTable(r, c, matrixGen);
    }

    private void readStructFromFile(File inFile) throws Exception {
        r = 0;
        c = 0;
        int [] xArr = new int[256];
        int [] yArr = new int[256];
        int k = 0;
        Scanner Scanner = new Scanner(new BufferedReader(new FileReader(inFile)));
        while (Scanner.hasNextLine()) {
            String struct;
            int x = 0, y = 0;
            String xx, yy;
            struct = Scanner.next().replaceAll(":", "");
            xx = Scanner.next();
            x = Integer.parseInt(xx.replaceAll(",", ""));
            yy = Scanner.next();
            y = Integer.parseInt(yy.replaceAll(",", ""));
            if(struct.equals("Diode")) {
                if(y + 2 < 12)
                    y = 12;
                else
                    y += 2;
                if(x + 3 < 12)
                    x = 12;
                else
                    x += 3;
                Scanner.next();
            }
            xArr[k] = x;
            yArr[k] = y;
            k++;
        }
        Scanner.close();

        Arrays.sort(xArr);
        Arrays.sort(yArr);
        r = yArr[yArr.length - 1];
        c = xArr[xArr.length - 1];

        matrixGen = new int[r][c];

        Scanner nScanner = new Scanner(new BufferedReader(new FileReader(inFile)));
        while (nScanner.hasNextLine()) {
            String struct;
            int x = 0, y = 0;
            String xx, yy;
            String type;
            struct = nScanner.next().replaceAll(":", "");
            xx = nScanner.next();
            x = Integer.parseInt(xx.replaceAll(",", ""));
            yy = nScanner.next();
            y = Integer.parseInt(yy.replaceAll(",", ""));
            if(struct.equals("Diode")) {
                type = nScanner.next();
                if(type.equals("Normal"))
                    Struct.drawNormalDiode(x, y, c, matrixGen);
                else if(type.equals("Reversed"))
                    Struct.drawReversedDiode(x, y, c, matrixGen);
                continue;
            }
            switch(struct){
                case "Empty":
                    setValue(x, y, EMPTY);
                    break;
                case "ElectronHead":
                    setValue(x, y, HEAD);
                    break;
                case "ElectronTail":
                    setValue(x, y, TAIL);
                    break;
                case "Conductor":
                    setValue(x, y, CONDUCTOR);
            }
        }
        nScanner.close();

        Struct.fillTable(r, c, matrixGen);
    }

    private void copyArrayValues() {
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++)
                    setValue(i,j,getNext(i,j));
        }
    }
    private void setNext(int x, int y,int v) {
        try{
            next[x][y]=v;
        } catch (NullPointerException e){
            System.err.println("Błąd kopiowania do tablicy: zły adres");
            System.exit(1);
        }
    }
    private int getNext(int x, int y) {
        return next[x][y];
    }
    private boolean isInFrame(int x, int y)
    {
        if((x >= 0 && x < r) && (y >= 0 && y < c))
            return true;
        return false;
    }
    private void whatCellType(int x, int y) {

        int neighHeads = 0;

        if (isInFrame(x, y - 1))
            if (getValue(x, y - 1) == HEAD)
                neighHeads++;
        if (isInFrame(x, y + 1))
            if (getValue(x, y + 1) == HEAD)
                neighHeads++;
        if (isInFrame(x - 1, y))
            if (getValue(x - 1, y) == HEAD)
                neighHeads++;
        if (isInFrame(x + 1, y))
            if (getValue(x + 1, y) == HEAD)
                neighHeads++;
        if (isInFrame(x + 1, y + 1))
            if (getValue(x + 1, y + 1) == HEAD)
                neighHeads++;
        if (isInFrame(x - 1, y - 1))
            if (getValue(x - 1, y - 1) == HEAD)
                neighHeads++;
        if (isInFrame(x + 1, y - 1))
            if (getValue(x + 1, y - 1) == HEAD)
                neighHeads++;
        if (isInFrame(x - 1, y + 1))
            if (getValue(x - 1, y + 1) == HEAD)
                neighHeads++;

        int current = getValue(x, y);
        if(current == EMPTY)
            setNext(x, y, EMPTY);
        else if(current == HEAD)
            setNext(x, y, TAIL);
        else if(current == TAIL)
            setNext(x, y, CONDUCTOR);
        else if(neighHeads ==1 || neighHeads == 2)
            setNext(x,y,HEAD);
        else
            setNext(x,y,CONDUCTOR);
    }

}
