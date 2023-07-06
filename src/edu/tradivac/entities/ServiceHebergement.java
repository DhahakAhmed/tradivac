/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.entities;

import java.sql.Date;

/**
 *
 * @author devhk
 */
public class ServiceHebergement {
   private int id_service;
    private int id_user;
    private String nom_service;
    private String type;
    private String description;
    private double prix;
    private String destination;
    private Date date_debut;
    private Date date_fin;
    private int duree;
    private int nb_trimestre;
    private String hebergement_classification_taille;
    private boolean hebergement_meublee;

    public ServiceHebergement() {
    }

    public ServiceHebergement(int id_service, int id_user, String nom_service, String type, String description, double prix, String destination, Date date_debut, Date date_fin, String hebergement_classification_taille, boolean hebergement_meublee) {
        this.id_service = id_service;
        this.id_user = id_user;
        this.nom_service = nom_service;
        this.type = type;
        this.description = description;
        this.prix = prix;
        this.destination = destination;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.duree = duree;
        this.nb_trimestre = nb_trimestre;
        this.hebergement_classification_taille = hebergement_classification_taille;
        this.hebergement_meublee = hebergement_meublee;
    }

    /**
     * @return the id_service
     */
    public int getId_service() {
        return id_service;
    }

    /**
     * @param id_service the id_service to set
     */
    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    /**
     * @return the id_user
     */
    public int getId_user() {
        return id_user;
    }

    /**
     * @param id_user the id_user to set
     */
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    /**
     * @return the nom_service
     */
    public String getNom_service() {
        return nom_service;
    }

    /**
     * @param nom_service the nom_service to set
     */
    public void setNom_service(String nom_service) {
        this.nom_service = nom_service;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the prix
     */
    public double getPrix() {
        return prix;
    }

    /**
     * @param prix the prix to set
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return the date_debut
     */
    public Date getDate_debut() {
        return date_debut;
    }

    /**
     * @param date_debut the date_debut to set
     */
    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    /**
     * @return the date_fin
     */
    public Date getDate_fin() {
        return date_fin;
    }

    /**
     * @param date_fin the date_fin to set
     */
    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    /**
     * @return the duree
     */
    public int getDuree() {
        return duree;
    }

    /**
     * @param duree the duree to set
     */
    public void setDuree(int duree) {
        this.duree = duree;
    }

    /**
     * @return the nb_trimestre
     */
    public int getNb_trimestre() {
        return nb_trimestre;
    }

    /**
     * @param nb_trimestre the nb_trimestre to set
     */
    public void setNb_trimestre(int nb_trimestre) {
        this.nb_trimestre = nb_trimestre;
    }

    /**
     * @return the hebergement_classification_taille
     */
    public String getHebergement_classification_taille() {
        return hebergement_classification_taille;
    }

    /**
     * @param hebergement_classification_taille the hebergement_classification_taille to set
     */
    public void setHebergement_classification_taille(String hebergement_classification_taille) {
        this.hebergement_classification_taille = hebergement_classification_taille;
    }

    /**
     * @return the hebergement_meublee
     */
    public boolean isHebergement_meublee() {
        return hebergement_meublee;
    }

    /**
     * @param hebergement_meublee the hebergement_meublee to set
     */
    public void setHebergement_meublee(boolean hebergement_meublee) {
        this.hebergement_meublee = hebergement_meublee;
    }

    
}
