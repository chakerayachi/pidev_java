/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Maison;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class MaisonCardController implements Initializable {

    @FXML
    private VBox box;
 
    @FXML
    private ImageView image;
    @FXML
    private Label prix;
    @FXML
    private Label nbChambre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
     public void setData(Maison m){
        nbChambre.setText("Chambre(s) :  "+Integer.toString(m.getNb_chambres()));
        prix.setText("Prix :  "+Float.toString(m.getPrix()));
        image.setImage(new Image("file:/" + m.getImage()));
       
       
    }
    
}
