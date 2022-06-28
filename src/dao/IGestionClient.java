package dao;

import java.util.ArrayList;

import bean.Chambre;
import bean.Client;

public interface IGestionClient {

	// Get Functions
	public ArrayList<Client> GetAllClients();
	public ArrayList<String> GetAllCin();
	public Client GetClientById(int id);
	public Client GetClientByCin(String cin);
	public ArrayList<Client> GetClientByNom(String nom);
	public ArrayList<Client> GetClientByTelephone(String telephone);
	public int GetNumberClient();

	//Update Functions
	public Boolean UpdateNom(int id , String newnom);
	public Boolean UpdatePrenom(int id , String newprenom);
	public Boolean UpdateTelephone(int id , String newtelephone);
	public Boolean UpdateCin(int id , String newcin);
	public Boolean UpdateAll(int id , String newnom, String newprenom, String newtelephone, String newcin);
	public Boolean UpdateAll(int id , Client newclient);
	
	// Add Functions
	public Boolean AddClient(String newnom, String newprenom, String newtelephone, String newcin);
	public Boolean AddClient(Client newclient);

	// Drop Function
	public Boolean DropClientById(int id);
	public Boolean DropClientByCin(String cin);
	
}
