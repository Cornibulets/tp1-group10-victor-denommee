package com.atoudeft.banque;

public class OperationDepot extends Operation {
    private final double montant;

    public OperationDepot(double montant) {
        super(TypeOperation.DEPOT);
        this.montant = montant;
    }

    @Override
    public String toString(){
        return String.format("%s\t\t\t%s\t\t\t%s", this.date, this.type, this.montant);
    }
}
