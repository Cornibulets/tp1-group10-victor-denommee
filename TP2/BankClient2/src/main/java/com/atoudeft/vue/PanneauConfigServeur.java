package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauConfigServeur extends JPanel {
    private JTextField txtAdrServeur, txtNumPort;

    public PanneauConfigServeur(String adr, int port) {
        txtAdrServeur = new JTextField(20);
        txtAdrServeur.setText(adr);
        txtNumPort = new JTextField(20);
        txtNumPort.setText(Integer.toString(port));
        JLabel labelAdrServeur = new JLabel("Adresse IP : ");
        labelAdrServeur.setHorizontalAlignment(SwingConstants.RIGHT);
        JLabel labelNumPort = new JLabel("Port : ");
        labelNumPort.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel pTextFields = new JPanel();
        GridLayout textFieldsLayout = new GridLayout(2,1);
        textFieldsLayout.setVgap(5);
        pTextFields.setLayout(textFieldsLayout);
        pTextFields.add(this.txtAdrServeur);
        pTextFields.add(this.txtNumPort);

        JPanel pLabels = new JPanel();
        GridLayout labelsLayout = new GridLayout(2,1);
        labelsLayout.setVgap(5);
        pLabels.setLayout(labelsLayout);
        pLabels.add(labelAdrServeur);
        pLabels.add(labelNumPort);

        JPanel pParent = new JPanel(new BorderLayout());
        pParent.add(pTextFields, BorderLayout.CENTER);
        pParent.add(pLabels, BorderLayout.WEST);
        this.add(pParent);
    }
    public String getAdresseServeur() {
        return txtAdrServeur.getText();
    }
    public String getPortServeur() {
        return txtNumPort.getText();
    }
}
