/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Firas CHKOUNDALI
 */
public class MenuController implements Initializable {

    @FXML
    private JFXButton forum;
    @FXML
    private JFXButton hotel;
    @FXML
    private JFXButton maison;
    @FXML
    private JFXButton ajoutmaison;
    @FXML
    private JFXButton voiture;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void consulterforum(ActionEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/gestiontopic.fxml"));
            Parent root = loader.load();
            forum.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void consulterhotel(ActionEvent event) {
        
        
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/ClientHotel.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../GUI/Stylesheet.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void consultermaison(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/ClientMaison.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../GUI/Stylesheet.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajoutmaison(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutMaison.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../GUI/Stylesheet.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void consultervoiture(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/Uiclientreservationvoiture.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../GUI/Stylesheet.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reclamation(ActionEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/reclamation.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../GUI/Stylesheet.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void evenement(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/ClientEvenement.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../GUI/Stylesheet.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
