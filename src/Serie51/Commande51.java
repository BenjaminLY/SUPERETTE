package Serie51;

import java.util.Vector;
import java.io.Serializable;
import Serie22.ClientS22;
import Utils.DateUser;

public class Commande51 implements MesInterfaces.InterfaceStructure<LigneDeCommande51,Integer>, Serializable {
	
	private Vector<LigneDeCommande51> cde;
	private DateUser dateCommande;
	private DateUser dateFacturation;
	private String numeroCommande;
	private Boolean etatFacture= false;
	
	public Commande51() {
		cde= new Vector<LigneDeCommande51>();
		dateCommande= new DateUser();
	}
	
	public Commande51(Vector<LigneDeCommande51> cde, DateUser dateCde, String numCde) {
		this.cde= cde;
		this.dateCommande= dateCde;
		this.numeroCommande= numCde;
	}
	
	public String getNumCde() {
		return this.numeroCommande;
	}
	
	public void setNumCde(String num) {
		numeroCommande= num;
	}
	
	public void setDateCommande(DateUser date) {
		dateCommande= date;
	}
	
	public String toString() {
		String st= 	"\n*** VOTRE COMMANDE  Numero " + numeroCommande +
					" du " + dateCommande + " ***\n";
		for(LigneDeCommande51 ligne: cde)
			st+= ligne.toString() + "\n";
		return st;
	}
	
	public String toString(TableDesArticles51 tabArt) {
		String st= 	"\n*** VOTRE COMMANDE  Numero " + numeroCommande +
					" du " + dateCommande + " ***\n";
		if (etatFacture) st+= "Date de Facturation : " + dateFacturation;
		else st+= "Commande non facturée";
		st+= "\n";
		
		for(int i=0; i<taille(); i++)
			st+= (i+1) + "- " + cde.get(i).toStringWithDetails(tabArt) + "\n";
		return st;
	}
	
	public int taille() { 
		return cde.size(); 
	}
	
	public void ajouter(LigneDeCommande51 ldc) {
		cde.addElement(ldc);
	}
	
	public void supprimer(Integer code) {
		cde.remove((int)code);
	}
	
	public LigneDeCommande51 retourner(Integer code) {
		for(int i=0; i<cde.size(); i++) {
			LigneDeCommande51 ldc = cde.get(i);
			if (ldc.getCode()== code) return ldc;
		}
		return null;
	}
	
	public LigneDeCommande51 retournerAvecIndice(int indice) {
		return cde.get(indice);
	}
	
	public String facturer(TableDesArticles51 tabArt) {
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
