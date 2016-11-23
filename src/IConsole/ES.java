package IConsole;

import java.util.*;
import MesExceptions.Abandon;

public class ES {
	
	static Scanner sc= new Scanner(System.in);
	
	public static void affiche(String msg) {
		System.out.print(msg);
	}
	
	public static char saisieCarac(String msg) {
		affiche(msg);
		return sc.next().charAt(0);
	}
	
	public static int saisie(String msg, int inf, int sup) throws Abandon {
		int saisie;
		affiche(msg);
		do {
			try {
				saisie= sc.nextInt();				
				if (saisie >= inf && saisie <= sup) break;
				throw new Abandon();
			} catch (InputMismatchException e) {
				affiche("** SAISIE NON NUMERIQUE ...... RESSAISISSEZ **\n");
				sc.nextLine();
			} catch (Abandon ab) {
				char rep= saisieCarac(" Voulez-vous abandonner ? (O/N) ");
				if (rep == 'o' || rep == 'O') throw ab;
				else affiche("** SAISIE HORS INTERVALLE ...... RESSAISISSEZ **\n");
			}
		} while (true);
		sc.nextLine();
		return saisie;
	}
	
	public static int saisie(String msg, int inf) throws Abandon {
		int saisie;
		affiche(msg);
		do {
			try {
				saisie= sc.nextInt();				
				if (saisie >= inf) break;
				throw new Abandon();
			} catch (InputMismatchException e) {
				affiche("** SAISIE NON NUMERIQUE ...... RESSAISISSEZ **\n");
				sc.nextLine();
			} catch (Abandon ab) {
				char rep= saisieCarac(" Voulez-vous abandonner ? (O/N) ");
				if (rep == 'o' || rep == 'O') throw ab;
				else affiche("** SAISIE HORS INTERVALLE ...... RESSAISISSEZ **\n");
			}
		} while (true);
		sc.nextLine();
		return saisie;
	}
	
	public static float saisie(String msg, float inf, float sup) {
		float saisie;
		affiche(msg);
		do {
			try {
				saisie= sc.nextFloat();				
				if (saisie >= inf && saisie <= sup) break;
			} catch (InputMismatchException e) {
				affiche("** SAISIE NON NUMERIQUE (Merci d'utiliser une virgule pour délimiter la partie décimale) ...... RESSAISISSEZ **\n");
				sc.nextLine();
			}
		} while (true);
		sc.nextLine();
		return saisie;
	}
	
	public static float saisie(String msg, float inf) {
		float saisie;
		affiche(msg);
		do {
			try {
				saisie= sc.nextFloat();				
				if (saisie >= inf) break;
			} catch (InputMismatchException e) {
				affiche("** SAISIE NON NUMERIQUE (Merci d'utiliser une virgule pour délimiter la partie décimale) ...... RESSAISISSEZ **\n");
				sc.nextLine();
			}
		} while (true);
		sc.nextLine();
		return saisie;
	}
	
	public static String saisie(String msg) {
		affiche(msg);
		return sc.nextLine();
	}
}
