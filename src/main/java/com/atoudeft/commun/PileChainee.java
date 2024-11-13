package com.atoudeft.commun;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PileChainee implements Serializable {
    Noeud tete;
    private int numNoeuds;

    public PileChainee(){
        this.tete = null;
        this.numNoeuds = 0;
    }

    public void ajouter(Object data){
        tete = new Noeud(data, tete);
        numNoeuds++;
    }

    public Noeud getTete() {
        return tete;
    }

    public int getSize() {
        return numNoeuds;
    }

    public Noeud getNoeud(int index){
        if(index>=numNoeuds) return null;
        Noeud temp = tete;
        for(int i = 0;i<index;i++) {
            temp = temp.suivant;
        }
        return temp;
    }

    public Iterator<Noeud> iterator(){
        return new PileChaineeIterator();
    }

    public void remove(Object data){
        if(tete.data.equals(data)) {
            tete = tete.suivant;
        }else{
            Noeud temp = tete;

            for(int i = 0;i<numNoeuds;i++){
                if(temp.suivant.data.equals(data)){
                    temp.suivant = temp.suivant.suivant;
                    break;
                }
                temp = temp.suivant;
            }

            temp = null;
        }
    }

    public void remove(int index){
        if(index == 0){
            tete = tete.suivant;
        }else{
            Noeud temp = tete;

            for(int i = 0;i<index-1;i++){
                temp = temp.suivant;
            }
            temp.suivant = temp.suivant.suivant;
            temp = null;
        }
    }

    public class Noeud implements Serializable {
        private final Object data;
        private Noeud suivant;

        private Noeud(Object data, Noeud suivant) {
            this.data = data;
            this.suivant = suivant;
        }

        public Object getData(){
            return data;
        }
    }

    private class PileChaineeIterator implements Iterator<Noeud> {
        private int currentIndex;
        private Noeud currentNoeud;

        private PileChaineeIterator(){
            currentIndex = -1;
            currentNoeud = null;
        }

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < numNoeuds && numNoeuds > 0;
        }

        @Override
        public Noeud next() {
            if(!hasNext()) throw new NoSuchElementException();
            currentIndex++;
            if(currentNoeud == null)
                currentNoeud = tete;
            else
                currentNoeud = currentNoeud.suivant;

            return currentNoeud;
        }

        @Override
        public void remove(){
            PileChainee.this.remove(currentIndex);
        }
    }
}
