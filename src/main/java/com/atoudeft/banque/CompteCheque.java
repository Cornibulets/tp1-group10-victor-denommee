package com.atoudeft.banque;

public class CompteCheque extends CompteBancaire{
    public CompteCheque(String numero){
        super(numero, TypeCompte.CHEQUE);
    }

    public boolean crediter(double montant){
        if(montant < 0) return false;
        this.solde += montant;
        this.historique.ajouter(new OperationDepot(montant));
        return true;
    }
    public boolean debiter(double montant){
        if(montant < 0 || montant > this.solde) return false;
        this.solde -= montant;
        this.historique.ajouter(new OperationRetrait(montant));
        return true;
    }
    public boolean payerFacture(String numeroFacture, double montant, String description){
        this.historique.ajouter(new OperationFacture(montant, numeroFacture, description));
        return false;
    }
    public boolean transferer(double montant, String numeroCompteDestinataire){
        this.historique.ajouter(new OperationTransfer(montant, numeroCompteDestinataire));
        return false;
    }
}
