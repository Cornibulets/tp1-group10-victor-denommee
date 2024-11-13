package com.atoudeft.banque;

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
        if (montant > 0){
            solde += montant;
            this.historique.ajouter(new OperationDepot(montant));
            return true;
        }
        return false;
    }

    @Override
    public boolean debiter(double montant) {
        if (montant > 0 && solde >= montant) {
            if (solde < LIMITE_SOLDE)
                solde -= FRAIS; // le compte pourrait être négatif après cette opération et le retrait, on assume que c'est ok
            solde -= montant;
            this.historique.ajouter(new OperationRetrait(montant));
            return true;
        }

        return false;
    }

    @Override
    public boolean payerFacture(String numeroFacture, double montant, String description) {
        this.historique.ajouter(new OperationFacture(montant, numeroFacture, description));
        return false;
    }

    @Override
    public boolean transferer(double montant, String numeroCompteDestinataire) {
        this.historique.ajouter(new OperationTransfer(montant,numeroCompteDestinataire));
        return false;
    }

    public void ajouterInterets() {
        // double interets = solde * tauxInterets / 100.; // mettre les calculs simples sur une ligne et éviter de créer des variables inutilement
        solde += solde * tauxInterets / 100.;
    }
}
