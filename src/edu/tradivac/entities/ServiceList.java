/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.entities;

/**
 *
 * @author devhk
 */
public class ServiceList {
    private String nom_service;
    private String type;
    private String destination;
    private double prix;
    private ServiceExcursion SE;

    public ServiceList() {
    }

    public ServiceList(String nom_service, String type, String destination, double prix, ServiceExcursion SE) {
        this.nom_service = nom_service;
        this.type = type;
        this.destination = destination;
        this.prix = prix;
        this.SE = SE;
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
     * @return the SE
     */
    public ServiceExcursion getSE() {
        return SE;
    }

    /**
     * @param SE the SE to set
     */
    public void setSE(ServiceExcursion SE) {
        this.SE = SE;
    }
    
    
}

