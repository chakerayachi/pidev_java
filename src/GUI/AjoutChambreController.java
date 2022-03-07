/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Chambre;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ServiceChambreIPM;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class AjoutChambreController implements Initializable {
    ServiceChambreIPM s = new ServiceChambreIPM();
    public static int id_hotel;
    
   ObservableList<String> typeList = FXCollections.observableArrayList("Single", "Double", "Triple", "Quadruple");

    @FXML
    private ChoiceBox type;
    @FXML
    private TextField prix;
    @FXML
    private Button ajouterBtn;
    @FXML
    private ImageView logo;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.setValue("Single"); 
        type.setItems(typeList);
        System.out.print("idHotel :"+id_hotel);
    }    
    
    @FXML
    private void Insert(ActionEvent event) throws IOException {
        
                Chambre c = new Chambre();

                c.setType((String) type.getSelectionModel().getSelectedItem());
                c.setPrix(Float.parseFloat(prix.getText()));
                c.setId_hotel(id_hotel);



                int id=s.create(c);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.setTitle("Ajout chambre");
                                        alert.setHeaderText("Vous voulez ajouter une autre chambre ?");
                                        Optional<ButtonType> option = alert.showAndWait();
                                        if (option.get() == ButtonType.OK) {

                                            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutChambre.fxml"));
                                                    Scene scene = new Scene(root);
                                                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                                    stage.setScene(scene);
                                                    stage.show();
                                        }else{
                                            Parent root = FXMLLoader.load(getClass().getResource("../GUI/ListHotel.fxml"));
                                                    Scene scene = new Scene(root);
                                                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                                    stage.setScene(scene);
                                                    stage.show();
                                        }
        }
        
    }
    
   

