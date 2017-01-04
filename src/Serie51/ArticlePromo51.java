package Serie51;

import Serie22.ClientS22;

public class ArticlePromo51 extends ArticleAbstrait<Integer> {
	
	private int quantiteMini;
	private float reduction;
	
	public ArticlePromo51(Integer code, String designation, float pu, int quantiteMini, float reduction) {
		super(code, designation, pu);
		this.quantiteMini= quantiteMini;
		this.reduction= reduction;
	}
	
	public ArticlePromo51() {}
	
	public int getQuantiteMini() {
		return quantiteMini;
	}
	
	public float getReduction() {
		return reduction;
	}
	
	public void setQuantiteMini(int quantiteMini) {
		this.quantiteMini= quantiteMini;
	}
	
	public void setReduction(float reduction) {
		this.reduction= reduction;
	}	
	
	public String toString() {
		return "\t" + getCode() + "\t" + getDesignation() + "\t" + getPu() + "\t" + quantiteMini + "\t" + reduction;
	}
	
	public String facturer(int quantite) {
		return 	"\t" + getCode() + "\t" + getDesignation() + "\t" +
				quantite + "\t" + ClientS22.arrondi(getPu()) + 
				"\t" + ClientS22.arrondi(prixFacture(quantite)) + "\t** PROMO plus de " 
				+ quantiteMini + " articles achetés, " + reduction + "% de réduction";
	}
	
	public float prixFacture(int quantite) {
		if (quantite < quantiteMini) return getPu() * quantite;
		else return getPu() * quantite * (1-reduction/100F);
	}
}
