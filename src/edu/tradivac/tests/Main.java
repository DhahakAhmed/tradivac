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
import edu.tradivac.utils.mailAPI;
import edu.tradivac.utils.smsAPI;


/**
 *
 * @author wassou
 */
public class Main {
    
    public static void main(String[] args) {
        ServiceExcursion ex1 = new ServiceExcursion(12,1,"Updated","Excursion","newest",50.00,"destination",new Date(02,02,2031),new Date(02,01,2031),true,true,"type");
        MySQLConnection mc = new MySQLConnection();
        //System.out.println(ex1.getDate_fin());
        ServiceExcursionCrud SEC = new ServiceExcursionCrud();
        
        //SEC.addEntity(ex1);
        //SEC.updateEntity(ex1);
        //SEC.deleteEntity(ex1);
         
        List<ServiceExcursion>  listSEC = new ArrayList<>();
        listSEC = SEC.displayEntities();
        System.out.println(listSEC.get(0).getDestination());
        //System.out.println(smsAPI.sendSMS());
        mailAPI.send("service.tradivac@gmail.com","bdrpqdsliuycjxww","ahmeddhk2001@gmail.com","hello javatpoint","How r u?");  
         //mailAPI.send("tradivac@ahmeddhahak.com","TradivacMdp177@","devhkahmed@gmail.com","hello javatpoint","How r u?");  
            
    //sendEmailService.send("service.tradivac@gmail.com","tradivacmdp","devhkahmed@gmail.com","hello javatpoint","How r u?");  
    }
    
}
