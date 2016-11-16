package Serie22;

public class Article<TypeCode> {
	
	private TypeCode code;
	private String designation;
	private float pu;
	
	public Article(TypeCode code, String designation, float pu) {
		this.code= code; this.designation= designation; this.pu= pu;
	}
	
	public Article(){}
	
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
}
