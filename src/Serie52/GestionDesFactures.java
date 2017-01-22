package Serie52;

import Connexions.ConnexionFichier;
import IPane.ES;
import MesExceptions.Abandon;
import Utils.DateUser;

public class GestionDesFactures implements MesInterfaces.InterfaceGestion<TableDesFactures,TableDesCommandes52> {

	ConnexionFichier<TableDesFactures> fichTabFactures;
	
	public GestionDesFactures(String nomPhysique) {
		fichTabFactures= new ConnexionFichier<TableDesFactures>(nomPhysique);
	}
	
	public TableDesFactures recupererTab() {
		TableDesFactures tabFact= fichTabFactures.lire();
		if (tabFact == null) {
			ES.affiche("Création d'un nouveau fichier de factures ...");
			tabFact= new TableDesFactures();
		}
		return tabFact;
	}
	
	public void sauvegarder(TableDesFactures tabFact) {
		fichTabFactures.ecrire(tabFact);
	}
	
	public void menuGeneral(TableDesFactures tabFact, TableDesCommandes52 tabCde) {}
	
	public void menuGeneral(TableDesFactures tabFact, TableDesCommandes52 tabCde, TableDesArticles52 tabArt) throws Abandon {
		int choix;
		do {
			choix= menuChoix();
			switch (choix) {
			case 1: ajouter(tabFact, tabCde, tabArt);break;
			case 2: afficher(tabFact, tabCde); break; 
			case 3: supprimer(tabFact, tabCde); break; 
			case 4: afficherToutesLesFactures(tabFact); break;
			}
		} while (choix != 0);
	}
	
	public int menuChoix() throws Abandon {
		String msg= "\n********  MENU GESTION DES FACTURES  ********\n" +
					"______________________________________________\n" +
					"CREER UNE FACTURE ...........................1\n" +
					"AFFICHER UNE FACTURE ........................2\n" +
					"SUPPRIMER UNE FACTURE .......................3\n" +
					"AFFICHER TOUTES LES FACTURES ................4\n" +
					"FIN .........................................0\n" +
					"VOTRE CHOIX ....";
		return ES.saisie(msg, 0, 4);
	}
	
	public void ajouter(TableDesFactures tabFact, TableDesCommandes52 tabCde){}
	
	public void ajouter(TableDesFactures tabFact, TableDesCommandes52 tabCde, TableDesArticles52 tabArt) throws Abandon {
		if (tabCde.taille() != 0) {
			Commande52 cde= selectionCommande(tabCde);
			if (cde != null && cde.getEtatFacture() == false) {
				cde.setDateFacturation(new DateUser());
				cde.setEtatFacture(true);
				Facture facture= new Facture(cde.getDateFacturation(),cde.getNumCde(),cde.facturer(tabArt));
				tabFact.ajouter(facture);
				ES.affiche("** La commande a été facturée ! **\n" + facture.toString());
			} else ES.affiche("** Cette commande n'existe pas ou elle a déjà été facturée **");
		} else ES.affiche("** Aucune facture **\n");
	}
	
	private Commande52 selectionCommande(TableDesCommandes52 tabCde) throws Abandon {
		ES.affiche(tabCde.cle() + "\n");
		String numero= ES.saisie("Quelle commande ?");
		try {
			return tabCde.retourner(numero);			
		}catch (NullPointerException e) {
			return null;
		}
	}
	
	public void afficher(TableDesFactures tabFact, TableDesCommandes52 cde) throws Abandon {
		if (tabFact.taille() != 0) {
			Facture facture= selectionFacture(tabFact);
			if (facture != null) {
				ES.affiche(facture.toString());
			} else ES.affiche("** Cette facture n'existe pas **");
		} else ES.affiche("** Aucune facture **\n");
	}

	private Facture selectionFacture(TableDesFactures tabFact) throws Abandon {
		ES.affiche(tabFact.cle() + "\n");
		String numero= ES.saisie("Quel numéro de facture ?");
		try {
			return tabFact.retourner(numero);			
		}catch (NullPointerException e) {
			return null;
		}
	}

	public void supprimer(TableDesFactures tabFact, TableDesCommandes52 tabCde) throws Abandon {
		if (tabFact.taille() != 0) {
			Facture facture= selectionFacture(tabFact);
			if (facture != null && facture.supprimable()) {
				tabFact.supprimer(facture.getNumero());
				Commande52 cde= tabCde.retourner(facture.getNumero());
				boolean suppCde= ES.saisieOuiNon("Souhaitez-vous également supprimer la commande associée ?");
				if (suppCde) tabCde.supprimer(cde.getNumCde());
				else
					cde.setEtatFacture(false); 
					cde.setDateFacturation(null);
				ES.affiche("** Facture supprimée **");
			} else ES.affiche("** Cette facture n'existe pas OU elle ne peut être supprimée (< 7 jours) **");
		} else ES.affiche("** Aucune facture **\n");
	}
	
	public void afficherToutesLesFactures(TableDesFactures tabFact) {
		if (tabFact.taille() != 0) ES.affiche(tabFact.toString());
		else ES.affiche("** Aucune facture **\n"); 
	}
	
}
