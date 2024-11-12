package com.atoudeft.banque;

public class CompteCheque extends CompteBancaire{
    public CompteCheque(String numero){
        super(numero, TypeCompte.CHEQUE);
    }

    public boolean crediter(double montant){
        if(montant < 0) return false;
        this.solde += montant;
        // ajouter une entrée dans l'historique
        return true;
    }
    public boolean debiter(double montant){
        if(montant < 0 || montant > this.solde) return false;
        this.solde -= montant;
        // ajouter une entrée dans l'historique
        return true;
    }
    public boolean payerFacture(String numeroFacture, double montant, String description){
        return false;
    }
    public boolean transferer(double montant, String numeroCompteDestinataire){
        return false;
    }
}
