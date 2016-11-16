package Serie22;

public class LigneDeCommande {
	
	private int code;
	private int quantite;
	
	public LigneDeCommande(int code, int quantite) {
		this.code= code;
		this.quantite= quantite;
	}

	public LigneDeCommande(){}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public int getQuantite() {
		return quantite;
	}
	
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public void addToQuantite(int quantite) {
		this.quantite += quantite;
	}

	public String toString() {
		return "Code de l'article commandé: " + code + " quantite: " + quantite;
	}
	
	public String toStringWithDetails(TableDesArticles tabArt) {
		Article<Integer> art= tabArt.retourner(code);
		String str= "| Code: " + code + " | Désignation: " + art.getDesignation();
		if (art.getDesignation().length() < 16) str+= "\t";
		str+= "\t| PU: " + art.getPu() + " | Quantité: " + quantite + " |";
		return str;
	}
	
	public String facturer(TableDesArticles tabArt) {
		Article<Integer> art= tabArt.retourner(code);
		if (art != null) {
			String designation= art.getDesignation();
			if (designation.length() < 16) designation+= "\t";
			return 	"\t" + code + "\t" + designation + "\t" +
					quantite + "\t" + ClientS22.arrondi(art.getPu()) + 
					"\t" + prixTotal(tabArt);
		} else return "";
	}
	
	public float prixTotal(TableDesArticles tabArt) {
		Article<Integer> art= tabArt.retourner(code);
		return ClientS22.arrondi(art.getPu() * quantite);
	}
}
