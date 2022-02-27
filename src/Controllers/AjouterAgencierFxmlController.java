/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.ServiceUtilisateurIMP;

/**
 * FXML Controller class
 *
 * @author chaker
 */
public class AjouterAgencierFxmlController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private JFXTextField nomTexte;
    @FXML
    private JFXTextField prenomTexte;
    @FXML
    private JFXTextField cinTexte;
    @FXML
    private JFXTextField cinTexte4;
    @FXML
    private JFXTextField loginTexte;
    @FXML
    private JFXTextField passwordTexte;
    @FXML
    private JFXTextField emailtexte;
    @FXML
    private JFXTextField num_telTexte;
    @FXML
    private JFXTextField afresseTexte;

    /**
     * Initializes the controller class.
     */
    ServiceUtilisateurIMP su = new ServiceUtilisateurIMP();
    Utilisateur userConn = Utilisateur.user_connecter;
    String mewImagePath = userConn.getImage();
    @FXML
    private JFXButton btn_image;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        String path = "Users/chaker/NetBeansProjects/pidev_java_chaker/pidev_java/src/Ressources/Image/" + mewImagePath;
        image.setImage(new Image("file:/" + path, 193, 200, false, false));

    }

    public Boolean CheckLogin() {
        Boolean verif = true;
        List<Utilisateur> list_user = su.afficherUtilisateur();
        for (int i = 0; i < list_user.size(); i++) {
            if (list_user.get(i).getLogin().equals(loginTexte.getText())) {
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
        Matcher m = p.matcher(cinTexte.getText());
        if (m.find() && m.group().equals(cinTexte.getText()) && cinTexte.getText().length() == 8) {
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
        Matcher m = p.matcher(num_telTexte.getText());
        if (m.find() && m.group().equals(num_telTexte.getText()) && num_telTexte.getText().length() == 8) {
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
        Matcher m = p.matcher(emailtexte.getText());
        if (m.find() && m.group().equals(emailtexte.getText())) {
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

    public Boolean VerifUserChamps() {

        int verif = 0;

        String style = " -fx-border-color: red;";
        // i will make it better 
        if (loginTexte.getText().trim().equals("")) {
            loginTexte.setStyle(style);
            verif = 1;

        }
        if (passwordTexte.getText().trim().equals("")) {
            passwordTexte.setStyle(style);
            verif = 1;

        }
        if (nomTexte.getText().trim().equals("")) {
            nomTexte.setStyle(style);
            verif = 1;

        }
        if (prenomTexte.getText().trim().equals("")) {
            prenomTexte.setStyle(style);
            verif = 1;

        }
        if (emailtexte.getText().trim().equals("")) {
            emailtexte.setStyle(style);
            verif = 1;

        }
        if (num_telTexte.getText().trim().equals("")) {
            num_telTexte.setStyle(style);
            verif = 1;

        }
        if (afresseTexte.getText().trim().equals("")) {
            afresseTexte.setStyle(style);
            verif = 1;

        }
        if (cinTexte.getText().trim().equals("")) {
            cinTexte.setStyle(style);
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

    public void goToDahsboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AdminDashboardFXML.fxml"));
            Parent root = loader.load();
            btn_image.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjouterAgencierFxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterAgencier(ActionEvent event) {
        Utilisateur agencierUtilisateur = new Utilisateur();

        if (CheckLogin() && VerifUserChamps() && validateNumberCin() && validateNumberphone() && ValidateEmail()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Demande de confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Est-ce que sur d ajouter un nouveau agencier ");
            Optional<ButtonType> btn = alert.showAndWait();
            if (btn.get() == ButtonType.OK) {
                agencierUtilisateur.setLogin(loginTexte.getText());
                agencierUtilisateur.setPassword(su.encrypt(passwordTexte.getText()));
                agencierUtilisateur.setNom(nomTexte.getText());
                agencierUtilisateur.setPrenom(prenomTexte.getText());
                agencierUtilisateur.setEmail(emailtexte.getText());
                agencierUtilisateur.setNum_tel(Integer.parseInt(num_telTexte.getText()));
                agencierUtilisateur.setCin(Integer.parseInt(cinTexte.getText()));
                Date date1 = new Date();
                String account_date = new SimpleDateFormat("yyyy-MM-dd").format(date1);
                agencierUtilisateur.setAccount_date(account_date);
                su.ajoutAgencier(agencierUtilisateur);

                Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
                resAlert.setHeaderText(null);
                resAlert.setContentText("L ajout a été avec sucess");
                resAlert.showAndWait();
                goToDahsboard();
            } else {

                alert.close();
            }

        }
    }

    @FXML
    private void goToDashboard(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AdminDashboardFXML.fxml"));
            Parent root = loader.load();
            nomTexte.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AjouterAgencierFxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AjouterAgencier(ActionEvent event) {
    }

    public void sucess() {
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("confirmation");
        al.setContentText("Agenicer a ete ajouter avec sucees");
        al.setHeaderText(null);
        al.show();
    }

    public void confirmation() {
        Alert al = new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("confirmation");
        al.setContentText("Agenicer a ete ajouter avec sucees");
        al.setHeaderText(null);
        al.show();
    }

}
