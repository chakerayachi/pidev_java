/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Hani
 */
public class ReservationvoitureController implements Initializable {

    @FXML
    private ImageView imagevoiture;
    public static ImageView image;
    @FXML
    private Label labelmarque;
    public static Label marque;
    @FXML
    private Label labelmodel;
 public static Label model;
    
    @FXML
    private Label labelcapacite;
     public static Label capacite;
    @FXML
    private Label labelcouleur;
    public static Label couleur;
    @FXML
    private JFXButton detail;
    public static JFXButton deta;
    @FXML
    private Rating rate;
    public static Rating review;
    @FXML
    private Button reserver;
    public static Button reservation;
    @FXML
    private Label labelprix;
    public static Label prix;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("4444");
        image=imagevoiture;
       marque=labelmarque;
       model=labelmodel;
       capacite=labelcapacite;
        couleur = labelcouleur;
        prix=labelprix;
        deta=detail;
        reservation=reserver;
        review=rate;
        rate.setUpdateOnHover(false);
        // TODO
          System.out.println("card");
    }    

    @FXML
    private void reservation(ActionEvent event) {
        
    }
    
}
