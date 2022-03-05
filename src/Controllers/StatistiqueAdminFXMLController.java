/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.util.Duration;
import services.ServiceUtilisateurIMP;

/**
 * FXML Controller class
 *
 * @author chaker
 */
public class StatistiqueAdminFXMLController implements Initializable {

    @FXML
    private PieChart pieChart;
    ServiceUtilisateurIMP su = new ServiceUtilisateurIMP();
    @FXML
    private BarChart<String, Integer> barchart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // pie chart
        Map<String, Integer> countMap = new HashMap<>();
        countMap.putAll(su.countUsersByRole());

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        countMap.forEach((role, count) -> {
            pieChartData.add(new PieChart.Data(role, count));
        });
        pieChart.setTitle("Statistiques des roles des utilisateurs");
        pieChart.setData(pieChartData);
        FadeTransition transition = new FadeTransition(Duration.seconds(3), pieChart);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.play();

        // bar chart
        Map<String, Integer> countMapDate = new HashMap<>();
        countMapDate.putAll(su.countUsersByDate());
        XYChart.Series<String, Integer> barSeries = new XYChart.Series();
        barSeries.setName("Date Creation");
        countMapDate.forEach((date, count) -> {
            barSeries.getData().add(new XYChart.Data(date, count));

        });
        barchart.setTitle("Statistiques les Dates ");
        barchart.getData().addAll(barSeries);

    }

    @FXML
    private void goToAdminDashboard(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AdminDashboardFXML.fxml"));
            Parent root = loader.load();
            pieChart.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(StatistiqueAdminFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

}
