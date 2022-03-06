/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.HotelDetailsController;
import entities.Maison;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class MaisonDetailsController implements Initializable {
    public static Maison maison;
    @FXML
    private Label description;
    @FXML
    private Label region;
    
    @FXML
    private Button reservation;
    @FXML
    private Button back;
    
    @FXML
    private Label prix;
    @FXML
    private Label nbChambre;
    @FXML
    private Label capacite;
    @FXML
    private Label ville;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        description.setText(maison.getDescription());
        region.setText(maison.getRegion());
        prix.setText("Prix :  "+Float.toString(maison.getPrix()));
        nbChambre.setText("Chambre(s) :  "+Integer.toString(maison.getNb_chambres()));
        capacite.setText("Capacité :  "+Integer.toString(maison.getCapacite()));
        image.setImage(new Image("file:/" + maison.getImage()));
        
        
        
    }    

    @FXML
    private void reservation(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../GUI/ClientMaison.fxml"));
            Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HotelDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
