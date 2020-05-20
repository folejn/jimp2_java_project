package gui;

import javax.swing.*;
import java.awt.*;

public class WireWorld {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SymulationWindow();
            }
        });
    }
}
