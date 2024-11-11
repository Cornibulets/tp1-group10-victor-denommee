package com.atoudeft.serveur;

import com.atoudeft.banque.Banque;
import com.atoudeft.banque.CompteBancaire;
import com.atoudeft.banque.CompteClient;
import com.atoudeft.banque.serveur.ConnexionBanque;
import com.atoudeft.banque.serveur.ServeurBanque;
import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;

import java.util.Arrays;
import java.util.List;

/**
 * Cette classe représente un gestionnaire d'événement d'un serveur. Lorsqu'un serveur reçoit un texte d'un client,
 * il crée un événement à partir du texte reçu et alerte ce gestionnaire qui réagit en gérant l'événement.
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-09-01
 */
public class GestionnaireEvenementServeur implements GestionnaireEvenement {
    private final Serveur serveur;

    /**
     * Construit un gestionnaire d'événements pour un serveur.
     *
     * @param serveur Serveur Le serveur pour lequel ce gestionnaire gère des événements
     */
    public GestionnaireEvenementServeur(Serveur serveur) {
        this.serveur = serveur;
    }

    /**
     * Méthode de gestion d'événements. Cette méthode contiendra le code qui gère les réponses obtenues d'un client.
     *
     * @param evenement L'événement à gérer.
     */
    @Override
    public void traiter(Evenement evenement) {
        Object source = evenement.getSource();
        ServeurBanque serveurBanque = (ServeurBanque) serveur;
        Banque banque;
        ConnexionBanque cnx;
        String msg, typeEvenement, argument, numCompteClient, nip;
        String[] t;

        if (source instanceof Connexion) {
            cnx = (ConnexionBanque) source;
            System.out.println("SERVEUR: Recu : " + evenement.getType() + " " + evenement.getArgument());
            typeEvenement = evenement.getType();
            cnx.setTempsDerniereOperation(System.currentTimeMillis());
            switchTraitement:
            switch (typeEvenement) {
                /******************* COMMANDES GÉNÉRALES *******************/
                case "EXIT": //Ferme la connexion avec le client qui a envoyé "EXIT":
                    cnx.envoyer("END");
                    serveurBanque.enlever(cnx);
                    cnx.close();
                    break;
                case "LIST": //Envoie la liste des numéros de comptes-clients connectés :
                    cnx.envoyer("LIST " + serveurBanque.list());
                    break;
                /******************* COMMANDES DE GESTION DE COMPTES *******************/
                case "NOUVEAU": //Crée un nouveau compte-client :
                    if (cnx.getNumeroCompteClient() != null) {
                        cnx.envoyer("NOUVEAU NO deja connecte");
                        break;
                    }
                    argument = evenement.getArgument();
                    t = argument.split(":");
                    if (t.length < 2) {
                        cnx.envoyer("NOUVEAU NO");
                    } else {
                        numCompteClient = t[0];
                        nip = t[1];
                        banque = serveurBanque.getBanque();
                        if (banque.ajouter(numCompteClient, nip)) {
                            CompteClient compteClient = banque.getCompteClient(numCompteClient);
                            cnx.setNumeroCompteClient(numCompteClient);
                            cnx.setNumeroCompteActuel(banque.getNumeroCompteParDefaut(numCompteClient));
                            cnx.setCompteClient(compteClient);
                            cnx.setCompteBancaireActuel(compteClient.getComptes().get(0));
                            cnx.envoyer("NOUVEAU OK " + t[0] + " cree");
                        } else cnx.envoyer("NOUVEAU NO " + t[0] + " existe");
                    }
                    break;
                case "CONNECT": // Connection à un compte-client
                    String[] info = evenement.getArgument().split(":");
                    String numCompteClientEvt = info[0], nipEvt = info[1];
                    banque = serveurBanque.getBanque();

                    for (Connexion c : serveurBanque.connectes) {
                        ConnexionBanque cb = (ConnexionBanque) c;
                        if (cb.getNumeroCompteClient() != null && cb.getNumeroCompteClient().equals(numCompteClientEvt)) {
                            cnx.envoyer("CONNECT NO: CLIENT ALREADY CONNECTED");
                            break;
                        }
                    }

                    CompteClient compteClient = banque.getCompteClient(numCompteClientEvt);

                    if (compteClient == null) {
                        cnx.envoyer("CONNECT NO: INVALID CLIENT NUMBER");
                        break;
                    }
                    if (!compteClient.verifierNip(nipEvt)) {
                        cnx.envoyer("CONNECT NO: WRONG NIP");
                        break;
                    }

                    cnx.setNumeroCompteClient(compteClient.getNumero());
                    cnx.setCompteClient(compteClient);
                    cnx.setNumeroCompteActuel(banque.getNumeroCompteParDefaut(compteClient.getNumero()));
                    break;
                case "DISCONNECT":
                    numCompteClient = cnx.getNumeroCompteClient().toUpperCase(); // copie le string
                    if (cnx.getNumeroCompteClient() != null && !cnx.getNumeroCompteClient().isEmpty()) {
                        cnx.setNumeroCompteClient(null);
                        cnx.setNumeroCompteActuel(null);
                        cnx.setCompteClient(null);
                    }
                    cnx.envoyer(String.format("DISCONNECTED %s", numCompteClient));
                    break;
                // TODO 4.2 Ajouter EPARGNE (dépend de 4.1)
                // TODO 5.1 Ajouter SELECT (dépend de Q2 et Q4)
                // TODO 6.1 Ajouter DEPOT (dépend de Q2 et Q4)
                // TODO 6.2 Ajouter RETRAIT (dépend de Q2 et Q4)
                // TODO 6.3 Ajouter FACTURE (dépend de Q2 et Q4)
                // TODO 6.4 Ajouter TRANSFER (dépend de Q2 et Q4)

                case "SELECT":
                    if (cnx.getCompteClient() != null) {
                        String typeCompte = evenement.getArgument();
                        List<CompteBancaire> comptesBancaires = cnx.getCompteClient().getComptes();
                        for (CompteBancaire c : comptesBancaires) {
                            if (c.getType().toString().equals(typeCompte)) {
                                cnx.setNumeroCompteActuel(c.getNumero());
                                cnx.setCompteBancaireActuel(c);
                                cnx.envoyer("SELECT OK");
                                break switchTraitement;
                            }
                        }
                    }
                    cnx.envoyer("SELECT NO");
                    break;

                case "DEPOT":
                    if (cnx.getCompteClient() != null && cnx.getCompteBancaireActuel() != null) {
                        float montant = Float.parseFloat(evenement.getArgument());
                        if (cnx.getCompteBancaireActuel().crediter(montant)) {
                            cnx.envoyer("DEPOT OK");
                            break;
                        }
                    }
                    cnx.envoyer("DEPOT NO");
                    break;

                case "RETRAIT":
                    if (cnx.getCompteClient() != null && cnx.getCompteBancaireActuel() != null) {
                        float montant = Float.parseFloat(evenement.getArgument());
                        if (cnx.getCompteBancaireActuel().debiter(montant)) {
                            cnx.envoyer("RETRAIT OK");
                            break;
                        }
                    }
                    cnx.envoyer("RETRAIT NO");
                    break;

                case "FACTURE":
                    if (cnx.getCompteClient() != null && cnx.getCompteBancaireActuel() != null) {
                        String[] args = evenement.getArgument().split(" ");
                        if (args.length >= 3) {
                            try {
                                double montant = Float.parseFloat(args[0]);
                                String numeroFacture = args[1];
                                String descriptionFacture = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                                if (cnx.getCompteBancaireActuel().payerFacture(numeroFacture, montant, descriptionFacture)) {
                                    cnx.envoyer("FACTURE OK");
                                    break;
                                }
                            } catch (NumberFormatException ignored) {
                            }
                        }
                    }
                    cnx.envoyer("FACTURE NO");
                    break;

                case "TRANSFER":
                    if (cnx.getCompteClient() != null && cnx.getCompteBancaireActuel() != null) {
                        String[] args = evenement.getArgument().split(" ");
                        if (args.length == 2) {
                            try {
                                double montant = Float.parseFloat(args[0]);
                                String numeroCompte = args[1];
                                if (cnx.getCompteBancaireActuel().transferer(montant, numeroCompte)) {
                                    cnx.envoyer("FACTURE OK");
                                    break;
                                }
                            } catch (NumberFormatException ignored) {
                            }
                        }
                    }
                    cnx.envoyer("TRANSFER NO");
                    break;

                case "SOLDE":
                    if (cnx.getCompteClient() != null && cnx.getCompteBancaireActuel() != null) {
                        cnx.envoyer("SOLDE OK: " + cnx.getCompteBancaireActuel().getSolde() + "$");
                        break;
                    }
                    cnx.envoyer("SOLDE NO");
                    break;

                /******************* TRAITEMENT PAR DÉFAUT *******************/
                default: //Renvoyer le texte recu convertit en majuscules :
                    msg = (evenement.getType() + " " + evenement.getArgument()).toUpperCase();
                    cnx.envoyer(msg);
            }
        }
    }
}