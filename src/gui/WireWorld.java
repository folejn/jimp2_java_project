package gui;

import generation.Gener;
import java.awt.*;
import java.io.File;

public class WireWorld {
    File in;
    static Gener gen;
    public static String[]outf = null;
    public static void main(String[] args) {
        int st = 0;
        gen = null;
        File in = new File(args.length > 1 ? args[1] : "resources/inStruct.txt");
        int steps = args.length > 2 ? Integer.parseInt(args[2]) : 20;
        File out = new File(args.length >3 ? args[3] : "resources/out.txt");
        if(args.length > 4) {
            outf = new String[args.length-4];
            System.arraycopy(args,4,outf,0,outf.length);
            System.out.println(outf[0]);
        }
        if(out == null) {
            System.err.println(new NullPointerException());
            System.err.println("Nie można znaleźć pliku wyjściowego");
            System.exit(3);
        }
        System.out.println(in);
        try {
            gen = new Gener(in);
        } catch (Exception e){
            System.err.println("Nie można otworzyć pliku wejściowego");
            System.err.println(e);
            System.exit(1);
        }
        Gener finalGen = gen;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SymulationWindow(finalGen,steps,out);
            }
        });
    }
}
