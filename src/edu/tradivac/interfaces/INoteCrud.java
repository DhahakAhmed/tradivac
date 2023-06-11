/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.tradivac.interfaces;

import java.util.List;

public interface INoteCrud <T >{
     public void addEntity (T t);
     public void updateteEntity(T t);
      public void deleteEntity(T t);
     public  List<T> displayEntities();

   

    
   
    
    
}






