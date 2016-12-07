package MesInterfaces;

public interface EntreeSortie {
	
	public abstract int saisie(String msg, int inf, int sup) throws Exception;
	
	public abstract int saisie(String msg, int inf) throws Exception;
	
	public abstract float saisie(String msg, float inf, float sup) throws Exception;
	
	public abstract float saisie(String msg, float inf) throws Exception;
	
	public abstract void affiche(String msg);
	
	public abstract String saisie(String msg);
	
	public abstract boolean saisieOuiNon(String msg);
	
	public abstract char saisieChar(String msg);
	
}
