import java.awt.EventQueue;
import java.sql.*;

import javax.crypto.SecretKey;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author disch
 *
 */

public class affichage_fsalaire {

	private JFrame frame;
	private JTable table;
	private JLabel lblFicheSalaireTotale;
	private int count = 0;

	/**
	 * Launch the application.
	 */
	public static void ficheSalaire(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					affichage_fsalaire window = new affichage_fsalaire(login);
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
	public affichage_fsalaire(String login) {
		Connect();
		initialize(login);
		count_load();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JButton btnNewButton;

	/**
	 *  Connection avec la base de donnee
	 */

 
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
		 *  Le nombre totale de fiche salaires realisees 
		 */
		
		public void count_load() {
			count = 0;
			try {
				pst = con.prepareStatement("select * from fiche_salaire");
				rs = pst.executeQuery();

				while (rs.next()) {
					count++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			lblFicheSalaireTotale.setText("Fiche Salaire Totale: "+count);
		}
		
		/**
		 *  L'affichage de la table salaire + l'option autoincrement pour le mois d'apres pour le nombre de commission realises
		 */
		

		public void table_load() {
			try {
				pst = con.prepareStatement("select * from fiche_salaire where MONTH(date_salaire) = MONTH(CURRENT_DATE())\r\n" + 
						"AND YEAR(date_salaire) = YEAR(CURRENT_DATE())");
				rs = pst.executeQuery();

				DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {},
						new String[] { "ID", "Nom", "Prenom", "Email",
								"Compte Bancaire", "Numero ID", "salaire", "Poste", "Departement","Commission" });
				while (rs.next()) {
					String id_emp = rs.getString("id_salaire");
					String nom = rs.getString("nom");
					String prenom = rs.getString("prenom");
					String email = rs.getString("email");
					String compte_bancaire = Decrypt_Banque(rs.getString("compte_bancaire"));
					String numero_id = rs.getString("numero_id");
					String salaire = Decrypt_Banque(rs.getString("salaire"));
					String poste = rs.getString("poste");
					String departement = rs.getString("departement");
					String commission = rs.getString("commission");

					String[] data = { id_emp, nom, prenom,  email, 
							compte_bancaire, numero_id, salaire, poste, departement,commission };
					tableModel.addRow(data);

				}
				table.setModel(tableModel);
			} catch (Exception e) {
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
		frame.setBounds(100, 100, 1288, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AFFICHAGE SALAIRE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(468, 0, 326, 79);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 1248, 439);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblFicheSalaireTotale = new JLabel();
		lblFicheSalaireTotale.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblFicheSalaireTotale.setBounds(1016, 546, 242, 16);
		frame.getContentPane().add(lblFicheSalaireTotale);
		
		btnNewButton = new JButton("Sortir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous quitter la table 'Affichage salaire'?", "Warning",
						dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
				affichage_fsalaire.this.frame.setVisible(false);
				login_connection.main(login);
				}
			}
		});
		btnNewButton.setBounds(609, 545, 97, 25);
		frame.getContentPane().add(btnNewButton);
		
	
			}
}
