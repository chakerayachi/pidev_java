/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceEvenementIMP;

/**
 * FXML Controller class
 *
 * @author Sabri
 */
public class UpdateEvenementController implements Initializable {
    
    ServiceEvenementIMP s = new ServiceEvenementIMP();
    @FXML
    private Button updateBtn;
    @FXML
    private TextField libelle;
    @FXML
    private TextField emplacement;
    @FXML
    private TextField nbPlaces;
    @FXML
    private TextField duree;
    @FXML
    private DatePicker date;
    @FXML
    private TextArea description;
    
    private int idEven;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    void setTextField(int id, String lib, String emp, int nbp, int dure, String desc) {

        idEven = id;
        libelle.setText(lib);
        emplacement.setText(emp);
        nbPlaces.setText(Integer.toString(nbp));
        duree.setText(Integer.toString(dure));
        //date.setText(LOCAL_DATE(d));
        description.setText(desc);

    }
    

    @FXML
    private void update(ActionEvent event) {
        Evenement e = new Evenement();
        e.setId(idEven);
        e.setLibelle(libelle.getText());
        e.setEmplacement(emplacement.getText());
        e.setNb_place(Integer.parseInt(nbPlaces.getText()));
        e.setDuree(Integer.parseInt(duree.getText()));
        e.setDescription(description.getText());
        
        
        s.modify(e);
    }
    
    @FXML
    private void affiche(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AffichageEvenement.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
}
