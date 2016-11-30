package Serie41;

import Serie22.ClientS22;

public class ArticlePromo extends Article41<Integer> {
	
	private int quantiteMini;
	private float reduction;
	
	public ArticlePromo(Integer code, String designation, float pu, int quantiteMini, float reduction) {
		super(code, designation, pu);
		this.quantiteMini= quantiteMini;
		this.reduction= reduction;
	}
	
	public ArticlePromo() {}
	
	public String toString() {
		return super.toString() + " Quantite Mini : " + quantiteMini + " Réduction : " + reduction;
	}
	
	public String facturer(int quantite) {
		return 	"\t" + getCode() + "\t" + getDesignation() + "\t" +
				quantite + "\t" + ClientS22.arrondi(getPu()) + 
				"\t" + ClientS22.arrondi(prixFacture(quantite)) + " Quantite mini: " 
				+ quantiteMini + " réduction: " + reduction;
	}
	
	public float prixFacture(int quantite) {
		if (quantite < quantiteMini) return super.prixFacture(quantite);
		else return (super.prixFacture(quantite) * (1-reduction/100F));
	}
}
