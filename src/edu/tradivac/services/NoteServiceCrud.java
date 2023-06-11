/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.services;

import edu.tradivac.entitiess.NoteService;
import edu.tradivac.interfaces.INoteCrud;
import edu.tradivac.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
  
 * @author devhk
 */
public  class NoteServiceCrud implements INoteCrud<NoteService>{
    MyConnection myCnx = new MyConnection();

    @Override
     public void addEntity(NoteService t) {
        try {
            String request = "INSERT INTO note_service(id_service ,id_user ,nb_etoiles ,commentaire ,username_touriste) "
                    + "VALUES(?,?,?,?,?)";
            
            PreparedStatement pst = myCnx.getCnx().prepareStatement(request);
            pst.setInt(1,t.getId_Service());
            pst.setInt(2,t.getId_user());
             pst.setInt(3,t.getNb_etoile());
              pst.setString(4,t.getCommentiare());
               pst.setString(5,t.getUsername_touriste());
            pst.executeUpdate();
            System.out.println("NoteService ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateteEntity(NoteService t) {
         try {
            String request = " UPDATE note_service SET id_Service=?, id_user=?, nb_etoiles=?, commentaire=?, username_touriste=? WHERE id_note_service=?";

          PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(request);
            pst.setInt(1,t.getId_Service());
             pst.setInt(2 ,t.getId_user());
              pst.setInt(3 ,t.getNb_etoile());
               pst.setString(4 ,t.getCommentiare());
                pst.setString(5,t.getUsername_touriste());
                
                pst.setInt(6, t.getId_note_servise());
                
            pst.executeUpdate();
            System.out.println("the note_service has been update");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
       
    }
 @Override
           public void deleteEntity( NoteService t) {
           try {
            String request = " delete from  note_service  where   id_Service= ?";
           
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(request);
            
            pst.setInt(1,t.getId_Service());
            pst.executeUpdate();
            
            System.out.println("the note_service has been  delete");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
    }
   
     
        
           }

    @Override
    public List<NoteService> displayEntities() {
                 
        List<NoteService>  myList = new ArrayList<>();
        
        String request = "SELECT * FROM note_service";
             try {
               PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(request);
             ResultSet rs = pst.executeQuery(request);
             while (rs.next()){
                 NoteService ns = new NoteService();
                 ns.setId_note_servise(0);
                 ns.setId_Service(rs.getInt(1));
                 ns.setId_user(rs.getInt(2));
                 ns.setNb_etoile(rs.getInt(3));
                 ns.setCommentiare(rs.getString(4));
                ns.setUsername_touriste( rs.getString(5));
                 myList.add(ns);
             }
                 System.out.println("the note_service has been display");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }


}


  
        
    
        
        
    
    






 


   
    


    


  
   



    
    

