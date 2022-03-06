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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.ServiceChambreIPM;

/**
 * FXML Controller class
 *
 * @author alaaz
 */
public class AjouterRéservationChambreController implements Initializable {

    @FXML
    private DatePicker date_debut_field;
    @FXML
    private DatePicker date_fin_field;
    @FXML
    private ChoiceBox<KeyValuePair> select_field;
    @FXML
    private ChoiceBox<Integer> disponibiliter_field;
    @FXML
    private Label total_fied;
    @FXML
    private Button confirmer_button;
    ServiceChambreIPM service_chambre=new ServiceChambreIPM(); 
    Float total=0f;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        total_fied.setText("");
        date_debut_field.setDayCellFactory(callB);
        date_fin_field.setDayCellFactory(call_date_debut); 
        date_fin_field.setDisable(true);
         service_chambre.get_chambre_by_hotel_id(4).forEach((p)->{
             select_field .getItems().add(new KeyValuePair(p.getId(),p.getType(),p.getPrix())); 
        });
        select_field.setOnAction(event -> {
            int ds=service_chambre.get_disponibiliter_id(select_field.getValue().getKey()); 
            disponibiliter_field.getSelectionModel().clearSelection();
            disponibiliter_field.getItems().clear();
            disponibiliter_field.setValue(1);
            for (int i=1; i<=ds;i++) {
                disponibiliter_field.getItems().add(i);
            }
        });
        disponibiliter_field.setOnAction(event -> {
           float price= select_field.getValue().getData(); 
           int ds=disponibiliter_field.getValue();
           total=ds*price; 
            total_fied.setText(total.toString());
            System.out.println(date_debut_field.getValue());
        });
        date_debut_field.setOnAction(event -> { 
           if(date_debut_field.getValue()!=null)
            date_fin_field.setDisable(false);
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
            int id_chambre=select_field.getValue().getKey();
            int nbr_des=disponibiliter_field.getValue(); 
            Reservation reservation=new Reservation(date_deb,date_fn,total,0,0,0,id_chambre,32,0,"hotel"); 
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
      public boolean check_from_filled(){
        boolean check=false;
        System.out.println("date_debut_field"+date_debut_field.getValue());
        System.out.println("date_fin_field"+date_fin_field.getValue());
        System.out.println("disponibiliter_field"+disponibiliter_field.getValue());
        System.out.println("select_field"+select_field.getValue());
        if(date_debut_field.getValue()!=null && date_fin_field.getValue()!=null && disponibiliter_field.getValue()!=null && select_field.getValue()!=null ){
            check=true;
        }
        return check;
    }
    
}
