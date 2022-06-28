package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import bean.Chambre;
import dao.GestionChambre;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class EditChambreController implements Initializable {
		Integer dispo, srv, numero;
	    String type;
	    Double prix;
	    static int id_de_chambre_a_modifier = 0;
	   @FXML
	    private TextField numeroText;

	    @FXML
	    private TextArea typeText;

	    @FXML
	    private ComboBox<Integer> serviceText;

	    @FXML
	    private ComboBox<Integer> dispoText;

	    @FXML
	    private TextField prixText;

	    @FXML
	    private Button EditBtn;

	    @FXML
	    private Button clearbtn;

	    @FXML
	    private Button CancelBtn;

	    @FXML
	    void Clear(ActionEvent event) {
	    	numeroText.setText(null);
	    	prixText.setText(null);
	    	serviceText.setValue(0);
	    	dispoText.setValue(0);
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
	         prix = Double.parseDouble(prixText.getText());
	         //System.out.println("fet prix   :"+prix);    
	         dispo =  dispoText.getSelectionModel().getSelectedItem();
		     //System.out.println("fet dispo  :"+dispo);
		   	 srv =  serviceText.getSelectionModel().getSelectedItem();   	 
		     //System.out.println("fet srv  :"+srv);
		     type = typeText.getText();
		     //System.out.println("fet type   :"+type);
		     numero = Integer.parseInt(numeroText.getText());
		     //System.out.println("fet numero   :"+numero);

	     if(type.isEmpty()) {
		 	sb.append("=> Type  da la chambre est requis! ! \n"); 
		   	erreur = true;
		 } 
	     if(dispo == null || srv== null)
	  	 {
	   		sb.append("=> Disponibilité et service sont requis 1-Oui 0-Non ! \n"); 
	   		erreur = true;

		 }
	    
	     if(!erreur) {
	    	 Chambre c = new Chambre(0,numero,type,srv,prix,dispo);
	    	 //System.out.println(c.toString());
		   	erreur  = new GestionChambre().UpdateAll(id_de_chambre_a_modifier,c);
		   		if(erreur) {
		   		Alert alert = new Alert(AlertType.INFORMATION,"La modification a été réussi !",javafx.scene.control.ButtonType.FINISH);
				alert.showAndWait();
				this.Close(event);
		   		}else {
		   			Alert alert = new Alert(AlertType.ERROR,"Une erreur s'est produit. verifiez-vous \n si la chambre est déja affecter a un client \n Ou son numero est deja existe  !",javafx.scene.control.ButtonType.FINISH);
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		numeroText.textProperty().addListener((ChangeListener<? super String>) new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				 if (!arg2.matches("\\d")) {
					 numeroText.setText(arg2.replaceAll("[^\\d]", ""));
			        }}
		});		
		ObservableList<Integer> list = FXCollections.observableArrayList(0,1);
		serviceText.setItems(list);;
		dispoText.setItems(list);
	}
	void initData(Chambre c) {
		id_de_chambre_a_modifier = c.getD();
		numeroText.setText(String.valueOf(c.getNumero()));
		typeText.setText(c.getType());
		prixText.setText(String.valueOf(c.getPrix()));
		dispoText.setValue(c.getDisponibilite());
		serviceText.setValue(c.getEnService());
	}

}
