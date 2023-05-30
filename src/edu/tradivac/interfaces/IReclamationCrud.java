/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.tradivac.interfaces;

import java.util.List;

/**
 *
 * @author meriem
 * @param <T>
 */
public interface IReclamationCrud<T> {
         public void addEntity(T t);
    
    public List<T> displayEntities();
    public void updateEntity(T t);
    public void deleteEntity(int id);


    
}
