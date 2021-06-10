import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Rapport {

	private AdminAccount account = new AdminAccount();
	private JFrame frame;
	private String date;
	private String client;
	private String vendeur;
	private String marque;
	private String model;
	private String no_moteur;
	private String couleur;
	private String prix;
	@SuppressWarnings({ "rawtypes", "unused" })
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rapport window = new Rapport(login);
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
	public Rapport(String login) {
		Connect();
		initialize(login);
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtDate;
	private JTable table;
	private JTextField txtid;
	private JTextField txtclient;
	private JTextField txtvendeur;
	private JTextField txtmarque;
	private JTextField txtmodel;
	private JTextField txtmoteur;
	private JTextField txtcouleur;
	private JTextField txtprix;
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
		
	public void table_load(String type) {
		
		///////////////Jour////////////////
		
		if (type == "jour") {
			try {
				pst = con.prepareStatement("select * from comptable_vente where (date) = (?)");
				pst.setString(1, date);
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
  				table.getColumnModel().getColumn(1).setPreferredWidth(200); //Client
  				table.getColumnModel().getColumn(2).setPreferredWidth(200);  // Vendeur
  				table.getColumnModel().getColumn(3).setPreferredWidth(200); // Marque
  				table.getColumnModel().getColumn(4).setPreferredWidth(100); // Model
  				table.getColumnModel().getColumn(5).setPreferredWidth(200); // no_moteur
  				table.getColumnModel().getColumn(6).setPreferredWidth(100); // couleur
  				table.getColumnModel().getColumn(7).setPreferredWidth(200); // prix
  				table.getColumnModel().getColumn(8).setPreferredWidth(200); // date
  				
  				table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
  				
  				String client = txtclient.getText();
  				String vendeur = txtvendeur.getText();
  				String marque = txtmarque.getText();
  				String model = txtmodel.getText();
  				String moteur = txtmoteur.getText();
  				String couleur = txtcouleur.getText();
  				String prix = Decrypt_Prix(txtprix.getText());
  				String date = txtdate.getText();
				
				JFileChooser dialog = new JFileChooser();
				dialog.setSelectedFile(new File(client+"_Vendeur"+".pdf"));
				int dialogResult = dialog.showSaveDialog(null);
				if (dialogResult==JFileChooser.APPROVE_OPTION) {
					String filePath = dialog.getSelectedFile().getPath();
					
					try {
						
						Document myDoc = new Document();
						@SuppressWarnings("unused")
						PdfWriter  myWriter = PdfWriter.getInstance(myDoc, new FileOutputStream(filePath));
						myDoc.open();						
						myDoc.add(new Paragraph("Vendeur", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));						
						myDoc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
						myDoc.add(new Paragraph("Detail de la vente", FontFactory.getFont(FontFactory.HELVETICA,15)));
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
				if (!rs.isBeforeFirst()) {
					JOptionPane.showMessageDialog(null, "aucune données trouvé pour la date selectionée !");
					txtDate.setText("");
				} else {
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (type == "mois") {	
			
			////////////////////Mois////////////////////////
			
			try {
				
				pst = con.prepareStatement("select * from comptable_vente where month(date) = MONTH(?) and year(date) = year(?)");
				pst.setString(1, date);
				pst.setString(2, date);
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
  				table.getColumnModel().getColumn(1).setPreferredWidth(200); //Client
  				table.getColumnModel().getColumn(2).setPreferredWidth(200);  // Vendeur
  				table.getColumnModel().getColumn(3).setPreferredWidth(200); // Marque
  				table.getColumnModel().getColumn(4).setPreferredWidth(100); // Model
  				table.getColumnModel().getColumn(5).setPreferredWidth(200); // no_moteur
  				table.getColumnModel().getColumn(6).setPreferredWidth(100); // couleur
  				table.getColumnModel().getColumn(7).setPreferredWidth(200); // prix
  				table.getColumnModel().getColumn(8).setPreferredWidth(200); // date
  				
  				table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
  				
  				String client = txtclient.getText();
  				String vendeur = txtvendeur.getText();
  				String marque = txtmarque.getText();
  				String model = txtmodel.getText();
  				String moteur = txtmoteur.getText();
  				String couleur = txtcouleur.getText();
  				String prix = Decrypt_Prix(txtprix.getText());
  				String date = txtdate.getText();
				
				JFileChooser dialog = new JFileChooser();
				dialog.setSelectedFile(new File(client+"_Vendeur"+".pdf"));
				int dialogResult = dialog.showSaveDialog(null);
				if (dialogResult==JFileChooser.APPROVE_OPTION) {
					String filePath = dialog.getSelectedFile().getPath();
					
					try {
						
						Document myDoc = new Document();
						@SuppressWarnings("unused")
						PdfWriter  myWriter = PdfWriter.getInstance(myDoc, new FileOutputStream(filePath));
						myDoc.open();						
						myDoc.add(new Paragraph("Vendeur", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));						
						myDoc.add(new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA,20)));
						myDoc.add(new Paragraph("Detail de la vente", FontFactory.getFont(FontFactory.HELVETICA,15)));
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
				
				if (!rs.isBeforeFirst()) {
					JOptionPane.showMessageDialog(null, "aucune données trouvé pour la date selectionée !");
					txtDate.setText("");
				} else {
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
		}
	}
		////////////////Année////////////////
	
	if (type == "annee") {
		try {
			pst = con.prepareStatement("select * from comptable_vente where year(date) = year(?)");
			pst.setString(1, date);
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
				table.getColumnModel().getColumn(1).setPreferredWidth(200); //Client
				table.getColumnModel().getColumn(2).setPreferredWidth(200);  // Vendeur
				table.getColumnModel().getColumn(3).setPreferredWidth(200); // Marque
				table.getColumnModel().getColumn(4).setPreferredWidth(100); // Model
				table.getColumnModel().getColumn(5).setPreferredWidth(200); // no_moteur
				table.getColumnModel().getColumn(6).setPreferredWidth(100); // couleur
				table.getColumnModel().getColumn(7).setPreferredWidth(200); // prix
				table.getColumnModel().getColumn(8).setPreferredWidth(200); // date
				
				table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
				
				String client = txtclient.getText();
  				String vendeur = txtvendeur.getText();
  				String marque = txtmarque.getText();
  				String model = txtmodel.getText();
  				String moteur = txtmoteur.getText();
  				String couleur = txtcouleur.getText();
  				String prix = Decrypt_Prix(txtprix.getText());
  				String date = txtdate.getText();
			
			JFileChooser dialog = new JFileChooser();
			dialog.setSelectedFile(new File(client+"Voiture"+".pdf"));
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
			if (!rs.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null, "aucune données trouvé pour la date selectionée !");
				txtDate.setText("");
			} else {
				table.setModel(DbUtils.resultSetToTableModel(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String login) {
		account.DatabaseConnexion(login, null, null, frame);
		System.out.print(account.getId());
		frame = new JFrame();
		frame.setBounds(100, 100, 1250, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRapportDeVentes = new JLabel("Rapport de Ventes Des Voitures");
		lblRapportDeVentes.setBounds(167, 10, 354, 53);
		lblRapportDeVentes.setFont(new Font("Arial", Font.BOLD, 21));
		lblRapportDeVentes.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblRapportDeVentes);
		
		JLabel lblFaitesVotreChoix = new JLabel("Faites votre choix :");
		lblFaitesVotreChoix.setBounds(10, 172, 170, 36);
		lblFaitesVotreChoix.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblFaitesVotreChoix);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(211, 175, 170, 36);
		comboBox.addItem("jour");
		comboBox.addItem("mois");
		comboBox.addItem("annee");
		frame.getContentPane().add(comboBox);
		
		JLabel lblDate = new JLabel("Entrez une date(aaaa/mm/j) :");
		lblDate.setBounds(10, 96, 246, 46);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setBounds(266, 99, 161, 46);
		frame.getContentPane().add(txtDate);
		txtDate.setColumns(10);
		
		JButton btnAfficherMensuel = new JButton("Anne");
		btnAfficherMensuel.setBounds(211, 368, 170, 46);
		btnAfficherMensuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				date = txtDate.getText();
				table_load(comboBox.getSelectedItem().toString());	
				
			}
		});		
		btnAfficherMensuel.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(btnAfficherMensuel);
		
		JButton btnAfficherHebdomadaire = new JButton("Mois");
		btnAfficherHebdomadaire.setBounds(167, 297, 238, 46);
		btnAfficherHebdomadaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				date = txtDate.getText();
				table_load(comboBox.getSelectedItem().toString());
			}
		});
		
		btnAfficherHebdomadaire.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(btnAfficherHebdomadaire);
		
		JButton btnJournalier = new JButton("Jour");
		btnJournalier.setBounds(190, 234, 202, 36);
		btnJournalier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				date = txtDate.getText();
				table_load(comboBox.getSelectedItem().toString());
				
			}
		});
		btnJournalier.setFont(new Font("Tahoma", Font.BOLD, 17));
		frame.getContentPane().add(btnJournalier);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(442, 98, 773, 349);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(243, 443, 85, 36);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous quitter la page 'Rapport'?",
						"Warning", dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					Rapport.this.frame.setVisible(false);
					login_connection.main(login);
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		frame.getContentPane().add(btnExit);
		
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
		txtid.setBounds(10, 218, 120, 19);
		frame.getContentPane().add(txtid);
		txtid.setColumns(10);
		
		txtclient = new JTextField();
		txtclient.setBounds(10, 246, 120, 19);
		frame.getContentPane().add(txtclient);
		txtclient.setColumns(10);
		
		txtvendeur = new JTextField();
		txtvendeur.setBounds(10, 275, 120, 19);
		frame.getContentPane().add(txtvendeur);
		txtvendeur.setColumns(10);
		
		txtmarque = new JTextField();
		txtmarque.setBounds(10, 302, 120, 19);
		frame.getContentPane().add(txtmarque);
		txtmarque.setColumns(10);
		
		txtmodel = new JTextField();
		txtmodel.setBounds(10, 331, 120, 19);
		frame.getContentPane().add(txtmodel);
		txtmodel.setColumns(10);
		
		txtmoteur = new JTextField();
		txtmoteur.setBounds(10, 360, 120, 19);
		frame.getContentPane().add(txtmoteur);
		txtmoteur.setColumns(10);
		
		txtcouleur = new JTextField();
		txtcouleur.setBounds(10, 395, 120, 19);
		frame.getContentPane().add(txtcouleur);
		txtcouleur.setColumns(10);
		
		txtprix = new JTextField();
		txtprix.setBounds(10, 424, 120, 19);
		frame.getContentPane().add(txtprix);
		txtprix.setColumns(10);
		
		txtdate = new JTextField();
		txtdate.setBounds(10, 454, 120, 19);
		frame.getContentPane().add(txtdate);
		txtdate.setColumns(10);
		
	}
}
