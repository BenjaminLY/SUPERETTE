package Utils;
import java.util.*;

public class ClientUtils {

  static Scanner sc = new Scanner(System.in);

  public static void main(String[] s) {
	System.out.println("Saisir votre jour, mois et année de naissance svp");
	int jour = lireEnt("Jour: ", 1, 31);
	int mois = lireEnt("Mois: ", 1, 12);
	int annee = lireEnt("Année: ", 0, Integer.MAX_VALUE);
	
	if (DateUser.validDate(jour, mois, annee)) {
		DateUser dat1 = new DateUser(jour,mois,annee);
		System.out.println("La date saisie est : " + dat1.toString());
		System.out.println("C'était un '" + dat1.jourDeSemaine() + "'");
		System.out.println("Vous êtes agés de : " + dat1.age() + " an(s)");
		dat1.lendemain();
		System.out.println("La date du lendemain de votre naissance était le " + dat1.toString());
		
		DateUser dat2 = new DateUser();
		DateUser dat3 = new DateUser();
		DateUser dat4 = new DateUser();
		dat3 = dat2.lendemainBis();
		dat4 = dat2.hierBis();
		System.out.println("Hier, nous étions le " + dat4.jourDeSemaine() + " " + dat4.toString());
		System.out.println("Aujourd'hui, nous sommes le " + dat2.jourDeSemaine() + " " + dat2.toString());
		System.out.println("Demain, nous serons le " + dat3.jourDeSemaine() + " " + dat3.toString());
	} 
	else System.out.print("DATE INVALIDE\n");
  }
  
  public static int lireEnt(String msg, int inf, int sup) {
	  System.out.print(msg);
	  int saisie;
	  boolean ok;
	  do {
		  saisie= sc.nextInt();
		  ok= (saisie >= inf) && (saisie <= sup);
		  if (!ok) System.out.println("HORS INTERVALLE");
	  } while(!ok);  
	  return saisie;
  }
}
