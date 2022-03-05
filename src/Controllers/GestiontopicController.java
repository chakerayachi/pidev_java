/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import services.ServiceCommentaireIMP;
import services.ServiceTopicIMP;

/**
 * FXML Controller class
 *
 * @author Firas CHKOUNDALI
 */
public class GestiontopicController implements Initializable {
   ServiceTopicIMP st = new ServiceTopicIMP();
   public  static int iduser=1;
    @FXML
    public TableView<Topic> tvtopics;
    
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
    private Button translate;
    @FXML
    private Label labeltrans;
    @FXML
    private FontAwesomeIconView cloud;
    public int getIdtopic() {
        return idtopic;
    }
    
    
  //  public TableView<Topic> tvtopic;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //ServiceTopicIMP st = new ServiceTopicIMP();
      
        ObservableList<Topic> topiclist;      
            try {
                
                
            topiclist = st.gettopiclisteafficher();
            ServiceCommentaireIMP scom=new ServiceCommentaireIMP();

//            //colid.setCellValueFactory(new PropertyValueFactory<Topic, Integer>("idtopic"));
            coltitre.setCellValueFactory(new PropertyValueFactory<Topic, String>("titretopic"));
            coldescription.setCellValueFactory(new PropertyValueFactory<Topic, String>("description"));
            coldate.setCellValueFactory(new PropertyValueFactory<Topic, String>("date"));
//            colaccepter.setCellValueFactory(new PropertyValueFactory<Topic, Integer>("accepter"));
            colnbsujet.setCellValueFactory(new PropertyValueFactory<Topic, Integer>("nbsujet"));
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
                             //Topic topic = tvtopics.getSelectionModel().getSelectedItem();
                            deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                                
                               
Topic topic = tvtopics.getSelectionModel().getSelectedItem();
//                                st.supprimer(topic.getIdtopic());
//                                tvtopics.refresh();
 if(iduser==topic.getIduser()){
                                    
                  
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
                tvtopics.setItems(st.gettopiclisteafficher());
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec");
                alert.setHeaderText(null);
                alert.setContentText("Delete Failed !");
            }

        } else {
            alert2.close();
        }
                                
                                
                                
                                
                                
 }else
 { 
            ServiceTopicIMP st = new ServiceTopicIMP();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete");
                alert.setHeaderText("vous pouver supprimer seulement tes topics");
                alert.setContentText(" Done!");
                alert.show();
    try {
        tvtopics.setItems(st.gettopiclisteafficher());
    } catch (SQLException ex) {
        Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
    }
 }
                            });
                            
                            editIcon.setOnMouseClicked((MouseEvent event) -> {
                               Topic topic = tvtopics.getSelectionModel().getSelectedItem();
                               
                                if(iduser==topic.getIduser()){
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/GUI/updatetopic.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            UpdatetopicController updatetopicController = loader.getController();
                            updatetopicController.setTextField(topic);
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();    
                           new animatefx.animation.ZoomIn(parent).play();

                          stage.setOnHiding( event2 -> { try {
                              tvtopics.setItems(st.gettopicliste());
                             } catch (SQLException ex) {
                                 Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
                              }
                              } );
                                }
                                else{
                                     ServiceTopicIMP st = new ServiceTopicIMP();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modifier");
                alert.setHeaderText("vous pouver modifier seulement tes topics");
                alert.setContentText(" Done!");
                alert.show();
    try {
        tvtopics.setItems(st.gettopicliste());
    } catch (SQLException ex) {
        Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
    }
                                }
                            
                            });
                            
                            HBox managebtn = new HBox(editIcon, deleteIcon);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(deleteIcon, new Insets(0, 0, 0, 0));
                            HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
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
                            GestionsujetController.idtopic=topic.getIdtopic();
//                           
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();
                         Parent root = FXMLLoader.load(getClass().getResource("/GUI/gestionsujet.fxml"));
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

    @FXML
    private void ajoutertopic(MouseEvent event)  {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/ajoutertopic.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            new animatefx.animation.ZoomIn(parent).play();
            stage.setOnHiding( event2 -> { try {
                
                tvtopics.setItems(st.gettopiclisteafficher());
                } catch (SQLException ex) {
                    Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
                }
} );
        } catch (IOException ex) {
            Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
    }
    
    public void refreshTable(MouseEvent event) {
       try {
           tvtopics.setItems(st.gettopiclisteafficher());
       } catch (SQLException ex) {
           Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @FXML
    private void translation(ActionEvent event) {
             
       try {
            ObservableList<Topic> topiclist;      
           try {
               topiclist = st.gettopiclisteafficher();
           
           Topic topic=new Topic();
           topic = tvtopics.getSelectionModel().getSelectedItem();
           Document d = (Document) Jsoup.connect("https://api.nlpcloud.io/v1/opus-mt-en-fr/translation")
                   .header("Authorization", "Token 4352881aedef35e459f323edac54e9d865119731")
                   .ignoreContentType(true)
                   .userAgent("Mozilla")
                   //.data("'text'","'John Doe has been working for Microsoft in Seattle since 1999.'")
                   .ignoreHttpErrors(true)
                   .requestBody("{\"text\":\"" + topic.getDescription().replaceAll("\n", "")
                           .replaceAll("\r", "") + "\"}"
                   )
                   .post();
           
           ObjectMapper obj = new ObjectMapper();
           JsonNode jn = obj.readTree(d.body().text());
           labeltrans.setText(jn.get("translation_text").asText());
           tvtopics.setItems(topiclist);
          } catch (SQLException ex) {
               Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
           } 
           //topic.setTopic_description(jn.get("translation_text").asText());
       } catch (IOException ex) {
           Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
       }
                

         
        
    }

    @FXML
    private void weather(MouseEvent event) {
        
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/Primary.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            new animatefx.animation.ZoomIn(parent).play();
            stage.setOnHiding( event2 -> { try {
                
                tvtopics.setItems(st.gettopiclisteafficher());
                } catch (SQLException ex) {
                    Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
                }
} );
        } catch (IOException ex) {
            Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
        
        
     
}
