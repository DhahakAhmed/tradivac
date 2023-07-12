/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.services;

import edu.tradivac.entities.NotePaquet;
import edu.tradivac.interfaces.INoteCrud;
import edu.tradivac.utils.MySQLConnection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author devhk
 */
public class NotePaquetCrud implements INoteCrud<NotePaquet> {
    

    @Override
      public void addEntity(NotePaquet t) {
        try {
            String request ="INSERT INTO notes_paquets (id_paquet,id_user,nb_etoiles,commentaire) "
                    + "VALUES('"+t.getId_paquet()+"','"+t.getId_user()+"','"+t.getNb_etoiles()+"','"+t.getCommentaire()+"');";
            
            PreparedStatement pst = MySQLConnection.getInstance().getCnx().prepareStatement(request);
            pst.executeUpdate(request);
            System.out.println("Note ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  
    }

    @Override
    public List<NotePaquet> displayEntities() {
        List<NotePaquet>  myList = new ArrayList<>();
        
        String request = "SELECT * FROM notes_paquets";
             try {
            PreparedStatement pst = MySQLConnection.getInstance().getCnx().prepareStatement(request);
             ResultSet rs = pst.executeQuery(request);
             while (rs.next()){
                 NotePaquet np = new NotePaquet();
                 np.setId_note_paquet(1);
                 np.setId_paquet(rs.getInt(2));
                 np.setId_user(rs.getInt(3));
                 np.setNb_etoiles(rs.getInt(4));
                 np.setCommentaire(rs.getString(5));
                 myList.add(np);
             }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void updateEntity(NotePaquet t) {
         String requet = "UPDATE `notes_paquets` SET`id_paquet`='"+t.getId_paquet()+"',`id_user`='"+t.getId_user()+"',`nb_etoiles`='"+t.getNb_etoiles()+"',`commentaire`='"+t.getCommentaire()+"' WHERE `id_note_paquet`='"+t.getId_note_paquet()+"'";
        try (PreparedStatement pst = MySQLConnection.getInstance().getCnx().prepareStatement(requet)) {

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("note modifié");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteEntity(int id) {
        String requet = "DELETE FROM notes_paquets WHERE id_note_paquet = " + id;
        try (PreparedStatement pst = MySQLConnection.getInstance().getCnx().prepareStatement(requet)) {
            pst.executeUpdate(requet);

            System.out.println("Note supprimé");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
