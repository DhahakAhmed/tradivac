/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Meriem
 */
public class Reclamation {

    private int id_reclamation;
    private int id_touriste;
    private String objet;
    private String description;
    private Timestamp date;

    public Reclamation(int id_touriste, String objet, String description, Timestamp date) {

        this.id_touriste = id_touriste;
        this.objet = objet;
        this.description = description;
        this.date = date;
    }

    public Reclamation(int id_touriste, String objet, String description) {

        this.id_touriste = id_touriste;
        this.objet = objet;
        this.description = description;

    }

    public Reclamation(String objet, String description) {

        this.objet = objet;
        this.description = description;

    }

    public Reclamation() {
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public int getId_touriste() {
        return id_touriste;
    }

    public String getObjet() {
        return objet;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setId_touriste(int id_touriste) {
        this.id_touriste = id_touriste;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", id_touriste=" + id_touriste + ", objet=" + objet + ", description=" + description + ", date=" + date + '}';
    }

}
