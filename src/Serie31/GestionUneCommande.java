package Serie31;

import IConsole.ES;
import MesExceptions.Abandon;
import Serie22.TableDesArticles;
import Serie22.Commande;
import Serie22.LigneDeCommande;

public class GestionUneCommande {
	
	public void menuGeneral(TableDesArticles tabArt, Commande cde) throws Abandon {
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
	
	public int menuChoix() throws Abandon {
		String msg= "\n**********  GESTION D'UNE COMMANDE  **********\n" +
				"______________________________________________\n" +
				"AJOUTER UNE LIGNE DE COMMANDE ...............1\n" +
				"EDITER LA QUANTITE D'UNE LIGNE DE COMMANDE ..2\n" +
				"SUPPRIMER UNE LIGNE DE COMMANDE .............3\n" +
				"AFFICHER LA COMMANDE ........................4\n" +
				"FACTURER LA COMMANDE ........................5\n" +
				"FIN .........................................0\n" +
				"VOTRE CHOIX ....";
		return ES.saisie(msg, 0, 5);
	}
	
	public void ajouter(TableDesArticles tabArt, Commande cde) throws Abandon {
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

	public void editer(TableDesArticles tabArt, Commande cde) throws Abandon {
		afficher(tabArt, cde);
		if (cde.taille() != 0) {
			int number= ES.saisie("Quelle ligne? ", 1, cde.taille());
			LigneDeCommande ligne= cde.retournerAvecIndice(number - 1);
			int quantite= ES.saisie("Quelle quantité ? ", 1); // BLY
			ligne.setQuantite(quantite);
		}
	}
	
	public void supprimer(TableDesArticles tabArt, Commande cde) throws Abandon {
		afficher(tabArt, cde);
		if (cde.taille() != 0) {
			int number= ES.saisie("Quelle ligne? ", 1, cde.taille());
			cde.supprimer(number - 1);			
		}
	}
	
	public void afficher(TableDesArticles tabArt, Commande cde) {
		if (cde.taille() == 0) ES.affiche("*** COMMANDE VIDE !!! ***\n");
		else ES.affiche(cde.toString(tabArt));
	}
	
	public void facturer(TableDesArticles tabArt, Commande cde) {
		if (cde.taille() == 0) ES.affiche("*** COMMANDE VIDE !!! ***\n");
		else ES.affiche(cde.facturer(tabArt));
	}
	
	public LigneDeCommande saisie(TableDesArticles tabArt) throws Abandon {
		int code= ES.saisie("Quel code? ", 1);
		
		if (tabArt.retourner(code) != null) {
			int quantite= ES.saisie("Quelle quantité ? ", 1);
			return new LigneDeCommande(code,quantite);
		} else {
			ES.affiche(" *** Ce code n'existe pas ? ");
			return null;
		}
	}
}
