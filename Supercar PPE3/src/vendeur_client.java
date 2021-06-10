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

public class vendeur_client {

	private JFrame frame;
	private JTextField txtnom;
	private JTextField txtprenom;
	private JTextField txtadresse;
	private JTextField txtemail;
	private JTextField txttelephone;
	private JTextField txtnumid; 
	private JTable table;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vendeur_client window = new vendeur_client(login);
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
	public vendeur_client(String login) {
		initialize(login);
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
	 
	  public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select * from vendeur_client");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
	    	} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
	    	 } 
	    }
	  
	  //Junit test
	  
	  public static String testNom(String nom) {
		  final String NOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
		  final Pattern NOM_PATTERN = Pattern.compile(NOM_REGEX);

		  if (NOM_PATTERN.matcher(nom).matches() == false) {
		  JOptionPane.showMessageDialog(null, "L`insertion du nom n`est pas bon");
		  }

		  return nom;
		  }

		  public static String testPrenom(String prenom) {
		  return prenom;
		  }
	
	 

	/**
	 * Initialise the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize(String login) {
		AdminAccount account = new AdminAccount();
		account.DatabaseConnexion(login, null, null, frame);
		System.out.print(account.getId());
		frame = new JFrame();
		frame.setBounds(100, 100, 1206, 591);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CLIENT");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(537, -11, 229, 79);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(24, 204, 430, 258);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblnom = new JLabel("Nom");
		lblnom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblnom.setBounds(24, 19, 99, 33);
		panel.add(lblnom);
		
		JLabel lblprenom = new JLabel("Prenom");
		lblprenom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblprenom.setBounds(24, 51, 99, 33);
		panel.add(lblprenom);
		
		txtnom = new JTextField();
		txtnom.setBounds(160, 27, 211, 22);
		panel.add(txtnom);
		txtnom.setColumns(10);
		
		txtprenom = new JTextField();
		txtprenom.setColumns(10);
		txtprenom.setBounds(160, 59, 211, 22);
		panel.add(txtprenom);
		
		JLabel Lblsex = new JLabel("Sex");
		Lblsex.setFont(new Font("Tahoma", Font.BOLD, 14));
		Lblsex.setBounds(24, 83, 99, 33);
		panel.add(Lblsex);
		
		JLabel lbladresse = new JLabel("Adresse");
		lbladresse.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbladresse.setBounds(24, 115, 99, 33);
		panel.add(lbladresse);
		
		txtadresse = new JTextField();
		txtadresse.setColumns(10);
		txtadresse.setBounds(160, 123, 211, 22);
		panel.add(txtadresse);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblemail.setBounds(24, 147, 99, 33);
		panel.add(lblemail);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(160, 155, 211, 22);
		panel.add(txtemail);
		
		JLabel lbltelephone = new JLabel("Telephone");
		lbltelephone.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltelephone.setBounds(24, 179, 99, 33);
		panel.add(lbltelephone);
		
		JLabel lblnic = new JLabel("Num id");
		lblnic.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblnic.setBounds(24, 211, 99, 33);
		panel.add(lblnic);
		
		txtnumid = new JTextField();
		txtnumid.setColumns(10);
		txtnumid.setBounds(160, 219, 211, 22);
		panel.add(txtnumid);
		
		txttelephone = new JTextField();
		txttelephone.setColumns(10);
		txttelephone.setBounds(160, 187, 211, 22);
		panel.add(txttelephone);
		
		JComboBox dropsex = new JComboBox();
		dropsex.setBounds(160, 91, 211, 21);
		panel.add(dropsex);
		
		dropsex.addItem("");
		dropsex.addItem("Homme");
		dropsex.addItem("Femme");
		
		JButton btnNewButton = new JButton("Creation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{			
				String nom,prenom,adresse,email,numero_telephone,num_id,sex;
				nom = txtnom.getText();
				prenom = txtprenom.getText();
				sex = dropsex.getSelectedItem().toString();
				adresse = txtadresse.getText();
				email = txtemail.getText();
				numero_telephone = txttelephone.getText();
				num_id = txtnumid.getText();
				
					 
					 try {
						 
						 	final String NOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
						 
						    final Pattern NOM_PATTERN = Pattern.compile(NOM_REGEX);
						    
						    final String PRENOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
						 
						    final Pattern PRENOM_PATTERN = Pattern.compile(PRENOM_REGEX);
						    
						    final String ADRESSE_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z][0-9]*)*$";
						    
						    final Pattern ADRESSE_PATTERN = Pattern.compile(ADRESSE_REGEX);
						    
						    final String EMAIL_REGEX = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";
						    
						    final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
						    
						    final String NUMERO_TELEPHONE_REGEX = "^[0-9]{8}$";
							 
						    final Pattern NUMERO_TELEPHONE_PATTERN = Pattern.compile(NUMERO_TELEPHONE_REGEX);
						    
						    final String NUM_ID_REGEX = "^[A-Z]+[0-9]{12}+[A-Z]$";
							 
						    final Pattern NUM_ID_PATTERN = Pattern.compile(NUM_ID_REGEX);
						    
						   
						    
						    if (NOM_PATTERN.matcher(nom).matches() == false) {
						    	JOptionPane.showMessageDialog(null, "L`insertion du nom n`est pas bon");
						    }
						    
						    if(PRENOM_PATTERN.matcher(prenom).matches()  == false) {
						    	JOptionPane.showMessageDialog(null, "L`insertion du prenom n`est pas bon");
						    }
						    
						    if (sex.equals("")) {
						    	JOptionPane.showMessageDialog(null, "L`insertion du sex n'est pas bon");
						    }
						    
						    if( ADRESSE_PATTERN.matcher(adresse).matches()  == false) {
						    	JOptionPane.showMessageDialog(null, "L`insertion de l'adresse n`est pas bon");
						    }
						    
						    if( EMAIL_PATTERN.matcher(email).matches() == false) {
						    	JOptionPane.showMessageDialog(null, "L`insertion de l'email n`est pas bon");
						    }
						    
						    if( NUMERO_TELEPHONE_PATTERN.matcher(numero_telephone).matches()  == false) {
						    	JOptionPane.showMessageDialog(null, "L`insertion du numero telephone n`est pas bon");
						    }
						    
						    if( NUM_ID_PATTERN.matcher(num_id).matches()  == false) {
						    	JOptionPane.showMessageDialog(null, "L`insertion du NIC n`est pas bon");
						    }
						   
							
							if (NOM_PATTERN.matcher(nom).matches()&&
									PRENOM_PATTERN.matcher(prenom).matches() && !sex.equals("") &&
					                ADRESSE_PATTERN.matcher(adresse).matches()&&
					                EMAIL_PATTERN.matcher(email).matches()&&
					                NUMERO_TELEPHONE_PATTERN.matcher(numero_telephone).matches() && 
					                NUM_ID_PATTERN.matcher(num_id).matches()) 
							{
								
						 
						 
						pst = con.prepareStatement("insert into vendeur_client(nom,prenom,sex,adresse,email,numero_telephone,num_id)values(?,?,?,?,?,?,?)");
						pst.setString(1, nom);
						pst.setString(2, prenom);
						pst.setString(3, sex);
						pst.setString(4, adresse);
						pst.setString(5, email);
						pst.setString(6, numero_telephone);
						pst.setString(7, num_id);
						pst.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
							}	
							
						table_load();
					
						           
					txtnom.setText("");
					txtprenom.setText("");
					dropsex.setSelectedItem("");
					txtadresse.setText("");
					txtemail.setText("");
					txttelephone.setText("");
					txtnumid.setText("");
					txtnom.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
			        }
			}
		});
		btnNewButton.setBounds(121, 472, 107, 50);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Sortir");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous quitter la page 'Client'?", "Warning",
						dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
				vendeur_client.this.frame.setVisible(false);
				login_connection.main(login);
				}
			}
		});
		btnExit.setBounds(1069, 472, 107, 50);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Effacer");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtnom.setText("");
				txtprenom.setText("");
				dropsex.setSelectedItem("");
				txtadresse.setText("");
				txtemail.setText("");
				txttelephone.setText("");
				txtnumid.setText(""); 
				txtnom.requestFocus();
			}
		});
		btnClear.setBounds(294, 472, 107, 50);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(494, 85, 676, 377);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(24, 78, 430, 122);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBookId = new JLabel("ID");
		lblBookId.setBounds(27, 24, 60, 17);
		lblBookId.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblBookId);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 try {
			          
			            String id = txtid.getText();

			                pst = con.prepareStatement("select nom,prenom,sex,adresse,email,numero_telephone,num_id from vendeur_client where id = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			              
			                String nom = rs.getString(1);
			                String prenom = rs.getString(2);
			                String sex = rs.getString(3);
			                String adresse = rs.getString(4);
			                String email = rs.getString(5);
			                String numero_telephone = rs.getString(6);
			                String num_id = rs.getString(7);
			                
			                txtnom.setText(nom);
			                txtprenom.setText(prenom);
			                dropsex.setSelectedItem(sex);
			                txtadresse.setText(adresse);
			                txtemail.setText(email);
			                txttelephone.setText(numero_telephone);
			                txtnumid.setText(num_id);
			                
			            }   
			            else
			            {
			            	txtnom.setText("");
			            	txtprenom.setText("");
			                dropsex.setSelectedItem("");
			                txtadresse.setText("");
			            	txtemail.setText("");
			                txttelephone.setText("");
			                txtnumid.setText("");
			            }

			        } 
				
				 catch (SQLException ex) {
			           
			        }
			}
		});
		txtid.setBounds(102, 24, 278, 22);
		txtid.setColumns(10);
		panel_1.add(txtid);
		
		JButton btnUpdate = new JButton("Mise a jour");
		btnUpdate.setBounds(98, 56, 107, 50);
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Suppression");
		btnDelete.setBounds(269, 56, 107, 50);
		panel_1.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                String id;
			id  = txtid.getText();
			
			 try {
					pst = con.prepareStatement("delete from vendeur_client where id =?");
			
		            pst.setString(1, id);
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Deleted!");
		            table_load();
		           
		            txtnom.setText("");
		            txtprenom.setText("");
		            txtadresse.setText("");
		            txtemail.setText("");
		            txttelephone.setText("");
		            txtnumid.setText("");
		            txtnom.requestFocus();
				}
		
		        catch (SQLException e1) {
					
					e1.printStackTrace();
				}
					}
				});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom,prenom,adresse,email,numero_telephone,num_id,id, sex;
				
				nom = txtnom.getText();
				prenom = txtprenom.getText();
				sex = dropsex.getSelectedItem().toString();
				adresse = txtadresse.getText();
				email = txtemail.getText();
				numero_telephone = txttelephone.getText();
				num_id = txtnumid.getText();
				id  = txtid.getText();
				
				 try {
					 
					 final String NOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					 
					    final Pattern NOM_PATTERN = Pattern.compile(NOM_REGEX);
					    
					    final String PRENOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					 
					    final Pattern PRENOM_PATTERN = Pattern.compile(PRENOM_REGEX);
					    
					    final String ADRESSE_REGEX = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z][0-9]*)*$";
					    
					    final Pattern ADRESSE_PATTERN = Pattern.compile(ADRESSE_REGEX);
					    
					    final String EMAIL_REGEX = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";
					    
					    final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
					    
					    final String NUMERO_TELEPHONE_REGEX = "^[0-9]{8}$";
						 
					    final Pattern NUMERO_TELEPHONE_PATTERN = Pattern.compile(NUMERO_TELEPHONE_REGEX);
					    
					    final String NUM_ID_REGEX = "^[A-Z]+[0-9]{12}+[A-Z]$";
						 
					    final Pattern NUM_ID_PATTERN = Pattern.compile(NUM_ID_REGEX);
					    
					   
					    
					    if (NOM_PATTERN.matcher(nom).matches() == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion du nom n`est pas bon");
					    }
					    
					    if(PRENOM_PATTERN.matcher(prenom).matches()  == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion du prenom n`est pas bon");
					    }
					    
					    if (sex.equals("")) {
					    	JOptionPane.showMessageDialog(null, "L`insertion du sex n'est pas bon");
					    }
					    
					    if( ADRESSE_PATTERN.matcher(adresse).matches()  == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion de l'adresse n`est pas bon");
					    }
					    
					    if( EMAIL_PATTERN.matcher(email).matches() == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion de l'email n`est pas bon");
					    }
					    
					    if( NUMERO_TELEPHONE_PATTERN.matcher(numero_telephone).matches()  == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion du numero telephone n`est pas bon");
					    }
					    
					    if( NUM_ID_PATTERN.matcher(num_id).matches()  == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion du NIC n`est pas bon");
					    }
					 
					 	if (NOM_PATTERN.matcher(nom).matches()&&
								PRENOM_PATTERN.matcher(prenom).matches() && !sex.equals("") &&
				                ADRESSE_PATTERN.matcher(adresse).matches()&&
				                EMAIL_PATTERN.matcher(email).matches()&&
				                NUMERO_TELEPHONE_PATTERN.matcher(numero_telephone).matches() && 
				                NUM_ID_PATTERN.matcher(num_id).matches()) 
						{
						pst = con.prepareStatement("update vendeur_client set nom= ?,prenom=?,sex=?,adresse= ?,email=?,numero_telephone=?,num_id=? where id =?");
						pst.setString(1, nom);
			            pst.setString(2, prenom);
			            pst.setObject(3, sex);
			            pst.setString(4, adresse);
			            pst.setString(5, email);
			            pst.setString(6, numero_telephone);
			            pst.setString(7, num_id);
			            pst.setString(8, id);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Mise a jour completer!");
						}
			            table_load();
			           
			            txtnom.setText("");
			            txtprenom.setText("");
			            dropsex.setSelectedItem("");
			            txtadresse.setText("");
			            txtemail.setText("");
			            txttelephone.setText("");
			            txtnumid.setText("");
			            txtnom.requestFocus();
					}

		            catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
		});
	}
}

