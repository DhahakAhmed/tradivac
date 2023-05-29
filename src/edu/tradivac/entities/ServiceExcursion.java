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
public class ServiceExcursion {
    private int id_service;
    private int id_user;
    private String type;
    private String description;
    private double prix;
    private String destination;
    private Date date_debut;
    private Date date_fin;
    private boolean excursion_plage;
    private boolean excursion_foret;
    private String excursion_type;

    public ServiceExcursion(){
    
    };
    
    public ServiceExcursion(String type,int id_user,String description, double prix, String destination, Date date_debut, Date date_fin, boolean excursion_plage, boolean excursion_foret, String excursion_type ){
        this.type = type;
        this.id_user = id_user;
        this.description = description;
        this.prix = prix;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.excursion_plage = excursion_plage;
        this.excursion_foret = excursion_foret;
        this.excursion_type = excursion_type;
        
    };

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
     * @return the excursion_plage
     */
    public boolean isExcursion_plage() {
        return excursion_plage;
    }

    /**
     * @param excursion_plage the excursion_plage to set
     */
    public void setExcursion_plage(boolean excursion_plage) {
        this.excursion_plage = excursion_plage;
    }

    /**
     * @return the excursion_foret
     */
    public boolean isExcursion_foret() {
        return excursion_foret;
    }

    /**
     * @param excursion_foret the excursion_foret to set
     */
    public void setExcursion_foret(boolean excursion_foret) {
        this.excursion_foret = excursion_foret;
    }

    /**
     * @return the excursion_type
     */
    public String getExcursion_type() {
        return excursion_type;
    }

    /**
     * @param excursion_type the excursion_type to set
     */
    public void setExcursion_type(String excursion_type) {
        this.excursion_type = excursion_type;
    }

    /**
     * @return the id_user
     */
    public int getId_user() {
        return id_user;
    }

    
}
