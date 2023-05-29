/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.services;

import edu.tradivac.entities.ServiceExcursion;
import edu.tradivac.interfaces.IServiceCrud;
import edu.tradivac.utils.MySQLConnection;
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
public class ServiceExcursionCrud implements IServiceCrud<ServiceExcursion>{
    MySQLConnection myCnx = new MySQLConnection();
    @Override
    public void addEntity(ServiceExcursion t) {
       try {
            String request = "INSERT INTO Service (type,description,prix,destination,date_debut,date_fin,excursion_plage,excursion_foret,excursion_type,id_user) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement pst = myCnx.getCnx().prepareStatement(request);
            pst.setString(1,t.getType());
            pst.setString(2,t.getDescription());
            pst.setDouble(3,t.getPrix());
            pst.setString(4,t.getDestination());
            pst.setDate(5,t.getDate_debut());
            pst.setDate(6,t.getDate_fin());
            pst.setBoolean(7,t.isExcursion_plage());
            pst.setBoolean(8,t.isExcursion_foret());
            pst.setString(9,t.getExcursion_type());
            pst.setInt(10,t.getId_user());
            pst.executeUpdate();
            System.out.println("Service Excursion ajouté!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<ServiceExcursion> displayEntities() {
       List<ServiceExcursion>  myList = new ArrayList<>();
        try {
            String request = "SELECT * FROM Service";
            Statement st = myCnx.getCnx().createStatement();
            ResultSet Rs = st.executeQuery(request);
            
            while(Rs.next()){
                ServiceExcursion p = new ServiceExcursion();
                if(Rs.getString("type").equalsIgnoreCase("excursion")){
                    p.setId_service(Rs.getInt(1)); // Choose nb column
                    p.setType(Rs.getString("type"));
                    p.setDescription(Rs.getString("description")); // CHOOSE NAME COLUMN
                    p.setPrix(Rs.getDouble("prix"));
                    p.setDate_debut(Rs.getDate("date_debut"));
                    p.setDate_fin(Rs.getDate("date_fin"));
                    p.setExcursion_type(Rs.getString("excursion_type"));
                    p.setExcursion_foret(Rs.getBoolean("excursion_foret"));
                    p.setExcursion_plage(Rs.getBoolean("excursion_plage"));
                    myList.add(p);
                }
                
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return myList;
    }

    @Override
    public void deleteEntity(ServiceExcursion t) {
       try {
            String request = "DELETE FROM Service WHERE id_service=? ";
            
            PreparedStatement pst = myCnx.getCnx().prepareStatement(request);
            pst.setInt(1,t.getId_service());
            pst.executeUpdate();
            System.out.println("Service Excursion supprimé!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateEntity(ServiceExcursion t) {
       try {
            String request = "UPDATE Service SET  description= ?, prix =?, destination =?, date_debut=? , date_fin=?, excursion_plage=?, excursion_foret=?, excursion_type=? "
                    + "WHERE id_service = ?;";
            
            PreparedStatement pst = myCnx.getCnx().prepareStatement(request);
            pst.setString(1,t.getDescription());
            pst.setDouble(2,t.getPrix());
            pst.setString(3,t.getDestination());
            pst.setDate(4,t.getDate_debut());
            pst.setDate(5,t.getDate_fin());
            pst.setBoolean(6,t.isExcursion_plage());
            pst.setBoolean(7,t.isExcursion_foret());
            pst.setString(8,t.getExcursion_type());
            pst.setInt(9,t.getId_service());
            pst.executeUpdate();
            System.out.println("Service Excursion modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
