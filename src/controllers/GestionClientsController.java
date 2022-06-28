package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import bean.Chambre;
import bean.Client;
import dao.GestionChambre;
import dao.GestionClient;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class GestionClientsController implements Initializable  {


    @FXML
    public Button ActualiserBtn;

    @FXML
    private Button AjouterBtn;

    @FXML
    private Button ModifierBtn;

    @FXML
    private Button SupprimerBtn;

    @FXML
    private TableColumn<Client, Integer> id;

    @FXML
    private TableColumn<Client, String> cinC;

    @FXML
    private TableColumn<Client, String> nomC;

    @FXML
    private TableColumn<Client, String> numC;

    @FXML
    private TableColumn<Client, String> prenomC;

    @FXML
    private TableView<Client> tableClient;

	
    @FXML
    void Actualiser(ActionEvent event) {
    	ObservableList<Client> list = FXCollections.observableArrayList(new GestionClient().GetAllClients());
    	id.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));
    	nomC.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
    	prenomC.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
    	numC.setCellValueFactory(new PropertyValueFactory<Client,String>("telephone"));
    	cinC.setCellValueFactory(new PropertyValueFactory<Client,String>("cin"));
    	tableClient.setItems(list);
    }

    @FXML
    void Ajouter(ActionEvent event) {
    	try {
			Parent parent = FXMLLoader.load(getClass().getResource("/interfaces/AddClient.fxml"));
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
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/EditClient.fxml"));
    	Scene scene = new Scene(loader.load());
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
    	if(tableClient.getSelectionModel().getSelectedItem() != null) {
        	EditClientController c = loader.getController();
			Client cl = tableClient.getSelectionModel().getSelectedItem();	    	
			c.initData(cl);
			stage.show();
	}else {
		Alert alert = new Alert(AlertType.INFORMATION,"Veuillez sélectionner une chambre",javafx.scene.control.ButtonType.OK);
		alert.showAndWait();
	}
    	
	} catch (IOException e) {
		e.printStackTrace();
	}
    }

    @FXML
    void Supprimer(ActionEvent event) {
    	boolean perfect = true;
    	if(tableClient.getSelectionModel().getSelectedItem() != null) {
    		Alert alert = new Alert(AlertType.CONFIRMATION,"Etes-vous sûr de vouloir supprimer ce client ?",javafx.scene.control.ButtonType.YES,javafx.scene.control.ButtonType.NO);
    		final Optional<ButtonType> result = alert.showAndWait();
    		if(result.get() == ButtonType.YES) {
    			perfect = new GestionClient().DropClientById(tableClient.getSelectionModel().getSelectedItem().getId());
    			if (perfect) {
    			Actualiser(new ActionEvent());
    			}else {
    				Alert aler = new Alert(AlertType.ERROR,"Une erreur s'produit, \n possible que ce client est déja associé à une chambre",javafx.scene.control.ButtonType.OK);
    	    		aler.showAndWait();
    			}
    		}
    		else 
    			System.out.println("safi");
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION,"Veuillez sélectionner un client",javafx.scene.control.ButtonType.OK);
    		alert.showAndWait();
    	}
    }
    


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Actualiser(new ActionEvent());
	}


}
