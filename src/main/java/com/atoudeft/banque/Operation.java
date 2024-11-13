package com.atoudeft.banque;

import java.io.Serializable;
import java.util.Date;

public abstract class Operation implements Serializable {
    protected TypeOperation type;
    protected Date date;

    public Operation(TypeOperation type) {
        this.type = type;
        this.date = new Date(System.currentTimeMillis());
    }
}