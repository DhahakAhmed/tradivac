/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.tests;

import edu.tradivac.api.EmailSender;
import edu.tradivac.entities.NotePaquet;
import edu.tradivac.services.NotePaquetCrud;
import edu.tradivac.services.NotePaquetMetier;
import edu.tradivac.utils.MySQLConnection;

/**
 *
 * @author rafik
 */
public class Main {
    
    public static void main(String[] args) {
        NotePaquet note1 = new NotePaquet(1, 1, 4, " hhhhhhh");
        NotePaquetCrud npc = new NotePaquetCrud();
//       
        npc.addEntity(note1);
        System.out.println(npc.displayEntities().get(0).getCommentaire());
        
        NotePaquet note = new NotePaquet();
        note.setId_note_paquet(7);
        note.setId_paquet(1);
        note.setId_user(1);
        note.setNb_etoiles(6);
        note.setCommentaire("go Fuck your Ass");
       
        //npc.updateEntity(note);
        
        System.out.println(npc.displayEntities());
      // **********fct delete  
//        npc.deleteEntity(5);
//        System.out.println(npc.displayEntities());
        
        NotePaquetMetier npm = new NotePaquetMetier();
        
        String phrase = note.getCommentaire();
        
        String[] motsListe = {"Fuck", "Ass"};
        String nouvellePhrase = npm.controle(phrase, motsListe);
        System.out.println(nouvellePhrase);
        note.setCommentaire(nouvellePhrase);

        // **********fct update  

//        npc.updateEntity(note);
//        System.out.println(npc.displayEntities());
        
    //    EmailSender.send("bouchaoua.rafik@esprit.tn","sopaqgboqicykutq","rafik_boch@hotmail.com","Note Tradivac","Vous avez mis une note");
        
        
        
        
    }
    
    
}
