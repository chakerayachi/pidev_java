/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Ticket;
import java.io.IOException;
import java.net.URL;
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
import services.ServiceTicketIMP;

/**
 * FXML Controller class
 *
 * @author Sabri
 */
public class AffichageTicketController implements Initializable {
    
    ObservableList list;
    @FXML
    private TableView<Ticket> tableView;
    @FXML
    private TableColumn<Ticket, String> type;
    @FXML
    private TableColumn<Ticket, Integer> prix;
    @FXML
    private TableColumn<Ticket, Integer> idEven;
    @FXML
    private TableColumn<Ticket, String> editcol;
    ServiceTicketIMP s = new ServiceTicketIMP();
    @FXML
    private Button actualiser;
    @FXML
    private ImageView fuck;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichage();
    }    
    
     private void affichage(){
        List tickets = s.afficher();
        list = FXCollections.observableArrayList(tickets);
        tableView.setItems(list);
        //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        idEven.setCellValueFactory(new PropertyValueFactory<>("id_evenement"));
        
        
        


                //add cell of button edit 
         Callback<TableColumn<Ticket, String>, TableCell<Ticket, String>> cellFoctory = (TableColumn<Ticket, String> param) -> {
            // make cell containing buttons
            final TableCell<Ticket, String> cell = new TableCell<Ticket, String>() {
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
                            Ticket t = tableView.getSelectionModel().getSelectedItem();
                            s.delete(t.getId());
                            
 

                        });
                        editIcon.setOnMouseClicked((event) -> {
                            
                            Ticket t = tableView.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("../GUI/ModifierTicket.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AffichageTicketController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            ModifierTicketController m = loader.getController();
                            m.setTextField(t.getId(), t.getPrix() ,t.getType());
                        
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
    private void actualiser() {
        list.clear();    
        tableView.getItems().clear(); 
        affichage();
        
    }
    
    @FXML
    private void affiche(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/AffichageEvenement.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        
    }

    
    
}
