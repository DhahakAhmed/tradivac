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
public class ServiceRestauration {
    private int id_service;
    private int id_user;
    private String type;
    private String description;
    private double prix;
    private String destination;
    private Date date_debut;
    private Date date_fin;
    private String restauration_type;
    private boolean restauration_repas_sur_demande;
    private boolean restauration_dejeuner;
    private boolean restauration_diner;

    public ServiceRestauration(int id_service, int id_user, String type, String description, double prix, String destination, Date date_debut, Date date_fin, String restauration_type, boolean restauration_repas_sur_demande, boolean restauration_dejeuner, boolean restauration_diner) {
        this.id_service = id_service;
        this.id_user = id_user;
        this.type = type;
        this.description = description;
        this.prix = prix;
        this.destination = destination;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.restauration_type = restauration_type;
        this.restauration_repas_sur_demande = restauration_repas_sur_demande;
        this.restauration_dejeuner = restauration_dejeuner;
        this.restauration_diner = restauration_diner;
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
     * @return the restauration_type
     */
    public String getRestauration_type() {
        return restauration_type;
    }

    /**
     * @param restauration_type the restauration_type to set
     */
    public void setRestauration_type(String restauration_type) {
        this.restauration_type = restauration_type;
    }

    /**
     * @return the restauration_repas_sur_demande
     */
    public boolean isRestauration_repas_sur_demande() {
        return restauration_repas_sur_demande;
    }

    /**
     * @param restauration_repas_sur_demande the restauration_repas_sur_demande to set
     */
    public void setRestauration_repas_sur_demande(boolean restauration_repas_sur_demande) {
        this.restauration_repas_sur_demande = restauration_repas_sur_demande;
    }

    /**
     * @return the restauration_dejeuner
     */
    public boolean isRestauration_dejeuner() {
        return restauration_dejeuner;
    }

    /**
     * @param restauration_dejeuner the restauration_dejeuner to set
     */
    public void setRestauration_dejeuner(boolean restauration_dejeuner) {
        this.restauration_dejeuner = restauration_dejeuner;
    }

    /**
     * @return the restauration_diner
     */
    public boolean isRestauration_diner() {
        return restauration_diner;
    }

    /**
     * @param restauration_diner the restauration_diner to set
     */
    public void setRestauration_diner(boolean restauration_diner) {
        this.restauration_diner = restauration_diner;
    }
            
    
}
