/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Maison;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import services.ServiceMaisonIPM;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class ClientMaisonController implements Initializable {
       ObservableList<String> regionList = FXCollections.observableArrayList("Korba", "Hammamet", "Tunis", "Carthage", "Monastir", "Sousse", "Djerba", "Mahdia", "Tozeur", "Tabarka", "Sfax", "Gabes");
       ServiceMaisonIPM s = new ServiceMaisonIPM();
       List<Maison> maisons ;
    @FXML
    private GridPane maisonContainer;
    @FXML
    private ChoiceBox<String> region;
    @FXML
    private Button retour;
    @FXML
    private Button maisonAc;
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        region.setValue("Bonjour"); 
        region.setItems(regionList);
            affiche();
        
            
        region.setOnAction((event)->{
        maisonContainer.getChildren().clear();
                maisons = new ArrayList<>();
    
                    int col = 0;
                    int row = 1;
                    maisons = s.afficherParRegion((String) region.getSelectionModel().getSelectedItem());
                    
                    try {
                    for (Maison m:maisons){
                        Label a = new Label();
                        a.setPadding(new Insets(30,0,0,130));
                        a.setId("voir");
                        a.setStyle("-fx-text-fill:WHITE; -fx-font-size: 20; -fx-font-weight: bold;");

                        FXMLLoader fxmlloader = new FXMLLoader();
                        fxmlloader.setLocation(getClass().getResource("MaisonCard.fxml"));
                        VBox maisonBox = fxmlloader.load();
                        MaisonCardController hc = fxmlloader.getController();
                        hc.setData(m);  
                        maisonBox.getChildren().add(a);
                        a.setText("voir");
                        a.setOnMouseClicked((event2) ->  {

                            try {
                                MaisonDetailsController dc = new MaisonDetailsController();
                                dc.maison=m;

                                Parent root = FXMLLoader.load(getClass().getResource("../GUI/MaisonDetails.fxml"));
                                Scene scene = new Scene(root);
                                Stage stage = (Stage) ((Node) event2.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(ClientMaisonController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        if(col == 3){
                            col = 0;
                            row++;
                        }
                        maisonContainer.add(maisonBox, col++, row);
                        GridPane.setMargin(maisonBox, new Insets(10));
                    } 
                    }catch (IOException ex) {
                            Logger.getLogger(ClientMaisonController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                                    });
            
        
    }
    
    
    public void affiche(){
        
        maisons = new ArrayList<>();
    
        int col = 0;
        int row = 1;
        maisons = s.afficher();
        try {
                for (Maison m:maisons){
                    Label b = new Label();
                    b.setPadding(new Insets(30,0,0,130));
                    b.setId("voir");
                    b.setStyle("-fx-text-fill:WHITE; -fx-font-size: 20; -fx-font-weight: bold;");

                    FXMLLoader fxmlloader = new FXMLLoader();
                    fxmlloader.setLocation(getClass().getResource("MaisonCard.fxml"));
                    VBox maisonBox = fxmlloader.load();
                    MaisonCardController hc = fxmlloader.getController();
                    hc.setData(m);  
                    maisonBox.getChildren().add(b);
                    b.setText("voir");
                    b.setOnMouseClicked((event) ->  {

                        try {
                            MaisonDetailsController dc = new MaisonDetailsController();
                            dc.maison=m;

                            Parent root = FXMLLoader.load(getClass().getResource("../GUI/MaisonDetails.fxml"));
                            Scene scene = new Scene(root);
                            //scene.getStylesheets().add(getClass().getResource("../GUI/Stylesheet.css").toExternalForm());
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ClientMaisonController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    if(col == 3){
                        col = 0;
                        row++;
                    }
                    maisonContainer.add(maisonBox, col++, row);
                    GridPane.setMargin(maisonBox, new Insets(10));
                } 
        }catch (IOException ex) {
                Logger.getLogger(ClientMaisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ClientMaison.fxml"));
                            Scene scene = new Scene(root);
                            scene.getStylesheets().add(getClass().getResource("../GUI/Stylesheet.css").toExternalForm());
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
    }

    @FXML
    private void maisonAc(ActionEvent event) {
        maisonContainer.getChildren().clear();
                maisons = new ArrayList<>();
    
                    int col = 0;
                    int row = 1;
                    maisons = s.afficherMaisonAccueil();
                    
                    try {
                    for (Maison m:maisons){
                        Label a = new Label();
                        a.setPadding(new Insets(30,0,0,130));
                        a.setId("voir");
                        a.setStyle("-fx-text-fill:WHITE; -fx-font-size: 20; -fx-font-weight: bold;");

                        FXMLLoader fxmlloader = new FXMLLoader();
                        fxmlloader.setLocation(getClass().getResource("MaisonCard.fxml"));
                        VBox maisonBox = fxmlloader.load();
                        MaisonCardController hc = fxmlloader.getController();
                        hc.setData(m);  
                        maisonBox.getChildren().add(a);
                        a.setText("voir");
                        a.setOnMouseClicked((event2) ->  {

                            try {
                                MaisonDetailsController dc = new MaisonDetailsController();
                                dc.maison=m;

                                Parent root = FXMLLoader.load(getClass().getResource("../GUI/MaisonDetails.fxml"));
                                Scene scene = new Scene(root);
                                Stage stage = (Stage) ((Node) event2.getSource()).getScene().getWindow();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(ClientMaisonController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        if(col == 3){
                            col = 0;
                            row++;
                        }
                        maisonContainer.add(maisonBox, col++, row);
                        GridPane.setMargin(maisonBox, new Insets(10));
                    } 
                    }catch (IOException ex) {
                            Logger.getLogger(ClientMaisonController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                                    
    }
    

    
    
}
