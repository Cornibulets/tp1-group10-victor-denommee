package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauFormOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;
    private PanneauFormOperation panneauFormOperation;

    public EcouteurOperationsCompte(Client client) {
        this.client = client;
    }

    public EcouteurOperationsCompte(Client client, PanneauFormOperation panneauOperationsCompte) {
        this.client = client;
        this.panneauFormOperation = panneauOperationsCompte;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String action;
        if(source instanceof JButton) {
            action = ((JButton) source).getActionCommand();
            switch(action) {
                case "EPARGNE":
                    client.envoyer("EPARGNE");
                    break;
                case "DEPOT":
                    this.panneauFormOperation.hideAll();
                    this.panneauFormOperation.showFormDepot();
                    break;
                case "CONFIRME_DEPOT":
                    client.envoyer("DEPOT " + panneauFormOperation.getDepot().replace(",", "."));
                    break;
                case "RETRAIT":
                    this.panneauFormOperation.hideAll();
                    this.panneauFormOperation.showFormRetrait();
                    break;
                case "CONFIRME_RETRAIT":
                    client.envoyer("RETRAIT " + panneauFormOperation.getRetrait().replace(",", "."));
                    break;
                case "FACTURE":
                    this.panneauFormOperation.hideAll();
                    this.panneauFormOperation.showFormFacture();
                    break;
                case "CONFIRME_FACTURE":
                    client.envoyer("FACTURE " + this.panneauFormOperation.getFacture());
                    break;
                case "TRANSFER":
                    this.panneauFormOperation.hideAll();
                    this.panneauFormOperation.showFormTransfer();
                    break;
                case "CONFIRME_TRANSFER":
                    client.envoyer("TRANSFER " + this.panneauFormOperation.getTransfer());
                    break;
                case "ANNULER":
                    this.panneauFormOperation.hideAll();
                    break;
                case "HIST":
                    client.envoyer("HIST");
                    break;
            }
        }
    }
}
