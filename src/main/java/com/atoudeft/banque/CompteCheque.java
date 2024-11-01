package com.atoudeft.banque;

public class CompteCheque extends CompteBancaire{
    public CompteCheque(String numero){
        super(numero, TypeCompte.CHEQUE);
    }

    public boolean crediter(double montant){
        if(montant < 0) return false;
        this.solde += montant;
        return true;
    }
    public boolean debiter(double montant){
        if(montant < 0 || montant > this.solde) return false;
        this.solde -= montant;
        return true;
    }
    public boolean payerFacture(String numeroFacture, double montant, String description){
        // TODO : Alex -> À compléter plus tard
        return false;
    }
    public boolean transferer(double montant, String numeroCompteDestinataire){
        // TODO : Alex -> À compléter plus tard
        return false;
    }
}
