package Serie41;
import java.util.TreeMap;

public class TableDesArticles41 {
	
	private TreeMap<Integer, Article41<Integer>> tabArt;
	
	public TableDesArticles41() {
		Article41<Integer> a1= new Article41<Integer>(2,"CARTE SON NVIDIA",23.8F);
		Article41<Integer> a2= new Article41<Integer>(4,"CARTE RESEAU",24.7F);
		ArticlePromo a3= new ArticlePromo(5,"CLE USB SUPER 32GB",12F, 5, 30F);
		Article41<Integer> a4= new Article41<Integer>(12,"BOITE 100CD",75.5F);
		Article41<Integer> a5= new Article41<Integer>(18,"MEMOIRE FLASH",17F);
		
		tabArt= new TreeMap<Integer, Article41<Integer>>();
		tabArt.put(a5.getCode(), a5);
		tabArt.put(a1.getCode(), a1);
		tabArt.put(a2.getCode(), a2);
		tabArt.put(a3.getCode(), a3);
		tabArt.put(a4.getCode(), a4);
	}
	
	public TreeMap<Integer, Article41<Integer>> getTabArticles() {
		return tabArt;
	}

	public void setTabArticles(TreeMap<Integer, Article41<Integer>> tabArticles) {
		this.tabArt = tabArticles;
	}

	public Article41<Integer> retourner(int code) {
		return tabArt.get(code);
	}
	
	public void ajouter(Article41<Integer> art) {
		if (retourner(art.getCode()) == null) tabArt.put(art.getCode(), art);
	}
	
	public void supprimer(Article41<Integer> art, TableDesCommandes41 tabCde) {
		if (tabArt.containsValue(art)) {
			tabArt.remove(art);
			tabCde.purge(art.getCode());
		}
	}

	public void supprimer(int code, TableDesCommandes41 tabCde) {
		if (tabArt.containsKey(code)) {
			tabArt.remove(code);
			tabCde.purge(code);
		}
	}
	
	public int taille() { 
		return tabArt.size(); 
	}
	
	public String toString() {
		String st= 	"\n\t************  TABLE DES ARTICLES  ************\n" +
					"\tCode\tDésignation\t\tPrix\tQté min\tRéduction\n";
		for(Article41<Integer> art: tabArt.values()) 
			st+= art.toString() + "\n";
		return st;
	}
	
	public String toStringPromo() {
		String st= 	"\n\t******  TABLE DES ARTICLES EN PROMOTION  ******\n" +
					"\tCode\tDésignation\t\tPrix\tQté min\tRéduction\n";
		// on pourrait vérifier sur la table est vide à ce niveau
		
		for(Article41<Integer> art: tabArt.values())  {
			if (art instanceof ArticlePromo)
				st+= art.toString() + "\n";		
		}
		return st;		
	}
	
	public String cle() {
		String st= "\n ** LISTE DES CLES DE LA TABLE DES ARTICLES **\n";
		for(Integer code: tabArt.keySet())
			st+= code + " ** ";
		return st;
	}
}
