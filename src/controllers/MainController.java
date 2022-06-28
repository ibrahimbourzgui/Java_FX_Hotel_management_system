package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import bean.Administrateur;
import dao.Authentification;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.TextField;

public class MainController implements Initializable {
	Connection cnx;
	public PreparedStatement st;
	public ResultSet rs;
	
    @FXML
    private TextField login;

    @FXML
    private PasswordField passF;


	@FXML
	private VBox VBox;

    @FXML
    private Button btn_seconnecter;
    private Parent fxml;
/**/
    @FXML
    private Button btnexit;
    
    private DialogPane dialogPane;

    

    @FXML
    void exit(ActionEvent event) {
    	Alert alert =new Alert(AlertType.CONFIRMATION);
    	
    	alert.setHeaderText("Quitter l'application ?");
    	alert.setContentText("Voulez vous vraimenet sortir de l'application ? :");
    	 dialogPane = alert.getDialogPane();
    	 dialogPane.getStylesheets().add(
    	       getClass().getResource("/application/application.css").toExternalForm());
    	    dialogPane.getStyleClass().add("dialogPane");
    	    
    	    
    	
    	if(alert.showAndWait().get()==ButtonType.OK) {
    		VBox.getScene().getWindow().hide();
    	}
    }
    
    
    
    @FXML
    void openHome() {
    	
		String nom = login.getText();
    	String passw= passF.getText();
    	Administrateur admin = new Authentification().Login(nom, passw);
    	
    	if(admin != null) {
		VBox.getScene().getWindow().hide();
		Stage home= new Stage();
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/home.fxml"));
				HomeController c = loader.getController();
				c.username = admin.getLogin();
				Scene scene =new Scene(loader.load(),900,500);
				home.setScene(scene);
				home.show();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			Alert alertt = new Alert(AlertType.ERROR,"nom ou mot de passe est incorrect",javafx.scene.control.ButtonType.OK);
	    	 dialogPane = alertt.getDialogPane();
	    	 dialogPane.getStylesheets().add(
	    	       getClass().getResource("/application/application.css").toExternalForm());
	    	    dialogPane.getStyleClass().add("dialogPane");
				alertt.showAndWait();

	    	    
		}  	
    }
  
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Transition image to right with 1sc duration
		TranslateTransition t= new TranslateTransition(Duration.seconds(1),VBox);
		t.setToX(VBox.getLayoutX() * 90.5);
		t.play();
	}

}
