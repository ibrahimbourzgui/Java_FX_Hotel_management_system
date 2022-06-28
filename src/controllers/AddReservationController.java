package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dao.GestionChambre;
import dao.GestionClient;
import dao.GestionReservation;
import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddReservationController  implements Initializable{
		public static int num_chambre = 0;
	    @FXML
	    private DatePicker dateDebut;
	
	    @FXML
	    private DatePicker dateFin;
	
	    @FXML
	    private ComboBox<String> idClientSelect;

	    @FXML
	    private ComboBox<Integer> cinChambreSelect;
	    
	    @FXML
	    private TextField prix;

	    @FXML
	    private Button AddBtn;

	    @FXML
	    private Button clearbtn;

	    @FXML
	    private Button CancelBtn;

	    @FXML
	    void Add(ActionEvent event) {
	    	Integer numeroChambre = cinChambreSelect.getValue();
		    String cin =idClientSelect.getValue();
		    LocalDate dated = dateDebut.getValue(); 
		    LocalDate datef = dateFin.getValue(); 
		    Double prixT;
		    if( prix.getText() != null &&  prix.getText().length()>0) {
			prixT  = Double.parseDouble(prix.getText());

		    }else prixT = 0.0;
		    
		    if(numeroChambre == null || cin == null || dated ==null || datef ==null || prixT < 0 ) {
		    	 Alert alert = new Alert(AlertType.INFORMATION,
						 "Il faux remplir touts les champs !",javafx.scene.control.ButtonType.OK);
		   			alert.showAndWait();
		    }
		    else {
		    	if(new GestionReservation().AddReservation(cin,numeroChambre, dated, datef, prixT,1)) {
		    		Alert alert = new Alert(AlertType.INFORMATION,"L'Ajoute a été réussi !",javafx.scene.control.ButtonType.FINISH);
		 			alert.showAndWait();
		 			this.Close(event);
		    	}else {
	 	   			Alert alert = new Alert(AlertType.ERROR,"Une erreur s'est produit. ",javafx.scene.control.ButtonType.FINISH);
	 	   			alert.showAndWait();
	 	   		}
		    }
	    }

	    @FXML
	    void Clear(ActionEvent event) { 	
	    	cinChambreSelect.setValue(null);
		    idClientSelect.setValue(null);
		    dateDebut.setValue(null); 
		    dateFin.setValue(null);
		    prix.setText(null);
	    }

	    @FXML
	    void Close(ActionEvent event) {
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.close();
	    }

	public void setTotalPrix() {
		long totalJ = 0;
		if(dateDebut.getValue() != null) {
			if(dateFin.getValue() == null) return;
			 totalJ=ChronoUnit.DAYS.between(dateDebut.getValue(),dateFin.getValue());
				 if(totalJ <= 0 )
				 {
					 Alert alert = new Alert(AlertType.WARNING,
							 "La date de début doit être antérieure à la date de fin !",javafx.scene.control.ButtonType.OK);
			   			alert.showAndWait();
			   			dateFin.setValue(null);
				 }
				 else {
					 if(num_chambre !=0) {
							prix.setText(String.valueOf((new GestionChambre().GetChambrePrixByNum(num_chambre))*totalJ));
					 }
				 }
			}
		}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> listClient = FXCollections.observableArrayList(new GestionClient().GetAllCin());
		idClientSelect.setItems(listClient);
		prix.setEditable(false);

		dateDebut.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
			String Date = newValue.replace('/', '-');
			StringBuffer sb = new StringBuffer(Date);
			String day = sb.substring(0, 2);
			String month = sb.substring(3, 5);
			String year = sb.substring(6, 10);
			sb.setLength(0);
			sb.append(year+'-'+month+'-'+day);
			ObservableList<Integer> listChambre = FXCollections.observableArrayList(new GestionChambre().GetAllUnusedChambresNum(LocalDate.parse(sb)));
			cinChambreSelect.setItems(listChambre);
			setTotalPrix();
				
		});
		dateFin.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
			setTotalPrix();
		});
		
		
		cinChambreSelect.valueProperty().addListener(new ChangeListener<Integer>() {
			@Override
			public void changed(ObservableValue<? extends Integer> observale, Integer oldVal, Integer newVal) {
				
				if(newVal != null) {num_chambre = newVal;
				setTotalPrix();
			}
			}
		});
	

		
	}

}
