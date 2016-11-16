package Serie23;

import java.util.Scanner;

import Utils.ClientUtils;

import Serie22.Article;
import Serie22.TableDesArticles;
import Serie22.TableDesCommandes;

public class GestionTableDesArticles {
	
	static Scanner sc= new Scanner(System.in);
	
	public void menuGeneral(TableDesArticles tabArt, TableDesCommandes tabCde) {
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
	
	public int menuChoix() {
		String msg= "\n***********  GESTION DES ARTICLES  ***********\n" +
					"______________________________________________\n" +
					"AFFICHER TOUS LES ARTICLES ..................1\n" +
					"AJOUTER UN NOUVEL ARTICLE ...................2\n" +
					"SUPPRIMER UN ARTICLE ........................3\n" +
					"FIN .........................................0\n" +
					"VOTRE CHOIX ....";
		return ClientUtils.lireEnt(msg, 0, 3);
	}
	
	public void afficher(TableDesArticles tabArt) {
		if (tabArt.taille() == 0) System.out.println("*** Stock vide ! ***");
		else System.out.println(tabArt.toString());
	}
	
	public void ajouter(TableDesArticles tabArt) {
		Article<Integer> article= saisie(tabArt);
		if (article != null) tabArt.ajouter(article);
		else System.out.println("*** Article de code déjà existant ! ***");
	}
	
	public void supprimer(TableDesArticles tabArt, TableDesCommandes tabCde) {
		if (tabArt.taille() == 0) System.out.print("*** Stock vide ! Pas de suppression possible. ***");
		else {
			System.out.println(tabArt.cle()); // BLY
			int code= ClientUtils.lireEnt("Quel code voulez-vous supprimer? ", 1, Integer.MAX_VALUE);
			tabArt.supprimer(code, tabCde);		
		}
	}
	
	public Article<Integer> saisie(TableDesArticles tabArt) {
		System.out.println("\n*** CREATION d'un nouvel ARTICLE ***");
		int code= ClientUtils.lireEnt("Quel code? ", 1, Integer.MAX_VALUE);
		
		if (tabArt.retourner(code) == null) {
			System.out.print("Désignation du produit (minimum 8 caractères)? ");
			String designation= sc.nextLine();
			System.out.print("Prix unitaire? ");
			float pu= sc.nextFloat();
			return new Article<Integer>(code,designation,pu);
		} else return null;
	}
}
