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

public class employee {

	private JFrame frame;
	private JTextField txtcivilite;
	private JTextField txtnom;
	private JTextField txtprenom;
	private JTable table;
	private JTextField txtid_emp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employee window = new employee();
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
	public employee() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtage;
	private JTextField txttelephone;
	private JTextField txtadresse;
	private JTextField txtemail;
	private JTextField txtlicense;
	private JTextField txtcompte_bancaire;
	private JTextField txtnumero_id;
	private JTextField txtsalaire;
	private JTextField txtposte;
	private JTextField txtdepartement;

 
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
		    pst = con.prepareStatement("select * from rh_employee");
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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1384, 893);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee");
		lblNewLabel.setBounds(546, -14, 194, 79);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 168, 411, 665);
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
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAge.setBounds(12, 189, 99, 33);
		panel.add(lblAge);
		
		txtcivilite = new JTextField();
		txtcivilite.setBounds(160, 47, 211, 27);
		panel.add(txtcivilite);
		txtcivilite.setColumns(10);
		
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
		lblTelephone.setBounds(12, 235, 99, 33);
		panel.add(lblTelephone);
		
		txtage = new JTextField();
		txtage.setColumns(10);
		txtage.setBounds(160, 194, 211, 27);
		panel.add(txtage);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdresse.setBounds(12, 281, 99, 33);
		panel.add(lblAdresse);
		
		txttelephone = new JTextField();
		txttelephone.setColumns(10);
		txttelephone.setBounds(160, 239, 211, 27);
		panel.add(txttelephone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEmail.setBounds(12, 327, 99, 33);
		panel.add(lblEmail);
		
		txtadresse = new JTextField();
		txtadresse.setColumns(10);
		txtadresse.setBounds(160, 285, 211, 27);
		panel.add(txtadresse);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(160, 331, 211, 27);
		panel.add(txtemail);
		
		txtlicense = new JTextField();
		txtlicense.setColumns(10);
		txtlicense.setBounds(160, 381, 211, 27);
		panel.add(txtlicense);
		
		txtcompte_bancaire = new JTextField();
		txtcompte_bancaire.setColumns(10);
		txtcompte_bancaire.setBounds(160, 427, 211, 27);
		panel.add(txtcompte_bancaire);
		
		txtnumero_id = new JTextField();
		txtnumero_id.setColumns(10);
		txtnumero_id.setBounds(160, 472, 211, 27);
		panel.add(txtnumero_id);
		
		txtsalaire = new JTextField();
		txtsalaire.setColumns(10);
		txtsalaire.setBounds(160, 514, 211, 27);
		panel.add(txtsalaire);
		
		txtposte = new JTextField();
		txtposte.setColumns(10);
		txtposte.setBounds(160, 554, 211, 27);
		panel.add(txtposte);
		
		txtdepartement = new JTextField();
		txtdepartement.setColumns(10);
		txtdepartement.setBounds(160, 594, 211, 27);
		panel.add(txtdepartement);
		
		JLabel lblLicense = new JLabel("License");
		lblLicense.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblLicense.setBounds(12, 373, 99, 33);
		panel.add(lblLicense);
		
		JLabel lblComptebancaire = new JLabel("Compte_Bank");
		lblComptebancaire.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblComptebancaire.setBounds(12, 422, 136, 33);
		panel.add(lblComptebancaire);
		
		JLabel lblNumeroid = new JLabel("Numero_id");
		lblNumeroid.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNumeroid.setBounds(12, 467, 109, 33);
		panel.add(lblNumeroid);
		
		JLabel lblSalaire = new JLabel("Salaire");
		lblSalaire.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSalaire.setBounds(12, 508, 99, 33);
		panel.add(lblSalaire);
		
		JLabel lblPoste = new JLabel("Poste");
		lblPoste.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPoste.setBounds(12, 549, 99, 33);
		panel.add(lblPoste);
		
		JLabel lblDepartement = new JLabel("Departement");
		lblDepartement.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDepartement.setBounds(12, 589, 123, 33);
		panel.add(lblDepartement);
		
		JButton btnNewButton = new JButton("Sauvegarder");
		btnNewButton.setBounds(434, 716, 107, 50);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{			
				String civilite,nom,prenom,age,telephone,adresse,email,license,compte_bancaire,numero_id,salaire,poste,departement;
				civilite = txtcivilite.getText();
				nom = txtnom.getText();
				prenom = txtprenom.getText();
				age = txtage.getText();
				telephone = txttelephone.getText();
				adresse = txtadresse.getText();
				email = txtemail.getText();
				license = txtlicense.getText();
				compte_bancaire = txtcompte_bancaire.getText();
				numero_id = txtnumero_id.getText();
				salaire = txtsalaire.getText();
				poste = txtposte.getText();
				departement = txtdepartement.getText();
							
				 try {
					pst = con.prepareStatement("insert into rh_employee(civilite,nom,prenom,age,telephone,adresse,email,license,compte_bancaire,numero_id,salaire,poste,departement)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
					pst.setString(1, civilite);
					pst.setString(2, nom);
					pst.setString(3, prenom);
					pst.setString(4, age);
					pst.setString(5, telephone);
					pst.setString(6, adresse);
					pst.setString(7, email);
					pst.setString(8, license);
					pst.setString(9, compte_bancaire);
					pst.setString(10, numero_id);
					pst.setString(11, salaire);
					pst.setString(12, poste);
					pst.setString(13, departement);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Added!");
					table_load();
					
						           
					txtcivilite.setText("");
					txtnom.setText("");
					txtprenom.setText("");
					txtage.setText("");
					txttelephone.setText("");
					txtadresse.setText("");
					txtemail.setText("");
					txtlicense.setText("");
					txtcompte_bancaire.setText("");
					txtnumero_id.setText("");
					txtsalaire.setText("");
					txtposte.setText("");
					txtdepartement.setText("");	
					txtcivilite.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Sortir");
		btnExit.setBounds(1091, 716, 107, 50);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Effacer");
		btnClear.setBounds(920, 716, 107, 50);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtcivilite.setText("");
				txtnom.setText("");
				txtprenom.setText("");
				txtage.setText("");
				txttelephone.setText("");
				txtadresse.setText("");
				txtemail.setText("");
				txtlicense.setText("");
				txtcompte_bancaire.setText("");
				txtnumero_id.setText("");
				txtsalaire.setText("");
				txtposte.setText("");
				txtdepartement.setText("");
				txtcivilite.requestFocus();
			}
		});
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(435, 168, 910, 509);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(144, 64, 1067, 69);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblid_emp = new JLabel("id_emp");
		lblid_emp.setBounds(34, 24, 75, 17);
		lblid_emp.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1.add(lblid_emp);
		
		txtid_emp = new JTextField();
		txtid_emp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 try {
			          
			            String id = txtid_emp.getText();

			                pst = con.prepareStatement("select civilite,nom,prenom,age,telephone,adresse,email,license,compte_bancaire,numero_id,salaire,poste,departement from rh_employee where id_emp = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			              
			                String civilite = rs.getString(1);
			                String nom = rs.getString(2);
			                String prenom = rs.getString(3);
			                String age = rs.getString(4);
			                String telephone = rs.getString(5);
			                String adresse = rs.getString(6);
			                String email = rs.getString(7);
			                String license = rs.getString(8);
			                String compte_bancaire = rs.getString(9);
			                String numero_id = rs.getString(10);
			                String salaire = rs.getString(11);
			                String poste = rs.getString(12);
			                String departement = rs.getString(13);
			                
			                
			                txtcivilite.setText(civilite);
			                txtnom.setText(nom);
			                txtprenom.setText(prenom);
			                txtage.setText(age);
			                txttelephone.setText(telephone);
			                txtadresse.setText(adresse);
			                txtemail.setText(email);
			                txtlicense.setText(license);
			                txtcompte_bancaire.setText(compte_bancaire);
			                txtnumero_id.setText(numero_id);
			                txtsalaire.setText(salaire);
			                txtposte.setText(poste);
			                txtdepartement.setText(departement);
			         
			            }   
			            else
			            {
			            	txtcivilite.setText("");
			            	txtnom.setText("");
			                txtprenom.setText("");
			                txtage.setText("");
			                txttelephone.setText("");
			                txtadresse.setText("");
			                txtemail.setText("");
			                txtlicense.setText("");
			                txtcompte_bancaire.setText("");
			                txtnumero_id.setText("");
			                txtsalaire.setText("");
			                txtposte.setText("");
			                txtdepartement.setText("");
			
			                 
			            }

			        } 
				
				 catch (SQLException ex) {
			           
			        }
			}
		});
		txtid_emp.setBounds(121, 22, 884, 22);
		txtid_emp.setColumns(10);
		panel_1.add(txtid_emp);
		
		JButton btnUpdate = new JButton("Modifier");
		btnUpdate.setBounds(592, 716, 107, 50);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String civilite,nom,prenom,age,telephone,adresse,email,license,compte_bancaire,numero_id,salaire,poste,departement,id_emp;
				
				civilite = txtcivilite.getText();
				nom = txtnom.getText();
				prenom = txtprenom.getText();
				age = txtage.getText();
				telephone = txttelephone.getText();
				adresse = txtadresse.getText();
				email = txtemail.getText();
				license = txtlicense.getText();
				compte_bancaire = txtcompte_bancaire.getText();
				numero_id = txtnumero_id.getText();
				salaire = txtsalaire.getText();
				poste = txtposte.getText();
				departement = txtdepartement.getText();
				id_emp  = txtid_emp.getText();
				
				 try {
						pst = con.prepareStatement("update rh_employee set civilite= ?,nom=?,prenom=?,age=?,telephone=?,adresse=?,email=?,license=?,compte_bancaire=?,numero_id=?,salaire=?,poste=?,departement=? where id_emp =?");
						pst.setString(1, civilite);
			            pst.setString(2, nom);
			            pst.setString(3, prenom);
			            pst.setString(4, age);
			            pst.setString(5, telephone);
			            pst.setString(6, adresse);
			            pst.setString(7, email);
			            pst.setString(8, license);
			            pst.setString(9, compte_bancaire);
			            pst.setString(10, numero_id);
			            pst.setString(11, salaire);
			            pst.setString(12, poste);
			            pst.setString(13, departement);
						pst.setString(14, id_emp);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Updated!");
			            table_load();
			           
			            txtcivilite.setText("");
						txtnom.setText("");
						txtprenom.setText("");
						txtage.setText("");
						txttelephone.setText("");
						txtadresse.setText("");
						txtemail.setText("");
						txtlicense.setText("");
						txtcompte_bancaire.setText("");
						txtnumero_id.setText("");
						txtsalaire.setText("");
						txtposte.setText("");
						txtdepartement.setText("");	
			            
			            txtcivilite.requestFocus();
					}

		            catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
		});
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.setBounds(744, 716, 107, 50);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                String id_emp;
			id_emp  = txtid_emp.getText();
			
			 try {
					pst = con.prepareStatement("delete from rh_employee where id_emp =?");
			
		            pst.setString(1, id_emp);
		            pst.executeUpdate();
		            JOptionPane.showMessageDialog(null, "Record Deleted!");
		            table_load();
		           
		            txtcivilite.setText("");
		            txtnom.setText("");
		            txtprenom.setText("");
		            txtage.setText("");
		            txttelephone.setText("");
		            txtadresse.setText("");
		            txtemail.setText("");
		            txtlicense.setText("");
		            txtcompte_bancaire.setText("");
		            txtnumero_id.setText("");
		            txtsalaire.setText("");
		            txtposte.setText("");
		            txtdepartement.setText("");
		     
		            txtcivilite.requestFocus();
				}
		
		        catch (SQLException e1) {
					
					e1.printStackTrace();
				}
					}
				});
				frame.getContentPane().add(btnDelete);
			}
}
