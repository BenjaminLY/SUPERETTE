package Serie51;

import IPane.ES;
import MesExceptions.Abandon;

public class ClientS51 {
	
	public static void main(String[] args) {
		
		TableDesArticles51 tabArt= new TableDesArticles51();
		TableDesCommandes51 tabCde= new TableDesCommandes51();
		GestionTableDesArticles51 gta= new GestionTableDesArticles51();
		GestionDesCommandes51 gdc= new GestionDesCommandes51();
		
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
