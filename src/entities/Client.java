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
public class Client extends Utilisateur {
    
    private int id ;

    public Client(){}
    
    public Client(int id) {
        this.id = id;
    }

    public Client(int id, String login, String password, String nom, String prenom, String email, int num_tel, int cin, String adresse, String role, String image, String description, int id1) {
        super(id, login, password, nom, prenom, email, num_tel, cin, adresse, role, image, description);
        this.id = id1;
    }

    public Client(String login, String password, String nom, String prenom, String email, int num_tel, int cin, String adresse, String role, String image, String description, int id) {
        super(login, password, nom, prenom, email, num_tel, cin, adresse, role, image, description);
        this.id = id;
    }

    public Client(String chaker, String chaker123, String chaker0, String ayachi, String chakerayachiesprittn, int i, int i0, String tunis_, String admin, Object object, String ee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return "Client{" +
                "id=" + id +
                '}';
    }
    
    
}
