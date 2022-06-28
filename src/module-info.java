module HotelFx {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	opens application to javafx.graphics, javafx.fxml;
	exports bean;
	exports controllers;
	opens controllers to javafx.graphics, javafx.fxml;
	opens bean to javafx.graphics, javafx.fxml;

}
