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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

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
		
		textvoiture = new JTextField();
		textvoiture.setColumns(10);
		textvoiture.setBounds(160, 303, 211, 27);
		panel.add(textvoiture);
		
		JLabel lbletat = new JLabel("Etat");
		lbletat.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbletat.setBounds(12, 392, 99, 33);
		panel.add(lbletat);
		
		JComboBox dropetat = new JComboBox();
		dropetat.setBounds(160, 397, 211, 26);
		panel.add(dropetat);
		
		dropetat.addItem("En attente");
		dropetat.addItem("En cours");
		dropetat.addItem("Termine");
		
		dropetat.setSelectedItem("Selecter Etat");
		
		JComboBox dropcivil = new JComboBox();
		dropcivil.setBounds(160, 47, 211, 26);
		panel.add(dropcivil);
		
		dropcivil.addItem(" ");
		dropcivil.addItem("Monsieur");
		dropcivil.addItem("Madame");
		
		
		
		JButton btnNewButton = new JButton("Sauvegarder");
		btnNewButton.setBounds(51, 679, 135, 50);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{			
				String nom,prenom,telephone,email,voiture,date;
				Object civilite,etat;
			
				civilite = dropcivil.getSelectedItem();
				nom = txtnom.getText();
				prenom = txtprenom.getText();
				telephone = txttelephone.getText();
				email = txtemail.getText();
				voiture = textvoiture.getText();
				date = textdate.getText();
				etat = dropetat.getSelectedItem();
							
				 try {
					 
					 
					 final String NOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					 
					 final Pattern NOM_PATTERN = Pattern.compile(NOM_REGEX);
					 
					 final String PRENOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					 
					 final Pattern PRENOM_PATTERN = Pattern.compile(PRENOM_REGEX);
					 
					 final String TELEPHONE_REGEX = "^[0-9]{8}$";
					 
					 final Pattern TELEPHONE_PATTERN = Pattern.compile(TELEPHONE_REGEX);
					 
					 final String EMAIL_REGEX = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";
					 
					 final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
					 
					 final String VOITURE_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z][0-9]*)*$";
					 
					 final Pattern VOITURE_PATTERN = Pattern.compile(VOITURE_REGEX);
					 
					 final String DATE_REGEX = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";
					 
					 final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);
					 
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
					 
					 if (VOITURE_PATTERN.matcher(voiture).matches() == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion de la voiture n`est pas bon");
					 }
					 
					
					 if (DATE_PATTERN.matcher(date).matches() == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion de la date n`est pas bon");
					 }
					 
					 if (NOM_PATTERN.matcher(nom).matches() && PRENOM_PATTERN.matcher(prenom).matches()
						 && TELEPHONE_PATTERN.matcher(telephone).matches() && EMAIL_PATTERN.matcher(email).matches()
						 && VOITURE_PATTERN.matcher(voiture).matches() && DATE_PATTERN.matcher(date).matches())
					 {
					 
					pst = con.prepareStatement("insert into test_conduite(civilite,nom,prenom,telephone,email,voiture,date,etat)values(?,?,?,?,?,?,?,?)");
					pst.setString(1, (String) civilite);
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
					
					dropcivil.setSelectedItem("");	          
					txtnom.setText("");
					txtprenom.setText("");
					txttelephone.setText("");
					txtemail.setText("");
					textvoiture.setText("");
					textdate.setText("");
					dropetat.setSelectedItem("");
					txtnom.requestFocus();
				   }
				 }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Sortir");
		btnExit.setBounds(851, 679, 122, 50);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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
				textvoiture.setText("");
				textdate.setText("");
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

			                pst = con.prepareStatement("select civilite,nom,prenom,telephone,email,voiture,date,etat from test_conduite where id_test = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			              
			            	Object civilite = rs.getObject(1);
			                String nom = rs.getString(2);
			                String prenom = rs.getString(3);
			                String telephone = rs.getString(4);
			                String email = rs.getString(5);
			                String voiture = rs.getString(6);
			                String date = rs.getString(7);
			                Object etat = rs.getObject(8);
			                   
			                dropcivil.setSelectedItem(civilite);
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
			            	
			            	
			            	
			            	dropcivil.setSelectedItem("");
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
		txtid_test.setBounds(145, 22, 1154, 22);
		txtid_test.setColumns(10);
		panel_1.add(txtid_test);
		
		JButton btnUpdate = new JButton("Modifier");
		btnUpdate.setBounds(242, 679, 128, 50);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				String nom,prenom,telephone,email,voiture,date,id_test;
				Object etat,civilite;
				
		
				civilite = dropcivil.getSelectedItem();
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
						pst.setString(1, (String) civilite);
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
			           
			           
			            dropcivil.setSelectedItem("");
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
		btnDelete.setBounds(435, 679, 135, 50);
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
