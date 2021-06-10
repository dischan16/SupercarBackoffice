import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class table_commande {

	private JFrame frame;
	private JTable table;
	private JLabel lblNoCommande;
	private JLabel lblNoVente;
	private JLabel lblNoCommandeEn;
	private JLabel lblTotalCommandeDeja;
	private int count = 0;
	private int count1 = 0;
	private int count2 = 0;
	private int count3 = 0;

	/**
	 * Launch the application.
	 */
	public static void table_commande(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					table_commande window = new table_commande(login);
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
	public table_commande(String login) {
		initialize(login);
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
	private JButton btnSortir;

	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/backoffice", "root", "");
		} catch (ClassNotFoundException ex) {

		} catch (SQLException ex) {

		}
	}

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
		lblNoCommande.setText("Total commande : " + count);
	}

	// nombre de commande termine

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
		lblNoVente.setText("Total vente conclus : " + count1);
	}

	// nombre de commande en cours

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
		lblNoCommandeEn.setText("Total commande en cours : " + count2);
	}

	// commande deja passe

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
		lblTotalCommandeDeja.setText("Total commande deja passe : " + count3);
	}

	public void table_load() {
		try {
			pst = con.prepareStatement(
					"select * from vendeur_commande,vendeur_client,voitures,rh_employee where vendeur_commande.id_voiture = voitures.id AND vendeur_commande.id_emp = rh_employee.id_emp AND vendeur_commande.id_client = vendeur_client.id");
			rs = pst.executeQuery();
			DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {},
					new String[] { "id", "voiture", "prix", "quantite", "employee", "client", "statue", "date"});
			
			while (rs.next()) {
				String id = rs.getString("vendeur_commande.id");
				String voiture = rs.getString("voitures.id") + " - " + rs.getString("voitures.marque") + " " + rs.getString("voitures.modele") ;
				String prix = rs.getString("vendeur_commande.prix");
				String quantite = rs.getString("vendeur_commande.quantite");
				String employee =  rs.getString("rh_employee.id_emp") + " - " + rs.getString("rh_employee.prenom") + " " + rs.getString("rh_employee.nom") ;
				String client =  rs.getString("vendeur_client.id") + " = " +rs.getString("vendeur_client.prenom") + " " + rs.getString("vendeur_client.nom") ;
				String statue = rs.getString("vendeur_commande.statue");
				String date = rs.getString("vendeur_commande.date");
				String[] data = { id, voiture, prix, quantite, employee, client, statue, date};
				tableModel.addRow(data);

			}
			table.setModel(tableModel);
		} catch (SQLException e) {
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
		frame.setBounds(100, 100, 1206, 705);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("COMMANDE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(552, 10, 229, 79);
		frame.getContentPane().add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 1166, 445);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		lblNoCommandeEn = new JLabel();
		lblNoCommandeEn.setText("Total commande en cours :0");
		lblNoCommandeEn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNoCommandeEn.setBounds(10, 540, 252, 31);
		frame.getContentPane().add(lblNoCommandeEn);

		lblTotalCommandeDeja = new JLabel();
		lblTotalCommandeDeja.setText("Total commande deja passe :0");
		lblTotalCommandeDeja.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTotalCommandeDeja.setBounds(595, 540, 252, 31);
		frame.getContentPane().add(lblTotalCommandeDeja);

		lblNoVente = new JLabel();
		lblNoVente.setText("Total vente :0");
		lblNoVente.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNoVente.setBounds(924, 540, 252, 31);
		frame.getContentPane().add(lblNoVente);

		lblNoCommande = new JLabel();
		lblNoCommande.setText("Total commande : 0");
		lblNoCommande.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNoCommande.setBounds(333, 541, 252, 31);
		frame.getContentPane().add(lblNoCommande);

		btnSortir = new JButton("Sortir");
		btnSortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous quitter la table 'Commande'?",
						"Warning", dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					table_commande.this.frame.setVisible(false);
					login_connection.main(login);
				}
			}
		});
		btnSortir.setBounds(595, 584, 97, 25);
		frame.getContentPane().add(btnSortir);
	}
}
