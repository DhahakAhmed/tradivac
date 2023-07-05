/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tradivac.entities;

/**
 *
 * @author itoga
 */
public class User {
    private int id = 0;
    private String rol = "client";
    private String nombre;
    private String mail;
    private int phone;
    private int card;
    private String pwd;

    public User(String nombre, String mail, int phone, int card, String pwd) {
        this.nombre = nombre;
        this.mail = mail;
        this.phone = phone;
        this.card = card;
        this.pwd = pwd;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public long getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
