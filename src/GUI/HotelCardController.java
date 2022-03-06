/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Hotel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 *
 * @author Wael
 */
public class HotelCardController implements Initializable {

     @FXML
    private VBox box;

    @FXML
    private ImageView image;

    @FXML
    private Label libelle;

    @FXML
    private Label nbEtoile;
    @FXML
    private VBox card;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String cssLayout = "-fx-border-color: #DCDCDC;\n" +
                   "-fx-background-color: #DCDCDC;\n" +
                   "-fx-border-insets: 5;\n" +
                   "-fx-border-width: 3;\n" +
                   "-fx-border-style: solid;\n";

          
        box.setStyle(cssLayout);
    }    
    public void setData(Hotel h){
        libelle.setText(h.getLibelle());
        nbEtoile.setText("Etoile :  "+Integer.toString(h.getNb_etoile())); 
        
    }

}
