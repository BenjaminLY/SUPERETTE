package Serie52;

import Utils.DateUser;
import java.io.Serializable;

public class Facture implements Serializable {
	
	private DateUser dateFacturation;
	private String numero;
	private String contenu;
	
	public Facture(DateUser dateFacturation, String numero, String contenu) {
		this.dateFacturation= dateFacturation;
		this.numero= numero;
		this.contenu= contenu;
	}
	
	public Facture() {}
	
	public String getNumero() {
		return numero;
	}
	
	public DateUser getDateFacturation() {
		return this.dateFacturation;
	}
	
	public boolean supprimable() {
		DateUser date= new DateUser(dateFacturation.getJour(),dateFacturation.getMois(),dateFacturation.getAnnee());
		date.ajouterNombreJours(7);
		return date.avant(new DateUser());
	}

	public String toString() {
		return "Numéro Facture : " + numero + " | Date de Facturation : " + dateFacturation.toString() + "\n" + contenu;
	}
	
}
