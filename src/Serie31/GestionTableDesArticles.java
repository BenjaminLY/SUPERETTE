package Serie31;

import IConsole.ES;
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
		if (tabArt.taille() == 0) System.out.println("*** Stock vide ! ***");
		else System.out.println(tabArt.toString());
	}
	
	public void ajouter(TableDesArticles tabArt) {
		Article<Integer> article= new Article<Integer>();
		do {
			try {
				article= saisie(tabArt);
				throw new Abandon();
			} catch (Abandon ab) {
				System.out.println("*** Article de code déjà existant ! ***");				
			}
		} while (article != null);
		tabArt.ajouter(article);
//		Article<Integer> article= saisie(tabArt);
//		if (article != null) tabArt.ajouter(article);
//		else System.out.println("*** Article de code déjà existant ! ***");
	}
	
	public void supprimer(TableDesArticles tabArt, TableDesCommandes tabCde) throws Abandon {
		if (tabArt.taille() == 0) System.out.print("*** Stock vide ! Pas de suppression possible. ***");
		else {
			System.out.println(); // BLY
			int code= ES.saisie("Quel code voulez-vous supprimer? ", 1);
			tabArt.supprimer(code, tabCde);		
		}
	}
	
	public Article<Integer> saisie(TableDesArticles tabArt) throws Abandon {
		System.out.println("\n*** CREATION d'un nouvel ARTICLE ***");
		int code= ES.saisie("Quel code? ", 1);
		
		if (tabArt.retourner(code) == null) {
			String designation= ES.saisie("Désignation du produit (minimum 8 caractères)? ");
			float pu= ES.saisie("Prix unitaire? ", 0);
			return new Article<Integer>(code,designation,pu);
		} else return null;
	}
}
