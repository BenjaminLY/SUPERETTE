package Serie21;
import java.util.Scanner;
import Utils.ClientUtils;

public class ClientS21 {
	static Scanner sc= new Scanner(System.in);
	
	public static void main(String[] args) {
		TableDesArticles tabArt= new TableDesArticles();
		TableDesLignesDeCommande cde= new TableDesLignesDeCommande();
		
		int choix;
		do {
			choix= menuChoixGeneral();
			switch (choix) {
			case 1: gestionTabArticles(tabArt); break;
			case 2: gestionUneCommande(tabArt, cde); break;
			}
		} while (choix != 0);
		
		System.out.println("Au revoir et à bientôt sur Superette");		
	}
	
	public static void gestionUneCommande(TableDesArticles tabArt, TableDesLignesDeCommande cde) {
		int choix;
		do {
			choix= menuChoixGestion1Commande();
			switch (choix) {
			case 1: ajouter(tabArt, cde); break;
			case 2: supprimer(tabArt, cde); break;
			case 3: afficher(tabArt, cde); break;
			case 4: facturer(tabArt, cde);
			}
		} while (choix != 0);
	}
	
	public static void gestionTabArticles(TableDesArticles tabArt) {
		int choix;
		do {
			choix= menuChoixGestionTabArticles();
			switch (choix) {
			case 1: afficherTousLesArticles(tabArt); break;
			case 2: ajouter1Article(tabArt); break;
			case 3: supprimer1Article(tabArt); break;
			}
		} while (choix != 0);
	}
	
	public static void ajouter(TableDesArticles tabArt, TableDesLignesDeCommande cde) {
		LigneDeCommande ldc= saisieldc(tabArt);
		if (ldc != null) {
			LigneDeCommande ligne= cde.retourner(ldc.getCode());
			if (ligne == null) {
				cde.ajouter(ldc);
			} else {
				ligne.addToQuantite(ldc.getQuantite());
			}
		}
	}
	
	public static void supprimer(TableDesArticles tabArt, TableDesLignesDeCommande cde) {
		afficher(tabArt, cde);
		if (cde.taille() != 0) {
			int number= ClientUtils.lireEnt("Quelle ligne? ", 1, cde.taille());
			cde.supprimer(number - 1);			
		}
	}
	
	public static void afficher(TableDesArticles tabArt, TableDesLignesDeCommande cde) {
		if (cde.taille() == 0) System.out.println("*** COMMANDE VIDE !!! ***");
		else System.out.print(cde.toString(tabArt));
	}
	
	public static void facturer(TableDesArticles tabArt, TableDesLignesDeCommande cde) {
		if (cde.taille() == 0) System.out.println("*** COMMANDE VIDE !!! ***");
		else System.out.print(cde.facturer(tabArt));
	}
	
	public static void afficherTousLesArticles(TableDesArticles tabArt) {
		System.out.print(tabArt.toString());
	}
	
	public static void ajouter1Article(TableDesArticles tabArt) {
		Article article= saisieArt(tabArt);
		if (article != null) tabArt.ajouter(article);
	}
	
	public static void supprimer1Article(TableDesArticles tabArt) {
		if (tabArt.taille() != 0) {
			System.out.print("Quel code ? ");
			int code= sc.nextInt();
			if (tabArt.retourner(code) != null) 
				tabArt.supprimer(code);
			else
				System.out.println("*** Ce code n'existe pas... ***");			
		}
	}
	
	public static int menuChoixGestion1Commande() {
		String msg= "\n**********  GESTION D'UNE COMMANDE  **********\n" +
					"______________________________________________\n" +
					"AJOUTER UNE LIGNE DE COMMANDE ...............1\n" +
					"SUPPRIMER UNE LIGNE DE COMMANDE .............2\n" +
					"AFFICHER LA COMMANDE ........................3\n" +
					"FACTURER LA COMMANDE ........................4\n" +
					"FIN .........................................0\n" +
					"VOTRE CHOIX ....";
		return ClientUtils.lireEnt(msg, 0, 4);
	}
	
	public static int menuChoixGeneral() {
		String msg= "\n*********  BIENVENUE A LA SUPERETTE  *********\n" +
					"______________________________________________\n" +
					"GESTION DES ARTICLES ........................1\n" +
					"GESTION D'UNE COMMANDE ......................2\n" +
					"FIN .........................................0\n" +
					"VOTRE CHOIX ....";
		return ClientUtils.lireEnt(msg, 0, 2);
	}
	
	public static int menuChoixGestionTabArticles() {
		// menu choix
		String msg= "\n***********  GESTION DES ARTICLES  ***********\n" +
					"______________________________________________\n" +
					"AFFICHER TOUS LES ARTICLES ..................1\n" +
					"AJOUTER UN NOUVEL ARTICLE ...................2\n" +
					"SUPPRIMER UN ARTICLE ........................3\n" +
					"FIN .........................................0\n" +
					"VOTRE CHOIX ....";
		return ClientUtils.lireEnt(msg, 0, 3);
	}
	
	public static LigneDeCommande saisieldc(TableDesArticles tabArt) {
		int code= ClientUtils.lireEnt("Quel code? ", 1, Integer.MAX_VALUE);
		
		if (tabArt.retourner(code) != null) {
			System.out.print("Quelle quantité ? ");
			int quantite= sc.nextInt();
			return new LigneDeCommande(code,quantite);
		} else {
			System.out.print(" *** Ce code n'existe pas ? ");
			return null;
		}
	}
	
	public static Article saisieArt(TableDesArticles tabArt) {
		int code= ClientUtils.lireEnt("Quel code? ", 1, Integer.MAX_VALUE);
		
		if (tabArt.retourner(code) == null) {
			System.out.print("Quel nom pour ce produit (minimum 8 caractères)? ");
			String designation= sc.next();
			System.out.print("Quel prix? ");
			int pu= sc.nextInt();
			return new Article(code,designation,pu);
		} else {
			System.out.print("*** Ce code existe déjà. Veuillez en choisir un autre. ***");
			return null;
		}
	}
	
	public static float arrondi(float number) {
		return ((int)(number * 100))/100.0F;
	}
} 
