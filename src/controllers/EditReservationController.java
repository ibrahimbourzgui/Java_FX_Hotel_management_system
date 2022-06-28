package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import bean.Reservation;
import dao.GestionChambre;
import dao.GestionClient;
import dao.GestionReservation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class EditReservationController implements Initializable {

    static int id_reservation = 0;
    static int num_chambre = 0;

    @FXML
    private Button CancelBtn;

    @FXML
    private Button EditBtn;

    @FXML
    private Button clearbtn;

    @FXML
    private DatePicker dateDebut;

    @FXML
    private DatePicker dateFin;

    @FXML
    private TextField prixTotal;
    
    @FXML
    void Clear(ActionEvent event) {
	    dateDebut.setValue(null); 
	    dateFin.setValue(null);
	    prixTotal.setText(null);
    }

    @FXML
    void Close(ActionEvent event) {
    	Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.close();
    }

    @FXML
    void Edit(ActionEvent event) {
	    LocalDate dated = dateDebut.getValue(); 
	    LocalDate datef = dateFin.getValue(); 
	    Double prixT;
	    if( prixTotal.getText() != null &&  prixTotal.getText().length()>0) {
		prixT  = Double.parseDouble(prixTotal.getText());

	    }else prixT = 0.0;
	    
	    if(dated ==null || datef ==null || prixT < 0 ) {
	    	 Alert alert = new Alert(AlertType.INFORMATION,
					 "Il faux remplir touts les champs !",javafx.scene.control.ButtonType.OK);
	   			alert.showAndWait();
	    }
	    else {
	    	if(new GestionReservation().UpdateDatesReservation(id_reservation, dated, datef)) {
	    		Alert alert = new Alert(AlertType.INFORMATION,"La modification a été réussi !",javafx.scene.control.ButtonType.FINISH);
	 			alert.showAndWait();
	 			this.Close(event);
	    	}else {
 	   			Alert alert = new Alert(AlertType.ERROR,"Une erreur s'est produit. ",javafx.scene.control.ButtonType.FINISH);
 	   			alert.showAndWait();
 	   		}
	    }
    }
    
    public void setTotalPrix() {
		long totalJ = 0;
		if(dateDebut.getValue() != null) {
			if(dateFin.getValue() == null) return;
			 totalJ=ChronoUnit.DAYS.between(dateDebut.getValue(),dateFin.getValue());
				 if(totalJ <= 0)
				 {
					 Alert alert = new Alert(AlertType.WARNING,
							 "La date de début doit être antérieure à la date de fin !",javafx.scene.control.ButtonType.OK);
			   			alert.showAndWait();
			   			dateFin.setValue(null);
				 }
				 else {
						prixTotal.setText(String.valueOf((new GestionChambre().GetChambrePrixByNum(num_chambre))*totalJ));
				 }
			}
		}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		prixTotal.setEditable(false);

		dateDebut.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
			setTotalPrix();
				
		});
		dateFin.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
			setTotalPrix();
		});
		
				
	}
	public void initData(Reservation r) {
		dateDebut.setValue(r.getDate_debut());
		dateFin.setValue(r.getDate_fin());
		id_reservation = r.getId();
		num_chambre = r.getNumero_chambre();
		prixTotal.setText(String.valueOf(r.getPrix_total()));
	}
}
