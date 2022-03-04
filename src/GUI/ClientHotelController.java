/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Hotel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ServiceHotelIPM;
import java.awt.Color;
import java.awt.FlowLayout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
 
import javax.swing.BorderFactory;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class ClientHotelController implements Initializable {
    ObservableList<String> regionList = FXCollections.observableArrayList("Korba", "Hammamet", "Tunis", "Carthage", "Monastir", "Sousse", "Djerba", "Mahdia", "Tozeur", "Tabarka", "Sfax", "Gabes");
    ServiceHotelIPM s = new ServiceHotelIPM();
    List<Hotel> hotels = new ArrayList<>();
    
    @FXML
    private GridPane hotelContainer;
    @FXML
    private ChoiceBox<String> region;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            region.setValue("Bonjour"); 
            region.setItems(regionList);
            affich();
            region.setOnAction((event)->{
                hotelContainer.getChildren().clear();
                int col = 0;
                int row = 1;
                hotels = s.afficherHotelsParNbRegion(region.getSelectionModel().getSelectedItem());
                try {
                for (Hotel h:hotels){
                    Label a = new Label();
                    a.setPadding(new Insets(30,0,0,130));
                    a.setId("voir");

                    FXMLLoader fxmlloader = new FXMLLoader();
                    fxmlloader.setLocation(getClass().getResource("HotelCard.fxml"));
                    VBox hotelBox = fxmlloader.load();
                    HotelCardController hc = fxmlloader.getController();
                    hc.setData(h);  
                    hotelBox.getChildren().add(a);
                    a.setText("voir");
                    a.setOnMouseClicked((event2) ->  {

                        try {
                            HotelDetailsController dc = new HotelDetailsController();
                            dc.hot=h;

                            Parent root = FXMLLoader.load(getClass().getResource("../GUI/HotelDetails.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ClientHotelController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    if(col == 3){
                        col = 0;
                        row++;
                    }
                    hotelContainer.add(hotelBox, col++, row);
                    GridPane.setMargin(hotelBox, new Insets(10));
                } 
                }catch (IOException ex) {
                        Logger.getLogger(ClientHotelController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        
        }
    
    public void affich(){
        int col = 0;
        int row = 1;
        hotels = s.afficher();
        try {
        for (Hotel h:hotels){
            Label b = new Label();
            b.setPadding(new Insets(30,0,0,130));
            b.setId("voir");

            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("HotelCard.fxml"));
            VBox hotelBox = fxmlloader.load();
            HotelCardController hc = fxmlloader.getController();
            hc.setData(h);  
            hotelBox.getChildren().add(b);
            b.setText("voir");
            b.setOnMouseClicked((event) ->  {
                            
                try {
                    HotelDetailsController dc = new HotelDetailsController();
                    dc.hot=h;
                    
                    Parent root = FXMLLoader.load(getClass().getResource("../GUI/HotelDetails.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ClientHotelController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            if(col == 3){
                col = 0;
                row++;
            }
            hotelContainer.add(hotelBox, col++, row);
            GridPane.setMargin(hotelBox, new Insets(10));
        } 
        }catch (IOException ex) {
                Logger.getLogger(ClientHotelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
}    
    

