/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import services.ServiceReclamationIMP;
import services.ServicesVoitureIMP;

/**
 * FXML Controller class
 *
 * @author Hani
 */
public class ReclamationController implements Initializable {

private String[] rec ={"technique","graphique","service","autre"};
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private Button envoi;
    @FXML
    private TextArea description;
    ServiceReclamationIMP sc = new ServiceReclamationIMP();
        /**
     * Initializes the controller class.
     */
  
     Date date1 = new Date();
        String account_date = new SimpleDateFormat("yyyy-MM-dd").format(date1);
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().addAll(rec);
        
    }    

    @FXML
    private void ajout(ActionEvent event) {
        Reclamation re =  new Reclamation();
        re.setDiscreption(description.getText());
        re.setType(type.getValue());
        re.setDate(account_date);
        sc.ajout(re);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès!");
            alert.setHeaderText(null);
            alert.setContentText("merci pour votre ");
            alert.showAndWait();
        
    }

   
    
}
