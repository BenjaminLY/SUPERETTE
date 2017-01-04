package Serie51;

import java.util.TreeMap;
import java.io.Serializable;

public class TableDesArticles51 implements MesInterfaces.InterfaceStructure<ArticleAbstrait<Integer>,Integer>, Serializable {
	
	private TreeMap<Integer, ArticleAbstrait<Integer>> tabArt;
	
	public TableDesArticles51() {
		ArticleAbstrait<Integer> a1= new Article51(2,"CARTE SON NVIDIA",23.8F);
		ArticleAbstrait<Integer> a2= new Article51(4,"CARTE RESEAU",24.7F);
		ArticleAbstrait<Integer> a3= new ArticlePromo51(5,"CLE USB SUPER 32GB",12F, 5, 30F);
		ArticleAbstrait<Integer> a4= new Article51(12,"BOITE 100CD",75.5F);
		ArticleAbstrait<Integer> a5= new Article51(18,"MEMOIRE FLASH",17F);
		
		tabArt= new TreeMap<Integer, ArticleAbstrait<Integer>>();
		tabArt.put(a5.getCode(), a5);
		tabArt.put(a1.getCode(), a1);
		tabArt.put(a2.getCode(), a2);
		tabArt.put(a3.getCode(), a3);
		tabArt.put(a4.getCode(), a4);
	}
	
	public TreeMap<Integer, ArticleAbstrait<Integer>> getTabArticles() {
		return tabArt;
	}

	public void setTabArticles(TreeMap<Integer, ArticleAbstrait<Integer>> tabArticles) {
		this.tabArt = tabArticles;
	}

	public ArticleAbstrait<Integer> retourner(Integer code) {
		return tabArt.get(code);
	}
	
	public void ajouter(ArticleAbstrait<Integer> art) {
		if (retourner(art.getCode()) == null) tabArt.put(art.getCode(), art);
	}
	
	public void supprimer(ArticleAbstrait<Integer> art, TableDesCommandes51 tabCde) {
		if (tabArt.containsValue(art)) {
			tabArt.remove(art);
			tabCde.purge(art.getCode());
		}
	}

	public void supprimer(Integer code, TableDesCommandes51 tabCde) {
		if (tabArt.containsKey(code)) {
			tabArt.remove(code);
			tabCde.purge(code);
		}
	}
	
	public void supprimer(Integer code) {}
	
	public int taille() { 
		return tabArt.size(); 
	}
	
	public String toString() {
		String st= 	"\n\t************  TABLE DES ARTICLES  ************\n" +
					"\tCode\tDésignation\t\tPrix\tQté min\tRéduction\n";
		for(ArticleAbstrait<Integer> art: tabArt.values()) 
			st+= art.toString() + "\n";
		return st;
	}
	
	public String toStringPromo() {
		String st= 	"\n\t******  TABLE DES ARTICLES EN PROMOTION  ******\n" +
					"\tCode\tDésignation\t\tPrix\tQté min\tRéduction\n";
		
		for(ArticleAbstrait<Integer> art: tabArt.values())  {
			if (art instanceof ArticlePromo51)
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
