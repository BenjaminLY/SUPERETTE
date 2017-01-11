package Connexions;
import java.io.*;

import IPane.ES;

public class ConnexionFichier <TypeStructure> {

	private String nomPhysique;
	
	public ConnexionFichier(String nomP) {
		nomPhysique= nomP;
	}
	
	public ConnexionFichier() {}
	
	public TypeStructure lire() {
		TypeStructure tab= null;
		
		// associe le nom physique au nom logique
		try {
			FileInputStream fis = new FileInputStream(nomPhysique);
			ObjectInputStream ois= new ObjectInputStream(fis);
			tab= (TypeStructure)ois.readObject();
		} catch (FileNotFoundException e) {
			ES.affiche("Lecture d'un fichier inexistant ... ***\n");
		} catch (IOException e) {
			// Pb de lecture
			ES.affiche("** Problème disk sur la lecture du fichier **\n");
		} catch (ClassNotFoundException e) {
			ES.affiche("Type incohérent ... ***\n");
		}
		return tab;
	}
	
	public void ecrire(TypeStructure tab) {
		
		try {
			// si true alors ecrire a la fin du fichier // si false on ecrase le nouveau
			FileOutputStream fos= new FileOutputStream(nomPhysique, false);
			ObjectOutputStream oos= new ObjectOutputStream(fos );
			oos.writeObject(tab);
		} catch (FileNotFoundException e) {
			// Fichier n'existe pas, alors il va etre créé
			// en infos, cela n'influe pas sur la creation de ce fichier
			ES.affiche("Fichier nouveau ... il sera créé ***\n");
		} catch (IOException e) {
			// Pb de lecture
			ES.affiche("** Problème d'écriture sur le fichier **\n");
		}
		
	}
	
}
