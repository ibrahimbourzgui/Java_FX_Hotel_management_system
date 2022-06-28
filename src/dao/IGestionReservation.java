package dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;


import bean.Reservation;

public interface IGestionReservation {
	
	// Get Functions
	
	public ArrayList<Reservation> GetAllReservation();
	public Reservation GetReservationById(int id);
	public ArrayList<Reservation> GetReservationByCinClient(String cin);
	public ArrayList<Reservation> GetReservationByNumeroChambre(int numero_chambre);
	public int GetReservationByYearMonth(int month, int year);
	//public ArrayList<S> GetReservationByMounth();
	
	//Update Functions
	public boolean UpdateDatesReservation(int id,LocalDate date_debut,LocalDate date_fin);
	public boolean UpdateConfirmeReservation(int id);
	
	// Add Functions
	public Boolean AddReservation(String cin, int numero_chambre, LocalDate date_debut,LocalDate date_fin,double prix_total,int confirme);
	
	// Delete Function
	public Boolean DeleteReservation(int id);
	
	// used function 
	public Boolean ActualiserPrixTotal(int id);

	

}
