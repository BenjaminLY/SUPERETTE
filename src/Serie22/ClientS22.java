package Serie22;
import java.util.Scanner;
import Utils.ClientUtils;
import Utils.DateUser;

public class ClientS22 {
	static Scanner sc= new Scanner(System.in);
	
	public static void main(String[] args) {
		TableDesArticles tabArt= new TableDesArticles();
//		Commande cde= new Commande();
		TableDesCommandes tabCde= new TableDesCommandes();
		
		int choix;
		do {
			choix= menuChoixGeneral();
			switch (choix) {
			case 1: gestionTabArticles(tabArt, tabCde); break;
			case 2: gestionDesCommandes(tabArt, tabCde); break;
			}
		} while (choix != 0);
		
		System.out.println("Au revoir et à bientôt sur Superette");		
	}
	
	public static void gestionDesCommandes(TableDesArticles tabArt, TableDesCommandes tabCde) {
		int choix;
		Commande cde;
		do {
			cde= new Commande();
			choix= menuChoixGestionDesCommandes();
			switch (choix) {
			case 1: gestionUneCommande(tabArt, cde);
					if (cde.taille() != 0) {
						int numero= premierNumeroDispo(tabCde);
						cde.setDateCommande(new DateUser());
						cde.setNumCde(numero);
						tabCde.ajouter(cde); 
					}
					break;
			case 2: supprimerUneCommande(tabCde); break; // BLY
			case 3: afficherUneCommande(tabCde); break; // BLY
			case 4: afficherToutesLesCommandes(tabCde, tabArt); break; //BLY
			case 5: facturer1Commande(tabArt, tabCde); break;
			case 0: break;
			}
		} while (choix != 0);
	}
	
	public static void facturer1Commande(TableDesArticles tabArt, TableDesCommandes tabCde) {
		System.out.print(tabCde.cle());
		int numero= ClientUtils.lireEnt("Quelle commande ?", 1, 999);
		
		if (tabCde.retourner(numero) != null) 
			System.out.println("\n" + tabCde.retourner(numero).facturer(tabArt));
	}
	
	public static void supprimerUneCommande(TableDesCommandes tabCde) {
		System.out.println(tabCde.cle());
		int numero= ClientUtils.lireEnt("Quelle commande ?", 1, 999);
		
		if (tabCde.retourner(numero) != null) tabCde.supprimer(numero);
	}
	
	public static void afficherUneCommande(TableDesCommandes tabCde) {
		System.out.println(tabCde.cle());
		int numero= ClientUtils.lireEnt("Quelle commande ?", 1, 999);
		if (tabCde.retourner(numero) != null) 
			System.out.print(tabCde.retourner(numero).toString());
	}
	
	public static void afficherToutesLesCommandes(TableDesCommandes tabCde, TableDesArticles tabArt) {
		System.out.print(tabCde.toString(tabArt));
	}
	
	public static int premierNumeroDispo(TableDesCommandes tabCde) {
		int numero= 1;
		while (tabCde.retourner(numero) != null) numero++;
		return numero;
	}
	
	public static void gestionUneCommande(TableDesArticles tabArt, Commande cde) {
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
	
	public static void gestionTabArticles(TableDesArticles tabArt, TableDesCommandes tabCde) {
		int choix;
		do {
			choix= menuChoixGestionTabArticles();
			switch (choix) {
			case 1: afficher(tabArt); break;
			case 2: ajouter(tabArt); break;
			case 3: supprimer(tabArt, tabCde); break;
			}
		} while (choix != 0);
	}
	
	public static void ajouter(TableDesArticles tabArt, Commande cde) {
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
	
	public static void supprimer(TableDesArticles tabArt, Commande cde) {
		afficher(tabArt, cde);
		if (cde.taille() != 0) {
			int number= ClientUtils.lireEnt("Quelle ligne? ", 1, cde.taille());
			cde.supprimer(number - 1);			
		}
	}
	
	public static void afficher(TableDesArticles tabArt, Commande cde) {
		if (cde.taille() == 0) System.out.println("*** COMMANDE VIDE !!! ***");
		else System.out.print(cde.toString(tabArt));
	}
	
	public static void facturer(TableDesArticles tabArt, Commande cde) {
		if (cde.taille() == 0) System.out.println("*** COMMANDE VIDE !!! ***");
		else System.out.print(cde.facturer(tabArt));
	}
	
	public static void afficher(TableDesArticles tabArt) {
		if (tabArt.taille() == 0) System.out.println("*** Stock vide ! ***");
		else System.out.print(tabArt.toString());
	}
	
	public static void ajouter(TableDesArticles tabArt) {
		Article<Integer> article= saisieArt(tabArt);
		if (article != null) tabArt.ajouter(article);
		else System.out.print("*** Article de code déjà existant ! ***");
	}
	
	public static void supprimer(TableDesArticles tabArt, TableDesCommandes tabCde) {
		if (tabArt.taille() == 0) System.out.print("** Stock vide ! Pas de suppression possible. **");
		else {
			int code= ClientUtils.lireEnt("Quel code voulez-vous supprimer? ", 1, Integer.MAX_VALUE);
			tabArt.supprimer(code, tabCde);
//			if (tabArt.retourner(code) != null) 
//				tabArt.supprimer(code);
//			else
//				System.out.println("*** Ce code n'existe pas... ***");			
		}
	}
	
	public static int menuChoixGestionDesCommandes() {
		String msg= "\n********  MENU GESTION DES COMMANDES  ********\n" +
					"______________________________________________\n" +
					"CREER UNE COMMANDE ..........................1\n" +
					"SUPPRIMER UNE COMMANDE ......................2\n" +
					"AFFICHER UNE COMMANDE .......................3\n" +
					"AFFICHER TOUTES LES COMMANDES ...............4\n" +
					"FACTURER UNE COMMANDE .......................5\n" +
					"FIN .........................................0\n" +
					"VOTRE CHOIX ....";
		return ClientUtils.lireEnt(msg, 0, 5);
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
	
	public static Article<Integer> saisieArt(TableDesArticles tabArt) {
		int code= ClientUtils.lireEnt("Quel code? ", 1, Integer.MAX_VALUE);
		
		if (tabArt.retourner(code) == null) {
			System.out.print("Désignation du produit (minimum 8 caractères)? ");
			String designation= sc.nextLine();
			System.out.print("Prix unitaire? ");
			float pu= sc.nextFloat();
			return new Article<Integer>(code,designation,pu);
		} else return null;
	}
	
	public static float arrondi(float number) {
		return ((int)(number * 100))/100.0F;
	}
} 
