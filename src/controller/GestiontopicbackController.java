/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.GestiontopicController.iduser;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Topic;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.controlsfx.control.Notifications;
import services.ServiceCommentaireIMP;
import services.ServiceTopicIMP;

/**
 * FXML Controller class
 *
 * @author Firas CHKOUNDALI
 */
public class GestiontopicbackController implements Initializable {
 ServiceTopicIMP st = new ServiceTopicIMP();
    @FXML
    private TableView<Topic> tvtopics;
   
    @FXML
    private TableColumn<Topic, String> coltitre;
    @FXML
    private TableColumn<Topic, String> coldescription;
    @FXML
    private TableColumn<Topic, String> coldate;
    @FXML
    private TableColumn<Topic, Integer> colnbsujet;
    private TableColumn<Topic, Integer> coliduser;
    @FXML
    private TableColumn<Topic, String> editcol;
    private int idtopic;
    @FXML
    private TableColumn<Topic, Integer> colhide;
    

    /**
     * Initializes the controller class.
     */
    
      public boolean clickkk(MouseEvent event){

             TablePosition tableposition = tvtopics.getSelectionModel().getSelectedCells().get(0);
             int row = tableposition.getRow();
             int col = tableposition.getColumn();
             if (col == 0){
             Topic topic = tvtopics.getItems().get(0);
             TableColumn tablecolumn = tableposition.getTableColumn();
             String data = (String) tablecolumn.getCellObservableValue(topic).getValue();
             System.out.println(data);
             return true ; 
             }else return false ; 

        }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         ObservableList<Topic> topiclist;      
            try {
                
                
            topiclist = st.gettopicliste();
            ServiceCommentaireIMP scom=new ServiceCommentaireIMP();

//            //colid.setCellValueFactory(new PropertyValueFactory<Topic, Integer>("idtopic"));
            coltitre.setCellValueFactory(new PropertyValueFactory<Topic, String>("titretopic"));
            coldescription.setCellValueFactory(new PropertyValueFactory<Topic, String>("description"));
            coldate.setCellValueFactory(new PropertyValueFactory<Topic, String>("date"));
//            colaccepter.setCellValueFactory(new PropertyValueFactory<Topic, Integer>("accepter"));
            colnbsujet.setCellValueFactory(new PropertyValueFactory<Topic, Integer>("nbsujet"));
            //colhide.setCellValueFactory(new PropertyValueFactory<Topic, Integer>("hide"));
            //coliduser.setText(user.getLogin());
//            coliduser.setCellValueFactory(new PropertyValueFactory<Topic, Integer>("iduser"));
            
            
            Callback<TableColumn<Topic, String>, TableCell<Topic, String>> cellFoctory;
            cellFoctory = (TableColumn<Topic, String> param) -> {
                // make cell containing buttons
                final TableCell<Topic, String> cell;
                cell = new TableCell<Topic, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        //that cell created only on non-empty rows
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                            
                        } else {
                            
                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                            FontAwesomeIconView HideIcon = new FontAwesomeIconView(FontAwesomeIcon.TOGGLE_OFF);
                            FontAwesomeIconView onIcon = new FontAwesomeIconView(FontAwesomeIcon.TOGGLE_ON);
                            FontAwesomeIconView acceptIcon = new FontAwesomeIconView(FontAwesomeIcon.CHECK);

                            deleteIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#ff1744;"
                            );
                            HideIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#00E676;"
                            );
                              onIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#00E676;"
                            );
                              acceptIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#00E676;"
                            );
                             //Topic topic = tvtopics.getSelectionModel().getSelectedItem();
                            deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                                
                               
