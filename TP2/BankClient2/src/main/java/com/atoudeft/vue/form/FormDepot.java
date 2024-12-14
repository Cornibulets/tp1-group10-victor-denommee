package com.atoudeft.vue.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FormDepot extends JPanel {

    private JTextField fieldMontant;
    private JButton boutonConfirmer, boutonAnnuler;

    public FormDepot() {
        this.fieldMontant = new JTextField(30);
        this.fieldMontant.setBorder(BorderFactory.createTitledBorder("Montant"));
        this.boutonConfirmer = new JButton("Deposer");
        this.boutonAnnuler = new JButton("Annuler");

        this.boutonConfirmer.setActionCommand("CONFIRME_DEPOT");
        this.boutonAnnuler.setActionCommand("ANNULER");

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel pTout = new JPanel(new GridLayout(3,1));

        p1.add(this.fieldMontant);
        p2.add(this.boutonConfirmer);
        p2.add(this.boutonAnnuler);

        this.setLayout(new BorderLayout());
        pTout.add(p1);
        pTout.add(p2);
        this.add(pTout, BorderLayout.NORTH);
        this.setBorder(BorderFactory.createLineBorder(new Color(0x00000000,true),200));
    }

    public String getDepot() {
        return this.fieldMontant.getText();
    }

    public void setEcouteur(ActionListener ecouteur) {
        boutonConfirmer.addActionListener(ecouteur);
        boutonAnnuler.addActionListener(ecouteur);
    }
}
