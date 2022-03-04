/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Chambre;
import entities.Hotel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ServiceChambreIPM;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class AjoutChambreController implements Initializable {
    ServiceChambreIPM s = new ServiceChambreIPM();
    
   ObservableList<String> typeList = FXCollections.observableArrayList("Single", "Double", "Triple", "Quadruple");

    @FXML
    private ChoiceBox type;
    @FXML
    private TextField prix;
    @FXML
    private Button ajouterBtn;
    @FXML
    private ImageView logo;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.setValue("Single"); 
        type.setItems(typeList);
    }    
    
    @FXML
    private void Insert(ActionEvent event) throws IOException {
        Chambre c = new Chambre();
       
        c.setType((String) type.getSelectionModel().getSelectedItem());
        c.setPrix(Float.parseFloat(prix.getText()));
        c.setId_hotel(6);
        
     
        
        s.create(c);
        
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListChambre.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
}
