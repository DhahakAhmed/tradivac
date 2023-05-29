/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tradivac.tests;

import edu.tradivac.entities.ServiceExcursion;
import edu.tradivac.services.ServiceExcursionCrud;
import edu.tradivac.utils.MySQLConnection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wassou
 */
public class Main {
    
    public static void main(String[] args) {
        ServiceExcursion ex1 = new ServiceExcursion("Excursion",1,"updated",100.00,"destination",new Date(02,02,2031),new Date(02,03,2031),true,true,"type");
        MySQLConnection mc = new MySQLConnection();
        //System.out.println(ex1.getDate_fin());
        ServiceExcursionCrud SEC = new ServiceExcursionCrud();
        //SEC.addEntity(ex1);
        SEC.updateEntity(ex1);
        List<ServiceExcursion>  listSEC = new ArrayList<>();
        listSEC = SEC.displayEntities();
        System.out.println(listSEC.size());
        //SEC.deleteEntity(listSEC.get(0));
        /*
        List<Personne> testList = pcd.displayEntities();
        System.out.println(testList.get(0).getNom());*/
    }
    
}
