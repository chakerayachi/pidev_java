/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controllers.MenuController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.ServiceEvenementIMP;

/**
 * FXML Controller class
 *
 * @author Sabri
 */
public class AffichageEvenementController implements Initializable {
    ServiceEvenementIMP ev = new ServiceEvenementIMP();
    
    ObservableList list;
    
@FXML
    private TableView<Evenement> id_table;
@FXML
    private TableColumn<Evenement, Integer> id;
@FXML
    private TableColumn<Evenement, String> libelle;
@FXML
    private TableColumn<Evenement, Date> date;
@FXML
    private TableColumn<Evenement, String> description;
@FXML
    private TableColumn<Evenement, String> emplacement;
@FXML
    private TableColumn<Evenement, Integer> nb_places;
@FXML
    private TableColumn<Evenement, Integer> duree;
@FXML
    private TableColumn<Evenement, String> editcol;
@FXML
    private Button listeT;
    @FXML
    private ImageView estimation;
    @FXML
    private FontAwesomeIconView backbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficher();
    }
    
    private void afficher(){
        List<Evenement> e = ev.afficher();

        list = FXCollections.observableArrayList(e);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        emplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
        nb_places.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
        duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
        
        
        Callback<TableColumn<Evenement, String>, TableCell<Evenement, String>> cellFoctory = (TableColumn<Evenement, String> param) -> {
            // make cell containing buttons
            final TableCell<Evenement, String> cell = new TableCell<Evenement, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        
                        
                        deleteIcon.setOnMouseClicked((event) -> {
                            Evenement e = id_table.getSelectionModel().getSelectedItem();
                            ev.delete(e.getId());
                            
 

                        });
                        editIcon.setOnMouseClicked((event) -> {
                            
                            Evenement e = id_table.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../GUI/UpdateEvenement.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AffichageEvenementController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            UpdateEvenementController s = loader.getController();
                            s.setTextField(e.getId(), e.getLibelle(), e.getEmplacement(), e.getNb_place(), e.getDuree(), e.getDescription());
                        
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        editcol.setCellFactory(cellFoctory);
        
        
          id_table.setItems(list); 
    }
    
    @FXML
    private void actualiser() {
        list.clear();    
        id_table.getItems().clear(); 
        afficher();
    }
    
    @FXML
    private void affiche(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutEvenement.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();

        }
    
    @FXML
    private void affiche2(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AffichageTicket.fxml"));
		Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("../GUI/Styling.css").toExternalForm());
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        
    }
    
    @FXML
    private void affiche3(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/EstimationEvenement.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();

        }

    @FXML
    private void backtopic(MouseEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/menuback.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../GUI/Stylesheet.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageEvenementClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   }
