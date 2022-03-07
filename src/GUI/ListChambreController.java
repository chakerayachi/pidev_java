/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Chambre;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.ServiceChambreIPM;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class ListChambreController implements Initializable {
    
    ServiceChambreIPM s = new ServiceChambreIPM();
    
    ObservableList list;

    @FXML
    private TableView<Chambre> tableView;
    @FXML
    private TableColumn<Chambre, String> type;
    @FXML
    private TableColumn<Chambre, Float> prix;
    @FXML
    private TableColumn<Chambre, String> editcol;
    @FXML
    private TextField txtMaisonSearch;
    @FXML
    private Button refresh;
    @FXML
    private ImageView logo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichage();
       
    }    
    
    private void affichage(){
        List chambres = s.afficher();
        list = FXCollections.observableArrayList(chambres);
        tableView.setItems(list);
        //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        
        


                //add cell of button edit 
         Callback<TableColumn<Chambre, String>, TableCell<Chambre, String>> cellFoctory = (TableColumn<Chambre, String> param) -> {
            // make cell containing buttons
            final TableCell<Chambre, String> cell = new TableCell<Chambre, String>() {
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
                        
                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Annuler une réservation");
                                alert.setHeaderText("Vous voulez vraiment supprimer cette chambre ?");
                                Optional<ButtonType> option = alert.showAndWait();
                                if (option.get() == ButtonType.OK) {
                            Chambre chambre = tableView.getSelectionModel().getSelectedItem();
                            s.delete(chambre.getId());
                            
                                }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Chambre chambre = tableView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../GUI/ModifierChambre.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ListHotelController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            ModifierChambreController s = loader.getController();
                            s.setTextField(chambre.getId(),chambre.getType(), chambre.getPrix());
                        
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
		// 5. Add sorted (and filtered) data to the table.
	tableView.setItems(list);
    }
    
    @FXML
    private void refresh() {
        list.clear();    
        tableView.getItems().clear(); 
        affichage();
        
    }
    
   
    
}
