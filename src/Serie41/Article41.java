package Serie41;

import Serie22.ClientS22;

public class Article41<TypeCode> {
	
	private TypeCode code;
	private String designation;
	private float pu;
	
	public Article41(TypeCode code, String designation, float pu) {
		this.code= code; this.designation= designation; this.pu= pu;
	}
	
	public Article41(){}
	
	public TypeCode getCode() { 
		return code; 
	}

	public String getDesignation() {
		return designation;
	}

	public float getPu() {
		return pu;
	}
	
	public String toString() {
		String str = "\t" + code + "\t" + designation + "\t";
		if (designation.length() < 16) str+= "\t";
		str += pu + "\n";
		return str;
	}
	
	public String facturer(int quantite) {
		return 	"\t" + code + "\t" + designation + "\t" +
				quantite + "\t" + ClientS22.arrondi(pu) + 
				"\t" + ClientS22.arrondi(prixFacture(quantite));
	}
	
	public float prixFacture(int quantite) {
		return pu*quantite;
	}
}
