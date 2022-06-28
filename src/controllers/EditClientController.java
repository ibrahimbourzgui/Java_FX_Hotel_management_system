package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import bean.Client;
import dao.GestionClient;
import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EditClientController implements Initializable {

    static int id_de_client_a_modifier = 0;
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
    private Button EditBtn;

    @FXML
    private Button clearbtn;

    @FXML
    private Button CancelBtn;

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

    @FXML
    void Edit(ActionEvent event) {
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
 	   	erreur  = new GestionClient().UpdateAll(id_de_client_a_modifier,new Client(0,nom,prenom,telephone,cin));
 	   		if(erreur) {
 	   		Alert alert = new Alert(AlertType.INFORMATION,"La modification a été réussi !",javafx.scene.control.ButtonType.FINISH);
 			alert.showAndWait();
 			this.Close(event);
 	   		}else {
 	   			Alert alert = new Alert(AlertType.ERROR,"Une erreur s'est produit. \n Essayez-vous de verifier ce que vous avez entrer",javafx.scene.control.ButtonType.FINISH);
 	   			alert.showAndWait();
 	   		}
     }else {
     	Alert alert = new Alert(AlertType.ERROR,sb.toString(),javafx.scene.control.ButtonType.OK);
 		alert.showAndWait();	
    	 }
     
     }catch(Exception e) {
     	Alert alert = new Alert(AlertType.ERROR,"Entrée non valide ! : "+e.getMessage(),javafx.scene.control.ButtonType.OK);
 		alert.showAndWait();
     }
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
	
	void initData(Client c) {
		
		id_de_client_a_modifier = c.getId();
		nomText.setText(c.getNom());
		prenomtext.setText(c.getPrenom());
		cintext.setText(c.getCin());
		telephoneText.setText(c.getTelephone());
	}

}
