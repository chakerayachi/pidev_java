/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Evenement;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ServiceEvenementIMP;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class ClientEvenementController implements Initializable {
    
    ObservableList<String> empList = FXCollections.observableArrayList("Nabeul", "Tunis", "Zaghouan", "Ariana", "Benzart", "Beja", "Jendouba", "Kef", "Siliana", "Kairouan", "Soussa", "Monastir", "Mahdia", "Sfax", "Sidi Bouzid", "Gafsa", "Tozeur", "Kebili", "Gabès", "Medenine", "Tataouine");
    ServiceEvenementIMP s = new ServiceEvenementIMP();
    List<Evenement> evenements = new ArrayList<>();

    @FXML
    private GridPane evenementContainer;
    @FXML
    private ChoiceBox<String> emplacement;
    @FXML
    private ImageView retour;
    @FXML
    private ScrollPane vvbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            emplacement.setValue("Bonjour"); 
            emplacement.setItems(empList);
            affich();
            emplacement.setOnAction((event)->{
                evenementContainer.getChildren().clear();
                int col = 0;
                int row = 1;
                evenements = s.afficherEmp(emplacement.getSelectionModel().getSelectedItem());
                try {
                for (Evenement e:evenements){
                    
                    Label a = new Label();
                    a.setPadding(new Insets(30,0,0,130));
                    a.setId("voir");

                    FXMLLoader fxmlloader = new FXMLLoader();
                    fxmlloader.setLocation(getClass().getResource("EvenementCard.fxml"));
                    VBox eventBox = fxmlloader.load();
                    EvenementCardController hc = fxmlloader.getController();
                    hc.setData(e);  
                    eventBox.getChildren().add(a);
                    a.setText("voir");
                    a.setOnMouseClicked((event2) ->  {
                        AffichageEvenementClientController c = new AffichageEvenementClientController();
                        c.even=e;
                        FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../GUI/AffichageEvenementClient.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ClientEvenementController.class.getName()).log(Level.SEVERE, null, ex);
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
                    evenementContainer.add(eventBox, col++, row);
                    GridPane.setMargin(eventBox, new Insets(10));
                } 
                }catch (IOException ex) {
                        Logger.getLogger(ClientEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        
        }
    
    public void affich(){
        int col = 0;
        int row = 1;
        evenements = s.afficher();
        try {
        for (Evenement e:evenements){
            System.out.print(e.getImage());
            
            Label b = new Label();
            b.setPadding(new Insets(30,0,0,130));
            b.setId("voir");

            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("EvenementCard.fxml"));
            VBox evenementBox = fxmlloader.load();
            EvenementCardController hc = fxmlloader.getController();
            hc.setData(e);  
            evenementBox.getChildren().add(b);
            b.setText("voir plus");
            b.setOnMouseClicked((event) ->  {
                            
                try {
                    AffichageEvenementClientController dc = new AffichageEvenementClientController();
                    dc.even=e;
                    
                    Parent root = FXMLLoader.load(getClass().getResource("../GUI/AffichageEvenementClient.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("../GUI/Styling.css").toExternalForm());
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ClientEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            if(col == 4){
                col = 0;
                row++;
            }
            evenementContainer.add(evenementBox, col++, row);
            GridPane.setMargin(evenementBox, new Insets(10));
        } 
        }catch (IOException ex) {
                Logger.getLogger(ClientEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void accueil(MouseEvent event) {
        try {
                    Parent root = FXMLLoader.load(getClass().getResource("../GUI/Accueil.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("../GUI/Styling.css").toExternalForm());
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ClientEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }   
        
}    