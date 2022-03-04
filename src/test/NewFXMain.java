/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Wael
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/ClientHotel.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("../GUI/Stylesheet.css").toExternalForm());
            primaryStage.setScene(scene);
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
