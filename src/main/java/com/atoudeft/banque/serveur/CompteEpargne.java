package com.atoudeft.banque.serveur;

import com.atoudeft.banque.CompteBancaire;
import com.atoudeft.banque.TypeCompte;

public class CompteEpargne extends CompteBancaire {
    //Variables
    private double tauxInterets;
    private static final double LIMITE_SOLDE = 1000.0;
    private static final double FRAIS = 2.0;
    /**
     * Crée un compte bancaire.
     *
     * @param numero numéro du compte
     * @param type   type du compte
     */
    public CompteEpargne(String numero, TypeCompte type, double tauxInterets) {
        super(numero, type);
        this.tauxInterets = tauxInterets;
    }

    @Override
    public boolean crediter(double montant) {
        if (montant > 0)
            solde += montant;
        return false;
    }

    @Override
    public boolean debiter(double montant) {
        if (montant > 0 && solde >= montant) {
            solde -= montant;
        }
           if (solde < LIMITE_SOLDE) {
            solde -= FRAIS;
           }
        return false;
    }

    @Override
    public boolean payerFacture(String numeroFacture, double montant, String description) {
        return false;
    }

    @Override
    public boolean transferer(double montant, String numeroCompteDestinataire) {
        return false;
    }

    public void ajouterInterets() {
        double interets = (solde * tauxInterets) / 100;
        solde += interets;
    }
}
