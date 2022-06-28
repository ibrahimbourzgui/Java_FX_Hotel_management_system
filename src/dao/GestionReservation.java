package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


import bean.Reservation;
import databaseconnection.GetConnection;

public class GestionReservation implements IGestionReservation{
	
	// Get Functions
	
	public ArrayList<Reservation> GetAllReservation(){
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select * from reservation");
		ArrayList<Reservation> ReservationList = new ArrayList<>();
		try{	
			while(rs.next()) {
					Reservation r= new Reservation();
					r.setId(rs.getInt("id"));
					r.setCin_client(rs.getString("cin_client"));
					r.setNumero_chambre(rs.getInt("numero_chambre"));
					r.setDate_debut(rs.getDate("date_debut").toLocalDate());
					r.setDate_fin(rs.getDate("date_fin").toLocalDate());
					r.setPrix_total(rs.getDouble("prix_total"));
					r.setConfirme(rs.getInt("confirme"));
					ReservationList.add(r);
					
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();	
		return ReservationList;
		
	}
	
	public Reservation GetReservationById(int id) {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("SELECT * FROM reservation r JOIN chambre c ON r.numero_chambre=c.numero JOIN client cl on r.cin_client=cl.cin WHERE r.id='"+id+"'");
		Reservation r=null;
		try {
			while (rs.next()) {
				r=new Reservation();
				r.setId(rs.getInt("id"));
				r.setCin_client(rs.getString("cin_client"));
				r.setNumero_chambre(rs.getInt("numero_chambre"));
				r.setDate_debut(rs.getDate("date_debut").toLocalDate());
				r.setDate_fin(rs.getDate("date_fin").toLocalDate());
				r.setPrix_total(rs.getDouble("prix_total"));
				r.setConfirme(rs.getInt("confirme"));

			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		return r;
		
		
	}
	
	public int GetReservationByYearMonth(int month, int year) {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("SELECT count(*) FROM reservation where YEAR(date_debut) = "+year+" AND MONTH(date_debut) = "+month+"");
		int r=0;
		try {
			while (rs.next()) {
				r= rs.getInt("count(*)");

			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		return r;	
	}
	
	
	public ArrayList<Reservation> GetReservationByCinClient(String cin_client) {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select * FROM reservation JOIN client ON reservation.cin_client=client.cin WHERE cin_client='"+cin_client+"'");
		ArrayList<Reservation> ar=new ArrayList<Reservation>();
		try {			
			while (rs.next()) {
				Reservation r = new Reservation();
				r=new Reservation();
				r.setId(rs.getInt("id"));
				r.setCin_client(rs.getString("cin_client"));
				r.setNumero_chambre(rs.getInt("numero_chambre"));
				r.setDate_debut(rs.getDate("date_debut").toLocalDate());
				r.setDate_fin(rs.getDate("date_fin").toLocalDate());
				r.setPrix_total(rs.getDouble("prix_total"));
				r.setConfirme(rs.getInt("confirme"));
				ar.add(r);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		return ar;
		
		
	}
	
	
	public ArrayList<Reservation> GetReservationByNumeroChambre(int numero_chambre) {
		GetConnection.connect();
		ResultSet rs = null;
		rs = GetConnection.Select("select * FROM reservation JOIN chambre ON reservation.numero_chambre=chambre.numero WHERE numero_chambre LIKE'%"+numero_chambre+"%'");
		ArrayList<Reservation> ar=new ArrayList<Reservation>();
		try {
			
			while (rs.next()) {
				Reservation r = new Reservation();
				r=new Reservation();
				r.setId(rs.getInt("id"));
				r.setCin_client(rs.getString("cin_client"));
				r.setNumero_chambre(rs.getInt("numero_chambre"));
				r.setDate_debut(rs.getDate("date_debut").toLocalDate());
				r.setDate_fin(rs.getDate("date_fin").toLocalDate());
				r.setPrix_total(rs.getDouble("prix_total"));
				r.setConfirme(rs.getInt("confirme"));
				ar.add(r);
			}			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		GetConnection.disconnect();
		return ar;
	}
	
	// Update Function
	public boolean UpdateDatesReservation(int id, LocalDate date_debut,LocalDate date_fin) {
		GetConnection.connect();
		int num=0;
		ResultSet dates = GetConnection.Select("Select date_debut ,date_fin from reservation where id ='"+id+"'");
		LocalDate OldDebut = null;
		LocalDate OldFin = null;
		try {
			if(dates.next()) {
				// nstokew les date 9bel modification bash ila w93at shi erreur nredouhoum kif kanou
				OldDebut = dates.getDate("date_debut").toLocalDate();
				OldFin = dates.getDate("date_fin").toLocalDate();

			}
			num=GetConnection.Maj("UPDATE reservation SET date_debut='"+date_debut+"',date_fin='"+date_fin+"' where id='"+id+"'");		

			if(num > 0 && ActualiserPrixTotal(id)/* nActualisiw le prixTotal 3la hssab les date jdaad */) {

			}
			else {
				// nreddou les date l9dam 7it kayn erreur 
				GetConnection.Maj("UPDATE reservation SET date_debut='"+OldDebut+"',date_fin='"+OldFin+"' where id='"+id+"'");
				System.out.println("UpdateDatesReservation");

				return false;
			};
		} 
		catch (Exception e) {
			return false;
		}	
		GetConnection.disconnect();
		if(num > 0)
		return true;
		else return false;	
	}
	public boolean UpdateConfirmeReservation(int id) {
		GetConnection.connect();
		int num=0;
		try {
			num=GetConnection.Maj("UPDATE reservation SET confirme='1' where id='"+id+"'");		
		} 
		catch (Exception e) {
			return false;
		}	
		GetConnection.disconnect();
		if(num > 0)
		return true;
		else return false;	
	}
	
	// Add Function
	public Boolean AddReservation(String cin_client, int numero_chambre, LocalDate date_debut,LocalDate date_fin,double prix_total,int confirme){
		
		int num=0,n=0;
		try {
			Double prix= new GestionChambre().GetChambrePrixByNum(numero_chambre);
			System.out.println("cin   :  "+cin_client);
			GetConnection.connect();
			ResultSet rsc=GetConnection.Select("select * FROM client where cin = '"+cin_client+"'");
			if(prix>0 && rsc.next()) {
				num=GetConnection.Maj("insert into reservation (cin_client, numero_chambre,date_debut,date_fin, prix_total,confirme) values"
						+ "('"+cin_client+"','"+numero_chambre+"','"+date_debut+"','"+date_fin+"','"+prix+"','"+confirme+"')");
			}
			else return false;
		} 
		catch (Exception e) {
			 return false;
		}
		GetConnection.disconnect();
		if(num == 0 || !new GestionChambre().SetChambreOnServiceFalse(numero_chambre)) return false;
		else return true;
	}
	// Delete Function start
	public Boolean DeleteReservation(int id) {
		GetConnection.connect();
		int num=0, numero= 0;
		ResultSet rs = null;
		try {
			rs = GetConnection.Select("select * from reservation where id='"+id+"'");
			rs.next();
			numero = rs.getInt("numero_chambre");
			num=GetConnection.Maj("DELETE FROM reservation WHERE id='"+id+"'");
		} 
		catch(Exception e) {
			return false;
		}
		GetConnection.disconnect();
		if(num > 0 ) {
			new GestionChambre().SetChambreOnServiceTrue(numero);
			return true;
		}
		else return false;
		}	
	/* Actualiser Prix Total */ 
	public Boolean ActualiserPrixTotal(int id) {
		GetConnection.connect();
		int num=0;
		double prixTotal=0;
		try {
			
			ResultSet rs= GetConnection.Select("select r.date_debut ,r.date_fin , c.prix FROM reservation r, chambre c"
					+ " where r.id = '"+id+"' and r.numero_chambre = c.numero ");
			if(rs.next()){
			prixTotal = ChronoUnit.DAYS.between(rs.getDate("date_debut").toLocalDate(), rs.getDate("date_fin").toLocalDate())*rs.getDouble("prix");
			num = GetConnection.Maj("update reservation set prix_total = '"+prixTotal+"' where id = "+id);
			}
			else return false;
		} 
		catch (Exception e) {
			return false;
		}
		
		GetConnection.disconnect();
		if(num > 0)
		return true;
		else return false;
	};
	
	public static void main(String[] args) throws ParseException {
		 //System.out.println(new GestionReservation().AddReservation("NewCin",55, LocalDate.of(2022, 1, 9), LocalDate.of(2022, 1, 15), 0,1));
		//System.out.println(new GestionReservation().DeleteReservation(11));
		//System.out.println(new GestionReservation().GetAllReservation());
		//System.out.println(ChronoUnit.DAYS.between(LocalDate.now(),LocalDate.of(2021, 12, 28) ));
		//System.out.println(date.toLocalDate());
		//System.out.println(new Date(2021,26,30).toLocalDate());
		//System.out.println(new GestionReservation().UpdateDatesReservation(1,LocalDate.of(2021, 12, 20), LocalDate.of(2021, 12, 25)));
		//System.out.println(new GestionReservation().GetReservationByYearMonth(1, 2022));
	
	}
}


