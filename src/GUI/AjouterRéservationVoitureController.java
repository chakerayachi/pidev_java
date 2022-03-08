/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reservation;
import entities.Utilisateur;
import entities.Voiture;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.ServicesVoitureIMP;

/**
 * FXML Controller class
 *
 * @author alaaz
 */
public class AjouterRéservationVoitureController implements Initializable {

    @FXML
    private DatePicker date_debut_field;
    @FXML
    private DatePicker date_fin_field;
    @FXML
    private Label total_fied;
    @FXML
    private Button confirmer_button;
    @FXML
    private Label car_name;
    
    
    //to change
    static  Voiture voiture;
    float total=0f; 
    float prix=0f;
    
    
   
    ServicesVoitureIMP service_voiture=new ServicesVoitureIMP(); 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        car_name.setText(voiture.getModel());
       total_fied.setText("");
       date_debut_field.setDayCellFactory(callB);
        date_fin_field.setDayCellFactory(call_date_debut);
        date_fin_field.setDisable(true);
        service_voiture.get_voiture_by_id(voiture.getId()).forEach((p)->{  
             prix=p.getPrix();
             total=p.getPrix();
             total_fied.setText(p.getPrix() +"DT");
        });
        date_debut_field.setOnAction(event -> { 
           if(date_debut_field.getValue()!=null)
                date_fin_field.setDisable(false);
        });
        date_fin_field.setOnAction(event -> { 
            if(date_debut_field.getValue()!=null){
                   total=calculate_total(prix);
                   total_fied.setText(total+" $");
            }            
        });
    }
    Callback<DatePicker, DateCell> callB = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                        LocalDate today = LocalDate.now();
                        setDisable(empty || item.compareTo(today) < 0);
                    }

                };
            }
    };
    Callback<DatePicker, DateCell> call_date_debut = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                        LocalDate today = (LocalDate)date_debut_field.getValue();
                        setDisable(empty || item.compareTo(today) < 0);
                    }

                };
            }

    };

    @FXML
    private void create_reservation(ActionEvent event) {
          if(check_from_filled()){  
            System.out.println("Im here");
            Date date_deb = Date.valueOf(date_debut_field.getValue()); 
            Date date_fn = Date.valueOf(date_fin_field.getValue());  
            Reservation reservation=new Reservation(date_deb,date_fn,total,voiture.getId(),0,0,0,Utilisateur.user_connecter.getId(),0,"voiture");
             FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../GUI/AjoutPaiement.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AjouterRéservationVoitureController.class.getName()).log(Level.SEVERE, null, ex);
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
     public boolean check_from_filled(){
        boolean check=false;
        System.out.println("date_debut_field"+date_debut_field.getValue());
        System.out.println("date_fin_field"+date_fin_field.getValue());
        if(date_debut_field.getValue()!=null && date_fin_field.getValue()!=null){
            check=true;
        }
        return check;
    }
     public  float calculate_total(float price){ 
         Date date_deb = Date.valueOf(date_debut_field.getValue()); 
         Date date_fn = Date.valueOf(date_fin_field.getValue());
         long diff = date_fn.getTime() - date_deb.getTime();
	 int res = (int) (diff / (1000*60*60*24))+1;
         return (res*price);
         
    }
     
    
}
