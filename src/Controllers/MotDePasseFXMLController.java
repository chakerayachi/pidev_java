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
import utils.SmsTools;

/**
 * FXML Controller class
 *
 * @author chaker
 */
public class MotDePasseFXMLController implements Initializable {

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
    private int randomnumber = (int) (Math.random() * 9999);
    @FXML
    private JFXButton dd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SmsTools.sendSms(Utilisateur.user_connecter, randomnumber + "");
    }

    @FXML
    private void changerPass(ActionEvent event) {

        if (motdepasseTexte.getText().equals(randomnumber + "")) {
            if (nouveaumotdepasseTexte.getText().equals(confiramtiondumotdupasseTexte.getText())) {
                su.ChangerPassword(Utilisateur.user_connecter.getId(), nouveaumotdepasseTexte.getText());

                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("Sucess");
                al.setContentText(" votre mot de passe a ete bien rnitialiser ");
                al.setHeaderText(null);
                al.showAndWait();
                goToLogin(event);
                
                
            } else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Alert");
                al.setContentText("Verifier la confirmiter de votre nouveau mot de passe ");
                al.setHeaderText(null);
                al.show();

            }

        } else {

            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Alert");
            al.setContentText("Votre code est incorrecte");
            al.setHeaderText(null);
            al.show();
        }

    }

    @FXML
    private void goToLogin(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/LoginFXML.fxml"));
            Parent root = loader.load();
            motdepasseTexte.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(MotDePasseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
