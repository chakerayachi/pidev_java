/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Maison;
import entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.stage.FileChooser;
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
    @FXML
    private Button upload;
    @FXML
    private TextField imgField;
    
    Utilisateur userConn = Utilisateur.user_connecter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
    
    
    @FXML
    private void Insert(ActionEvent event) throws IOException {
        if (control() && validateNumberphone()){
                Maison m = new Maison();
                m.setAdresse(adresse.getText());
                m.setRegion(region.getText());
                m.setNum_tel(Integer.parseInt(tel.getText()));
                m.setPrix(Float.parseFloat(prix.getText()));
                m.setCapacite(Integer.parseInt(capacite.getText()));
                m.setDescription(description.getText());
                m.setNb_chambres(Integer.parseInt(nbChambre.getText()));
                m.setImage(imgField.getText());
                //  m .setId_user(userConn.getId());
                m.setId_user(27);
                
                

                s.create(m);

                //  envoyer un mail de confirmation
                    try {
                        GUI.MaillingController.sendMail(userConn, " Confirmation  ", " Votre maison a ete bien publier sur notre apllication nous vous informer lors d une reservation du votre maison merci d'utiliser notre applicationss ");
                    } catch (Exception ex) {
                        Logger.getLogger(AjouterMaisonController.class.getName()).log(Level.SEVERE, null, ex);
                    }

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
        } else if(control()==false || validateNumberphone()==false){
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
    
    public boolean validateNumberphone() {

        Pattern p = Pattern.compile("[0-9]+\\.[0-9]+|[0-9]+");
        Matcher m = p.matcher(tel.getText());
        if (m.find() && m.group().equals(tel.getText()) && tel.getText().length() == 8) {
            return true;
        } else {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Alert");
            al.setContentText("Le numero du telephone doit etre 8 didgets");
            al.setHeaderText(null);
            al.show();

        }
        return false;
    }

    @FXML
    private void upload(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
           
            //new FileChooser.ExtensionFilter("IMAGE FILES", ".jpg", ".PNG")
    );


        File file = fileChooser.showOpenDialog(upload.getScene().getWindow());

        if (file != null) {
        // pickUpPathField it's your TextField fx:id
        imgField.setText(file.getPath());

        } 
        else  {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("image");
            alert.setHeaderText(null);
            alert.setContentText("soisir une image s'l vous plais");
            alert.showAndWait();
        }
    }
    
}
