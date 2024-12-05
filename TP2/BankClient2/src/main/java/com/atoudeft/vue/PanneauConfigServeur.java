package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauConfigServeur extends JPanel {
    private JTextField txtAdrServeur, txtNumPort;

    public PanneauConfigServeur(String adr, int port) {
        txtAdrServeur = new JTextField(30);
        txtAdrServeur.setText(adr);
        txtNumPort = new JTextField(30);
        txtNumPort.setText(Integer.toString(port));
        JLabel labelAdrServeur = new JLabel("Adresse IP : ");
        JLabel labelNumPort = new JLabel("Port : ");

        JPanel pNORTH = new JPanel();
        JPanel pSOUTH = new JPanel();
        JPanel pTout = new JPanel(new GridLayout(2,2));

        pNORTH.add(labelAdrServeur, BorderLayout.WEST);
        pNORTH.add(txtAdrServeur, BorderLayout.EAST);
        pSOUTH.add(labelNumPort, BorderLayout.WEST);
        pSOUTH.add(txtNumPort, BorderLayout.EAST);
        pTout.add(pNORTH, BorderLayout.NORTH);
        pTout.add(pSOUTH, BorderLayout.SOUTH);
        this.add(pTout);
    }
    public String getAdresseServeur() {
        return txtAdrServeur.getText();
    }
    public String getPortServeur() {
        return txtNumPort.getText();
    }
}
