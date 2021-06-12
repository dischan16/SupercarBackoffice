import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

/**
 * 
 * @author disch
 *
 */


public class departement {

	private JFrame frame;
	private JTable table;
	private JTextField txtid_dept;

	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					departement window = new departement(login);
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
	public departement(String login) {
		initialize(login);
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	// Connection base de donnee	
 
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
	 
	  public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select * from rh_departement");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
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
		AdminAccount account = new AdminAccount();
		account.DatabaseConnexion(login, null, null, frame);
		System.out.print(account.getId());
		frame = new JFrame();
		frame.setBounds(100, 100, 1384, 893);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Departement");
		lblNewLabel.setBounds(526, 13, 282, 79);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 222, 454, 403);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblEdition = new JLabel("Adresse_Departement");
		lblEdition.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEdition.setBounds(12, 129, 166, 33);
		panel.add(lblEdition);
		
		JLabel lblNom_Departement = new JLabel("Nom_Departement");
		lblNom_Departement.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNom_Departement.setBounds(12, 63, 166, 33);
		panel.add(lblNom_Departement);
		
		JComboBox<String> dropnom = new JComboBox<String>();
		dropnom.setBounds(214, 69, 209, 27);
		panel.add(dropnom);
		
		dropnom.addItem("");
		dropnom.addItem("Administration");
		dropnom.addItem("Comptabilite");
		dropnom.addItem("Vente	");
		dropnom.addItem("RH");
		
		JComboBox<String> dropadd = new JComboBox<String>();
		dropadd.setBounds(214, 135, 209, 27);
		panel.add(dropadd);
		
		dropadd.addItem("");
		dropadd.addItem("Siege Social");		
		dropadd.addItem("Port Louis");
		dropadd.addItem("Baie du Tombeau");
		dropadd.addItem("Phoenix");
		dropadd.addItem("Plaisance");
		
		// Sauvegarder les donnees
		
		JButton btnNewButton = new JButton("Sauvegarder");
		btnNewButton.setBounds(40, 645, 107, 50);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{			
				
				// Controle de saisie sur les champs
				
				String nom_dept,adresse_dept;
				
				nom_dept = dropnom.getSelectedItem().toString();
				adresse_dept = dropadd.getSelectedItem().toString();
				
				if (nom_dept.equals("")) {
					JOptionPane.showMessageDialog(null, "L`insertion du nom invalide");
				}

				if (adresse_dept.equals("")) {
					JOptionPane.showMessageDialog(null, "L`insertion de l'adresse invalide");
				}

							
				 try {
					 
				if (!nom_dept.equals("") && !adresse_dept.equals("") && account.getAccountType().contains("RH")) {
					pst = con.prepareStatement("insert into rh_departement(nom_dept,adresse_dept)values(?,?)");
					pst.setString(1, nom_dept);
					pst.setString(2, adresse_dept);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Departement Ajouter!");
					table_load();
					
						           
					dropnom.setSelectedItem("");
					dropadd.setSelectedItem("");
				}
				else {
					JOptionPane.showMessageDialog(null, "Vous n'avez pas les privileges !");
				}
				

				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Sortie");
		btnExit.setBounds(193, 740, 107, 50);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous quitter la page 'Departement'?", "Warning",
						dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
				departement.this.frame.setVisible(false);
				login_connection.main(login);
				}
			}
		});
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Effacer");
		btnClear.setBounds(40, 740, 107, 50);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dropnom.setSelectedItem("");
				dropadd.setSelectedItem("");
				
			}
		});
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(523, 230, 810, 560);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(210, 80, 883, 110);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lbldepartment = new JLabel("Department ID");
		lbldepartment.setBounds(28, 52, 112, 17);
		lbldepartment.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lbldepartment);
		
		txtid_dept = new JTextField();
		txtid_dept.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 try {
					 
					 	// La bar de recherche au niveau ID	
			          
			            String id = txtid_dept.getText();

			                pst = con.prepareStatement("select nom_dept,adresse_dept from rh_departement where id_dept = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			              
			                String nom_dept = rs.getString(1);
			                String adresse_dept = rs.getString(2);
		
			                
			                dropnom.setSelectedItem(nom_dept);
			                dropadd.setSelectedItem(adresse_dept);
			        
			                
			            }   
			            else
			            {
			            	 dropnom.setSelectedItem("");
				             dropadd.setSelectedItem("");
			                 
			            }

			        } 
				
				 catch (SQLException ex) {
			           
			        }
			}
		});
		txtid_dept.setBounds(172, 50, 663, 22);
		txtid_dept.setColumns(10);
		panel_1.add(txtid_dept);
		
		
		
		JButton btnUpdate = new JButton("Modifier");
		btnUpdate.setBounds(193, 645, 107, 50);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id_dept;
				String nom_dept,adresse_dept;
				
				nom_dept = dropnom.getSelectedItem().toString();
				adresse_dept = dropadd.getSelectedItem().toString();
				id_dept  = txtid_dept.getText();
				
				 try {
					 	
					 if (account.getAccountType().contains("RH")) {
					 	
						pst = con.prepareStatement("update rh_departement set nom_dept= ?,adresse_dept=? where id_dept =?");
						pst.setString(1,  nom_dept);
			            pst.setString(2,  adresse_dept);
			            pst.setString(3, id_dept);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Departement Modifier!");
			            table_load();
			           
			            dropnom.setSelectedItem("");
						dropadd.setSelectedItem("");

					}else{
						JOptionPane.showMessageDialog(null, "Vous n'avez pas les privileges !");
					}

				 }
		            catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
		});
		frame.getContentPane().add(btnUpdate);
		
		// L'Option Supprimer des donnees 
		
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.setBounds(353, 645, 107, 50); 
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                String id_dept;
			id_dept  = txtid_dept.getText();
			
			 try {  
				 
				 if (account.getAccountType().contains("RH")) {
				 
					pst = con.prepareStatement("delete from rh_departement where id_dept =?");
			
		            pst.setString(1, id_dept);
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Departement Supprimer!");
		            table_load();
		         

				}
				 else{
						JOptionPane.showMessageDialog(null, "Vous n'avez pas les privileges !");
				}
			 }
		        catch (SQLException e1) {
					
					e1.printStackTrace();
				}
					}
				});
				frame.getContentPane().add(btnDelete);
			}
}
