import java.awt.EventQueue;

import java.util.regex.Pattern;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;

/**
 * 
 * @author Haashim Potyram
 *
 */

public class Vente {
	
	private AdminAccount account = new AdminAccount();
	private JFrame frame;
	private JTextField txtclient;
	private JTextField txtvendeur;
	private JTextField txtmarque;
	private JTextField txtmodel;
	private JTextField txtmoteur;
	private JTextField txtcouleur;
	private JTextField txtprix;
	private JTable table;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vente window = new Vente(login);
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
	public Vente(String login) {
		Connect();
		initialize(login);
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtdate;
	
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
	
	/**
	 * 
	 * @param paiement
	 * @return
	 */
	
	public String Decrypt_Prix(String prix) {
		if (account.getAccountType().contains("Comptabilite")) {
		SecretKey key;
		try {
			key = ApiBlowfish.decryptKey();
			prix = ApiBlowfish.decryptInString(prix, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

		return prix;
	}
	
	public String Encrpyt_Prix(String prix) {
		SecretKey key;
		try {
			key = ApiBlowfish.decryptKey();
			prix = ApiBlowfish.encryptInString(prix, key);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return prix;
	}
	
	public void table_load()

    {

             try

             {

                 pst = con.prepareStatement("select * from comptable_vente");

                 rs = pst.executeQuery();
                 
                 DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {},
      					new String[] { "ID","Client", "Vendeur", "Marque", "Model", "No_Moteur", "Couleur", "Prix",
      							"Date"});
      			


      				while (rs.next()) {
      				String id_vente = rs.getString("id_vente");
      				String client = rs.getString("client");
      				String vendeur = rs.getString("vendeur");
      				String marque = rs.getString("marque");
      				String model = rs.getString("model");
      				String no_moteur = rs.getString("no_moteur");
      				String couleur = rs.getString("couleur");
      				String prix = Decrypt_Prix(rs.getString("prix"));
      				String date = rs.getString("date");

      				String[] data = { id_vente, client, vendeur, marque, model, no_moteur, couleur, prix,
      						date };
      				tableModel.addRow(data);


             }
      				table.setModel(tableModel);
      				
      				table.getColumnModel().getColumn(0).setPreferredWidth(75); // ID
      				table.getColumnModel().getColumn(1).setPreferredWidth(100); //Client
      				table.getColumnModel().getColumn(2).setPreferredWidth(100);  // Vendeur
      				table.getColumnModel().getColumn(3).setPreferredWidth(100); // Marque
      				table.getColumnModel().getColumn(4).setPreferredWidth(100); // Model
      				table.getColumnModel().getColumn(5).setPreferredWidth(100); // no_moteur
      				table.getColumnModel().getColumn(6).setPreferredWidth(100); // couleur
      				table.getColumnModel().getColumn(7).setPreferredWidth(100); // prix
      				table.getColumnModel().getColumn(8).setPreferredWidth(100); // date
      				
      				table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

             }catch (SQLException e)

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
		frame.setBounds(100, 100, 1380, 750);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblVente = new JLabel("Vente");
		lblVente.setForeground(Color.RED);
		lblVente.setFont(new Font("Arial Black", Font.BOLD, 33));
		lblVente.setHorizontalAlignment(SwingConstants.CENTER);
		lblVente.setBounds(459, 10, 156, 50);
		frame.getContentPane().add(lblVente);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 81, 379, 443);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblClient = new JLabel("Client :");
		lblClient.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblClient.setBounds(10, 20, 71, 32);
		panel.add(lblClient);
		
		txtclient = new JTextField();
		txtclient.setBounds(105, 20, 142, 32);
		panel.add(txtclient);
		txtclient.setColumns(10);
		
		JLabel lblVendeur = new JLabel("Vendeur :");
		lblVendeur.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblVendeur.setBounds(10, 70, 84, 32);
		panel.add(lblVendeur);
		
		txtvendeur = new JTextField();
		txtvendeur.setBounds(117, 71, 142, 32);
		panel.add(txtvendeur);
		txtvendeur.setColumns(10);
		
		JLabel lblMarque = new JLabel("Marque :");
		lblMarque.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMarque.setBounds(10, 116, 71, 32);
		panel.add(lblMarque);
		
		txtmarque = new JTextField();
		txtmarque.setBounds(105, 119, 166, 32);
		panel.add(txtmarque);
		txtmarque.setColumns(10);
		
		JLabel lblModel = new JLabel("Model :");
		lblModel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblModel.setBounds(10, 168, 71, 32);
		panel.add(lblModel);
		
		txtmodel = new JTextField();
		txtmodel.setBounds(105, 168, 154, 32);
		panel.add(txtmodel);
		txtmodel.setColumns(10);
		
		JLabel lblNomoteur = new JLabel("No_Moteur :");
		lblNomoteur.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNomoteur.setBounds(10, 223, 102, 32);
		panel.add(lblNomoteur);
		
		txtmoteur = new JTextField();
		txtmoteur.setBounds(136, 226, 135, 32);
		panel.add(txtmoteur);
		txtmoteur.setColumns(10);
		
		JLabel lblCouleur = new JLabel("Couleur :");
		lblCouleur.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCouleur.setBounds(10, 276, 84, 25);
		panel.add(lblCouleur);
		
		txtcouleur = new JTextField();
		txtcouleur.setBounds(105, 274, 160, 32);
		panel.add(txtcouleur);
		txtcouleur.setColumns(10);
		
		JLabel lblPrix = new JLabel("Prix :");
		lblPrix.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrix.setBounds(10, 325, 64, 25);
		panel.add(lblPrix);
		
		txtprix = new JTextField();
		txtprix.setBounds(105, 324, 154, 32);
		panel.add(txtprix);
		txtprix.setColumns(10);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDate.setBounds(10, 373, 64, 32);
		panel.add(lblDate);
		
		txtdate = new JTextField();
		txtdate.setBounds(105, 376, 135, 29);
		panel.add(txtdate);
		txtdate.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String client = txtclient.getText();
				String vendeur = txtvendeur.getText();
				String marque = txtmarque.getText();
				String model = txtmodel.getText();
				String moteur = txtmoteur.getText();
				String couleur = txtcouleur.getText();
				String prix = Encrpyt_Prix(txtprix.getText());
				String date = txtdate.getText();
				
				try {
					
					final String CLIENT_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
				 
				    final Pattern CLIENT_PATTERN = Pattern.compile(CLIENT_REGEX);
				    
				    final String VENDEUR_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
				 
				    final Pattern VENDEUR_PATTERN = Pattern.compile(VENDEUR_REGEX);
				    
				    final String MARQUE_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
				 
				    final Pattern MARQUE_PATTERN = Pattern.compile(MARQUE_REGEX);
				    
				    final String MODEL_REGEX = "^[a-zA-Z0-9_]+$";
				    
				    final Pattern MODEL_PATTERN = Pattern.compile(MODEL_REGEX);
				    
				    final String MOTEUR_REGEX = "^[0-9]*$";
				    
				    final Pattern MOTEUR_PATTERN = Pattern.compile(MOTEUR_REGEX);
				    
				    final String COULEUR_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					 
				    final Pattern COULEUR_PATTERN = Pattern.compile(COULEUR_REGEX);
				    
                    final String PRIX_REGEX = "^[0-9]+([',. -])*$";
				    
				    final Pattern PRIX_PATTERN = Pattern.compile(PRIX_REGEX);
				    
				    /*final String DATE_REGEX = "^(?:(?:(?:0?[13578]|1[02])(\\/|-|\\.)31)\\1|" +
				            "(?:(?:0?[1,3-9]|1[0-2])(\\/|-|\\.)(?:29|30)\\2))" +
				            "(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:0?2(\\/|-|\\.)29\\3" +
				            "(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|" +
				            "[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|" +
				            "^(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\\.)(?:0?[1-9]|1\\d|" +
				            "2[0-8])\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
				    
				    final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);*/
				    
				   
				    
				    if (CLIENT_PATTERN.matcher(client).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du nom du client n'est pas bon,re-inserez son nom s'il vous plait");
				    }
				    
				    if(VENDEUR_PATTERN.matcher(vendeur).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du nom du client n'est pas bon,re-inserez le nom du vendeur s'il vous plait");
				    }
				    
				    if( MARQUE_PATTERN.matcher(marque).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du marque n'est pas bon,re-inserez la marque de la voiture s'il vous plait");
				    }
				    
				    if( MODEL_PATTERN.matcher(model).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du model n'est pas bon,re-inserez le model de la voiture s'il vous plait");
				    }
				    
				    if( MOTEUR_PATTERN.matcher(moteur).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du moteur n'est pas bon,re-inserez le numéro du moteur de la voiture s'il vous plait");
				    }
				    
				    if( COULEUR_PATTERN.matcher(couleur).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du couleur n'est pas bon,re-inserez la couleur s'il vous plait");
				    }
				    
				    if( PRIX_PATTERN.matcher(Decrypt_Prix(prix)).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "Le format du prix n'est pas bon,re-inserez le prix de la voiture s'il vous plait");
				    }
				    
				 
	
					///////////setVisible(false);///////////////
					////////////////////new InsertData().setVisible(true);//////////////////
					
					if (VENDEUR_PATTERN.matcher(vendeur).matches()&&
			              CLIENT_PATTERN.matcher(client).matches() &&
			                MARQUE_PATTERN.matcher(marque).matches() &&
			                MODEL_PATTERN.matcher(model).matches()&&
			                MOTEUR_PATTERN.matcher(moteur).matches()&&
			                COULEUR_PATTERN.matcher(couleur).matches()&&
			                PRIX_PATTERN.matcher(Decrypt_Prix(prix)).matches()) {
						
						
						
						pst = con.prepareStatement("insert into comptable_vente(client,vendeur,marque,model,no_moteur,couleur,prix,date)values(?,?,?,?,?,?,?,?)");
						pst.setString(1, client);
						pst.setString(2, vendeur);
						pst.setString(3, marque);
						pst.setString(4, model);
						pst.setString(5, moteur);
						pst.setString(6, couleur);
						pst.setString(7, prix);
						pst.setString(8, date);
						pst.executeUpdate();
						
						FileWriter Writer = new FileWriter("RapportVente.txt",true);
						//getting current date and time using Date class
					       DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
					       Date dateobj = new Date();
						Writer.write(" Client :"+client+" \n Vendeur : "+vendeur+" \n Marque : "+marque+" \n Model : "+model+" \n Moteur : "+moteur+" \n Couleur : "+couleur+" \n Prix : "+prix+" \n Date : "+date+" \n "
								+ "Date Du Rapport : "+df.format(dateobj));
						Writer.write(System.getProperty("line.separator"));
						Writer.close();	
						
						
						JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					}
					
					table_load();
					
						           
					
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				 client = txtclient.getText();
				 vendeur = txtvendeur.getText();
				 marque = txtmarque.getText();
				 model = txtmodel.getText();
				 moteur = txtmoteur.getText();
				 couleur = txtcouleur.getText();
				 prix = Decrypt_Prix(txtprix.getText());
				 date = txtdate.getText();
				 
				 JFileChooser dialog = new JFileChooser();
					dialog.setSelectedFile(new File(client+"_Voiture"+".pdf"));
					int dialogResult = dialog.showSaveDialog(null);
					if (dialogResult==JFileChooser.APPROVE_OPTION) {
						String filePath = dialog.getSelectedFile().getPath();
						
						try {
							
							Document myDoc = new Document();
							@SuppressWarnings("unused")
							PdfWriter  myWriter = PdfWriter.getInstance(myDoc, new FileOutputStream(filePath));
							myDoc.open();
							
							myDoc.add(new Paragraph("Voiture", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
							
							myDoc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
							
							myDoc.add(new Paragraph("Detail de la voiture", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Client: "+client+ " ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Vendeur: "+vendeur+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Marque: "+marque+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Model: "+model+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Moteur: "+moteur+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Couleur: "+couleur+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Prix: "+prix+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							myDoc.add(new Paragraph("Date: "+date+" ", FontFactory.getFont(FontFactory.HELVETICA,15)));
							
							myDoc.close();
							JOptionPane.showMessageDialog(null, "PDF Valider");
							
						          txtclient.setText("");
				                  txtvendeur.setText("");
				                  txtmarque.setText("");
				                  txtmodel.setText("");
				                  txtmoteur.setText("");
				                  txtcouleur.setText("");
				                  txtprix.setText("");
				                  txtdate.setText("");
				                  txtclient.requestFocus();
							
							
						}catch (FileNotFoundException | DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					catch(Exception er) {
						JOptionPane.showMessageDialog(null, "Error");
					}
				
			}
		}
	});
		btnSave.setFont(new Font("Arial", Font.BOLD, 20));
		btnSave.setBounds(10, 554, 92, 33);
		frame.getContentPane().add(btnSave);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String client,vendeur,marque,model,moteur,couleur,prix,date,id;
				
				 client = txtclient.getText();
				 vendeur = txtvendeur.getText();
				 marque = txtmarque.getText();
				 model = txtmodel.getText();
				 moteur = txtmoteur.getText();
				 couleur = txtcouleur.getText();
				 prix = Encrpyt_Prix(txtprix.getText());
				 date = txtdate.getText();
				 id = txtid.getText();
				
				try {
					
					final String CLIENT_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					 
				    final Pattern CLIENT_PATTERN = Pattern.compile(CLIENT_REGEX);
				    
				    final String VENDEUR_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
				 
				    final Pattern VENDEUR_PATTERN = Pattern.compile(VENDEUR_REGEX);
				    
				    final String MARQUE_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
				 
				    final Pattern MARQUE_PATTERN = Pattern.compile(MARQUE_REGEX);
				    
				    final String MODEL_REGEX = "^[a-zA-Z0-9_]+$";
				    
				    final Pattern MODEL_PATTERN = Pattern.compile(MODEL_REGEX);
				    
				    final String MOTEUR_REGEX = "^[0-9]*$";
				    
				    final Pattern MOTEUR_PATTERN = Pattern.compile(MOTEUR_REGEX);
				    
				    final String COULEUR_REGEX = "^[A-Za-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
					 
				    final Pattern COULEUR_PATTERN = Pattern.compile(COULEUR_REGEX);
				    
                    final String PRIX_REGEX = "^[0-9]+([',. -])*$";
				    
				    final Pattern PRIX_PATTERN = Pattern.compile(PRIX_REGEX);
				    
				    /*final String DATE_REGEX = "^(?:(?:(?:0?[13578]|1[02])(\\/|-|\\.)31)\\1|" +
				            "(?:(?:0?[1,3-9]|1[0-2])(\\/|-|\\.)(?:29|30)\\2))" +
				            "(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:0?2(\\/|-|\\.)29\\3" +
				            "(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|" +
				            "[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|" +
				            "^(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\\.)(?:0?[1-9]|1\\d|" +
				            "2[0-8])\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
				    
				    final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);*/
				    
				   
				    
				    if (CLIENT_PATTERN.matcher(client).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du client n`est pas bon");
				    }
				    
				    if(VENDEUR_PATTERN.matcher(vendeur).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du vendeur n`est pas bon");
				    }
				    
				    if( MARQUE_PATTERN.matcher(marque).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion de la marque n`est pas bon");
				    }
				    
				    if( MODEL_PATTERN.matcher(model).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du model n`est pas bon");
				    }
				    
				    if( MOTEUR_PATTERN.matcher(moteur).matches() == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du moteur n`est pas bon");
				    }
				    
				    if( COULEUR_PATTERN.matcher(couleur).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du couleur n`est pas bon");
				    }
				    
				    if( PRIX_PATTERN.matcher(Decrypt_Prix(prix)).matches()  == false) {
				    	JOptionPane.showMessageDialog(null, "L`insertion du prix n`est pas bon");
				    }
				    
				    if (VENDEUR_PATTERN.matcher(vendeur).matches()&&
				              CLIENT_PATTERN.matcher(client).matches() &&
				                MARQUE_PATTERN.matcher(marque).matches() &&
				                MODEL_PATTERN.matcher(model).matches()&&
				                MOTEUR_PATTERN.matcher(moteur).matches()&&
				                COULEUR_PATTERN.matcher(couleur).matches()&&
				                PRIX_PATTERN.matcher(Decrypt_Prix(prix)).matches()) {
					
					pst = con.prepareStatement("update comptable_vente set client = ?,vendeur = ?,marque = ?,model = ?,no_moteur = ?,couleur = ?,prix = ?,date = ? where id_vente = ?");
					
					pst.setString(1, client);
					pst.setString(2, vendeur);
					pst.setString(3, marque);
					pst.setString(4, model);
					pst.setString(5, moteur);
					pst.setString(6, couleur);
					pst.setString(7, prix);
					pst.setString(8, date);
					pst.setString(9, id);
					pst.executeUpdate();
					
					  JOptionPane.showMessageDialog(null, "Record Updated!");
				    }

	                  table_load();

	                
	                  txtclient.setText("");
	                  txtvendeur.setText("");
	                  txtmarque.setText("");
	                  txtmodel.setText("");
	                  txtmoteur.setText("");
	                  txtcouleur.setText("");
	                  txtprix.setText("");
	                  txtdate.setText("");
	                  txtclient.requestFocus();
						
					}catch (SQLException e1) {

	                    e1.printStackTrace();

	      }
				
			}
		});
		btnEdit.setFont(new Font("Arial", Font.BOLD, 20));
		btnEdit.setBounds(129, 554, 85, 33);
		frame.getContentPane().add(btnEdit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtclient.setText("");
                txtvendeur.setText("");
                txtmarque.setText("");
                txtmodel.setText("");
                txtmoteur.setText("");
                txtcouleur.setText("");
                txtprix.setText("");
                txtdate.setText("");
                txtclient.requestFocus();
				
			}
		});
		btnClear.setFont(new Font("Arial", Font.BOLD, 20));
		btnClear.setBounds(255, 554, 85, 33);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(469, 70, 836, 503);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 620, 330, 74);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblId = new JLabel("Id :");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblId.setBounds(10, 25, 58, 24);
		panel_1.add(lblId);
		
