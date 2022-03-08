/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author alaaz
 */
public class StatistiquesRéservationsController implements Initializable {

    @FXML
    private AnchorPane statistics_container;
    @FXML
    private Label hotels_nav_button;
    @FXML
    private Label masions_nav_button;
    @FXML
    private Label voitures_nav_button;
    @FXML
    private Label evenements_nav_button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            hotels_nav_button.getStyleClass().add("pressed_button");
            AnchorPane view =FXMLLoader.load(getClass().getResource("../GUI/StatistiquesRéservationsHotels.fxml"));
            statistics_container.getChildren().clear();
            statistics_container.getChildren().add(view);
        } catch (IOException ex) {
            Logger.getLogger(StatistiquesRéservationsController.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    } 

    @FXML
    private void get_hotels_statistics(MouseEvent event) { 
        hotels_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        masions_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        voitures_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        evenements_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        hotels_nav_button.getStyleClass().add("pressed_button");
        try {
            AnchorPane view =FXMLLoader.load(getClass().getResource("../GUI/StatistiquesRéservationsHotels.fxml"));
            statistics_container.getChildren().clear();
            statistics_container.getChildren().add(view);
        } catch (IOException ex) {
            Logger.getLogger(StatistiquesRéservationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void get_houses_statistics(MouseEvent event) {
        hotels_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        masions_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        voitures_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        evenements_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        masions_nav_button.getStyleClass().add("pressed_button"); 
        try {
            AnchorPane view =FXMLLoader.load(getClass().getResource("../GUI/StatistiquesRéservationsMaisons.fxml"));
            statistics_container.getChildren().clear();
            statistics_container.getChildren().add(view);
        } catch (IOException ex) {
            Logger.getLogger(StatistiquesRéservationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void get_cars_statistics(MouseEvent event) {
         hotels_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        masions_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        voitures_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        evenements_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        voitures_nav_button.getStyleClass().add("pressed_button"); 
        try {
            AnchorPane view =FXMLLoader.load(getClass().getResource("../GUI/StatistiquesRéservationsVoitures.fxml"));
            statistics_container.getChildren().clear();
            statistics_container.getChildren().add(view);
        } catch (IOException ex) {
            Logger.getLogger(StatistiquesRéservationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void get_events_statistics(MouseEvent event) {
        hotels_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        masions_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        voitures_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        evenements_nav_button.getStyleClass().removeIf(style -> style.equals("pressed_button"));
        evenements_nav_button.getStyleClass().add("pressed_button");
        try {
            AnchorPane view =FXMLLoader.load(getClass().getResource("../GUI/StatistiquesRéservationsEvenements.fxml"));
            statistics_container.getChildren().clear();
            statistics_container.getChildren().add(view);
        } catch (IOException ex) {
            Logger.getLogger(StatistiquesRéservationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     

    
}
