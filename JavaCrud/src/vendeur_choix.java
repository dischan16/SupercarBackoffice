import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class vendeur_choix {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vendeur_choix window = new vendeur_choix();
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
	public vendeur_choix() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INTERFACE VENDEUR");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(135, 10, 263, 33);
		panel.add(lblNewLabel);
		
		JButton btnCommande = new JButton("COMMANDE");
		btnCommande.setBounds(56, 94, 122, 33);
		panel.add(btnCommande);
		
		JButton btnStock = new JButton("STOCK");
		btnStock.setBounds(56, 157, 122, 33);
		panel.add(btnStock);
		
		JButton btnVente = new JButton("VENTE");
		btnVente.setBounds(256, 94, 122, 33);
		panel.add(btnVente);
		
		JButton btnClient = new JButton("CLIENT");
		btnClient.setBounds(256, 157, 122, 33);
		panel.add(btnClient);
		
		JButton btnDevis = new JButton("DEVIS");
		btnDevis.setBounds(162, 209, 122, 33);
		panel.add(btnDevis);
	}
}
