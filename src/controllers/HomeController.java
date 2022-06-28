package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HomeController implements Initializable  {
	
	public static String username;
    @FXML
	private Parent fxml;
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane parentt;
    @FXML
    private Label Username;
    
    @FXML
    private ImageView logoutBtn;
    Stage stage;
    private DialogPane dialogPane;

 
    @FXML
    void logout(MouseEvent event) {
		
		Alert alert =new Alert(AlertType.CONFIRMATION);

    	alert.setHeaderText("Quitter l'application ?");
    	alert.setContentText("Voulez vous vraimenet sortir de l'application ? :");
    	 dialogPane = alert.getDialogPane();
    	 dialogPane.getStylesheets().add(
    	       getClass().getResource("/application/application.css").toExternalForm());
    	    dialogPane.getStyleClass().add("dialogPane");
    	    

    	if(alert.showAndWait().get()==ButtonType.OK) {
    		stage= (Stage) root.getScene().getWindow();
    		stage.close();    	
    	
    		
    	}
		


    }
    @FXML
    void chambre(MouseEvent event) {
    	try {
			fxml = FXMLLoader.load(getClass().getResource("/interfaces/GestionChambres.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void clients(MouseEvent event) {
		try {
			fxml = FXMLLoader.load(getClass().getResource("/interfaces/GestionClients.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void dash(MouseEvent event) {
    	
    	try {
			fxml = FXMLLoader.load(getClass().getResource("/interfaces/Dashboard.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void reservation(MouseEvent event) {
    	try {
			fxml = FXMLLoader.load(getClass().getResource("/interfaces/GestionReservations.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
   


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Username.setText(username);
		try {
			

			fxml = FXMLLoader.load(getClass().getResource("/interfaces/Dashboard.fxml"));
			root.getChildren().removeAll();
			root.getChildren().setAll(fxml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}

	
}
