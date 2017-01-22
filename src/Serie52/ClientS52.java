package Serie52;

import IPane.ES;
import MesExceptions.Abandon;

public class ClientS52 {
	
	public static void main(String[] args) {
		
		TableDesArticles52 tabArt;
		GestionTableDesArticles52 gta= new GestionTableDesArticles52("CATALOGUE.DAT");
		tabArt= gta.recupererTab();
		
		TableDesCommandes52 tabCde;
		GestionDesCommandes52 gdc= new GestionDesCommandes52("COMMANDES.DAT");
		tabCde= gdc.recupererTab();
		
		TableDesFactures tabFact;
		GestionDesFactures gdf= new GestionDesFactures("FACTURES.DAT");
		tabFact= gdf.recupererTab();
		
		int choix;
		do {
			try  {
				choix= menuChoixGeneral();
				switch (choix) {
				case 1: gta.menuGeneral(tabArt, tabCde); break;
				case 2: gdc.menuGeneral(tabCde, tabArt); break;
				case 3: gdf.menuGeneral(tabFact, tabCde, tabArt);break;
				}
			} catch (Abandon ab){ 
				choix= 0;
			}
		} while (choix != 0);
		ES.affiche("** Sauvegarde du catalogue, des commandes et des factures **\n");
		gta.sauvegarder(tabArt);
		gdc.sauvegarder(tabCde);
		gdf.sauvegarder(tabFact);
		ES.affiche("Au revoir et à bientôt sur Superette");		
	}
	
	public static int menuChoixGeneral() throws Abandon {
		String msg= "\n*********  BIENVENUE A LA SUPERETTE  *********\n" +
					"______________________________________________\n" +
					"GESTION DES ARTICLES ........................1\n" +
					"GESTION D'UNE COMMANDE ......................2\n" +
					"GESTION DES FACTURES ........................3\n" +
					"FIN .........................................0\n" +
					"VOTRE CHOIX ....";
		return ES.saisie(msg, 0, 3);
	}
} 
