/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.MaisonDetailsController.maison;
import entities.Chambre;
import entities.Hotel;
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
import services.ServiceChambreIPM;


/**
 * FXML Controller class
 *
 * @author Wael
 */
public class HotelDetailsController implements Initializable {
    public static Hotel hot;
    ServiceChambreIPM s = new ServiceChambreIPM();
    Chambre c ;

    @FXML
    private Label libelle;
    @FXML
    private Label description;
    @FXML
    private Label region;
    @FXML
    private Label ville;
    @FXML
    private Label nbEtoile;
    @FXML
    private Button reservation;
    @FXML
    private Button back;
    @FXML
    private ImageView image;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        libelle.setText(hot.getLibelle());
        nbEtoile.setText("Etoile :  "+Integer.toString(hot.getNb_etoile()));
        description.setText("Description :\n"+hot.getDescription());
        region.setText("Region :  "+hot.getRegion());
        ville.setText("Ville :  "+hot.getVille());
        image.setImage(new Image("file:/" + hot.getImage()));
        
    }    
    
    
    

    @FXML
    private void reservation(ActionEvent event) throws IOException {
        
        /*Parent root = FXMLLoader.load(getClass().getResource("../GUI/.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();*/
    }

    @FXML
    private void back(ActionEvent event) {
        
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../GUI/ClientHotel.fxml"));
            Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HotelDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
		
    }
    
}
