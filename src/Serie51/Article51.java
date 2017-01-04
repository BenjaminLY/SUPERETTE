package Serie51;

import Serie22.ClientS22;

public class Article51 extends ArticleAbstrait<Integer> {
	
	public Article51(Integer code, String designation, float pu) {
		super(code,designation,pu);
	}
	
	public Article51(){}
	
	public String toString() {
		String str = "\t" + getCode() + "\t" + getDesignation() + "\t";
		str += getPu();
		return str;
	}
	
	public String facturer(int quantite) {
		return 	"\t" + getCode() + "\t" + getDesignation() + "\t" +
				quantite + "\t" + ClientS22.arrondi(getPu()) + 
				"\t" + ClientS22.arrondi(prixFacture(quantite));
	}
	
	public float prixFacture(int quantite) {
		return getPu()*quantite;
	}
}
