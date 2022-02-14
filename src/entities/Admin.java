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
public class Admin extends Utilisateur{
    private int id ;

    public Admin() {
    }

    public Admin(int id, String login, String password, String nom, String prenom, String email, int num_tel, int cin, String adresse, String role, String image, String description, String etat) {
        super(id, login, password, nom, prenom, email, num_tel, cin, adresse, role, image, description,etat);
    }

    public Admin(String login, String password, String nom, String prenom, String email, int num_tel, int cin, String adresse, String role, String image, String description,String etat) {
        super(login, password, nom, prenom, email, num_tel, cin, adresse, role, image, description,etat);
    }
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                '}';
    }
    
    
    
}
