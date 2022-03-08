/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Maison;
import entities.Reservation;
import entities.Utilisateur;
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
import services.ServiceMaisonIPM;

/**
 * FXML Controller class
 *
 * @author alaaz
 */
public class AjouterRéservationMaisonController implements Initializable {

    @FXML
    private DatePicker date_debut_field;
    @FXML
    private DatePicker date_fin_field;
    @FXML
    private ChoiceBox<Integer> disponibiliter_field;
    @FXML
    private Label total_fied;
    @FXML
    private Button confirmer_button;
    @FXML
    private Label house_name;
    
    
    //to change  
    static Maison maison;
    float total=0f;
    
    ServiceMaisonIPM service_maison=new ServiceMaisonIPM();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        house_name.setText(maison.getDescription());
        date_debut_field.setDayCellFactory(callB);
        date_fin_field.setDayCellFactory(call_date_debut);
        date_fin_field.setDisable(true);
        service_maison.get_maison_by_id(maison.getId()).forEach((p)->{ 
            total=p.getPrix();
            disponibiliter_field.setValue(1);
             int ds=p.getCapacite(); 
             for (int i=1; i<=ds;i++) {
                disponibiliter_field.getItems().add(i);
             }
             total_fied.setText(p.getPrix() +"DT");
        });

        disponibiliter_field.setOnAction(event -> {
           int ds=disponibiliter_field.getValue();
        }); 
         date_debut_field.setOnAction(event -> { 
           if(date_debut_field.getValue()!=null)
            date_fin_field.setDisable(false);
        });
         
        date_fin_field.setOnAction(event -> { 
            if(date_debut_field.getValue()!=null){
                   total=calculate_total(total);
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
        total_fied.setText("");
         if(check_from_filled()){  
            Date date_deb = Date.valueOf(date_debut_field.getValue()); 
            Date date_fn = Date.valueOf(date_fin_field.getValue());  
            int nbr_des=disponibiliter_field.getValue(); 
            Reservation reservation=new Reservation(date_deb,date_fn,total,0,0,maison.getId(),0,Utilisateur.user_connecter.getId(),0,"maison"); 
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
        if(date_debut_field.getValue()!=null && date_fin_field.getValue()!=null && disponibiliter_field.getValue()!=null  ){
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
