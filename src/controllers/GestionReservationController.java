package controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import bean.Chambre;
import bean.Reservation;
import dao.GestionChambre;
import dao.GestionReservation;
import javafx.fxml.Initializable;

public class GestionReservationController  implements Initializable   {


    @FXML
    private Button ActualiserBtn;
    @FXML
    private Button AjouterBtn;
    @FXML
    private Button ModifierBtn;
    @FXML
    private Button SupprimerBtn;
    @FXML
    private Button confirmBtn;


    @FXML
    private TableColumn<Reservation, Integer> confirmation;

    @FXML
    private TableView<Reservation> reservationTable;

    @FXML
    private TableColumn<Reservation, String> clientCin;

    @FXML
    private TableColumn<Reservation, LocalDate> dateD;

    @FXML
    private TableColumn<Reservation, LocalDate> dateF;

    @FXML
    private TableColumn<Reservation, Integer> id;

    @FXML
    private TableColumn<Reservation, Integer> numChambre;

    @FXML
    private TableColumn<Reservation, Double> prixTotal;
    
    @FXML
    void Actualiser(ActionEvent event) {
    	ObservableList<Reservation> list = FXCollections.observableArrayList(
				new GestionReservation().GetAllReservation()
				);
		clientCin.setCellValueFactory(new PropertyValueFactory<Reservation, String>("cin_client"));
		dateD.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDate>("date_debut"));
		dateF.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDate>("date_fin"));
		id.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("id"));
		numChambre.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("numero_chambre"));
		prixTotal.setCellValueFactory(new PropertyValueFactory<Reservation, Double>("prix_total"));
		confirmation.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("confirme"));
		reservationTable.setItems(list);
		
    }

    @FXML
    void Ajouter(ActionEvent event) {
    	try {
			Parent parent = FXMLLoader.load(getClass().getResource("/interfaces/AddReservation.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

    @FXML
    void Modifier(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/EditReservation.fxml"));    				
			Scene scene = new Scene(loader.load());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			EditReservationController c = loader.getController();
			
			if(reservationTable.getSelectionModel().getSelectedItem() != null) {
					Reservation r = reservationTable.getSelectionModel().getSelectedItem();	    	
					c.initData(r);
					stage.show();
	    	}else {
	    		Alert alert = new Alert(AlertType.INFORMATION,"Veuillez sélectionner une ligne",javafx.scene.control.ButtonType.OK);
	    		alert.showAndWait();
	    	}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void Supprimer(ActionEvent event) {
    	if(reservationTable.getSelectionModel().getSelectedItem() != null) {
    		Alert alert = new Alert(AlertType.CONFIRMATION,"Etes-vous sûr de vouloir supprimer cette réservation ?",javafx.scene.control.ButtonType.YES,javafx.scene.control.ButtonType.NO);
    		final Optional<ButtonType> result = alert.showAndWait();
    		if(result.get() == ButtonType.YES) {
    			new GestionReservation().DeleteReservation(reservationTable.getSelectionModel().getSelectedItem().getId());
    			Actualiser(new ActionEvent());
    		}
    		else 
    			System.out.println("safi");
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION,"Veuillez sélectionner une réservation",javafx.scene.control.ButtonType.OK);
    		alert.showAndWait();
    	}
    }
    
    @FXML
    void Confirmer(ActionEvent event) {
    	if(reservationTable.getSelectionModel().getSelectedItem() != null) {
			Reservation r = reservationTable.getSelectionModel().getSelectedItem();	    	
			new GestionReservation().UpdateConfirmeReservation(r.getId());
			Actualiser(event);
	}else {
		Alert alert = new Alert(AlertType.INFORMATION,"Veuillez sélectionner la reservation à confirmé",javafx.scene.control.ButtonType.OK);
		alert.showAndWait();
	}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Actualiser(new ActionEvent());
		
	}
}
