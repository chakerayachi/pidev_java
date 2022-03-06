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
public class Ticket {
    
     private int id ;
    private int prix ;
    private int id_evenement ;

    // constructeur par default
    public Ticket(){}

    //constructeur parametere avec le champs id
    public Ticket(int id, int prix, int id_evenement) {
        this.id = id;
        this.prix = prix;
        this.id_evenement = id_evenement;
    }

    //constructeur parametere sans le champs id
    public Ticket(int prix, int id_evenement) {
        this.prix = prix;
        this.id_evenement = id_evenement;
    }

    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    // toString methode

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", prix=" + prix +
                ", id_evenement=" + id_evenement +
                '}';
    }
    
}
