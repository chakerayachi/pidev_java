/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Hotel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceHotelIPM;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class ModifierHotelController implements Initializable {
    
    ServiceHotelIPM s = new ServiceHotelIPM();

    
    private int hotelId;
    private TextField ville;
    @FXML
    private TextField region;
    @FXML
    private TextField tel;
    private TextField libelle;
    private TextField etoile;
    @FXML
    private TextArea adresse;
    @FXML
    private TextArea description;
    @FXML
    private TextField nbChambre;
    @FXML
    private TextField capacite;
    @FXML
    private TextField prix;
    @FXML
    private Button modifierBtn;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    @FXML
    private void modifier(ActionEvent event) throws IOException {
       Hotel h = new Hotel();
       
        
       h.setId(hotelId);
       h.setVille(ville.getText());
       h.setRegion(region.getText());
       h.setNum_tel(Integer.parseInt(tel.getText()));
       h.setLibelle(libelle.getText());
       h.setNb_etoile(Integer.parseInt(etoile.getText()));
       h.setAdresse(adresse.getText());
       h.setDescription(description.getText());
       
     
        
        s.modify(h);
        
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListHotel.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
   void setTextField(int id, String hville, int htel, String hlibelle, int hnbetoile, String hadresse, String hdescription, String hregion) {

        hotelId = id;
        ville.setText(hville);
        tel.setText(Integer.toString(htel));
        libelle.setText(hlibelle);
        etoile.setText(Integer.toString(hnbetoile));
        adresse.setText(hadresse);
        description.setText(hdescription);
        region.setText(hregion);
        System.out.println(hotelId);

    }

    
    
    
}
