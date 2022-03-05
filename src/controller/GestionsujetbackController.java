/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.GestionsujetController.idtopic;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Sujet;
import entities.Topic;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.Notifications;
import services.ServiceCommentaireIMP;
import services.ServiceSujetIMP;
import services.ServiceTopicIMP;

/**
 * FXML Controller class
 *
 * @author Firas CHKOUNDALI
 */
public class GestionsujetbackController implements Initializable {
ServiceSujetIMP sp = new ServiceSujetIMP();
ServiceTopicIMP st=new ServiceTopicIMP();

GestiontopicController  gestiontopicController=new GestiontopicController();

    @FXML
    private FontAwesomeIconView backbtn;
    
    @FXML
    private TableColumn<Sujet, String> coltitre;
    @FXML
    private TableColumn<Sujet, String> coldescription;
    @FXML
    private TableColumn<Sujet, String> coldate;
   
    private TableColumn<Sujet, Integer> coliduser;
    @FXML
    private TableColumn<Sujet, String> editcol;
    @FXML
    private Label titretopic;
    @FXML
    private Label desctopic;
    @FXML
    private Label datetopic;
    @FXML
    private Label nbsujettopic;
    @FXML
    private TableView<Sujet> tvsujet;
    @FXML
    private TableColumn<Sujet, Integer> colnbcom;
    public static int idtopic;
    @FXML
    private Label labelusername;
    @FXML
    private Button delleteall;
    
    
      public boolean clickkk(MouseEvent event){

             TablePosition tableposition = tvsujet.getSelectionModel().getSelectedCells().get(0);
             int row = tableposition.getRow();
             int col = tableposition.getColumn();
             if (col == 0){
             Sujet sujet = tvsujet.getItems().get(0);
             TableColumn tablecolumn = tableposition.getTableColumn();
             String data = (String) tablecolumn.getCellObservableValue(sujet).getValue();
             System.out.println(data);
             return true ; 
             }else return false ; 

        }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        ServiceCommentaireIMP scom=new ServiceCommentaireIMP();

        Topic topic=st.gettopicbyid(idtopic);         
        Utilisateur user=scom.getuserbyid(topic.getIduser());
            titretopic.setText(topic.getTitretopic());
        desctopic.setText(topic.getDescription());
        datetopic.setText(topic.getDate());
        nbsujettopic.setText(Integer.toString(st.getnbsujet(idtopic)));
        labelusername.setText(user.getLogin());
        
