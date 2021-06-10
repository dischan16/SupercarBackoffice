import java.awt.EventQueue;
import java.sql.*;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

public class creation_utilisateur {

	private JFrame frame;
	private JTextField txtnom;
	private JTextField txtprenom;
	private JTextField txtemail;
	private JTextField txtmotdepasse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					creation_utilisateur window = new creation_utilisateur();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public creation_utilisateur() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
 
	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/backoffice", "root","");
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          
	        }
	        catch (SQLException ex) 
	        {
	        	 
	        }
	    }
	 
	  public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select * from vendeur_client");
		    rs = pst.executeQuery();
	    	} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
	    	 } 
	    }
	  
	  //Junit test
	  
	  public static String testNom(String nom) {
		  final String NOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
		  final Pattern NOM_PATTERN = Pattern.compile(NOM_REGEX);

		  if (NOM_PATTERN.matcher(nom).matches() == false) {
		  JOptionPane.showMessageDialog(null, "L`insertion du nom n`est pas bon");
		  }

		  return nom;
		  }

		  public static String testPrenom(String prenom) {
		  return prenom;
		  }
	
	 

	/**
	 * Initialise the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 460, 482);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CREATION UTILISATEUR");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(42, -18, 398, 79);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 71, 430, 379);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblnom = new JLabel("Nom");
		lblnom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblnom.setBounds(24, 43, 99, 33);
		panel.add(lblnom);
		
		JLabel lblprenom = new JLabel("Prenom");
		lblprenom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblprenom.setBounds(24, 75, 99, 33);
		panel.add(lblprenom);
		
		txtnom = new JTextField();
		txtnom.setBounds(160, 51, 211, 22);
		panel.add(txtnom);
		txtnom.setColumns(10);
		
		txtprenom = new JTextField();
		txtprenom.setColumns(10);
		txtprenom.setBounds(160, 83, 211, 22);
		panel.add(txtprenom);
		
		JLabel Lblsex = new JLabel("Type");
		Lblsex.setFont(new Font("Tahoma", Font.BOLD, 14));
		Lblsex.setBounds(24, 173, 99, 33);
		panel.add(Lblsex);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblemail.setBounds(24, 107, 99, 33);
		panel.add(lblemail);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(160, 115, 211, 22);
		panel.add(txtemail);
		
		JLabel lbltelephone = new JLabel("Mot de passe");
		lbltelephone.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltelephone.setBounds(24, 141, 99, 33);
		panel.add(lbltelephone);
		
		txtmotdepasse = new JTextField();
		txtmotdepasse.setColumns(10);
		txtmotdepasse.setBounds(160, 149, 211, 22);
		panel.add(txtmotdepasse);
		
		JComboBox droptype = new JComboBox();
		droptype.setBounds(160, 181, 211, 21);
		panel.add(droptype);
		
		droptype.addItem("");
		droptype.addItem("Vente");
		droptype.addItem("RH");
		droptype.addItem("Comptabilite");
		droptype.addItem("Administration");
		droptype.addItem("Manager");
		
		JButton btnNewButton = new JButton("Creation");
		btnNewButton.setBounds(10, 295, 107, 50);
		panel.add(btnNewButton);
		
		JButton btnClear = new JButton("Effacer");
		btnClear.setBounds(160, 295, 107, 50);
		panel.add(btnClear);
		
		JButton btnExit = new JButton("Sortir");
		btnExit.setBounds(313, 295, 107, 50);
		panel.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtnom.setText("");
				txtprenom.setText("");
				txtemail.setText("");
				txtmotdepasse.setText("");
				droptype.setSelectedItem(""); 
				txtnom.requestFocus();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{			
				String nom,prenom,email,mot_de_passe,type;
				nom = txtnom.getText();
				prenom = txtprenom.getText();
				email = txtemail.getText();
				mot_de_passe = txtmotdepasse.getText();
				type = droptype.getSelectedItem().toString();
				
					 
					 try {
						 
						 	final String NOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
						 
						    final Pattern NOM_PATTERN = Pattern.compile(NOM_REGEX);
						    
						    final String PRENOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
						 
						    final Pattern PRENOM_PATTERN = Pattern.compile(PRENOM_REGEX);
						    
						    final String EMAIL_REGEX = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";
						    
						    final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
						    
						    final String MOT_DE_PASSE_REGEX = "((?=.*[a-z])(?=.*\\\\d)(?=.*[A-Z])(?=.*[@#$%!]).{12,30})";
						    
						    final Pattern MOT_DE_PASSE_PATTERN = Pattern.compile(EMAIL_REGEX);
						    
						   
						    
						    if (NOM_PATTERN.matcher(nom).matches() == false) {
						    	JOptionPane.showMessageDialog(null, "L`insertion du nom n`est pas bon");
						    }
						    
						    if(PRENOM_PATTERN.matcher(prenom).matches()  == false) {
						    	JOptionPane.showMessageDialog(null, "L`insertion du prenom n`est pas bon");
						    }
						    
						    
						    if( EMAIL_PATTERN.matcher(email).matches() == false) {
						    	JOptionPane.showMessageDialog(null, "L`insertion de l'email n`est pas bon");
						    }
						    
						    if( MOT_DE_PASSE_PATTERN.matcher(mot_de_passe).matches()  == false) {
						    	JOptionPane.showMessageDialog(null, "L`insertion du mot de passe n`est pas bon");
						    }
						    
						    if (type.equals("")) {
						    	JOptionPane.showMessageDialog(null, "L`insertion du type n'est pas bon");
						    }
						   
							
							if (NOM_PATTERN.matcher(nom).matches()&&
									PRENOM_PATTERN.matcher(prenom).matches() &&
					                EMAIL_PATTERN.matcher(email).matches()&&
					                MOT_DE_PASSE_PATTERN.matcher(mot_de_passe).matches() && 
					                !type.equals("")) 
							{
								
						 
						 
						pst = con.prepareStatement("insert into login(name,surname,login,password,type)values(?,?,?,?,?)");
						pst.setString(1, nom);
						pst.setString(2, prenom);
						pst.setString(3, email);
						pst.setString(4, mot_de_passe);
						pst.setString(5, type);
						pst.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Utilisateur Ajouter");
							}	
							
						table_load();
					
						           
					txtnom.setText("");
					txtprenom.setText("");
					txtemail.setText("");
					txtmotdepasse.setText("");
					droptype.setSelectedItem("");
					txtnom.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
			        }
			}
		});
	}
}

