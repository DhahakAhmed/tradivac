/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.services;

import edu.tradivac.entities.Reclamation;
import edu.tradivac.interfaces.IReclamationCrud;
import edu.tradivac.utils.MySQLConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author devhk
 */
public class ReclamationCrud implements IReclamationCrud<Reclamation> {
 MySQLConnection myCnx = new MySQLConnection();
    @Override
    public void addEntity(Reclamation r) {
             try {
                 
            String request = "INSERT INTO reclamation (id_touriste, objet, description, date) "
                    + "VALUES('"+r.getId_touriste()+"','"+r.getObjet()+"','"+r.getDescription()+"','"+r.getDate()+"');";
            
            Statement st = myCnx.getCnx().createStatement();
            st.executeUpdate(request);
            System.out.println("Reclamation ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 
    public void deleteEntity(int id) {
        String requet = "DELETE FROM `reclamation` WHERE `id_reclamation`= " + id;
        try (PreparedStatement pst =  myCnx.getCnx().prepareStatement(requet)) {
            pst.executeUpdate(requet);

            pst.executeUpdate();

            System.out.println("condidature supprimé");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void updateEntity(Reclamation r) {
        String requet = "UPDATE `reclamation` SET `id_touriste`='"+r.getId_touriste()+"',`objet`='"+r.getObjet()+
                "',`description`='"+r.getDescription()+"',`date`='"+r.getDate()+"' WHERE `id_reclamation` = '"+r.getId_reclamation()+"';";
        try (PreparedStatement pst = myCnx.getCnx().prepareStatement(requet)) {
            pst.setString(1, r.getObjet());
            pst.setString(2, r.getDescription());
            pst.setTimestamp(2, r.getDate());


            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Condidature modifié");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public List<Reclamation> displayEntities() {
        List<Reclamation>  myList = new ArrayList<>();
        try {
            String request = "SELECT * FROM reclamation";
            Statement st = myCnx.getCnx().createStatement();
            ResultSet Rs = st.executeQuery(request);
            
            while(Rs.next()){
                Reclamation p = new Reclamation();
                p.setId_reclamation(Rs.getInt(1)); // Choose nb column
                p.setObjet(Rs.getString("objet de reclamation")); // CHOOSE NAME COLUMN
                p.setDescription(Rs.getString("description"));
                p.setDate(Rs.getTimestamp("date"));
                myList.add(p);
                
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return myList;
       
    }    }
     

