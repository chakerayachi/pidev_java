/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reservation;
import entities.Utilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import services.ServiceRéservationIMP;

/**
 * FXML Controller class
 *
 * @author alaaz
 */
public class DétailsRéservationTicketController implements Initializable {

    @FXML
    private Label label_nom;
    @FXML
    private Label label_localisation;
    @FXML
    private Label label_num_tel;
    @FXML
    private Label label_type;
    @FXML
    private Label label_quantite;
    @FXML
    private Label label_total;
    @FXML
    private Label label_debut;
    @FXML
    private Label label_fin;
    @FXML
    private Label label_crée;
    @FXML
    private Label label_paye;
    @FXML
    private Label label_client;
    @FXML
    private Label label_email;
    Reservation reservation=null; 
    ServiceRéservationIMP service_reservation=new ServiceRéservationIMP();  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void exporter_pdf(ActionEvent event) {
    }
    
     public void set_data(int reservation_id, String type_reservation){
         Utilisateur user=service_reservation.get_user_by_reservation_id(reservation_id); 
        System.out.println("user data "+user.getNom());
        label_client.setText(user.getNom().toString()+" "+user.getPrenom().toString());
        label_email.setText(user.getEmail());
        service_reservation.get_reservation_details_by_id(reservation_id,type_reservation).forEach((p)->{
             label_nom.setText(p.get(4).toString()); 
             label_localisation.setText(p.get(5).toString());
             label_num_tel.setText("(+216) "+p.get(2).toString()); 
             label_quantite.setText("2"); 
             label_total.setText(p.get(8).toString()+" $"); 
             label_debut.setText(p.get(3).toString());
             label_fin.setText(p.get(6).toString()+" h");
             label_crée.setText(p.get(1).toString());
             label_paye.setText(p.get(8).toString()+" $");

        });
         
            
    } 
}
