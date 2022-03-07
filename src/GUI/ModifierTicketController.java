/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Ticket;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sabri
 */
public class ModifierTicketController implements Initializable {
     ObservableList<String> typeList = FXCollections.observableArrayList("Standard", "VIP");
    @FXML
    private TextField prix;
    private int idTicket;
    @FXML
    private ChoiceBox type;
    services.ServiceTicketIMP s = new services.ServiceTicketIMP();
    @FXML
    private Button modifierBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.setValue("Standard"); 
        type.setItems(typeList);    
    }    

    @FXML
    private void modifier(ActionEvent event) {
        Ticket t = new Ticket();
        t.setId(idTicket);
        t.setType((String) type.getSelectionModel().getSelectedItem());
        t.setPrix(Integer.parseInt(prix.getText()));
        
        s.modify(t);
    }
    void setTextField(int id, int tprix, String ttype) {
        idTicket = id;
        type.setValue(ttype);
        type.setItems(typeList);
        prix.setText(Integer.toString(tprix));
        
    }
    @FXML
    private void affiche(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AffichageTicket.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        
    }
    
}
