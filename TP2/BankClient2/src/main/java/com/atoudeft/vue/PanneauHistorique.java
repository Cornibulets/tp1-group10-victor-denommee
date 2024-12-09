package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

public class PanneauHistorique extends JPanel {
    public PanneauHistorique(String historique){
        this.setLayout(new BorderLayout());

        JTextArea text = new JTextArea();
        text.setEditable(false);
        text.setColumns(4);
        text.setFont(new Font("Monospaced", Font.PLAIN, 12));
        String[] operations = historique.split("\n");

        for(int i=0;i<operations.length;i++){
            text.append(operations[i]+"\n");
        }

        this.add(text, BorderLayout.CENTER);
    }
}
