/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Hotel;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ServiceHotelIPM;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class AjoutHotelController implements Initializable {

    @FXML
    private TextField ville;
    @FXML
    private TextField region;
    @FXML
    private TextField tel;
    @FXML
    private TextField libelle;
    @FXML
    private TextField etoile;
    @FXML
    private TextArea adresse;
    @FXML
    private TextArea description;
    @FXML
    private Button ajouterBtn;
    
    ServiceHotelIPM s = new ServiceHotelIPM();
    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        

    @FXML
    private void Insert(ActionEvent event) throws IOException {
        
        
     
        if (control()){
            
            Hotel h = new Hotel();
            h.setAdresse(adresse.getText());
            h.setVille(ville.getText());
            h.setRegion(region.getText());
            h.setNum_tel(Integer.parseInt(tel.getText()));
            h.setLibelle(libelle.getText());
            h.setNb_etoile(Integer.parseInt(etoile.getText()));
            h.setDescription(description.getText());
            h.setVille(ville.getText());
            h.setId_user(1);
            s.create(h);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès!");
            alert.setHeaderText(null);
            alert.setContentText("Hotel ajouté avec succès");
            alert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListHotel.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
       
            } else if(control()==false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur Validation!");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
            
        }
        
        
     
        
    
    private boolean control(){
        if(adresse.getText().isEmpty() || ville.getText().isEmpty() ||  region.getText().isEmpty()|| tel.getText().isEmpty() || libelle.getText().isEmpty() || etoile.getText().isEmpty() || description.getText().isEmpty() || ville.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
}
