/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author Wael
 */
public interface Ihebergement<T> {
     public int create(T t);
    public void modify(T t);
    public void delete(int id);
    public List<T> afficher();
    
}
