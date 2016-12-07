package MesInterfaces;

public interface InterfaceStructure <TypeMetier,TypeCode> {

	public abstract TypeMetier retourner(TypeCode code);
	
	public abstract void ajouter(TypeMetier metier);
	
	public abstract void supprimer(TypeCode code);
	
	public abstract int taille();
	
}
