package com.atoudeft.client;

import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;
import com.atoudeft.vue.PanneauHistorique;
import com.atoudeft.vue.PanneauPrincipal;
import com.programmes.MainFrame;

import javax.swing.*;
import java.util.Objects;

public class GestionnaireEvenementClient2 implements GestionnaireEvenement {
    private Client client;
    private PanneauPrincipal panneauPrincipal;

    /**
     * Construit un gestionnaire d'événements pour un client.
     *
     * @param client Client Le client pour lequel ce gestionnaire gère des événements
     */
    public GestionnaireEvenementClient2(Client client, PanneauPrincipal panneauPrincipal) {

        this.client = client;
        this.panneauPrincipal = panneauPrincipal;
        this.client.setGestionnaireEvenement(this);
    }
    @Override
    public void traiter(Evenement evenement) {
        Object source = evenement.getSource();
        //Connexion cnx;
        String typeEvenement, arg, str;
        int i;
        String[] t;
        MainFrame fenetre;

        if (source instanceof Connexion) {
            //cnx = (Connexion) source;
            typeEvenement = evenement.getType();
            String[] args = evenement.getArgument().split(" ");
            switch (typeEvenement) {
                /******************* COMMANDES GÉNÉRALES *******************/
                case "END": //Le serveur demande de fermer la connexion
                    client.deconnecter(); //On ferme la connexion
                    break;
                /******************* CREATION et CONNEXION *******************/
                case "HIST": //Le serveur a renvoyé
                    panneauPrincipal.setVisible(true);
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal, new PanneauHistorique(arg));
                    break;
                case "OK":
                    panneauPrincipal.setVisible(true);
                    fenetre = (MainFrame)panneauPrincipal.getTopLevelAncestor();
                    fenetre.setTitle(MainFrame.TITRE);//+" - Connecté"
                    break;
                case "NOUVEAU":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Nouveau refusé");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        panneauPrincipal.ajouterCompte(str);
                    }
                    break;
                case "CONNECT":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Connexion refusée");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        t = str.split(":");
                        for (String s:t) {
                            panneauPrincipal.ajouterCompte(s);
                        }
                    }
                    break;
                /******************* SÉLECTION DE COMPTES *******************/
                case "EPARGNE" :
                    arg = evenement.getArgument();
                    if(arg.trim().startsWith("OK")){
                        String numCompte = arg.split(" ")[1];
                        panneauPrincipal.ajouterCompte(String.format("%s[EPARGNE] %s",numCompte, "0.0"));
                        JOptionPane.showMessageDialog(panneauPrincipal, String.format("Compte d'épargne %s créé avec succès.",numCompte), "Opération réussie", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(panneauPrincipal,"EPARGNE "+arg, "Opération refusée", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "SELECT" :
                    arg = evenement.getArgument();
//                    JOptionPane.showMessageDialog(panneauPrincipal,"SELECT "+arg);
                    if (Objects.equals(args[0], "OK")) {
                        panneauPrincipal.setSolde(Double.parseDouble(args[2].replace(",", ".")));
                    }
                    for (String argument : args) {
                        System.out.println(argument);
                    }
//                    panneauPrincipal.setSolde();

                    break;

                /******************* OPÉRATIONS BANCAIRES *******************/
                case "DEPOT" :
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal,"DEPOT "+arg);
                    args = arg.split(" ");
                    if (arg.contains("OK")) {
                        panneauPrincipal.setSolde(Double.parseDouble(args[1].replace(",", ".")));
                    }
                    break;
                case "RETRAIT" :
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal,"RETRAIT "+arg);
                    if (Objects.equals(args[0], "OK")) {
                        panneauPrincipal.setSolde(Double.parseDouble(args[1].replace(",", ".")));
                    }
                    break;
                case "FACTURE" :
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal,"FACTURE" + arg);
                    if (Objects.equals(args[0], "OK")) {
                        panneauPrincipal.setSolde(Double.parseDouble(args[1].replace(",", ".")));
                    }
                    break;
                case "TRANSFER" :
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal,"TRANSFER " + arg);

                    if (Objects.equals(args[0], "OK")) {
                        panneauPrincipal.setSolde(Double.parseDouble(args[1].replace(",", ".")));
                    }
                    break;
                /******************* TRAITEMENT PAR DÉFAUT *******************/
                default:
                    System.out.println("RECU : "+evenement.getType()+" "+evenement.getArgument());
            }
        }
    }
}