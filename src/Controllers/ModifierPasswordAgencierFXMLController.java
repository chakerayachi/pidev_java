/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import services.ServiceUtilisateurIMP;

/**
 * FXML Controller class
 *
 * @author chaker
 */
public class ModifierPasswordAgencierFXMLController implements Initializable {

    @FXML
    private TextField motdepasseTexte;
    @FXML
    private TextField nouveaumotdepasseTexte;
    @FXML
    private TextField confiramtiondumotdupasseTexte;
    @FXML
    private JFXButton validerButton;

    /**
     * Initializes the controller class.
     */
    ServiceUtilisateurIMP su = new ServiceUtilisateurIMP();
    Utilisateur userConn = Utilisateur.user_connecter;
    String passcrypter = userConn.getPassword();
    String passDecrypter = su.decrypt(passcrypter);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    
    public void chngerInterface()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AgencierFXML.fxml"));
            Parent root = loader.load();
            validerButton.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModifierPasswordAgencierFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void changerPass(ActionEvent event) {
        if (passDecrypter.equals(motdepasseTexte.getText())) {
            if (nouveaumotdepasseTexte.getText().equals(confiramtiondumotdupasseTexte.getText())) {
                
                su.ChangerPassword(userConn.getId(), nouveaumotdepasseTexte.getText());
                   Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
            resAlert.setHeaderText(null);
            resAlert.setContentText("votre  nouveau mot de passe a ete changer avec sucess");
            resAlert.showAndWait();
            chngerInterface();                

            } else {
                
                
                Alert resAlert = new Alert(Alert.AlertType.WARNING);
            resAlert.setHeaderText("Miss match");
            resAlert.setContentText("verfier votre  nouveau mot de passe");
            resAlert.showAndWait();
            }
            

        }else {
            Alert resAlert = new Alert(Alert.AlertType.WARNING);
            resAlert.setHeaderText("Erreur");
            resAlert.setContentText("votre mot de passe st incorrect");
            resAlert.showAndWait();
        }

    }

}
