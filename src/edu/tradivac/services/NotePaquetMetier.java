/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.services;

import edu.tradivac.entities.NotePaquet;
import edu.tradivac.interfaces.METIER;
import edu.tradivac.utils.MySQLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class NotePaquetMetier implements METIER<NotePaquet> {

    public static boolean contientMot(String mot, String[] motsListe) {
        for (String motListe : motsListe) {
            if (motListe.equals(mot)) {
                return true;
            }
        }
        return false;
    }

    public static String genererEtoiles(int longueurMot) {
        StringBuilder etoiles = new StringBuilder();
        for (int i = 0; i < longueurMot; i++) {
            etoiles.append("*");
        }
        return etoiles.toString();
    }

    @Override
    public String controle(String phrase, String[] motsListe) {
        String[] motsPhrase = phrase.split(" ");

        for (int i = 0; i < motsPhrase.length; i++) {
            String mot = motsPhrase[i];

            if (contientMot(mot, motsListe)) {
                motsPhrase[i] = genererEtoiles(mot.length());
            }
        }

        // Rejoindre les mots de la phrase modifiée en une seule chaîne
        String nouvellePhrase = String.join(" ", motsPhrase);

        return nouvellePhrase;
    }

    public String showName(NotePaquet t) {
        String fullName = null;
        try {
            String request = "SELECT u.first_name, u.last_name "
                    + "FROM user u "
                    + "JOIN notes_paquets np ON u.id_user = np.id_user "
                    + "WHERE np.id_user = ?";

            PreparedStatement pst = MySQLConnection.getInstance().getCnx().prepareStatement(request);
            pst.setInt(1, t.getId_user());
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                fullName = firstName + " " + lastName;
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return fullName;
    }

}
