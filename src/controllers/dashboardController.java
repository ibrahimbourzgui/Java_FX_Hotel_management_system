package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.GestionChambre;
import dao.GestionClient;
import dao.GestionReservation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;

public class dashboardController implements Initializable {

    @FXML
    private Text NChambre;

    @FXML
    private Text Ncleint;

    @FXML
    private LineChart<String, Number> chart;

    @FXML
    private CategoryAxis moisAx;

    @FXML
    private NumberAxis numberAx;

    public void Actualiser() {/*
    	XYChart.Series<String, Number> series2022 = new XYChart.Series<String, Number>();
		series2022.getData().add(new XYChart.Data<String, Number>("Jan",new GestionReservation().GetReservationByYearMonth(1, 2022)));
		series2022.getData().add(new XYChart.Data<String, Number>("Feb",new GestionReservation().GetReservationByYearMonth(2, 2022)));
		series2022.getData().add(new XYChart.Data<String, Number>("Mar",new GestionReservation().GetReservationByYearMonth(3, 2022)));
		series2022.getData().add(new XYChart.Data<String, Number>("Apr",new GestionReservation().GetReservationByYearMonth(4, 2022)));
		series2022.getData().add(new XYChart.Data<String, Number>("May",new GestionReservation().GetReservationByYearMonth(5, 2022)));
		series2022.getData().add(new XYChart.Data<String, Number>("Jun",new GestionReservation().GetReservationByYearMonth(6, 2022)));
		series2022.getData().add(new XYChart.Data<String, Number>("Jul",new GestionReservation().GetReservationByYearMonth(7, 2022)));
		series2022.getData().add(new XYChart.Data<String, Number>("Aug",new GestionReservation().GetReservationByYearMonth(8, 2022)));
		series2022.getData().add(new XYChart.Data<String, Number>("Sep",new GestionReservation().GetReservationByYearMonth(9, 2022)));
		series2022.getData().add(new XYChart.Data<String, Number>("Oct",new GestionReservation().GetReservationByYearMonth(10, 2022)));
		series2022.getData().add(new XYChart.Data<String, Number>("Nov",new GestionReservation().GetReservationByYearMonth(11, 2022)));
		series2022.getData().add(new XYChart.Data<String, Number>("Dec",new GestionReservation().GetReservationByYearMonth(12, 2022)));
		series2022.setName("2022");
		chart.getData().add(series2022);
		*/
		XYChart.Series<String, Number> series2021 = new XYChart.Series<String, Number>();
		series2021.getData().add(new XYChart.Data<String, Number>("Jan",new GestionReservation().GetReservationByYearMonth(1, 2021)));
		series2021.getData().add(new XYChart.Data<String, Number>("Feb",new GestionReservation().GetReservationByYearMonth(2, 2021)));
		series2021.getData().add(new XYChart.Data<String, Number>("Mar",new GestionReservation().GetReservationByYearMonth(3, 2021)));
		series2021.getData().add(new XYChart.Data<String, Number>("Apr",new GestionReservation().GetReservationByYearMonth(4, 2021)));
		series2021.getData().add(new XYChart.Data<String, Number>("May",new GestionReservation().GetReservationByYearMonth(5, 2021)));
		series2021.getData().add(new XYChart.Data<String, Number>("Jun",new GestionReservation().GetReservationByYearMonth(6, 2021)));
		series2021.getData().add(new XYChart.Data<String, Number>("Jul",new GestionReservation().GetReservationByYearMonth(7, 2021)));
		series2021.getData().add(new XYChart.Data<String, Number>("Aug",new GestionReservation().GetReservationByYearMonth(8, 2021)));
		series2021.getData().add(new XYChart.Data<String, Number>("Sep",new GestionReservation().GetReservationByYearMonth(9, 2021)));
		series2021.getData().add(new XYChart.Data<String, Number>("Oct",new GestionReservation().GetReservationByYearMonth(10, 2021)));
		series2021.getData().add(new XYChart.Data<String, Number>("Nov",new GestionReservation().GetReservationByYearMonth(11, 2021)));
		series2021.getData().add(new XYChart.Data<String, Number>("Dec",new GestionReservation().GetReservationByYearMonth(12, 2021)));
		series2021.setName("2021");
		chart.getData().add(series2021);   
		
		
    }

 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Ncleint.setText(String.valueOf(new GestionClient().GetNumberClient()));
		NChambre.setText(String.valueOf(new GestionChambre().GetNumberChambre()));
		Actualiser();
		//System.out.println("ssss");
	}

}
