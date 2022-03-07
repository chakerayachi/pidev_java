/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Hotel;
import entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import services.ServiceHotelIPM;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class AjoutHotelController implements Initializable {

    @FXML
    private TextField ville;
    @FXML
    private TextField region;
    @FXML
    private TextField tel;
    @FXML
    private TextField libelle;
    @FXML
    private TextField etoile;
    @FXML
    private TextArea adresse;
    @FXML
    private TextArea description;
    @FXML
    private Button ajouterBtn;
    
    ServiceHotelIPM s = new ServiceHotelIPM();
    @FXML
    private ImageView logo;
    @FXML
    private TextField imgField;
    @FXML
    private Button upload;
    
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
            
            Hotel h = new Hotel();
            h.setAdresse(adresse.getText());
            h.setVille(ville.getText());
            h.setRegion(region.getText());
            h.setNum_tel(Integer.parseInt(tel.getText()));
            h.setLibelle(libelle.getText());
            h.setNb_etoile(Integer.parseInt(etoile.getText()));
            h.setDescription(description.getText());
            h.setImage(imgField.getText());
            h.setVille(ville.getText());
            
            // id user connecter 
             h .setId_user(userConn.getId());
            int id_h=s.create(h);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès!");
            alert.setHeaderText(null);
            alert.setContentText("Hotel ajouté avec succès");
            alert.showAndWait();
            
            AjoutChambreController ac = new AjoutChambreController();
            ac.id_hotel=id_h;
            
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutChambre.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
       
            } 
        else if(control()==false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur Validation!");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
            
        }
        
        
     
        
    
    private boolean control(){
        if(adresse.getText().isEmpty() || ville.getText().isEmpty() ||  region.getText().isEmpty()|| tel.getText().isEmpty() || libelle.getText().isEmpty() || etoile.getText().isEmpty() || description.getText().isEmpty() || ville.getText().isEmpty()) {
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
