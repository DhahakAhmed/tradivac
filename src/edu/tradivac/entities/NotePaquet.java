/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.entities;

/**
 *
 * @author devhk
 */
public class NotePaquet {
    private int id_note_paquet;
    private int id_paquet;
    private int id_user;
    private int nb_etoiles;
    private String commentaire;
    
    public NotePaquet(){
        
    }
    public NotePaquet( int id_paquet,int id_user,int nb_etoiles, String commentaire){
        
        this.nb_etoiles = nb_etoiles;
        this.commentaire = commentaire;
        this.id_paquet = id_paquet;
        this.id_user = id_user;
        
    }

    /**
     * @return the id_note_paquet
     */
    public int getId_note_paquet() {
        return id_note_paquet;
    }

    /**
     * @param id_note_paquet the id_note_paquet to set
     */
    public void setId_note_paquet(int id_note_paquet) {
        this.id_note_paquet = id_note_paquet;
    }

    /**
     * @return the id_paquet
     */
    public int getId_paquet() {
        return id_paquet;
    }

    /**
     * @param id_paquet the id_paquet to set
     */
    public void setId_paquet(int id_paquet) {
        this.id_paquet = id_paquet;
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
     * @return the nb_etoiles
     */
    public int getNb_etoiles() {
        return nb_etoiles;
    }

    /**
     * @param nb_etoiles the nb_etoiles to set
     */
    public void setNb_etoiles(int nb_etoiles) {
        this.nb_etoiles = nb_etoiles;
    }

    /**
     * @return the commentaire
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * @param commentaire the commentaire to set
     */
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "NotePaquet{" + "id_note_paquet=" + id_note_paquet + ", id_paquet=" + id_paquet + ", id_user=" + id_user + ", nb_etoiles=" + nb_etoiles + ", commentaire=" + commentaire + '}';
    }
    
    
 }
