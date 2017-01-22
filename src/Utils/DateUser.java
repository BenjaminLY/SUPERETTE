package Utils;
import java.util.*;
import java.io.Serializable;

public class DateUser implements Serializable {

  private int jour, mois, annee;

  public DateUser() { 
	  Calendar cal = Calendar.getInstance();
	  jour= cal.get(Calendar.DAY_OF_MONTH);
	  mois= cal.get(Calendar.MONTH) + 1;
	  annee= cal.get(Calendar.YEAR);
  }

  public DateUser(int jour, int mois, int annee) {
	  this.jour = jour; this.mois = mois; this.annee = annee;
  }

  public int getJour() { return jour; }
  public void setJour(int jj) { jour = jj; }
  
  public int getMois() { return mois; }
  public void setMois(int mm) { mois = mm; }
  
  public int getAnnee() { return annee; }
  public void setAnnee(int aa) { annee = aa; }

  public String toString() {
	  return (jour + "/" + mois + "/" + annee);
  }
  
  public String inversee() {
	  return ("" + annee + mois + jour);
  }
  
  public static int nbJourMax(int mois, int annee) {
	  switch(mois) {
		  case 4: case 6: case 9: case 11: return 30;
		  case 2: if ((annee % 400 == 0) || (annee % 100 != 0) && (annee % 4 == 0)) return 29; else return 28;
		  default: return 31;
	  }
  }
  
  public static boolean validDate(int jour, int mois, int annee) {
	  return (jour <= nbJourMax(mois, annee));
  }
  
  public void lendemain() {
	  jour++;
	  if (jour > nbJourMax(mois, annee)) {
		  jour=1; mois++; if (mois > 12){ mois=1; annee++; }
	  }
  }
  
  public DateUser lendemainBis() {
	  DateUser date = new DateUser(jour,mois,annee);
	  date.lendemain();
	  return date;
  }
  
  public void hier() {
	  jour--;
	  if (jour == 0) {
		  mois--; if (mois == 0){ mois=12; annee--; };
		  if (annee == 0) { annee=0; }
		  jour=nbJourMax(mois, annee);
	  }
  }
  
  public DateUser hierBis() {
	  DateUser date = new DateUser(jour,mois,annee);
	  date.hier();
	  return date;
  }
  
  public boolean avant(DateUser date) {
	  return (annee < date.annee) || (annee == date.annee && mois < date.mois || mois == date.mois && jour < date.jour);
  }
  
  public int age() {
	  DateUser today = new DateUser();
	  int age = 0;
	  if (this.avant(today)) {
		  age = today.annee - annee;
		  if (mois > today.mois || mois == today.mois && jour > today.jour) age--;
	  }
	  return age;
  }
  
  public int zeller() {
	  int mz, az, sz, year;
	  year= annee;
	  if (mois > 2) mz= mois-2; else { mz= mois+10; year--; }
	  az= year%100;
	  sz= (int)(year/100);
	  return (((int)(2.6*mz-0.2) + jour + az + (int)(az/4) + (int)(sz/4) - 2*sz) %7);
  }
  
  public String jourDeSemaine() {
	  DateUser anneeValide = new DateUser(15,10,1582);
	  if (anneeValide.avant(this)) {
		  int z= this.zeller();
		  switch(z) {
		  case 0: return "Dimanche";
		  case 1: return "Lundi";
		  case 2: return "Mardi";
		  case 3: return "Mercredi";
		  case 4: return "Jeudi";
		  case 5: return "Vendredi";
		  case 6: return "Samedi";
		  default: return "A FAIRE";
		  }
	  }
	  return "";
  }

  public void ajouterNombreJours(int nb) {
	  jour= jour + nb;
	  while (jour > nbJourMax(mois, annee)) {
		  jour+= jour - nbJourMax(mois, annee);
		  mois++;
		  if (mois > 12) { mois= 1; annee++; }
	  }
  }
  
}
