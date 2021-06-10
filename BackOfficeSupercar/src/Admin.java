import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

/**
 * 
 * @author Haashim Potyram
 *
 */

public class Admin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void NewAcceuil() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
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
	public Admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBienvenue = new JLabel("Bienvenue a l'Admin");
		lblBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenue.setBackground(Color.GREEN);
		lblBienvenue.setForeground(Color.BLUE);
		lblBienvenue.setFont(new Font("Arial Black", Font.BOLD, 20));
		lblBienvenue.setBounds(418, 45, 300, 54);
		frame.getContentPane().add(lblBienvenue);
		
		JButton btnAdministration = new JButton("Administration");
		btnAdministration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				/*Administration ad = new Administration();
				ad.NewAdministration();*/
				
			}
		});
		btnAdministration.setBackground(Color.GREEN);
		btnAdministration.setForeground(Color.BLUE);
		btnAdministration.setFont(new Font("Arial", Font.BOLD, 21));
		btnAdministration.setBounds(495, 144, 211, 60);
		frame.getContentPane().add(btnAdministration);
		
		JButton btnComptable = new JButton("Comptable");
		btnComptable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoginComptable lc = new LoginComptable();
				lc.NewScreen();
				
			}
		});
		btnComptable.setBackground(Color.GREEN);
		btnComptable.setForeground(Color.BLUE);
		btnComptable.setFont(new Font("Arial", Font.BOLD, 21));
		btnComptable.setBounds(495, 250, 223, 54);
		frame.getContentPane().add(btnComptable);
		
		JButton btnRh = new JButton("RH");
		btnRh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnRh.setBackground(Color.GREEN);
		btnRh.setForeground(Color.BLUE);
		btnRh.setFont(new Font("Arial", Font.BOLD, 19));
		btnRh.setBounds(495, 351, 211, 46);
		frame.getContentPane().add(btnRh);
		
		JButton btnVente = new JButton("Vente");
		btnVente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnVente.setBackground(Color.GREEN);
		btnVente.setForeground(Color.BLUE);
		btnVente.setFont(new Font("Arial", Font.BOLD, 21));
		btnVente.setBounds(495, 455, 211, 46);
		frame.getContentPane().add(btnVente);
		
		JButton btnEntrepots = new JButton("Entrepots");
		btnEntrepots.setBackground(Color.CYAN);
		btnEntrepots.setForeground(Color.BLUE);
		btnEntrepots.setFont(new Font("Arial", Font.BOLD, 25));
		btnEntrepots.setBounds(495, 543, 211, 46);
		frame.getContentPane().add(btnEntrepots);
	}

}
