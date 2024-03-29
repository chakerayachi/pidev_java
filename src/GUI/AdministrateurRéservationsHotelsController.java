/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Reservation;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import services.ServiceRéservationIMP;
import services.ServiceStripeIMP;

/**
 * FXML Controller class
 *
 * @author alaaz
 */
public class AdministrateurRéservationsHotelsController implements Initializable {

    @FXML
    private TableView<List> table_reservation_hotel;
    @FXML
    private TableColumn<ArrayList, Timestamp> created_At;
    @FXML
    private TableColumn<ArrayList, Date> date_debut;
    @FXML
    private TableColumn<ArrayList, Date> date_fin;
    @FXML
    private TableColumn<ArrayList,Float> montant_a_payer;
    @FXML
    private TableColumn<ArrayList, Integer> taux_avance;
    @FXML
    private TableColumn<ArrayList, Float> montant_paye;
    @FXML
    private TableColumn<ArrayList, Float> reste_a_payer;
    @FXML
    private TableColumn<ArrayList, String> etat;
    @FXML
    private TableColumn<ArrayList, String> user_name;
    @FXML
    private TableColumn<ArrayList, String> hotel_name; 
    @FXML
    private TextField search_user_field; 
    @FXML
    private TextField search_hotel_field; 
    @FXML
    private DatePicker search_date_field; 
    @FXML
    private Button rechercher_utilisateur;
    @FXML
    private TableColumn<ArrayList, String> editcol;
    @FXML
    private Button voiture_nav;
    
