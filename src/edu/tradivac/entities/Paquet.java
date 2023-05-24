/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author devhk
 */
public class Paquet {

    private Integer id;
    private String nom;
    private double prix;
    private Date dateDebut;
    private Date dateFin;
    private String discription;
    private String paquetPicture;
    private List<Service> services;

    public Paquet(Integer id, String Nom, double prix, Date dateDebut, Date dateFin, String discription, String paquetPicture, List<Service> services) {
        this.id = id;
        this.nom = Nom;
        this.prix = prix;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.discription = discription;
        this.paquetPicture = paquetPicture;
        this.services = services;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String Nom) {
        this.nom = Nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPaquetPicture() {
        return paquetPicture;
    }

    public void setPaquetPicture(String paquetPicture) {
        this.paquetPicture = paquetPicture;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Paquet{" + "id=" + id + ", Nom=" + nom + ", prix=" + prix + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", discription=" + discription + ", paquetPicture=" + paquetPicture + ", services=" + services + '}';
    }

  
}
