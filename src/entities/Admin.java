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
   

    public Admin() {
        super();
    }

    public Admin(int id, String login, String password, String nom, String prenom, String email, int num_tel, int cin, String adresse, String role, String image, String description, String etat , String account_date) {
        super(id, login, password, nom, prenom, email, num_tel, cin, adresse, role, image, description,etat ,account_date);
    }

    public Admin(String login, String password, String nom, String prenom, String email, int num_tel, int cin, String adresse, String role, String image, String description,String etat , String account_date) {
        super(login, password, nom, prenom, email, num_tel, cin, adresse, role, image, description,etat, account_date);
    }
    
  


    @Override
    public String toString() {
        return "Admin{" +
               super.toString() +
                '}';
    }
    
    
    
}
