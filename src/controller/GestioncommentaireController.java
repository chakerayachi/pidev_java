/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.GestionsujetController.idtopic;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import services.ServiceTopicIMP;

/**
 * FXML Controller class
 *
 * @author Firas CHKOUNDALI
 */
public class GestioncommentaireController implements Initializable {
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
    @FXML
    private TableColumn<Commentaire, Integer> coliduser;
    private TableColumn<Commentaire, Integer> colidsujet;
    @FXML
    private TableColumn<Commentaire, String> editcol;
    @FXML
    private TableColumn<Commentaire, Integer> colnblike;
    @FXML
    private TableColumn<Commentaire, Integer> colnbdislike;

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
        coliduser.setCellValueFactory(new PropertyValueFactory<Commentaire, Integer>("iduser"));
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
                            FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                            FontAwesomeIconView likeIcon = new FontAwesomeIconView(FontAwesomeIcon.THUMBS_ALT_UP);
                            FontAwesomeIconView dislikeIcon = new FontAwesomeIconView(FontAwesomeIcon.THUMBS_DOWN);

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
                            likeIcon.setStyle( " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#3e79e6;");
                            dislikeIcon.setStyle(
                            " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#3e79e6;"
                            );
                            deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                                
                                
Commentaire commentaire = tvcommentaire.getSelectionModel().getSelectedItem();
//                                st.supprimer(topic.getIdtopic());
//                                tvtopics.refresh();
if(GestiontopicController.iduser==sujet.getIduser()){
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
}
else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete");
                alert.setHeaderText("vous pouver supprimer seulement tes commentaires");
                alert.setContentText(" Done!");
                alert.show();
                tvcommentaire.setItems(scom.getcommentbysujet(idsujet)); 
}
                                
                                
                                
                                
                            });
                            //edit
                            editIcon.setOnMouseClicked((MouseEvent event) -> {
                               Commentaire commentaire = tvcommentaire.getSelectionModel().getSelectedItem();
                               if(GestiontopicController.iduser==sujet.getIduser()){

                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/GUI/updatecommentaire.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(GestioncommentaireController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            UpdatecommentaireController updatecommentaireController = loader.getController();
                            updatecommentaireController.setTextField(commentaire);
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();    
                          stage.setOnHiding( event2 -> { tvcommentaire.setItems(scom.getcommentbysujet(idsujet));
                              } );
                            }
                               else
                               {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modifier");
                alert.setHeaderText("vous pouver modifier seulement tes commentaires");
                alert.setContentText(" Done!");
                alert.show();
                tvcommentaire.setItems(scom.getcommentbysujet(idsujet));
                               }}
                               );
                           likeIcon.setOnMouseClicked((MouseEvent event) -> {
                               Commentaire commentaire = tvcommentaire.getSelectionModel().getSelectedItem();
                               int check=scom.veriflikeuser(GestiontopicController.iduser,commentaire.getIdcom());
                               if(check==0){
                                   if(scom.verifdislikeuser(GestiontopicController.iduser,commentaire.getIdcom())==0)
                                    {
                                   scom.ajoutlike(GestiontopicController.iduser,commentaire.getIdcom() );
                               int nblike=scom.getnblike(commentaire.getIdcom());
                               nblike++;
                               scom.setlike(commentaire.getIdcom(), nblike);
                                   }
                                   else
                                   {
                                   scom.ajoutlike(GestiontopicController.iduser,commentaire.getIdcom() );
                               int nblike=scom.getnblike(commentaire.getIdcom());
                               nblike++;
                               scom.setlike(commentaire.getIdcom(), nblike);
                               int nbdislike=scom.getnbdislike(commentaire.getIdcom());
                               nbdislike--;
                               scom.setdislike(commentaire.getIdcom(), nbdislike);
                               //**********
                               scom.supprimerdislike(scom.verifdislikeuser(GestiontopicController.iduser,commentaire.getIdcom()));
                                   }
                               }
                               else
                               {
                               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Like");
                alert.setHeaderText("vous avez deja liké ce commentaire");
                alert.setContentText(" Done!");
                alert.show();
                               }
                tvcommentaire.setItems(scom.getcommentbysujet(idsujet));
                }
                );  
                            dislikeIcon.setOnMouseClicked((MouseEvent event) -> {
                               Commentaire commentaire = tvcommentaire.getSelectionModel().getSelectedItem();
                               if(scom.verifdislikeuser(GestiontopicController.iduser,commentaire.getIdcom())==0)
                               {if(scom.veriflikeuser(GestiontopicController.iduser,commentaire.getIdcom())==0){
                                scom.ajoutdislike(GestiontopicController.iduser,commentaire.getIdcom());
                               int nbdislike=scom.getnbdislike(commentaire.getIdcom());
                               nbdislike++;
                               scom.setdislike(commentaire.getIdcom(), nbdislike);}
                               else{
                               scom.ajoutdislike(GestiontopicController.iduser,commentaire.getIdcom());
                               int nbdislike=scom.getnbdislike(commentaire.getIdcom());
                               nbdislike++;
                               scom.setdislike(commentaire.getIdcom(), nbdislike);
                               int nblike=scom.getnblike(commentaire.getIdcom());
                               nblike--;
                               scom.setlike(commentaire.getIdcom(), nblike);
                               //***********
                               scom.supprimerlike(scom.veriflikeuser(GestiontopicController.iduser,commentaire.getIdcom()));
                               
                               }
                               }
                               else
                               {
                               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Like");
                alert.setHeaderText("vous avez deja disliké ce commentaire");
                alert.setContentText(" Done!");
                alert.show();
                               }
                               
                tvcommentaire.setItems(scom.getcommentbysujet(idsujet));
                }
                );  
                            HBox managebtn = new HBox(editIcon, deleteIcon,likeIcon,dislikeIcon);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                            HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                            HBox.setMargin(likeIcon, new Insets(2, 4, 0, 2));
                            HBox.setMargin(dislikeIcon, new Insets(2, 4, 0, 2));

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

 
    void setid(Sujet t) {

       idsujet=t.getIdsujet();
        

    }

    @FXML
    private void backtopic(MouseEvent event) {
        
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/gestionsujet.fxml"));
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
    private void ajoutercommentaire(MouseEvent event) {
         try {
            AjoutercommentaireController.idsujet=idsujet;
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/ajoutercommentaire.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            stage.setOnHiding( event2 -> { tvcommentaire.setItems(scom.getcommentbysujet(idsujet));        
            labelnbcom.setText(Integer.toString(ssuj.getnbcom(idsujet)));


} );
        } catch (IOException ex) {
            Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
