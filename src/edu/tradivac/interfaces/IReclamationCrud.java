/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.tradivac.interfaces;

import edu.tradivac.entities.Reclamation;
import java.util.List;

/**
 *
 * @author meriem
 * @param <T>
 */
public interface IReclamationCrud<T extends Reclamation> {

    public void addReclamation(T t);

    public List<T> displayReclamation();

    public void updateReclamation(T t);

    public void deleteReclamation(int id);

}
