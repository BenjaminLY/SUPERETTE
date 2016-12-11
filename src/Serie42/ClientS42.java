package Serie42;

import IPane.ES;
import MesExceptions.Abandon;

public class ClientS42 {
	
	public static void main(String[] args) {
		
		TableDesArticles42 tabArt= new TableDesArticles42();
		TableDesCommandes42 tabCde= new TableDesCommandes42();
		GestionTableDesArticles42 gta= new GestionTableDesArticles42();
		GestionDesCommandes42 gdc= new GestionDesCommandes42();
		
		int choix;
		do {
			try  {
				choix= menuChoixGeneral();
				switch (choix) {
				case 1: gta.menuGeneral(tabArt, tabCde); break;
				case 2: gdc.menuGeneral(tabCde, tabArt); break;
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
