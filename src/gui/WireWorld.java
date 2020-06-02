package gui;

import generation.Gener;
import java.awt.*;
import java.io.File;

public class WireWorld {
    static Gener gen;
    public static String[]outf = null;
    public static void main(String[] args) {
        gen = null;
        File in = new File(args.length > 0 ? args[0] : "resources/in.txt");
        int steps;
        try {
            steps = args.length > 1 ? Integer.parseInt(args[1]) : 20;
        } catch(Exception e) {
            steps = 20;
        }
        File out = new File(args.length >2 ? args[2] : "resources/out.txt");
        if(args.length > 3) {
            outf = new String[args.length-3];
            System.arraycopy(args,3,outf,0,outf.length);
        }
        if(out == null) {
            System.err.println(new NullPointerException());
            System.err.println("Cannot find input file");
            System.exit(3);
        }
        System.out.println(in);
        try {
            gen = new Gener(in);
        } catch (Exception e){
            System.err.println("Cannot open input file");
            System.err.println(e);
            System.exit(1);
        }
        Gener finalGen = gen;
        final int stepsN = steps;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SymulationWindow(finalGen,stepsN,out);
            }
        });
    }
}