Topic topic = tvtopics.getSelectionModel().getSelectedItem();
//                                st.supprimer(topic.getIdtopic());
//                                tvtopics.refresh();
                                    
                  
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous supprimer ce topic  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceTopicIMP st = new ServiceTopicIMP();
            try {
                st.supprimer(topic.getIdtopic());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete");
                alert.setHeaderText(null);
                alert.setContentText(" Done!");
                alert.show();
                tvtopics.setItems(st.gettopicliste());
              //  tvtopics.refresh();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec");
                alert.setHeaderText(null);
                alert.setContentText("Delete Failed !");
            }

        } else {
            alert2.close();
        }
                                
                                
                                
                                
                                
 
    try {
        tvtopics.setItems(st.gettopicliste());
    } catch (SQLException ex) {
        Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
    }
 
                            });
                            
                            HideIcon.setOnMouseClicked((MouseEvent event) -> {
                               Topic topic = tvtopics.getSelectionModel().getSelectedItem();
                               
                                if(st.gethide(topic.getIdtopic())==0)
                                { System.out.println(topic.getIdtopic());
                              
                               
                                 Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous hider ce topic  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceTopicIMP st = new ServiceTopicIMP();
            try {
                 st.hidetopic(topic.getIdtopic());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("hide");
                alert.setHeaderText(null);
                alert.setContentText(" Done!");
                alert.show();
                tvtopics.setItems(st.gettopicliste());
                 tvtopics.refresh();
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec");
                alert.setHeaderText(null);
                alert.setContentText("Delete Failed !");
            }

        } else {
            alert2.close();
        }
                         
                                }
                               else{
                                   Notifications notificationbuilder;
                    notificationbuilder = Notifications.create()
                           .title("Alert").text("ce topic est deja en hide!").graphic(null).hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_CENTER)
                            .onAction(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent event)
                                {
                                    System.out.println("clicked on");
                                }
                            });
                notificationbuilder.darkStyle();
                notificationbuilder.showInformation();}
                 tvtopics.setItems(topiclist);

                            
                            
                            });
                            
                                 onIcon.setOnMouseClicked((MouseEvent event) -> {
                               Topic topic = tvtopics.getSelectionModel().getSelectedItem();
                                if(st.gethide(topic.getIdtopic())==1)
                                {
                                                      Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous afficher ce topic  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceTopicIMP st = new ServiceTopicIMP();
            try {
                 st.hideofftopic(topic.getIdtopic());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("hideoff");
                alert.setHeaderText(null);
                alert.setContentText(" Done!");
                alert.show();
                tvtopics.setItems(st.gettopicliste());
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec");
                alert.setHeaderText(null);
                alert.setContentText("Delete Failed !");
            }

        } else {
            alert2.close();
        }
                                
                                } else{
                                   Notifications notificationbuilder;
                    notificationbuilder = Notifications.create()
                           .title("Alert").text("ce topic est deja afficher!").graphic(null).hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_CENTER)
                            .onAction(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent event)
                                {
                                    System.out.println("clicked on");
                                }
                            });
                notificationbuilder.darkStyle();
                notificationbuilder.showInformation();}
                               
               tvtopics.setItems(topiclist);

                            });
          acceptIcon.setOnMouseClicked((MouseEvent event) -> {
                               Topic topic = tvtopics.getSelectionModel().getSelectedItem();
                                if(st.getaccept(topic.getIdtopic())==0)
                                {
              Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Confirmation");
        alert2.setHeaderText("voulez vous accepter ce topic  ?");
        Optional<ButtonType> result = alert2.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceTopicIMP st = new ServiceTopicIMP();
            try {
                 st.accepttopic(topic.getIdtopic());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Accepter");
                alert.setHeaderText(null);
                alert.setContentText(" Done!");
                alert.show();
                tvtopics.setItems(st.gettopicliste());
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec");
                alert.setHeaderText(null);
                alert.setContentText("Delete Failed !");
            }

        } else {
            alert2.close();
        }
                                
                                } else{
                                   Notifications notificationbuilder;
                    notificationbuilder = Notifications.create()
                           .title("Alert").text("ce topic est deja accepter!").graphic(null).hideAfter(javafx.util.Duration.seconds(5)).position(Pos.TOP_CENTER)
                            .onAction(new EventHandler<ActionEvent>(){
                                @Override
                                public void handle(ActionEvent event)
                                {
                                    System.out.println("clicked on");
                                }
                            });
                notificationbuilder.darkStyle();
                notificationbuilder.showInformation();}
                               
               tvtopics.setItems(topiclist);

                            });                        
                            HBox managebtn = new HBox(acceptIcon,HideIcon, deleteIcon,onIcon);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(deleteIcon, new Insets(0, 0, 0, 0));
                            HBox.setMargin(HideIcon, new Insets(2, 3, 0, 2));
                            HBox.setMargin(onIcon, new Insets(2, 3, 0, 2));
                            HBox.setMargin(acceptIcon, new Insets(2, 3, 0, 2));
                            setGraphic(managebtn);
                            setText(null);
                            
                        }
                    }
                    
                };
                
                return cell;
            };
            
                        
            editcol.setCellFactory(cellFoctory);
            

             //editcol.setCellFactory(cellFoctory);

             tvtopics.setItems(topiclist);
             
             
             //tvtopics.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            // tvtopics.getSelectionModel().setCellSelectionEnabled(false);
             tvtopics.setOnMouseClicked(event->{
                 
                    if (clickkk(event)== true){
                        try {
                            Topic topic = tvtopics.getSelectionModel().getSelectedItem();
                            System.out.println(topic.getIdtopic());
                            GestionsujetbackController.idtopic=topic.getIdtopic();
//                           
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();
                         Parent root = FXMLLoader.load(getClass().getResource("/GUI/gestionsujetback.fxml"));
                         Scene scene = new Scene(root);
                         stage.setScene(scene);
                         stage.show();
                                 new animatefx.animation.ZoomIn(root).play();

                            //System.out.println(idtopic);
                         
                        } catch (IOException e) {
                         System.err.println(String.format("Error: %s", e.getMessage()));
                        } 

                    }
              
             });
             //tvtopic=tvtopics;
             
        } catch (SQLException ex) {
            Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
