package Serie52;

import Serie22.ClientS22;
import java.io.Serializable;

public class LigneDeCommande52 implements Serializable {
	
	private int code;
	private int quantite;
	
	public LigneDeCommande52(int code, int quantite) {
		this.code= code;
		this.quantite= quantite;
	}

	public LigneDeCommande52(){}
	
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
	
	public String toStringWithDetails(TableDesArticles52 tabArt) {
		ArticleAbstrait<Integer> art= tabArt.retourner(code);
		String str= "| Code: " + code + " | Désignation: " + art.getDesignation();
		if (art.getDesignation().length() < 16) str+= "\t";
		str+= "\t| PU: " + art.getPu() + " | Quantité: " + quantite + " |";
		return str;
	}
	
	public String facturer(TableDesArticles52 tabArt) {
		ArticleAbstrait<Integer> art= tabArt.retourner(code);
		if (art != null) {
			String designation= art.getDesignation();
			if (designation.length() < 16) designation+= "\t";
			return 	art.facturer(quantite);
		} else return "";
	}
	
	public float prixTotal(TableDesArticles52 tabArt) {
		ArticleAbstrait<Integer> art= tabArt.retourner(code);
		return ClientS22.arrondi(art.prixFacture(quantite));
	}
}
