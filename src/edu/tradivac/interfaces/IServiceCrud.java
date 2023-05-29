/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.tradivac.interfaces;

import java.util.List;

/**
 *
 * @author devhk
 * @param <T>
 */
public interface IServiceCrud<T> {
    
    public void addEntity(T t);
    
    public void deleteEntity (T t);
    
    public void updateEntity ( T t);
    
    public List<T> displayEntities();
    
    //public void deleteEntity(T t);
}
