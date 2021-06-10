import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Haashim Potyram
 *
 */

public class Administration {
	
	private AdminAccount account = new AdminAccount();
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
					Administration window = new Administration(login);
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
	public Administration(String login) {
		initialize(login);
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect() {
		
		/**
		 * 
		 */
		
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
	
	public void table_load()

    {

             try

             {

                 pst = con.prepareStatement("select * from voitures");

                 rs = pst.executeQuery();
                 
                 DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {},
       					new String[] { "Reference","Marque", "Model","Puissance", "Transmission", "Carburant","Couleur", "Pays", "Option_Voitures", "Entrepot","Prix","Quantite",
       							"Devis"});
       			

       				while (rs.next()) {
       				String reference = rs.getString("reference");
       				String marque = rs.getString("marque");
       				String model = rs.getString("model");
       				String puissance = rs.getString("puissance");
       				String transmission = rs.getString("transmission");
       				String carburant = rs.getString("carburant");
       				String couleur = rs.getString("couleur");
       				String pays = rs.getString("pays");
       				String option_voiture = rs.getString("option_voiture");
       				String entrepot = rs.getString("entrepot");
       				String prix = rs.getString("prix");
       				String quantite = rs.getString("quantite");
       				String devis = rs.getString("devis");

       				String[] data = { reference, marque, model, puissance, transmission, carburant,couleur, pays, option_voiture, entrepot,prix,quantite,
       						devis };
       				tableModel.addRow(data);


              }
       				table.setModel(tableModel);
       				
       				table.getColumnModel().getColumn(0).setPreferredWidth(75); // Reference
       				table.getColumnModel().getColumn(1).setPreferredWidth(100); //Marque
       				table.getColumnModel().getColumn(2).setPreferredWidth(80);  // Model
       				table.getColumnModel().getColumn(3).setPreferredWidth(100);  // Puissance
       				table.getColumnModel().getColumn(4).setPreferredWidth(150); // Transmission
       				table.getColumnModel().getColumn(5).setPreferredWidth(100); // Carburant
       				table.getColumnModel().getColumn(6).setPreferredWidth(100);  // Couleur
       				table.getColumnModel().getColumn(7).setPreferredWidth(100); // Pays
       				table.getColumnModel().getColumn(8).setPreferredWidth(150); // Option_Voitures
       				table.getColumnModel().getColumn(9).setPreferredWidth(100); // Entrepot
       				table.getColumnModel().getColumn(10).setPreferredWidth(100);  // Prix
       				table.getColumnModel().getColumn(11).setPreferredWidth(75); // Quantite
       				table.getColumnModel().getColumn(12).setPreferredWidth(100); // Devis
       				
       				table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);


             }

             catch (SQLException e)

              {

                           e.printStackTrace();

               }

    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String login) {
		account.DatabaseConnexion(login, null, null, frame);
		System.out.print(account.getId());
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 650);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblVoitures = new JLabel("Voitures");
		lblVoitures.setForeground(Color.RED);
		lblVoitures.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoitures.setFont(new Font("Arial Black", Font.BOLD, 33));
		lblVoitures.setBounds(423, 10, 234, 61);
		frame.getContentPane().add(lblVoitures);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 88, 977, 388);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(91, 499, 363, 98);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblReference = new JLabel("Reference :");
		lblReference.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblReference.setBounds(10, 37, 107, 31);
		panel.add(lblReference);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
					String reference = txtid.getText();
					
					 pst = con.prepareStatement("select marque,model,moteur,puissance,transmission,carburant,couleur,pays,option_voiture,entrepot,prix,quantite,devis from voitures where reference = ?");
		             pst.setString(1, reference);
	                 ResultSet rs = pst.executeQuery();
	                 
	                 table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
                 catch (SQLException ex) {
					
				}
				
			}
		});
		txtid.setBounds(138, 37, 117, 31);
		panel.add(txtid);
		txtid.setColumns(10);
		
		JButton btnCommande = new JButton("Commande");
		btnCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Commande c = new Commande(login);
				c.main(login);
			}
		});
		btnCommande.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCommande.setBounds(560, 524, 163, 41);
		frame.getContentPane().add(btnCommande);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous quitter la page 'Administration'?",
						"Warning", dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					Administration.this.frame.setVisible(false);
					login_connection.main(login);
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setBounds(771, 523, 112, 41);
		frame.getContentPane().add(btnExit);
	}
}
