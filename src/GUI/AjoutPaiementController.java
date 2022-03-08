/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import entities.Carte;
import entities.Reservation;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ServiceChambreIPM;
import services.ServiceEvenementIMP;
import services.ServiceMailIMP;
import services.ServiceRéservationIMP;
import services.ServiceStripeIMP;
import services.ServiceTicketIMP;
import services.ServiceTransactionIMP;

/**
 * FXML Controller class
 *
 * @author alaaz
 */
public class AjoutPaiementController implements Initializable {
    
    @FXML
    private TextField card_number;
    @FXML
    private DatePicker exipration_date;
    @FXML
    private TextField cvc;
    
    
    
    
    Reservation reservation_data=null; 
    public static final int maxLength = 16;
    public static final int maxLengthCvc = 3;
 
    ServiceStripeIMP stripe =new ServiceStripeIMP();
    ServiceRéservationIMP service=new ServiceRéservationIMP();
    ServiceChambreIPM service_chambre=new ServiceChambreIPM();
    ServiceEvenementIMP service_evenement=new ServiceEvenementIMP();
    ServiceTicketIMP service_ticket=new ServiceTicketIMP();
    ServiceTransactionIMP service_transaction=new ServiceTransactionIMP();
    ServiceMailIMP service_mail=new ServiceMailIMP(); 
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
               if(check_card_valid()){
                   Carte carte=new Carte(); 
                    int  exp_month= exipration_date.getValue().getMonth().getValue();  
                    int  exp_year=exipration_date.getValue().getYear(); 
                    carte.setCvc(Integer.parseInt(cvc.getText()));
                    carte.setExp_month(exp_month);
                    carte.setExp_year(exp_year); 
                    carte.setNumber(card_number.getText());
                                System.out.println("reservation data"+reservation_data);
                                 String paymentIntent_id=stripe.payment(Utilisateur.user_connecter, (int) reservation_data.getMontant_a_payer(), carte);
                                 System.out.println("paymment intet "+ paymentIntent_id);
                                 if (paymentIntent_id!=""){
                                             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                             alert.setTitle("Paiement etat");
                                             alert.setHeaderText(null);
                                             alert.setContentText("Paiement effectué avec succées");
                                             alert.showAndWait(); 

                                             if(reservation_data.getType()=="hotel"){ 
                                                   System.out.println("reservation data "+reservation_data);
                                                   service_chambre.update_chambre_disponibiliter(reservation_data.getId_chambre());
                                                   int res=service.add_reservation_hotel_chambre(reservation_data,paymentIntent_id);
                                                   System.out.println("hotel res "+res);
                                                   service_mail.send_payment_message(Utilisateur.user_connecter, reservation_data, service_transaction.get_transaction_by_id(res));
                                                  try {
                                                       Parent root = FXMLLoader.load(getClass().getResource("../GUI/UtilisateurRéservations.fxml"));
                                                       Scene scene = new Scene(root);
                                                       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                                       stage.setScene(scene);
                                                       stage.show();
                                                   } catch (IOException ex) {
                                                       Logger.getLogger(AdministrateurRéservationsEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
                                                   }


                                             }else if(reservation_data.getType()=="maison"){ 
                                                   int res=service.add_reservation_house(reservation_data,paymentIntent_id);
                                                   service_mail.send_payment_message(Utilisateur.user_connecter, reservation_data, service_transaction.get_transaction_by_id(res));

                                                  try {
                                                       Parent root = FXMLLoader.load(getClass().getResource("../GUI/UtilisateurRéservations.fxml"));
                                                       Scene scene = new Scene(root);
                                                       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                                       stage.setScene(scene);
                                                       stage.show();
                                                   } catch (IOException ex) {
                                                       Logger.getLogger(AdministrateurRéservationsEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
                                                   }

                                             }else if(reservation_data.getType()=="ticket"){ 
                                                   service_evenement.update_evenement_disponibiliter(service_evenement.get_event_by_ticket_id(reservation_data.getId_ticket()));
                                                   int res=service.add_reservation_ticket(reservation_data,paymentIntent_id);
                                                   service_mail.send_payment_message(Utilisateur.user_connecter, reservation_data, service_transaction.get_transaction_by_id(res));
                                                  try {
                                                       Parent root = FXMLLoader.load(getClass().getResource("../GUI/UtilisateurRéservations.fxml"));
                                                       Scene scene = new Scene(root);
                                                       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                                       stage.setScene(scene);
                                                       stage.show();
                                                   } catch (IOException ex) {
                                                       Logger.getLogger(AdministrateurRéservationsEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
                                                   }

                                             }else{ 
                                                   int res=service.add_reservation_car(reservation_data,paymentIntent_id);
                                                   service_mail.send_payment_message(Utilisateur.user_connecter, reservation_data, service_transaction.get_transaction_by_id(res));
                                                   try {
                                                       Parent root = FXMLLoader.load(getClass().getResource("../GUI/UtilisateurRéservations.fxml"));
                                                       Scene scene = new Scene(root);
                                                       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                                       stage.setScene(scene);
                                                       stage.show();
                                                   } catch (IOException ex) {
                                                       Logger.getLogger(AdministrateurRéservationsEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
                                                   }

                                               }
                                        }else{ 
                                             System.out.println("card invalid output");
                                         }    
                   
               }else{ 
                   Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Erreur Validation Formulaire");
                    alert.setHeaderText(null);
                    alert.setContentText("Numéro de carte invalide ");
                    alert.showAndWait();
                   
               }
                 
        }
    } 
    public boolean check_card_valid(){
        boolean check=false; 
        String card=card_number.getText().toString();
        if(card.equals("4242424242424242") || 
                card.equals("4000056655665556") || 
                card.equals("5555555555554444")|| 
                card.equals("2223003122003222") || 
                card.equals("5200828282828210") ||
                card.equals("5105105105105100") ||
                card.equals("378282246310005") ||
                card.equals("6011111111111117") ||
                card.equals("3056930009020004")){ 
                check=true;
        }
        return check;
    }
    
}

