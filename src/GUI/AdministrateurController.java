/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author alaaz
 */
public class AdministrateurController implements Initializable {

    @FXML
    private ScrollPane scrollpane_container;
    @FXML
    private ScrollPane nav_container;
    @FXML
    private VBox vbox_container;
    @FXML
    private Button nav_button_reservation;
    @FXML
    private Button nav_button_evenement;
    @FXML
    private Button nav_button_evenement1;
    @FXML
    private Button nav_button_evenement11;
    @FXML
    private Button nav_button_evenement111;
    @FXML
    private Button nav_button_evenement112;
    @FXML
    private Button nav_button_evenement113;
    @FXML
    private Button nav_button_evenement114;
    @FXML
    private VBox vbox_container_users;
    @FXML
    private VBox vbox_container_hergement;
    @FXML
    private VBox vbox_container_voiture;
    @FXML
    private VBox vbox_container_evenement;
    @FXML
    private VBox vbox_container_forum;
    @FXML
    private VBox vbox_container_reservation;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        nav_container.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        vbox_container_users.setVisible(false);
        vbox_container_users.setManaged(false);
        vbox_container_hergement.setVisible(false);
        vbox_container_hergement.setManaged(false);
        vbox_container_voiture.setVisible(false);
        vbox_container_voiture.setManaged(false);
        vbox_container_evenement.setVisible(false);
        vbox_container_evenement.setManaged(false);
        vbox_container_forum.setVisible(false);
        vbox_container_forum.setManaged(false);
        vbox_container_reservation.setVisible(false);
        vbox_container_reservation.setManaged(false);

    }    

    @FXML
    private void show_local_nav_list(ActionEvent event) { 
       if(!vbox_container_hergement.isVisible()){ 
          vbox_container_hergement.setVisible(true);
          vbox_container_hergement.setManaged(true);
       }else{
          vbox_container_hergement.setVisible(false);
          vbox_container_hergement.setManaged(false);
       }
        
    }

    @FXML
    private void show_car_nav_list(ActionEvent event) {
        if(!vbox_container_voiture.isVisible()){ 
          vbox_container_voiture.setVisible(true);
          vbox_container_voiture.setManaged(true);
       }else{
          vbox_container_voiture.setVisible(false);
          vbox_container_voiture.setManaged(false);
       }
    }

    @FXML
    private void show_event_nav_list(ActionEvent event) {
        if(!vbox_container_evenement.isVisible()){ 
          vbox_container_evenement.setVisible(true);
          vbox_container_evenement.setManaged(true);
       }else{
          vbox_container_evenement.setVisible(false);
          vbox_container_evenement.setManaged(false);
       }
    }

    @FXML
    private void show_forum_nav_list(ActionEvent event) {
          if(!vbox_container_forum.isVisible()){ 
          vbox_container_forum.setVisible(true);
          vbox_container_forum.setManaged(true);
       }else{
          vbox_container_forum.setVisible(false);
          vbox_container_forum.setManaged(false);
       }
         
    }

    @FXML
    private void show_reservation_nav_list(ActionEvent event) throws IOException {  
      
       if(!vbox_container_reservation.isVisible()){ 
             vbox_container_reservation.setVisible(true);
             vbox_container_reservation.setManaged(true);
       }else{
           /* int button_index = vbox_container.getChildren().indexOf(nav_button_reservation);
            vbox_container.getChildren().add(button_index+1, vbox_container_reservation);*/
              vbox_container_reservation.setVisible(false);
             vbox_container_reservation.setManaged(false);
       }
    }

    @FXML
    private void show_hotels_reservations(ActionEvent event) {
        try {
            AnchorPane view =FXMLLoader.load(getClass().getResource("../GUI/AdministrateurRéservationsHotels.fxml"));
            scrollpane_container.setContent(view);
        } catch (IOException ex) {
            Logger.getLogger(AdministrateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void show_houses_reservations(ActionEvent event) {
        
    }

    @FXML
    private void show_cars_reservations(ActionEvent event) {
    }

    @FXML
    private void show_tickets_reservations(ActionEvent event) {
    }
    
}
