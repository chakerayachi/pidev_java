/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.org.apache.bcel.internal.generic.INEG;
import entities.Categorie;
import entities.Images;
import entities.Voiture;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceCategorieIMP;
import services.ServiceImageIMP;
import services.ServicesVoitureIMP;

/**
 * FXML Controller class
 *
 * @author Hani
 */
public class AjoutvoitureController implements Initializable {
    

    @FXML
    private TextField model;
    @FXML
    private TextField marque;
    @FXML
    private TextField descreption;
    @FXML
    private TextField couleur;
    @FXML
    private TextField capacite;
    ServicesVoitureIMP sv = new ServicesVoitureIMP();
    ServiceCategorieIMP sc = new ServiceCategorieIMP();
    ServiceImageIMP sm = new ServiceImageIMP();
    @FXML
    private TextField immat;
    @FXML
    private TableView<List> listvoiture;
    @FXML
    private TableColumn<ArrayList, String> imm;
    @FXML
    private TableColumn<ArrayList, String> mod;
    @FXML
    private TableColumn<ArrayList, String> cou;
    @FXML
    private TableColumn<ArrayList, String> cap;
    @FXML
    private TableColumn<ArrayList, String> des;
    @FXML
    private TableColumn<ArrayList, String> mar;
    @FXML
    private Button afficher;
    Categorie t = new Categorie();
    @FXML
    private TableColumn<ArrayList, String> cat;
    int x = -1;
     List<Object> list1= new ArrayList<>();
    ObservableList<List> obsreservationlist=FXCollections.observableArrayList();
    @FXML
    private TextField categorie;
    @FXML
    private TextField chercher;
    @FXML
    private Button save;
    @FXML
    private Button supprimer;
    private int id_voiture; 
    @FXML
    private TableColumn<ArrayList, String> id_v;
    @FXML
    private Button actualiser;
    @FXML
    private Button addimage;
    @FXML
    private TextField lien;
    @FXML
    private ImageView image;
    @FXML
    private Button show;
    @FXML
    private TextField prix;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        capacite.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
                capacite.setText(newValue.replaceAll("[^\\d]", ""));
        });
        affiche();
       
 
    }  
    public void affiche(){
                ServicesVoitureIMP sp = new ServicesVoitureIMP();
              
         sp.afficherr().forEach((p)->{
       // System.out.println(p);
       obsreservationlist.add(p);
        });
       
        
        listvoiture.setItems(obsreservationlist);
        
     /* imm.setCellValueFactory(new PropertyValueFactory<>("immat"));
        mod.setCellValueFactory(new PropertyValueFactory<>("model"));
        mar.setCellValueFactory(new PropertyValueFactory<>("marque"));
         cou.setCellValueFactory(new PropertyValueFactory<>("couleur"));
          cap.setCellValueFactory(new PropertyValueFactory<>("capacite"));
           des.setCellValueFactory(new PropertyValueFactory<>("description"));
      cat.setCellValueFactory(new PropertyValueFactory<>("libelle")); */
       imm.setCellValueFactory(data->{
             String obj=(String)data.getValue().get(0);
             ObservableValue<String> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
       mar.setCellValueFactory(data->{
             String obj=(String)data.getValue().get(1);
             ObservableValue<String> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
       mod.setCellValueFactory(data->{
             String obj=(String)data.getValue().get(2);
             ObservableValue<String> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
       cou.setCellValueFactory(data->{
             String obj=(String)data.getValue().get(3);
             ObservableValue<String> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
       cap.setCellValueFactory(data->{
             String obj=(String)data.getValue().get(4);
             ObservableValue<String> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
     des.setCellValueFactory(data->{
             String obj=(String)data.getValue().get(5);
             ObservableValue<String> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
     cat.setCellValueFactory(data->{
             String obj=(String)data.getValue().get(6);
             ObservableValue<String> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
        id_v.setCellValueFactory(data->{
             String obj=(String)data.getValue().get(7);
             ObservableValue<String> obs=new SimpleObjectProperty<>(obj);
             return obs;
         });
}
       @FXML
    private void ajout(ActionEvent event) {
        if (control()){
        Voiture v = new Voiture();
          
        v.setmodel(model.getText());
        v.setCouleur(couleur.getText());
        v.setCapacite(Integer.parseInt(capacite.getText()));
        v.setImmat(immat.getText());
        v.setMarque(marque.getText());
        v.setDescription(descreption.getText());
        v.setId_categorie(sc.getIdCategorie(categorie.getText()));
         v.setPrix(prix.getText());
       int id_voi = sv.ajouter(v);
        
        Images imag = new Images();
        imag.setImg_blob(lien.getText());
        imag.setId_voiture(id_voi);
        
        sm.ajout(imag);
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès!");
            alert.setHeaderText(null);
            alert.setContentText(" voiture  est ajouté avec succès");
            alert.showAndWait();
          } else if(control()==false){

            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Erreur d'ajou !");

            alert.setHeaderText(null);

            alert.setContentText("s'il vous plais remplir tous les champs");

            alert.showAndWait();

        }
        
    }
    

   



    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
            x = listvoiture.getSelectionModel().getSelectedIndex();
            System.out.println (x);
       if(x <= -1){
           return;
       }
            model.setText(mod.getCellData(x));
            marque.setText(mar.getCellData(x));
             capacite.setText(cap.getCellData(x));
               couleur.setText(cou.getCellData(x));
                descreption.setText(des.getCellData(x));
                    immat.setText(imm.getCellData(x));
                         categorie.setText(cat.getCellData(x));
                    id_voiture=Integer.parseInt(id_v.getCellData(x));
                         
   
    }

    @FXML
    private void modifier(ActionEvent event) {
        System.out.println(id_voiture);
        
       ///List data_row=(List) listvoiture.getSelectionModel().getSelectedItem();
       //System.out.print(data_row);
       //int id_v = Integer.parseInt((String) data_row.get(6));
       System.out.print(id_voiture);
        String mode = model.getText();
        System.out.print(mode);
        String marq = marque.getText();
        String pri = prix.getText();
        int capa = Integer.parseInt(capacite.getText());
      
        String coul= couleur.getText();
        String desc = descreption.getText();
        String imma = immat.getText();
    int cate = sc.getIdCategorie(categorie.getText());
                Voiture r = new Voiture(id_voiture,imma, mode, marq, coul, capa , desc,cate,pri);
       sv.modifier(r); 
       
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès!");
            alert.setHeaderText(null);
            alert.setContentText("modification avec succès");
            alert.showAndWait();
        
        
    }

    @FXML
    private void delete(ActionEvent event) {
        
                             
                            System.out.print(id_voiture );
                            obsreservationlist.clear();
                            listvoiture.getItems().clear();
                            sv.supprimer(id_voiture); 
                            sv.afficherr().forEach((p)->{
                            obsreservationlist.add(p);
                            });
                             
                            
    }

   
 private boolean control(){

        if(immat.getText().isEmpty() || model.getText().isEmpty() ||  couleur.getText().isEmpty()|| capacite.getText().isEmpty() || descreption.getText().isEmpty() || marque.getText().isEmpty() || categorie.getText().isEmpty() ) {

            return false;

        } else {

            return true;

        }
   
 }                    

    @FXML
    private void actualiser(ActionEvent event) {
            obsreservationlist.clear();    
        listvoiture.getItems().clear(); 
        affiche();
    }

    @FXML
    private void addimage(ActionEvent event) {
          FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Upload File Path");
    fileChooser.getExtensionFilters().addAll(
           
            new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png")
    );


    File file = fileChooser.showOpenDialog(addimage.getScene().getWindow());

    if (file != null) {
        // pickUpPathField it's your TextField fx:id
        lien.setText(file.getPath());

    } else  {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("image");
            alert.setHeaderText(null);
            alert.setContentText("soisir une image s'l vous plais");
            alert.showAndWait();
    }
   String bath = lien.getText();
image.setAccessibleText(bath);
}

    @FXML
    private void show(ActionEvent event) {
        String bath = lien.getText();
image.setImage(new Image  ("file:"+bath));
    }
        
   
   
}
