import java.awt.EventQueue;
import java.util.Date;
import java.util.regex.Pattern;

import net.proteanit.sql.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * 
 * @author Haashim Potyram
 *
 */

public class Commande {

	private AdminAccount account = new AdminAccount();
	private JFrame frame;
	private JTextField txtmarque;
	private JTextField txtmodel;
	private JTextField txtTransmission;
	private JTextField txtcapacite;
	private JTextField txtentrepot;
	private JTextField txtquantite;
	private JTextField txtid;
	private JTable table;
	private int count = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Commande window = new Commande(login);
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
	public Commande(String login) {
		Connect();
		initialize(login);
		count_load();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtdate;
	private JTextField txtpays;
	private JTextField txtfournisseur;
	private JLabel lblNoCommande;
	
	public void Connect() {
		
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

                 pst = con.prepareStatement("select * from administration_commande");

                 rs = pst.executeQuery();
                 
                 DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {},
        					new String[] { "ID","Marque", "Model","Transmission", "Capacite","Pays", "Nom_Fournisseur", "Entrepot","Quantite",
        							"Date"});
                 
         		while (rs.next()) {
       				String id_commande = rs.getString("id_commande");
       				String marque = rs.getString("marque");
       				String model = rs.getString("model");
       				String transmission = rs.getString("transmission");
       				String capacite = rs.getString("capacite");
       				String pays = rs.getString("pays");
       				String nom_fournisseur = rs.getString("nom_fournisseur");
       				String entrepot = rs.getString("entrepot");
       				String quantite = rs.getString("quantite");
       				String date = rs.getString("date");

       				String[] data = { id_commande, marque, model, transmission, capacite,pays, nom_fournisseur, entrepot,quantite,
       						date };
       				tableModel.addRow(data);
       				
         		}
         		
         		table.setModel(tableModel);
   				
   				table.getColumnModel().getColumn(0).setPreferredWidth(50); // id_commande
   				table.getColumnModel().getColumn(1).setPreferredWidth(100); //Marque
   				table.getColumnModel().getColumn(2).setPreferredWidth(100);  // Model
   				table.getColumnModel().getColumn(3).setPreferredWidth(150); // Transmission
   				table.getColumnModel().getColumn(4).setPreferredWidth(100); // Capacite
   				table.getColumnModel().getColumn(5).setPreferredWidth(100); // Pays
   				table.getColumnModel().getColumn(6).setPreferredWidth(150); // nom_fournisseur
   				table.getColumnModel().getColumn(7).setPreferredWidth(150); // Entrepot
   				table.getColumnModel().getColumn(8).setPreferredWidth(50); // Quantite
   				table.getColumnModel().getColumn(9).setPreferredWidth(100); // Date
   				
   				table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);


             }

             catch (SQLException e)

              {

                           e.printStackTrace();

               }

    }
	
	public void count_load() {
		count = 0;
		try {
		pst = con.prepareStatement("select * from administration_commande");
		rs = pst.executeQuery();

		 while (rs.next()) {
		count++;
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
		lblNoCommande.setText("No Commande :"+count);;
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String login) {
		account.DatabaseConnexion(login, null, null, frame);
		System.out.print(account.getId());
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 810);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCommande = new JLabel("Commande");
		lblCommande.setForeground(Color.RED);
		lblCommande.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommande.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblCommande.setBounds(574, 10, 229, 65);
		frame.getContentPane().add(lblCommande);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 79, 359, 526);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMarque = new JLabel("Marque :");
		lblMarque.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMarque.setBounds(10, 23, 79, 30);
		panel.add(lblMarque);
		
		txtmarque = new JTextField();
		txtmarque.setBounds(118, 23, 138, 30);
		panel.add(txtmarque);
		txtmarque.setColumns(10);
		
		JLabel lblModel = new JLabel("Model :");
		lblModel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblModel.setBounds(10, 74, 79, 30);
		panel.add(lblModel);
		
		txtmodel = new JTextField();
		txtmodel.setBounds(118, 74, 154, 30);
		panel.add(txtmodel);
		txtmodel.setColumns(10);
		
		JLabel lblTransmission = new JLabel("Transmission :");
		lblTransmission.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTransmission.setBounds(10, 122, 125, 30);
		panel.add(lblTransmission);
		
		txtTransmission = new JTextField();
		txtTransmission.setBounds(161, 122, 147, 30);
		panel.add(txtTransmission);
		txtTransmission.setColumns(10);
		
		JLabel lblCapacite = new JLabel("Capacite :");
		lblCapacite.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCapacite.setBounds(10, 170, 93, 30);
		panel.add(lblCapacite);
		
		txtcapacite = new JTextField();
		txtcapacite.setBounds(118, 170, 138, 30);
		panel.add(txtcapacite);
		txtcapacite.setColumns(10);
		
		JLabel lblQuantite = new JLabel("Quantite :");
		lblQuantite.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQuantite.setBounds(10, 389, 79, 30);
		panel.add(lblQuantite);
		
		txtquantite = new JTextField();
		txtquantite.setBounds(118, 392, 106, 30);
		panel.add(txtquantite);
		txtquantite.setColumns(10);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDate.setBounds(10, 441, 69, 25);
		panel.add(lblDate);
		
		txtdate = new JTextField();
		txtdate.setBounds(118, 440, 125, 32);
		panel.add(txtdate);
		txtdate.setColumns(10);
		
		JLabel lblPays = new JLabel("Pays :");
		lblPays.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPays.setBounds(10, 223, 69, 30);
		panel.add(lblPays);
		
		txtpays = new JTextField();
		txtpays.setBounds(118, 223, 138, 30);
		panel.add(txtpays);
		txtpays.setColumns(10);
		
		JLabel lblFournisseur = new JLabel("Fournisseur :");
		lblFournisseur.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFournisseur.setBounds(10, 269, 106, 30);
		panel.add(lblFournisseur);
		
		txtfournisseur = new JTextField();
		txtfournisseur.setBounds(127, 271, 145, 30);
		panel.add(txtfournisseur);
		txtfournisseur.setColumns(10);
		
		JLabel lblEntrepot = new JLabel("Entrepot :");
		lblEntrepot.setBounds(10, 334, 87, 30);
		panel.add(lblEntrepot);
		lblEntrepot.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		txtentrepot = new JTextField();
		txtentrepot.setBounds(118, 337, 138, 30);
		panel.add(txtentrepot);
		txtentrepot.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String marque = txtmarque.getText();
				String model = txtmodel.getText();
				String transmission = txtTransmission.getText();
				String capacite = txtcapacite.getText();
				String pays = txtpays.getText();
				String fournisseur = txtfournisseur.getText();
				String entrepot = txtentrepot.getText();
				String quantite = txtquantite.getText();
				String date = txtdate.getText();
				
				try {
					
					final String MARQUE_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					 
				    final Pattern MARQUE_PATTERN = Pattern.compile(MARQUE_REGEX);
				    
				    final String MODEL_REGEX = "^[a-zA-Z0-9_]+$";
				    
				    final Pattern MODEL_PATTERN = Pattern.compile(MODEL_REGEX);
				    
				    final String TRANSMISSION_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					 
				    final Pattern TRANSMISSION_PATTERN = Pattern.compile(TRANSMISSION_REGEX);
				    
				    final String CAPACITE_REGEX = "^[0-9]*$";
				    
				    final Pattern CAPACITE_PATTERN = Pattern.compile(CAPACITE_REGEX);
				    
				    final String PAYS_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					 
				    final Pattern PAYS_PATTERN = Pattern.compile(PAYS_REGEX);
				    
				    final String FOURNISSEUR_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
				    
				    final Pattern FOURNISSEUR_PATTERN = Pattern.compile(FOURNISSEUR_REGEX);
				    
				    final String ENTREPOT_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
				    
				    final Pattern ENTREPOT_PATTERN = Pattern.compile(ENTREPOT_REGEX);
				    
                    final String QUANTITE_REGEX = "^[0-9]*$";
				    
				    final Pattern QUANTITE_PATTERN = Pattern.compile(QUANTITE_REGEX);
				    
                    /*final String DATE_REGEX = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
				    
				    final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);*/
				    
				    if (MARQUE_PATTERN.matcher(marque).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du marque n'est pas bon,re-inserez la marque s'il vous plait");
				    }
				    
				    if(MODEL_PATTERN.matcher(model).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du model n'est pas bon,re-inserez le model s'il vous plait");
				    }
				    
				    if( TRANSMISSION_PATTERN.matcher(transmission).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du transmission n'est pas bon,re-inserez le transmission s'il vous plait");
				    }
				    
				    if( CAPACITE_PATTERN.matcher(capacite).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du cpacite n'est pas bon,re-inserez la capacite s'il vous plait");
				    }
				    
				    if( PAYS_PATTERN.matcher(pays).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du pays n'est pas bon,re-inserez le pays s'il vous plait");
				    }
				    
				    if( FOURNISSEUR_PATTERN.matcher(fournisseur).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du fournisseur n'est pas bon,re-inserez le nom du fournisseur s'il vous plait");
				    }
				    
				    if( ENTREPOT_PATTERN.matcher(entrepot).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format de l'entrepot n'est pas bon,re-inserez le nom s'il vous plait");
				    }
				    
				    if( QUANTITE_PATTERN.matcher(quantite).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du quantite n'est pas bon,re-inserez le quantite s'il vous plait");
				    }
				    
				    /*if ( DATE_PATTERN.matcher(date).matches()== false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion de la date n`est pas bonn");
				    	txtdate.setText("");
				    }*/
				    
				    if (MARQUE_PATTERN.matcher(marque).matches()&&
				              MODEL_PATTERN.matcher(model).matches() &&
				                TRANSMISSION_PATTERN.matcher(transmission).matches() &&
				                CAPACITE_PATTERN.matcher(capacite).matches()&&
				                PAYS_PATTERN.matcher(pays).matches()&&
				                FOURNISSEUR_PATTERN.matcher(fournisseur).matches()&&
				                ENTREPOT_PATTERN.matcher(entrepot).matches()&&
				                QUANTITE_PATTERN.matcher(quantite).matches()) {
					
					pst = con.prepareStatement("insert into administration_commande(marque,model,transmission,capacite,pays,nom_fournisseur,entrepot,quantite,date)values(?,?,?,?,?,?,?,?,?)");
					
					pst.setString(1, marque);
					pst.setString(2, model);
					pst.setString(3, transmission);
					pst.setString(4, capacite);
					pst.setString(5, pays);
					pst.setString(6, fournisseur);
					pst.setString(7, entrepot);
					pst.setString(8, quantite);
					pst.setString(9, date);
					pst.executeUpdate();
					
					FileWriter Writer = new FileWriter("RapportAchat.txt",true);
					//getting current date and time using Date class
				       DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				       Date dateobj = new Date();
					Writer.write(" Marque :"+marque+" \n Model : "+model+" \n Transmission : "+transmission+" \n Capacite : "+capacite+" \n Pays : "+pays+" \n Fournisseur : "+fournisseur+" \n Entrepot : "+entrepot+" \n Quantite : "+date+" \n Date : "+date+" \n "
							+ "Date Du Rapport : "+df.format(dateobj));
					Writer.write(System.getProperty("line.separator"));
					Writer.close();	
					
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					
				    }
				    				
					table_load();
				    count_load();	
					
					
				} catch (SQLException e1) 
		        {
					
			e1.printStackTrace();
			} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 marque = txtmarque.getText();
				 model = txtmodel.getText();
				 transmission = txtTransmission.getText();
				 capacite = txtcapacite.getText();
				 pays = txtpays.getText();
				 fournisseur = txtfournisseur.getText();
				 entrepot = txtentrepot.getText();
				 quantite = txtquantite.getText();
				 date = txtdate.getText();
				
				JFileChooser dialog = new JFileChooser();
				dialog.setSelectedFile(new File(marque+"_Achat"+".pdf"));
				int dialogResult = dialog.showSaveDialog(null);
				if (dialogResult==JFileChooser.APPROVE_OPTION) {
					String filePath = dialog.getSelectedFile().getPath();
					
					try {
						
						Document myDoc = new Document();
						@SuppressWarnings("unused")
						PdfWriter  myWriter = PdfWriter.getInstance(myDoc, new FileOutputStream(filePath));
						myDoc.open();
						
						myDoc.add(new Paragraph("Achat", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						
						myDoc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
						
						myDoc.add(new Paragraph("Detail de l'achat d'un voiture", FontFactory.getFont(FontFactory.HELVETICA,15)));
						myDoc.add(new Paragraph("Marque: "+marque+ " ", FontFactory.getFont(FontFactory.HELVETICA,15)));
						myDoc.add(new Paragraph("Model: "+model+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
						myDoc.add(new Paragraph("Transmission: "+transmission+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
						myDoc.add(new Paragraph("Capacite: "+capacite+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
						myDoc.add(new Paragraph("Pays: "+pays+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
						myDoc.add(new Paragraph("Fournisseur: "+fournisseur+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
						myDoc.add(new Paragraph("Entrepot: "+entrepot+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
						myDoc.add(new Paragraph("Quantite: "+quantite+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
						myDoc.add(new Paragraph("Date: "+date+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
						
						myDoc.close();
						JOptionPane.showMessageDialog(null, "PDF Valider");
						
						txtmarque.setText("");
	     				txtmodel.setText("");
	     				txtTransmission.setText("");
	     				txtcapacite.setText("");
	     				txtpays.setText("");
	     				txtfournisseur.setText("");
	     				txtentrepot.setText("");
	     				txtquantite.setText("");
	     				txtdate.setText("");
	     				txtmarque.requestFocus();
						
					}catch (FileNotFoundException | DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
				
			}
		});
		btnSave.setFont(new Font("Arial", Font.BOLD, 18));
		btnSave.setBounds(10, 627, 85, 30);
		frame.getContentPane().add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String marque,model,transmission,capacite,pays,fournisseur,entrepot,quantite,date,id;
				
				 marque = txtmarque.getText();
				 model = txtmodel.getText();
				 transmission = txtTransmission.getText();
				 capacite = txtcapacite.getText();
				 pays = txtpays.getText();
				 fournisseur = txtfournisseur.getText();
				 entrepot = txtentrepot.getText();
				 quantite = txtquantite.getText();
				 date = txtdate.getText();
				 id = txtid.getText();
				 
				 try {
					 
					 final String MARQUE_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					 
					    final Pattern MARQUE_PATTERN = Pattern.compile(MARQUE_REGEX);
					    
					    final String MODEL_REGEX = "^[a-zA-Z0-9_]+$";
					    
					    final Pattern MODEL_PATTERN = Pattern.compile(MODEL_REGEX);
					    
					    final String TRANSMISSION_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
						 
					    final Pattern TRANSMISSION_PATTERN = Pattern.compile(TRANSMISSION_REGEX);
					    
					    final String CAPACITE_REGEX = "^[0-9]*$";
					    
					    final Pattern CAPACITE_PATTERN = Pattern.compile(CAPACITE_REGEX);
					    
					    final String PAYS_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
						 
					    final Pattern PAYS_PATTERN = Pattern.compile(PAYS_REGEX);
					    
					    final String FOURNISSEUR_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					    
					    final Pattern FOURNISSEUR_PATTERN = Pattern.compile(FOURNISSEUR_REGEX);
					    
					    final String ENTREPOT_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					    
					    final Pattern ENTREPOT_PATTERN = Pattern.compile(ENTREPOT_REGEX);
					    
	                    final String QUANTITE_REGEX = "^[0-9]*$";
					    
					    final Pattern QUANTITE_PATTERN = Pattern.compile(QUANTITE_REGEX);
					    
	                    /*final String DATE_REGEX = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
					    
					    final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);*/
					    
					    if (MARQUE_PATTERN.matcher(marque).matches() == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion du marque n`est pas bon");
					    }
					    
					    if(MODEL_PATTERN.matcher(model).matches()  == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion du model n`est pas bon");
					    }
					    
					    if( TRANSMISSION_PATTERN.matcher(transmission).matches()  == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion de la transmission n`est pas bon");
					    }
					    
					    if( CAPACITE_PATTERN.matcher(capacite).matches()  == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion du capacite n`est pas bon");
					    }
					    
					    if( PAYS_PATTERN.matcher(pays).matches() == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion du pays n`est pas bon");
					    }
					    
					    if( FOURNISSEUR_PATTERN.matcher(fournisseur).matches()  == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion du fournisseur n`est pas bon");
					    }
					    
					    if( ENTREPOT_PATTERN.matcher(entrepot).matches()  == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion du entrepot n`est pas bon");
					    }
					    
					    if( QUANTITE_PATTERN.matcher(quantite).matches()  == false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion du quantite n`est pas bon");
					    }
					    
					    /*if ( DATE_PATTERN.matcher(date).matches()== false) {
					    	JOptionPane.showMessageDialog(null, "L`insertion de la date n`est pas bonn");
					    	txtdate.setText("");
					    }*/
					    
					    if (MARQUE_PATTERN.matcher(marque).matches()&&
					              MODEL_PATTERN.matcher(model).matches() &&
					                TRANSMISSION_PATTERN.matcher(transmission).matches() &&
					                CAPACITE_PATTERN.matcher(capacite).matches()&&
					                PAYS_PATTERN.matcher(pays).matches()&&
					                FOURNISSEUR_PATTERN.matcher(fournisseur).matches()&&
					                ENTREPOT_PATTERN.matcher(entrepot).matches()&&
					                QUANTITE_PATTERN.matcher(quantite).matches()) {
					 
					 pst = con.prepareStatement("update administration_commande set marque = ?,model = ?,transmission = ?,capacite = ?,pays = ?,nom_fournisseur = ?,entrepot = ?,quantite = ?, date = ? where id_commande = ?");
					 
					    pst.setString(1, marque);
						pst.setString(2, model);
						pst.setString(3, transmission);
						pst.setString(4, capacite);
						pst.setString(5, pays);
						pst.setString(6, fournisseur);
						pst.setString(7, entrepot);
						pst.setString(8, quantite);
						pst.setString(9, date);
						pst.setString(10, id);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Updateddd!!!!!");
					    }
						table_load();
						
						txtmarque.setText("");
						txtmodel.setText("");
						txtTransmission.setText("");
						txtcapacite.setText("");
						txtpays.setText("");
						txtfournisseur.setText("");
						txtentrepot.setText("");
						txtquantite.setText("");
						txtdate.setText("");
						txtmarque.requestFocus();
					 
				 } catch (SQLException e1) {

	                    e1.printStackTrace();

	      }
				
				
			}
		});
		btnEdit.setFont(new Font("Arial", Font.BOLD, 18));
		btnEdit.setBounds(117, 628, 85, 28);
		frame.getContentPane().add(btnEdit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtmarque.setText("");
				txtmodel.setText("");
				txtTransmission.setText("");
				txtcapacite.setText("");
				txtpays.setText("");
				txtfournisseur.setText("");
				txtentrepot.setText("");
				txtquantite.setText("");
				txtdate.setText("");
				txtmarque.requestFocus();
				
			}
		});
		btnClear.setFont(new Font("Arial", Font.BOLD, 16));
		btnClear.setBounds(237, 626, 85, 30);
		frame.getContentPane().add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 684, 359, 80);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblId = new JLabel("Id :");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBounds(10, 30, 57, 25);
		panel_1.add(lblId);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
					String id = txtid.getText();
					
	                pst = con.prepareStatement("select marque,model,transmission,capacite,pays,nom_fournisseur,entrepot,quantite,date from administration_commande where id_commande = ?");
	                pst.setString(1, id);
                    ResultSet rs = pst.executeQuery();
                    
                	if(rs.next()==true)
					{

						String marque = rs.getString(1);
						String model = rs.getString(2);
						String transmission = rs.getString(3);
						String capacite = rs.getString(4);
						String pays = rs.getString(5);
						String fournisseur = rs.getString(6);
						String entrepot = rs.getString(7);
						String quantite = rs.getString(8);
						String date = rs.getString(9);
						
						txtmarque.setText(marque);
						txtmodel.setText(model);
						txtTransmission.setText(transmission);
						txtcapacite.setText(capacite);
						txtpays.setText(pays);
						txtfournisseur.setText(fournisseur);
						txtentrepot.setText(entrepot);
						txtquantite.setText(quantite);
						txtdate.setText(date); 
						
					}
                	else {
                		
                		txtmarque.setText("");
        				txtmodel.setText("");
        				txtTransmission.setText("");
        				txtcapacite.setText("");
        				txtpays.setText("");
        				txtfournisseur.setText("");
        				txtentrepot.setText("");
        				txtquantite.setText("");
        				txtdate.setText("");
                	}
					
				}
                catch (SQLException ex) {
					
				}
				
			}
		});
		txtid.setBounds(92, 30, 96, 25);
		panel_1.add(txtid);
		txtid.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(434, 78, 776, 423);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String id = txtid.getText(); 			 
					String marque = txtmarque.getText();
					String model = txtmodel.getText();
					String transmission = txtTransmission.getText();
					String capacite = txtcapacite.getText();
					String pays = txtpays.getText();
					String fournisseur = txtfournisseur.getText();
					String entrepot = txtentrepot.getText();
					String quantite = txtquantite.getText();
					String date = txtdate.getText();
					
					try {
						
						pst = con.prepareStatement("delete from administration_commande where id_commande =?");
						
						 pst.setString(1, id);

	                     pst.executeUpdate();

	                     JOptionPane.showMessageDialog(null, "Record Deleted!");
	                     table_load();
	                     count_load();
	                     
	                     txtmarque.setText("");
	     				txtmodel.setText("");
	     				txtTransmission.setText("");
	     				txtcapacite.setText("");
	     				txtpays.setText("");
	     				txtfournisseur.setText("");
	     				txtentrepot.setText("");
	     				txtquantite.setText("");
	     				txtdate.setText("");
	     				txtmarque.requestFocus();
						
					}
					catch (SQLException e1) {                   

	                    e1.printStackTrace();

	       }
			}
		});
		btnDelete.setFont(new Font("Arial", Font.BOLD, 18));
		btnDelete.setBounds(543, 529, 98, 30);
		frame.getContentPane().add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous quitter la page 'Commande'?",
						"Warning", dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					Commande.this.frame.setVisible(false);
					login_connection.main(login);
				}
			}
		});
		btnExit.setFont(new Font("Arial", Font.BOLD, 18));
		btnExit.setBounds(688, 529, 85, 30);
		frame.getContentPane().add(btnExit);
		
		lblNoCommande = new JLabel("No.De Commandes :");
		lblNoCommande.setFont(new Font("Arial", Font.BOLD, 20));
		lblNoCommande.setBounds(515, 587, 271, 53);
		frame.getContentPane().add(lblNoCommande);
	}
}
