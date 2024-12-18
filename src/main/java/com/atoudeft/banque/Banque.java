package com.atoudeft.banque;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Banque implements Serializable {
    private String nom;
    private List<CompteClient> comptes;

    public Banque(String nom) {
        this.nom = nom;
        this.comptes = new ArrayList<>();
    }

    /**
     * Recherche un compte-client à partir de son numéro.
     *
     * @param numeroCompteClient le numéro du compte-client
     * @return le compte-client s'il a été trouvé. Sinon, retourne null
     */
    public CompteClient getCompteClient(String numeroCompteClient) {
        CompteClient cpt = new CompteClient(numeroCompteClient,"");
        int index = this.comptes.indexOf(cpt);
        if (index != -1)
            return this.comptes.get(index);
        else
            return null;
    }

    /**
     * Vérifier qu'un compte-bancaire appartient bien au compte-client.
     *
     * @param numeroCompteBancaire numéro du compte-bancaire
     * @param numeroCompteClient    numéro du compte-client
     * @return  true si le compte-bancaire appartient au compte-client
     */
    public boolean appartientA(String numeroCompteBancaire, String numeroCompteClient) {
        throw new NotImplementedException();
        // TODO 1
    }

    /**
     * Effectue un dépot d'argent dans un compte-bancaire
     *
     * @param montant montant à déposer
     * @param numeroCompte numéro du compte
     * @return true si le dépot s'est effectué correctement
     */
    public boolean deposer(double montant, String numeroCompte) {
        // TODO 2
        throw new NotImplementedException();
    }

    /**
     * Effectue un retrait d'argent d'un compte-bancaire
     *
     * @param montant montant retiré
     * @param numeroCompte numéro du compte
     * @return true si le retrait s'est effectué correctement
     */
    public boolean retirer(double montant, String numeroCompte) {
        // TODO 3
        throw new NotImplementedException();
    }

    /**
     * Effectue un transfert d'argent d'un compte à un autre de la même banque
     * @param montant montant à transférer
     * @param numeroCompteInitial   numéro du compte d'où sera prélevé l'argent
     * @param numeroCompteFinal numéro du compte où sera déposé l'argent
     * @return true si l'opération s'est déroulée correctement
     */
    public boolean transferer(double montant, String numeroCompteInitial, String numeroCompteFinal) {
        // TODO 4
        throw new NotImplementedException();
    }

    /**
     * Effectue un paiement de facture.
     * @param montant montant de la facture
     * @param numeroCompte numéro du compte bancaire d'où va se faire le paiement
     * @param numeroFacture numéro de la facture
     * @param description texte descriptif de la facture
     * @return true si le paiement s'est bien effectuée
     */
    public boolean payerFacture(double montant, String numeroCompte, String numeroFacture, String description) {
        // TODO 5
        throw new NotImplementedException();
    }

    /**
     * Crée un nouveau compte-client avec un numéro et un nip et l'ajoute à la liste des comptes.
     *
     * @param numCompteClient numéro du compte-client à créer
     * @param nip nip du compte-client à créer
     * @return true si le compte a été créé correctement
     */
    public boolean ajouter(String numCompteClient, String nip) {
        /*À compléter et modifier :
            - Vérifier que le numéro a entre 6 et 8 caractères et ne contient que des lettres majuscules et des chiffres.
              Sinon, retourner false.
            - Vérifier que le nip a entre 4 et 5 caractères et ne contient que des chiffres. Sinon,
              retourner false.
            - Vérifier s'il y a déjà un compte-client avec le numéro, retourner false.
            - Sinon :
                . Créer un compte-client avec le numéro et le nip;
                . Générer (avec CompteBancaire.genereNouveauNumero()) un nouveau numéro de compte bancaire qui n'est
                  pas déjà utilisé;
                . Créer un compte-chèque avec ce numéro et l'ajouter au compte-client;
                . Ajouter le compte-client à la liste des comptes et retourner true.
         */

        // Validations du numéro de compte et nip
        if(!validerNumeroCompte(numCompteClient)) return false;
        if(compteExisteDeja(numCompteClient)) return false;
        if(!validerNip(nip)) return false;

        // Création du compte client et compte chèque par défaut
        CompteClient nouveauCompte = new CompteClient(numCompteClient,nip);
        CompteCheque nouveauCompteCheque = new CompteCheque(genererNumCompteBancaireUnique());
        nouveauCompte.ajouter(nouveauCompteCheque);

        return this.comptes.add(nouveauCompte);
    }

    private boolean validerNumeroCompte(String numCompteClient){
        if (numCompteClient.length() < 6 || numCompteClient.length() > 8)
            return false;

        for (int i = 0; i < numCompteClient.length(); i++) {
            char currentChar = numCompteClient.charAt(i);
            if(!(currentChar >= 65 && currentChar <= 90) && !(currentChar >= 48 && currentChar <= 57))
                return false;
        }

        return true;
    }

    private boolean compteExisteDeja(String numCompteClient){
        if(findCompteClient(numCompteClient)==null) return false;
        return true;
    }

    private boolean validerNip(String nip){
        if (nip.length() < 4 || nip.length() > 5)
            return false;

        for (int i = 0; i < nip.length(); i++) {
            if (nip.charAt(i) < 48 || nip.charAt(i) > 57)
                return false;
        }

        return true;
    }

    public String genererNumCompteBancaireUnique(){
        String numeroCompte = CompteBancaire.genereNouveauNumero();
        boolean unique = false;

        while(!unique){
            unique = true;
            for(int i = 0;i < this.comptes.size();i++){
                for(int j = 0;j < this.comptes.get(i).getComptes().size();j++){
                    if(this.comptes.get(i).getComptes().get(j).getNumero().equals(numeroCompte)){
                        unique = false;
                        numeroCompte = CompteBancaire.genereNouveauNumero();
                    }
                }
            }
        }

        return numeroCompte;
    }

    private CompteClient findCompteClient(String numCompteClient){
        for(int i = 0;i < this.comptes.size();i++){
            if(this.comptes.get(i).getNumero().equals(numCompteClient))
                return this.comptes.get(i);
        }
        return null;
    }

    /**
     * Retourne le numéro du compte-chèque d'un client à partir de son numéro de compte-client.
     *
     * @param numCompteClient numéro de compte-client
     * @return numéro du compte-chèque du client ayant le numéro de compte-client
     */
    public String getNumeroCompteParDefaut(String numCompteClient) {
        return findCompteClient(numCompteClient).getCompte(0).getNumero();
    }

    public CompteBancaire getCompteParDefaut(String numCompteClient){
        return findCompteClient(numCompteClient).getCompte(0);
    }
}