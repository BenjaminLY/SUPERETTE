package Serie41;
import java.util.TreeMap;

public class TableDesCommandes41 {
	
	private TreeMap<Integer, Commande41> tabCde;
	
	public TableDesCommandes41() {
		tabCde= new TreeMap<Integer, Commande41>();
	}
	
	public void ajouter(Commande41 cde) {
		if (retourner(cde.getNumCde()) == null) tabCde.put(cde.getNumCde(), cde);
	}
	
	public void supprimer(int numCde) {
		if (retourner(numCde) != null) tabCde.remove(numCde);
	}
	
	public Commande41 retourner(int numCde) {
		return tabCde.get(numCde);
	}
	
	public void facturer(TableDesArticles41 tabArt) {
		// BLY
		for (Commande41 cde: tabCde.values())
			cde.facturer(tabArt);
	}
	
	public String toString(TableDesArticles41 tabArt) {
		String st= 	"\n******** LISTES DES COMMANDES PASSEES ********\n";
		for (Commande41 cde: tabCde.values())
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
		for(Commande41 cde: tabCde.values()) {
			cde.purge(code);
			if (cde.taille() != 0) ajouter(cde);
			else supprimer(cde.getNumCde());
		}
	}
}
