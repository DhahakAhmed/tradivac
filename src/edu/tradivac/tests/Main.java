/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.tests;

import edu.tradivac.entities.Reclamation;
import edu.tradivac.utils.MySQLConnection;
import edu.tradivac.services.ReclamationCrud;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Meriem
 */
public class Main {

    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Reclamation r1 = new Reclamation(6, "esprit 12/07", "esprit", timestamp);
        System.err.println(r1.getDate());
       
        ReclamationCrud crd = new ReclamationCrud();

        //crd.addReclamation(r1);
        crd.deleteReclamation(13);
        // crd.displayReclamation();
        //crd.updateReclamation(r1);
       
        //crd.getReclamationsByUserId(123);
        //crd.getReclamationCountByUserId(123);
        //crd.getReclamationCountByUserIdAndDate(123);
       // crd.getReclamationCountByUserId(4);

    }

}
