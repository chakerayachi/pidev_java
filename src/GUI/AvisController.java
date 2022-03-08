/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Avis;
import entities.Voiture;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import services.ServiceAvisIMP;

/**
 * FXML Controller class
 *
 * @author Hani
 */
public class AvisController implements Initializable {

    @FXML
    private Rating addreview;
    @FXML
    private Button ok;
    public static Voiture voiture;
ServiceAvisIMP sa = new ServiceAvisIMP();
    private int id_voiture;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }   
    
  
       public void setid_voiture(int id_voiture) {
        
       this.id_voiture = id_voiture;
    }

    @FXML
    private void submit(ActionEvent event) {
          
        Avis a = new Avis();
      
        a.setValeur( addreview.getRating());
        System.out.println("rating"+addreview.getRating());
        a.setId_voiture(id_voiture);
        //System.out.println("voiture id"+voiture.getId());
        sa.ajout(a);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Avis!");
        alert.setHeaderText(null);
        alert.setContentText("Merci pour votre Avis!");
        alert.showAndWait();
        
        try {
                    Parent root = FXMLLoader.load(getClass().getResource("../GUI/Uiclientreservationvoiture.fxml"));
                    
                    
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                               scene.getStylesheets().add(getClass().getResource("../GUI/Styling.css").toExternalForm());

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ClientEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
}
