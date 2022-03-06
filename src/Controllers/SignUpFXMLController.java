/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import services.ServiceUtilisateurIMP;

/**
 * FXML Controller class
 *
 * @author chaker
 */
public class SignUpFXMLController implements Initializable {

    ServiceUtilisateurIMP su = new ServiceUtilisateurIMP();
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
    @FXML
    private JFXTextField ImageUser;

    /**
     * Initializes the controller class.
     */
    // service call
    ServiceUtilisateurIMP service_user = new ServiceUtilisateurIMP();

    Date date1 = new Date();
    String account_date = new SimpleDateFormat("yyyy-MM-dd").format(date1);

    private List<String> listfiles;
    @FXML
    private ImageView imageQR;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        listfiles = new ArrayList<>();
        listfiles.add("*.png");
        listfiles.add("*.jpg");
        imageQR.setImage(new Image("file:/Users/Firas CHKOUNDALI/Desktop/pidev_java-main/src/Ressources/Image/wlecomeQR.png", 193, 200, false, false));

    }

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
        // i will make it better 
        if (LoginTexte.getText().trim().equals("")) {
            LoginTexte.setStyle(style);
            verif = 1;

        }
        if (PasswordTexte.getText().trim().equals("")) {
            PasswordTexte.setStyle(style);
            verif = 1;

        }
        if (NomTexte.getText().trim().equals("")) {
            NomTexte.setStyle(style);
            verif = 1;

        }
        if (PrenomTexte.getText().trim().equals("")) {
            PrenomTexte.setStyle(style);
            verif = 1;

        }
        if (EmailTexte.getText().trim().equals("")) {
            EmailTexte.setStyle(style);
            verif = 1;

        }
        if (PhoneNumberTexte.getText().trim().equals("")) {
            PhoneNumberTexte.setStyle(style);
            verif = 1;

        }
        if (AdresseTexte.getText().trim().equals("")) {
            AdresseTexte.setStyle(style);
            verif = 1;

        }
        if (CinTexte.getText().trim().equals("")) {
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

    public boolean validateNumberCin() {

        Pattern p = Pattern.compile("[0-9]+\\.[0-9]+|[0-9]+");
        Matcher m = p.matcher(CinTexte.getText());
        if (m.find() && m.group().equals(CinTexte.getText()) && CinTexte.getText().length() == 8) {
            return true;
        } else {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Alert");
            al.setContentText("cin only can containes 8 didgets");
            al.setHeaderText(null);
            al.show();

        }
        return false;
    }

    public boolean validateNumberphone() {

        Pattern p = Pattern.compile("[0-9]+\\.[0-9]+|[0-9]+");
        Matcher m = p.matcher(PhoneNumberTexte.getText());
        if (m.find() && m.group().equals(PhoneNumberTexte.getText()) && PhoneNumberTexte.getText().length() == 8) {
            return true;
        } else {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Alert");
            al.setContentText("Phone number only can containes 8 didgets");
            al.setHeaderText(null);
            al.show();

        }
        return false;
    }

    public boolean ValidateEmail() {

        Pattern p = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$");
        Matcher m = p.matcher(EmailTexte.getText());
        if (m.find() && m.group().equals(EmailTexte.getText())) {
            return true;
        } else {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Alert");
            al.setContentText("Email is wrong");
            al.setHeaderText(null);
            al.show();

        }
        return false;
    }

    // sign up avec cryptage de mot de passe
    @FXML
    private void SignUp(ActionEvent event) {

        Utilisateur user = new Utilisateur();

        if (CheckLogin() && VerifUserChamps() && validateNumberCin() && validateNumberphone() && ValidateEmail()) {

            user.setLogin(LoginTexte.getText());
            user.setPassword(su.encrypt(PasswordTexte.getText()));
            user.setNom(NomTexte.getText());
            user.setPrenom(PrenomTexte.getText());
            user.setEmail(EmailTexte.getText());
            user.setNum_tel(Integer.parseInt(PhoneNumberTexte.getText()));
            user.setCin(Integer.parseInt(CinTexte.getText()));
            user.setAdresse(AdresseTexte.getText());
            user.setEtat("active");
            user.setAccount_date(account_date);
            user.setImage(ImageUser.getText());
            System.out.println(ImageUser.getText());
            System.out.println(user.getImage());
            service_user.ajoutClient(user);
            Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
            resAlert.setHeaderText(null);
            resAlert.setContentText("Account a ete cree avec sucess");
            resAlert.showAndWait();
            GoToLogin(event);

        }

    }

    @FXML
    private void FileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Image Files", listfiles));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            ImageUser.setText(f.getName());
        }

    }

    @FXML
    private void GoT(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/LoginFXML.fxml"));
            Parent root = loader.load();
            ImageUser.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(SignUpFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
