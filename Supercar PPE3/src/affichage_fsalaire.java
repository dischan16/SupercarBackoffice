import java.awt.EventQueue;
import java.sql.*;

import javax.crypto.SecretKey;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class affichage_fsalaire {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void ficheSalaire() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					affichage_fsalaire window = new affichage_fsalaire();
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
	public affichage_fsalaire() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;


 
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
				pst = con.prepareStatement("select * from fiche_salaire");
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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1288, 593);
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
		
	
			}
}
