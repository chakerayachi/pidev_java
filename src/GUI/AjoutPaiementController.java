/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.stripe.Stripe;
import entities.Carte;
import entities.Reservation;
import entities.Utilisateur;
import static java.awt.SystemColor.text;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import services.ServiceRéservationIMP;
import services.ServiceStripeIMP;

/**
 * FXML Controller class
 *
 * @author alaaz
 */
public class AjoutPaiementController implements Initializable {

    @FXML
    private VBox left_container;
    @FXML
    private ImageView image_card;
    @FXML
    private Text previous_text;
     @FXML
    private TextField card_number;
    @FXML
    private DatePicker exipration_date;
    Reservation reservation_data=null; 
    public static final int maxLength = 16;
    public static final int maxLengthCvc = 3;
    @FXML
    private TextField cvc;
    ServiceStripeIMP stripe =new ServiceStripeIMP();
            ServiceRéservationIMP service=new ServiceRéservationIMP();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stripe.apiKey="sk_test_51KW6yrADzWkQoAVyoXU2QxKdXriWn9eO6XH8SCttqQlpC7ZVzsd3DQ5zwq9LYr5wOgEJYQJ6KIZmLQXLCmBz6pqG00xeuLIrsT";
        exipration_date.setDayCellFactory(callB);
        card_number.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
                card_number.setText(newValue.replaceAll("[^\\d]", ""));
        });
        /*card_number.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                try {
                    Integer.parseInt(newValue);
                    if(newValue.length() > maxLength)
                        card_number.setText(oldValue);
                } catch (Exception e) {
                    card_number.setText(oldValue);
                }
            }
        });*/
        cvc.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
                cvc.setText(newValue.replaceAll("[^\\d]", ""));
        });
        cvc.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                try {
                    Integer.parseInt(newValue);
                    if(newValue.length() > maxLengthCvc)
                        cvc.setText(oldValue);
                } catch (Exception e) {
                    cvc.setText(oldValue);
                }
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
    public void set_reservation_data(Reservation reservation){  
        System.out.println("reservation py :"+reservation);
        reservation_data=reservation;
    } 
    
      public boolean check_from_filled(){
        boolean check=false;
        if(card_number.getLength()==16 &&cvc.getLength()==maxLengthCvc && exipration_date.getValue()!=null ){
            check=true;
        }
        return check;
    }

    @FXML
    private void pay_reservation(ActionEvent event) { 
           if(check_from_filled()){  
               Carte carte=new Carte(); 
               int  exp_month= exipration_date.getValue().getMonth().getValue();  
               int  exp_year=exipration_date.getValue().getYear(); 
               carte.setCvc(Integer.parseInt(cvc.getText()));
               carte.setExp_month(exp_month);
               carte.setExp_year(exp_year); 
               carte.setNumber(card_number.getText()); 
               System.out.println(carte.getNumber());
               Utilisateur user= new Utilisateur("alaa","zarrouk","alaa","zarrouk","alaazarrouk7@gmail.com",56353474,10014035,"medina","","",""); 
               String paymentIntent_id=stripe.payment(user, (int) reservation_data.getMontant_a_payer(), carte); 
              if (paymentIntent_id!=""){ 
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Paiement etat");
                  alert.setHeaderText(null);
                  alert.setContentText("Paiement effectué avec succées");
                  alert.showAndWait();
                  if(reservation_data.getType()=="hotel"){
                       service.add_reservation_hotel_chambre(reservation_data,paymentIntent_id); 
                       try {
                Parent root = FXMLLoader.load(getClass().getResource("../GUI/AdministrateurRéservations.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdministrateurRéservationsEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
                       
                       
                  }else if(reservation_data.getType()=="maison"){ 
                      service.add_reservation_house(reservation_data,paymentIntent_id);
                      try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AdministrateurRéservationsMaisons.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdministrateurRéservationsEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
                     
                  }else if(reservation_data.getType()=="ticket"){ 
                      service.add_reservation_ticket(reservation_data,paymentIntent_id);
                      try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AdministrateurRéservationsEvenements.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdministrateurRéservationsEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
                  }else{
                       try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AdministrateurRéservationsVoitures.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdministrateurRéservationsEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
                          
                  }
              }
              
               
               
               
           }else{ 
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur Validation Formulaire");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir le formulaire");
                alert.showAndWait();
           }
        
    }
    
}
