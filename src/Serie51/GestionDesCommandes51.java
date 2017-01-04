package Serie51;

import IPane.ES;
import MesExceptions.Abandon;

public class GestionDesCommandes51 implements MesInterfaces.InterfaceGestion<TableDesCommandes51,TableDesArticles51> {
	
	public void menuGeneral(TableDesCommandes51 tabCde, TableDesArticles51 tabArt) throws Abandon {
		int choix;
		do {
			choix= menuChoix();
			switch (choix) {
			case 1: ajouter(tabCde, tabArt);break;
			case 2: supprimer(tabCde, tabArt); break; 
			case 3: afficher(tabCde, tabArt); break; 
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
	
	public void ajouter(TableDesCommandes51 tabCde, TableDesArticles51 tabArt) throws Abandon {
		Commande51 cde= new Commande51();
		
		// attribution numero de commande et ajout dans stock
		int numero= premierNumeroDispo(tabCde);
		cde.setNumCde(numero);
		tabCde.ajouter(cde);
		
		GestionUneCommande51 guc= new GestionUneCommande51();
		guc.menuGeneral(cde, tabArt);
		if (cde.taille() == 0) { // Supp commande du stock si vide
			tabCde.supprimer(numero); 
		}
	}
	
	public void supprimer(TableDesCommandes51 tabCde, TableDesArticles51 tabArt) throws Abandon {
		if (tabCde.taille() != 0) { 
			Commande51 cde= selectionCommande(tabCde); 
			if (cde != null) tabCde.supprimer(cde.getNumCde());
		} else ES.affiche("** Aucune commande **"); 
	}
	
	public void afficher(TableDesCommandes51 tabCde, TableDesArticles51 tabArt) throws Abandon {
		if (tabCde.taille() != 0) { 
			Commande51 cde= selectionCommande(tabCde);
			if (cde != null) 
				ES.affiche(cde.toString());
			else ES.affiche("** Cette commande n'existe pas. **"); 
		} else ES.affiche("** Aucune commande **\n"); 
	}
	
	public void editer(TableDesCommandes51 tabCde, TableDesArticles51 tabArt) throws Abandon {
		if (tabCde.taille() != 0) { 
			Commande51 cde= selectionCommande(tabCde); 
			if (cde != null) {
				GestionUneCommande51 guc= new GestionUneCommande51();
				guc.menuGeneral(cde,tabArt);
			} else ES.affiche("** Cette commande n'existe pas. **"); 
		} else ES.affiche("** Aucune commande **\n"); 
	}
	
	public void afficherToutesLesCommandes(TableDesCommandes51 tabCde, TableDesArticles51 tabArt) {
		if (tabCde.taille() != 0) ES.affiche(tabCde.toString(tabArt));
		else ES.affiche("** Aucune commande **\n"); 
	}
	
	public void facturer(TableDesArticles51 tabArt, TableDesCommandes51 tabCde) throws Abandon {
		if (tabCde.taille() != 0) { 
			Commande51 cde= selectionCommande(tabCde);
			if (cde != null)
				ES.affiche("\n" + cde.facturer(tabArt) + "\n");
			else ES.affiche("** Cette commande n'existe pas. **");
		} else ES.affiche("** Aucune commande **\n");
	}
	
	public Commande51 selectionCommande(TableDesCommandes51 tabCde) throws Abandon {
		ES.affiche(tabCde.cle() + "\n");
		int numero= ES.saisie("Quelle commande ?", 1);
		return tabCde.retourner(numero);
	}
	
	public int premierNumeroDispo(TableDesCommandes51 tabCde) {
		int numero= 1;
		while (tabCde.retourner(numero) != null) numero++;
		return numero;
	}
}
