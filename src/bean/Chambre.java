package bean;

import javafx.scene.control.Button;

public class Chambre {

	private int d;
	private int numero;
	private String type;
	private int enService;
	private double prix;
	private int disponibilite;
	public Chambre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Chambre(int d, int numero, String type, int enService, double prix, int disponibilite) {
		super();
		this.d = d;
		this.numero = numero;
		this.type = type;
		this.enService = enService;
		this.prix = prix;
		this.disponibilite = disponibilite;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getEnService() {
		return enService;
	}

	public void setEnService(int enService) {
		this.enService = enService;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(int disponibilite) {
		this.disponibilite = disponibilite;
	}

	@Override
	public String toString() {
		return "Chambre [d=" + d + ", numero=" + numero + ", type=" + type + ", enService=" + enService + ", prix="
				+ prix + ", disponibilite=" + disponibilite + "]";
	}
	
	
	
}
