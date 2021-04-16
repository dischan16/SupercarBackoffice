import java.awt.EventQueue;
import java.sql.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Document;


import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.Color;

public class fiche_salaire {

	private JFrame frame;
	private JTextField txtnom;
	private JTextField txtprenom;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fiche_salaire window = new fiche_salaire();
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
	public fiche_salaire() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtemail;
	private JTextField txtcompte_bancaire;
	private JTextField txtnumero_id;
	private JTextField txtsalaire;
	private JTextField txtposte;
	private JTextField txtdepartement;
	private JTextField textField;

 
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
		    pst = con.prepareStatement("select id_emp,nom,prenom,email,numero_id,compte_bancaire,salaire,poste,departement from rh_employee");
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
		frame.setBounds(100, 100, 1591, 899);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fiche Salaire");
		lblNewLabel.setBounds(546, -14, 245, 79);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(11, 123, 411, 673);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "fiche_salaire", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNom.setBounds(12, 41, 99, 33);
		panel.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrenom.setBounds(12, 97, 99, 33);
		panel.add(lblPrenom);
		
		txtnom = new JTextField();
		txtnom.setColumns(10);
		txtnom.setBounds(160, 46, 211, 27);
		panel.add(txtnom);
		
		txtprenom = new JTextField();
		txtprenom.setColumns(10);
		txtprenom.setBounds(160, 102, 211, 27);
		panel.add(txtprenom);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEmail.setBounds(12, 153, 99, 33);
		panel.add(lblEmail);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(160, 158, 211, 27);
		panel.add(txtemail);
		
		txtcompte_bancaire = new JTextField();
		txtcompte_bancaire.setColumns(10);
		txtcompte_bancaire.setBounds(160, 207, 211, 27);
		panel.add(txtcompte_bancaire);
		
		txtnumero_id = new JTextField();
		txtnumero_id.setColumns(10);
		txtnumero_id.setBounds(160, 266, 211, 27);
		panel.add(txtnumero_id);
		
		txtsalaire = new JTextField();
		txtsalaire.setColumns(10);
		txtsalaire.setBounds(160, 317, 211, 27);
		panel.add(txtsalaire);
		
		txtposte = new JTextField();
		txtposte.setColumns(10);
		txtposte.setBounds(160, 373, 211, 27);
		panel.add(txtposte);
		
		txtdepartement = new JTextField();
		txtdepartement.setColumns(10);
		txtdepartement.setBounds(160, 426, 211, 27);
		panel.add(txtdepartement);
		
		JLabel lblComptebancaire = new JLabel("Compte_Bank");
		lblComptebancaire.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblComptebancaire.setBounds(12, 202, 136, 33);
		panel.add(lblComptebancaire);
		
		JLabel lblNumeroid = new JLabel("Numero_id");
		lblNumeroid.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNumeroid.setBounds(12, 266, 109, 33);
		panel.add(lblNumeroid);
		
		JLabel lblSalaire = new JLabel("Salaire");
		lblSalaire.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSalaire.setBounds(12, 312, 99, 33);
		panel.add(lblSalaire);
		
		JLabel lblPoste = new JLabel("Poste");
		lblPoste.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPoste.setBounds(12, 368, 99, 33);
		panel.add(lblPoste);
		
		JLabel lblDepartement = new JLabel("Departement");
		lblDepartement.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDepartement.setBounds(12, 421, 123, 33);
		panel.add(lblDepartement);
		
