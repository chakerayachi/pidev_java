/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import services.ServiceUtilisateurIMP;

/**
 * FXML Controller class
 *
 * @author chaker
 */
public class SignUpFXMLController implements Initializable {

    @FXML
    private Label LAbelCreateAccount;
    @FXML
    private JFXTextField LoginTexte;
    @FXML
    private JFXTextField NomTexte;
    @FXML
    private JFXTextField PrenomTexte;
    @FXML
    private JFXTextField EmailTexte;
    @FXML
    private JFXTextField PhoneNumberTexte;
    @FXML
    private JFXTextField AdresseTexte;
    @FXML
    private JFXTextField CinTexte;
    @FXML
    private JFXPasswordField PasswordTexte;

    /**
     * Initializes the controller class.
     */
    // service call
    ServiceUtilisateurIMP service_user = new ServiceUtilisateurIMP();
    
    Date date1 = new Date();
      String account_date = new SimpleDateFormat("yyyy-MM-dd").format(date1);
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoToLogin(ActionEvent event) {
        
        try {
            
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/LoginFXML.fxml"));
            Parent root = loader.load();
            LAbelCreateAccount.getScene().setRoot(root);
            
            
        } catch (IOException ex) {
            Logger.getLogger(SignUpFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void SignUp(ActionEvent event) {
        
        Utilisateur user = new Utilisateur();
        
        user.setLogin(LoginTexte.getText());
        user.setPassword(PasswordTexte.getText());
        user.setNom(NomTexte.getText());
        user.setPrenom(PrenomTexte.getText());
        user.setEmail(EmailTexte.getText());
        user.setNum_tel(Integer.parseInt(PhoneNumberTexte.getText()));
        user.setCin(Integer.parseInt(CinTexte.getText()));
        user.setAdresse(AdresseTexte.getText());
        user.setEtat("active");
        user.setAccount_date(account_date);
        
        
        service_user.ajoutClient(user);
        
        
        

        
        
        
        
        
        
        
    }
    
    
}
