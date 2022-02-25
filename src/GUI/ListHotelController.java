/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Hotel;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ServiceHotelIPM;

/**
 * FXML Controller class
 *
 * @author Wael
 */
public class ListHotelController implements Initializable {
    
    ServiceHotelIPM s = new ServiceHotelIPM();

    @FXML
    private TableView<Hotel> tableView;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> libelle;
    @FXML
    private TableColumn<?, ?> adresse;
    @FXML
    private TableColumn<?, ?> ville;
    @FXML
    private TableColumn<?, ?> region;
    @FXML
    private TableColumn<?, ?> tel;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> nbEtoile;
    @FXML
    private TextField txtHotelSearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List personnes = s.afficher();
        ObservableList list = FXCollections.observableArrayList(personnes);
        tableView.setItems(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        region.setCellValueFactory(new PropertyValueFactory<>("region"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        nbEtoile.setCellValueFactory(new PropertyValueFactory<>("nb_etoile"));
        
        
        
        FilteredList<Hotel> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		txtHotelSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Hotel -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Hotel.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Hotel.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (Hotel.getDescription().toLowerCase().indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Hotel> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableView.setItems(sortedData);
                // Wrap the ObservableList in a FilteredList (initially display all data).
		
		// 2. Set the filter Predicate whenever the filter changes.
		txtHotelSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Hotel -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Hotel.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Hotel.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (Hotel.getDescription().toLowerCase().indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableView.setItems(sortedData);
       
    }    
    
}
