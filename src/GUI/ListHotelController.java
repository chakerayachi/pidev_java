/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Hotel;
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
import services.ServiceHotelIPM;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class ListHotelController implements Initializable {
    
    ServiceHotelIPM s = new ServiceHotelIPM();
    ObservableList list;

    @FXML
    private TableView<Hotel> tableView;
    @FXML
    private TableColumn<Hotel, String> libelle;
    @FXML
    private TableColumn<Hotel, String> adresse;
    @FXML
    private TableColumn<Hotel, String> ville;
    @FXML
    private TableColumn<Hotel, String> region;
    @FXML
    private TableColumn<Hotel, Integer> tel;
    @FXML
    private TableColumn<Hotel, String> description;
    @FXML
    private TableColumn<Hotel, Integer> nbEtoile;
    @FXML
    private TextField txtHotelSearch;
    @FXML
    private TableColumn<Hotel, String> editcol;
    @FXML
    private Button refresh;
    @FXML
    private ImageView logo;
    @FXML
    private Button ajouterBtn;
    @FXML
    private FontAwesomeIconView backbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichage();
       
    }    
    
    private void affichage(){
        List hotels = s.afficher();
        list = FXCollections.observableArrayList(hotels);
        System.out.println(list);
        tableView.setItems(list);
        //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        region.setCellValueFactory(new PropertyValueFactory<>("region"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        nbEtoile.setCellValueFactory(new PropertyValueFactory<>("nb_etoile"));
        
        
        
        FilteredList<Hotel> filteredData = new FilteredList<>(list, b -> true);
		
		
		txtHotelSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Hotel -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare 
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Hotel.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter 
				} else if (Hotel.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter 
				}else if (Hotel.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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
		
		
		SortedList<Hotel> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
		
		tableView.setItems(sortedData);
                
		
		
		txtHotelSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Hotel -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Hotel.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter 
				} else if (Hotel.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter 
				}else if (Hotel.getVille().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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
		
		
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
                
                
                
                //add cell of button edit 
         Callback<TableColumn<Hotel, String>, TableCell<Hotel, String>> cellFoctory = (TableColumn<Hotel, String> param) -> {
            // make cell containing buttons
            final TableCell<Hotel, String> cell = new TableCell<Hotel, String>() {
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
                                alert.setHeaderText("Vous voulez vraiment supprimer ce hotel ?");
                                Optional<ButtonType> option = alert.showAndWait();
                                if (option.get() == ButtonType.OK) {
                                    Hotel hotel = tableView.getSelectionModel().getSelectedItem();
                                    s.delete(hotel.getId());
                                }
                            
 

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Hotel hotel = tableView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../GUI/ModifierHotel.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ListHotelController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            ModifierHotelController s = loader.getController();
                            s.setTextField(hotel.getId(),hotel.getVille(), hotel.getNum_tel(), hotel.getLibelle(), hotel.getNb_etoile(), hotel.getAdresse(),hotel.getDescription(), hotel.getRegion());
                        
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
    private void refresh() {
        list.clear();    
        tableView.getItems().clear(); 
        affichage();
        
    }
    
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutHotel.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        
    }

    @FXML
    private void back(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/menu.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../GUI/Stylesheet.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListHotelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
