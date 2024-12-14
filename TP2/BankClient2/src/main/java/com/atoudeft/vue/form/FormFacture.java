package com.atoudeft.vue.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FormFacture extends JPanel {

    private JTextField fieldMontant, fieldNoFacture, fieldDescription;
    private JButton boutonConfirmer, boutonAnnuler;

    public FormFacture() {
        this.fieldMontant = new JTextField(30);
        this.fieldMontant.setBorder(BorderFactory.createTitledBorder("Montant"));

        this.fieldNoFacture = new JTextField(30);
        this.fieldNoFacture.setBorder(BorderFactory.createTitledBorder("No Facture"));

        this.fieldDescription = new JTextField(30);
        this.fieldDescription.setBorder(BorderFactory.createTitledBorder("Description"));

        this.boutonConfirmer = new JButton("Facturer");
        this.boutonAnnuler = new JButton("Annuler");

        this.boutonConfirmer.setActionCommand("CONFIRME_FACTURE");
        this.boutonAnnuler.setActionCommand("ANNULER");

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel pTout = new JPanel(new GridLayout(4,1));

        p1.add(this.fieldMontant);
        p2.add(this.fieldNoFacture);
        p3.add(this.fieldDescription);
        p4.add(this.boutonConfirmer);
        p4.add(this.boutonAnnuler);

        this.setLayout(new BorderLayout());
        pTout.add(p1);
        pTout.add(p2);
        pTout.add(p3);
        pTout.add(p4);
        this.add(pTout, BorderLayout.NORTH);
        this.setBorder(BorderFactory.createLineBorder(new Color(0x00000000,true),200));
    }

    public String getFacture() {
        return this.fieldMontant.getText() + " " + this.fieldNoFacture.getText() + " " + this.fieldDescription.getText();
    }

    public void setEcouteur(ActionListener ecouteur) {
        boutonConfirmer.addActionListener(ecouteur);
        boutonAnnuler.addActionListener(ecouteur);
    }
}
