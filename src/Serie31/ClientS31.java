package Serie31;
import Serie22.TableDesArticles;
import Serie22.TableDesCommandes;
import Serie31.GestionDesCommandes;
import Serie31.GestionTableDesArticles;
import IConsole.ES;
import MesExceptions.Abandon;

public class ClientS31 {
	
	public static void main(String[] args) {
		TableDesArticles tabArt= new TableDesArticles();
		TableDesCommandes tabCde= new TableDesCommandes();
		GestionTableDesArticles gta= new GestionTableDesArticles();
		GestionDesCommandes gdc= new GestionDesCommandes();
		
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
		
		System.out.println("Au revoir et à bientôt sur Superette");		
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
