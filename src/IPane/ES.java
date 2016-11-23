package IPane;

import javax.swing.*;
import MesExceptions.Abandon;

public class ES {
	
	static final String titre= "SUPERETTE";

	public static void affiche(String mes) {
		JOptionPane.showMessageDialog(null, mes);
	}
	
	public static char saisieChar(String mes) {
		do {
			String saisi= JOptionPane.showInputDialog(mes);
			try { 
				char rep= saisi.charAt(0); 
				return rep; 
			}
			catch (NullPointerException e) { affiche(" Chaîne vide ! "); }
		} while (true);
	}
	
	public static boolean saisieOuiNon(String mes) {
		int saisi= JOptionPane.showConfirmDialog(null, mes, "A CONFIRMER", JOptionPane.YES_NO_OPTION);
		return saisi == 0;
	}
	
	public static int saisie(String mes, int inf, int sup) throws Abandon {
		String saisi;
		do {
			try {
				saisi= JOptionPane.showInputDialog(null,mes,titre,JOptionPane.NO_OPTION);
				if (saisi == null) throw new Abandon();
				if (saisi.equals("")) throw new Exception();
				int valeur;
				valeur= Integer.parseInt(saisi);
				if (valeur >= inf && valeur <= sup) return valeur;
				affiche(" Saisie hors intervalle... Resaisissez svp ! ");							
			} catch (NumberFormatException e) {
				affiche(" Saisie non numérique... Resaisissez svp ! ");
			} catch (Abandon ab) {
				if (saisieOuiNon("Abandon ?")) throw ab;
			} catch (Exception e) {
				affiche(" Merci de saisir quelque chose svp. ");
			}
		}while(true);
	}
	
	public static int saisie(String mes, int inf) throws Abandon {
		String saisi;
		do {
			try {
				saisi= JOptionPane.showInputDialog(mes);
				if (saisi == null) throw new Abandon();
				if (saisi.equals("")) throw new Exception();
				int valeur;
				valeur= Integer.parseInt(saisi);
				if (valeur >= inf) return valeur;
				affiche(" Saisie inférieur à " + inf + " ... Resaisissez svp ! ");							
			} catch (NumberFormatException e) {
				affiche(" Saisie non numérique... Resaisissez svp ! ");
			} catch (Abandon ab) {
				if (saisieOuiNon("Abandon ?")) throw ab;
			} catch (Exception e) {
				affiche(" Merci de saisir quelque chose svp. ");
			}
		}while(true);
	}
	
	public static float saisie(String mes, float inf, float sup) throws Abandon {
		String saisi;
		do {
			try {
				saisi= JOptionPane.showInputDialog(mes);
				if (saisi == null) throw new Abandon();
				if (saisi.equals("")) throw new Exception();
				float valeur;
				valeur= Float.parseFloat(saisi);
				if (valeur >= inf && valeur <= sup) return valeur;
				affiche(" Saisie hors intervalle... Resaisissez svp ! ");							
			} catch (NumberFormatException e) {
				affiche(" Saisie non numérique... Resaisissez svp ! ");
			} catch (Abandon ab) {
				if (saisieOuiNon("Abandon ?")) throw ab;
			} catch (Exception e) {
				affiche(" Merci de saisir quelque chose svp. ");
			}
		}while(true);
	}
	
	public static float saisie(String mes, float inf) throws Abandon {
		String saisi;
		do {
			try {
				saisi= JOptionPane.showInputDialog(mes);
				if (saisi == null) throw new Abandon();
				if (saisi.equals("")) throw new Exception();
				float valeur;
				valeur= Float.parseFloat(saisi);
				if (valeur >= inf) return valeur;
				affiche(" Saisie inférieur à " + inf + " ... Resaisissez svp ! ");							
			} catch (NumberFormatException e) {
				affiche(" Saisie non numérique... Resaisissez svp ! ");
			} catch (Abandon ab) {
				if (saisieOuiNon("Abandon ?")) throw ab;
			} catch (Exception e) {
				affiche(" Merci de saisir quelque chose svp. ");
			}
		}while(true);
	}
	
	public static String saisie(String mes) {
		return JOptionPane.showInputDialog(mes);
	}
}
