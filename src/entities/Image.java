/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author chaker
 */
public class Image {
    private int id ;
    private String url;
    private int id_chambre ;
    private int id_evenement;
    private int id_hotel;
    private int id_maison;
    private int id_voiture;

    public Image() {
    }

    public Image(int id, String url, int id_chambre, int id_evenement, int id_hotel, int id_maison, int id_voiture) {
        this.id = id;
        this.url = url;
        this.id_chambre = id_chambre;
        this.id_evenement = id_evenement;
        this.id_hotel = id_hotel;
        this.id_maison = id_maison;
        this.id_voiture = id_voiture;
    }

    public Image(String url, int id_chambre, int id_evenement, int id_hotel, int id_maison, int id_voiture) {
        this.url = url;
        this.id_chambre = id_chambre;
        this.id_evenement = id_evenement;
        this.id_hotel = id_hotel;
        this.id_maison = id_maison;
        this.id_voiture = id_voiture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId_chambre() {
        return id_chambre;
    }

    public void setId_chambre(int id_chambre) {
        this.id_chambre = id_chambre;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public int getId_maison() {
        return id_maison;
    }

    public void setId_maison(int id_maison) {
        this.id_maison = id_maison;
    }

    public int getId_voiture() {
        return id_voiture;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    @Override
    public String toString() {
        return "Image{" + "id=" + id + ", url=" + url + ", id_chambre=" + id_chambre + ", id_evenement=" + id_evenement + ", id_hotel=" + id_hotel + ", id_maison=" + id_maison + ", id_voiture=" + id_voiture + '}';
    }
    
    
    
    
    
    
    
}
