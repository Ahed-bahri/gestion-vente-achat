package tn.poly.bahri.models;

public class Stock {
    int refStock;
    String designation;
    String category;
    String unite;
    String ProviderName;
    int quantite;
    int quantiteVente;
    int quantiteReste;
    float prixUnitaire;
    float prixVente;
    
    
	public Stock(int refStock, String designation, String category, String unite, String providerName, int quantite,
			int quantiteVente, int quantiteReste, float prixUnitaire, float prixVente) {
		super();
		this.refStock = refStock;
		this.designation = designation;
		this.category = category;
		this.unite = unite;
		ProviderName = providerName;
		this.quantite = quantite;
		this.quantiteVente = quantiteVente;
		this.quantiteReste = quantiteReste;
		this.prixUnitaire = prixUnitaire;
		this.prixVente = prixVente;
	}

		
		
	public Stock(int refStock, String designation, int quantite,int quantiteVente, int quantiteReste, float prixVente) {
		super();
		this.refStock = refStock;
		this.designation = designation;
		this.quantite = quantite;
		this.quantiteVente = quantiteVente;
		this.quantiteReste = quantiteReste;
		this.prixVente = prixVente;
	}
	
	
	public int getRefStock() {
		return refStock;
	}


	public void setRefStock(int refStock) {
		this.refStock = refStock;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getUnite() {
		return unite;
	}


	public void setUnite(String unite) {
		this.unite = unite;
	}


	public String getProviderName() {
		return ProviderName;
	}


	public void setProviderName(String providerName) {
		ProviderName = providerName;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}


	public int getQuantiteVente() {
		return quantiteVente;
	}


	public void setQuantiteVente(int quantiteVente) {
		this.quantiteVente = quantiteVente;
	}


	public int getQuantiteReste() {
		return quantiteReste;
	}


	public void setQuantiteReste(int quantiteReste) {
		this.quantiteReste = quantiteReste;
	}


	public float getPrixUnitaire() {
		return prixUnitaire;
	}


	public void setPrixUnitaire(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}


	public float getPrixVente() {
		return prixVente;
	}


	public void setPrixVente(float prixVente) {
		this.prixVente = prixVente;
	}



}