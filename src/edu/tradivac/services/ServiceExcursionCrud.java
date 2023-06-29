/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.services;

import edu.tradivac.entities.ServiceExcursion;
import edu.tradivac.interfaces.IServiceCrud;
import edu.tradivac.utils.MySQLConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import static java.util.concurrent.TimeUnit.DAYS;

/**
 *
 * @author devhk
 */
public class ServiceExcursionCrud implements IServiceCrud<ServiceExcursion>{
    MySQLConnection myCnx = new MySQLConnection();

    private static long daysBetween(Date one, Date two) { long difference = (one.getTime()-two.getTime())/86400000; return Math.abs(difference); }
    @Override
    public void addEntity(ServiceExcursion t) {
       try {
            ServiceExcursionCrud SEC = new ServiceExcursionCrud();
            List<ServiceExcursion>  listSEC = new ArrayList<>();
            listSEC = SEC.displayEntities();
            boolean nomServiceUsed = false;
            int i = 0;
            while( i < listSEC.size()){
                if( listSEC.get(i).getNom_service().equals(t.getNom_service())){
                    nomServiceUsed = true;
                }
                i++;
            }
            if( t.getDate_debut().compareTo(t.getDate_fin()) > 0){
                System.out.println("Date de fin doit etre superieure a la date de debut.");
            }
            else if(t.getPrix() <= 0){
                System.out.println("Le prix doit etre superieur a 0.");
            }
            else if ( nomServiceUsed == true){
                System.out.println("Nom service utilisé, essayez un autre.");
            }
            else{
                int currSem = 0;
                if( t.getDate_debut().getMonth() <= 4){
                    currSem = 1;
                }
                else if( t.getDate_debut().getMonth() > 4 && t.getDate_debut().getMonth()<=8){
                    currSem = 2;
                }
                else if( t.getDate_debut().getMonth() > 8 && t.getDate_debut().getMonth()<=12){
                    currSem = 3;
                }
                String request = "INSERT INTO Service (nom_service,type,description,prix,destination,date_debut,date_fin,duree,excursion_plage,excursion_foret,excursion_type,id_user,nb_trimestre) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement pst = myCnx.getCnx().prepareStatement(request);
                pst.setString(1,t.getNom_service());
                pst.setString(2,t.getType());
                pst.setString(3,t.getDescription());
                pst.setDouble(4,t.getPrix());
                pst.setString(5,t.getDestination());
                pst.setDate(6,t.getDate_debut());
                pst.setDate(7,t.getDate_fin());
                pst.setInt(8, (int) daysBetween(t.getDate_debut(),t.getDate_fin()));
                pst.setBoolean(9,t.isExcursion_plage());
                pst.setBoolean(10,t.isExcursion_foret());
                pst.setString(11,t.getExcursion_type());
                pst.setInt(12,t.getId_user());
                pst.setInt(13,currSem);
                pst.executeUpdate();
                System.out.println("Service Excursion ajouté!");
            }
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
                    p.setNom_service(Rs.getString("nom_service"));
                    p.setType(Rs.getString("type"));
                    p.setDescription(Rs.getString("description")); // CHOOSE NAME COLUMN
                    p.setDestination(Rs.getString("destination"));
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
        ServiceExcursionCrud SEC = new ServiceExcursionCrud();
            List<ServiceExcursion>  listSEC = new ArrayList<>();
            listSEC = SEC.displayEntities();
            boolean nomServiceUsed = false;
            int i = 0;
            while( i < listSEC.size()){
                if( listSEC.get(i).getNom_service().equals(t.getNom_service()) && t.getId_service() != listSEC.get(i).getId_service()){
                    nomServiceUsed = true;
                }
                i++;
            }
            if( t.getDate_debut().compareTo(t.getDate_fin()) > 0){
                System.out.println("Date de fin doit etre superieure a la date de debut.");
            }
            else if(t.getPrix() <= 0){
                System.out.println("Le prix doit etre superieur a 0.");
            }
            else if ( nomServiceUsed == true ){
                System.out.println("Nom service utilisé, essayez un autre.");
            }
            else{
                try {
                    String request = "UPDATE Service SET  nom_service = ?, description= ?, prix =?, destination =?, date_debut=? , date_fin=?, excursion_plage=?, excursion_foret=?, excursion_type=? "
                            + "WHERE id_service = ?;";

                    PreparedStatement pst = myCnx.getCnx().prepareStatement(request);
                    pst.setString(1,t.getNom_service());
                    pst.setString(2,t.getDescription());
                    pst.setDouble(3,t.getPrix());
                    pst.setString(4,t.getDestination());
                    pst.setDate(5,t.getDate_debut());
                    pst.setDate(6,t.getDate_fin());
                    pst.setBoolean(7,t.isExcursion_plage());
                    pst.setBoolean(8,t.isExcursion_foret());
                    pst.setString(9,t.getExcursion_type());
                    pst.setInt(10,t.getId_service());
                    pst.executeUpdate();
                    System.out.println("Service Excursion modifié!");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
    }

}
