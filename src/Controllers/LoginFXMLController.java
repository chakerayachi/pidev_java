/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.Utilisateur;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import services.ServiceUtilisateurIMP;
import static utils.CodeQR.generateEAN13BarcodeImage;

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
    String messageAcceuil = "Welcome to Tfarhida : contact us"
            + "\n Tfarhida Contact : www.facebook.com/"
            + "\n Nexus Contact : ww.facebook.com/";
    @FXML
    private ImageView qrimage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            BufferedImage buffer = generateEAN13BarcodeImage(messageAcceuil);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(buffer, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);
            BufferedImage bImage2 = ImageIO.read(bis);
            ImageIO.write(bImage2, "png", new File("/Users/chaker/NetBeansProjects/pidev_java_chaker/pidev_java/src/Ressources/Image/wlecomeQR.png"));
            System.out.println("image created");
            qrimage.setImage(new Image("file:/Users/chaker/NetBeansProjects/pidev_java_chaker/pidev_java/src/Ressources/Image/wlecomeQR.png", 193, 200, false, false));
            
        } catch (Exception ex) {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AgencierFXML.fxml"));
            Parent root = loader.load();
            LabelHelloFriend.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean Active_account() {
        Boolean verif = true;
        List<Utilisateur> list_user = su.afficherUtilisateur();
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

    @FXML
    private void Login(ActionEvent event) {
        Utilisateur user = null;
        String encPass = su.encrypt(PasswordTexte.getText());
        if (su.Authentification(LoginTexte.getText(), encPass) != null) {

            System.out.println(encPass);
            user = su.Authentification(LoginTexte.getText(), encPass);
            Utilisateur.user_connecter = user;
            if (null != user.getRole()) {
                if (user.getEtat().equals("desactive")) {
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setTitle("Alert");
                    al.setContentText("Votre account est suspendue, Veuillez contacter l admininstrateur");
                    al.setHeaderText(null);
                    al.show();
                } else {
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

        } else {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Alert");
            al.setContentText("invalid login or mot de passe");
            al.setHeaderText(null);
            al.show();

        }

    }

    @FXML
    private void SignUpPage(ActionEvent event) {
         
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/SignUpFXML.fxml"));
            Parent root = loader.load();
            LabelHelloFriend.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }

    @FXML
    private void goToPasswordReset(ActionEvent event) {
        
        if (null != su.FindByLogin(LoginTexte.getText())) {
            
                Utilisateur.user_connecter= su.FindByLogin(LoginTexte.getText());

              try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/MotDePasseFXML.fxml"));
            Parent root = loader.load();
            LabelHelloFriend.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            
        }else{
              Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Alert");
            al.setContentText("invalid login");
            al.setHeaderText(null);
            al.show();
            
            
            
        }
        
        
        
        
        
        
        
        
        
        
        
      
        
    }

}
