/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Maison;
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
import services.ServiceMaisonIPM;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class ModifierMaisonController implements Initializable {
    
    ServiceMaisonIPM s = new ServiceMaisonIPM();
    
    private int maisonId;

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
        Maison m = new Maison();
       
        
       m.setId(maisonId);
       m.setRegion(region.getText());
       m.setNum_tel(Integer.parseInt(tel.getText()));
       m.setAdresse(adresse.getText());
       m.setDescription(description.getText());
       m.setNb_chambres(Integer.parseInt(nbChambre.getText()));
       m.setPrix(Float.parseFloat(nbChambre.getText()));
       
     
        
        s.modify(m);
        
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListMaison.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
    void setTextField(int id, int htel, String madresse, String mdescription, String mregion, int mnbchambre, int mcapacite, float mprix) {

        maisonId = id;
        adresse.setText(madresse);
        region.setText(mregion);
        tel.setText(Integer.toString(htel));
        description.setText(mdescription);
        nbChambre.setText(Integer.toString(mnbchambre));
        capacite.setText(Integer.toString(mcapacite));
        prix.setText(Float.toString(mprix));

    }
    
}
