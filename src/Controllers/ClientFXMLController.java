/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import services.ServiceUtilisateurIMP;

/**
 * FXML Controller class
 *
 * @author chaker
 */
public class ClientFXMLController implements Initializable {

    private GridPane gridImage;
    @FXML
    private ImageView image;
    @FXML
    private JFXButton btn_image;
    @FXML
    private JFXTextField nomTexte;
    @FXML
    private JFXTextField prenomTexte;
    @FXML
    private JFXTextField emailTexte;
    @FXML
    private JFXTextField numTexte;
    @FXML
    private JFXTextField cinTexte;
    @FXML
    private VBox choiceBox;
    @FXML
    private JFXTextField adresseTexte;

    /**
     * Initializes the controller class.
     */
    
    
     ServiceUtilisateurIMP su = new ServiceUtilisateurIMP();
    Utilisateur userConn = Utilisateur.user_connecter;
    private List<String> listfiles;
    String mewImagePath = userConn.getImage();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listfiles = new ArrayList<>();
        listfiles.add("*.png");
        listfiles.add("*.jpg");

        String path = "Users/Firas CHKOUNDALI/Desktop/pidev_java-main/src/Ressources/Image/" + mewImagePath;
        image.setImage(new Image("file:/" + path, 193, 200, false, false));
        nomTexte.setText(userConn.getNom());
        prenomTexte.setText(userConn.getPrenom());
        emailTexte.setText(userConn.getEmail());
        numTexte.setText(String.valueOf(userConn.getNum_tel()));
        cinTexte.setText("" + userConn.getCin());
        adresseTexte.setText(""+userConn.getAdresse());
   
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
        Matcher m = p.matcher(numTexte.getText());
        if (m.find() && m.group().equals(numTexte.getText()) && numTexte.getText().length() == 8) {
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
        Matcher m = p.matcher(emailTexte.getText());
        if (m.find() && m.group().equals(emailTexte.getText())) {
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


        if (nomTexte.getText().trim().equals("")) {
            nomTexte.setStyle(style);
            verif = 1;

        }
        if (prenomTexte.getText().trim().equals("")) {
            prenomTexte.setStyle(style);
            verif = 1;

        }
        if (emailTexte.getText().trim().equals("")) {
            emailTexte.setStyle(style);
            verif = 1;

        }
        if (numTexte.getText().trim().equals("")) {
            numTexte.setStyle(style);
            verif = 1;

        }

        if (cinTexte.getText().trim().equals("")) {
            cinTexte.setStyle(style);
            verif = 1;

        }
        if (adresseTexte.getText().trim().equals("")) {
            adresseTexte.setStyle(style);
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

    @FXML
    private void updateClient(ActionEvent event) {
        
         Utilisateur user = new Utilisateur();
        if (VerifUserChamps() && validateNumberCin() && validateNumberphone() && ValidateEmail()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Demande de confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Est-ce que vous etes sur de votre modification");
            Optional<ButtonType> btn = alert.showAndWait();
            if (btn.get() == ButtonType.OK) {
                user.setLogin(userConn.getLogin());
                user.setPassword(userConn.getPassword());
                user.setAdresse(adresseTexte.getText());
                user.setRole(userConn.getRole());
                user.setDescription(userConn.getDescription());
                user.setEtat(userConn.getEtat());
                user.setAccount_date(userConn.getAccount_date());
                user.setNom(nomTexte.getText());
                user.setPrenom(prenomTexte.getText());
                user.setEmail(emailTexte.getText());
                user.setNum_tel(Integer.parseInt(numTexte.getText()));
                user.setCin(Integer.parseInt(cinTexte.getText()));
                user.setImage(mewImagePath);
                su.modifierUtilisateur(user, userConn.getId());
                String path = "Users/Firas CHKOUNDALI/Desktop/pidev_java-main/src/Ressources/Image/" + mewImagePath;
                image.setImage(new Image("file:/" + path, 193, 200, false, false));

                Alert resAlert = new Alert(Alert.AlertType.INFORMATION);
                resAlert.setHeaderText(null);
                resAlert.setContentText("La modification a été effectuer avec succes");
                resAlert.showAndWait();
            } else {
                mewImagePath = userConn.getImage();
                String path = "Users/Firas CHKOUNDALI/Desktop/pidev_java-main/src/Ressources/Image/" + mewImagePath;
                nomTexte.setText(userConn.getNom());
                prenomTexte.setText(userConn.getPrenom());
                emailTexte.setText(userConn.getEmail());
                numTexte.setText(String.valueOf(userConn.getNum_tel()));
                cinTexte.setText("" + userConn.getCin());
                adresseTexte.setText(userConn.getAdresse());
                alert.close();
            }

        }
        
        
        
    }

    @FXML
    private void FileChooser(ActionEvent event) {
         FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", listfiles));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            mewImagePath = f.getName();
        }
    }

    @FXML
    private void goToForgetPass(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ModifierPasswordClientFXML.fxml"));
            Parent root = loader.load();
            adresseTexte.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/LoadingFXML.fxml"));
            Parent root = loader.load();
            adresseTexte.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
