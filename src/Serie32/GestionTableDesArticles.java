package Serie32;

import IPane.ES;
import MesExceptions.Abandon;
import Serie22.Article;
import Serie22.TableDesArticles;
import Serie22.TableDesCommandes;

public class GestionTableDesArticles {
	
	public void menuGeneral(TableDesArticles tabArt, TableDesCommandes tabCde) throws Abandon {
		int choix;
		do {
			choix= menuChoix();
			switch (choix) {
			case 1: afficher(tabArt); break;
			case 2: ajouter(tabArt); break;
			case 3: supprimer(tabArt, tabCde); break;
			}
		} while (choix != 0);
	}
	
	public int menuChoix() throws Abandon {
		String msg= "\n***********  GESTION DES ARTICLES  ***********\n" +
					"______________________________________________\n" +
					"AFFICHER TOUS LES ARTICLES ..................1\n" +
					"AJOUTER UN NOUVEL ARTICLE ...................2\n" +
					"SUPPRIMER UN ARTICLE ........................3\n" +
					"FIN .........................................0\n" +
					"VOTRE CHOIX ....";
		return ES.saisie(msg, 0, 3);
	}
	
	public void afficher(TableDesArticles tabArt) {
		if (tabArt.taille() == 0) ES.affiche("*** Stock vide ! ***\n");
		else ES.affiche(tabArt.toString() + "\n");
	}
	
	public void ajouter(TableDesArticles tabArt) throws Abandon {
		ES.affiche("\n*** CREATION d'un nouvel ARTICLE ***\n");
		Article<Integer> article= new Article<Integer>();
		do { // BLY
			article= saisie(tabArt);
			if (article == null)
				ES.affiche("*** Article de code déjà existant ! ***\n");
		} while (article == null);
		tabArt.ajouter(article);
	}
	
	public void supprimer(TableDesArticles tabArt, TableDesCommandes tabCde) throws Abandon {
		if (tabArt.taille() == 0) ES.affiche("*** Stock vide ! Pas de suppression possible. ***");
		else {
			ES.affiche(tabArt.cle() + "\n"); // BLY
			int code= ES.saisie("Quel code voulez-vous supprimer? ", 1);
			tabArt.supprimer(code, tabCde);		
		}
	}
	
	public Article<Integer> saisie(TableDesArticles tabArt) throws Abandon {
		int code= ES.saisie("Quel code? ", 1);
		
		if (tabArt.retourner(code) == null) {
			String designation= ES.saisie("Désignation du produit (minimum 8 caractères)? ");
			float pu= ES.saisie("Prix unitaire? ", (float)0);
			return new Article<Integer>(code,designation,pu);
		} else return null;
	}
}
