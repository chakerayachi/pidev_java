/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import services.ServiceRéservationIMP;

/**
 * FXML Controller class
 *
 * @author alaaz
 */
public class StatistiquesRéservationsMaisonsController implements Initializable {

    @FXML
    private Pane statistics_data_container;
    @FXML
    private Pane statistics_data_container1;
    @FXML
    private Pane statistics_data_container2;
    @FXML
    private Pane statistics_data_container3;
    ServiceRéservationIMP service_reservation=new ServiceRéservationIMP();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        statistics_data_container.getChildren().add(buildBarChart()); 
        statistics_data_container1.getChildren().add(buildPieChart());
        statistics_data_container2.getChildren().add(buildBarChartGainsEstimationbyDate_annualy());
        statistics_data_container3.getChildren().add(buildLineChartIncomesMonthly());
    }
    
     private BarChart buildBarChart() { 
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart barChart = new BarChart(xAxis, yAxis);
        XYChart.Series dataSeries1 = new XYChart.Series();
        service_reservation.get_statistics_incomes_maisons().forEach((p)->{
            dataSeries1.getData().add(new XYChart.Data(p.get(0), p.get(1)));
        });
        barChart.getData().add(dataSeries1);
        barChart.setId("bar_chart");
        barChart.setPrefHeight(370);
        barChart.setPrefWidth(340); 
        barChart.setLegendVisible(false);
        return barChart;
    }
     
      private BarChart buildBarChartGainsEstimationbyDate_annualy() { 
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart barChart = new BarChart(xAxis, yAxis);
        XYChart.Series dataSeries1 = new XYChart.Series();
        int year = Calendar.getInstance().get(Calendar.YEAR); 
        String last_year=String.valueOf(year-1);
        String this_year=String.valueOf(year);
        dataSeries1.getData().add(new XYChart.Data(last_year, service_reservation.get_statistics_incomes_annualy_maisons(last_year)));
        dataSeries1.getData().add(new XYChart.Data(this_year, service_reservation.get_statistics_incomes_annualy_maisons(this_year)));
        barChart.getData().add(dataSeries1);
        barChart.setId("bar_chart");
        barChart.setPrefHeight(190);
        barChart.setPrefWidth(324);
        barChart.setLegendVisible(false);
        return barChart;
    } 
      private LineChart buildLineChartIncomesMonthly(){ 
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            final LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis); 
            XYChart.Series series = new XYChart.Series(); 
            String this_year=String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            series.getData().add(new XYChart.Data("Jan", service_reservation.get_statistics_incomes_monthly_maisons("01",this_year)));
            series.getData().add(new XYChart.Data("Feb",  service_reservation.get_statistics_incomes_monthly_maisons("02",this_year)));
            series.getData().add(new XYChart.Data("Mar",  service_reservation.get_statistics_incomes_monthly_maisons("03",this_year)));
            series.getData().add(new XYChart.Data("Apr",  service_reservation.get_statistics_incomes_monthly_maisons("04",this_year)));
            series.getData().add(new XYChart.Data("May",  service_reservation.get_statistics_incomes_monthly_maisons("05",this_year)));
            series.getData().add(new XYChart.Data("Jun",  service_reservation.get_statistics_incomes_monthly_maisons("06",this_year)));
            series.getData().add(new XYChart.Data("Jul",  service_reservation.get_statistics_incomes_monthly_maisons("07",this_year)));
            series.getData().add(new XYChart.Data("Aug",  service_reservation.get_statistics_incomes_monthly_maisons("08",this_year)));
            series.getData().add(new XYChart.Data("Sep",  service_reservation.get_statistics_incomes_monthly_maisons("09",this_year)));
            series.getData().add(new XYChart.Data("Oct",  service_reservation.get_statistics_incomes_monthly_maisons("10",this_year)));
            series.getData().add(new XYChart.Data("Nov",  service_reservation.get_statistics_incomes_monthly_maisons("11",this_year)));
            series.getData().add(new XYChart.Data("Dec",  service_reservation.get_statistics_incomes_monthly_maisons("12",this_year)));
            lineChart.setPrefHeight(190);
            lineChart.setPrefWidth(324);
            lineChart.getData().add(series); 
            lineChart.setLegendVisible(false);
            return lineChart;
      }
     
     private PieChart buildPieChart() {
        //Preparing ObservbleList object
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        service_reservation.get_statistics_nb_reservations_maisons().forEach((p)->{
             pieChartData.add(new PieChart.Data(p.get(0).toString(), Integer.valueOf(p.get(1).toString())));
        });
        PieChart pieChart = new PieChart(pieChartData); //Creating a Pie chart 
        pieChart.setPrefHeight(320);
        pieChart.setPrefWidth(450);
        //attach tooltips
        createToolTips(pieChart);
        pieChart.setClockwise(true); //setting the direction to arrange the data 
        pieChart.setLabelLineLength(50); //Setting the length of the label line 
        pieChart.setLabelsVisible(true); //Setting the labels of the pie chart visible
        pieChart.setLegendVisible(false);
        //pieChart.setStartAngle(180);

        //bind value and label on each pie slice to reflect changes
        pieChartData.forEach(data ->
                data.nameProperty().bind(Bindings.concat(data.getName(), " ", data.pieValueProperty(), " ")
                ));
        ContextMenu contextMenu = new ContextMenu(); //create context menu
        MenuItem miSwitchToBarChart = new MenuItem("Switch to Bar Chart");
        contextMenu.getItems().add(miSwitchToBarChart);

        //Add event handler to display context menu
        pieChart.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            contextMenu.show(pieChart, event.getScreenX(), event.getScreenY());
                        }                        
                    }
                });
        return pieChart;
    }
      private void createToolTips(PieChart pc) {

        for (PieChart.Data data: pc.getData()) {
            String msg = Double.toString(data.getPieValue());

            Tooltip tp = new Tooltip(msg);

            Tooltip.install(data.getNode(), tp);

            //update tooltip data when changed
            data.pieValueProperty().addListener((observable, oldValue, newValue) ->
            {
                tp.setText(newValue.toString());
            });
        }
    }
    
}