		JButton btnExit = new JButton("Sortir");
		btnExit.setBounds(805, 716, 107, 50);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Effacer");
		btnClear.setBounds(623, 716, 107, 50);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtnom.setText("");
				txtprenom.setText("");
				txtemail.setText("");
				txtnumero_id.setText("");
				txtcompte_bancaire.setText("");
				txtsalaire.setText("");
				txtposte.setText("");
				txtdepartement.setText("");
				txtnom.requestFocus();
			}
		});
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(447, 134, 1114, 528);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
			
		JButton btnsalaire = new JButton("PDF Salaire");
		btnsalaire.setBounds(447, 716, 107, 50);
		btnsalaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom,prenom,email,numero_id,compte_bancaire,salaire,poste,departement;
				
				nom = txtnom.getText();
				prenom = txtprenom.getText();
				email = txtemail.getText();
				numero_id = txtnumero_id.getText();
				compte_bancaire = txtcompte_bancaire.getText();
				salaire = txtsalaire.getText();
				poste = txtposte.getText();
				departement = txtdepartement.getText();
							
				 try {
					pst = con.prepareStatement("insert into fiche_salaire(nom,prenom,email,numero_id,compte_bancaire,salaire,poste,departement)values(?,?,?,?,?,?,?,?)");
					pst.setString(1, nom);
					pst.setString(2, prenom);
					pst.setString(3, email);
					pst.setString(4, numero_id);
					pst.setString(5, compte_bancaire);
					pst.setString(6, salaire);
					pst.setString(7, poste);
					pst.setString(8, departement);
					pst.executeUpdate();
					table_load();
					
						           
			
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
				

				
			    nom = txtnom.getText();
				prenom = txtprenom.getText();
				email = txtemail.getText();
				numero_id = txtnumero_id.getText();
				compte_bancaire = txtcompte_bancaire.getText();
				salaire = txtsalaire.getText();
				poste = txtposte.getText();
				departement = txtdepartement.getText();
				
	
				JFileChooser dialog = new JFileChooser();
				dialog.setSelectedFile(new File(nom+"_FicheSalaire"+".pdf"));
				int dialogResult = dialog.showSaveDialog(null);
				if (dialogResult==JFileChooser.APPROVE_OPTION) {
					String filePath = dialog.getSelectedFile().getPath();
					
					
					try {
						Document myDoc = new Document();
						@SuppressWarnings("unused")
						PdfWriter  myWriter = PdfWriter.getInstance(myDoc, new FileOutputStream(filePath));
						myDoc.open();
						
						
						
						myDoc.add(new Paragraph("FICHE SALAIRE", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						
						myDoc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
						
						myDoc.add(new Paragraph("Detail de l'employé", FontFactory.getFont(FontFactory.HELVETICA,20)));
						myDoc.add(new Paragraph("Nom: "+nom+ " ", FontFactory.getFont(FontFactory.HELVETICA,20)));
						myDoc.add(new Paragraph("Prenom: "+prenom+" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
						myDoc.add(new Paragraph("Email: "+email+" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
						myDoc.add(new Paragraph("Numéro carte d'identité: "+numero_id+" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
						myDoc.add(new Paragraph("Nombre compte bancaire: "+compte_bancaire+" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
						myDoc.add(new Paragraph("Salaire: "+salaire+" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
						myDoc.add(new Paragraph("Poste: "+poste+" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
						myDoc.add(new Paragraph("Departement: "+departement+" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
					
						myDoc.close();
						JOptionPane.showMessageDialog(null, "PDF Valider");
						
						

						txtnom.setText("");
						txtprenom.setText("");
						txtemail.setText("");
						txtnumero_id.setText("");
						txtcompte_bancaire.setText("");
						txtsalaire.setText("");
						txtposte.setText("");
						txtdepartement.setText("");	
						txtnom.requestFocus();
						
						
						
					} catch (FileNotFoundException | DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					catch(Exception er) {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}
				
			}
		});
		frame.getContentPane().add(btnsalaire);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(11, 41, 1067, 69);
		frame.getContentPane().add(panel_1);
		
		JLabel label = new JLabel("id_emp");
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(34, 24, 75, 17);
		panel_1.add(label);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
		
			@Override
			public void keyReleased(KeyEvent e) {
				 try {
			          
			            String id = textField.getText();

			                pst = con.prepareStatement("select nom,prenom,email,numero_id,compte_bancaire,salaire,poste,departement from rh_employee where id_emp = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			              
			           
			                String nom = rs.getString(1);
			                String prenom = rs.getString(2);
			                String email = rs.getString(3);
			                String numero_id = rs.getString(4);
			                String compte_bancaire = rs.getString(5);
			                String salaire = rs.getString(6);
			                String poste = rs.getString(7);
			                String departement = rs.getString(8);
			                
			                
			               
			                txtnom.setText(nom);
			                txtprenom.setText(prenom);
			                txtemail.setText(email);
			                txtnumero_id.setText(numero_id);
			                txtcompte_bancaire.setText(compte_bancaire);
			                txtsalaire.setText(salaire);
			                txtposte.setText(poste);
			                txtdepartement.setText(departement);
			         
			            }   
			            else
			            {
			            
			            	txtnom.setText("");
			                txtprenom.setText("");
			                txtemail.setText("");
			                txtnumero_id.setText("");
			                txtcompte_bancaire.setText("");
			                txtsalaire.setText("");
			                txtposte.setText("");
			                txtdepartement.setText("");
			
			                 
			            }

			        } 
				
				 catch (SQLException ex) {
			           
			        }
			}
		});
		textField.setColumns(10);
		textField.setBounds(121, 22, 884, 22);
		panel_1.add(textField);
			}
}
