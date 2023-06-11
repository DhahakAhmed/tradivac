/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.entitiess;


public class NoteService {
    private int id_note_service ;
    private int  id_service;
    private int id_user;
    private int nb_etoile;
    private String commentaire ;
    private String username_touriste;      

public NoteService(){
}
 public NoteService(int id_service ,int id_user ,int nb_etoile ,String commentaire,String username_touriste) {
    this. id_service =id_service;
    this.id_user=id_user;
    this.nb_etoile=nb_etoile;
    this.commentaire = commentaire;
    this.username_touriste=username_touriste;

}

    public int getId_note_servise() {
        return id_note_service;
    }

    public void setId_note_servise(int id_note_servise) {
        this.id_note_service = id_note_servise;
    }

    public int getId_Service() {
        return id_service;
    }

    public void setId_Service(int id_Service) {
        this.id_service = id_Service;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getNb_etoile() {
        return nb_etoile;
    }

    public void setNb_etoile(int nb_etoile) {
        this.nb_etoile = nb_etoile;
    }

    public String getCommentiare() {
        return commentaire;
    }

    public void setCommentiare(String commentiare) {
        this.commentaire = commentiare;
    }

    public String getUsername_touriste() {
        return username_touriste;
    }

    public void setUsername_touriste(String username_touriste) {
        this.username_touriste = username_touriste;
    }

}