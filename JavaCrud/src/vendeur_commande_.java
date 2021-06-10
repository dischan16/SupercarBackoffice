import java.awt.EventQueue;
import java.sql.*;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * @author Khushveer
 *
 */

public class vendeur_commande_ {

	//INITIALISATION DES VARIABLES
	
	private JFrame frame;
	private JTable table;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	
	//POUR POUVOIR OUVRIR LA PAGE DANS L'INTERFACE vendeur_commande
	
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vendeur_commande_ window = new vendeur_commande_(login);
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
	public vendeur_commande_(String login) {
		initialize(login);
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtcouleur;
	private JTextField txtcarburant;
	private JTextField txttransmission;
	private JTextField txtoption;
	private JTextField txtpays;
	private JTextField txtcapacite;
	private JTextField txtprix;
	private JTextField txtquantite;
	private JTextField txtstatue;
	private JTextField txtlieu;
	private JTextField txtmarque;
	private JTextField txtmodele;
 
	//CONNEXION AVEC LA BASE DE DONNEE
	
	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","");
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          
	        }
	        catch (SQLException ex) 
	        {
	        	 
	        }
	    }
	 
	 //REQUETE SQL POUR L'AFFICHAGE DES DONNEES SUR LA TABLE
	 
	  public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select * from voitures");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	
	  
	  //DROPDOWN CONCATENATION DE id, prenom et nom
	  
	  @SuppressWarnings({ "rawtypes", "unchecked" })
		public JComboBox Test(JComboBox jc) throws SQLException {
		   
		    DefaultComboBoxModel theModel = (DefaultComboBoxModel) jc.getModel();
			PreparedStatement pst = con.prepareStatement("Select * from vendeur_client");
			ResultSet rs = pst.executeQuery();
			theModel.removeAllElements();
			theModel.addElement("");
			while (rs.next()) {
				theModel.addElement(rs.getString("id") + " - " + rs.getString("prenom")+ " " + rs.getString("nom"));
			}
			jc.setModel(theModel);
			return jc;
		}
	  
	  

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize(String login) {
		AdminAccount account = new AdminAccount();
		account.DatabaseConnexion(login, null, null, frame);
		System.out.print(account.getId());
		frame = new JFrame();
		frame.setBounds(100, 100, 1371, 731);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(37, 161, 430, 446);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblmarque = new JLabel("Marque");
		lblmarque.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblmarque.setBounds(24, 38, 99, 33);
		panel.add(lblmarque);
		
		JLabel Lblcouleur = new JLabel("Couleur");
		Lblcouleur.setFont(new Font("Tahoma", Font.BOLD, 14));
		Lblcouleur.setBounds(24, 204, 99, 33);
		panel.add(Lblcouleur);
		
		JLabel lblcarburant = new JLabel("Carburant");
		lblcarburant.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblcarburant.setBounds(24, 171, 99, 33);
		panel.add(lblcarburant);
		
		JLabel lbltransmission = new JLabel("Transmission");
		lbltransmission.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltransmission.setBounds(24, 139, 99, 33);
		panel.add(lbltransmission);
		
		JLabel lbloption_ref = new JLabel("Option");
		lbloption_ref.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbloption_ref.setBounds(24, 270, 99, 33);
		panel.add(lbloption_ref);
		
		JLabel lblpays = new JLabel("Pays");
		lblpays.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblpays.setBounds(24, 236, 99, 33);
		panel.add(lblpays);
		
		JLabel lblNomclient = new JLabel("Nom client");
		lblNomclient.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomclient.setBounds(24, 11, 99, 33);
		panel.add(lblNomclient);
		
		txtcouleur = new JTextField();
		txtcouleur.setEditable(false);
		txtcouleur.setColumns(10);
		txtcouleur.setBounds(160, 212, 211, 22);
		panel.add(txtcouleur);
		
		txtcarburant = new JTextField();
		txtcarburant.setEditable(false);
		txtcarburant.setColumns(10);
		txtcarburant.setBounds(160, 179, 211, 22);
		panel.add(txtcarburant);
		
		txttransmission = new JTextField();
		txttransmission.setEditable(false);
		txttransmission.setColumns(10);
		txttransmission.setBounds(160, 147, 211, 22);
		panel.add(txttransmission);
		
		txtoption = new JTextField();
		txtoption.setEditable(false);
		txtoption.setColumns(10);
		txtoption.setBounds(160, 278, 211, 22);
		panel.add(txtoption);
		
		txtpays = new JTextField();
		txtpays.setEditable(false);
		txtpays.setColumns(10);
		txtpays.setBounds(160, 244, 211, 22);
		panel.add(txtpays);
		
		JLabel lblCapacite = new JLabel("Capacite");
		lblCapacite.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCapacite.setBounds(24, 107, 99, 33);
		panel.add(lblCapacite);
		
		txtcapacite = new JTextField();
		txtcapacite.setEditable(false);
		txtcapacite.setColumns(10);
		txtcapacite.setBounds(160, 115, 211, 22);
		panel.add(txtcapacite);
		
		txtprix = new JTextField();
		txtprix.setEditable(false);
		txtprix.setColumns(10);
		txtprix.setBounds(160, 339, 211, 22);
		panel.add(txtprix);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrix.setBounds(24, 331, 99, 33);
		panel.add(lblPrix);
		
		txtquantite = new JTextField();
		txtquantite.setColumns(10);
		txtquantite.setBounds(160, 371, 211, 22);
		panel.add(txtquantite);
		
		JLabel lblQuantite = new JLabel("Quantite");
		lblQuantite.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuantite.setBounds(24, 363, 99, 33);
		panel.add(lblQuantite);

		txtstatue = new JTextField();
		txtstatue.setEditable(false);
		txtstatue.setColumns(10);
		txtstatue.setBounds(160, 403, 211, 22);
		panel.add(txtstatue);

		
		JLabel lblStatue = new JLabel("Statue");
		lblStatue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStatue.setBounds(24, 395, 99, 33);
		panel.add(lblStatue);
		
		
		txtlieu = new JTextField();
		txtlieu.setEditable(false);
		txtlieu.setColumns(10);
		txtlieu.setBounds(160, 310, 211, 22);
		panel.add(txtlieu);
		
		JLabel lblLieu = new JLabel("Lieu");
		lblLieu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLieu.setBounds(24, 299, 99, 33);
		panel.add(lblLieu);
		
		JLabel lblModele = new JLabel("Modele");
		lblModele.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblModele.setBounds(24, 64, 99, 33);
		panel.add(lblModele);
		
		txtmarque = new JTextField();
		txtmarque.setEditable(false);
		txtmarque.setColumns(10);
		txtmarque.setBounds(160, 47, 211, 22);
		panel.add(txtmarque);
		
		txtmodele = new JTextField();
		txtmodele.setEditable(false);
		txtmodele.setColumns(10);
		txtmodele.setBounds(160, 73, 211, 22);
		panel.add(txtmodele);
		
		//DROPDOWN POUR LE NOM
		
		@SuppressWarnings("rawtypes")
		JComboBox dropnom = new JComboBox();
		dropnom.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Test(dropnom);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	
		dropnom.setBounds(160, 17, 211, 22);
		panel.add(dropnom);
		
		
		JButton btnNewButton = new JButton("Creation");
		btnNewButton.addActionListener(new ActionListener() {
			private String Client;
			private String[] clientArray;
			private String idClientString;

			public void actionPerformed(ActionEvent e) 
			{			
				String id_voiture,quantite,prix,statue,id_emp;
				
				id_voiture = txtid.getText(); 
				quantite = txtquantite.getText();
				prix = txtprix.getText();
				statue = txtstatue.getText();
				if (statue.contains("En stock")) {
					statue = "En cours";
				}
				
				id_emp = account.getId();
				Client = dropnom.getSelectedItem().toString();
										
				 try {
					 
					 //REGEX POUR LES VALEURS ENTRER
					
					final String QUANTITE_REGEX = "^[0-9]$";
					final Pattern QUANTITE_PATTERN = Pattern.compile(QUANTITE_REGEX);
					
					final String PRIX_REGEX = "[0-9]{3,}";
					final Pattern PRIX_PATTERN = Pattern.compile(PRIX_REGEX);
					
					final String STATUE_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					final Pattern STATUE_PATTERN = Pattern.compile(STATUE_REGEX);
					
					//MESSAGE ERREUR
					
					if (dropnom.getSelectedItem().toString().contains("")) {
				    	JOptionPane.showMessageDialog(null, "Aucun client sélectionee !");
				    }
					
					if (txtid.getText().contains("")) {
				    	JOptionPane.showMessageDialog(null, "Aucune voiture selectionnee !");
				    }
				
					if (QUANTITE_PATTERN.matcher(quantite).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du quantite n`est pas bon");
				    }
					
					if (PRIX_PATTERN.matcher(prix).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du prix n`est pas bon");
				    }
					
					if (STATUE_PATTERN.matcher(statue).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du statue n`est pas bon");
				    }		
					
					//CONDITION POUR L'INSERTION DES DONNEES
					
					if (QUANTITE_PATTERN.matcher(quantite).matches() && PRIX_PATTERN.matcher(prix).matches()
						&& STATUE_PATTERN.matcher(statue).matches() && !dropnom.getSelectedItem().toString().contains("") && !txtid.getText().contains(""))
					{
					
						
					clientArray = Client.split("-");
					idClientString = clientArray[0];
				 
					//INSERTION DES DONNEES.
					pst = con.prepareStatement("insert into vendeur_commande(id_voiture,prix,quantite,id_emp,id_client,statue)values(?,?,?,?,?,?)");
					pst.setString(1, id_voiture);
					pst.setString(2, prix);
					pst.setString(3, quantite);
					pst.setString(4, id_emp);
					pst.setString(5, idClientString);
					pst.setString(6, statue);
					
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Ajout reussi !");
				
					id_voiture = txtid.getText(); 
					prix = txtprix.getText();
					quantite = txtquantite.getText();
					statue = txtstatue.getText();
					id_emp = account.getId();
	
					
					}
					table_load();
				
				   }
			 
				catch (SQLException e1) 
			        {			
					e1.printStackTrace();
					}
				
			}	 	
		});
		btnNewButton.setBounds(557, 623, 107, 50);
		frame.getContentPane().add(btnNewButton);
		
		//BOUTTON POUR SORTIR DE L'INTERFACE
		
		JButton btnExit = new JButton("Sortir");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous quitter la page 'Commande'?", "Warning",
						dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
				vendeur_commande_.this.frame.setVisible(false);
				login_connection.main(login);
				}
			}
		});
		btnExit.setBounds(916, 623, 107, 50);
		frame.getContentPane().add(btnExit);
		
		//EFFACER LES DONNEES INSERER
		
		JButton btnClear = new JButton("Effacer");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				txtcapacite.setText("");
				txttransmission.setText("");
				txtcarburant.setText("");
				txtcouleur.setText("");
				txtpays.setText("");
				txtoption.setText("");
				txtlieu.setText("");
				txtprix.setText("");
				txtquantite.setText("");
				txtstatue.setText("");
				dropnom.setSelectedItem("");

			}
		});
		btnClear.setBounds(742, 623, 107, 50);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 96, 847, 511);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(37, 59, 430, 110);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBookId = new JLabel("ID");
		lblBookId.setBounds(30, 57, 60, 17);
		lblBookId.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblBookId);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 try {
			          //SELECTION DES IDS POUR REMPLIR LES CHAMPS
			            String id = txtid.getText();

			                pst = con.prepareStatement("select marque,modele,capacite,transmission,carburant,couleur,pays,option_ref,lieu,prix,quantite,statue from voitures where id = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
	
			            	String marque = rs.getString(1);
			            	String modele = rs.getString(2);
			                String capacite = rs.getString(3);
			                String transmission = rs.getString(4);
			                String carburant = rs.getString(5);
			                String couleur = rs.getString(6);
			                String pays = rs.getString(7);
			                String option_ref = rs.getString(8);
			                String lieu = rs.getString(9);
			                String prix = rs.getString(10);
			                String quantite = rs.getString(11);
			                String statue = rs.getString(12);
			                
			                txtmarque.setText(marque);
			                txtmodele.setText(modele);
							txtcapacite.setText(capacite);
							txttransmission.setText(transmission);
							txtcarburant.setText(carburant);
							txtcouleur.setText(couleur);
							txtpays.setText(pays);
							txtoption.setText(option_ref);
							txtlieu.setText(lieu);
							txtprix.setText(prix);
							txtquantite.setText(quantite);
							txtstatue.setText(statue);

			                
			                
			            }   
			            else
			            {
			            	txtmarque.setText("");
			            	txtmodele.setText("");
							txtcapacite.setText("");
							txttransmission.setText("");
							txtcarburant.setText("");
							txtcouleur.setText("");
							txtpays.setText("");
							txtoption.setText("");
							txtlieu.setText("");
							txtprix.setText("");
							txtquantite.setText("");
							txtstatue.setText("");
							dropnom.setSelectedItem("");

			            }

			        } 
				
				 catch (SQLException ex) {
			           
			        }
			}
		});
		txtid.setBounds(106, 57, 278, 22);
		txtid.setColumns(10);
		panel_1.add(txtid);
		
		JLabel lblSelectionnerLidentifiantDe = new JLabel("SELECTIONNER L'IDENTIFIANT DE LA VOITURE VOULU");
		lblSelectionnerLidentifiantDe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSelectionnerLidentifiantDe.setBounds(30, 20, 354, 27);
		panel_1.add(lblSelectionnerLidentifiantDe);
				
				JLabel lblNewLabel = new JLabel("CREATION COMMANDE");
				lblNewLabel.setBounds(579, -12, 401, 79);
				frame.getContentPane().add(lblNewLabel);
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
				
				JButton btnTable = new JButton("Table Commande");
				btnTable.addActionListener(new ActionListener() {
					@SuppressWarnings("static-access")
					public void actionPerformed(ActionEvent e) {
						table_commande td = new table_commande(login);
						td.table_commande(login);
					}
				});
				btnTable.setBounds(1207, 623, 140, 50);
				frame.getContentPane().add(btnTable);
				
				JLabel lblStockDesVoitures = new JLabel("STOCK DES VOITURES");
				lblStockDesVoitures.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblStockDesVoitures.setBounds(858, 59, 186, 27);
				frame.getContentPane().add(lblStockDesVoitures);
			}
}

