package generation;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.Scanner;
import static generation.CellProperties.*;

public class Gener {

    private int[][] matrixGen;
    private final int[][] next;
    private final int[][] zeroGen;
    private int r, c;   // wymiary macierzy będą pobierane z pliku przez readFromFile lub readStructFromFile


    public Gener(File inFile) throws Exception {
        if(whatTypeOfData(inFile))
            readFromFile(inFile);
        else
            readStructFromFile(inFile);
        next = new int[r][c];
        zeroGen = new int[r][c];
        for(int i=0;i<r;i++)
            for(int j=0;j<c;j++)
                zeroGen[i][j] = matrixGen[i][j];
    }
    public int getRows() {return r;}
    public int getCols() {return c;}
    public void backToStart() {
        for(int i=0;i<r;i++)
            for(int j=0;j<c;j++)
                matrixGen[i][j] = zeroGen[i][j];
    }
    public int getValue(int x, int y) throws NullPointerException{
        if (x > r || y > c || x < 0 || y < 0)
            throw new NullPointerException("Illegal coordinates");
        return matrixGen[x][y];
    }
    public void setValue(int x, int y, int value) throws NullPointerException{
        if (x > r || y > c || x < 0 || y < 0)
            throw new NullPointerException("Illegal coordinates");
        matrixGen[x][y] = value;
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

    private boolean whatTypeOfData(File inFile) throws Exception {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(inFile)));
            String tmp = sc.next();
            if(Character.isDigit(tmp.charAt(0)))
                return true;
            else
                return false;
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
        Dim dim = Struct.structFileLines(inFile);
        r = dim.r;
        c = dim.c;
        matrixGen = new int[++r][++c];

        Scanner nScanner = new Scanner(new BufferedReader(new FileReader(inFile)));
        while (nScanner.hasNextLine()) {
            String struct;
            String type;
            struct = nScanner.next().replaceAll(":", "");
            int x = Struct.replaceInStr(nScanner.next(),",","");
            int y = Struct.replaceInStr(nScanner.next(),",","");
            if(struct.equals("Diode")) {
                type = nScanner.next();
                if(type.equals("Normal"))
                    Struct.drawNormalDiode(x, y, c, matrixGen);
                else if(type.equals("Reversed"))
                    Struct.drawReversedDiode(x, y, c, matrixGen);
                continue;
            }
            if(struct.equals("OR")) {
                type = nScanner.next();
                if(type.equals("Normal"))
                    Struct.drawNormalOR(x, y, c, matrixGen);
                else if(type.equals("Reversed"))
                    Struct.drawReversedOR(x, y, c, matrixGen);
                continue;
            }
            if(struct.equals("XOR")) {
                type = nScanner.next();
                if(type.equals("Normal"))
                    Struct.drawNormalXOR(x, y, c, matrixGen);
                else if(type.equals("Reversed"))
                    Struct.drawReversedXOR(x, y, c, matrixGen);
                continue;
            }
            if(struct.equals("AND")) {
                Struct.drawAND(x, y, c, matrixGen);
                continue;
            }
            switch(struct){
                case "Empty":
                    matrixGen[y][x] = EMPTY;
                    break;
                case "ElectronHead":
                    matrixGen[y][x] = HEAD;
                    break;
                case "ElectronTail":
                    matrixGen[y][x] = TAIL;
                    break;
                case "Conductor":
                    matrixGen[y][x] = CONDUCTOR;
                    break;
            }
        }
        nScanner.close();

        Struct.fillTable(r, c, matrixGen);
    }

    public void writeToFile(File outFile){
        try {
            FileWriter f = new FileWriter(outFile);
            PrintWriter writer = new PrintWriter(f);
            for(int i = 0; i < r; i++){
                for(int j = 0; j < c; j++){
                    writer.print(matrixGen[i][j] + " ");
                }
                writer.println();
            }
            writer.close();
        } catch(Exception e) {
            System.err.println("Błąd przy zapisywaniu");
            System.err.println(e);
            System.exit(1);
        }
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
            System.err.println("Pointer to null exc");
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
    void whatCellType(int x, int y) {

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
