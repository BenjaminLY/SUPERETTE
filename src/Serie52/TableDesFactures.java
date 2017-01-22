package Serie52;

import java.io.Serializable;
import java.util.TreeMap;

public class TableDesFactures implements MesInterfaces.InterfaceStructure<Facture,String>, Serializable {
	
	private TreeMap<String, Facture> tabFact;
	
	public TableDesFactures() {
		tabFact= new TreeMap<String, Facture>();
		TableDesCommandes52 tabCde= new TableDesCommandes52();
		TableDesArticles52 tabArt= new TableDesArticles52();
		Commande52 cde= tabCde.retourner("20171101");
		Facture fact= new Facture(cde.getDateFacturation(),cde.getNumCde(),cde.facturer(tabArt));
		tabFact.put(fact.getNumero(),fact);
	}
	
	public TreeMap<String, Facture> getTabFactures() {
		return tabFact;
	}
	
	public int taille() { 
		return tabFact.size(); 
	}
	
	public Facture retourner(String numero) {
		return tabFact.get(numero);
	}
	
	public void ajouter(Facture fact) {
		if (retourner(fact.getNumero()) == null) tabFact.put(fact.getNumero(), fact);
	}
	
	public void supprimer(String numero) {
		if (retourner(numero) != null)
			tabFact.remove(numero);
	}
	
	public String toString() {
		String st= 	"\n\t************  TABLE DES FACTURES  ************\n\n";
		for(Facture fact: tabFact.values()) 
			st+= fact.toString() + "\n";
		return st;
	}
	
	public String cle() {
		String st= 	"\n******* LISTE DES NUMEROS DE FACTURES *******\n";
		for(String code: tabFact.keySet())
			st+= code + " ** ";
		return st;
	}
	
}
