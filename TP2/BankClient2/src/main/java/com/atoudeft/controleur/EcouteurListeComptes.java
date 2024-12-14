package com.atoudeft.controleur;

import com.atoudeft.client.Client;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class EcouteurListeComptes extends MouseAdapter {

    private Client client;

    public EcouteurListeComptes(Client client) {
        this.client = client;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2 && evt.getSource() instanceof  JList) {
            JList<?> liste = (JList<?>) evt.getSource();
            if (liste.getSelectedValue() instanceof  String) {
                if  (((String) liste.getSelectedValue()).contains("EPARGNE")) {
                    client.envoyer("SELECT epargne");
                } else if (((String) liste.getSelectedValue()).contains("CHEQUE")) {
                    client.envoyer("SELECT cheque");
                }
            }
        }

        //à compléter
    }
}
