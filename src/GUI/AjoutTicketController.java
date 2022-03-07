/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
import entities.Ticket;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceEvenementIMP;
import services.ServiceTicketIMP;

/**
 * FXML Controller class
 *
 * @author Sabri
 */
public class AjoutTicketController implements Initializable {
    ObservableList<String> typeList = FXCollections.observableArrayList("Standard", "VIP");
    
    ServiceTicketIMP s = new ServiceTicketIMP();

    @FXML
    private Button ajouterBtn;
    @FXML
    private ChoiceBox type;
    @FXML
    private TextField prixStandard;
    @FXML
    private TextField prixVIP;
    public static int event_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    @FXML
    private void Insert(javafx.event.ActionEvent event) throws IOException {
        if(validateFields() && validateNumber()){
        Ticket t = new Ticket();
        t.setType("STANDARD");
        t.setPrix(Integer.parseInt(prixStandard.getText()));
        t.setId_evenement(event_id);
        s.add_ticket(t);
        Ticket t1 = new Ticket();
        t1.setType("VIP");
        t1.setPrix(Integer.parseInt(prixVIP.getText()));
        t1.setId_evenement(event_id);
        s.add_ticket(t1);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès!");
            alert.setHeaderText(null);
            alert.setContentText("Les tickets ont été ajoutés avec succès");
            alert.showAndWait();
         Parent root = FXMLLoader.load(getClass().getResource("../GUI/AffichageEvenement.fxml"));
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
            alert.setContentText("Prix VIP ou/et Prix Standard invalide(s)");
            alert.showAndWait();
                 }
        
       
}
          private boolean validateNumber() {
        Pattern p = Pattern.compile("[0-9]+\\.[0-9]+|[0-9]+");
        Matcher m = p.matcher(prixVIP.getText());
        Matcher m1 = p.matcher(prixStandard.getText());

        if(m.find() && m.group().equals(prixVIP.getText())&& m1.find() && m1.group().equals(prixStandard.getText())) {
            return true;
        } else {
            return false;
        }
    }
     
   // Test de validation de saisie
    private boolean validateFields(){
        if(prixVIP.getText() == null  ||  prixStandard.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
}

    
    
  