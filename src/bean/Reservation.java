package bean;

import java.sql.Date;
import java.time.LocalDate;

public class Reservation {

	private int id;
	private String cin_client;
	private int numero_chambre;
	private LocalDate date_debut;
	private LocalDate date_fin;
	private double prix_total;
	private int confirme;

	public Reservation() {
		super();
	}

	public Reservation(int id, String cin_client, int numero_chambre, LocalDate date_debut, LocalDate date_fin,
			double prix_total,int confirme) {
		super();
		this.id = id;
		this.cin_client = cin_client;
		this.numero_chambre = numero_chambre;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.prix_total = prix_total;
		this.confirme = confirme;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCin_client() {
		return cin_client;
	}

	public void setCin_client(String cin_client) {
		this.cin_client = cin_client;
	}

	public int getNumero_chambre() {
		return numero_chambre;
	}

	public void setNumero_chambre(int numero_chambre) {
		this.numero_chambre = numero_chambre;
	}

	public LocalDate getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(LocalDate date_debut) {
		this.date_debut = date_debut;
	}

	public LocalDate getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(LocalDate date_fin) {
		this.date_fin = date_fin;
	}

	public double getPrix_total() {
		return prix_total;
	}

	public void setPrix_total(double prix_total) {
		this.prix_total = prix_total;
	}

	public int getConfirme() {
		return confirme;
	}

	public void setConfirme(int confirme) {
		this.confirme = confirme;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", cin_client=" + cin_client + ", numero_chambre=" + numero_chambre
				+ ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", prix_total=" + prix_total + ", prix_total"+confirme+"]";
	}
	
	
}
