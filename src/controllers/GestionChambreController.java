package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import bean.Chambre;
import dao.GestionChambre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GestionChambreController  implements Initializable   {
	

    @FXML
    private Button ActualiserBtn;

    @FXML
    private Button AjouterBtn;

    @FXML
    private Button ModifierBtn;

    @FXML
    private Button SupprimerBtn;
    
    @FXML
    private Button ajouter;
	@FXML
	private TableView<Chambre> tableChambre;
	@FXML
	private TableColumn<Chambre, Integer> id;
	@FXML
	private TableColumn<Chambre, Integer> numero;
	@FXML
	private TableColumn<Chambre, String> type;
	@FXML
	private TableColumn<Chambre, Integer> enservice;
	@FXML
	private TableColumn<Chambre, Double> prix;
	@FXML
	private TableColumn<Chambre, Integer> disponibilite;
	
	@FXML
    void Actualiser(ActionEvent event) {
		ObservableList<Chambre> list = FXCollections.observableArrayList(
				new GestionChambre().GetAllChambres()
				);
		id.setCellValueFactory(new PropertyValueFactory<Chambre , Integer>("d"));
		numero.setCellValueFactory(new PropertyValueFactory<Chambre , Integer>("numero"));
		type.setCellValueFactory(new PropertyValueFactory<Chambre , String>("type"));
		enservice.setCellValueFactory(new PropertyValueFactory<Chambre , Integer>("enService"));
		prix.setCellValueFactory(new PropertyValueFactory<Chambre , Double>("prix"));
		disponibilite.setCellValueFactory(new PropertyValueFactory<Chambre , Integer>("disponibilite"));
		tableChambre.setItems(list);
    }
	

    @FXML
    void Ajouter(ActionEvent event) {
    	try {
			Parent parent = FXMLLoader.load(getClass().getResource("/interfaces/AddChambre.fxml"));
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
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/EditChambre.fxml"));
    				
			Scene scene = new Scene(loader.load());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			EditChambreController c = loader.getController();
			
			if(tableChambre.getSelectionModel().getSelectedItem() != null) {
					Chambre ch = tableChambre.getSelectionModel().getSelectedItem();	    	
					c.initData(ch);
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
    	boolean perfect = true;
    	if(tableChambre.getSelectionModel().getSelectedItem() != null) {
    		Alert alert = new Alert(AlertType.CONFIRMATION,"Etes-vous sûr de vouloir supprimer cette chambre ?",javafx.scene.control.ButtonType.YES,javafx.scene.control.ButtonType.NO);
    		final Optional<ButtonType> result = alert.showAndWait();
    		if(result.get() == ButtonType.YES) {
    			perfect = new GestionChambre().DropChambreById(tableChambre.getSelectionModel().getSelectedItem().getD());
    			if (perfect) {
        			Actualiser(new ActionEvent());
        			}else {
        				Alert aler = new Alert(AlertType.ERROR,"Une erreur s'produit, \n possible que cette chambre est déja associé à un client",javafx.scene.control.ButtonType.OK);
        	    		aler.showAndWait();
        			}
    		}
    		else 
    			System.out.println("safi");
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION,"Veuillez sélectionner une chambre",javafx.scene.control.ButtonType.OK);
    		alert.showAndWait();
    	}
    }


	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Actualiser(new ActionEvent());
	}

}
