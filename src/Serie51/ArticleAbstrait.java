package Serie51;

public abstract class ArticleAbstrait<TypeCode> implements java.io.Serializable {
	
	private TypeCode code;
	private String designation;
	private float pu;
	
	public ArticleAbstrait(TypeCode code, String designation, float pu) {
		this.code= code; this.designation= designation; this.pu= pu;
	}
	
	public ArticleAbstrait(){}
	
	public TypeCode getCode() { 
		return code; 
	}

	public String getDesignation() {
		return designation;
	}

	public float getPu() {
		return pu;
	}
	
	public abstract String toString();
	
	public abstract String facturer(int quantite);
	
	public abstract float prixFacture(int quantite);
	
}
