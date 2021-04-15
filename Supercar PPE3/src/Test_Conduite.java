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
import javax.swing.JRadioButton;

public class Test_Conduite {                

	private JFrame frame;
	private JTextField txtnom;
	private JTextField txtprenom;
	private JTable table;
	private JTextField txtid_test;
   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test_Conduite window = new Test_Conduite();
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
	public Test_Conduite() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txttelephone;
	private JTextField txtemail;
	private JTextField textvoiture;
	private JTextField textdate;
	private JRadioButton rdmonsieur_1;
	private JRadioButton rdmadame_1;
	 
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
		    pst = con.prepareStatement("select * from test_conduite");
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1384, 893);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Test Conduite");
		lblNewLabel.setBounds(497, -14, 243, 79);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 168, 411, 509);
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
		
		textvoiture = new JTextField();
		textvoiture.setColumns(10);
		textvoiture.setBounds(160, 303, 211, 27);
		panel.add(textvoiture);
		
		JLabel lbletat = new JLabel("Etat");
		lbletat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbletat.setBounds(12, 392, 99, 33);
		panel.add(lbletat);
		
		JComboBox dropetat = new JComboBox();
		dropetat.setEditable(true);
		dropetat.setBounds(160, 397, 211, 26);
		panel.add(dropetat);
		
		dropetat.addItem("En attente");
		dropetat.addItem("En cours");
		dropetat.addItem("Termine");
		
		dropetat.setSelectedItem("Selecter Etat");
		
		rdmonsieur_1 = new JRadioButton("Monsieur");
		rdmonsieur_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(rdmonsieur_1.isSelected()) {
					rdmadame_1.setSelected(false);
				}
			}
		});
		rdmonsieur_1.setBounds(158, 48, 81, 25);
		panel.add(rdmonsieur_1);
		
		rdmadame_1 = new JRadioButton("Madame");
		rdmadame_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(rdmadame_1.isSelected()) {
					rdmonsieur_1.setSelected(false);
				}
			}
		});
		rdmadame_1.setBounds(270, 48, 81, 25);
		panel.add(rdmadame_1);
		
		JButton btnNewButton = new JButton("Sauvegarder");
		btnNewButton.setBounds(12, 690, 107, 50);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{			
				String civilite = null,nom,prenom,telephone,email,voiture,date;
				Object etat;
			
				if(rdmonsieur_1.isSelected()) {
					civilite = "Monsieur";
				}
				
				if(rdmadame_1.isSelected()) {
					civilite = "Madame";
				}
				
				if (civilite.equals("Monsieur")) {
					rdmonsieur_1.setSelected(true);
				}
				else {
					rdmadame_1.setSelected(true);
				}
			
				
				nom = txtnom.getText();
				prenom = txtprenom.getText();
				telephone = txttelephone.getText();
				email = txtemail.getText();
				voiture = textvoiture.getText();
				date = textdate.getText();
				etat = dropetat.getSelectedItem();
							
				 try {
					pst = con.prepareStatement("insert into test_conduite(civilite,nom,prenom,telephone,email,voiture,date,etat)values(?,?,?,?,?,?,?,?)");
					pst.setString(1, civilite);
					pst.setString(2, nom);
					pst.setString(3, prenom);
					pst.setString(4, telephone);
					pst.setString(5, email);
					pst.setString(6, voiture);
					pst.setString(7, date);
					pst.setString(8, (String) etat);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added!");
					table_load();
					
						          
					txtnom.setText("");
					txtprenom.setText("");
					txttelephone.setText("");
					txtemail.setText("");
					textvoiture.setText("");
					textdate.setText("");
					dropetat.setSelectedItem("");
					txtnom.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Sortir");
		btnExit.setBounds(170, 768, 107, 50);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Effacer");
		btnClear.setBounds(12, 768, 107, 50);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtnom.setText("");
				txtprenom.setText("");
				txttelephone.setText("");
				txtemail.setText("");
				textvoiture.setText("");
				textdate.setText("");
				dropetat.setSelectedItem("");
				txtnom.requestFocus();
			}
		});
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(435, 168, 910, 650);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(144, 64, 1067, 69);
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

			                pst = con.prepareStatement("select civilite,nom,prenom,telephone,email,voiture,date,etat from test_conduite where id_test = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			              
			       
			                String nom = rs.getString(2);
			                String prenom = rs.getString(3);
			                String telephone = rs.getString(4);
			                String email = rs.getString(5);
			                String voiture = rs.getString(6);
			                String date = rs.getString(7);
			                Object etat = rs.getObject(8);
			                   
			                
			                txtnom.setText(nom);
			                txtprenom.setText(prenom);
			                txttelephone.setText(telephone);
			                txtemail.setText(email);
			                textvoiture.setText(voiture);
			                textdate.setText(date);
			                dropetat.setSelectedItem(etat);
			                
			               
			            }   
			            else
			            {
			            	
			            	
			            	
			            	
			            	txtnom.setText("");
			                txtprenom.setText("");
			                txttelephone.setText("");
			                txtemail.setText("");
			                textvoiture.setText("");
			                textdate.setText("");
			                dropetat.setSelectedItem(""); 
			            }

			        } 
				
				 catch (SQLException ex) {
			           
			        }
			}
		});
		txtid_test.setBounds(145, 22, 860, 22);
		txtid_test.setColumns(10);
		panel_1.add(txtid_test);
		
		JButton btnUpdate = new JButton("Modifier");
		btnUpdate.setBounds(170, 690, 107, 50);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				String civilite = null,nom,prenom,telephone,email,voiture,date,id_test;
				Object etat;
				
				if(rdmonsieur_1.isSelected()) {
					civilite = "Monsieur";
				}
				
				if(rdmadame_1.isSelected()) {
					civilite = "Madame";
				}
				
				if (civilite.equals("Monsieur")) {
					rdmonsieur_1.setSelected(true);
				}
				else {
					rdmadame_1.setSelected(true);
				}
				

				nom = txtnom.getText();
				prenom = txtprenom.getText();
				telephone = txttelephone.getText();
				email = txtemail.getText();
				voiture = textvoiture.getText();
				date = textdate.getText();
				id_test  = txtid_test.getText();
				etat = dropetat.getSelectedItem(); 			
				
				 try {
					 
					 
						pst = con.prepareStatement("update test_conduite set civilite=?,nom=?,prenom=?,telephone=?,email=?,voiture=?,date=?,etat=? where id_test =?");
						pst.setString(1, civilite);
			            pst.setString(2, nom);
			            pst.setString(3, prenom);
			            pst.setString(4, telephone);
			            pst.setString(5, email);
			            pst.setString(6, voiture);
			            pst.setString(7, date);
			            pst.setString(8, (String) etat);
						pst.setString(9, id_test);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Updated!");
			            table_load();
			           
			           
			            
						txtnom.setText("");
						txtprenom.setText("");
						txttelephone.setText("");
						txtemail.setText("");
						textvoiture.setText("");
						textdate.setText("");
						dropetat.setSelectedItem("");
						
			            txtnom.requestFocus();
					}

		            catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
		});
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.setBounds(313, 690, 107, 50);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
             String id_test;
			id_test  = txtid_test.getText();
			
			 try {
					pst = con.prepareStatement("delete from test_conduite where id_test =?");
			
		            pst.setString(1, id_test);
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Deleted!");
		            table_load();
		           
		      
		            txtnom.setText("");
		            txtprenom.setText("");
		            txttelephone.setText("");
		            txtemail.setText("");
		            textvoiture.setText("");
		            textdate.setText("");
		            
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
