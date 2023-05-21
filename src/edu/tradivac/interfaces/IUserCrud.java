/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.tradivac.interfaces;

import edu.tradivac.entities.User;
import java.util.List;

/**
 *
 * @author wassou
 * @param <T>
 */
public interface IUserCrud<T extends User> {
    public T addUser(T t);
    public void deleteUser(int id);
    public T updateUser(T t);
    public T getUserById(int id); 
    public T getUser(T t); 
    public List<T> getAllUsers(); 
}
