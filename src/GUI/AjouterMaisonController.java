/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Maison;
import entities.Utilisateur;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ServiceMaisonIPM;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class AjouterMaisonController implements Initializable {
    
    ServiceMaisonIPM s = new ServiceMaisonIPM();

    @FXML
    private TextArea adresse;
    @FXML
    private TextField region;
    @FXML
    private TextField tel;
    @FXML
    private TextArea description;
    @FXML
    private TextField nbChambre;
    @FXML
    private TextField capacite;
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
        // TODO
    }    
    // lehna zeda 
    Utilisateur userConn = new Utilisateur();
    
    @FXML
    private void Insert(ActionEvent event) throws IOException {
        if (control()){
        Maison m = new Maison();
        m.setAdresse(adresse.getText());
        m.setRegion(region.getText());
        m.setNum_tel(Integer.parseInt(tel.getText()));
        m.setPrix(Float.parseFloat(prix.getText()));
        m.setCapacite(Integer.parseInt(capacite.getText()));
        m.setDescription(description.getText());
        m.setNb_chambres(Integer.parseInt(nbChambre.getText()));
        m.setId_user(1);
        // ki theb t3adi id user connecter 
        //  m .setId_user(userConn.getId());

        s.create(m);
        
        //  zeyeda lena 
            try {
                GUI.MaillingController.sendMail(userConn, " Confirmation  ", " Votre maison a ete bien publier sur notre apllication nous vous informer lors d une reservation du votre maison merci d utiliswe notre applicationss ");
            } catch (Exception ex) {
                Logger.getLogger(AjouterMaisonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        // toufa lena 
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès!");
            alert.setHeaderText(null);
            alert.setContentText("Maison ajouté avec succès");
            alert.showAndWait();
        
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListMaison.fxml"));
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
        if(adresse.getText().isEmpty() || region.getText().isEmpty() ||  tel.getText().isEmpty()|| prix.getText().isEmpty() || capacite.getText().isEmpty() || description.getText().isEmpty() || nbChambre.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
}
