package dao;

import java.time.LocalDate;
import java.util.ArrayList;

import bean.Chambre;

public interface IGestionChambre {
	
	// Get Functions
	public ArrayList<Chambre> GetAllChambres();
	public ArrayList<Integer> GetAllUnusedChambresNum(LocalDate dateFin);
	public Chambre GetChambreById(int id);
	public Chambre GetChambreByNum(int num);
	public Double GetChambrePrixByNum(int num);
	public ArrayList<Chambre> GetChambreByType(String type);
	public ArrayList<Chambre> GetChambreByPrix(double prix);
	public int GetNumberChambre();
	
	//Update Functions
	public Boolean UpdateNum(int id , int newnum);
	public Boolean UpdateType(int id , String newtype);
	public Boolean UpdatePrix(int id , double newprix);
	public Boolean UpdateEnService(int id , int newenservice);
	public Boolean UpdateDisponibilite(int id , int newdisponibilite);
	public Boolean UpdateAll(int id , int newnum, String newtype, int newenservice, double newprix, int newdisponibilite);
	public Boolean UpdateAll(int id , Chambre newchambre);
	
	// Add Functions
	public Boolean AddChambre(int newnum, String newtype, int newenservice, double newprix, int newdisponibilite);
	public Boolean AddChambre(Chambre newchambre);

	// Drop Function
	public Boolean DropChambreById(int id);
	public Boolean DropChambreByNum(int Num);

	// Used Function
	public Boolean SetChambreOnServiceFalse(int numero);
	public Boolean SetChambreOnServiceTrue(int numero);

	
}
