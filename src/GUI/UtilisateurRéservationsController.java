/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.KeyValuePair;
import static entities.Utilisateur.user_connecté;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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
public class UtilisateurRéservationsController implements Initializable {

    @FXML
    private TableView<List> table_reservation_utilisateur;
    @FXML
    private TableColumn<ArrayList, Timestamp> created_At;
    @FXML
    private TableColumn<ArrayList, Float> montant_a_payer;
    @FXML
    private TableColumn<ArrayList, Integer> taux_avance;
    @FXML
    private TableColumn<ArrayList, Float> montant_paye;
    @FXML
    private TableColumn<ArrayList, Float> reste_a_payer;
    @FXML
    private TableColumn<ArrayList, String> etat;
    @FXML
    private TableColumn<ArrayList, String> type;
    @FXML
    private TableColumn<ArrayList, String> editcol;
    @FXML
    private DatePicker search_field_date;
    @FXML
    private Button rechercher_utilisateur;
    @FXML
    private ChoiceBox<String> select_field_reservation_type;
    
    
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
       service_reservation.get_reservations_by_user_id(32).forEach((p)->{
            obsreservationlist.add(p);
        });
        loadData();
        table_reservation_utilisateur.setItems(obsreservationlist);
        select_field_reservation_type.setValue("tous");
        select_field_reservation_type .getItems().addAll("tous","hotel","maison","voiture","ticket"); 
        
        
    }    

    @FXML
    private void rechercher_reservation_type(ActionEvent event) {   
        search_field_date.setValue(null);
        obsreservationlist.clear();
        if(select_field_reservation_type.getValue()=="tous"){  
            service_reservation.get_reservations_user_by_type(32,"tous").forEach((p)->{
                obsreservationlist.add(p);
            });
            
        }else if(select_field_reservation_type.getValue()=="hotel"){ 
            service_reservation.get_reservations_user_by_type(32,"hotel").forEach((p)->{
                obsreservationlist.add(p);
            });
            
        }else if(select_field_reservation_type.getValue()=="maison"){  
            System.out.println("im here ");
            service_reservation.get_reservations_user_by_type(32,"maison").forEach((p)->{
                obsreservationlist.add(p);
            });
            
        }else if(select_field_reservation_type.getValue()=="voiture"){
             service_reservation.get_reservations_user_by_type(32,"voiture").forEach((p)->{
                obsreservationlist.add(p);
            });
            
        }else { 
            service_reservation.get_reservations_user_by_type(32,"ticket").forEach((p)->{
                obsreservationlist.add(p);
            });  
        }
        if(obsreservationlist.size()==0){ 
             table_reservation_utilisateur.getItems().clear();
        }
        else{ 
            loadData();
        }
    }


    @FXML
    private void rechercher_reservation_date(ActionEvent event) {
        obsreservationlist.clear();
        String date = search_field_date.getValue().toString();
        String type=select_field_reservation_type.getValue();
        if(select_field_reservation_type.getValue()=="tous"){  
            service_reservation.get_reservations_user_by_date(date,type,32).forEach((p)->{
                obsreservationlist.add(p);
            });
            
        }else if(select_field_reservation_type.getValue()=="hotel"){ 
            service_reservation.get_reservations_user_by_date(date,type,32).forEach((p)->{
                obsreservationlist.add(p);
            });
            
        }else if(select_field_reservation_type.getValue()=="maison"){ 
            service_reservation.get_reservations_user_by_date(date,type,32).forEach((p)->{
                obsreservationlist.add(p);
            });
            
        }else if(select_field_reservation_type.getValue()=="voiture"){
             service_reservation.get_reservations_user_by_date(date,type,32).forEach((p)->{
                obsreservationlist.add(p);
            });
            
        }else { 
            service_reservation.get_reservations_user_by_date(date,type,32).forEach((p)->{
                obsreservationlist.add(p);
            });  
        }
         if(obsreservationlist.size()==0){ 
             table_reservation_utilisateur.getItems().clear();
        }
        else{ 
            loadData();
        }
    }

    @FXML
    private void clear_search_fields(ActionEvent event) {  
          obsreservationlist.clear();
          search_field_date.setValue(null);
          service_reservation.get_reservations_by_user_id(32).forEach((p)->{
            obsreservationlist.add(p);
          });
          loadData();
    }
    
    
    
    
    
    
    public void loadData(){
      
        table_reservation_utilisateur.setItems(obsreservationlist); 
        System.out.println("table data"+table_reservation_utilisateur.getItems());
        //clear_search_fields();
         created_At.setCellValueFactory(data->{
             Timestamp obj=(Timestamp) data.getValue().get(1);
             ObservableValue<Timestamp> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
         montant_a_payer.setCellValueFactory(data->{
             Float obj=(Float) data.getValue().get(4);
             ObservableValue<Float> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
         taux_avance.setCellValueFactory(data->{
             Integer obj=(Integer) data.getValue().get(6);
             ObservableValue<Integer> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
          montant_paye.setCellValueFactory(data->{
             Float obj=(Float) data.getValue().get(7);
             ObservableValue<Float> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
          reste_a_payer.setCellValueFactory(data->{
             Float obj=(Float) data.getValue().get(5);
             ObservableValue<Float> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
          type.setCellValueFactory(data->{
             String obj=(String)data.getValue().get(13);
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
                        Button button = new Button(); 
                        if(data!=null){ 
                            if(data.get(8).toString().equals("annulée")){
                                button.setId("annuler_etat");
                                button.setText("Annulée");
                            }else{ 
                                button.setId("confirmer_button");
                                button.setText("Confirmée");
                            }
                            HBox managebtn = new HBox(button);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(button,new Insets(2, 2, 8, 8));
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
                            String etat=data.get(8).toString();
                            Date reservation_date=(Date) data.get(2);
                            if(check_to_update(reservation_date,etat)){
                                managebtn.getChildren().addAll(label);
                            }
                             details_Label.setId("details_button");
                             details_Label.setText("Détails");
                             label.setId("annuler_button");
                             label.setText("Annuler");
                           
                           
                            label.setOnMouseClicked((event)->{ 
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Annuler une réservation");
                                alert.setHeaderText("Vous voulez vraiment annuler la réservation ?");
                                Optional<ButtonType> option = alert.showAndWait();
                                if (option.get() == ButtonType.OK) {
                                    List data_row=(List) table_reservation_utilisateur.getSelectionModel().getSelectedItem();
                                    int id_reservation=(int) data_row.get(0);
                                    service_reservation.update_reservation_by_id(id_reservation);
                                    obsreservationlist.clear();
                                    service_reservation.get_reservations_hotels().forEach((p)->{
                                        obsreservationlist.add(p);
                                    });                                    
                                    stripe.refund_customer(data_row.get(14).toString());
                                    loadData();
                                }
                                
                            });
                            details_Label.setOnMouseClicked((event) -> { 
                                    List data_row=(List) table_reservation_utilisateur.getSelectionModel().getSelectedItem();
                                    int id_reservation=(int) data_row.get(0); 
                                    String type=data_row.get(13).toString(); 
                                    if(type.equals("hotel")){
                                         FXMLLoader loader = new FXMLLoader ();
                                        loader.setLocation(getClass().getResource("../GUI/DétailsRéservationHotel.fxml"));
                                        try {
                                            loader.load();
                                        } catch (IOException ex) {
                                            Logger.getLogger(AjouterRéservationChambreController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        DétailsRéservationHotelController dt = loader.getController();
                                        dt.set_data(id_reservation,type);
                                        Parent parent = loader.getRoot();
                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(parent));
                                        stage.initStyle(StageStyle.UTILITY);
                                        stage.show();
                                    }else if (type.equals("maison")){ 
                                        FXMLLoader loader = new FXMLLoader ();
                                        loader.setLocation(getClass().getResource("../GUI/DétailsRéservationMaison.fxml"));
                                        try {
                                            loader.load();
                                        } catch (IOException ex) {
                                            Logger.getLogger(AjouterRéservationChambreController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        DétailsRéservationMaisonController dt = loader.getController();
                                        dt.set_data(id_reservation,type);
                                        Parent parent = loader.getRoot();
                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(parent));
                                        stage.initStyle(StageStyle.UTILITY);
                                        stage.show();
                                    }else if(type.equals("voiture")){
                                        FXMLLoader loader = new FXMLLoader ();
                                        loader.setLocation(getClass().getResource("../GUI/DétailsRéservationVoiture.fxml"));
                                        try {
                                            loader.load();
                                        } catch (IOException ex) {
                                            Logger.getLogger(AjouterRéservationChambreController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        DétailsRéservationVoitureController dt = loader.getController();
                                        dt.set_data(id_reservation,type);
                                        Parent parent = loader.getRoot();
                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(parent));
                                        stage.initStyle(StageStyle.UTILITY);
                                        stage.show(); 
                                    }else{ 
                                         FXMLLoader loader = new FXMLLoader ();
                                        loader.setLocation(getClass().getResource("../GUI/DétailsRéservationTicket.fxml"));
                                        try {
                                            loader.load();
                                        } catch (IOException ex) {
                                            Logger.getLogger(AjouterRéservationChambreController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        DétailsRéservationTicketController dt = loader.getController();
                                        dt.set_data(id_reservation,type);
                                        Parent parent = loader.getRoot();
                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(parent));
                                        stage.initStyle(StageStyle.UTILITY);
                                        stage.show(); 
                                        
                                    }
                                    
                        });
                        
                        managebtn.getChildren().addAll(details_Label);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(label,new Insets(2, 2, 8, 8));
                        HBox.setMargin(details_Label,new Insets(2, 2, 8, 8));
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
         table_reservation_utilisateur.setItems(obsreservationlist); 
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
    
}
