package tn.poly.bahri.models;

public class Vente {
    int idVente;
    String nomClient;
    String details;
    float prix;
    String dateVente;
    
    
	public Vente(int idVente, String nomClient, String details, float prix, String dateVente) {
		super();
		this.idVente = idVente;
		this.nomClient = nomClient;
		this.details = details;
		this.prix = prix;
		this.dateVente = dateVente;
	}


	public int getIdVente() {
		return idVente;
	}


	public void setIdVente(int idVente) {
		this.idVente = idVente;
	}


	public String getNomClient() {
		return nomClient;
	}


	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public float getPrix() {
		return prix;
	}


	public void setPrix(float prix) {
		this.prix = prix;
	}


	public String getDateVente() {
		return dateVente;
	}


	public void setDateVente(String dateVente) {
		this.dateVente = dateVente;
	}
        
    
    
}
