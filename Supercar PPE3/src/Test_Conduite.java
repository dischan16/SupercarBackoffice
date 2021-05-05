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
  * @author disch
  *
  */

public class Test_Conduite {

	private JFrame frame;
	private JTextField txtnom;
	private JTextField txtprenom;
	private JTable table;
	private JTextField txtid_test;
	

	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test_Conduite window = new Test_Conduite(login);
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
	public Test_Conduite(String login) {
		Connect();
		initialize(login);
		count_load();
		table_load();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txttelephone;
	private JTextField txtemail;
	private JTextField textdate;
	private JLabel lblNoTestConduite;
	private int count = 0;

	// Connection base de donnee
	
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/backoffice", "root", "");
		} catch (ClassNotFoundException ex) {

		} catch (SQLException ex) {

		}
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return this.count;
	}
	
	/**
	 *  Affichage du nombre total de test de conduite  
	 */
	
	public void count_load() {
		count = 0;
		try {
			pst = con.prepareStatement("select * from test_conduite");
			rs = pst.executeQuery();

			while (rs.next()) {
				count++;
			}
			lblNoTestConduite.setText("No Test Conduite : "+getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void table_load() {
		try {
			pst = con.prepareStatement("select * from test_conduite");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			/**
			 *  controle de largeur colone 
			 */
			
			table.getColumnModel().getColumn(0).setPreferredWidth(75); // ID
			table.getColumnModel().getColumn(1).setPreferredWidth(100); //Civilite
			table.getColumnModel().getColumn(2).setPreferredWidth(100);  // Nom
			table.getColumnModel().getColumn(3).setPreferredWidth(100); // Prenom
			table.getColumnModel().getColumn(4).setPreferredWidth(100); // Telephone
			table.getColumnModel().getColumn(5).setPreferredWidth(200); // Email
			table.getColumnModel().getColumn(6).setPreferredWidth(175); // Voiture
			table.getColumnModel().getColumn(7).setPreferredWidth(125); // Date
			table.getColumnModel().getColumn(8).setPreferredWidth(125); // Etat
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 *  concatenation pour avoir la description d'une voiture marque + modele
	 * @param jc
	 * @return
	 * @throws SQLException
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox Test(JComboBox jc) throws SQLException {
		DefaultComboBoxModel theModel = (DefaultComboBoxModel) jc.getModel();
		PreparedStatement pst = con.prepareStatement("Select * from voitures");
		ResultSet rs = pst.executeQuery();
		theModel.removeAllElements();
		theModel.addElement("");
		while (rs.next()) {
			theModel.addElement(rs.getString("marque") + " " + rs.getString("modele"));
		}
		jc.setModel(theModel);
		return jc;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize(String login) {
		AdminAccount account = new AdminAccount();
		account.DatabaseConnexion(login, null, null, frame);
		System.out.print(account.getId());
		frame = new JFrame();
		frame.setBounds(100, 100, 1393, 810);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Test Conduite");
		lblNewLabel.setBounds(556, -12, 243, 79);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(12, 168, 411, 475);
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
		lblTelephone.setBounds(12, 189, 99, 33);
		panel.add(lblTelephone);

		txttelephone = new JTextField();
		txttelephone.setColumns(10);
		txttelephone.setBounds(160, 194, 211, 27);
		panel.add(txttelephone);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEmail.setBounds(12, 243, 99, 33);
		panel.add(lblEmail);

		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(160, 248, 211, 27);
		panel.add(txtemail);

		JLabel lblVoiture = new JLabel("Voiture");
		lblVoiture.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVoiture.setBounds(12, 298, 99, 33);
		panel.add(lblVoiture);

		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDate.setBounds(12, 344, 99, 33);
		panel.add(lblDate);

		textdate = new JTextField();
		textdate.setColumns(10);
		textdate.setBounds(160, 349, 211, 27);
		panel.add(textdate);

		JLabel lbletat = new JLabel("Etat");
		lbletat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbletat.setBounds(12, 392, 99, 33);
		panel.add(lbletat);

		JComboBox dropetat = new JComboBox();
		dropetat.setBounds(160, 397, 211, 26);
		panel.add(dropetat);

		dropetat.addItem("");
		dropetat.addItem("En attente");
		dropetat.addItem("En cours");
		dropetat.addItem("Termine");

		dropetat.setSelectedItem("Selecter Etat");

		JComboBox dropcivil = new JComboBox();
		dropcivil.setBounds(160, 47, 211, 26);
		panel.add(dropcivil);

		dropcivil.addItem("");
		dropcivil.addItem("Monsieur");
		dropcivil.addItem("Madame");

		JComboBox dropvoiture = new JComboBox();
		dropvoiture.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					Test(dropvoiture);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		dropvoiture.setBounds(160, 305, 211, 26);
		panel.add(dropvoiture);
		dropvoiture.addItem("");
		
		/**
		 *  Sauvegarder les donnees
		 */

		JButton btnNewButton = new JButton("Sauvegarder");
		btnNewButton.setBounds(51, 679, 135, 50);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String civilite, nom, prenom, telephone, email, voiture, date, etat;
	
				civilite = dropcivil.getSelectedItem().toString();
				nom = txtnom.getText();
				prenom = txtprenom.getText();
				telephone = txttelephone.getText();
				email = txtemail.getText();
				voiture = dropvoiture.getSelectedItem().toString();
				date = textdate.getText();
				etat = dropetat.getSelectedItem().toString();
						
				try {
					
					/**
					 *  Controle de saisie sur les champs
					 */

					final String NOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

					final Pattern NOM_PATTERN = Pattern.compile(NOM_REGEX);

					final String PRENOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

					final Pattern PRENOM_PATTERN = Pattern.compile(PRENOM_REGEX);

					final String TELEPHONE_REGEX = "^[0-9]{8}$";

					final Pattern TELEPHONE_PATTERN = Pattern.compile(TELEPHONE_REGEX);

					final String EMAIL_REGEX = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";

					final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

					final String DATE_REGEX = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";

					final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);

					if (civilite.equals("")) {
						JOptionPane.showMessageDialog(null, "L`insertion civilite invalide");
					}
					
					if (NOM_PATTERN.matcher(nom).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du nom n`est pas bon");
					}

					if (PRENOM_PATTERN.matcher(prenom).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du prenom n`est pas bon");
					}

					if (TELEPHONE_PATTERN.matcher(telephone).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du numero telephone n`est pas bon");
					}

					if (EMAIL_PATTERN.matcher(email).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion de l'email n`est pas bon");
					}
					
					if (voiture.equals("")) {
						JOptionPane.showMessageDialog(null, "L`insertion de la voiture invalide");
					}


					if (DATE_PATTERN.matcher(date).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion de la date n`est pas bon (dd/mm/yy)");
					}
					
					if (etat.equals("")) {
						JOptionPane.showMessageDialog(null, "L`insertion de l'etat invalide");
					}
					

					if (!civilite.equals("") && NOM_PATTERN.matcher(nom).matches() && PRENOM_PATTERN.matcher(prenom).matches()
							&& TELEPHONE_PATTERN.matcher(telephone).matches() && EMAIL_PATTERN.matcher(email).matches()
							&& DATE_PATTERN.matcher(date).matches()
							&& !etat.equals("") && !voiture.equals("")) {

						pst = con.prepareStatement(
								"insert into test_conduite(civilite,nom,prenom,telephone,email,voiture,date,etat)values(?,?,?,?,?,?,?,?)");
						pst.setString(1, civilite);
						pst.setString(2, nom);
						pst.setString(3, prenom);
						pst.setString(4, telephone);
						pst.setString(5, email);
						pst.setString(6, voiture);
						pst.setString(7, date);
						pst.setString(8, etat);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Added!");
						count_load();
						table_load();

						dropcivil.setSelectedItem("");
						txtnom.setText("");
						txtprenom.setText("");
						txttelephone.setText("");
						txtemail.setText("");
						textdate.setText("");
						dropvoiture.setSelectedItem("");
						dropetat.setSelectedItem("");
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
		btnExit.setBounds(851, 679, 122, 50);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous quitter la page 'Test de conduite'?", "Warning",
						dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
				Test_Conduite.this.frame.setVisible(false);
				login_connection.main(login);
				}
			}
		});
		frame.getContentPane().add(btnExit);

		JButton btnClear = new JButton("Effacer");
		btnClear.setBounds(643, 679, 128, 50);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dropcivil.setSelectedItem("");
				txtnom.setText("");
				txtprenom.setText("");
				txttelephone.setText("");
				txtemail.setText("");
				textdate.setText("");
				dropvoiture.setSelectedItem("");
				dropetat.setSelectedItem("");
				txtnom.requestFocus();
			
			}
		});
		frame.getContentPane().add(btnClear);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(435, 179, 910, 464);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 64, 1333, 69);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblid_test = new JLabel("id_test");
		lblid_test.setBounds(34, 24, 75, 17);
		lblid_test.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1.add(lblid_test);

		txtid_test = new JTextField();
		txtid_test.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				try {
					
					

					String id = txtid_test.getText();
					
					 /**
					  *  La bar de recherche au niveau ID	
					  */

					pst = con.prepareStatement(
							"select civilite,nom,prenom,telephone,email,voiture,date,etat from test_conduite where id_test = ?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();

					if (rs.next() == true) {

						String civilite = rs.getString(1);
						String nom = rs.getString(2);
						String prenom = rs.getString(3);
						String telephone = rs.getString(4);
						String email = rs.getString(5);
						String voiture = rs.getString(6);
						String date = rs.getString(7);
						String etat = rs.getString(8);

						dropcivil.setSelectedItem(civilite);
						txtnom.setText(nom);
						txtprenom.setText(prenom);
						txttelephone.setText(telephone);
						txtemail.setText(email);
						dropvoiture.setSelectedItem(voiture);
						textdate.setText(date);
						dropetat.setSelectedItem(etat);

					} else {

						dropcivil.setSelectedItem("");
						txtnom.setText("");
						txtprenom.setText("");
						txttelephone.setText("");
						txtemail.setText("");
						dropvoiture.setSelectedItem("");
						textdate.setText("");
						dropetat.setSelectedItem("");
					}

				}

				catch (SQLException ex) {

				}
			}
		});
		txtid_test.setBounds(145, 22, 1154, 22);
		txtid_test.setColumns(10);
		panel_1.add(txtid_test);
		
		/**
		 *  L'option modification de donnee
		 */

		JButton btnUpdate = new JButton("Modifier");
		btnUpdate.setBounds(242, 679, 128, 50);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String civilite, nom, prenom, telephone, email, voiture, date, id_test, etat;
		

				civilite = dropcivil.getSelectedItem().toString();
				nom = txtnom.getText();
				prenom = txtprenom.getText();
				telephone = txttelephone.getText();
				email = txtemail.getText();
				voiture = dropvoiture.getSelectedItem().toString();
				date = textdate.getText();
				id_test = txtid_test.getText();
				etat = dropetat.getSelectedItem().toString();

				
				try {
					
					/**
					 *  Controle de saisie sur les champs
					 */
					

					final String NOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

					final Pattern NOM_PATTERN = Pattern.compile(NOM_REGEX);

					final String PRENOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

					final Pattern PRENOM_PATTERN = Pattern.compile(PRENOM_REGEX);

					final String TELEPHONE_REGEX = "^[0-9]{8}$";

					final Pattern TELEPHONE_PATTERN = Pattern.compile(TELEPHONE_REGEX);

					final String EMAIL_REGEX = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";

					final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

					final String DATE_REGEX = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";

					final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);

					if (civilite.equals("")) {
						JOptionPane.showMessageDialog(null, "L`insertion civilite invalide");
					}
					
					if (NOM_PATTERN.matcher(nom).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du nom n`est pas bon");
					}

					if (PRENOM_PATTERN.matcher(prenom).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du prenom n`est pas bon");
					}

					if (TELEPHONE_PATTERN.matcher(telephone).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du numero telephone n`est pas bon");
					}

					if (EMAIL_PATTERN.matcher(email).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion de l'email n`est pas bon");
					}
					
					if (voiture.equals("")) {
						JOptionPane.showMessageDialog(null, "L`insertion de la voiture invalide");
					}


					if (DATE_PATTERN.matcher(date).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion de la date n`est pas bon (dd/mm/yy)");
					}
					
					if (etat.equals("")) {
						JOptionPane.showMessageDialog(null, "L`insertion de l'etat invalide");
					}
					

					if (!civilite.equals("") && NOM_PATTERN.matcher(nom).matches() && PRENOM_PATTERN.matcher(prenom).matches()
							&& TELEPHONE_PATTERN.matcher(telephone).matches() && EMAIL_PATTERN.matcher(email).matches()
							&& DATE_PATTERN.matcher(date).matches()
							&& !etat.equals("") && !voiture.equals("")) {
					
				

					pst = con.prepareStatement(
							"update test_conduite set civilite=?,nom=?,prenom=?,telephone=?,email=?,voiture=?,date=?,etat=? where id_test =?");
					pst.setString(1, civilite);
					pst.setString(2, nom);
					pst.setString(3, prenom);
					pst.setString(4, telephone);
					pst.setString(5, email);
					pst.setString(6, voiture);
					pst.setString(7, date);
					pst.setString(8, etat);
					pst.setString(9, id_test);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated!");
					table_load();

					dropcivil.setSelectedItem("");
					txtnom.setText("");
					txtprenom.setText("");
					txttelephone.setText("");
					txtemail.setText("");
					dropvoiture.setSelectedItem("");
					textdate.setText("");
					dropetat.setSelectedItem("");

					txtnom.requestFocus();
				
				
					}
					
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnUpdate);
		
		/**
		 *  l'option supprimer les donnees
		 */

		JButton btnDelete = new JButton("Supprimer");
		btnDelete.setBounds(435, 679, 135, 50);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id_test;
				id_test = txtid_test.getText();

				try {
					pst = con.prepareStatement("delete from test_conduite where id_test =?");

					pst.setString(1, id_test);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted!");
					count_load();
					table_load();

					txtnom.setText("");
					txtprenom.setText("");
					txttelephone.setText("");
					txtemail.setText("");
					dropvoiture.setSelectedItem("");
					textdate.setText("");
					txtnom.requestFocus();
				}

				catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnDelete);
		
		lblNoTestConduite = new JLabel();
		lblNoTestConduite.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNoTestConduite.setBounds(1070, 690, 277, 27);
		frame.getContentPane().add(lblNoTestConduite);
	}
}
