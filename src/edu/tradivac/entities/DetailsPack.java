/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tradivac.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Juan Carlos
 */
public class DetailsPack {

    private int idPack;
    private int idactivity;
    private int numLinea;
    private String nombreDetailsPack;
    private int numPlaces;
    private double price;
    private double priceBase;
    private LocalDate startDate;
    private LocalDate finalDate;
    private String startDateES;
    private String finalDateES;
    private LocalTime duration;
    private ImageView imgBorrar = new ImageView(new Image("/edu/tradivac/img/borrar.png"));

    public DetailsPack(int idPack, int idactivity, String nombreDetailsPack, int numPlaces, double price, LocalDate startDate, LocalDate finalDate, LocalTime duration) {
        this.idPack = idPack;
        this.idactivity = idactivity;
        this.nombreDetailsPack = nombreDetailsPack;
        this.numPlaces = numPlaces;
        this.price = price;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.startDateES = formatFechaES(startDate);
        this.finalDateES = formatFechaES(finalDate);
        this.duration = duration;
        imgBorrar.setFitHeight(33);
        imgBorrar.setFitWidth(33);
    }

    public DetailsPack() {
    }

    public String getStartDateES() {
        return startDateES;
    }

    public void setStartDateES(String startDateES) {
        this.startDateES = startDateES;
    }

    public String getFinalDateES() {
        return finalDateES;
    }

    public void setFinalDateES(String finalDateES) {
        this.finalDateES = finalDateES;
    }

    //sets
    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }

    public void setIdActivity(int idactivity) {
        this.idactivity = idactivity;
    }

    public void setNombreDetailsPack(String nombreDetailsPack) {
        this.nombreDetailsPack = nombreDetailsPack;
    }

    public void setNumPlaces(int numPlaces) {
        this.numPlaces = numPlaces;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;

    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public void setNumLinea(int numLinea) {
        this.numLinea = numLinea;
    }

    public void setImgBorrar(ImageView imgBorrar) {
        this.imgBorrar = imgBorrar;
    }

    public void setPriceBase(double priceBase) {
        this.priceBase = priceBase;
    }

    //gets
    public int getIdPack() {
        return idPack;
    }

    public int getIdActivity() {
        return idactivity;
    }

    public String getNombreDetailsPack() {
        return nombreDetailsPack;
    }

    public int getNumPlaces() {
        return numPlaces;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public int getNumLinea() {
        return numLinea;
    }

    public ImageView getImgBorrar() {
        return imgBorrar;
    }

    public double getPriceBase() {
        return priceBase;
    }

    public String formatFechaES(LocalDate fecha) {
        DateTimeFormatter formatoEspana = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaEspana = fecha.format(formatoEspana);
        return fechaEspana;
    }

    @Override
    public String toString() {
        return "DetailsPack{" + "idPack=" + idPack + ", idactivity=" + idactivity + ", numLinea=" + numLinea + ", nombreDetailsPack=" + nombreDetailsPack + ", numPlaces=" + numPlaces + ", price=" + price + ", priceBase=" + priceBase + ", startDate=" + startDate + ", finalDate=" + finalDate + ", startDateES=" + startDateES + ", finalDateES=" + finalDateES + ", duration=" + duration + ", imgBorrar=" + imgBorrar + '}';
    }
    
    
}
