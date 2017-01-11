package Serie51;

import java.util.TreeMap;
import java.io.Serializable;

public class TableDesCommandes51 implements MesInterfaces.InterfaceStructure<Commande51,String>, Serializable {
	
	private TreeMap<String, Commande51> tabCde;

	public TableDesCommandes51() {
		tabCde= new TreeMap<String, Commande51>();
	}
	
	public TreeMap<String, Commande51> getTabCde() {
		return tabCde;
	}
	
	public void ajouter(Commande51 cde) {
		if (retourner(cde.getNumCde()) == null) tabCde.put(cde.getNumCde(), cde);
	}
	
	public void supprimer(String numCde) {
		if (retourner(numCde) != null) tabCde.remove(numCde);
	}
	
	public Commande51 retourner(String numCde) {
		return tabCde.get(numCde);
	}
	
	public void facturer(TableDesArticles51 tabArt) {
		for (Commande51 cde: tabCde.values())
			cde.facturer(tabArt);
	}
	
	public String toString(TableDesArticles51 tabArt) {
		String st= 	"\n******** LISTES DES COMMANDES PASSEES ********\n";
		for (Commande51 cde: tabCde.values())
			st+= cde.toString(tabArt) + "*** \n";
		return st;
	}
	
	public String cle() {
		String st= 	"\n******* LISTE DES NUMEROS DE COMMANDES *******\n";
		for(String code: tabCde.keySet())
			st+= code + " ** ";
		return st;
	}
	
	public int taille() {
		return tabCde.size();
	}
	
	public void purge(int code) {
		for(Commande51 cde: tabCde.values()) {
			cde.purge(code);
			if (cde.taille() != 0) ajouter(cde);
			else supprimer(cde.getNumCde());
		}
	}
}
