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
public class LoginFXMLController implements Initializable {

    @FXML
    private Label LabelHelloFriend;
    @FXML
    private JFXTextField LoginTexte;
    @FXML
    private JFXPasswordField PasswordTexte;

    /**
     * Initializes the controller class.
     */
    ServiceUtilisateurIMP su = new ServiceUtilisateurIMP();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void GoToSignUp(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/SignUpFXML.fxml"));
            Parent root = loader.load();
            LabelHelloFriend.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void GoToAdminDashboard(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AdminDashboardFXML.fxml"));
            Parent root = loader.load();
            LabelHelloFriend.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void GoToClientFML(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ClientFXML.fxml"));
            Parent root = loader.load();
            LabelHelloFriend.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void GoToAgencier(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AgenicerFXML.fxml"));
            Parent root = loader.load();
            LabelHelloFriend.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Login(ActionEvent event) {
        Utilisateur user = null;
        String encPass = su.encrypt(PasswordTexte.getText());
        if (su.Authentification(LoginTexte.getText(), encPass) != null) {

            System.out.println(encPass);
            user = su.Authentification(LoginTexte.getText(), encPass);
            Utilisateur.user_connecter = user;
            if (null != user.getRole()) {
                switch (user.getRole()) {
                    case "admin":
                        // go to admin
                        GoToAdminDashboard(event);
                        System.out.println("admin");
                        break;
                    case "client":
                        // go to client
                        GoToClientFML(event);
                        System.out.println("client");
                        break;
                    case "agencier":
                        // go to agenceier
                        GoToAgencier(event);
                        System.out.println("agencier");
                        break;
                    default:
                        break;
                }
            }

        }

    }

}
