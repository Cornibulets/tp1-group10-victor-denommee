package com.atoudeft.banque;

public class OperationTransfer extends Operation {
    private final double montant;
    private final String numeroCompteDestinataire;

    public OperationTransfer(double montant, String numeroCompteDestinataire) {
        super(TypeOperation.TRANSFER);
        this.montant = montant;
        this.numeroCompteDestinataire = numeroCompteDestinataire;
    }

    @Override
    public String toString(){
        return String.format("%s\t\t\t%s\t\t\t%s\t\t\t%s", this.date, this.type, this.montant, this.numeroCompteDestinataire);
    }
}