        ObservableList<Sujet> sujetlist;      
        sujetlist = sp.getsujetbytopic(idtopic);
        System.out.println("teeeeeeet"+gestiontopicController.getIdtopic());
        //colid.setCellValueFactory(new PropertyValueFactory<Topic, Integer>("idtopic"));
        coltitre.setCellValueFactory(new PropertyValueFactory<Sujet, String>("titresujet"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Sujet, String>("contenu"));
        coldate.setCellValueFactory(new PropertyValueFactory<Sujet, String>("date"));
      //  colaccepter.setCellValueFactory(new PropertyValueFactory<Sujet, Integer>("accepter"));
        colnbcom.setCellValueFactory(new PropertyValueFactory<Sujet, Integer>("nbcom"));
        //coliduser.setCellValueFactory(new PropertyValueFactory<Sujet, Integer>("iduser"));

        Callback<TableColumn<Sujet, String>, TableCell<Sujet, String>> cellFoctory;
            cellFoctory = (TableColumn<Sujet, String> param) -> {
                // make cell containing buttons
                final TableCell<Sujet, String> cell;
                cell = new TableCell<Sujet, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        //that cell created only on non-empty rows
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                            
                        } else {
                            
                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                            FontAwesomeIconView accepterIcon = new FontAwesomeIconView(FontAwesomeIcon.CHECK);
                            
                            deleteIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#ff1744;"
                            );
                            accepterIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#00E676;"
                            );
                     deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                                
                                
Sujet sujet = tvsujet.getSelectionModel().getSelectedItem();
//                                st.supprimer(topic.getIdtopic());
//                                tvtopics.refresh();

                                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer ce sujet  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceTopicIMP st = new ServiceTopicIMP();
            sp.supprimer(sujet.getIdsujet());
            int nbsujet=st.getnbsujet(idtopic);
                nbsujet--;
                st.setnbsujet(idtopic, nbsujet);
                nbsujettopic.setText(Integer.toString(st.getnbsujet(idtopic)));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete");
            alert.setHeaderText(null);
            alert.setContentText(" Done!");
            alert.show();
            tvsujet.setItems(sp.getsujetbytopic(idtopic));

        } else {
            alert2.close();
        }});              
                     accepterIcon.setOnMouseClicked((MouseEvent event) -> {
                                
                                
Sujet sujet = tvsujet.getSelectionModel().getSelectedItem();
//                                st.supprimer(topic.getIdtopic());
//                                tvtopics.refresh();
if(sp.getaccept(sujet.getIdsujet())==0){
                                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous accepter ce sujet  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println(sujet.getIdsujet());
            ServiceTopicIMP st = new ServiceTopicIMP();
            
            sp.acceptsujet(sujet.getIdsujet());
             int nbsujet=st.getnbsujet(idtopic);
                nbsujet++;
                st.setnbsujet(idtopic, nbsujet);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Accepter");
            alert.setHeaderText("sujet accepter");
            alert.setContentText(" Done!");
            alert.show();
            tvsujet.setItems(sp.getsujetbytopic(idtopic));
            nbsujettopic.setText(Integer.toString(st.getnbsujet(idtopic)));

        } else {
            alert2.close();
        } }else
                    {
                        Notifications notificationbuilder;
                    notificationbuilder = Notifications.create()
                           .title("Alert").text("ce sujet est deja accepter!").graphic(null).hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_CENTER)
                            .onAction(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent event)
                                {
                                    System.out.println("clicked on");
                                }
                            });
                notificationbuilder.darkStyle();
                notificationbuilder.showInformation();}
                    
                    });              
                                     
                            HBox managebtn = new HBox(accepterIcon, deleteIcon);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                            HBox.setMargin(accepterIcon, new Insets(2, 3, 0, 2));
                            
                            setGraphic(managebtn);
                            
                            setText(null);
                            
                        }
                    }
                    
                };
                
                return cell;
            };
           
            editcol.setCellFactory(cellFoctory);
             //editcol.setCellFactory(cellFoctory);

        
        
        
        
        tvsujet.setOnMouseClicked(event->{
                 
                    if (clickkk(event)== true){
                        try {
                            Sujet sujet = tvsujet.getSelectionModel().getSelectedItem();
                            System.out.println(sujet.getIdsujet());
                            GestioncommentairebackController.idsujet=sujet.getIdsujet();
//                           
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();
                        Parent root = FXMLLoader.load(getClass().getResource("/GUI/gestioncommentaireback.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        //System.out.println(idtopic);
                         
                        } catch (IOException e) {
                         System.err.println(String.format("Error: %s", e.getMessage()));
                        } 

                    }
              
             });
            nbsujettopic.setText(Integer.toString(st.getnbsujet(idtopic)));
tvsujet.setItems(sujetlist);
    }    

    @FXML
    private void backtopic(MouseEvent event) {
    try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/gestiontopicback.fxml"));
                Scene scene = new Scene(page1);
               // Node root=(Node) event.getSource().getScene().getWindow():
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
            
             ServiceTopicIMP st = new ServiceTopicIMP();
            sp.supprimersujets(idtopic);
            int nbsujet=st.getnbsujet(idtopic);
                nbsujet=0;
                st.setnbsujet(idtopic, nbsujet);
                nbsujettopic.setText(Integer.toString(st.getnbsujet(idtopic)));
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Accepter");
            alert.setHeaderText("sujet accepter");
            alert.setContentText(" Done!");
            alert.show();
            tvsujet.setItems(sp.getsujetbytopic(idtopic));

        } else {
            alert2.close();
        }
        
    }
    
}
