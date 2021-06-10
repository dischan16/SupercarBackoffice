import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

/***
 * 
 * @author Haashim Potyram
 *
 */


public class login_connection {

	private JFrame frame;
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textLogin;
	private JPasswordField textPwd;
	

	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_connection window = new login_connection(login);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public login_connection(String login) {
		initialize(login);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize(String login) {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setBounds(100, 100, 1092, 704);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		

		JLabel lblconnected = new JLabel("");
		lblconnected.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		lblconnected.setHorizontalAlignment(SwingConstants.CENTER);
		lblconnected.setBounds(85, 95, 217, 46);
		frame.getContentPane().add(lblconnected);
		
		AdminAccount account = new AdminAccount();
		account.DatabaseConnexion(login, null, null, frame);
		lblconnected.setText("Salut " + account.name);

		
		/**
		 *  Privilege pour l'RH
		 */
		
		
		/*if (account.getAccountType().contains("RH")) {
			
			JLabel lblHome = new JLabel("RESSOURCES HUMAINES");
			lblHome.setFont(new Font("Calibri Light", Font.PLAIN, 45));
			lblHome.setHorizontalAlignment(SwingConstants.CENTER);
			lblHome.setBounds(366, 23, 459, 46);
			frame.getContentPane().add(lblHome);
		
			JButton btnEmploye = new JButton("Employee");
			btnEmploye.setFont(new Font("Dialog", Font.BOLD, 16));
			btnEmploye.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					login_connection.this.frame.setVisible(false);
					employee.main(login);
				}
			});
			btnEmploye.setBounds(149, 190, 242, 86);
			frame.getContentPane().add(btnEmploye);
			
			JButton btnFicheSalaire = new JButton("Fiche Salaire");
			btnFicheSalaire.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login_connection.this.frame.setVisible(false);
					fiche_salaire.main(login);
				}
			});
			btnFicheSalaire.setFont(new Font("Dialog", Font.BOLD, 16));
			btnFicheSalaire.setBounds(149, 404, 242, 86);
			frame.getContentPane().add(btnFicheSalaire);
			
			JButton btnDepartement = new JButton("Departement");
			btnDepartement.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login_connection.this.frame.setVisible(false);
					departement.main(login);
				}
			});
			btnDepartement.setFont(new Font("Dialog", Font.BOLD, 16));
			btnDepartement.setBounds(738, 190, 242, 86);
			frame.getContentPane().add(btnDepartement);
	}*/
		
		/**
		 *  Privilege Vente
		 */
		
		
		/*if (account.getAccountType().contains("Vente")) {
			
			JLabel lblHome = new JLabel("Vente");
			lblHome.setFont(new Font("Calibri Light", Font.PLAIN, 45));
			lblHome.setHorizontalAlignment(SwingConstants.CENTER);
			lblHome.setBounds(264, 27, 459, 46);
			frame.getContentPane().add(lblHome);
			
			JButton btnTestDeConduite = new JButton("Test de conduite");
			btnTestDeConduite.setFont(new Font("Dialog", Font.BOLD, 16));
			btnTestDeConduite.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login_connection.this.frame.setVisible(false);
					Test_Conduite.main(login);
				}
			});
			btnTestDeConduite.setBounds(698, 367, 217, 90);
			frame.getContentPane().add(btnTestDeConduite);
			
			JButton btnClient = new JButton("Client");
			btnClient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login_connection.this.frame.setVisible(false);
					vendeur_client.main(login);
				}
			});
			btnClient.setFont(new Font("Dialog", Font.BOLD, 16));
			btnClient.setBounds(68, 186, 217, 90);
			frame.getContentPane().add(btnClient);
			
			
			JButton btnDevis = new JButton("Devis");
			btnDevis.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login_connection.this.frame.setVisible(false);
					vendeur_devis.main(login);
				}
			});
			btnDevis.setFont(new Font("Dialog", Font.BOLD, 16));
			btnDevis.setBounds(68, 367, 217, 90);
			frame.getContentPane().add(btnDevis);
			
			
			JButton btnStock = new JButton("Stock");
			btnStock.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login_connection.this.frame.setVisible(false);
					vendeur_int.main(login);
				}
			});
			btnStock.setFont(new Font("Dialog", Font.BOLD, 16));
			btnStock.setBounds(386, 285, 217, 90);
			frame.getContentPane().add(btnStock);
			
			JButton btnCommande = new JButton("Commande");
			btnCommande.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login_connection.this.frame.setVisible(false);
					vendeur_commande_.main(login);
				}
			});
			btnCommande.setFont(new Font("Dialog", Font.BOLD, 16));
			btnCommande.setBounds(704, 186, 217, 90);
			frame.getContentPane().add(btnCommande);
		
		}*/
		
		/**
		 *  Privilege pour Comptabilité
		 */
		
		
		if (account.getAccountType().contains("Comptabilite")) {
			
			JLabel lblHome = new JLabel("Comptabilite");
			lblHome.setFont(new Font("Calibri Light", Font.PLAIN, 45));
			lblHome.setHorizontalAlignment(SwingConstants.CENTER);
			lblHome.setBounds(366, 23, 459, 46);
			frame.getContentPane().add(lblHome);
		
			JButton btnFournisseur = new JButton("Fournisseur");
			btnFournisseur.setFont(new Font("Dialog", Font.BOLD, 16));
			btnFournisseur.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					login_connection.this.frame.setVisible(false);
					InterFournisseur.main(login);
				}
			});
			btnFournisseur.setBounds(149, 190, 242, 86);
			frame.getContentPane().add(btnFournisseur);
			
