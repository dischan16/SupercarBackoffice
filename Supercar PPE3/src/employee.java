import java.awt.EventQueue;
import java.sql.*;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
//import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.io.IOException;

import javax.swing.JComboBox;

public class employee {

	private JFrame frame;
	private JTextField txtnom;
	private JTextField txtprenom;
	private JTable table;
	private JTextField txtid_emp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employee window = new employee();
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
	public employee() {
		initialize();
		Connect();
		table_load();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtage;
	private JTextField txttelephone;
	private JTextField txtadresse;
	private JTextField txtemail;
	private JTextField txtcompte_bancaire;
	private JTextField txtnumero_id;
	private JTextField txtsalaire;

	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/backoffice", "root", "");
		} catch (ClassNotFoundException ex) {

		} catch (SQLException ex) {

		}
	}

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

	public void table_load() {
		try {
			pst = con.prepareStatement("select * from rh_employee");
			rs = pst.executeQuery();

			DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {},
					new String[] { "ID", "civilite", "Nom", "Prenom", "Age", "Telephone", "Adresse", "Email", "license",
							"Compte Bancaire", "Numero ID", "salaire", "Poste", "Departement", "Lieu" });
			while (rs.next()) {
				String id_emp = rs.getString("id_emp");
				String civilite = rs.getString("civilite");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String age = rs.getString("age");
				String telephone = rs.getString("telephone");
				String adresse = rs.getString("adresse");
				String email = rs.getString("email");
				String license = rs.getString("license");
				String compte_bancaire = Decrypt_Banque(rs.getString("compte_bancaire"));
				String numero_id = rs.getString("numero_id");
				String salaire = Decrypt_Banque(rs.getString("salaire"));
				String poste = rs.getString("poste");
				String departement = rs.getString("departement");
				String lieu = rs.getString("lieu");

				String[] data = { id_emp, civilite, nom, prenom, age, telephone, adresse, email, license,
						compte_bancaire, numero_id, salaire, poste, departement, lieu };
				tableModel.addRow(data);

			}
			table.setModel(tableModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1953, 1135);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Employee");
		lblNewLabel.setBounds(836, 0, 194, 79);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 40));
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(12, 162, 411, 805);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblCivilite = new JLabel("Civilite");
		lblCivilite.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCivilite.setBounds(12, 42, 99, 33);
		panel.add(lblCivilite);

		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNom.setBounds(12, 97, 99, 33);
		panel.add(lblNom);

		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrenom.setBounds(12, 143, 99, 33);
		panel.add(lblPrenom);

		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAge.setBounds(12, 189, 99, 33);
		panel.add(lblAge);

		txtnom = new JTextField();
		txtnom.setColumns(10);
		txtnom.setBounds(160, 96, 211, 27);
		panel.add(txtnom);

		txtprenom = new JTextField();
		txtprenom.setColumns(10);
		txtprenom.setBounds(160, 148, 211, 27);
		panel.add(txtprenom);

		JLabel lblTelephone = new JLabel("Telephone");
		lblTelephone.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTelephone.setBounds(12, 235, 99, 33);
		panel.add(lblTelephone);

		txtage = new JTextField();
		txtage.setColumns(10);
		txtage.setBounds(160, 194, 211, 27);
		panel.add(txtage);

		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdresse.setBounds(12, 281, 99, 33);
		panel.add(lblAdresse);

		txttelephone = new JTextField();
		txttelephone.setColumns(10);
		txttelephone.setBounds(160, 239, 211, 27);
		panel.add(txttelephone);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEmail.setBounds(12, 327, 99, 33);
		panel.add(lblEmail);

		txtadresse = new JTextField();
		txtadresse.setColumns(10);
		txtadresse.setBounds(160, 285, 211, 27);
		panel.add(txtadresse);

		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(160, 331, 211, 27);
		panel.add(txtemail);

		txtcompte_bancaire = new JTextField();
		txtcompte_bancaire.setColumns(10);
		txtcompte_bancaire.setBounds(160, 427, 211, 27);
		panel.add(txtcompte_bancaire);

		txtnumero_id = new JTextField();
		txtnumero_id.setColumns(10);
		txtnumero_id.setBounds(160, 472, 211, 27);
		panel.add(txtnumero_id);

		txtsalaire = new JTextField();
		txtsalaire.setColumns(10);
		txtsalaire.setBounds(160, 514, 211, 27);
		panel.add(txtsalaire);

		JLabel lblLicense = new JLabel("License");
		lblLicense.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLicense.setBounds(12, 373, 99, 33);
		panel.add(lblLicense);

		JLabel lblComptebancaire = new JLabel("Compte_Bank");
		lblComptebancaire.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblComptebancaire.setBounds(12, 422, 136, 33);
		panel.add(lblComptebancaire);

		JLabel lblNumeroid = new JLabel("Numero_id");
		lblNumeroid.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNumeroid.setBounds(12, 467, 109, 33);
		panel.add(lblNumeroid);

		JLabel lblSalaire = new JLabel("Salaire");
		lblSalaire.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSalaire.setBounds(12, 508, 99, 33);
		panel.add(lblSalaire);

		JLabel lblPoste = new JLabel("Poste");
		lblPoste.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPoste.setBounds(12, 549, 99, 33);
		panel.add(lblPoste);

		JLabel lblDepartement = new JLabel("Departement");
		lblDepartement.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDepartement.setBounds(12, 589, 123, 33);
		panel.add(lblDepartement);

		JLabel lblEntrepot = new JLabel("Lieu");
		lblEntrepot.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEntrepot.setBounds(14, 634, 123, 33);
		panel.add(lblEntrepot);

		JComboBox<String> dropcivil = new JComboBox<String>();
		dropcivil.setBounds(160, 49, 211, 26);
		panel.add(dropcivil);

		dropcivil.addItem(" ");
		dropcivil.addItem("Monsieur");
		dropcivil.addItem("Madame");

		JComboBox<String> droplicense = new JComboBox<String>();
		droplicense.setBounds(159, 380, 212, 27);
		panel.add(droplicense);

		droplicense.addItem(" ");
		droplicense.addItem("Oui");
		droplicense.addItem("Non");

		JComboBox<String> dropposte = new JComboBox<String>();
		dropposte.setBounds(160, 554, 211, 27);
		panel.add(dropposte);

		dropposte.addItem(" ");
		dropposte.addItem("Vendeur");
		dropposte.addItem("RH");
		dropposte.addItem("Comptable");
		dropposte.addItem("Administrateur");
		dropposte.addItem("Manager");

		JComboBox<String> dropdept = new JComboBox<String>();
		dropdept.setBounds(160, 596, 211, 26);
		panel.add(dropdept);

		dropdept.addItem(" ");
		dropdept.addItem("Administration");
		dropdept.addItem("Comptabilite");
		dropdept.addItem("Vente	");
		dropdept.addItem("RH");

		JComboBox<String> droplieu = new JComboBox<String>();
		droplieu.setBounds(160, 641, 211, 26);
		panel.add(droplieu);

		droplieu.addItem(" ");
		droplieu.addItem("Siege Social");
		droplieu.addItem("Port Louis");
		droplieu.addItem("Baie du Tombeau");
		droplieu.addItem("Phoenix");
		droplieu.addItem("Plaisance");

		JButton btnNewButton = new JButton("Sauvegarder");
		btnNewButton.setBounds(435, 917, 156, 50);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom, prenom, age, telephone, adresse, email, compte_bancaire, numero_id, salaire;
				Object civilite, license, poste, departement, lieu;

				civilite = dropcivil.getSelectedItem();
				nom = txtnom.getText();
				prenom = txtprenom.getText();
				age = txtage.getText();
				telephone = txttelephone.getText();
				adresse = txtadresse.getText();
				email = txtemail.getText();
				license = droplicense.getSelectedItem();
				compte_bancaire = Encrpyt_Banque(txtcompte_bancaire.getText());
				numero_id = txtnumero_id.getText();
				salaire = Encrpyt_Banque(txtsalaire.getText());
				poste = dropposte.getSelectedItem();
				departement = dropdept.getSelectedItem();
				lieu = droplieu.getSelectedItem();

				try {

					final String NOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

					final Pattern NOM_PATTERN = Pattern.compile(NOM_REGEX);

					final String PRENOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

					final Pattern PRENOM_PATTERN = Pattern.compile(PRENOM_REGEX);

					final String AGE_REGEX = "^(?:|60|1[8-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9])$";

					final Pattern AGE_PATTERN = Pattern.compile(AGE_REGEX);

					final String TELEPHONE_REGEX = "^[0-9]{8}$";

					final Pattern TELEPHONE_PATTERN = Pattern.compile(TELEPHONE_REGEX);

					final String ADRESSE_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z][0-9]*)*$";

					final Pattern ADRESSE_PATTERN = Pattern.compile(ADRESSE_REGEX);

					final String EMAIL_REGEX = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";

					final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

					final String BANCAIRE_REGEX = "^[0-9]{12}$";

					final Pattern BANCAIRE_PATTERN = Pattern.compile(BANCAIRE_REGEX);

					final String NUMID_REGEX = "^[A-Z]+[0-9]{12}+[A-Z]$";

					final Pattern NUMID_PATTERN = Pattern.compile(NUMID_REGEX);

					final String SALAIRE_REGEX = "[0-9]{3,}";

					final Pattern SALAIRE_PATTERN = Pattern.compile(SALAIRE_REGEX);

					if (NOM_PATTERN.matcher(nom).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du nom n`est pas bon");
					}

					if (PRENOM_PATTERN.matcher(prenom).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du prenom n`est pas bon");
					}

					if (AGE_PATTERN.matcher(age).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion de age n`est pas bon");
					}

					if (TELEPHONE_PATTERN.matcher(telephone).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du numero telephone n`est pas bon");
					}

					if (ADRESSE_PATTERN.matcher(adresse).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion de l'adresse n`est pas bon");
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

					if (NOM_PATTERN.matcher(nom).matches() && PRENOM_PATTERN.matcher(prenom).matches()
							&& AGE_PATTERN.matcher(age).matches() && TELEPHONE_PATTERN.matcher(telephone).matches()
							&& ADRESSE_PATTERN.matcher(adresse).matches() && EMAIL_PATTERN.matcher(email).matches()
							&& BANCAIRE_PATTERN.matcher(Decrypt_Banque(compte_bancaire)).matches()
							&& NUMID_PATTERN.matcher(numero_id).matches()
							&& SALAIRE_PATTERN.matcher(Decrypt_Banque(salaire)).matches()) {

						pst = con.prepareStatement(
								"insert into rh_employee(civilite,nom,prenom,age,telephone,adresse,email,license,compte_bancaire,numero_id,salaire,poste,departement,lieu) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						pst.setString(1, (String) civilite);
						pst.setString(2, nom);
						pst.setString(3, prenom);
						pst.setString(4, age);
						pst.setString(5, telephone);
						pst.setString(6, adresse);
						pst.setString(7, email);
						pst.setString(8, (String) license);
						pst.setString(9, compte_bancaire);
						pst.setString(10, numero_id);
						pst.setString(11, salaire);
						pst.setString(12, (String) poste);
						pst.setString(13, (String) departement);
						pst.setString(14, (String) lieu);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Added!");
						table_load();

						dropcivil.setSelectedItem("");
						txtnom.setText("");
						txtprenom.setText("");
						txtage.setText("");
						txttelephone.setText("");
						txtadresse.setText("");
						txtemail.setText("");
						droplicense.setSelectedItem("");
						txtcompte_bancaire.setText("");
						txtnumero_id.setText("");
						txtsalaire.setText("");
						dropposte.setSelectedItem("");
						dropdept.setSelectedItem("");
						droplieu.setSelectedItem("");
						txtnom.requestFocus();
					}
				}

				catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton);

		JButton btnExit = new JButton("Sortir");
		btnExit.setBounds(1372, 917, 156, 50);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnExit);

		JButton btnClear = new JButton("Effacer");
		btnClear.setBounds(1139, 917, 156, 50);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dropcivil.setSelectedItem("");
				txtnom.setText("");
				txtprenom.setText("");
				txtage.setText("");
				txttelephone.setText("");
				txtadresse.setText("");
				txtemail.setText("");
				droplicense.setSelectedItem("");
				txtcompte_bancaire.setText("");
				txtnumero_id.setText("");
				txtsalaire.setText("");
				dropposte.setSelectedItem("");
				dropdept.setSelectedItem("");
				droplieu.setSelectedItem("");
				txtnom.requestFocus();
			}
		});
		frame.getContentPane().add(btnClear);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(435, 168, 1455, 717);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 66, 1878, 69);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblid_emp = new JLabel("id_emp");
		lblid_emp.setBounds(34, 24, 75, 17);
		lblid_emp.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1.add(lblid_emp);

		txtid_emp = new JTextField();
		txtid_emp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				try {

					String id = txtid_emp.getText();

					pst = con.prepareStatement(
							"select civilite,nom,prenom,age,telephone,adresse,email,license,compte_bancaire,numero_id,salaire,poste,departement,lieu from rh_employee where id_emp = ?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();

					if (rs.next() == true) {

						Object civilite = rs.getObject(1);
						String nom = rs.getString(2);
						String prenom = rs.getString(3);
						String age = rs.getString(4);
						String telephone = rs.getString(5);
						String adresse = rs.getString(6);
						String email = rs.getString(7);
						Object license = rs.getObject(8);
						String compte_bancaire = Decrypt_Banque(rs.getString(9));
						String numero_id = rs.getString(10);
						String salaire = Decrypt_Banque(rs.getString(11));
						Object poste = rs.getString(12);
						Object departement = rs.getString(13);
						String lieu = rs.getString(14);

						dropcivil.setSelectedItem(civilite);
						txtnom.setText(nom);
						txtprenom.setText(prenom);
						txtage.setText(age);
						txttelephone.setText(telephone);
						txtadresse.setText(adresse);
						txtemail.setText(email);
						droplicense.setSelectedItem(license);
						txtcompte_bancaire.setText(compte_bancaire);
						txtnumero_id.setText(numero_id);
						txtsalaire.setText(salaire);
						dropposte.setSelectedItem(poste);
						dropdept.setSelectedItem(departement);
						droplieu.setSelectedItem(lieu);

					} else {
						dropcivil.setSelectedItem("");
						txtnom.setText("");
						txtprenom.setText("");
						txtage.setText("");
						txttelephone.setText("");
						txtadresse.setText("");
						txtemail.setText("");
						droplicense.setSelectedItem("");
						txtcompte_bancaire.setText("");
						txtnumero_id.setText("");
						txtsalaire.setText("");
						dropposte.setSelectedItem("");
						dropdept.setSelectedItem("");
						droplieu.setSelectedItem("");

					}

				}

				catch (Exception ex) {

				}
			}
		});
		txtid_emp.setBounds(121, 22, 1721, 22);
		txtid_emp.setColumns(10);
		panel_1.add(txtid_emp);

		JButton btnUpdate = new JButton("Modifier");
		btnUpdate.setBounds(670, 917, 156, 50);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nom, prenom, age, telephone, adresse, email, compte_bancaire, numero_id, salaire, id_emp;
				Object civilite, license, poste, departement, lieu;

				civilite = dropcivil.getSelectedItem();
				nom = txtnom.getText();
				prenom = txtprenom.getText();
				age = txtage.getText();
				telephone = txttelephone.getText();
				adresse = txtadresse.getText();
				email = txtemail.getText();
				license = droplicense.getSelectedItem();
				compte_bancaire = Encrpyt_Banque(txtcompte_bancaire.getText());
				numero_id = txtnumero_id.getText();
				salaire = Encrpyt_Banque(txtsalaire.getText());
				poste = dropposte.getSelectedItem();
				departement = dropdept.getSelectedItem();
				lieu = droplieu.getSelectedItem();
				id_emp = txtid_emp.getText();

				try {
					pst = con.prepareStatement(
							"update rh_employee set civilite= ?,nom=?,prenom=?,age=?,telephone=?,adresse=?,email=?,license=?,compte_bancaire=?,numero_id=?,salaire=?,poste=?,departement=?,lieu=? where id_emp =?");
					pst.setString(1, (String) civilite);
					pst.setString(2, nom);
					pst.setString(3, prenom);
					pst.setString(4, age);
					pst.setString(5, telephone);
					pst.setString(6, adresse);
					pst.setString(7, email);
					pst.setString(8, (String) license);
					pst.setString(9, compte_bancaire);
					pst.setString(10, numero_id);
					pst.setString(11, salaire);
					pst.setString(12, (String) poste);
					pst.setString(13, (String) departement);
					pst.setString(14, (String) lieu);
					pst.setString(15, id_emp);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated!");
					table_load();

					dropcivil.setSelectedItem("");
					txtnom.setText("");
					txtprenom.setText("");
					txtage.setText("");
					txttelephone.setText("");
					txtadresse.setText("");
					txtemail.setText("");
					droplicense.setSelectedItem("");
					txtcompte_bancaire.setText("");
					txtnumero_id.setText("");
					txtsalaire.setText("");
					dropposte.setSelectedItem("");
					dropdept.setSelectedItem("");
					droplieu.setSelectedItem("");

					txtnom.requestFocus();
				}

				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnUpdate);

		JButton btnDelete = new JButton("Supprimer");
		btnDelete.setBounds(900, 917, 156, 50);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id_emp;
				id_emp = txtid_emp.getText();

				try {
					pst = con.prepareStatement("delete from rh_employee where id_emp =?");

					pst.setString(1, id_emp);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted!");
					table_load();

					txtnom.setText("");
					txtprenom.setText("");
					txtage.setText("");
					txttelephone.setText("");
					txtadresse.setText("");
					txtemail.setText("");
					txtcompte_bancaire.setText("");
					txtnumero_id.setText("");
					txtsalaire.setText("");

					txtnom.requestFocus();
				}

				catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnDelete);
	}
}
