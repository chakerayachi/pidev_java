/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.KeyValuePair;
import entities.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ServiceChambreIPM;
import services.ServiceTicketIMP;

/**
 * FXML Controller class
 *
 * @author alaaz
 */
public class AjouterRéservationTicketController implements Initializable {

    @FXML
    private ChoiceBox<KeyValuePair> select_field;
    @FXML
    private Label total_fied;
    @FXML
    private Button confirmer_button;
    ServiceTicketIMP service_ticket=new ServiceTicketIMP(); 
    Float total=0f;
    Integer id_ticket=1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        total_fied.setText("");
        service_ticket.get_ticket_by_id(4).forEach((p)->{
             select_field .getItems().add(new KeyValuePair(p.getId(),p.getType(),p.getPrix())); 
        });
        
        select_field.setOnAction(event -> {
          total_fied.setText(select_field.getValue().getData()+" DT");
        });
    }

 public boolean check_from_filled(){
        boolean check=false;
   
        System.out.println("select_field"+select_field.getValue());
        if(select_field.getValue()!=null ){
            check=true;
        }
        return check;
    }    

    @FXML
    private void create_reservation(ActionEvent event) {
          if(check_from_filled()){  
            System.out.println("Im here");
            int id_ticket=select_field.getValue().getKey();
            Reservation reservation=new Reservation(); 
            reservation.setId_ticket(id_ticket);
            reservation.setType("ticket");
            reservation.setId_user(32);
            reservation.setMontant_a_payer(total);
             FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../GUI/AjoutPaiement.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AjouterRéservationChambreController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            AjoutPaiementController py = loader.getController();
                            py.set_reservation_data(reservation);
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
            
        }else{ 
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur Validation Formulaire");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir le formulaire");
            alert.showAndWait();
        } 
    }
    
}
