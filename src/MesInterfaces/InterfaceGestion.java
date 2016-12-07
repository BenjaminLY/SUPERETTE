package MesInterfaces;

public interface InterfaceGestion <TypeStructure, TypeStructure2> {
	
	public abstract void menuGeneral(TypeStructure tab1, TypeStructure2 tab2) throws Exception;
	
	public abstract int menuChoix() throws Exception;
	
	public abstract void ajouter(TypeStructure tab1,TypeStructure2 tab2) throws Exception;
	
	public abstract void supprimer(TypeStructure tab1, TypeStructure2 tab2) throws Exception;
	
	public abstract void afficher(TypeStructure tab1, TypeStructure2 tab2) throws Exception;
	
}