			JButton btnVente = new JButton("Vente");
			btnVente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login_connection.this.frame.setVisible(false);
					Vente.main(login);
				}
			});
			btnVente.setFont(new Font("Dialog", Font.BOLD, 16));
			btnVente.setBounds(149, 404, 242, 86);
			frame.getContentPane().add(btnVente);
		
	}
		
		if (account.getAccountType().contains("Administration")) {
			

			JLabel lblHome = new JLabel("Administration");
			lblHome.setFont(new Font("Calibri Light", Font.PLAIN, 45));
			lblHome.setHorizontalAlignment(SwingConstants.CENTER);
			lblHome.setBounds(366, 23, 459, 46);
			frame.getContentPane().add(lblHome);
		
			JButton btnAdministration = new JButton("Administration");
			btnAdministration.setFont(new Font("Dialog", Font.BOLD, 16));
			btnAdministration.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					login_connection.this.frame.setVisible(false);
					Administration.main(login);
				}
			});
			btnAdministration.setFont(new Font("Dialog", Font.BOLD, 16));
			btnAdministration.setBounds(149, 404, 242, 86);
			frame.getContentPane().add(btnAdministration);
			
			
		}
		
		/**
		 *  Privilege Manager
		 */
	

		/*if (account.getAccountType().contains("Manager")) {
			
			JLabel lblHome = new JLabel("Manager Platform");
			lblHome.setFont(new Font("Calibri Light", Font.PLAIN, 45));
			lblHome.setHorizontalAlignment(SwingConstants.CENTER);
			lblHome.setBounds(327, 26, 459, 46);
			frame.getContentPane().add(lblHome);
			
			JButton btnTestDeConduite = new JButton("Test de conduite");
			btnTestDeConduite.setFont(new Font("Dialog", Font.BOLD, 16));
			btnTestDeConduite.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login_connection.this.frame.setVisible(false);
					Test_Conduite.main(login);
				}
			});
			btnTestDeConduite.setBounds(738, 186, 217, 90);
			frame.getContentPane().add(btnTestDeConduite);
			
			JButton btnClient = new JButton("Client");
			btnClient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login_connection.this.frame.setVisible(false);
					vendeur_client.main(login);
				}
			});
			btnClient.setFont(new Font("Dialog", Font.BOLD, 16));
			btnClient.setBounds(68, 186, 217, 90);
			frame.getContentPane().add(btnClient);
			
			
			JButton btnDevis = new JButton("Devis");
			btnDevis.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login_connection.this.frame.setVisible(false);
					vendeur_devis.main(login);
				}
			});
			btnDevis.setFont(new Font("Dialog", Font.BOLD, 16));
			btnDevis.setBounds(68, 305, 217, 90);
			frame.getContentPane().add(btnDevis);
			
			
			JButton btnStock = new JButton("Stock");
			btnStock.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login_connection.this.frame.setVisible(false);
					vendeur_int.main(login);
				}
			});
			btnStock.setFont(new Font("Dialog", Font.BOLD, 16));
			btnStock.setBounds(68, 428, 217, 90);
			frame.getContentPane().add(btnStock);
			
			JButton btnCommande = new JButton("Commande");
			btnCommande.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login_connection.this.frame.setVisible(false);
					vendeur_commande_.main(login);
				}
			});
			btnCommande.setFont(new Font("Dialog", Font.BOLD, 16));
			btnCommande.setBounds(68, 550, 217, 90);
			frame.getContentPane().add(btnCommande);
			
			JButton btnEmploye = new JButton("Employee");
			btnEmploye.setFont(new Font("Dialog", Font.BOLD, 16));
			btnEmploye.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					login_connection.this.frame.setVisible(false);
					employee.main(login);
				}
			});
			btnEmploye.setBounds(407, 188, 217, 86);
			frame.getContentPane().add(btnEmploye);
			
			JButton btnDepartement = new JButton("Departement");
			btnDepartement.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					login_connection.this.frame.setVisible(false);
					departement.main(login);
				}
			});
			btnDepartement.setFont(new Font("Dialog", Font.BOLD, 16));
			btnDepartement.setBounds(407, 307, 217, 86);
			frame.getContentPane().add(btnDepartement);
			
			JButton btnChiffreDaffaire = new JButton("Chiffre D'affaire");
			btnChiffreDaffaire.setFont(new Font("Dialog", Font.BOLD, 16));
			btnChiffreDaffaire.setBounds(407, 428, 217, 86);
			frame.getContentPane().add(btnChiffreDaffaire);
			
			JButton btncommission = new JButton("Commission");
			btncommission.setFont(new Font("Dialog", Font.BOLD, 16));
			btncommission.setBounds(407, 550, 217, 86);
			frame.getContentPane().add(btncommission);
	}*/
		
		/**
		 *  Privilege Admin
		 */
		
		if (account.getAccountType().contains("admin")) {
			
			JPanel panel = new JPanel();
			panel.setBackground(SystemColor.menu);
			panel.setForeground(Color.WHITE);
			panel.setBorder(null);
			panel.setBounds(64, 142, 499, 317);
			frame.getContentPane().add(panel);
			panel.setLayout(null);

			JLabel lblNom = new JLabel("nom");
			lblNom.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNom.setHorizontalAlignment(SwingConstants.CENTER);
			lblNom.setBounds(10, 50, 113, 29);
			panel.add(lblNom);

			JLabel lblPrenom = new JLabel("prenom");
			lblPrenom.setHorizontalAlignment(SwingConstants.CENTER);
			lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblPrenom.setBounds(10, 100, 113, 29);
			panel.add(lblPrenom);

			JLabel lblLogin = new JLabel("login");
			lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
			lblLogin.setBounds(10, 150, 113, 29);
			panel.add(lblLogin);

			JLabel lblPwd = new JLabel("password");
			lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
			lblPwd.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblPwd.setBounds(10, 200, 113, 29);
			panel.add(lblPwd);

			JLabel lblType = new JLabel("type");
			lblType.setHorizontalAlignment(SwingConstants.CENTER);
			lblType.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblType.setBounds(10, 255, 113, 29);
			panel.add(lblType);

			textNom = new JTextField();
			textNom.setBounds(129, 50, 298, 29);
			panel.add(textNom);
			textNom.setColumns(10);

			textPrenom = new JTextField();
			textPrenom.setColumns(10);
			textPrenom.setBounds(129, 100, 298, 29);
			panel.add(textPrenom);

			textLogin = new JTextField();
			textLogin.setColumns(10);
			textLogin.setBounds(129, 154, 298, 29);
			panel.add(textLogin);

			textPwd = new JPasswordField();
			textPwd.setBounds(129, 199, 294, 29);
			panel.add(textPwd);

			JComboBox textType = new JComboBox();
			textType.setFont(new Font("Tahoma", Font.PLAIN, 20));
			textType.setBounds(129, 255, 298, 29);
			panel.add(textType);
			textType.addItem("user");
			textType.addItem("admin");
			textType.addItem("Vente");
			textType.addItem("RH");
			textType.addItem("Comptabilite");
			textType.addItem("Manager");
			
			JButton btnAdd = new JButton("Ajouter");
			btnAdd.setBounds(121, 506, 119, 42);
			frame.getContentPane().add(btnAdd);
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AdminAccount add = new AdminAccount();
					add.surname = textNom.getText();
					add.name = textPrenom.getText();
					add.login = textLogin.getText();
					add.setPassword(String.valueOf(textPwd.getPassword()));
					add.setAccountType(textType.getSelectedItem().toString());
					try {
						add.addUser(login, frame);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		
		}
		
		/**
		 *  Option Deconnexion
		 */
		
		JButton btnLogOut = new JButton("Deconnexion");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous vous deconnectez?", "Warning",
						dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					System.exit(1);
				}
			}
		});
		
		
		btnLogOut.setBounds(940, 599, 122, 46);
		frame.getContentPane().add(btnLogOut);
				
		
	}
}
