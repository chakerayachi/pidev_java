/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Hotel;
import entities.Maison;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ServiceHotelIPM;
import services.ServiceMaisonIPM;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class AjoutMaisonController implements Initializable {
    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void Insert(ActionEvent event) {
        Maison m = new Maison();
        m.setAdresse(adresse.getText());
        m.setRegion(region.getText());
        m.setNum_tel(Integer.parseInt(tel.getText()));
        m.setPrix(Float.parseFloat(prix.getText()));
        m.setCapacite(Integer.parseInt(capacite.getText()));
        m.setDescription(description.getText());
        m.setNb_chambres(Integer.parseInt(nbChambre.getText()));
        m.setId_user(1);
        
        
        s.create(m);
    }
    
}
