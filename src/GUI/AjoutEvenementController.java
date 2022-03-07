/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
import entities.Utilisateur;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JFileChooser;
import services.ServiceEvenementIMP;

/**
 * FXML Controller class
 *
 * @author Sabri
 */
public class AjoutEvenementController implements Initializable {
    
    ServiceEvenementIMP s = new ServiceEvenementIMP();

    @FXML
    private TextField libelle;
    @FXML
    private TextArea description;
    @FXML
    private TextField emplacement;
    @FXML
    private TextField nbPlaces;
    @FXML
    private TextField duree;
    @FXML
    private Button ajouterBtn;
    @FXML
    private DatePicker date;
    @FXML
    private Button parcourir;
    @FXML
    private ImageView imagev;
    @FXML
    private TextField img;
    @FXML
    private ImageView retour;
    Utilisateur userConn = Utilisateur.user_connecter;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  

    @FXML
    private void Insert(javafx.event.ActionEvent event) throws IOException {
             Evenement e = new Evenement();

        if ( validateFields() && validateNumber())
        
        {
        e.setLibelle(libelle.getText());
        e.setDescription(description.getText());
        e.setEmplacement(emplacement.getText());
        e.setNb_place(Integer.parseInt(nbPlaces.getText()));
        e.setDuree(Integer.parseInt(duree.getText()));
        e.setDate(Date.valueOf(date.getValue())) ;
        e.setId_user(32);
        //e.setId_user(userConn.getId());

        e.setImage(img.getText());
        int id_event = s.add_event(e);
        
        AjoutTicketController ticketController = new AjoutTicketController();
        ticketController.event_id= id_event;
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès!");
            alert.setHeaderText(null);
            alert.setContentText("L'évènement "+e.getLibelle()+" est ajouté avec succès");
            alert.showAndWait();
         Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutTicket.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
                 
                  } else if(validateFields()==false){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
                 else if(validateNumber()==false) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur!");
            alert.setHeaderText(null);
            alert.setContentText("Nombre de places ou/et Durée invalide(s)");
            alert.showAndWait();
                 }
       
    }
    
    
    private boolean validateNumber() {
        Pattern p = Pattern.compile("[0-9]+\\.[0-9]+|[0-9]+");
        Matcher m = p.matcher(duree.getText());
       Matcher m1 = p.matcher(nbPlaces.getText());

        if(m.find() && m.group().equals(duree.getText())&& m1.find() && m1.group().equals(nbPlaces.getText())) {
            return true;
        } else {
            return false;
        }
    }
     
   // Test de validation de saisie
    private boolean validateFields(){
        if(date.getValue() == null  ||  libelle.getText().isEmpty() || description.getText().isEmpty() || emplacement.getText().isEmpty() || nbPlaces.getText().isEmpty() || duree.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
          
    @FXML
    private void get_image(javafx.event.ActionEvent event) {
        JFileChooser chooser=new JFileChooser();
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        String filename=f.getAbsolutePath();
        img.setText(filename);
        Image imagee;
          try {
              imagee = new Image(new FileInputStream(filename));
           imagev.setImage(imagee);
          } catch (FileNotFoundException ex) {
              Logger.getLogger(AjoutEvenementController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @FXML
    private void affiche(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AffichageEvenement.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        
    }
    
}
    


   
