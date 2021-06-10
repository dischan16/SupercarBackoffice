import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Haashim Potyram
 *
 */

public class Comptable {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void NewComptable() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Comptable window = new Comptable();
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
	public Comptable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblComptabilite = new JLabel("Comptabilit\u00E9");
		lblComptabilite.setForeground(Color.RED);
		lblComptabilite.setHorizontalAlignment(SwingConstants.CENTER);
		lblComptabilite.setFont(new Font("Arial Black", Font.BOLD, 35));
		lblComptabilite.setBounds(216, 23, 305, 69);
		frame.getContentPane().add(lblComptabilite);
		
		JButton btnListeDesFournisseurs = new JButton("Fournisseur");
		btnListeDesFournisseurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*InterFournisseur f = new InterFournisseur();
				f.NewFournisseur();*/
			}
		});
		btnListeDesFournisseurs.setForeground(Color.BLUE);
		btnListeDesFournisseurs.setFont(new Font("Arial Black", Font.BOLD, 25));
		btnListeDesFournisseurs.setBounds(186, 119, 385, 62);
		frame.getContentPane().add(btnListeDesFournisseurs);
		
		JButton btnListeDesVentes = new JButton("Ventes");
		btnListeDesVentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*Vente V = new Vente();
				V.NewVente();*/
			}
		});
		btnListeDesVentes.setForeground(Color.BLUE);
		btnListeDesVentes.setFont(new Font("Arial Black", Font.BOLD, 25));
		btnListeDesVentes.setBounds(186, 234, 385, 62);
		frame.getContentPane().add(btnListeDesVentes);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnLogout.setForeground(Color.BLUE);
		btnLogout.setFont(new Font("Arial Black", Font.BOLD, 23));
		btnLogout.setBounds(264, 354, 198, 49);
		frame.getContentPane().add(btnLogout);
	}
}