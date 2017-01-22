package Serie52;

import Serie22.ClientS22;

public class Article52 extends ArticleAbstrait<Integer> {
	
	public Article52(Integer code, String designation, float pu) {
		super(code,designation,pu);
	}
	
	public Article52(){}
	
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
