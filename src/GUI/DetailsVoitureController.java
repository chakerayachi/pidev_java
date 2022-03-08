/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import entities.Images;
import entities.Voiture;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import services.ServiceImageIMP;
import services.ServicesVoitureIMP;

/**
 * FXML Controller class
 *
 * @author Hani
 */
public class DetailsVoitureController implements Initializable {
    
    
    public static Voiture voiture;
    ServiceImageIMP sm = new ServiceImageIMP();
    ServicesVoitureIMP sv = new ServicesVoitureIMP();
    private ImageView imgview;
    @FXML
    private JFXButton retour;
    
    @FXML
    private AnchorPane df;
    @FXML
    private FlowPane fp;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        int id_voiture = voiture.getId();
        List<Images> images = new ArrayList<>();
        images=sm.afficherimagebyid_voiture(id_voiture);
       
       // System.out.println(idsujet);
        for (Images i:images) {
            
            try {
                Parent imageFXML = FXMLLoader.load(getClass().getResource("/GUI/cardview.fxml"));
                //int id= voiture.getId();
                //int id_img = sm.afficherimagebyid_voiture(id).getImg_id();
                //int id= voiture.getId();
                //System.out.println("yoooo"+id);
                String path= i.getImg_blob();
                Image img = new Image("file:"+path);
                ImageView imgV = new ImageView();
                imgV.setImage(img);
             CardviewController.imgvoiture.setImage(img);
             System.out.println(path);
             
           fp.getChildren().add(imageFXML);     
            }
            // TODO
            
            catch (IOException ex) {
                Logger.getLogger(DetailsVoitureController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
    }    

    @FXML
    private void back(ActionEvent event) {
        try {
                                                        DetailsVoitureController dc = new DetailsVoitureController();
                                                       

                                                        Parent root = FXMLLoader.load(getClass().getResource("../GUI/Uiclientreservationvoiture.fxml"));
                                                        Scene scene = new Scene(root);
                                                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                                        stage.setScene(scene);
                                                        stage.show();
                                                    } catch (IOException ex) {
                                                        Logger.getLogger(DetailsVoitureController.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
    }

    @FXML
    private void reserver(ActionEvent event) {
         try {
                                                        AjouterRéservationVoitureController.voiture=voiture;
                                                       

                                                        Parent root = FXMLLoader.load(getClass().getResource("../GUI/Ajouter Réservation Voiture.fxml"));
                                                        Scene scene = new Scene(root);
                                                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                                        stage.setScene(scene);
                                                        stage.show();
                                                    } catch (IOException ex) {
                                                        Logger.getLogger(DetailsVoitureController.class.getName()).log(Level.SEVERE, null, ex);
             }
    }
    }


