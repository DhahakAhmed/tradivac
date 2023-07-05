/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tradivac.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author itoga
 */
public class Pack {
    
    private int idPack;
    private String nombre;
    private String description;
    private double price;
    private LocalTime tiempoCreacion;
    private LocalDate fechaCreacion;
    private LocalDate startDate;
    private LocalTime tiempoInicio;
    private LocalDate finalDate;
    private LocalTime tiempoFin;
    private int iduser;
    private ArrayList<DetailsPack> listaactivities = new ArrayList<>();
    private static int totalPacks = 1;

    public Pack(String nombre, LocalDate startDate, LocalDate finalDate, String description) {
        this.nombre = nombre;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.description = description;
        this.idPack = totalPacks;
        totalPacks++;
    }

    public Pack() {
    }
    
    //Gets
    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDate getstartDate() {
        return startDate;
    }

    public LocalDate getfinalDate() {
        return finalDate;
    }
    
    public LocalDateTime getTimeCreacion(){
        LocalDateTime time = fechaCreacion.atTime(tiempoCreacion);
        return time;
    }
    
    public LocalDateTime getTimeInicio(){
        LocalDateTime time = startDate.atTime(tiempoInicio);
        return time;
    }
    
    public LocalDateTime getTimeFin(){
        LocalDateTime time = finalDate.atTime(tiempoFin);
        return time;
    }

    public int getIdPack() {
        return idPack;
    }

    public String getDescription() {
        return description;
    }

    public double getprice() {
        return price;
    }

    public int getIduser() {
        return iduser;
    }

    public ArrayList<DetailsPack> getListaactivities() {
        return listaactivities;
    }
    
    //sets

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setstartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setfinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public void setTiempoCreacion(LocalTime tiempoCreacion) {
        this.tiempoCreacion = tiempoCreacion;
    }
    
    public void setTiempoInicio(LocalTime tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public void setTiempoFin(LocalTime tiempoFin) {
        this.tiempoFin = tiempoFin;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public void setprice(double price) {
        this.price = price;
    }

    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }
    
    @Override
    public String toString() {
        return "Pack "+idPack+": "+nombre+" - "+price;
    }

    public void calcularpriceTotal(){
        for (DetailsPack linea : listaactivities) {
            price += linea.getPrice();
        }
    }
    
    public void addcart(DetailsPack linea){
        listaactivities.add(linea);
    }
    
}
