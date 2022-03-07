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
public class Images {
   private int img_id;	
   private int id_voiture;
          	
           private String  img_taille;	
                  	
                          private String  img_blob;

    public Images() {
    }

    public Images(int img_id, String img_blob,int id_voiture) {
        this.img_id = img_id;
        this.img_blob = img_blob;
        this.id_voiture = id_voiture;
    }

    public int getImg_id() {
        return img_id;
    }

    public int getId_voiture() {
        return id_voiture;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    

   

   

    

    public String getImg_blob() {
        return img_blob;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

   

   

   

    public void setImg_blob(String img_blob) {
        this.img_blob = img_blob;
    }

    @Override
    public String toString() {
        return "Image{" + "img_id=" + img_id + ", img_taille=" + img_taille + ", img_blob=" + img_blob + '}';
    }

   
    
                          
    
}
