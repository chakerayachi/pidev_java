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
 * @author zcart
 */

public class FXMain extends Application {

    
    @Override
    public void start(Stage primaryStage) throws IOException {
    
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/gestiontopicback.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("gestion forum");
        primaryStage.setScene(scene);
        primaryStage.show();
        new animatefx.animation.ZoomIn(root).play();
    }   
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
