package Serie23;
import java.util.Scanner;
import Utils.ClientUtils;
import Serie22.TableDesArticles;
import Serie22.Commande;
import Serie22.LigneDeCommande;

public class GestionUneCommande {
	
	static Scanner sc= new Scanner(System.in);
	
	public void menuGeneral(TableDesArticles tabArt, Commande cde) {
		int choix;
		do {
			choix= menuChoix();
			switch (choix) {
			case 1: ajouter(tabArt, cde); break;
			case 2: editer(tabArt, cde); break;
			case 3: supprimer(tabArt, cde); break;
			case 4: afficher(tabArt, cde); break;
			case 5: facturer(tabArt, cde);
			}
		} while (choix != 0);
	}
	
	public int menuChoix() {
		String msg= "\n**********  GESTION D'UNE COMMANDE  **********\n" +
				"______________________________________________\n" +
				"AJOUTER UNE LIGNE DE COMMANDE ...............1\n" +
				"EDITER LA QUANTITE D'UNE LIGNE DE COMMANDE ..2\n" +
				"SUPPRIMER UNE LIGNE DE COMMANDE .............3\n" +
				"AFFICHER LA COMMANDE ........................4\n" +
				"FACTURER LA COMMANDE ........................5\n" +
				"FIN .........................................0\n" +
				"VOTRE CHOIX ....";
		return ClientUtils.lireEnt(msg, 0, 5);
	}
	
	public void ajouter(TableDesArticles tabArt, Commande cde) {
		LigneDeCommande ldc= saisie(tabArt);
		if (ldc != null) {
			LigneDeCommande ligne= cde.retourner(ldc.getCode());
			if (ligne == null) {
				cde.ajouter(ldc);
			} else {
				ligne.addToQuantite(ldc.getQuantite());
			}
		}
	}

	public void editer(TableDesArticles tabArt, Commande cde) {
		afficher(tabArt, cde);
		if (cde.taille() != 0) {
			int number= ClientUtils.lireEnt("Quelle ligne? ", 1, cde.taille());
			LigneDeCommande ligne= cde.retournerAvecIndice(number - 1);
			System.out.print("Quelle quantité ? ");
			int quantite= sc.nextInt();
			ligne.setQuantite(quantite);
		}
	}
	
	public void supprimer(TableDesArticles tabArt, Commande cde) {
		afficher(tabArt, cde);
		if (cde.taille() != 0) {
			int number= ClientUtils.lireEnt("Quelle ligne? ", 1, cde.taille());
			cde.supprimer(number - 1);			
		}
	}
	
	public void afficher(TableDesArticles tabArt, Commande cde) {
		if (cde.taille() == 0) System.out.println("*** COMMANDE VIDE !!! ***");
		else System.out.print(cde.toString(tabArt));
	}
	
	public void facturer(TableDesArticles tabArt, Commande cde) {
		if (cde.taille() == 0) System.out.println("*** COMMANDE VIDE !!! ***");
		else System.out.print(cde.facturer(tabArt));
	}
	
	public LigneDeCommande saisie(TableDesArticles tabArt) {
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
}
