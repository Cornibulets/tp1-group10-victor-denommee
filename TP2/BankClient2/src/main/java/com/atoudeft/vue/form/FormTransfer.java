package com.atoudeft.vue.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FormTransfer extends JPanel {

    private JTextField fieldMontant, fieldNoCompte;
    private JButton boutonConfirmer, boutonAnnuler;

    public FormTransfer() {
        this.fieldMontant = new JTextField(30);
        this.fieldMontant.setBorder(BorderFactory.createTitledBorder("Montant"));

        this.fieldNoCompte = new JTextField(30);
        this.fieldNoCompte.setBorder(BorderFactory.createTitledBorder("No Compte"));

        this.boutonConfirmer = new JButton("Transferer");
        this.boutonAnnuler = new JButton("Annuler");

        this.boutonConfirmer.setActionCommand("CONFIRME_TRANSFER");
        this.boutonAnnuler.setActionCommand("ANNULER");

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel pTout = new JPanel(new GridLayout(3,1));

        p1.add(this.fieldMontant);
        p2.add(this.fieldNoCompte);
        p3.add(this.boutonConfirmer);
        p3.add(this.boutonAnnuler);

        this.setLayout(new BorderLayout());
        pTout.add(p1);
        pTout.add(p2);
        pTout.add(p3);
        this.add(pTout, BorderLayout.NORTH);
        this.setBorder(BorderFactory.createLineBorder(new Color(0x00000000,true),200));
    }

    public String getTransfer() {
        return this.fieldMontant.getText() + " " + this.fieldNoCompte.getText();
    }

    public void setEcouteur(ActionListener ecouteur) {
        boutonConfirmer.addActionListener(ecouteur);
        boutonAnnuler.addActionListener(ecouteur);
    }
}
