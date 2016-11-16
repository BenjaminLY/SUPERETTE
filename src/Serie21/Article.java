package Serie21;

public class Article {
	
	private int code;
	private String designation;
	private float pu;
	
	public Article(int code,String designation,float pu) {
		this.code= code; this.designation= designation; this.pu= pu;
	}
	
	public Article(){}
	
	public int getCode() { 
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