		txtid = new JTextField();
		txtid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
					  String id = txtid.getText();
						
		                pst = con.prepareStatement("select client,vendeur,marque,model,no_moteur,couleur,prix,date from comptable_vente where id_vente = ?");
		                pst.setString(1, id);
	                    ResultSet rs = pst.executeQuery();
						
						//////////table.setModel(DbUtils.resultSetToTableModel(rs));///////////////////
						
						if(rs.next()==true)
						{
							String client = rs.getString(1);
							String vendeur = rs.getString(2);
							String marque = rs.getString(3);
							String model = rs.getString(4);
							String moteur = rs.getString(5);
							String couleur = rs.getString(6);
							String prix = Decrypt_Prix(rs.getString(7));
							String date = rs.getString(8);
							
							     txtclient.setText(client);
			                     txtvendeur.setText(vendeur);
			                     txtmarque.setText(marque);
			                     txtmodel.setText(model);
			                     txtmoteur.setText(moteur);
			                     txtcouleur.setText(couleur);
			                     txtprix.setText(prix);
			                     txtdate.setText(date);
			                    
						}
						
						else {
							
							txtclient.setText("");
			                txtvendeur.setText("");
			                txtmarque.setText("");
			                txtmodel.setText("");
			                txtmoteur.setText("");
			                txtcouleur.setText("");
			                txtprix.setText("");
			                txtdate.setText("");
							
						}
					
				} catch (SQLException ex) {
					
				}
			}
		});
		txtid.setBounds(99, 25, 96, 25);
		panel_1.add(txtid);
		txtid.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = txtid.getText();
				String client = txtclient.getText();
				String vendeur = txtvendeur.getText();
				String marque = txtmarque.getText();
				String model = txtmodel.getText();
				String moteur = txtmoteur.getText();
				String couleur = txtcouleur.getText();
				String prix = txtprix.getText();
				String date = txtdate.getText();
				
		try {
					
					pst = con.prepareStatement("delete from comptable_vente where id_vente =?");
					
					 pst.setString(1, id);

                     pst.executeUpdate();

                     JOptionPane.showMessageDialog(null, "Record Deleted!");

                     table_load();
                     
                     txtclient.setText("");
                     txtvendeur.setText("");
                     txtmarque.setText("");
                     txtmodel.setText("");
                     txtmoteur.setText("");
                     txtcouleur.setText("");
                     txtprix.setText("");
                     txtdate.setText("");
                     txtclient.requestFocus();
				}
				catch (SQLException e1) {                   

                    e1.printStackTrace();

       }
				
			}
		});
		btnDelete.setFont(new Font("Arial", Font.BOLD, 18));
		btnDelete.setBounds(571, 620, 92, 33);
		frame.getContentPane().add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous quitter la page 'Vente'?",
						"Warning", dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					Vente.this.frame.setVisible(false);
					login_connection.main(login);				
				}
			}
		});
		btnExit.setFont(new Font("Arial", Font.BOLD, 18));
		btnExit.setBounds(772, 620, 85, 33);
		frame.getContentPane().add(btnExit);
		
		JButton btnRapport = new JButton("Rapport");
		btnRapport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Rapport.main(login);
				
			}
		});
		btnRapport.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRapport.setBounds(947, 620, 97, 33);
		frame.getContentPane().add(btnRapport);
	}
}
