/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author alaaz
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       try {
            //Parent root = FXMLLoader.load(getClass().getResource("../GUI/AjoutPaiement.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/StatistiquesRéservations.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("../GUI/Ajouter Réservation Chambre.fxml"));
            Scene scene = new Scene(root); 
            scene.getStylesheets().add(getClass().getResource("../GUI/paiement.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("../styles/Administrateur.css").toExternalForm());
            //primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Paiement");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
