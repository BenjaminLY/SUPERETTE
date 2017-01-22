package Serie52;

import Connexions.ConnexionFichier;
import IPane.ES;
import MesExceptions.Abandon;
import Utils.DateUser;

public class GestionDesCommandes52 implements MesInterfaces.InterfaceGestion<TableDesCommandes52,TableDesArticles52> {
	
	ConnexionFichier<TableDesCommandes52> fichTabCommandes;
	
	public GestionDesCommandes52(String nomPhysique) {
		fichTabCommandes= new ConnexionFichier<TableDesCommandes52>(nomPhysique);
	}
	
	public TableDesCommandes52 recupererTab() {
		TableDesCommandes52 tabCde= fichTabCommandes.lire();
		if (tabCde == null) {
			ES.affiche("Création d'un nouveau fichier de commandes ...");
			tabCde= new TableDesCommandes52();
		}
		return tabCde;
	}
	
	public void sauvegarder(TableDesCommandes52 tabCde) {
		fichTabCommandes.ecrire(tabCde);
	}
	
	public void menuGeneral(TableDesCommandes52 tabCde, TableDesArticles52 tabArt) throws Abandon {
		int choix;
		do {
			choix= menuChoix();
			switch (choix) {
			case 1: ajouter(tabCde, tabArt);break;
			case 2: supprimer(tabCde, tabArt); break; 
			case 3: afficher(tabCde, tabArt); break; 
			case 4: editer(tabCde, tabArt); break; 
			case 5: afficherToutesLesCommandes(tabCde, tabArt); break;
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
					"FIN .........................................0\n" +
					"VOTRE CHOIX ....";
		return ES.saisie(msg, 0, 5);
	}
	
	public void ajouter(TableDesCommandes52 tabCde, TableDesArticles52 tabArt) throws Abandon {
		Commande52 cde= new Commande52();
		
		// attribution numero de commande et ajout dans stock
		String numero= premierNumeroDispo(tabCde);
		cde.setNumCde(numero);
		tabCde.ajouter(cde);
		
		GestionUneCommande52 guc= new GestionUneCommande52();
		guc.menuGeneral(cde, tabArt);
		if (cde.taille() == 0) { // Supp commande du stock si vide
			tabCde.supprimer(numero); 
		}
	}
	
	public void supprimer(TableDesCommandes52 tabCde, TableDesArticles52 tabArt) throws Abandon {
		if (tabCde.taille() != 0) { 
			Commande52 cde= selectionCommande(tabCde); 
			if (cde != null && cde.supprimable()) 
				tabCde.supprimer(cde.getNumCde());
			else ES.affiche("** Cette commande n'existe pas ou elle n'a pas encore été facturée **");
		} else ES.affiche("** Aucune commande **"); 
	}
	
	public void afficher(TableDesCommandes52 tabCde, TableDesArticles52 tabArt) throws Abandon {
		if (tabCde.taille() != 0) { 
			Commande52 cde= selectionCommande(tabCde);
			if (cde != null) 
				ES.affiche(cde.toString());
			else ES.affiche("** Cette commande n'existe pas. **"); 
		} else ES.affiche("** Aucune commande **\n"); 
	}
	
	public void editer(TableDesCommandes52 tabCde, TableDesArticles52 tabArt) throws Abandon {
		if (tabCde.taille() != 0) { 
			Commande52 cde= selectionCommande(tabCde); 
			if (cde != null) {
				GestionUneCommande52 guc= new GestionUneCommande52();
				guc.menuGeneral(cde,tabArt);
			} else ES.affiche("** Cette commande n'existe pas. **"); 
		} else ES.affiche("** Aucune commande **\n"); 
	}
	
	public void afficherToutesLesCommandes(TableDesCommandes52 tabCde, TableDesArticles52 tabArt) {
		if (tabCde.taille() != 0) ES.affiche(tabCde.toString(tabArt));
		else ES.affiche("** Aucune commande **\n"); 
	}
	
	public Commande52 selectionCommande(TableDesCommandes52 tabCde) throws Abandon {
		ES.affiche(tabCde.cle() + "\n");
		String numero= ES.saisie("Quelle commande ?");
		try {
			return tabCde.retourner(numero);			
		}catch (NullPointerException e) {
			return null;
		}
	}
	
	public String premierNumeroDispo(TableDesCommandes52 tabCde) {
		DateUser dateDuJour= new DateUser();
		String resultat= "";
		int numero= 0;
		do {
			numero++;
			resultat= dateDuJour.inversee() + numero;
		} while (tabCde.retourner(resultat) != null);
		
		return resultat;
	}
}
