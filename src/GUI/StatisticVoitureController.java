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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.ServiceAvisIMP;
import services.ServiceCategorieIMP;
import services.ServicesVoitureIMP;

/**
 * FXML Controller class
 *
 * @author Hani
 */
public class StatisticVoitureController implements Initializable {

    @FXML
    private PieChart pievoiture;
    @FXML
    private BarChart<String, Number> barmarque;
    ServiceCategorieIMP sc = new ServiceCategorieIMP();
    ServicesVoitureIMP sv = new ServicesVoitureIMP();
    @FXML
    private ImageView retour;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("luxe", sv.nombre_des_voiture_by_categorie(1)),
                new PieChart.Data("familial", sv.nombre_des_voiture_by_categorie(2)),
                new PieChart.Data("4x4", sv.nombre_des_voiture_by_categorie(3)),
                new PieChart.Data("sport", sv.nombre_des_voiture_by_categorie(0)));
                
        pievoiture.setData(pieChartData);
        pievoiture.setTitle("les voiture par categorie ");
        
        
        final Label caption = new Label("");
caption.setTextFill(Color.DARKORANGE);
caption.setStyle("-fx-font: 24 arial;");

for (final PieChart.Data data : pievoiture.getData()) {
    data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
        new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                caption.setTranslateX(e.getSceneX());
                caption.setTranslateY(e.getSceneY());
                caption.setText(String.valueOf(data.getPieValue()) + "%");
             }
        });
}
       CategoryAxis xAxis = new CategoryAxis();
       xAxis.setLabel("les marque des voiture");

       NumberAxis yAxis = new NumberAxis();
       yAxis.setLabel("Percent");
   //BarChart<String, Number> barmarque = new BarChart<String, Number>(xAxis, yAxis);

       // Series 1 - Data of 2014
       XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
       dataSeries1.setName("nombre des voiture par capacité");

       dataSeries1.getData().add(new XYChart.Data<String, Number>("2 place", 5));
       dataSeries1.getData().add(new XYChart.Data<String, Number>("4 place", 10));
       dataSeries1.getData().add(new XYChart.Data<String, Number>("6 place", 2));
       barmarque.getData().addAll(dataSeries1);
    }    

    @FXML
    private void retour(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../GUI/ajoutvoiture.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StatisticVoitureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
