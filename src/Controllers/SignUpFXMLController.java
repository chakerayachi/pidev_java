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
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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

    public Boolean VerifUserChamps() {

        int verif = 0;

        String style = " -fx-border-color: red;";

        String styledefault = "-fx-border-color: green;";

        // i will make it better 
        if (LoginTexte.getText().equals("")) {
            LoginTexte.setStyle(style);
            verif = 1;

        }
        if (PasswordTexte.getText().equals("")) {
            PasswordTexte.setStyle(style);
            verif = 1;

        }
        if (NomTexte.getText().equals("")) {
            NomTexte.setStyle(style);
            verif = 1;

        }
        if (PrenomTexte.getText().equals("")) {
            PrenomTexte.setStyle(style);
            verif = 1;

        }
        if (EmailTexte.getText().equals("")) {
            EmailTexte.setStyle(style);
            verif = 1;

        }
        if (PhoneNumberTexte.getText().equals("")) {
            PhoneNumberTexte.setStyle(style);
            verif = 1;

        }
        if (AdresseTexte.getText().equals("")) {
            AdresseTexte.setStyle(style);
            verif = 1;

        }
        if (CinTexte.getText().equals("")) {
            CinTexte.setStyle(style);
            verif = 1;

        }

        if (verif == 0) {
            return true;
        }
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("Alert");
        al.setContentText("Verifier les champs");
        al.setHeaderText(null);
        al.show();

        return false;

    }

    public Boolean CheckLogin() {
        Boolean verif = true;
        List<Utilisateur> list_user = service_user.afficherUtilisateur();
        for (int i = 0; i < list_user.size(); i++) {
            if (list_user.get(i).getLogin().equals(LoginTexte.getText())) {
                verif = false;

            }

        }
        if (verif == false) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Alert");
            al.setContentText("User login existe déja");
            al.setHeaderText(null);
            al.show();
        }

        return verif;
    }

    public String encrypt(String password) {

        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    public String decrypt(String password) {

        return new String(Base64.getMimeDecoder().decode(password));
    }

    // sign up avec cryptage de mot de passe
    @FXML
    private void SignUp(ActionEvent event) {

        Utilisateur user = new Utilisateur();

        if (CheckLogin() && VerifUserChamps()) {

            user.setLogin(LoginTexte.getText());
            user.setPassword(encrypt(PasswordTexte.getText()));
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

}
