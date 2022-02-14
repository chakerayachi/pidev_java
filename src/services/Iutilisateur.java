/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author chaker
 */
public interface Iutilisateur<T> {
    
    public void ajoutUtilisateur(T t);
    public void modifierUtilisateur(T t);
    public void supprimerUtilisateur(int id);
    public List<T> afficherUtilisateur();
    
    
    
}
