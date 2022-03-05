/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Hani
 */
public class Reclamation {
   private int id ;
   private int user_id ;
   private String type;
   private String discreption ;
   private String date;

    public Reclamation() {
    }

    public Reclamation(int id, int user_id,String type, String discreption) {
        this.id = id;
        this.user_id = user_id;
        this.discreption = discreption;
    }

    public Reclamation(int id, int user_id, String type, String discreption, String date) {
        this.id = id;
        this.user_id = user_id;
        this.type = type;
        this.discreption = discreption;
        this.date = date;
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

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", user_id=" + user_id + ", discreption=" + discreption + '}';
    }
    
    
}
