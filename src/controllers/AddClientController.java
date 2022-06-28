package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import bean.Chambre;
import bean.Client;
import dao.GestionChambre;
import dao.GestionClient;
import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddClientController implements Initializable  {


		String nom,prenom,cin,telephone;

		@FXML
	    private TextField cintext;

	    @FXML
	    private TextField nomText;

	    @FXML
	    private TextField prenomtext;

	    @FXML
	    private TextField telephoneText;

	    @FXML
	    private Button AddBtn;

	    @FXML
	    private Button clearbtn;

	    @FXML
	    private Button CancelBtn;
	    @FXML
	    void Add(ActionEvent event) {
	    	 Boolean erreur = false;
	         StringBuffer sb = new StringBuffer("");
	     try {
	    	 
	    	 nom = nomText.getText();
	    	 prenom = prenomtext.getText();
	    	 cin = cintext.getText();
	    	 telephone = telephoneText.getText();
	    	 
	     if(nom.isEmpty() || prenom.isEmpty() || cin.isEmpty() || telephone.isEmpty()) {
	 	 	sb.append("=> Tous les champs sont requis! \n"); 
	 	   	erreur = true;
	 	 } 
	      	     
	      if(!erreur) {
	 	   	erreur  = new GestionClient().AddClient(new Client(0,nom,prenom,telephone,cin));
	 	   		if(erreur) {
	 	   		Alert alert = new Alert(AlertType.INFORMATION,"L'Ajoute a été réussi !",javafx.scene.control.ButtonType.FINISH);
	 			alert.showAndWait();
	 			this.Close(event);
	 	   		}else {
	 	   			Alert alert = new Alert(AlertType.ERROR,"Une erreur s'est produit. \n essayez-vous de verifier ce que vous avez entrer",javafx.scene.control.ButtonType.FINISH);
	 	   			alert.showAndWait();
	 	   		}
	 	   		//new GestionChambreController().Actualiser();
	     }else {
	     	Alert alert = new Alert(AlertType.ERROR,sb.toString(),javafx.scene.control.ButtonType.OK);
	 		alert.showAndWait();	
	    	 }
	     
	     }catch(Exception e) {
	     	Alert alert = new Alert(AlertType.ERROR,"Entrée non valide ! : "+e.getMessage(),javafx.scene.control.ButtonType.OK);
	 		alert.showAndWait();
	     	//.out.println(e.getMessage()+"ddd");
	     }
	    }

	    @FXML
	    void Clear(ActionEvent event) {
	    	nomText.setText(null);
	    	prenomtext.setText(null);
	    	telephoneText.setText(null);
	    	cintext.setText(null);
	    }

	    @FXML
	    void Close(ActionEvent event) {
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.close();
	    }

	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		telephoneText.textProperty().addListener((ChangeListener<? super String>) new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				 if (!arg2.matches("\\d")) {
					 telephoneText.setText(arg2.replaceAll("[^\\d]", ""));
			        }}
		});			
	}

}
