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

@SuppressWarnings("unused")
public class update_commande {

	//Initialisation des variables
	
	private JFrame frame;
	private JTable table;
	private JTextField txtid;
	private int count = 0;
	private int count1 = 0;
	private int count2 = 0;
	private int count3 = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					update_commande window = new update_commande();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public update_commande() throws SQLException {
		initialize();
		Connect();
		count_load();
		count_load_termine();
		count_load_encours();
		count_load_commande();
		table_load();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	//Initialisation des textbox
	private JTextField txtquantite;
	private JTextField txtprix;

	
	//Connexion a la base de donnee.
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root", "");
		} catch (ClassNotFoundException ex) {

		} catch (SQLException ex) {

		}
	}

	// Compteur pour toutes les commandes
	
	public void count_load() {
		count = 0;
		try {
			pst = con.prepareStatement("select * from vendeur_commande");
			rs = pst.executeQuery();

			while (rs.next()) {
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Nombre de commande termine

	public void count_load_termine() {
		count1 = 0;
		try {
			pst = con.prepareStatement("select * from vendeur_commande where statue='Termine'");
			rs = pst.executeQuery();

			while (rs.next()) {
				count1++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Nombre de commande en cours

	public void count_load_encours() {
		count2 = 0;
		try {
			pst = con.prepareStatement("select * from vendeur_commande where statue='En cours'");
			rs = pst.executeQuery();

			while (rs.next()) {
				count2++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Nombre de commande deja passe

	public void count_load_commande() {
		count3 = 0;
		try {
			pst = con.prepareStatement("select * from vendeur_commande where statue='commande deja passe'");
			rs = pst.executeQuery();

			while (rs.next()) {
				count3++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Dropdown dynamique pour ajouter le id avec le nom et le prenom pour la table vendeur_client.
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox client(JComboBox jc) throws SQLException {

		DefaultComboBoxModel theModel = (DefaultComboBoxModel) jc.getModel();
		PreparedStatement pst = con.prepareStatement("Select * from vendeur_client");
		ResultSet rs = pst.executeQuery();
		theModel.removeAllElements();
		theModel.addElement("");
		while (rs.next()) {
			theModel.addElement(rs.getString("id") + " - " + rs.getString("prenom") + " " + rs.getString("nom"));
		}
		jc.setModel(theModel);
		return jc;
	}

	// Dropdown dynamique pour ajouter le id avec la marque et le modele pour la table voiture.
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox voiture(JComboBox jc) throws SQLException {

		DefaultComboBoxModel theModel = (DefaultComboBoxModel) jc.getModel();
		PreparedStatement pst = con.prepareStatement("Select * from voitures");
		ResultSet rs = pst.executeQuery();
		theModel.removeAllElements();
		theModel.addElement("");
		while (rs.next()) {
			theModel.addElement(rs.getString("id") + " - " + rs.getString("marque") + " " + rs.getString("modele"));
		}
		jc.setModel(theModel);
		return jc;
	}

	//// Dropdown dynamique pour ajouter le id avec le prenom et le nom d'un employee de la table rh_employee.
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox employee(JComboBox jc) throws SQLException {

		DefaultComboBoxModel theModel = (DefaultComboBoxModel) jc.getModel();
		PreparedStatement pst = con.prepareStatement("Select * from rh_employee");
		ResultSet rs = pst.executeQuery();
		theModel.removeAllElements();
		theModel.addElement("");
		while (rs.next()) {
			theModel.addElement(rs.getString("id_emp") + " - " + rs.getString("prenom") + " " + rs.getString("nom"));
		}
		jc.setModel(theModel);
		return jc;
	}

	//
	public void table_load() {
		try {
			//Selection pour l'affichage avec jointure.
			pst = con.prepareStatement(
					"select * from vendeur_commande,vendeur_client,voitures,rh_employee where vendeur_commande.id_voiture = voitures.id AND vendeur_commande.id_emp = rh_employee.id_emp AND vendeur_commande.id_client = vendeur_client.id");
			rs = pst.executeQuery();
			DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {},
					new String[] { "id", "voiture", "prix", "quantite", "employee", "client", "statue", "date" });

			//Initialisation des valeurs avec les jointure des tables
			while (rs.next()) {
				String id = rs.getString("vendeur_commande.id");
				String voiture = rs.getString("voitures.id") + " - " + rs.getString("voitures.marque") + " " + rs.getString("voitures.modele");
				String prix = rs.getString("vendeur_commande.prix");
				String quantite = rs.getString("vendeur_commande.quantite");
				String employee = rs.getString("rh_employee.id_emp") + " - " + rs.getString("rh_employee.prenom") + " " + rs.getString("rh_employee.nom");
				String client = rs.getString("vendeur_client.id") + " - " + rs.getString("vendeur_client.prenom") + " " + rs.getString("vendeur_client.nom");
				String statue = rs.getString("vendeur_commande.statue");
				String date = rs.getString("vendeur_commande.date");
				String[] data = { id, voiture, prix, quantite, employee, client, statue, date };
				tableModel.addRow(data);

			}
			table.setModel(tableModel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialise the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 1554, 820);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("MISE A JOUR COMMANDE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(659, -14, 444, 79);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(41, 163, 430, 451);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblstatue = new JLabel("Statue");
		lblstatue.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblstatue.setBounds(24, 75, 99, 33);
		panel.add(lblstatue);

		@SuppressWarnings("rawtypes")
		JComboBox dropstatue = new JComboBox();
		dropstatue.addItem("");
		dropstatue.addItem("En cours");
		dropstatue.addItem("Termine");
		dropstatue.addItem("Commande deja passe");

		dropstatue.setBounds(158, 86, 211, 21);
		panel.add(dropstatue);

		JLabel lblVoiture = new JLabel("Voiture");
		lblVoiture.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVoiture.setBounds(24, 221, 99, 33);
		panel.add(lblVoiture);

		// Dropdown pour le nom du client
		
		JComboBox dropclient = new JComboBox();
		dropclient.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					client(dropclient);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		dropclient.setBounds(158, 131, 211, 21);
		panel.add(dropclient);

		JLabel lblClient = new JLabel("Client");
		lblClient.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClient.setBounds(24, 123, 99, 33);
		panel.add(lblClient);

		// Dropdown pour le nom de l'employe
		
		JComboBox dropemploye = new JComboBox();
		dropemploye.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					employee(dropemploye);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		dropemploye.setBounds(158, 177, 211, 21);
		panel.add(dropemploye);

		JLabel lblVendeur = new JLabel("Vendeur");
		lblVendeur.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVendeur.setBounds(24, 166, 99, 33);
		panel.add(lblVendeur);

		//Dropdown pour la marque de la voiture
		
		JComboBox dropvoiture = new JComboBox();
		dropvoiture.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					voiture(dropvoiture);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		dropvoiture.setBounds(158, 229, 211, 21);
		panel.add(dropvoiture);

		txtquantite = new JTextField();
		txtquantite.setColumns(10);
		txtquantite.setBounds(158, 283, 211, 22);
		panel.add(txtquantite);

		JLabel lblQuantite = new JLabel("Quantite");
		lblQuantite.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuantite.setBounds(24, 275, 99, 33);
		panel.add(lblQuantite);

		txtprix = new JTextField();
		txtprix.setColumns(10);
		txtprix.setBounds(158, 336, 211, 22);
		panel.add(txtprix);

		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrix.setBounds(24, 328, 99, 33);
		panel.add(lblPrix);

		JButton btnExit = new JButton("Sortir");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(573, 639, 107, 50);
		frame.getContentPane().add(btnExit);

		//Pour effacer les donnees inserer.
		
		JButton btnClear = new JButton("Effacer");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtprix.setText("");
				txtquantite.setText("");
				dropstatue.setSelectedItem("");
				dropclient.setSelectedItem("");
				dropvoiture.setSelectedItem("");
				dropemploye.setSelectedItem("");

			}
		});
		btnClear.setBounds(402, 639, 107, 50);
		frame.getContentPane().add(btnClear);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(500, 51, 1030, 560);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(41, 51, 430, 110);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblBookId = new JLabel("ID");
		lblBookId.setBounds(30, 38, 60, 17);
		lblBookId.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblBookId);

		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {

				try {

					//Initialisation des variables
					
					String id = txtid.getText();
					client(dropclient);
					voiture(dropvoiture);
					employee(dropemploye);
					
					//Requete SQL avec jointure
					
					pst = con.prepareStatement(
							"select * from vendeur_commande,vendeur_client,voitures,rh_employee where vendeur_commande.id_voiture = voitures.id AND vendeur_commande.id_emp = rh_employee.id_emp AND vendeur_commande.id_client = vendeur_client.id AND vendeur_commande.id=?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();

					if (rs.next() == true) {
						
						//Concatenation des donnee de differente tables.
						String voiture = rs.getString("voitures.id") + " - " + rs.getString("voitures.marque") + " " + rs.getString("voitures.modele");
						String prix = rs.getString("vendeur_commande.prix");
						String quantite = rs.getString("vendeur_commande.quantite");
						String employee = rs.getString("rh_employee.id_emp") + " - " + rs.getString("rh_employee.prenom") + " " + rs.getString("rh_employee.nom");
						String client = rs.getString("vendeur_client.id") + " = " + rs.getString("vendeur_client.prenom") + " " + rs.getString("vendeur_client.nom");
						String statue = rs.getString("vendeur_commande.statue");
						String date = rs.getString("vendeur_commande.date");

						txtprix.setText(prix);
						txtquantite.setText(quantite);
						dropstatue.setSelectedItem(statue);
						dropclient.setSelectedItem(client);
						dropvoiture.setSelectedItem(voiture);
						dropemploye.setSelectedItem(employee);

					} else {

						txtprix.setText("");
						txtquantite.setText("");
						dropstatue.setSelectedItem("");
						dropclient.setSelectedItem("");
						dropvoiture.setSelectedItem("");
						dropemploye.setSelectedItem("");
						txtprix.requestFocus();

					}

				}

				catch (SQLException ex) {

				}
			}
		});
		txtid.setBounds(102, 36, 278, 22);
		txtid.setColumns(10);
		panel_1.add(txtid);

		JButton btnUpdate = new JButton("Mise a jour");
		btnUpdate.addActionListener(new ActionListener() {
			private String[] statueArray;
			private String[] empArray;
			private String[] voitureArray;
			private String[] clientArray;

			public void actionPerformed(ActionEvent e) {

				String prix, quantite, id, statue, id_emp, id_voiture, id_client;

				prix = txtprix.getText();
				quantite = txtquantite.getText();
				statue = dropstatue.getSelectedItem().toString();
				statueArray = statue.split("-");
				statue = statueArray[0];
				id_emp = dropemploye.getSelectedItem().toString();
				empArray = id_emp.split("-");
				id_emp = empArray[0];
				id_voiture = dropvoiture.getSelectedItem().toString();
				voitureArray = id_voiture.split("-");
				id_voiture = voitureArray[0];
				id_client = dropclient.getSelectedItem().toString();
				clientArray = id_client.split("-");
				id_client = clientArray[0];
				id = txtid.getText();

				try {
					
					//REGEX POUR LES INSERTIONS.

					final String PRIX_REGEX = "^[0-9]{7}$";

					final Pattern PRIX_PATTERN = Pattern.compile(PRIX_REGEX);

					final String QUANTITE_REGEX = "^[0-9]$";

					final Pattern QUANTITE_PATTERN = Pattern.compile(QUANTITE_REGEX);

					if (QUANTITE_PATTERN.matcher(quantite).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du quantite n`est pas bon");
					}

					if (PRIX_PATTERN.matcher(prix).matches() == false) {
						JOptionPane.showMessageDialog(null, "L`insertion du prix n`est pas bon");
					}

					if (id_voiture.equals("")) {
						JOptionPane.showMessageDialog(null, "Aucune voiture selectionnée");
					}

					if (id_emp.equals("")) {
						JOptionPane.showMessageDialog(null, "Aucun employée selectionnée");
					}

					if (id_client.equals("")) {
						JOptionPane.showMessageDialog(null, "Aucun client selectionnée");
					}

					if (statue.equals("")) {
						JOptionPane.showMessageDialog(null, "L`insertion du statue n'est pas bon");
					}

					//Conditions pour pouvoir faire un mise a jour
					
					if (!statue.equals("") && !id_voiture.equals("") && !id_emp.equals("") && !id_client.equals("")
							&& !id.equals("") && PRIX_PATTERN.matcher(prix).matches()
							&& QUANTITE_PATTERN.matcher(quantite).matches()) {

						pst = con.prepareStatement(
								"update vendeur_commande set id_voiture=?,prix=?,quantite=?,id_emp=?,id_client=?,statue=?  where id =?");

						pst.setString(1, id_voiture);
						pst.setString(2, prix);
						pst.setString(3, quantite);
						pst.setString(4, id_emp);
						pst.setString(5, id_client);
						pst.setString(6, statue);
						pst.setString(7, id);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Mise a jour completer!");

					}
					table_load();

					txtprix.setText("");
					txtquantite.setText("");
					dropstatue.setSelectedItem("");
					dropclient.setSelectedItem("");
					dropvoiture.setSelectedItem("");
					dropemploye.setSelectedItem("");
					txtprix.requestFocus();

				}

				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(41, 639, 107, 50);
		frame.getContentPane().add(btnUpdate);

		JButton btnDelete = new JButton("Suppression");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id;
				id = txtid.getText();
				
				// Suppression d'un donnee.
				
				try {
					pst = con.prepareStatement("delete from vendeur_commande where id =?");

					pst.setString(1, id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Suppression reussi !");
					count_load();
					count_load_termine();
					count_load_encours();
					count_load_commande();
					table_load();

					txtprix.setText("");
					txtquantite.setText("");
					dropstatue.setSelectedItem("");
					dropclient.setSelectedItem("");
					dropvoiture.setSelectedItem("");
					dropemploye.setSelectedItem("");
					txtprix.requestFocus();
				}

				catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(225, 639, 107, 50);
		frame.getContentPane().add(btnDelete);

	}
}
