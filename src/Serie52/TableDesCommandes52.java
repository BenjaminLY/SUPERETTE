package Serie52;

import java.util.TreeMap;
import java.util.Vector;

import Utils.DateUser;

import java.io.Serializable;

public class TableDesCommandes52 implements MesInterfaces.InterfaceStructure<Commande52,String>, Serializable {
	
	private TreeMap<String, Commande52> tabCde;

	public TableDesCommandes52() {
		tabCde= new TreeMap<String, Commande52>();
		Vector<LigneDeCommande52> cde= new Vector<LigneDeCommande52>();
		LigneDeCommande52 ldc1= new LigneDeCommande52(2,6);
		LigneDeCommande52 ldc2= new LigneDeCommande52(5,9);
		cde.addElement(ldc1);
		cde.addElement(ldc2);
		Commande52 commande= new Commande52(cde,new DateUser(10,1,2017),"20171101");
		tabCde.put(commande.getNumCde(),commande);
		commande.setDateFacturation(new DateUser(11,1,2017));
		commande.setEtatFacture(true);
	}
	
	public TreeMap<String, Commande52> getTabCde() {
		return tabCde;
	}
	
	public void ajouter(Commande52 cde) {
		if (retourner(cde.getNumCde()) == null) tabCde.put(cde.getNumCde(), cde);
	}
	
	public void supprimer(String numCde) {
		if (retourner(numCde) != null) tabCde.remove(numCde);
	}
	
	public Commande52 retourner(String numCde) {
		return tabCde.get(numCde);
	}
	
	public void facturer(TableDesArticles52 tabArt) {
		for (Commande52 cde: tabCde.values())
			cde.facturer(tabArt);
	}
	
	public String toString(TableDesArticles52 tabArt) {
		String st= 	"\n******** LISTES DES COMMANDES PASSEES ********\n";
		for (Commande52 cde: tabCde.values())
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
		for(Commande52 cde: tabCde.values()) {
			cde.purge(code);
			if (cde.taille() != 0) ajouter(cde);
			else supprimer(cde.getNumCde());
		}
	}
}
