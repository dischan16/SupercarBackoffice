import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class chiffre_affaire {

	private JFrame frame;
	private JTextField txtdate;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private String date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chiffre_affaire window = new chiffre_affaire();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;

	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root", "");
		} catch (ClassNotFoundException ex) {

		} catch (SQLException ex) {

		}
	}

	public void table_load(String type) {
		if (type == "jour") {
			try {
				pst = con.prepareStatement("select SUM(prix * quantite) AS Total from vendeur_commande where (date) = (?)");
				pst.setString(1, date);
				rs = pst.executeQuery();
				if (!rs.isBeforeFirst()) {
					JOptionPane.showMessageDialog(null, "aucune données trouvé pour la date selectionée !");
					txtdate.setText("");
				} else {
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (type == "mois") {
			try {
				pst = con.prepareStatement(
						"select SUM(prix * quantite) AS Total from vendeur_commande where month(date) = MONTH(?) and year(date) = year(?)");
				pst.setString(1, date);
				pst.setString(2, date);
				rs = pst.executeQuery();
				if (!rs.isBeforeFirst()) {
					JOptionPane.showMessageDialog(null, "aucune données trouvé pour la date selectionée !");
					txtdate.setText("");
				} else {
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (type == "annee") {
			try {
				pst = con.prepareStatement("select SUM(prix * quantite) AS Total from vendeur_commande where year(date) = year(?)");
				pst.setString(1, date);
				rs = pst.executeQuery();
				if (!rs.isBeforeFirst()) {
					JOptionPane.showMessageDialog(null, "aucune données trouvé pour la date selectionée !");
					txtdate.setText("");
				} else {
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the application.
	 */
	public chiffre_affaire() {
		Connect();
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 749, 731);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblDate = new JLabel("Calcul du Chiffre d'affaires");
		lblDate.setBounds(229, 22, 331, 25);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblDate);

		txtdate = new JTextField();
		txtdate.setHorizontalAlignment(SwingConstants.CENTER);

		txtdate.setBounds(229, 158, 216, 25);
		panel.add(txtdate);
		txtdate.setColumns(10);

		JButton btnSend = new JButton("Afficher");
		btnSend.setBounds(229, 195, 216, 21);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				date = txtdate.getText();
				table_load(comboBox.getSelectedItem().toString());
			}
		});
		panel.add(btnSend);

		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(296, 256, 84, 44);
		panel.add(scrollPane);

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);

		comboBox = new JComboBox();
		comboBox.setBounds(265, 97, 141, 21);
		panel.add(comboBox);
		comboBox.addItem("jour");
		comboBox.addItem("mois");
		comboBox.addItem("annee");

		JLabel lblEntrezUneDate = new JLabel("Entrez une date (aaaa-mm-jj)");
		lblEntrezUneDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrezUneDate.setBounds(229, 130, 216, 16);
		panel.add(lblEntrezUneDate);
		
		JLabel lblFaitesVotreChoix = new JLabel("Faites votre choix");
		lblFaitesVotreChoix.setHorizontalAlignment(SwingConstants.CENTER);
		lblFaitesVotreChoix.setBounds(229, 69, 216, 16);
		panel.add(lblFaitesVotreChoix);
		
		JLabel lblLeChiffreDaffaire = new JLabel("Le chiffre d'affaire est : ");
		lblLeChiffreDaffaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeChiffreDaffaire.setBounds(110, 273, 216, 16);
		panel.add(lblLeChiffreDaffaire);
	}
}
