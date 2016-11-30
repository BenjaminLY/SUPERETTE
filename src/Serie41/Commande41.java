package Serie41;
import java.util.Vector;

import Serie22.ClientS22;
import Utils.DateUser;

public class Commande41 {
	
	private Vector<LigneDeCommande41> cde;
	DateUser dateCommande;
	int numeroCommande;
	
	public Commande41() {
		cde= new Vector<LigneDeCommande41>();
		dateCommande= new DateUser(); // Initialisation avec la date système
	}
	
	public Commande41(Vector<LigneDeCommande41> cde, DateUser dateCde, int numCde) {
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
		for(LigneDeCommande41 ligne: cde)
			st+= ligne.toString() + "\n";
		return st;
	}
	
	public String toString(TableDesArticles41 tabArt) {
		String st= 	"\n*** VOTRE COMMANDE  Numero " + numeroCommande +
					" du " + dateCommande + " ***\n";
		for(int i=0; i<taille(); i++)
			st+= (i+1) + "- " + cde.get(i).toStringWithDetails(tabArt) + "\n";
		return st;
	}
	
	public int taille() { 
		return cde.size(); 
	}
	
	public void ajouter(LigneDeCommande41 ldc) {
		cde.addElement(ldc);
	}
	
	public void supprimer(int code) {
		cde.remove(code);
	}
	
	public LigneDeCommande41 retourner(int code) {
		for(int i=0; i<cde.size(); i++) {
			LigneDeCommande41 ldc = cde.get(i);
			if (ldc.getCode()== code) return ldc;
		}
		return null;
	}
	
	public LigneDeCommande41 retournerAvecIndice(int indice) { // Retourne la ldc de l'indice en entrée
		return cde.get(indice);
	}
	
	public String facturer(TableDesArticles41 tabArt) {
		String entete="", detail="", pied="";
		float totalHT= 0;
		entete= "\n\t**************" + " FACTURE Commande n°" + numeroCommande + 
				" du " + dateCommande + " *************\n" +
				"\t_______________________________________________________________\n" +
				"\tCODE \tDESIGNATION \t\tQTE \tP.U \tP.T(HT)\n";
		
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
