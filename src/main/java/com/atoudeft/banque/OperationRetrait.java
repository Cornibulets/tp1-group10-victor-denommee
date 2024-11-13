package com.atoudeft.banque;

public class OperationRetrait extends Operation {
    private final double montant;

    public OperationRetrait(double montant) {
        super(TypeOperation.RETRAIT);
        this.montant = montant;
    }

    @Override
    public String toString(){
        return String.format("%s\t\t\t%s\t\t\t%s", this.date, this.type, this.montant);
    }
}
