package Serie21;
import java.util.Vector;

public class TableDesLignesDeCommande {
	
	static int numeroFacture= 0;
	private Vector<LigneDeCommande> cde;
	
	public TableDesLignesDeCommande() {
		cde= new Vector<LigneDeCommande>();
	}
	
	public String toString(TableDesArticles tabArt) {
		String st= "\n*************  VOTRE COMMANDE  *************\n";
		for(int i=0; i<taille(); i++)
			st+= (i+1) + ". " + cde.get(i).toStringWithDetails(tabArt) + "\n";
		return st;
	}
	
	public int taille() { 
		return cde.size(); 
	}
	
	public void ajouter(LigneDeCommande ldc) {
		cde.addElement(ldc);
	}
	
	public void supprimer(int indice) {
		cde.remove(indice);
	}
	
	public LigneDeCommande retourner(int code) {
		for(int i=0; i<cde.size(); i++) {
			LigneDeCommande ldc = cde.get(i);
			if (ldc.getCode()== code) return ldc;
		}
		return null;
	}
	
	public String facturer(TableDesArticles tabArt) {
		String entete="", detail="", pied="";
		float totalHT= 0;
		numeroFacture++;
		entete= "\n\t*************************" + 
				" FACTURE n°" + numeroFacture + " " +
				"*************************\n" +
				"\t_______________________________________________________________\n" +
				"\tCODE \tDESIGNATION \t\tQTE \tP.U \tP.T(HT)\n";
		
		for(int i=0; i<taille(); i++) {
			totalHT+= cde.get(i).prixTotal(tabArt);
			detail+= cde.get(i).facturer(tabArt) + "\n";
		}
		
		pied= 	"\t_______________________________________________________________\n" +
				"\n\t\tTOTAL HORS TAXE :\t\t\t" + ClientS21.arrondi(totalHT) + "\n" +
				"\t\tTVA (19.6%) :\t\t\t\t" + ClientS21.arrondi(totalHT*0.196F) + "\n" +
				"\t\tTOTAL TOUT TAXE:\t\t\t" + ClientS21.arrondi(totalHT*1.196F) + "\n";
		return entete + detail + pied;
	}
}
