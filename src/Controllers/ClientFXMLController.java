/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import entities.Utilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author chaker
 */
public class ClientFXMLController implements Initializable {

    @FXML
    private GridPane gridImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     String image =Utilisateur.user_connecter.getImage();
          String path = "Users/chaker/NetBeansProjects/pidev_java_chaker/pidev_java/src/Ressources/Image/"+image;
      gridImage.getChildren().clear();
      gridImage.add(new ImageView(new Image("file:/"+path, 193, 200, false, false)), 0, 0);
    }    
    
}
