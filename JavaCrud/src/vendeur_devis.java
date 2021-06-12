import java.awt.EventQueue;
import java.sql.*;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;

/**
 * 
 * @author Khushveer
 *
 */

public class vendeur_devis {

	//INITIALISATION DES VARIABLES
	
	private JFrame frame;
	private JTable table;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vendeur_devis window = new vendeur_devis(login);
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
	public vendeur_devis(String login) {
		initialize(login);
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtnomclient;
	private JTextField txtmodele;
	private JTextField txtcouleur;
	private JTextField txtcarburant;
	private JTextField txttransmission;
	private JTextField txtoption;
	private JTextField txtpays;
	private JTextField txtmarque;
	private JTextField txtcapacite;
	private JTextField txtprix;
	private JTextField txtquantite;
	private JTextField txtstatue;
 
	
	//CONNEXION A LA BASE DE DONNEE
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
	 
	 //AFFICHAGE DES DONNEES SUR LA TABLE
	 
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
		
		JLabel lblmarque = new JLabel("Modele");
		lblmarque.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblmarque.setBounds(24, 75, 99, 33);
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
		
		txtnomclient = new JTextField();
		txtnomclient.setColumns(10);
		txtnomclient.setBounds(160, 19, 211, 22);
		panel.add(txtnomclient);
		
		JLabel lblNomclient = new JLabel("Nom client");
		lblNomclient.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomclient.setBounds(24, 11, 99, 33);
		panel.add(lblNomclient);
		
		txtmodele = new JTextField();
		txtmodele.setEditable(false);
		txtmodele.setColumns(10);
		txtmodele.setBounds(160, 83, 211, 22);
		panel.add(txtmodele);
		
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
		
		txtmarque = new JTextField();
		txtmarque.setEditable(false);
		txtmarque.setColumns(10);
		txtmarque.setBounds(160, 51, 211, 22);
		panel.add(txtmarque);
		
		JLabel lblMarque = new JLabel("Marque");
		lblMarque.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMarque.setBounds(24, 43, 99, 33);
		panel.add(lblMarque);
		
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
		txtprix.setBounds(160, 310, 211, 22);
		panel.add(txtprix);
		
		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrix.setBounds(24, 302, 99, 33);
		panel.add(lblPrix);
		
		txtquantite = new JTextField();
		txtquantite.setColumns(10);
		txtquantite.setBounds(160, 343, 211, 22);
		panel.add(txtquantite);
		
		JLabel lblQuantite = new JLabel("Quantite");
		lblQuantite.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuantite.setBounds(24, 335, 99, 33);
		panel.add(lblQuantite);
		
		txtstatue = new JTextField();
		txtstatue.setEditable(false);
		txtstatue.setColumns(10);
		txtstatue.setBounds(160, 375, 211, 22);
		panel.add(txtstatue);
		
		JLabel lblStatue = new JLabel("Statue");
		lblStatue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStatue.setBounds(24, 367, 99, 33);
		panel.add(lblStatue);
		
		//BOUTTON CREATION
		
		JButton btnNewButton = new JButton("Creation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{			
				String marque,modele,couleur,capacite,carburant,transmission,option_ref,pays,nom_client,quantite,prix,statue;
				marque = txtmarque.getText();
				modele = txtmodele.getText();
				capacite = txtcapacite.getText();
				couleur = txtcouleur.getText();
				carburant = txtcarburant.getText();
				transmission = txttransmission.getText();
				option_ref = txtoption.getText();
				pays = txtpays.getText();
				quantite = txtquantite.getText();
				prix = txtprix.getText();
				statue = txtstatue.getText();
				nom_client = txtnomclient.getText();
				
							//REGEX POUR LES INSERTIONS
				 try {
					 
					 
					final String MARQUE_REGEX = "^[a-zA-Z]+(([a-zA-Z ])?[a-zA-Z]*)*$";
					final Pattern MARQUE_PATTERN = Pattern.compile(MARQUE_REGEX);
					
					final String MODELE_REGEX = "[a-zA-Z][0-9]*$";
					final Pattern MODELE_PATTERN = Pattern.compile(MODELE_REGEX);
					
					final String CAPACITE_REGEX = "^[0-9]{4}$";
					final Pattern CAPACITE_PATTERN = Pattern.compile(CAPACITE_REGEX);
					
					final String COULEUR_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					final Pattern COULEUR_PATTERN = Pattern.compile(COULEUR_REGEX);
					
					final String CARBURANT_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					final Pattern CARBURANT_PATTERN = Pattern.compile(CARBURANT_REGEX);
					
					final String TRANSMISSION_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					final Pattern TRANSMISSION_PATTERN = Pattern.compile(TRANSMISSION_REGEX);
					
					final String OPTION_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					final Pattern OPTION_PATTERN = Pattern.compile(OPTION_REGEX);
					
					final String PAYS_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					final Pattern PAYS_PATTERN = Pattern.compile(PAYS_REGEX);
					
					final String QUANTITE_REGEX = "^[0-9]$";
					final Pattern QUANTITE_PATTERN = Pattern.compile(QUANTITE_REGEX);
					
					final String PRIX_REGEX = "[0-9]{3,}";
					final Pattern PRIX_PATTERN = Pattern.compile(PRIX_REGEX);
					
					final String STATUE_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					final Pattern STATUE_PATTERN = Pattern.compile(STATUE_REGEX);
					
					
					final String NOM_CLIENT_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					final Pattern NOM_CLIENT_PATTERN = Pattern.compile(NOM_CLIENT_REGEX);
					
					if (NOM_CLIENT_PATTERN.matcher(nom_client).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du nom n`est pas bon");
				    }
					
					if (MARQUE_PATTERN.matcher(marque).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du marque n`est pas bon");
				    }
					
					if (MODELE_PATTERN.matcher(modele).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du modele n`est pas bon");
				    }
					
					if (COULEUR_PATTERN.matcher(couleur).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du couleur n`est pas bon");
				    }
					
					if (CAPACITE_PATTERN.matcher(capacite).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du capacite n`est pas bon");
				    }
					
					if (CARBURANT_PATTERN.matcher(carburant).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du carburant n`est pas bon");
				    }
					
					if (TRANSMISSION_PATTERN.matcher(transmission).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du transmission n`est pas bon");
				    }
					
					if (OPTION_PATTERN.matcher(option_ref).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion de l'option n`est pas bon");
				    }
					
					if (PAYS_PATTERN.matcher(pays).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du pays n`est pas bon");
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
					
					int val1 = Integer.parseInt(txtquantite.getText());
					int val2 = Integer.parseInt(txtprix.getText());
					int total = val1 * val2;			
					
					//COMPARAISON POUR POUVOIR INSERER LES DONNEES
					
					if (NOM_CLIENT_PATTERN.matcher(nom_client).matches() && MARQUE_PATTERN.matcher(marque).matches() && MODELE_PATTERN.matcher(modele).matches() && COULEUR_PATTERN.matcher(couleur).matches()
							&& CAPACITE_PATTERN.matcher(capacite).matches() && CARBURANT_PATTERN.matcher(carburant).matches() && TRANSMISSION_PATTERN.matcher(transmission).matches() && OPTION_PATTERN.matcher(option_ref).matches()
							&& PAYS_PATTERN.matcher(pays).matches() && QUANTITE_PATTERN.matcher(quantite).matches() && PRIX_PATTERN.matcher(prix).matches()
							&& STATUE_PATTERN.matcher(statue).matches()) 
					{
					 
					//L'INSERTION DES DONNEES 
					pst = con.prepareStatement("insert into vendeur_devis(nom_client,marque,modele,capacite,transmission,carburant,couleur,pays,option_ref,prix,quantite,statue)values(?,?,?,?,?,?,?,?,?,?,?,?)");
					pst.setString(1, nom_client);
					pst.setString(2, marque);
					pst.setString(3, modele);
					pst.setString(4, capacite);
					pst.setString(5, transmission);
					pst.setString(6, carburant);
					pst.setString(7, couleur);
					pst.setString(8, pays);
					pst.setString(9, option_ref);
					pst.setString(10, prix);
					pst.setString(11, quantite);
					pst.setString(12, statue);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Ajout reussi !");
					
					nom_client = txtnomclient.getText();
					marque = txtmarque.getText();
					modele = txtmodele.getText();
					capacite = txtcapacite.getText();
					transmission = txttransmission.getText();
					carburant = txtcarburant.getText();
					couleur = txtcouleur.getText();
					pays = txtpays.getText();
					option_ref = txtoption.getText();
					prix = txtprix.getText();
					quantite = txtquantite.getText();
					statue = txtstatue.getText();
	
					// FICHIER PDF
					
					JFileChooser dialog = new JFileChooser();
					dialog.setSelectedFile(new File(nom_client+"_FicheDevis"+".pdf"));
					int dialogResult = dialog.showSaveDialog(null);
					if (dialogResult==JFileChooser.APPROVE_OPTION) {
						String filePath = dialog.getSelectedFile().getPath();
						
						try {
							
							Document myDoc = new Document();
							@SuppressWarnings("unused")
							PdfWriter  myWriter = PdfWriter.getInstance(myDoc, new FileOutputStream(filePath));
							myDoc.open();
							
							
							
							myDoc.add(new Paragraph("FICHE DEVIS", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
							
							myDoc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
							
							
							myDoc.add(new Paragraph("Detail du devis", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Nom de l'acheteur: "+nom_client+ " ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Marque: "+marque+ " ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Modele: "+modele+ " ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Couleur: "+couleur+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Carburant: "+carburant+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Transmission: "+transmission+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Option choisi: "+option_ref+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Pays: "+pays+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Quantite: "+quantite+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Prix: "+prix+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Statue: "+statue+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("devis: "+total+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));

							myDoc.close();
							JOptionPane.showMessageDialog(null, "PDF sauvegarder !");
							
							
							txtmarque.setText("");
							txtmodele.setText("");
							txtcapacite.setText("");
							txtcouleur.setText("");
							txtcarburant.setText("");
							txttransmission.setText("");
							txtoption.setText("");
							txtpays.setText("");
							txtquantite.setText("");
							txtprix.setText("");
							txtstatue.setText("");
							txtnomclient.setText("");
							txtmarque.requestFocus();
						
						}
							
						catch (FileNotFoundException | DocumentException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						catch(Exception er) {
							JOptionPane.showMessageDialog(null, "Error");
						}
					
						 
						}
					}
					table_load();
				
//					dropvoiture.setSelectedItem("");
//					dropcouleur.setSelectedItem("");
//					dropcarbu.setSelectedItem("");
//					droptrans.setSelectedItem("");
//					dropoption.setSelectedItem("");
//					droppays.setSelectedItem("");
//					txtdevis.setText("");
//					dropvoiture.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {			
					e1.printStackTrace();
					}
				
			}	 	
		});
		btnNewButton.setBounds(557, 623, 107, 50);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Sortir");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous quitter la page 'Devis'?", "Warning",dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
				vendeur_devis.this.frame.setVisible(false);
				login_connection.main(login);
				}
			}
		});
		btnExit.setBounds(744, 623, 107, 50);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Effacer");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtmarque.setText("");
				txtmodele.setText("");
				txtcapacite.setText("");
				txtcouleur.setText("");
				txtcarburant.setText("");
				txttransmission.setText("");
				txtoption.setText("");
				txtpays.setText("");
				txtquantite.setText("");
				txtprix.setText("");
				txtstatue.setText("");
				txtnomclient.setText("");
				txtmarque.requestFocus();
			}
		});
		btnClear.setBounds(932, 623, 107, 50);
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
			          //SELECTION DES DONNEES SUR LA PAGE VOITURE
			            String id = txtid.getText();

			                pst = con.prepareStatement("select * from voitures where id = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
	
			            	String marque = rs.getString(2);
			                String modele = rs.getString(3);
			                String capacite = rs.getString(4);
			                String transmission = rs.getString(5);
			                String carburant = rs.getString(6);
			                String couleur = rs.getString(7);
			                String pays = rs.getString(8);
			                String option_ref = rs.getString(9);
			                String prix = rs.getString(11);
			                String quantite = rs.getString(12);
			                String statue = rs.getString(13);
			                
			                txtmarque.setText(marque);
							txtmodele.setText(modele);
							txtcapacite.setText(capacite);
							txtcouleur.setText(couleur);
							txtcarburant.setText(carburant);
							txttransmission.setText(transmission);
							txtpays.setText(pays);
							txtoption.setText(option_ref);
							txtquantite.setText(quantite);
							txtprix.setText(prix);
							txtstatue.setText(statue);

			                
			                
			            }   
			            else
			            {
			            	txtmarque.setText("");
							txtmodele.setText("");
							txtcapacite.setText("");
							txtcouleur.setText("");
							txtcarburant.setText("");
							txttransmission.setText("");
							txtoption.setText("");
							txtpays.setText("");
							txtquantite.setText("");
							txtprix.setText("");
							txtstatue.setText("");
							txtnomclient.setText("");
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
				
				JLabel lblNewLabel = new JLabel("CREATION DEVIS");
				lblNewLabel.setBounds(579, -12, 401, 79);
				frame.getContentPane().add(lblNewLabel);
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
				
				JButton btnTable = new JButton("Table Devis");
				btnTable.addActionListener(new ActionListener() {
					@SuppressWarnings("static-access")
					public void actionPerformed(ActionEvent e) {
						table_devis td = new table_devis();
						td.table_devis();
					}
				});
				btnTable.setBounds(1099, 623, 113, 50);
				frame.getContentPane().add(btnTable);
				
				JLabel lblStockDesVoitures = new JLabel("STOCK DES VOITURES");
				lblStockDesVoitures.setFont(new Font("Tahoma", Font.PLAIN, 16));
				lblStockDesVoitures.setBounds(858, 59, 186, 27);
				frame.getContentPane().add(lblStockDesVoitures);
			}
}
