/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Maison;
import entities.Reclamation;
import java.awt.Insets;
import java.net.URL;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.ServiceReclamationIMP;

/**
 * FXML Controller class
 *
 * @author Hani
 */
public class Admin_reclamationController implements Initializable {
    ObservableList<String> recList = FXCollections.observableArrayList("Technique","Graphique","Service","Autre");
    ServiceReclamationIMP s = new ServiceReclamationIMP();
    ObservableList list;

    @FXML
    private TableColumn<Reclamation, String> reclamation;
    @FXML
    private TableColumn<Reclamation, String> type;
    @FXML
    private TableColumn<Reclamation, String> date;
    @FXML
    private TableColumn<Reclamation, String> editcol;
    @FXML
    private TableView<Reclamation> tableView;
    @FXML
    private Button actualiser;
    @FXML
    private Button recherche;
    private TextField rech;
    @FXML
    private ChoiceBox<String> rec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rec.setValue(" "); 
        rec.setItems(recList);
        affiche();
        
        rec.setOnAction((event)->{
            System.out.print("rec"+rec.getSelectionModel().getSelectedItem());
                tableView.getItems().clear(); 
                List l = s.chercherRec(rec.getSelectionModel().getSelectedItem());
                list = FXCollections.observableArrayList(l);
                System.out.println(list);
                tableView.setItems(list);
                type.setCellValueFactory(new PropertyValueFactory<>("type"));
                date.setCellValueFactory(new PropertyValueFactory<>("date"));
                reclamation.setCellValueFactory(new PropertyValueFactory<>("discreption"));

                               //add cell of button edit 
                 Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFoctory = (TableColumn<Reclamation, String> param) -> {
                    // make cell containing buttons
                    final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
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



                                deleteIcon.setOnMouseClicked((event) -> {
                                    Reclamation r = (Reclamation) tableView.getSelectionModel().getSelectedItem();
                                    s.supprimer(r.getId());



                                });


                                HBox managebtn = new HBox(deleteIcon);
                                managebtn.setStyle("-fx-alignment:center");
                                //HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                                setGraphic(managebtn);

                                setText(null);

                            }
                        }

                    };

                    return cell;
                };
                editcol.setCellFactory(cellFoctory);

                tableView.setItems(list);
        
                
        
    });
    }
    private void affiche(){
        List rec = s.afficher();
        list = FXCollections.observableArrayList(rec);
        System.out.println(list);
        tableView.setItems(list);
        //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        reclamation.setCellValueFactory(new PropertyValueFactory<>("discreption"));
        
                       //add cell of button edit 
         Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFoctory = (TableColumn<Reclamation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
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
                        
                        
                        
                        deleteIcon.setOnMouseClicked((event) -> {
                            Reclamation r = (Reclamation) tableView.getSelectionModel().getSelectedItem();
                            s.supprimer(r.getId());
                            
 

                        });
                        

                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        //HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        editcol.setCellFactory(cellFoctory);
        
        tableView.setItems(list);
    }
    @FXML
    private void actualiser(ActionEvent event) {
        list.clear();    
        tableView.getItems().clear(); 
        affiche();
    }

    @FXML
    private void chercher(ActionEvent event) {
        
        list.clear();    
        tableView.getItems().clear(); 
        //List rec = s.chercher(rech.getText());
        list = FXCollections.observableArrayList(rec);
        System.out.println(list);
        tableView.setItems(list);
        affiche();
    }
    
}
