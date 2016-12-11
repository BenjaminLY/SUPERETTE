package Serie42;

import IPane.ES;
import MesExceptions.Abandon;

public class GestionTableDesArticles42 implements MesInterfaces.InterfaceGestion<TableDesArticles42,TableDesCommandes42> {
	
	public void menuGeneral(TableDesArticles42 tabArt, TableDesCommandes42 tabCde) throws Abandon {
		int choix;
		do {
			choix= menuChoix();
			switch (choix) {
			case 1: afficher(tabArt, tabCde); break;
			case 2: afficherPromotion(tabArt); break;
			case 3: ajouter(tabArt, tabCde); break;
			case 4: supprimer(tabArt, tabCde); break;
			case 5: editerPromotion(tabArt, tabCde); break;
			}
		} while (choix != 0);
	}
	
	public int menuChoix() throws Abandon {
		String msg= "\n***********  GESTION DES ARTICLES  ***********\n" +
					"______________________________________________\n" +
					"AFFICHER TOUS LES ARTICLES ..................1\n" +
					"AFFICHER LES ARTICLES EN PROMOTION ..........2\n" +
					"AJOUTER UN NOUVEL ARTICLE ...................3\n" +
					"SUPPRIMER UN ARTICLE ........................4\n" +
					"DEFINIR UN ARTICLE EN PROMO .................5\n" +
					"FIN .........................................0\n" +
					"VOTRE CHOIX ....";
		return ES.saisie(msg, 0, 5);
	}
	
	public void afficher(TableDesArticles42 tabArt, TableDesCommandes42 tabCde) {
		if (tabArt.taille() == 0) ES.affiche("*** Stock vide ! ***\n");
		else ES.affiche(tabArt.toString() + "\n");
	}
	
	public void afficherPromotion(TableDesArticles42 tabArt) {
		if (tabArt.taille() != 0) ES.affiche(tabArt.toStringPromo());
		else ES.affiche("\n** Stock vide ! **\n");
	}
	
	public void ajouter(TableDesArticles42 tabArt, TableDesCommandes42 tabCde) throws Abandon {
		ES.affiche("\n*** CREATION d'un nouvel ARTICLE ***\n");
		ArticleAbstrait<Integer> article;
		do {
			article= saisie(tabArt);
			if (article == null)
				ES.affiche("*** Article de code déjà existant ! ***\n");
		} while (article == null);
		tabArt.ajouter(article);
	}
	
	public void supprimer(TableDesArticles42 tabArt, TableDesCommandes42 tabCde) throws Abandon {
		if (tabArt.taille() == 0) ES.affiche("*** Stock vide ! Pas de suppression possible. ***");
		else {
			ES.affiche(tabArt.cle() + "\n");
			int code= ES.saisie("Quel code voulez-vous supprimer? ", 1);
			tabArt.supprimer(code, tabCde);		
		}
	}
	
	public void editerPromotion(TableDesArticles42 tabArt, TableDesCommandes42 tabCde) throws Abandon {
		if (tabArt.taille() == 0) ES.affiche("*** Stock vide ! Pas d'édition possible. ***");
		else {
			ES.affiche(tabArt.cle() + "\n"); // BLY
			int code= ES.saisie("Quel est le code de l'article que vous voulez mettre en promotion? ", 1);
			ArticleAbstrait<Integer> art= tabArt.retourner(code);
			if (art instanceof ArticlePromo42) {
				ES.affiche("** Article déjà en Promotion ! Qté min : " + ((ArticlePromo42)art).getQuantiteMini() + " | Réduction: " + ((ArticlePromo42) art).getReduction() + "\n");
				if (ES.saisieOuiNon("Voulez-vous changer les paramètres de la promo en cours? (o/n)")) {
					art= (Article42)art;
					int quantiteMini= ES.saisie("Quantite minimum ? : ", 1, Integer.MAX_VALUE);
					float reduction= ES.saisie("Quelle réduction ? (en %) : ", 0, 100);
					ArticleAbstrait<Integer> article= new ArticlePromo42(art.getCode(),art.getDesignation(),art.getPu(),quantiteMini,reduction);
					tabArt.supprimer(code, tabCde);
					tabArt.ajouter(article);
				}
			} else {
				int quantiteMini= ES.saisie("Quantite minimum ? : ", 1, Integer.MAX_VALUE);
				float reduction= ES.saisie("Quelle réduction ? (en %) : ", 0, 100);
				ArticleAbstrait<Integer> article= new ArticlePromo42(art.getCode(),art.getDesignation(),art.getPu(),quantiteMini,reduction);
				tabArt.supprimer(code, tabCde);
				tabArt.ajouter(article);					
			}
		}
	}
	
	public ArticleAbstrait<Integer> saisie(TableDesArticles42 tabArt) throws Abandon {
		int code= ES.saisie("Quel code? ", 1);
		
		if (tabArt.retourner(code) == null) {
			String designation= ES.saisie("Désignation du produit (minimum 8 caractères)? ");
			float pu= ES.saisie("Prix unitaire? ", (float)0);
			
			if (ES.saisieOuiNon("Cet article est-il en promotion ? (o/n)")) {
				int quantiteMini= ES.saisie("Quantite minimum ? : ", 1, Integer.MAX_VALUE);
				float reduction= ES.saisie("Quelle réduction ? (en %) : ", 0, 100);
				return new ArticlePromo42(code,designation,pu,quantiteMini,reduction);
			}
			
			else return new Article42(code,designation,pu);
		} else return null;
	}
}
