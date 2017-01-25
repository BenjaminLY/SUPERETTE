package Serie61;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import IPane.ES;

import Serie52.*;

public class FrameGestionTableCommandes extends JFrame implements ActionListener {
	
	private JButton creation,suppression,affichage,fin;
	private TableDesArticles52 tabArt;
	private TableDesCommandes52 tabCde;
	
	//public FrameGestionTableArticles() {
	public FrameGestionTableCommandes(TableDesArticles52 tabArt, TableDesCommandes52 tabCde) {
		this.tabArt= tabArt;
		this.tabCde= tabCde;
		
		setLayout(new GridLayout(4,1,10,10));
		setSize(350,250);
		setTitle("GESTION DES ARTICLES");
		creation= new JButton("CREATION");
		suppression= new JButton("SUPPRESSION");
		affichage= new JButton("AFFICHAGE");
		fin= new JButton("FIN");
		
		add(creation); creation.addActionListener(this);
		add(suppression); suppression.addActionListener(this);
		add(affichage); affichage.addActionListener(this);
		add(fin); fin.addActionListener(this);
		
		this.setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {
		try {
			if (event.getSource()==affichage) afficher();
			//if (event.getSource()==creation) new FrameArticle(tabArt);
			if (event.getSource()==suppression) supprimer();
			if (event.getSource()==fin) { ES.affiche("AU REVOIR"); setVisible(false); }// new frame client principal normalement			
		} catch (Exception e) {
			
		}
		
	}
	
	public void afficher() {
		ES.affiche("\nTABLE DES ARTICLES **\n" + tabArt.toString());
	}
	
	public void supprimer() throws Exception {
		int code= ES.saisie("Quel code voulez-vous supprimer ?" + tabArt.cle(),1,9999);
		ES.affiche(""+code);
		if (tabArt.retourner(code) != null) tabArt.supprimer(code);
	}
}
