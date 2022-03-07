/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sabri
 */
public class AffichageEvenementClientController implements Initializable {
    public static Evenement even;
    @FXML
    private Text description;
    @FXML
    private Text date;
    @FXML
    private Text emplacement;
    @FXML
    private Text capacite;
    @FXML
    private Text duree;
    @FXML
    private Button reservation;
    @FXML
    private Text libelle;
    @FXML
    private Button retour;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        description.setText(description.getText()+"\n"+even.getDescription());
        date.setText(date.getText()+" : "+even.getDate().toString());
        emplacement.setText(emplacement.getText()+" : "+even.getEmplacement());
        capacite.setText(capacite.getText()+" : "+even.getNb_place());
        duree.setText(duree.getText()+" : "+Integer.toString(even.getDuree()));
        libelle.setText(even.getLibelle());
        image.setImage(new Image("file:/" + even.getImage()));
    }    

    @FXML
    private void reservation(ActionEvent event) {
        
        
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
                    Parent root = FXMLLoader.load(getClass().getResource("../GUI/ClientEvenement.fxml"));
                    
                    
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
