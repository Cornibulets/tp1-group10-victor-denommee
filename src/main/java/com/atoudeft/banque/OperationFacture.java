package com.atoudeft.banque;

public class OperationFacture extends Operation {
    private final double montant;
    private final String numeroFacture;
    private final String description;

    public OperationFacture(double montant, String numeroFacture, String description) {
        super(TypeOperation.FACTURE);
        this.montant = montant;
        this.numeroFacture = numeroFacture;
        this.description = description;
    }

    @Override
    public String toString(){
        return String.format("%s\t\t\t%s\t\t\t%s\t\t\t%s\t\t\t%s", this.date, this.type, this.montant, this.numeroFacture, this.description);
    }
}
