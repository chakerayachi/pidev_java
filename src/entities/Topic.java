/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Firas CHKOUNDALI
 */
public class Topic {
    private int idtopic;
    private String titretopic;
    private String description;
    private String date;
    private int accepter;
    private int nbsujet;
    private int iduser;

    public Topic() {
    }

    public Topic(String titretopic, String description, String date, int iduser) {
        this.titretopic = titretopic;
        this.description = description;
        this.date = date;
        this.iduser = iduser;
    }

    public Topic(String titretopic, String description, String date, int accepter, int nbcom, int iduser) {
        this.titretopic = titretopic;
        this.description = description;
        this.date = date;
        this.accepter = accepter;
        this.nbsujet = nbcom;
        this.iduser = iduser;
    }

    public Topic(int idtopic, String titretopic, String description, String date, int accepter, int nbcom, int iduser) {
        this.idtopic = idtopic;
        this.titretopic = titretopic;
        this.description = description;
        this.date = date;
        this.accepter = accepter;
        this.nbsujet = nbcom;
        this.iduser = iduser;
    }

    public int getIdtopic() {
        return idtopic;
    }

    public String getTitretopic() {
        return titretopic;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public int isAccepter() {
        return accepter;
    }

    public int getNbsujet() {
        return nbsujet;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIdtopic(int idtopic) {
        this.idtopic = idtopic;
    }

    public void setTitretopic(String titretopic) {
        this.titretopic = titretopic;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAccepter(int accepter) {
        this.accepter = accepter;
    }

    public void setNbsujet(int nbsujet) {
        this.nbsujet = nbsujet;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Override
    public String toString() {
        return "Topic{" + "idtopic=" + idtopic + ", titretopic=" + titretopic + ", description=" + description + ", date=" + date + ", accepter=" + accepter + ", nbsujet=" + nbsujet + ", iduser=" + iduser + '}';
    }
    
    
}
