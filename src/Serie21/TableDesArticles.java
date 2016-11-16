package Serie21;
import java.util.Vector;

public class TableDesArticles {
	
	private Vector<Article> tabArt;
	
	public TableDesArticles() {
		Article a1= new Article(2,"CARTE SON NVIDIA",23.8F);
		Article a2= new Article(4,"CARTE RESEAU",24.7F);
		Article a3= new Article(5,"DISK DUR",50.5F);
		Article a4= new Article(12,"BOITE 100CD",75.5F);
		Article a5= new Article(18,"MEMOIRE FLASH",17F);
		
		tabArt= new Vector<Article>();
		tabArt.addElement(a1);
		tabArt.addElement(a2);
		tabArt.addElement(a3);
		tabArt.addElement(a4);
		tabArt.addElement(a5);
	}
	
	public Vector<Article> getTabArticles() {
		return tabArt;
	}

	public void setTabArticles(Vector<Article> tabArticles) {
		this.tabArt = tabArticles;
	}

	
	public Article retourner(int code) {
		for(int i=0; i<tabArt.size(); i++) {
			Article a = tabArt.get(i);
			if (a.getCode()== code) return a;
		}
		return null;
	}
	
	public void ajouter(Article art) {
		for (int i= 0; i<tabArt.size(); i++) {
			Article a = tabArt.get(i);
			if (a.getCode() > art.getCode()) {
				tabArt.add(i, art); break;
			}
		}	
	}

	public void supprimer(int code) {
		for (int i= 0; i<tabArt.size(); i++) {
			Article a = tabArt.get(i);
			if (a.getCode() == code) {
				tabArt.remove(i); break;
			}
		}
	}
	
	public int taille() { 
		return tabArt.size(); 
	}
	
	public String toString() {
		String st= 	"\n\t************  TABLE DES ARTICLES  ************\n" +
					"\tCode\tDésignation\t\t\tPrix\n";
		for(int i=0; i<tabArt.size(); i++) {
			st+= tabArt.get(i).toString();
		}
		return st;
	}
}
