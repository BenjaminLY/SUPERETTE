package Serie61;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import IPane.ES;
import MesExceptions.Abandon;
import Serie52.GestionDesCommandes52;
import Serie52.GestionDesFactures;
import Serie52.GestionTableDesArticles52;
import Serie52.TableDesArticles52;
import Serie52.TableDesCommandes52;
import Serie52.TableDesFactures;

public class FrameClientS61 extends JFrame implements ActionListener {
	
	private JButton gestionArticles,gestionCommandes,gestionFactures,fin;
	private TableDesArticles52 tabArt= new TableDesArticles52();
	private TableDesCommandes52 tabCde;
	private TableDesFactures tabFact;
	
	public FrameClientS61() {
		
		setLayout(new GridLayout(4,1,10,10));
		setSize(300,400);
		setTitle("** BIENVENUE A LA SUPERETTE **");
		
//		GestionTableDesArticles52 gta= new GestionTableDesArticles52("CATALOGUE.DAT");
//		tabArt= gta.recupererTab();
//		
//		TableDesCommandes52 tabCde;
//		GestionDesCommandes52 gdc= new GestionDesCommandes52("COMMANDES.DAT");
//		tabCde= gdc.recupererTab();
//		
//		TableDesFactures tabFact;
//		GestionDesFactures gdf= new GestionDesFactures("FACTURES.DAT");
//		tabFact= gdf.recupererTab();
		
		gestionArticles= new JButton("GESTION DES ARTICLES");
		gestionCommandes= new JButton("GESTION DES COMMANDES");
		gestionFactures= new JButton("GESTION DES FACTURES");
		fin= new JButton("FIN");
		
		add(gestionArticles); gestionArticles.addActionListener(this);
		add(gestionCommandes); gestionCommandes.addActionListener(this);
		add(gestionFactures); gestionFactures.addActionListener(this);
		add(fin); fin.addActionListener(this);
		
		setLocationRelativeTo(null);
		setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	
	public void actionPerformed(ActionEvent event) {
		try {
			if (event.getSource()==gestionArticles) new FrameGestionTableArticles(tabArt,tabCde);
			if (event.getSource()==gestionCommandes) new FrameGestionTableCommandes(tabCde,tabArt);
			if (event.getSource()==gestionFactures) new FrameGestionTableFactures(tabFact,tabCde,tabArt);
			if (event.getSource()==fin) { ES.affiche("Au revoir et à bientôt sur Superette"); setVisible(false); System.exit(0); }
		} catch (Exception e) {
			
		}
	}
	
	public void recupererTab(){
		
	}
}
