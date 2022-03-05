/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.GestioncommentaireController.idsujet;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Commentaire;
import entities.Sujet;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.ServiceCommentaireIMP;
import services.ServiceSujetIMP;

/**
 * FXML Controller class
 *
 * @author Firas CHKOUNDALI
 */
public class GestioncommentairebackController implements Initializable {
 ServiceCommentaireIMP scom=new ServiceCommentaireIMP();
    ServiceSujetIMP ssuj=new ServiceSujetIMP();
    @FXML
    private Label labeltitre;
    @FXML
    private Label labelcont;
    @FXML
    private Label labelnbcom;
    public static int idsujet;
    @FXML
    private FontAwesomeIconView backbtn;
    @FXML
    private TableView<Commentaire> tvcommentaire;
    private TableColumn<Commentaire, Integer> colidcom;
    @FXML
    private TableColumn<Commentaire, String> colcontenu;
    @FXML
    private TableColumn<Commentaire, String> coldate;
    private TableColumn<Commentaire, Integer> coliduser;
    private TableColumn<Commentaire, Integer> colidsujet;
    @FXML
    private TableColumn<Commentaire, String> editcol;
    @FXML
    private TableColumn<Commentaire, Integer> colnblike;
    @FXML
    private TableColumn<Commentaire, Integer> colnbdislike;
    @FXML
    private Button delleteall;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         Sujet sujet=ssuj.getsujetbyid(idsujet);
        labeltitre.setText(sujet.getTitresujet());
        labelcont.setText(sujet.getContenu());
        labelnbcom.setText(Integer.toString(ssuj.getnbcom(idsujet)));
        
        ObservableList<Commentaire> commentairelist;      
        commentairelist = scom.getcommentbysujet(idsujet);
        
       // colidcom.setCellValueFactory(new PropertyValueFactory<Commentaire, Integer>("idcom"));
        colcontenu.setCellValueFactory(new PropertyValueFactory<Commentaire, String>("contenu"));
        coldate.setCellValueFactory(new PropertyValueFactory<Commentaire, String>("date"));
        //coliduser.setCellValueFactory(new PropertyValueFactory<Commentaire, Integer>("iduser"));
       //colidsujet.setCellValueFactory(new PropertyValueFactory<Commentaire, Integer>("idsujet"));
        colnblike.setCellValueFactory(new PropertyValueFactory<Commentaire, Integer>("nblike"));
        colnbdislike.setCellValueFactory(new PropertyValueFactory<Commentaire, Integer>("nbdislike"));
         Callback<TableColumn<Commentaire, String>, TableCell<Commentaire, String>> cellFoctory;
            cellFoctory = (TableColumn<Commentaire, String> param) -> {
                // make cell containing buttons
                final TableCell<Commentaire, String> cell;
                cell = new TableCell<Commentaire, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        //that cell created only on non-empty rows
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                            
                        } else {
                            
                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                            
                            

                            deleteIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#ff1744;"
                            );
                           
                           
                                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                                
                                
Commentaire commentaire = tvcommentaire.getSelectionModel().getSelectedItem();
//                                st.supprimer(topic.getIdtopic());
//                                tvtopics.refresh();

                                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer ce commentaire  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceSujetIMP st = new ServiceSujetIMP();
            scom.supprimer(commentaire.getIdcom());
            int nbsujet=st.getnbcom(idsujet);
                nbsujet--;
                st.setnbcom(idsujet, nbsujet);
               labelnbcom.setText(Integer.toString(st.getnbcom(idsujet)));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete");
            alert.setHeaderText(null);
            alert.setContentText(" Done!");
            alert.show();
            tvcommentaire.setItems(scom.getcommentbysujet(idsujet));

        } else {
            alert2.close();
        }
         });     
                                                 HBox managebtn = new HBox( deleteIcon);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                           
                          

                            setGraphic(managebtn);
                            
                            setText(null);
                            
                        }
                    }
                    
                };
               
                return cell;
            };
           
            editcol.setCellFactory(cellFoctory);
            
        tvcommentaire.setItems(commentairelist);
        labelnbcom.setText(Integer.toString(ssuj.getnbcom(idsujet)));
        
    }    


    @FXML
    private void backtopic(MouseEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/gestionsujetback.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                new animatefx.animation.ZoomIn(page1).play();

            } catch (IOException ex) {
                Logger.getLogger(GestionsujetController.class.getName()).log(Level.SEVERE, null, ex);
            }   
    }

    @FXML
    private void delleteall(ActionEvent event) {
    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer tous les sujets  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            
             ServiceCommentaireIMP sp = new ServiceCommentaireIMP();
             ServiceSujetIMP st= new ServiceSujetIMP();
            sp.supprimercommentaires(idsujet);
            int nbcom=st.getnbcom(idsujet);
                nbcom=0;
                st.setnbcom(idsujet, nbcom);
                labelnbcom.setText(Integer.toString(st.getnbcom(idsujet)));
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Accepter");
            alert.setHeaderText("sujets supprimer");
            alert.setContentText(" Done!");
            alert.show();
            tvcommentaire.setItems(sp.getcommentbysujet(idsujet));

        } else {
            alert2.close();
        }
    }
    
}
