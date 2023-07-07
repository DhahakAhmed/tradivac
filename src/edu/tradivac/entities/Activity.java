/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tradivac.entities;
/**
 *
 * @author Meriem
 */
public class Activity {

    private int idActivity;
    private String nombre;
    private String description;
    private String image;
    private String url;
    private int quality;
    private double price;
    private String type;
    private String location;

    //Constructor
    public Activity(int idActivity, String nombre, String description, String image, String url, int quality, double price, String type) {
        this.idActivity = idActivity;
        this.nombre = nombre;
        this.description = description;
        this.image = image;
        this.url = url;
        this.quality = quality;
        this.price = price;
        this.type = type;
    }
    
    public Activity(int idActivity, String nombre, String description, String image, String url, double price, String type) {
        this.idActivity = idActivity;
        this.nombre = nombre;
        this.description = description;
        this.image = image;
        this.url = url;
        this.price = price;
        this.type = type;
    }

    public Activity(int idActivity, String nombre, String description, String image, String url, int quality, double price, String type, String location) {
        this.idActivity = idActivity;
        this.nombre = nombre;
        this.description = description;
        this.image = image;
        this.url = url;
        this.quality = quality;
        this.price = price;
        this.type = type;
        this.location = location;
    }
    
    

    //gets
    public int getIdActivity() {
        return idActivity;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }

    public int getQuality() {
        return quality;
    }

    public double getPrice() {
        return price;
    }

    public String gettype() {
        return type;
    }

    public String getLocation() {
        return location;
    }
    
    

    //sets
    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void settype(String type) {
        this.type = type;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    @Override
    public String toString() {
        return "Activity" + idActivity + ": " + nombre + " - " + price;
    }

}
