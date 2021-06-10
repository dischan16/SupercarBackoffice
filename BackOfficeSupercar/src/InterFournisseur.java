import java.awt.EventQueue;
import java.awt.Window;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.crypto.SecretKey;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 
 * @author Haashim Potyram
 *
 */

public class InterFournisseur {
	
	private AdminAccount account = new AdminAccount();
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String login){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterFournisseur window = new InterFournisseur(login);
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
	public InterFournisseur(String login) {
		Connect();
		initialize(login);
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	private JTextField txtNom;
	private JTextField txtAdresse;
	private JTextField txtEmail;
	private JTextField txtPortable;
	private JTextField txtType;
	private JTextField txtQuantite;
	private JTextField txtQuotation;
	private JTextField txtDevise;
	private JTextField txtPaiement;
	private JTextField txtid;
	private JTable table;
	private JTextField txtPays;
	private JTextField txtEntrepot;
	
	
	
	public void Connect() {
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","");
        }
        catch (ClassNotFoundException ex) 
        {
          ex.printStackTrace();
        }
        catch (SQLException ex) 
        {
        	   ex.printStackTrace();
        }
		
	}
	
	/**
	 * 
	 * @param paiement
	 * @return
	 */
	
	public String Decrypt_Paiement(String paiement) {
		if (account.getAccountType().contains("Comptabilite")) {
		SecretKey key;
		try {
			key = ApiBlowfish.decryptKey();
			paiement = ApiBlowfish.decryptInString(paiement, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		return paiement;
	}
	
	/**
	 * 
	 * @param paiement
	 * @return
	 */
	
	public String Encrpyt_Paiement(String paiement) {
		SecretKey key;
		try {
			key = ApiBlowfish.decryptKey();
			paiement = ApiBlowfish.encryptInString(paiement, key);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return paiement;
	}
	/**
	 * 
	 */
	public void table_load()

    {

             try

             {

                 pst = con.prepareStatement("select * from comptable_fournisseur");
                 rs = pst.executeQuery();
                 
                 DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {},
     					new String[] { "ID","Nom", "Adresse", "Email", "Portable", "Type", "Pays", "Entrepot",
     							"Quantite", "Quotation", "Devise", "Paiement"});
     			


     				while (rs.next()) {
     				String id = rs.getString("id");
     				String nom = rs.getString("nom");
     				String adresse = rs.getString("adresse");
     				String email = rs.getString("email");
     				String portable = rs.getString("portable");
     				String type = rs.getString("type");
     				String pays = rs.getString("pays");
     				String entrepot = rs.getString("entrepot");
     				String quantite = rs.getString("quantite");
     				String quotation = Decrypt_Paiement(rs.getString("quotation"));
     				String devise = rs.getString("devise");
     				String paiement = Decrypt_Paiement(rs.getString("paiement"));

     				String[] data = { id, nom, adresse, email, portable, type, pays, entrepot,
     						quantite, quotation, devise, paiement };
     				tableModel.addRow(data);


             }
     				table.setModel(tableModel);
     				
     				table.getColumnModel().getColumn(0).setPreferredWidth(75); // ID
     				table.getColumnModel().getColumn(1).setPreferredWidth(200); //Nom
     				table.getColumnModel().getColumn(2).setPreferredWidth(200);  // Adresse
     				table.getColumnModel().getColumn(3).setPreferredWidth(280); // Email
     				table.getColumnModel().getColumn(4).setPreferredWidth(200); // Portable
     				table.getColumnModel().getColumn(5).setPreferredWidth(100); // Type
     				table.getColumnModel().getColumn(6).setPreferredWidth(200); // Pays
     				table.getColumnModel().getColumn(7).setPreferredWidth(290); // Entrepot
     				table.getColumnModel().getColumn(8).setPreferredWidth(120); // Quantite
     				table.getColumnModel().getColumn(9).setPreferredWidth(150); // Quotation
     				table.getColumnModel().getColumn(10).setPreferredWidth(100); // Devise
     				table.getColumnModel().getColumn(11).setPreferredWidth(200); // Paiement
     				
     				table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

             }catch (SQLException e)

              {

                           e.printStackTrace();

               }

    }
	
	 public static String testEmail(String emailText) {
		 final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*" +
		            "@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
			final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
			
			if (EMAIL_PATTERN.matcher(emailText).matches() == false) {
				JOptionPane.showMessageDialog(null, "L`insertion du email n`est pas bon");
			}

		 return emailText;
	    }
	 
	 public static String testNom(String nomText) {
		 return nomText;
	 }
	 
	 public static String testPaiement(String paiementText) {
		 
		 final String PAIEMENT_REGEX = "^[0-9]+([',. -])*$";
		    
		    final Pattern PAIEMENT_PATTERN = Pattern.compile(PAIEMENT_REGEX);
		    
		    if( PAIEMENT_PATTERN.matcher(paiementText).matches()  == false) {
		    	JOptionPane.showMessageDialog(null, "LE PRIX n`est pas bon");
		    }
		    return paiementText;
		    
	 }
	 
	 public static String testAdresse(String adresseText) {
		 return adresseText;
	 }
	 
	 

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String login) {
		account.DatabaseConnexion(login, null, null, frame);
		System.out.print(account.getId());
		frame = new JFrame();
		frame.setBounds(100, 100, 1300, 900);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFournisseur = new JLabel("Fournisseur");
		lblFournisseur.setForeground(Color.RED);
		lblFournisseur.setHorizontalAlignment(SwingConstants.CENTER);
		lblFournisseur.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblFournisseur.setBounds(464, 10, 236, 54);
		frame.getContentPane().add(lblFournisseur);
		
		JPanel panel_Form = new JPanel();
		panel_Form.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Form.setBounds(10, 70, 379, 569);
		frame.getContentPane().add(panel_Form);
		panel_Form.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNom.setBounds(10, 25, 61, 32);
		panel_Form.add(lblNom);
		
		txtNom = new JTextField();
		txtNom.setBounds(96, 28, 154, 32);
		panel_Form.add(txtNom);
		txtNom.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse :");
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAdresse.setBounds(10, 77, 76, 32);
		panel_Form.add(lblAdresse);
		
		txtAdresse = new JTextField();
		txtAdresse.setBounds(96, 80, 184, 32);
		panel_Form.add(txtAdresse);
		txtAdresse.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEmail.setBounds(10, 137, 69, 32);
		panel_Form.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(96, 140, 184, 32);
		panel_Form.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPortable = new JLabel("Portable :");
		lblPortable.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPortable.setBounds(10, 186, 76, 33);
		panel_Form.add(lblPortable);
		
		txtPortable = new JTextField();
		txtPortable.setBounds(96, 189, 184, 32);
		panel_Form.add(txtPortable);
		txtPortable.setColumns(10);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblType.setBounds(10, 242, 61, 32);
		panel_Form.add(lblType);
		
		txtType = new JTextField();
		txtType.setBounds(96, 245, 184, 32);
		panel_Form.add(txtType);
		txtType.setColumns(10);
		
		JLabel lblQuantite = new JLabel("Quantite :");
		lblQuantite.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQuantite.setBounds(10, 401, 90, 27);
		panel_Form.add(lblQuantite);
		
		txtQuantite = new JTextField();
		txtQuantite.setBounds(126, 401, 154, 32);
		panel_Form.add(txtQuantite);
		txtQuantite.setColumns(10);
		
		JLabel lblQuotation = new JLabel("Quotation :");
		lblQuotation.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQuotation.setForeground(new Color(0, 0, 0));
		lblQuotation.setBounds(10, 453, 90, 27);
		panel_Form.add(lblQuotation);
		
		txtQuotation = new JTextField();
		txtQuotation.setBounds(126, 443, 154, 32);
		panel_Form.add(txtQuotation);
		txtQuotation.setColumns(10);
		
		JLabel lblTauxdevente = new JLabel("Devise :");
		lblTauxdevente.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTauxdevente.setBounds(10, 490, 131, 27);
		panel_Form.add(lblTauxdevente);
		
		txtDevise = new JTextField();
		txtDevise.setBounds(151, 485, 161, 32);
		panel_Form.add(txtDevise);
		txtDevise.setColumns(10);
		
		JLabel lblPaiement = new JLabel("Paiement :");
		lblPaiement.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPaiement.setBounds(10, 527, 105, 32);
		panel_Form.add(lblPaiement);
		
		txtPaiement = new JTextField();
		txtPaiement.setBounds(126, 527, 154, 32);
		panel_Form.add(txtPaiement);
		txtPaiement.setColumns(10);
		
		JLabel lblPays = new JLabel("Pays :");
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPays.setBounds(10, 295, 61, 27);
		panel_Form.add(lblPays);
		
		txtPays = new JTextField();
		txtPays.setBounds(96, 295, 154, 32);
		panel_Form.add(txtPays);
		txtPays.setColumns(10);
		
		JLabel lblEntrepot = new JLabel("Entrepot :");
		lblEntrepot.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEntrepot.setBounds(10, 347, 90, 27);
		panel_Form.add(lblEntrepot);
		
		txtEntrepot = new JTextField();
		txtEntrepot.setBounds(137, 348, 131, 32);
		panel_Form.add(txtEntrepot);
		txtEntrepot.setColumns(10);
		
		JButton btnCalculer = new JButton("Calculer");
		btnCalculer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                    int val1,result;
                    double val2;
				
				try {
					
					val1 = Integer.parseInt(txtQuotation.getText());
					val2 = Double.parseDouble(txtDevise.getText());
					
					result = (int) (val1 * val2);
					
					txtPaiement.setText(Double.toString(result));					
					
				}catch (Exception error) {
					JOptionPane.showMessageDialog(null,"Entrer une valeur numerique valide");
				}
			}
		});
		btnCalculer.setFont(new Font("Arial", Font.BOLD, 20));
		btnCalculer.setBounds(20, 649, 119, 33);
		frame.getContentPane().add(btnCalculer);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom = txtNom.getText();
				String adresse = txtAdresse.getText();
				String email = txtEmail.getText();
				String portable = txtPortable.getText();
				String type = txtType.getText();
				String pays = txtPays.getText();
				String entrepot = txtEntrepot.getText();
				String quantite = txtQuantite.getText();
				String quotation = Encrpyt_Paiement(txtQuotation.getText());
				String devise = txtDevise.getText();
				String paiement = Encrpyt_Paiement(txtPaiement.getText());
				 
				try {
					
					final String NOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					 
				    final Pattern NOM_PATTERN = Pattern.compile(NOM_REGEX);
				    
				    final String ADRESSE_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z][0-9]*)*$";
				 
				    final Pattern ADRESSE_PATTERN = Pattern.compile(ADRESSE_REGEX);
				    
				    final String EMAIL_REGEX =
				            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*" +
				            "@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
				 
				    final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
				    
                    final String PORTABLE_REGEX = "^[0-9*]{8}$";
				    
				    final Pattern PORTABLE_PATTERN = Pattern.compile(PORTABLE_REGEX);
				    
				    final String TYPE_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
				 
				    final Pattern TYPE_PATTERN = Pattern.compile(TYPE_REGEX);
				    
				    final String PAYS_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					 
				    final Pattern PAYS_PATTERN = Pattern.compile(PAYS_REGEX);
				    
                    final String ENTREPOT_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
				    
				    final Pattern ENTREPOT_PATTERN = Pattern.compile(ENTREPOT_REGEX);
				    
				    final String QUANTITE_REGEX = "^[0-9]*$";
				    
				    final Pattern QUANTITE_PATTERN = Pattern.compile(QUANTITE_REGEX);
				    
                     final String QUOTATION_REGEX = "^[0-9]*$";
				    
				    final Pattern QUOTATION_PATTERN = Pattern.compile(QUOTATION_REGEX);
				    
                    final String TAUX_REGEX = "^[0-9]+([',. -])+([0-9])*$";
				    
				    final Pattern TAUX_PATTERN = Pattern.compile(TAUX_REGEX);
				    
                    /*final String PAIEMENT_REGEX = "^[0-9]+([',. -])+([0-9])*$";
				    
				    final Pattern PAIEMENT_PATTERN = Pattern.compile(PAIEMENT_REGEX);*/
				    
				    if (NOM_PATTERN.matcher(nom).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du nom n'est pas bon,re-inserez le nom du s'il vous plait");
				    }
				    
				    if(ADRESSE_PATTERN.matcher(adresse).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format de l'adresse n'est pas bon,re-inserez l'adresse du fournisseur s'il vous plait");
				    }
				    
				    if( EMAIL_PATTERN.matcher(email).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format de l'email n'est pas bon,re-inserez l'email du fournisseur s'il vous plait");
				    }
				    
				    if( PORTABLE_PATTERN.matcher(portable).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du portable n'est pas bon,re-inserez le numero portable s'il vous plait");
				    }
				    
				    if( TYPE_PATTERN.matcher(type).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du type de vente n'est pas bon,re-inserez le type du vente s'il vous plait");
				    }
				    
				    if( PAYS_PATTERN.matcher(pays).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du pays n'est pas bon,re-inserez le pays s'il vous plait");
				    }
				    
				    if( ENTREPOT_PATTERN.matcher(entrepot).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format de l'entrepot n'est pas bon,re-inserez le nom s'il vous plait");
				    }
				    
				    if( QUANTITE_PATTERN.matcher(quantite).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du quantite n'est pas bon,re-inserez le quantite du voiture achetés s'il vous plait");
				    }
				    
				    if( QUOTATION_PATTERN.matcher(Decrypt_Paiement(quotation)).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du quotation du fournisseur n'est pas bon,re-inserez le quotation du fournisseur s'il vous plait");
				    }
				    
				    if( TAUX_PATTERN.matcher(devise).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du devise n'est pas bon,re-inserez le devise des achats s'il vous plait");
				    }
				    
				    /*if( PAIEMENT_PATTERN.matcher(Decrypt_Paiement(paiement)).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du paiement n'est pas bon,re-calculer le paiement du fournisseur s'il vous plait");
				    }*/
				    
				    if (NOM_PATTERN.matcher(nom).matches()&&
				              ADRESSE_PATTERN.matcher(adresse).matches() &&
				                EMAIL_PATTERN.matcher(email).matches() &&
				                PORTABLE_PATTERN.matcher(portable).matches()&&
				                TYPE_PATTERN.matcher(type).matches()&&
				                PAYS_PATTERN.matcher(pays).matches()&&
				                ENTREPOT_PATTERN.matcher(entrepot).matches()&&
				                QUANTITE_PATTERN.matcher(quantite).matches()&&
				                QUOTATION_PATTERN.matcher(Decrypt_Paiement(quotation)).matches()&&
				                TAUX_PATTERN.matcher(devise).matches()&&
				                account.getAccountType().contains("Comptabilite")) {
					
					pst = con.prepareStatement("insert into comptable_fournisseur(nom,adresse,email,portable,type,pays,entrepot,quantite,quotation,devise,paiement)values(?,?,?,?,?,?,?,?,?,?,?)");
					pst.setString(1, nom);
					pst.setString(2, adresse);
					pst.setString(3, email);
					pst.setString(4, portable);
					pst.setString(5, type);
					pst.setString(6, pays);
					pst.setString(7, entrepot);
					pst.setString(8, quantite);
					pst.setString(9, quotation);
					pst.setString(10, devise);
					pst.setString(11, paiement);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					
				    }
					
					table_load();
						           
					txtNom.setText("");
					txtAdresse.setText("");
					txtEmail.setText("");
					txtPortable.setText("");
					txtType.setText("");
					txtPays.setText("");
					txtEntrepot.setText("");
					txtQuantite.setText("");
					txtQuotation.setText("");
					txtDevise.setText("");
					txtPaiement.setText("");
					txtNom.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
			}
		});
		btnSave.setFont(new Font("Arial", Font.BOLD, 20));
		btnSave.setBounds(170, 649, 101, 33);
		frame.getContentPane().add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom,adresse,email,portable,type,pays,entrepot,quantite,quotation,devise,paiement,id;
				
				 nom = txtNom.getText();
				 adresse = txtAdresse.getText();
				 email = txtEmail.getText();
				 portable = txtPortable.getText();
				 type = txtType.getText();
				 pays = txtPays.getText();
				 entrepot = txtEntrepot.getText();
				 quantite = txtQuantite.getText();
				 quotation = Encrpyt_Paiement(txtQuotation.getText());
				 devise = txtDevise.getText();
				 paiement = Encrpyt_Paiement(txtPaiement.getText());
				 id = txtid.getText();
				
				try {
					
					final String NOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					 
				    final Pattern NOM_PATTERN = Pattern.compile(NOM_REGEX);
				    
				    final String ADRESSE_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z][0-9]*)*$";
				 
				    final Pattern ADRESSE_PATTERN = Pattern.compile(ADRESSE_REGEX);
				    
				    final String EMAIL_REGEX =
				            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*" +
				            "@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
				 
				    final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
				    
                    final String PORTABLE_REGEX = "^[0-9*]{8}$";
				    
				    final Pattern PORTABLE_PATTERN = Pattern.compile(PORTABLE_REGEX);
				    
				    final String TYPE_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
				 
				    final Pattern TYPE_PATTERN = Pattern.compile(TYPE_REGEX);
				    
				    final String PAYS_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					 
				    final Pattern PAYS_PATTERN = Pattern.compile(PAYS_REGEX);
				    
                    final String ENTREPOT_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
				    
				    final Pattern ENTREPOT_PATTERN = Pattern.compile(ENTREPOT_REGEX);
				    
				    final String QUANTITE_REGEX = "^[0-9]*$";
				    
				    final Pattern QUANTITE_PATTERN = Pattern.compile(QUANTITE_REGEX);
				    
                     final String QUOTATION_REGEX = "^[0-9]+([',. -])*$";
				    
				    final Pattern QUOTATION_PATTERN = Pattern.compile(QUOTATION_REGEX);
				    
                    /*final String TAUX_REGEX = "^[0-9]+([',. -])*$";
				    
				    final Pattern TAUX_PATTERN = Pattern.compile(TAUX_REGEX);*/
				    
                    /*final String PAIEMENT_REGEX = "^[0-9]+([',. -])*$";
				    
				    final Pattern PAIEMENT_PATTERN = Pattern.compile(PAIEMENT_REGEX);*/
				    
				    if (NOM_PATTERN.matcher(nom).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du nom n'est pas bon,re-inserez le nom du s'il vous plait");
				    }
				    
				    if(ADRESSE_PATTERN.matcher(adresse).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format de l'adresse n'est pas bon,re-inserez l'adresse du fournisseur s'il vous plait");
				    }
				    
				    if( EMAIL_PATTERN.matcher(email).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format de l'email n'est pas bon,re-inserez l'email du fournisseur s'il vous plait");
				    }
				    
				    if( PORTABLE_PATTERN.matcher(portable).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du portable n'est pas bon,re-inserez le numero portable s'il vous plait");
				    }
				    
				    if( TYPE_PATTERN.matcher(type).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du type de vente n'est pas bon,re-inserez le type du vente s'il vous plait");
				    }
				    
				    if( PAYS_PATTERN.matcher(pays).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du pays n'est pas bon,re-inserez le pays s'il vous plait");
				    }
				    
				    if( ENTREPOT_PATTERN.matcher(entrepot).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format de l'entrepot n'est pas bon,re-inserez le nom s'il vous plait");
				    }
				    
				    if( QUANTITE_PATTERN.matcher(quantite).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du quantite n'est pas bon,re-inserez le quantite du voiture achetés s'il vous plait");
				    }
				    
				    if( QUOTATION_PATTERN.matcher(Decrypt_Paiement(quotation)).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du quotation du fournisseur n'est pas bon,re-inserez le quotation du fournisseur s'il vous plait");
				    }
				    
				    /*if( TAUX_PATTERN.matcher(devise).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du devise n'est pas bon,re-inserez le devise des achats s'il vous plait");
				    }*/
				    
				    /*if( PAIEMENT_PATTERN.matcher(Decrypt_Paiement(paiement)).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du paiement n'est pas bon,re-calculer le paiement du fournisseur s'il vous plait");
				    }*/
				    
				    if (NOM_PATTERN.matcher(nom).matches()&&
				              ADRESSE_PATTERN.matcher(adresse).matches() &&
				                EMAIL_PATTERN.matcher(email).matches() &&
				                PORTABLE_PATTERN.matcher(portable).matches()&&
				                TYPE_PATTERN.matcher(type).matches()&&
				                PAYS_PATTERN.matcher(pays).matches()&&
				                ENTREPOT_PATTERN.matcher(entrepot).matches()&&
				                QUANTITE_PATTERN.matcher(quantite).matches()&&
				                QUOTATION_PATTERN.matcher(Decrypt_Paiement(quotation)).matches()) {
					
				pst = con.prepareStatement("update comptable_fournisseur set nom = ?,adresse = ?,email = ?,portable = ?,type = ?,pays = ?,entrepot = ?,quantite = ?,quotation = ?,devise = ?,paiement = ? where id = ?");
				
				pst.setString(1, nom);
				pst.setString(2, adresse);
				pst.setString(3, email);
				pst.setString(4, portable);
				pst.setString(5, type);
				pst.setString(6, pays);
				pst.setString(7, entrepot);
				pst.setString(8, quantite);
				pst.setString(9, quotation);
				pst.setString(10, devise);
				pst.setString(11, paiement);
				pst.setString(12, id);
				pst.executeUpdate();
				
				  JOptionPane.showMessageDialog(null, "Record Updated!");
				    }

                  table_load();

                
                  txtNom.setText("");
                  txtAdresse.setText("");
                  txtEmail.setText("");
                  txtPortable.setText("");
                  txtType.setText("");
                  txtPays.setText("");
                  txtEntrepot.setText("");
                  txtQuantite.setText("");
                  txtQuotation.setText("");
                  txtDevise.setText("");
                  txtPaiement.setText("");
                  txtNom.requestFocus();
					
				}catch (SQLException e1) {

                    e1.printStackTrace();

      }
			}
		});
		btnEdit.setFont(new Font("Arial", Font.BOLD, 20));
		btnEdit.setBounds(304, 649, 85, 33);
		frame.getContentPane().add(btnEdit);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 708, 379, 82);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Arial", Font.BOLD, 20));
		lblId.setBounds(10, 29, 50, 31);
		panel.add(lblId);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
					 String id = txtid.getText();
					
					pst = con.prepareStatement("select nom,adresse,email,portable,type,pays,entrepot,quantite,quotation,devise,paiement from comptable_fournisseur where id=?");
					pst.setString(1, id);
                    ResultSet rs = pst.executeQuery();
					
					//////////table.setModel(DbUtils.resultSetToTableModel(rs));//////////////
					
					if(rs.next()==true)
					{
						String nom = rs.getString(1);
						String adresse = rs.getString(2);
						String email = rs.getString(3);
						String portable = rs.getString(4);
						String type = rs.getString(5);
						String pays = rs.getString(6);
						String entrepot = rs.getString(7);
						String quantite = rs.getString(8);
						String quotation = Decrypt_Paiement(rs.getString(9));
						String devise = rs.getString(10);
						String paiement = Decrypt_Paiement(rs.getString(11));
						
						     txtNom.setText(nom);
		                     txtAdresse.setText(adresse);
		                     txtEmail.setText(email);
		                     txtPortable.setText(portable);
		                     txtType.setText(type);
		                     txtPays.setText(pays);
		                     txtEntrepot.setText(entrepot);
		                     txtQuantite.setText(quantite);
		                     txtQuotation.setText(quotation);
		                     txtDevise.setText(devise);
		                     txtPaiement.setText(paiement);
					}
					
					else {
						
						 txtNom.setText("");
	                     txtAdresse.setText("");
	                     txtEmail.setText("");
	                     txtPortable.setText("");
	                     txtType.setText("");
	                     txtPays.setText("");
	                     txtEntrepot.setText("");
	                     txtQuantite.setText("");
	                     txtQuotation.setText("");
	                     txtDevise.setText("");
	                     txtPaiement.setText("");
						
					}
					
					
				} catch (SQLException ex) {
					
					
				}
			}
		});
		
		txtid.setBounds(80, 26, 105, 31);
		panel.add(txtid);
		txtid.setColumns(10);
		
		JButton lblDelete = new JButton("Delete");
		lblDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = txtid.getText();
				String nom = txtNom.getText();
				String adresse = txtAdresse.getText();
				String email = txtEmail.getText();
				String portable = txtPortable.getText();
				String type = txtType.getText();
				String pays = txtPays.getText();
				String entrepot = txtEntrepot.getText();
				String quantite = txtQuantite.getText();
				String quotation = txtQuotation.getText();
				String devise = txtDevise.getText();
				String paiement = txtPaiement.getText();
				
				try {
					
					pst = con.prepareStatement("delete from comptable_fournisseur where id =?");
					
					 pst.setString(1, id);

                     pst.executeUpdate();

                     JOptionPane.showMessageDialog(null, "Record Deleted!");

                     table_load();
                     
                     txtNom.setText("");
                     txtAdresse.setText("");
                     txtEmail.setText("");
                     txtPortable.setText("");
                     txtType.setText("");
                     txtPays.setText("");
                     txtEntrepot.setText("");
                     txtQuantite.setText("");
                     txtQuotation.setText("");
                     txtDevise.setText("");
                     txtPaiement.setText("");
                     txtNom.requestFocus();
				}
				catch (SQLException e1) {                   

                    e1.printStackTrace();

       }
				
			}
		});
		lblDelete.setFont(new Font("Arial", Font.BOLD, 20));
		lblDelete.setBounds(530, 647, 101, 33);
		frame.getContentPane().add(lblDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 txtNom.setText("");
                 txtAdresse.setText("");
                 txtEmail.setText("");
                 txtPortable.setText("");
                 txtType.setText("");
                 txtPays.setText("");
                 txtEntrepot.setText("");
                 txtQuantite.setText("");
                 txtQuotation.setText("");
                 txtDevise.setText("");
                 txtPaiement.setText("");
                 txtNom.requestFocus();
			}
		});
		btnClear.setFont(new Font("Arial", Font.BOLD, 20));
		btnClear.setBounds(704, 647, 101, 33);
		frame.getContentPane().add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous quitter la page 'Fournisseur'?",
						"Warning", dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					InterFournisseur.this.frame.setVisible(false);
					login_connection.main(login);
				}
				
			}
		});
		btnExit.setFont(new Font("Arial", Font.BOLD, 20));
		btnExit.setBounds(876, 647, 101, 33);
		frame.getContentPane().add(btnExit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(415, 72, 803, 540);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		
	}
}
