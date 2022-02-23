/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Hani
 */
public class Reclamation {
    int id ;
    int user_id ;
    String discreption ;

    public Reclamation() {
    }

    public Reclamation(int id, int user_id, String discreption) {
        this.id = id;
        this.user_id = user_id;
        this.discreption = discreption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDiscreption() {
        return discreption;
    }

    public void setDiscreption(String discreption) {
        this.discreption = discreption;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", user_id=" + user_id + ", discreption=" + discreption + '}';
    }
    
    
}
