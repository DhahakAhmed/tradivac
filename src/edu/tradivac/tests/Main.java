/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.tests;



import edu.tradivac.entitiess.NoteService;
import edu.tradivac.services.NoteServiceCrud;
import edu.tradivac.utils.MyConnection;
import java.util.List;


/**
 *
 * @author wassou
 */
public class Main {
    
    public static void main(String[] args) {
         
        MyConnection mc = new MyConnection();
        NoteService note = new NoteService(1,2,3,"ghanmi" ,"hichem");
        NoteServiceCrud ncd =new NoteServiceCrud();
        ncd.addEntity(note);
        ncd.deleteEntity(note);
        ncd.updateteEntity(note);
        ncd.displayEntities();
        List<NoteService> testList = (List<NoteService>) ncd.displayEntities();
        if (!testList.isEmpty()) {
        System.out.println(testList.get(0).getId_note_servise());
        } else {
        System.out.println("La liste est vide.");
        }
        }
        
       
      
        
      
   
    
    
}
    

