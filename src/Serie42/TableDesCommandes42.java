package Serie42;
import java.util.TreeMap;

public class TableDesCommandes42 implements MesInterfaces.InterfaceStructure<Commande42,Integer> {
	
	private TreeMap<Integer, Commande42> tabCde;
	
	public TableDesCommandes42() {
		tabCde= new TreeMap<Integer, Commande42>();
	}
	
	public void ajouter(Commande42 cde) {
		if (retourner(cde.getNumCde()) == null) tabCde.put(cde.getNumCde(), cde);
	}
	
	public void supprimer(Integer numCde) {
		if (retourner(numCde) != null) tabCde.remove(numCde);
	}
	
	public Commande42 retourner(Integer numCde) {
		return tabCde.get(numCde);
	}
	
	public void facturer(TableDesArticles42 tabArt) {
		// BLY
		for (Commande42 cde: tabCde.values())
			cde.facturer(tabArt);
	}
	
	public String toString(TableDesArticles42 tabArt) {
		String st= 	"\n******** LISTES DES COMMANDES PASSEES ********\n";
		for (Commande42 cde: tabCde.values())
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
		for(Commande42 cde: tabCde.values()) {
			cde.purge(code);
			if (cde.taille() != 0) ajouter(cde);
			else supprimer(cde.getNumCde());
		}
	}
}
