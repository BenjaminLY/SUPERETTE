package Serie22;
import java.util.TreeMap;

public class TableDesArticles {
	
	private TreeMap<Integer, Article<Integer>> tabArt;
	
	public TableDesArticles() {
		Article<Integer> a1= new Article<Integer>(2,"CARTE SON NVIDIA",23.8F);
		Article<Integer> a2= new Article<Integer>(4,"CARTE RESEAU",24.7F);
		Article<Integer> a3= new Article<Integer>(5,"DISK DUR",50.5F);
		Article<Integer> a4= new Article<Integer>(12,"BOITE 100CD",75.5F);
		Article<Integer> a5= new Article<Integer>(18,"MEMOIRE FLASH",17F);
		
		tabArt= new TreeMap<Integer, Article<Integer>>();
		tabArt.put(a5.getCode(), a5);
		tabArt.put(a1.getCode(), a1);
		tabArt.put(a2.getCode(), a2);
		tabArt.put(a3.getCode(), a3);
		tabArt.put(a4.getCode(), a4);
	}
	
	public TreeMap<Integer, Article<Integer>> getTabArticles() {
		return tabArt;
	}

	public void setTabArticles(TreeMap<Integer, Article<Integer>> tabArticles) {
		this.tabArt = tabArticles;
	}

	public Article<Integer> retourner(int code) {
		return tabArt.get(code);
	}
	
	public void ajouter(Article<Integer> art) {
		if (retourner(art.getCode()) == null) tabArt.put(art.getCode(), art);
	}
	
	public void supprimer(Article<Integer> art, TableDesCommandes tabCde) {
		if (tabArt.containsValue(art)) {
			tabArt.remove(art);
			tabCde.purge(art.getCode());
		}
	}

	public void supprimer(int code, TableDesCommandes tabCde) {
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
					"\tCode\tDésignation\t\tPrix\n";
		for(Article<Integer> art: tabArt.values()) 
			st+= art.toString();
		return st;
	}
	
	public String cle() {
		String st= "\n ** LISTE DES CLES DE LA TABLE DES ARTICLES **\n";
		for(Integer code: tabArt.keySet())
			st+= code + " ** ";
		return st;
	}
}
