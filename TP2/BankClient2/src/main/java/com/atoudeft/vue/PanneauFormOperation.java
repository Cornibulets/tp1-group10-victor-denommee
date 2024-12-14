package com.atoudeft.vue;

import com.atoudeft.vue.form.FormDepot;
import com.atoudeft.vue.form.FormFacture;
import com.atoudeft.vue.form.FormRetrait;
import com.atoudeft.vue.form.FormTransfer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanneauFormOperation extends JPanel {
    private FormDepot formDepot;
    private FormRetrait formRetrait;
    private FormFacture formFacture;
    private FormTransfer formTransfer;

    public PanneauFormOperation() {
        this.formDepot = new FormDepot();
        this.formRetrait = new FormRetrait();
        this.formFacture = new FormFacture();
        this.formTransfer = new FormTransfer();
        this.add(formDepot, BorderLayout.NORTH);
        this.add(formRetrait, BorderLayout.NORTH);
        this.add(formFacture, BorderLayout.NORTH);
        this.add(formTransfer, BorderLayout.NORTH);
        this.setBorder(BorderFactory.createLineBorder(new Color(0x00000000,true),200));
        this.hideAll();
    }

    public void setEcouteur(ActionListener ecouteur) {
        this.formDepot.setEcouteur(ecouteur);
        this.formRetrait.setEcouteur(ecouteur);
        this.formFacture.setEcouteur(ecouteur);
        this.formTransfer.setEcouteur(ecouteur);
    }

    public void  hideAll() {
        this.formDepot.setVisible(false);
        this.formRetrait.setVisible(false);
        this.formFacture.setVisible(false);
        this.formTransfer.setVisible(false);
    }

    public void showFormDepot() {
        this.formDepot.setVisible(true);
    }

    public void showFormRetrait() {
        this.formRetrait.setVisible(true);
    }

    public void showFormFacture() {
        this.formFacture.setVisible(true);
    }

    public void showFormTransfer() {
        this.formTransfer.setVisible(true);
    }

    public String getDepot() {
        return this.formDepot.getDepot();
    }

    public String getRetrait() {
        return this.formRetrait.getRetrait();
    }

    public String getFacture() {
        return this.formFacture.getFacture();
    }

    public String getTransfer() {
        return  this.formTransfer.getTransfer();
    }
}
