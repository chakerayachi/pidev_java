/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controllers.MenuController;
import GUI.HotelCardController;
import GUI.HotelDetailsController;
import GUI.ListHotelController;
import entities.Hotel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ServiceHotelIPM;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class ClientHotelController implements Initializable {
    ObservableList<String> regionList = FXCollections.observableArrayList("Korba", "Hammamet", "Tunis", "Carthage", "Monastir", "Sousse", "Djerba", "Mahdia", "Tozeur", "Tabarka", "Sfax", "Gabes");
    ObservableList<String> etoileList = FXCollections.observableArrayList("1", "2", "3" ,"4", "5");
    
    String cssLayout = "-fx-font: bold 11px Arial;;\n" +
                                        "-fx-text-decoration: none;\n" +
                                        "-fx-background-color: #EEEEEE;\n" +
                                        "-fx-color: #333333;\n" +
                                        "-fx-padding: 2px 6px 2px 6px;;\n"+
                                        "-fx-border-right: 1px solid #333333;\n" +
                                        "-fx-border-bottom: 1px solid #333333;\n" +
                                        "-fx-border-left: 1px solid #CCCCCC;\n" ;

    ServiceHotelIPM s = new ServiceHotelIPM();
    List<Hotel> hotels = new ArrayList<>();
    @FXML
    private GridPane hotelContainer;
    @FXML
    private ChoiceBox<String> region;
    @FXML
    private Button retour;
    @FXML
    private ChoiceBox<String> etoile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           region.setValue("Tout"); 
            region.setItems(regionList);
            etoile.setValue("Tout"); 
            etoile.setItems(etoileList);
            affich();
            
            //recherche par region event
            region.setOnAction((event)->{
                hotelContainer.getChildren().clear();
                int col = 0;
                int row = 1;
                hotels = s.afficherHotelsParNbRegion(region.getSelectionModel().getSelectedItem());
                try {
                for (Hotel h:hotels){
                    Label a = new Label();
                    a.setPadding(new Insets(30,0,0,130));
                    //a.setStyle("-fx-border-color: white;");
                    a.setId("voir");
                    a.setStyle("-fx-text-fill:WHITE; -fx-font-size: 20; -fx-font-weight: bold;");

                    FXMLLoader fxmlloader = new FXMLLoader();
                    fxmlloader.setLocation(getClass().getResource("HotelCard.fxml"));
                    VBox hotelBox = fxmlloader.load();
                    HotelCardController hc = fxmlloader.getController();
                    hc.setData(h);  
                    hotelBox.getChildren().add(a);
                    a.setText("voir");
                    a.setOnMouseClicked((event2) ->  {
                        HotelDetailsController dc = new HotelDetailsController();
                        dc.hot=h;
                        FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../GUI/HotelDetails.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ListHotelController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
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
            
            //Recherche par nbEtoile event
            etoile.setOnAction((event)->{
                
                hotelContainer.getChildren().clear();
                int col = 0;
                int row = 1;
                hotels = s.afficherHotelsParNbEtoile(Integer.parseInt(etoile.getSelectionModel().getSelectedItem()));
                try {
                for (Hotel h:hotels){
                    Label a = new Label();
                    

          
                    a.setPadding(new Insets(30,0,0,130));
                    a.setId("voir");
                    a.setStyle("-fx-text-fill:WHITE; -fx-font-size: 20; -fx-font-weight: bold;");

                    FXMLLoader fxmlloader = new FXMLLoader();
                    fxmlloader.setLocation(getClass().getResource("HotelCard.fxml"));
                    VBox hotelBox = fxmlloader.load();
                    HotelCardController hc = fxmlloader.getController();
                    hc.setData(h);  
                    hotelBox.getChildren().add(a);
                    a.setText("voir");
                    a.setOnMouseClicked((event2) ->  {
                        HotelDetailsController dc = new HotelDetailsController();
                        dc.hot=h;
                        FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../GUI/HotelDetails.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ListHotelController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
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
                
                b.setPadding(new Insets(30,40,0,130));
                b.setId("voir");
                b.setStyle("-fx-text-fill:WHITE; -fx-font-size: 20; -fx-font-weight: bold;");
                
                b.setAlignment(Pos.CENTER);
                b.setWrapText(true);
                
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
                        scene.getStylesheets().add(getClass().getResource("../GUI/Stylesheet.css").toExternalForm());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } 
                    catch (IOException ex) {
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

    @FXML
    private void retour(ActionEvent event) throws IOException {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/menu.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../GUI/Stylesheet.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ClientHotelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

        
        
}    