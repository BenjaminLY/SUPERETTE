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


public class FrameIdentification extends JFrame implements ActionListener {

	//tout ce qui est éditable en variables d'instances
	private JTextField login;
	private JPasswordField pwd;
	private JButton valid;
	
	public FrameIdentification(int i) { }
	
	public FrameIdentification() {
		setLayout(new GridLayout(3,2,10,10)); // dimensions de chaque composant
		setSize(300,200); // dimensionne la Frame
		setTitle("FRAME IDENTIFICATION");
		
		JPanel panel1, panel2, panel3;
		panel1= new JPanel();
		panel2= new JPanel();
		panel3= new JPanel();
		
		JLabel ident= new JLabel("Login : ");
		login= new JTextField(10);// va creer un evenement donc je met un ecouteur devant
		
		panel1.add(ident); panel1.add(login); login.addActionListener(this);
		this.add(panel1);
		
		JLabel lab2= new JLabel("Password : ");
		pwd= new JPasswordField(10); 
		
		panel2.add(lab2); panel2.add(pwd); pwd.addActionListener(this);
		this.add(panel2); // j'ajoute dans le panel
		
		valid= new JButton("VALIDER"); valid.addActionListener(this);
		panel3.add(valid); this.add(panel3);
		
		setLocationRelativeTo(null); // affiche la fenetre au milieu de l'ecran
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource()==valid) {
			String ident= login.getText();
			char[] mdpT= pwd.getPassword();
			String mdp= conv(mdpT);
			if (valid(ident,mdp)) { 
				ES.affiche("IDENTIFICATION OK!");
				new FrameClientS61(); this.setVisible(false);
			}
			else ES.affiche("PB D'IDENTIFICATION!!!");
			raz();
		}
	}
	
	public String conv(char[]tab) {
		String res= "";
		for (int i=0;i<tab.length;i++) res= res+tab[i];
		return res;
	}
	
	public boolean valid(String ident, String mdp) {
		return (ident.equals("BLY") && mdp.equals("BLY"));
	}
	
	public void raz() {
		login.setText(""); pwd.setText("");
	}	
	
}