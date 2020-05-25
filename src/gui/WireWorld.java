package gui;

import generation.Gener;
import java.awt.*;
import java.io.File;

public class WireWorld {
    File in;
    public static void main(String[] args) {
        Gener gen = null;
        File in = new File(args.length > 1 ? args[1] : "resources/in.txt");
        System.out.println(in);
        try {
            gen = new Gener(in);
        } catch (Exception e){
            System.err.println("Nie można otworzyć pliku wejściowego");
            System.exit(1);
        }
        Gener finalGen = gen;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SymulationWindow(finalGen);
            }
        });
    }
}
