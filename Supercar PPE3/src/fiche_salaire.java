import java.awt.EventQueue;
import java.sql.*;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JComboBox;


/**
 * 
 * @author disch
 *
 */


public class fiche_salaire {

	private JFrame frame;
	private JTextField txtnom;
	private JTextField txtprenom;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fiche_salaire window = new fiche_salaire(login);
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
	public fiche_salaire(String login) {
		initialize(login);
		Connect();
		table_load();
	}

	Connection con;
	PreparedStatement pst;

	ResultSet rs;
	private JTextField txtemail;
	private JTextField txtcompte_bancaire;
	private JTextField txtnumero_id;
	private JTextField txtsalaire;
	private JTextField textField;
	private JTextField txtcommission;
	private String date = "";

	/**
	 *  Connection avec la base de donnees
	 */
	
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/backoffice", "root", "");
		} catch (ClassNotFoundException ex) {

		} catch (SQLException ex) {

		}
	}
	
	/**
	 *  methode Decrypt_Banque pour la partie salaire et numero compte
	 * @param banque
	 * @return
	 */

	public String Decrypt_Banque(String banque) {
		SecretKey key;
		try {
			key = ApiBlowfish.decryptKey();
			banque = ApiBlowfish.decryptInString(banque, key);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return banque;
	}

	/**
	 *  methode Encrypt_Banque pour la partie salaire et numero compte
	 * @param banque
	 * @return
	 */
	
	public String Encrpyt_Banque(String banque) {
		SecretKey key;
		try {
			key = ApiBlowfish.decryptKey();
			banque = ApiBlowfish.encryptInString(banque, key);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return banque;
	}
	
	/**
	 *  Affichage de la table employee pour effectuer les salaires des employees
	 */

	public void table_load() {
		try {
			pst = con.prepareStatement(
					"select id_emp,nom,prenom,email,numero_id,compte_bancaire,salaire,poste,departement from rh_employee");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			pst = con.prepareStatement(
					"select  id_emp,nom,prenom,email,numero_id,compte_bancaire,salaire,poste,departement,CURDATE() as date from rh_employee");
			rs = pst.executeQuery();
			
			DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {},
					new String[] { "ID", "Nom", "Prenom", "Email", "Compte Bancaire", "Numero ID", "Salaire", "Poste",
							"Departement", "Commission" });
			
			while (rs.next()) {
				String id_emp = rs.getString("id_emp");

				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String compte_bancaire = Decrypt_Banque(rs.getString("compte_bancaire"));
				String numero_id = rs.getString("numero_id");
				String salaire = Decrypt_Banque(rs.getString("salaire"));
				String poste = rs.getString("poste");
				String departement = rs.getString("departement");
				String commission = "";
				date = rs.getString("date");
				if (departement.contains("Vente")) {
					String test = "SELECT SUM(b.quantite) * 3500 as 'test' FROM rh_employee AS a, vendeur_commande AS b WHERE a.id_emp = b.id_emp  AND statue = 'Termine'  AND a.departement = 'Vente' AND a.id_emp = ? AND MONTH(date) = MONTH(CURRENT_DATE()) AND YEAR(date) = YEAR(CURRENT_DATE())";
					pst = con.prepareStatement(test);
					pst.setString(1, id_emp);
					ResultSet rsVente = pst.executeQuery();
					while (rsVente.next()) {
						commission = String.valueOf(rsVente.getInt(1));
					}
				} else {
					commission = "0";
				}

				String[] data = { id_emp, nom, prenom, email, compte_bancaire, numero_id, salaire, poste, departement, commission };
				tableModel.addRow(data);

			}
			table.setModel(tableModel);
			
			/**
			 *  controle de largeur colone 
			 */
			
			table.getColumnModel().getColumn(0).setPreferredWidth(75); // ID
			table.getColumnModel().getColumn(1).setPreferredWidth(100);  // Nom
			table.getColumnModel().getColumn(2).setPreferredWidth(100); // Prenom
			table.getColumnModel().getColumn(3).setPreferredWidth(200); // Email
			table.getColumnModel().getColumn(4).setPreferredWidth(150); // Compte Bancaire
			table.getColumnModel().getColumn(5).setPreferredWidth(150); // Numero ID
			table.getColumnModel().getColumn(6).setPreferredWidth(100); // Salaire
			table.getColumnModel().getColumn(7).setPreferredWidth(100); // Poste
			table.getColumnModel().getColumn(8).setPreferredWidth(100); // Departement
			table.getColumnModel().getColumn(9).setPreferredWidth(100); // Commission
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String login) {
		AdminAccount account = new AdminAccount();
		account.DatabaseConnexion(login, null, null, frame);
		System.out.print(account.getId());
		frame = new JFrame();
		frame.setBounds(100, 100, 1591, 899);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("FICHE SALAIRE");
		lblNewLabel.setBounds(664, -11, 290, 79);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(11, 134, 411, 682);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "fiche_salaire",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNom.setBounds(12, 41, 99, 33);
		panel.add(lblNom);

		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrenom.setBounds(12, 97, 99, 33);
		panel.add(lblPrenom);

		txtnom = new JTextField();
		txtnom.setColumns(10);
		txtnom.setBounds(160, 46, 211, 27);
		panel.add(txtnom);

		txtprenom = new JTextField();
		txtprenom.setColumns(10);
		txtprenom.setBounds(160, 102, 211, 27);
		panel.add(txtprenom);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEmail.setBounds(12, 153, 99, 33);
		panel.add(lblEmail);

		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(160, 158, 211, 27);
		panel.add(txtemail);

		txtcompte_bancaire = new JTextField();
		txtcompte_bancaire.setColumns(10);
		txtcompte_bancaire.setBounds(160, 207, 211, 27);
		panel.add(txtcompte_bancaire);

		txtnumero_id = new JTextField();
		txtnumero_id.setColumns(10);
		txtnumero_id.setBounds(160, 266, 211, 27);
		panel.add(txtnumero_id);

		txtsalaire = new JTextField();
		txtsalaire.setColumns(10);
		txtsalaire.setBounds(160, 317, 211, 27);
		panel.add(txtsalaire);

		JLabel lblComptebancaire = new JLabel("Compte_Bank");
		lblComptebancaire.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblComptebancaire.setBounds(12, 202, 136, 33);
		panel.add(lblComptebancaire);

		JLabel lblNumeroid = new JLabel("Numero_id");
		lblNumeroid.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNumeroid.setBounds(12, 266, 109, 33);
		panel.add(lblNumeroid);

		JLabel lblSalaire = new JLabel("Salaire");
		lblSalaire.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSalaire.setBounds(12, 312, 99, 33);
		panel.add(lblSalaire);

		JLabel lblPoste = new JLabel("Poste");
		lblPoste.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPoste.setBounds(12, 368, 99, 33);
		panel.add(lblPoste);

		JLabel lblDepartement = new JLabel("Departement");
		lblDepartement.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDepartement.setBounds(12, 421, 123, 33);
		panel.add(lblDepartement);

		JLabel lblCommission = new JLabel("Commission");
		lblCommission.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCommission.setBounds(12, 475, 123, 33);
		panel.add(lblCommission);

		txtcommission = new JTextField("0");
		txtcommission.setColumns(10);
		txtcommission.setBounds(160, 480, 211, 27);
		panel.add(txtcommission);

		JComboBox<String> dropposte = new JComboBox<String>();
		dropposte.setBounds(160, 375, 211, 26);
		panel.add(dropposte);

		dropposte.addItem("");
		dropposte.addItem("Salarié");
		dropposte.addItem("Manager");
		dropposte.addItem("Admin");

		JComboBox<String> dropdept = new JComboBox<String>();
		dropdept.setBounds(160, 428, 211, 26);
		panel.add(dropdept);

		dropdept.addItem("");
		dropdept.addItem("Administration");
		dropdept.addItem("Comptabilite");
		dropdept.addItem("Vente");
		dropdept.addItem("RH");

		JButton btnExit = new JButton("Sortir");
		btnExit.setBounds(975, 766, 107, 50);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous quitter la page 'Fiche Salaire'?", "Warning",
						dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
				fiche_salaire.this.frame.setVisible(false);
				login_connection.main(login);
				}
			}
		});
		frame.getContentPane().add(btnExit);

		JButton btnClear = new JButton("Effacer");
		btnClear.setBounds(799, 766, 107, 50);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtnom.setText("");
				txtprenom.setText("");
				txtemail.setText("");
				txtnumero_id.setText("");
				txtcompte_bancaire.setText("");
				txtsalaire.setText("");
				dropposte.setSelectedItem("");
				dropdept.setSelectedItem("");
				txtcommission.setText("0");
				txtnom.requestFocus();
			}
		});
		frame.getContentPane().add(btnClear);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(447, 142, 1114, 611);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		// Option generer PDF 

		JButton btnsalaire = new JButton("PDF Salaire");
		btnsalaire.setBounds(447, 766, 107, 50);
		btnsalaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id, nom, prenom, email, numero_id, compte_bancaire, salaire, poste, departement, commission;

				id = textField.getText();
				nom = txtnom.getText();
				prenom = txtprenom.getText();
				email = txtemail.getText();
				numero_id = txtnumero_id.getText();
				compte_bancaire = Encrpyt_Banque(txtcompte_bancaire.getText());
				salaire = Encrpyt_Banque(txtsalaire.getText());
				poste = dropposte.getSelectedItem().toString();
				departement = dropdept.getSelectedItem().toString();
				commission = txtcommission.getText();


				try {
					
					/**
					 *  Controle de saisie sur les champs
					 */

					final String NOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

					final Pattern NOM_PATTERN = Pattern.compile(NOM_REGEX);

					final String PRENOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

					final Pattern PRENOM_PATTERN = Pattern.compile(PRENOM_REGEX);

					final String EMAIL_REGEX = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";

					final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

					final String NUMID_REGEX = "^[A-Z]+[0-9]{12}+[A-Z]$";

					final Pattern NUMID_PATTERN = Pattern.compile(NUMID_REGEX);

					final String BANCAIRE_REGEX = "^[0-9]{12}$";

					final Pattern BANCAIRE_PATTERN = Pattern.compile(BANCAIRE_REGEX);

					final String SALAIRE_REGEX = "[0-9]{4,}";

					final Pattern SALAIRE_PATTERN = Pattern.compile(SALAIRE_REGEX);

					final String COMMISSION_REGEX = "[0-9]{1,}";

					final Pattern COMMISSION_PATTERN = Pattern.compile(COMMISSION_REGEX);

					if (NOM_PATTERN.matcher(nom).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du nom n`est pas bon");
					}

					if (PRENOM_PATTERN.matcher(prenom).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du prenom n`est pas bon");
					}

					if (EMAIL_PATTERN.matcher(email).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion de l'email n`est pas bon");
					}

					if (BANCAIRE_PATTERN.matcher(Decrypt_Banque(compte_bancaire)).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du compte bancaire n`est pas bon");
					}

					if (NUMID_PATTERN.matcher(numero_id).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du numero identite n`est pas bon");
					}

					if (SALAIRE_PATTERN.matcher(Decrypt_Banque(salaire)).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du salaire n`est pas bon");
					}

					if (poste.equals("")) {
						JOptionPane.showMessageDialog(null, "L`insertion du poste invalide");
					}

					if (departement.equals("")) {
						JOptionPane.showMessageDialog(null, "L`insertion du departement invalide");
					}

					if (COMMISSION_PATTERN.matcher(commission).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion de la commission n`est pas bon");
					}

					int val1 = Integer.parseInt(txtsalaire.getText());
					int val2 = Integer.parseInt(txtcommission.getText());

					int total = val1 + val2;

					if (NOM_PATTERN.matcher(nom).matches() && PRENOM_PATTERN.matcher(prenom).matches()
							&& EMAIL_PATTERN.matcher(email).matches()
							&& BANCAIRE_PATTERN.matcher(Decrypt_Banque(compte_bancaire)).matches()
							&& NUMID_PATTERN.matcher(numero_id).matches()
							&& SALAIRE_PATTERN.matcher(Decrypt_Banque(salaire)).matches() && !poste.equals("")
							&& !departement.equals("") && COMMISSION_PATTERN.matcher(commission).matches()) {
						
						/**
						 *  insertion dans la table fiche salaire
						 */

						pst = con.prepareStatement(
								"insert into fiche_salaire(nom,prenom,email,numero_id,compte_bancaire,salaire,poste,departement,commission)values(?,?,?,?,?,?,?,?,?)");
						pst.setString(1, nom);
						pst.setString(2, prenom);
						pst.setString(3, email);
						pst.setString(4, numero_id);
						pst.setString(5, compte_bancaire);
						pst.setString(6, salaire);
						pst.setString(7, poste);
						pst.setString(8, departement);
						pst.setString(9, commission);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Added!");

						table_load();

						nom = txtnom.getText();
						prenom = txtprenom.getText();
						email = txtemail.getText();
						numero_id = txtnumero_id.getText();
						compte_bancaire = Encrpyt_Banque(txtcompte_bancaire.getText());
						salaire = Encrpyt_Banque(txtsalaire.getText());
						poste = dropposte.getSelectedItem().toString();
						departement = dropdept.getSelectedItem().toString();
						commission = txtcommission.getText();
						
						/**
						 *  Gernerer une fiche salaire pour la paye des employees
						 */

						JFileChooser dialog = new JFileChooser();
						dialog.setSelectedFile(new File(id + "_" + prenom + "_" + nom + "_FicheSalaire" + ".pdf"));
						int dialogResult = dialog.showSaveDialog(null);
						if (dialogResult == JFileChooser.APPROVE_OPTION) {
							String filePath = dialog.getSelectedFile().getPath();

							Document myDoc = new Document();
							@SuppressWarnings("unused")
							PdfWriter myWriter = PdfWriter.getInstance(myDoc, new FileOutputStream(filePath));
							myDoc.open();

							myDoc.add(new Paragraph("FICHE SALAIRE", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20)));

							myDoc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA, 10)));

							myDoc.add(new Paragraph("Detail de l'employée",
									FontFactory.getFont(FontFactory.HELVETICA_BOLD, 17)));

							myDoc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA, 20)));

							myDoc.add(new Paragraph("Date de la fiche salaire : " + date,
									FontFactory.getFont(FontFactory.HELVETICA, 15)));

							myDoc.add(new Paragraph("Nom: " + nom + " ", FontFactory.getFont(FontFactory.HELVETICA, 15)));
							myDoc.add(new Paragraph("Prenom: " + prenom + " ",
									FontFactory.getFont(FontFactory.HELVETICA, 15)));
							myDoc.add(new Paragraph("Email: " + email + " ",
									FontFactory.getFont(FontFactory.HELVETICA, 15)));
							myDoc.add(new Paragraph("Numéro carte d'identité: " + numero_id + " ",
									FontFactory.getFont(FontFactory.HELVETICA, 15)));
							myDoc.add(new Paragraph("Nombre compte bancaire: " + Decrypt_Banque(compte_bancaire) + " ",
									FontFactory.getFont(FontFactory.HELVETICA, 15)));
							myDoc.add(new Paragraph("Poste: " + poste + " ",
									FontFactory.getFont(FontFactory.HELVETICA, 15)));
							myDoc.add(new Paragraph("Departement: " + departement + " ",
									FontFactory.getFont(FontFactory.HELVETICA, 15)));
							myDoc.add(new Paragraph("Base Salaire: " + Decrypt_Banque(salaire) + " ",
									FontFactory.getFont(FontFactory.HELVETICA, 15)));
							myDoc.add(new Paragraph("Commission: " + commission + " ",
									FontFactory.getFont(FontFactory.HELVETICA, 15)));
							myDoc.add(new Paragraph("Totale Salaire: " + total + " ",
									FontFactory.getFont(FontFactory.HELVETICA, 15)));

							myDoc.close();
							JOptionPane.showMessageDialog(null, "PDF Valider");

							txtnom.setText("");
							txtprenom.setText("");
							txtemail.setText("");
							txtnumero_id.setText("");
							txtcompte_bancaire.setText("");
							txtsalaire.setText("");
							dropposte.setSelectedItem("");
							dropdept.setSelectedItem("");
							txtcommission.setText("0");
							txtnom.requestFocus();
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		frame.getContentPane().add(btnsalaire);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(11, 53, 1550, 69);
		frame.getContentPane().add(panel_1);

		JLabel label = new JLabel("id_emp");
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(34, 24, 75, 17);
		panel_1.add(label);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {

			// Recherche au niveau de l'id pour un employee specifique
			
			@Override
			public void keyReleased(KeyEvent e) {
				try {

					String id = textField.getText();

					pst = con.prepareStatement(
							"select nom,prenom,email,numero_id,compte_bancaire,salaire,poste,departement from rh_employee where id_emp = ?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();

					if (rs.next() == true) {

						String nom = rs.getString(1);
						String prenom = rs.getString(2);
						String email = rs.getString(3);
						String numero_id = rs.getString(4);
						String compte_bancaire = Decrypt_Banque(rs.getString(5));
						String salaire = Decrypt_Banque(rs.getString(6));
						String poste = rs.getString(7);
						String departement = rs.getString(8);
						String commission = "";
						
						/**
						 *  Une requete pour faire la jointure avec la table vendeur commission pour recevoir les commissions des vendeurs specifiques
						 */

						if (departement.contains("Vente")) {
							String test = "SELECT SUM(b.quantite) * 3500 FROM rh_employee AS a, vendeur_commande AS b WHERE a.id_emp = b.id_emp  AND statue = 'Termine'  AND a.departement = 'Vente' AND a.id_emp = ? AND MONTH(date) = MONTH(CURRENT_DATE())\r\n"
									+ "AND YEAR(date) = YEAR(CURRENT_DATE())";
							pst = con.prepareStatement(test);
							pst.setString(1, id);
							ResultSet rsVente = pst.executeQuery();
							while (rsVente.next()) {
								commission = String.valueOf(rsVente.getInt(1));
							}
						} else {
							commission = "0";
						}

						txtnom.setText(nom);
						txtprenom.setText(prenom);
						txtemail.setText(email);
						txtnumero_id.setText(numero_id);
						txtcompte_bancaire.setText(compte_bancaire);
						txtsalaire.setText(salaire);
						txtcommission.setText(commission);

						dropposte.setSelectedItem(poste);
						dropdept.setSelectedItem(departement);
					} else {

						txtnom.setText("");
						txtprenom.setText("");
						txtemail.setText("");
						txtnumero_id.setText("");
						txtcompte_bancaire.setText("");
						txtsalaire.setText("");
						txtcommission.setText("0");
						dropposte.setSelectedItem("");
						dropdept.setSelectedItem("");

					}

				}

				catch (SQLException ex) {

				}
			}
		});
		textField.setColumns(10);
		textField.setBounds(121, 22, 1087, 22);
		panel_1.add(textField);

		JButton btnaffichagesalaire = new JButton("Affichage Salaire");
		btnaffichagesalaire.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {

				affichage_fsalaire fsalaire = new affichage_fsalaire(login);
				fsalaire.ficheSalaire(login);

			}
		});
		btnaffichagesalaire.setBounds(612, 766, 136, 50);
		frame.getContentPane().add(btnaffichagesalaire);
	}
}
