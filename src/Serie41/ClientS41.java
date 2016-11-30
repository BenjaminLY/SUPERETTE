package Serie41;

import IConsole.ES;
import MesExceptions.Abandon;

public class ClientS41 {
	
	public static void main(String[] args) {
		TableDesArticles41 tabArt= new TableDesArticles41();
		TableDesCommandes41 tabCde= new TableDesCommandes41();
		GestionTableDesArticles41 gta= new GestionTableDesArticles41();
		GestionDesCommandes41 gdc= new GestionDesCommandes41();
		
		int choix;
		do {
			try  {
				choix= menuChoixGeneral();
				switch (choix) {
				case 1: gta.menuGeneral(tabArt, tabCde); break;
				case 2: gdc.menuGeneral(tabArt, tabCde); break;
				}
			} catch (Abandon ab){ 
				choix= 0;
			}
		} while (choix != 0);
		
		ES.affiche("Au revoir et à bientôt sur Superette");		
	}
	
	public static int menuChoixGeneral() throws Abandon {
		String msg= "\n*********  BIENVENUE A LA SUPERETTE  *********\n" +
					"______________________________________________\n" +
					"GESTION DES ARTICLES ........................1\n" +
					"GESTION D'UNE COMMANDE ......................2\n" +
					"FIN .........................................0\n" +
					"VOTRE CHOIX ....";
		return ES.saisie(msg, 0, 2);
	}
} 
