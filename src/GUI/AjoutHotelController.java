/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Hotel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void Insert(ActionEvent event) {
        Hotel h = new Hotel();
        h.setAdresse(adresse.getText());
        h.setVille(ville.getText());
        h.setRegion(region.getText());
        h.setNum_tel(Integer.parseInt(tel.getText()));
        h.setLibelle(libelle.getText());
        h.setNb_etoile(Integer.parseInt(etoile.getText()));
        h.setDescription(description.getText());
        h.setVille(ville.getText());
        h.setId_user(1);
     
        
        s.create(h);
    }
    
}
