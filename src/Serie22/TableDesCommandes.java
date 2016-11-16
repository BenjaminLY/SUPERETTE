package Serie22;
import java.util.TreeMap;

public class TableDesCommandes {
	
	private TreeMap<Integer, Commande> tabCde;
	
	public TableDesCommandes() {
		tabCde= new TreeMap<Integer, Commande>();
	}
	
	public void ajouter(Commande cde) {
		if (retourner(cde.getNumCde()) == null) tabCde.put(cde.getNumCde(), cde);
	}
	
	public void supprimer(int numCde) {
		if (retourner(numCde) != null) tabCde.remove(numCde);
	}
	
	public Commande retourner(int numCde) {
		return tabCde.get(numCde);
	}
	
	public void facturer(TableDesArticles tabArt) {
		// BLY
		for (Commande cde: tabCde.values())
			cde.facturer(tabArt);
	}
	
	public String toString(TableDesArticles tabArt) {
		String st= 	"\n******** LISTES DES COMMANDES PASSEES ********\n";
		for (Commande cde: tabCde.values())
			st+= cde.toString(tabArt) + "*** \n";
		return st;
	}
	
	public String cle() {
		String st= 	"\n******* LISTE DES NUMEROS DE COMMANDES *******\n";
		for(Integer code: tabCde.keySet())
			st+= code + " ** ";
		return st;
	}
	
	public int taille() {
		return tabCde.size();
	}
	
	public void purge(int code) {
		for(Commande cde: tabCde.values()) {
			cde.purge(code);
			if (cde.taille() != 0) ajouter(cde);
			else supprimer(cde.getNumCde());
		}
	}
}
