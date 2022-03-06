/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Maison;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
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
import services.ServiceMaisonIPM;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class ListMaisonController implements Initializable {
    
    ServiceMaisonIPM s = new ServiceMaisonIPM();
    
    ObservableList list;

    @FXML
    private TextField txtMaisonSearch;
    @FXML
    private TableView<Maison> tableView;
    @FXML
    private TableColumn<Maison, String> adresse;
    @FXML
    private TableColumn<Maison, String> region;
    @FXML
    private TableColumn<Maison, Integer> tel;
    @FXML
    private TableColumn<Maison, String> description;
    @FXML
    private TableColumn<Maison, String> capacite;
    @FXML
    private TableColumn<Maison, Integer> nbChambre;
    @FXML
    private TableColumn<Maison, Float> prix;
    @FXML
    private TableColumn<Maison, String> editcol;
    @FXML
    private Button refresh;
    @FXML
    private ImageView logo;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        affichage();
    }    
    
    public void affichage(){
        List maisons = s.afficher();
        list = FXCollections.observableArrayList(maisons);
        tableView.setItems(list);
        //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        region.setCellValueFactory(new PropertyValueFactory<>("region"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        nbChambre.setCellValueFactory(new PropertyValueFactory<>("nb_chambres"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        
        
        
         FilteredList<Maison> filteredData = new FilteredList<>(list, b -> true);
		
		txtMaisonSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Hotel -> {
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Hotel.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter 
				}else if (Hotel.getRegion().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter 
				}
				else if (Hotel.getDescription().toLowerCase().indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; 
			});
		});
		
		SortedList<Maison> sortedData = new SortedList<>(filteredData);
		
	
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
		tableView.setItems(sortedData);
		
		
		txtMaisonSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Hotel -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Hotel.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				}else if (Hotel.getRegion().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; 
				}
				else if (Hotel.getDescription().toLowerCase().indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; 
			});
		});
		
		
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
                
                
                       //add cell of button edit 
         Callback<TableColumn<Maison, String>, TableCell<Maison, String>> cellFoctory = (TableColumn<Maison, String> param) -> {
            // make cell containing buttons
            final TableCell<Maison, String> cell = new TableCell<Maison, String>() {
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
                                alert.setHeaderText("Vous voulez vraiment supprimer ce maison ?");
                                Optional<ButtonType> option = alert.showAndWait();
                                if (option.get() == ButtonType.OK) {
                            Maison maison = tableView.getSelectionModel().getSelectedItem();
                            s.delete(maison.getId());
                            
                                }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Maison maison = tableView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../GUI/ModifierMaison.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ListMaisonController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            ModifierMaisonController s = loader.getController();
                            s.setTextField(maison.getId(), maison.getNum_tel(),maison.getAdresse(),maison.getDescription(), maison.getRegion(), maison.getNb_chambres(), maison.getCapacite(), maison.getPrix());
                        
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
		tableView.setItems(sortedData);
    }

    @FXML
    private void refresh(ActionEvent event) {
        list.clear();    
        tableView.getItems().clear(); 
        affichage();
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutMaison.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
}
