/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.services;

import edu.tradivac.entities.Reclamation;
import edu.tradivac.interfaces.IReclamationCrud;
import edu.tradivac.utils.MySQLConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
/**
 *
 * @author devhk
 */
public class ReclamationCrud implements IReclamationCrud<Reclamation> {
 public static final Connection myCnx = MySQLConnection.getInstance().getConnection();
 
    @Override
    public void addReclamation(Reclamation r) {
             try {
                 
            String request = "INSERT INTO reclamation (id_touriste, objet, description, date) "
                    + "VALUES('"+r.getId_touriste()+"','"+r.getObjet()+"','"+r.getDescription()+"','"+r.getDate()+"');";
            
            Statement st = myCnx.createStatement();
            st.executeUpdate(request);
            System.out.println("Reclamation ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 
 @Override
    public void deleteReclamation(int id) {
        String requet = "DELETE FROM `reclamation` WHERE `id_reclamation`= " + id;
        try (PreparedStatement pst =  myCnx.prepareStatement(requet)) {
            pst.executeUpdate(requet);

            pst.executeUpdate();

            System.out.println("condidature supprimé");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
 @Override
    public void updateReclamation(Reclamation r) {
        String requet = "UPDATE `reclamation` SET `id_touriste`='"+r.getId_touriste()+"',`objet`='"+r.getObjet()+
                "',`description`='"+r.getDescription()+"',`date`='"+r.getDate()+"' WHERE `id_reclamation` = '"+r.getId_reclamation()+"';";
        try (PreparedStatement pst = myCnx.prepareStatement(requet)) {
            pst.setString(1, r.getObjet());
            pst.setString(2, r.getDescription());
            pst.setTimestamp(3, r.getDate());


            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Condidature modifié");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public List<Reclamation> displayReclamation() {
        List<Reclamation>  myList = new ArrayList<>();
        try {
            String request = "SELECT * FROM reclamation";
            Statement st = myCnx.createStatement();
            ResultSet Rs = st.executeQuery(request);
            
            while(Rs.next()){
                Reclamation p = new Reclamation();
                p.setId_reclamation(Rs.getInt(1)); // Choose nb column
                p.setObjet(Rs.getString("objet")); // CHOOSE NAME COLUMN
                p.setDescription(Rs.getString("description"));
                p.setDate(Rs.getTimestamp("date"));
                p.setId_touriste(Rs.getInt("id_touriste"));
                myList.add(p);
                
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return myList;
       
    }
    
        public List<Reclamation> getReclamationsByUserId(int userId) {
        List<Reclamation> reclamations = new ArrayList<>();
        try {
            String request = "SELECT * FROM reclamation WHERE id_touriste = ?";
            PreparedStatement ps = myCnx.prepareStatement(request);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reclamation reclamation = new Reclamation();
                reclamation.setId_reclamation(rs.getInt("id_reclamation"));
                reclamation.setObjet(rs.getString("objet"));
                reclamation.setDescription(rs.getString("description"));
                reclamation.setDate(rs.getTimestamp("date"));
                  reclamation.setId_touriste(rs.getInt("id_touriste"));
                reclamations.add(reclamation);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    System.out.println(reclamations);
        return reclamations;
    }
        //****Calculer nbr des reclamations par utilisateur
public int getReclamationCountByUserId(int userId) {
    int count = 0;
    try {
        String request = "SELECT COUNT(*) FROM reclamation WHERE id_touriste = ?";
        PreparedStatement ps = myCnx.prepareStatement(request);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
               System.out.println(count);
 
    return count;
}


public int getReclamationCountByUserIdAndDate(int userId) {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    int count = 0;
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(timestamp);
        System.out.println(dateString);

        String request = "SELECT COUNT(*) FROM reclamation WHERE id_touriste = ? AND created_at = ?";
        PreparedStatement ps = myCnx.prepareStatement(request);
        ps.setInt(1, userId);
        ps.setString(2, dateString);
        
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    System.out.println(count);

    return count;
}



    
    

}
     

