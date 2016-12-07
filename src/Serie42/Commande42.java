package Serie42;
import java.util.Vector;

import Serie22.ClientS22;
import Utils.DateUser;

public class Commande42 implements MesInterfaces.InterfaceStructure<LigneDeCommande42,Integer> {
	
	private Vector<LigneDeCommande42> cde;
	DateUser dateCommande;
	int numeroCommande;
	
	public Commande42() {
		cde= new Vector<LigneDeCommande42>();
		dateCommande= new DateUser();
	}
	
	public Commande42(Vector<LigneDeCommande42> cde, DateUser dateCde, int numCde) {
		this.cde= cde;
		this.dateCommande= dateCde;
		this.numeroCommande= numCde;
	}
	
	public int getNumCde() {
		return this.numeroCommande;
	}
	
	public void setNumCde(int num) {
		numeroCommande= num;
	}
	
	public void setDateCommande(DateUser date) {
		dateCommande= date;
	}
	
	public String toString() {
		String st= 	"\n*** VOTRE COMMANDE  Numero " + numeroCommande +
					" du " + dateCommande + " ***\n";
		for(LigneDeCommande42 ligne: cde)
			st+= ligne.toString() + "\n";
		return st;
	}
	
	public String toString(TableDesArticles42 tabArt) {
		String st= 	"\n*** VOTRE COMMANDE  Numero " + numeroCommande +
					" du " + dateCommande + " ***\n";
		for(int i=0; i<taille(); i++)
			st+= (i+1) + "- " + cde.get(i).toStringWithDetails(tabArt) + "\n";
		return st;
	}
	
	public int taille() { 
		return cde.size(); 
	}
	
	public void ajouter(LigneDeCommande42 ldc) {
		cde.addElement(ldc);
	}
	
	public void supprimer(Integer code) {
		cde.remove(code);
	}
	
	public LigneDeCommande42 retourner(Integer code) {
		for(int i=0; i<cde.size(); i++) {
			LigneDeCommande42 ldc = cde.get(i);
			if (ldc.getCode()== code) return ldc;
		}
		return null;
	}
	
	public LigneDeCommande42 retournerAvecIndice(int indice) {
		return cde.get(indice);
	}
	
	public String facturer(TableDesArticles42 tabArt) {
		String entete="", detail="", pied="";
		float totalHT= 0;
		entete= "\n\t**************" + " FACTURE Commande n°" + numeroCommande + 
				" du " + dateCommande + " *************\n" +
				"\t_______________________________________________________________\n" +
				"\tCODE \tDESIGNATION \t\tQTE \tP.U \tP.T(HT)\tCommentaires\n";
		
		for(int i=0; i<taille(); i++) {
			totalHT+= cde.get(i).prixTotal(tabArt);
			detail+= cde.get(i).facturer(tabArt) + "\n";
		}
		
		pied= 	"\t_______________________________________________________________\n" +
				"\n\t\tTOTAL HORS TAXE :\t\t\t" + ClientS22.arrondi(totalHT) + "\n" +
				"\t\tTVA (19.6%) :\t\t\t\t" + ClientS22.arrondi(totalHT*0.196F) + "\n" +
				"\t\tTOTAL TOUT TAXE:\t\t\t" + ClientS22.arrondi(totalHT*1.196F) + "\n";
		return entete + detail + pied;
	}
	
	public void purge(int code) {
		for(int i= 0; i < taille(); i++) {
			if (cde.get(i).getCode() == code) supprimer(i);
		}
	}
}
