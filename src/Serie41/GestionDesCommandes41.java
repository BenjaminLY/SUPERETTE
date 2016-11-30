package Serie41;

import IConsole.ES;
import MesExceptions.Abandon;

public class GestionDesCommandes41 {
	
	public void menuGeneral(TableDesArticles41 tabArt, TableDesCommandes41 tabCde) throws Abandon {
		int choix;
		do {
			choix= menuChoix();
			switch (choix) {
			case 1: ajouter(tabCde, tabArt);break;
			case 2: supprimer(tabCde); break; 
			case 3: afficher(tabCde); break; 
			case 4: editer(tabCde, tabArt); break; 
			case 5: afficherToutesLesCommandes(tabCde, tabArt); break;
			case 6: facturer(tabArt, tabCde); break;
			case 0: break;
			}
		} while (choix != 0);
	}
	
	public int menuChoix() throws Abandon {
		String msg= "\n********  MENU GESTION DES COMMANDES  ********\n" +
					"______________________________________________\n" +
					"CREER UNE COMMANDE ..........................1\n" +
					"SUPPRIMER UNE COMMANDE ......................2\n" +
					"AFFICHER UNE COMMANDE .......................3\n" +
					"EDITER UNE COMMANDE .........................4\n" +
					"AFFICHER TOUTES LES COMMANDES ...............5\n" +
					"FACTURER UNE COMMANDE .......................6\n" +
					"FIN .........................................0\n" +
					"VOTRE CHOIX ....";
		return ES.saisie(msg, 0, 6);
	}
	
	public void ajouter(TableDesCommandes41 tabCde, TableDesArticles41 tabArt) throws Abandon {
		Commande41 cde= new Commande41();
		
		// attribution numero de commande et ajout dans stock
		int numero= premierNumeroDispo(tabCde);
		cde.setNumCde(numero);
		tabCde.ajouter(cde);
		
		GestionUneCommande41 guc= new GestionUneCommande41();
		guc.menuGeneral(tabArt, cde);
		if (cde.taille() == 0) { // Supp commande du stock si vide
			tabCde.supprimer(numero); 
		}
	}
	
	public void supprimer(TableDesCommandes41 tabCde) throws Abandon {
		if (tabCde.taille() != 0) { 
			Commande41 cde= selectionCommande(tabCde); 
			if (cde != null) tabCde.supprimer(cde.getNumCde());
		} else ES.affiche("** Aucune commande **"); 
	}
	
	public void afficher(TableDesCommandes41 tabCde) throws Abandon {
		if (tabCde.taille() != 0) { 
			Commande41 cde= selectionCommande(tabCde);
			if (cde != null) 
				ES.affiche(cde.toString());
			else ES.affiche("** Cette commande n'existe pas. **"); 
		} else ES.affiche("** Aucune commande **\n"); 
	}
	
	public void editer(TableDesCommandes41 tabCde, TableDesArticles41 tabArt) throws Abandon {
		if (tabCde.taille() != 0) { 
			Commande41 cde= selectionCommande(tabCde); 
			if (cde != null) {
				GestionUneCommande41 guc= new GestionUneCommande41();
				guc.menuGeneral(tabArt, cde);
			} else ES.affiche("** Cette commande n'existe pas. **"); 
		} else ES.affiche("** Aucune commande **\n"); 
	}
	
	public void afficherToutesLesCommandes(TableDesCommandes41 tabCde, TableDesArticles41 tabArt) {
		if (tabCde.taille() != 0) ES.affiche(tabCde.toString(tabArt));
		else ES.affiche("** Aucune commande **\n"); 
	}
	
	public void facturer(TableDesArticles41 tabArt, TableDesCommandes41 tabCde) throws Abandon {
		if (tabCde.taille() != 0) { 
			Commande41 cde= selectionCommande(tabCde);
			if (cde != null)
				ES.affiche("\n" + cde.facturer(tabArt) + "\n");
			else ES.affiche("** Cette commande n'existe pas. **");
		} else ES.affiche("** Aucune commande **\n");
	}
	
	public Commande41 selectionCommande(TableDesCommandes41 tabCde) throws Abandon {
		ES.affiche(tabCde.cle() + "\n");
		int numero= ES.saisie("Quelle commande ?", 1);
		return tabCde.retourner(numero);
	}
	
	public int premierNumeroDispo(TableDesCommandes41 tabCde) {
		int numero= 1;
		while (tabCde.retourner(numero) != null) numero++;
		return numero;
	}
}
