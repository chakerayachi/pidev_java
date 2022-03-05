/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import services.ServiceCommentaireIMP;
import services.ServiceSujetIMP;
import services.ServiceTopicIMP;

/**
 * FXML Controller class
 *
 * @author Firas CHKOUNDALI
 */
public class GestionsujetController implements Initializable {
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
        System.out.println(idtopic);
        sujetlist = sp.getsujetbytopicaccept(idtopic);
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
                                
                                
Sujet sujet = tvsujet.getSelectionModel().getSelectedItem();
//                                st.supprimer(topic.getIdtopic());
//                                tvtopics.refresh();
if(GestiontopicController.iduser==sujet.getIduser()){
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
            tvsujet.setItems(sp.getsujetbytopicaccept(idtopic));

        } else {
            alert2.close();
        }
}else
                    {
                       ServiceSujetIMP ssujet = new ServiceSujetIMP();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete");
                alert.setHeaderText("vous pouver supprimer seulement tes sujets");
                alert.setContentText(" Done!");
                alert.show();
                tvsujet.setItems(ssujet.getsujetbytopicaccept(idtopic)); 
                    }
                                
                                
                                
                                
                                
                            });
                            editIcon.setOnMouseClicked((MouseEvent event) -> {
                               Sujet sujet = tvsujet.getSelectionModel().getSelectedItem();
                               if(GestiontopicController.iduser==sujet.getIduser()){
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("/GUI/updatesujet.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            UpdatesujetController updatesujetController = loader.getController();
                            updatesujetController.setTextField(sujet);
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();    
                                          new animatefx.animation.ZoomIn(parent).play();

                          stage.setOnHiding( event2 -> { tvsujet.setItems(sp.getsujetbytopic(idtopic));
                              } );
                            }
                               else
                    {
                       ServiceSujetIMP ssujet = new ServiceSujetIMP();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Modifier");
                alert.setHeaderText("vous pouver modifier seulement tes sujets");
                alert.setContentText(" Done!");
                alert.show();
                tvsujet.setItems(ssujet.getsujetbytopicaccept(idtopic));
                    }
                            }
                             
                    );
                            
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
             //editcol.setCellFactory(cellFoctory);

        
        
        
        
        tvsujet.setOnMouseClicked(event->{
                 
                    if (clickkk(event)== true){
                        try {
                            Sujet sujet = tvsujet.getSelectionModel().getSelectedItem();
                            System.out.println(sujet.getIdsujet());
                            CommentsController.idsujet=sujet.getIdsujet();
//                           
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();
                        Parent root = FXMLLoader.load(getClass().getResource("/GUI/comments.fxml"));
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
                Parent page1 = FXMLLoader.load(getClass().getResource("/GUI/gestiontopic.fxml"));
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
    private void ajoutersujet(MouseEvent event) {
        
        try {
            AjoutersujetController.idtopic=idtopic;
            Parent parent = FXMLLoader.load(getClass().getResource("/GUI/ajoutersujet.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
              new animatefx.animation.ZoomIn(parent).play();

            stage.setOnHiding( event2 -> { tvsujet.setItems(sp.getsujetbytopicaccept(idtopic));        
            nbsujettopic.setText(Integer.toString(st.getnbsujet(idtopic)));


} );
        } catch (IOException ex) {
            Logger.getLogger(GestiontopicController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    void setid(Topic t) {

       idtopic=t.getIdtopic();
        

    }

    @FXML
    private void print(MouseEvent event) {
        
        
          PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.tvsujet;
        
     if(job != null){
     job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
     Printer printer = job.getPrinter();
     PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
     boolean success = job.printPage(pageLayout, root);
     if(success){
        job.endJob();
     }
   }
     
     
     
     


    }
 

              
    
    
}