    //initilisation
     ServiceRéservationIMP service_reservation=new ServiceRéservationIMP();
     List<Object> list1= new ArrayList<>();
     ObservableList<List> obsreservationlist=FXCollections.observableArrayList();
     ServiceStripeIMP stripe=new ServiceStripeIMP(); 
     
      
     
     
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       ObservableList<List> obsreservationlist=FXCollections.observableArrayList();
       service_reservation.get_reservations_hotels().forEach((p)->{
            obsreservationlist.add(p);
        });
        loadData();
        table_reservation_hotel.setItems(obsreservationlist);
    } 
    
    

    @FXML
    private void rechercher_utilisateur(ActionEvent event) { 
        obsreservationlist.clear();
        service_reservation.get_reservations_hotel_by_user_email(search_user_field.getText()).forEach((p)->{
            obsreservationlist.add(p);
        }); 
        if(obsreservationlist.size()==0){ 
             table_reservation_hotel.getItems().clear();
        }
        else{ 
            loadData();
        }
    }
    
    @FXML
    private void rechercher_hotel(ActionEvent event) {
        obsreservationlist.clear();
        service_reservation.get_reservations_by_hotel_name(search_hotel_field.getText()).forEach((p)->{
            obsreservationlist.add(p);
        }); 
        if(obsreservationlist.size()==0){ 
             table_reservation_hotel.getItems().clear();
        }
        else{ 
            loadData();
        }
    }
    @FXML
    private void rechercher_date(ActionEvent event) {
        LocalDate date= search_date_field.getValue();  
        obsreservationlist.clear();
        service_reservation.get_reservations_hotels_by_date(date.toString()).forEach((p)->{
            obsreservationlist.add(p);
        }); 
        if(obsreservationlist.size()==0){ 
             table_reservation_hotel.getItems().clear();
        }
        else{ 
            loadData();
        }
    }
    
    @FXML
    private void clear_search_fields() {  
        search_user_field.clear(); 
        search_hotel_field.clear();
        search_date_field.setValue(null);
        table_reservation_hotel.getItems().clear();
        obsreservationlist.clear();
         service_reservation.get_reservations_hotels().forEach((p)->{
            obsreservationlist.add(p);
        });
        loadData();
        
    }
    
    
    
    public void loadData(){
      
        table_reservation_hotel.setItems(obsreservationlist); 
        System.out.println("table data"+table_reservation_hotel.getItems());
        //clear_search_fields();
         created_At.setCellValueFactory(data->{
             Timestamp obj=(Timestamp) data.getValue().get(0);
             ObservableValue<Timestamp> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
         user_name.setCellValueFactory(data->{
             String obj=(String)data.getValue().get(8);
             ObservableValue<String> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
         date_debut.setCellValueFactory(data->{
             Date obj=(Date) data.getValue().get(1);
             ObservableValue<Date> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
         date_fin.setCellValueFactory(data->{
             Date obj=(Date) data.getValue().get(2);
             ObservableValue<Date> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
         montant_a_payer.setCellValueFactory(data->{
             Float obj=(Float) data.getValue().get(3);
             ObservableValue<Float> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
         taux_avance.setCellValueFactory(data->{
             Integer obj=(Integer) data.getValue().get(4);
             ObservableValue<Integer> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
          montant_paye.setCellValueFactory(data->{
             Float obj=(Float) data.getValue().get(5);
             ObservableValue<Float> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
          reste_a_payer.setCellValueFactory(data->{
             Float obj=(Float) data.getValue().get(6);
             ObservableValue<Float> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
          hotel_name.setCellValueFactory(data->{
             String obj=(String)data.getValue().get(9);
             ObservableValue<String> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
        
        Callback<TableColumn<ArrayList, String>, TableCell<ArrayList, String>> cellFoctoryEtat;
        cellFoctoryEtat = (TableColumn<ArrayList, String> param) -> {
            // make cell containing buttons
            final TableCell<ArrayList, String> cell = new TableCell<ArrayList, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        List data=(List) this.getTableRow().getItem();
                        System.out.println("etat :: "+data);
                        Button button = new Button(); 
                        if(data!=null){ 
                            if(data.get(7).toString().equals("annulée")){
                                button.setId("annuler_etat");
                                button.setText("Annulée");
                            }else{ 
                                button.setId("confirmer_button");
                                button.setText("Confirmée");
                            }
                            HBox managebtn = new HBox(button);
                            managebtn.setStyle("-fx-alignment:center");
                              HBox.setMargin(button,new Insets(2, 2, 0, 0));
                            setGraphic(managebtn);
                            setText(null);
                        }
                        
                    }
                }

            };
           
            return cell;
        };
        
        
        Callback<TableColumn<ArrayList, String>, TableCell<ArrayList, String>> cellFoctoryAction;
          cellFoctoryAction = (TableColumn<ArrayList, String> param) -> {
            // make cell containing buttons
            final TableCell<ArrayList, String> cell = new TableCell<ArrayList, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        HBox managebtn = new HBox();
                        List data=(List)this.getTableRow().getItem();
                        
                       
                        if(data!=null){ 
                            Label label=new Label();
                            Label details_Label=new Label();
                            String etat=data.get(7).toString();
                            Date reservation_date=(Date) data.get(1);
                            System.out.println("data action factory "+data);
                            if(check_to_update(reservation_date,etat)){
                                managebtn.getChildren().addAll(label);
                            }
                            details_Label.setId("details_button");
                            details_Label.setText("Détails");
                            label.setId("annuler_button");
                            label.setText("Annuler");
                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                            deleteIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#ff1744;"
                            );
                          
                            label.setOnMouseClicked((event)->{ 
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Annuler une réservation");
                                alert.setHeaderText("Vous voulez vraiment annuler la réservation ?");
                                Optional<ButtonType> option = alert.showAndWait();
                                if (option.get() == ButtonType.OK) {
                                    List data_row=(List) table_reservation_hotel.getSelectionModel().getSelectedItem();
                                    int id_reservation=(int) data_row.get(10);
                                    service_reservation.update_reservation_by_id(id_reservation);
                                    obsreservationlist.clear();
                                    service_reservation.get_reservations_hotels().forEach((p)->{
                                        obsreservationlist.add(p);
                                    });                                    
                                    stripe.refund_customer(data_row.get(11).toString());
                                    loadData();
                                }
                                
                            });
                            deleteIcon.setOnMouseClicked((event) -> { 
                                System.out.println("selected item :: "+table_reservation_hotel.getSelectionModel().getSelectedItem());
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Supprimer une réservation");
                                alert.setHeaderText("Vous voulez vraiment effectuer la supression ?");
                                Optional<ButtonType> option = alert.showAndWait();
                                 if (option.get() == ButtonType.OK) {
                                   System.out.println("im in  delelte");
                                   System.out.println("selected item :: "+table_reservation_hotel.getSelectionModel().getSelectedItem());
                                   List data_row=(List) table_reservation_hotel.getSelectionModel().getSelectedItem();
                                   int id_reservation=(int) data_row.get(10);
                                   System.out.println("id reservation delete "+id_reservation);
                                   if(etat=="confirmée" && check_to_update(reservation_date,etat)){ 
                                        stripe.refund_customer(data_row.get(11).toString());
                                   } 
                                   service_reservation.delete_reservation_by_id(id_reservation);
                                   obsreservationlist.clear();
                                   service_reservation.get_reservations_hotels().forEach((p)->{
                                        obsreservationlist.add(p);
                                   });
                                   loadData();
                                }
                        });
                            details_Label.setOnMouseClicked((event) -> { 
                                    List data_row=(List) table_reservation_hotel.getSelectionModel().getSelectedItem();
                                    int id_reservation=(int) data_row.get(10); 
                                         FXMLLoader loader = new FXMLLoader ();
                                        loader.setLocation(getClass().getResource("../GUI/DétailsRéservationHotel.fxml"));
                                        try {
                                            loader.load();
                                        } catch (IOException ex) {
                                            Logger.getLogger(AjouterRéservationChambreController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        DétailsRéservationHotelController dt = loader.getController();
                                        dt.set_data(id_reservation,"hotel");
                                        Parent parent = loader.getRoot();
                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(parent));
                                        stage.initStyle(StageStyle.UTILITY);
                                        stage.setResizable(false);
                                        stage.show(); 
                        });
                        managebtn.getChildren().addAll(deleteIcon,details_Label);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(label,new Insets(4, 4, 0, 0));
                        HBox.setMargin(deleteIcon,new Insets(4, 4, 0, 0));
                        HBox.setMargin(details_Label,new Insets(4, 4,0, 0));
                        setGraphic(managebtn);
                        setText(null);
                        }
                    }
                }

            };
           
            return cell;
        };

         etat.setCellFactory(cellFoctoryEtat);
         editcol.setCellFactory(cellFoctoryAction);
         table_reservation_hotel.setItems(obsreservationlist); 
         
        
    }  
    
    //methods
    public boolean check_to_update(Date reservation_date_start,String etat){  
        boolean check=true; 
      
            Date todays_date=new Date(System.currentTimeMillis());
            //SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            //java.util.Date parsed = format.parse(reservation_date_start);
            //java.sql.Date sql = new java.sql.Date(parsed.getTime()); 
            if(todays_date.compareTo(reservation_date_start)>=0 || etat.equals("annulée")){ 
                  check=false;
            }
        return check;
    } 

    @FXML
    private void redirect_voiture(ActionEvent event) throws IOException { 
                Parent root = FXMLLoader.load(getClass().getResource("../GUI/AdministrateurRéservationsVoitures.fxml"));
		Scene scene = new Scene(root);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
        
    }

    @FXML
    private void go_to_tickets(ActionEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AdministrateurRéservationsEvenements.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdministrateurRéservationsEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @FXML
    private void go_to_maison(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/AdministrateurRéservationsMaisons.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AdministrateurRéservationsEvenementsController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
 
     
    

    

    
}
