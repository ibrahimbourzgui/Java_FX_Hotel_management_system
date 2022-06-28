package controllers;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import bean.Chambre;
import dao.GestionChambre;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class AddChambreController implements Initializable {



    @FXML
    private Button AddBtn;

    @FXML
    private Button CancelBtn;

    @FXML
    private ComboBox<Integer> dispoText;

    @FXML
    private TextField numeroText;

    @FXML
    private TextField prixText;

    @FXML
    private ComboBox<Integer> serviceText; 

    @FXML
    private TextArea typeText;
    Integer dispo, srv, numero;
    String type;
    Double prix;
    @FXML
    void Close(ActionEvent event) {
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		stage.close();
    }
    
    @FXML
    void Add(ActionEvent event) {
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
	 	//System.out.println("sb1 :   "+sb.toString());
	   	erreur = true;
	 } 
     if(dispo == null || srv== null)
  	 {
   		sb.append("=> La statue du disponibilité et de service \n sont requis 1-Oui 0-Non ! \n"); 
	 	//System.out.println("sb2 :   "+sb.toString());
   		erreur = true;

	 }
    
     if(!erreur) {
	   	erreur  = new GestionChambre().AddChambre(new Chambre(0,numero,type,srv,prix,dispo));
	   		if(erreur) {
	   		Alert alert = new Alert(AlertType.INFORMATION,"L'ajout a été réussi !",javafx.scene.control.ButtonType.FINISH);
			alert.showAndWait();
			this.Close(event);
	   		}else {
	   			Alert alert = new Alert(AlertType.ERROR,"Une erreur s'est produit. \n essayez-vous de verifier si le numero de chambre est déja existe !",javafx.scene.control.ButtonType.FINISH);
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
    	numeroText.setText(null);
    	prixText.setText(null);
    	serviceText.setValue(0);
    	dispoText.setValue(0);
    }

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ObservableList<Integer> list = FXCollections.observableArrayList(0,1);
		serviceText.setItems(list);
		dispoText.setItems(list);
		
		numeroText.textProperty().addListener((ChangeListener<? super String>) new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				 if (!arg2.matches("\\d")) {
					 numeroText.setText(arg2.replaceAll("[^\\d]", ""));
			        }}
		});
	}
	
	

}


