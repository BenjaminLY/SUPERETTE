package Serie52;

import IPane.ES;
import MesExceptions.Abandon;

public class GestionUneCommande52 implements MesInterfaces.InterfaceGestion<Commande52,TableDesArticles52> {
	
	public void menuGeneral(Commande52 cde, TableDesArticles52 tabArt) throws Abandon {
		int choix;
		do {
			choix= menuChoix();
			switch (choix) {
			case 1: ajouter(cde,tabArt); break;
			case 2: editer(cde,tabArt); break;
			case 3: supprimer(cde,tabArt); break;
			case 4: afficher(cde,tabArt); break;
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
				"FIN .........................................0\n" +
				"VOTRE CHOIX ....";
		return ES.saisie(msg, 0, 4);
	}
	
	public void ajouter(Commande52 cde, TableDesArticles52 tabArt) throws Abandon {
		LigneDeCommande52 ldc= saisie(tabArt);
		if (ldc != null) {
			LigneDeCommande52 ligne= cde.retourner(ldc.getCode());
			if (ligne == null) {
				cde.ajouter(ldc);
			} else {
				ligne.addToQuantite(ldc.getQuantite());
			}
		}
	}

	public void editer(Commande52 cde, TableDesArticles52 tabArt) throws Abandon {
		afficher(cde,tabArt);
		if (cde.taille() != 0) {
			int number= ES.saisie("Quelle ligne? ", 1, cde.taille());
			LigneDeCommande52 ligne= cde.retournerAvecIndice(number - 1);
			int quantite= ES.saisie("Quelle quantité ? ", 1);
			ligne.setQuantite(quantite);
		}
	}
	
	public void supprimer(Commande52 cde, TableDesArticles52 tabArt) throws Abandon {
		afficher(cde,tabArt);
		if (cde.taille() != 0) {
			int number= ES.saisie("Quelle ligne? ", 1, cde.taille());
			System.out.println(number);
			cde.supprimer(number - 1);
			
		}
	}
	
	public void afficher(Commande52 cde, TableDesArticles52 tabArt) {
		if (cde.taille() == 0) ES.affiche("*** COMMANDE VIDE !!! ***\n");
		else ES.affiche(cde.toString(tabArt));
	}
	
	public LigneDeCommande52 saisie(TableDesArticles52 tabArt) throws Abandon {
		int code= ES.saisie("Quel code? ", 1);
		ArticleAbstrait<Integer> art= tabArt.retourner(code);
		
		if (art != null) {
			if (art instanceof ArticlePromo52) {
				String msg= "Article en PROMO !!\nA partir de " + ((ArticlePromo52)art).getQuantiteMini() + " article(s), " +
							"vous obtenez une réduction de " + ((ArticlePromo52)art).getReduction() + "%\n";
				ES.affiche(msg);
			}
			int quantite= ES.saisie("Quelle quantité ? ", 1);
			return new LigneDeCommande52(code,quantite);
		} else {
			ES.affiche(" *** Ce code n'existe pas ? ");
			return null;
		}
	}
}
