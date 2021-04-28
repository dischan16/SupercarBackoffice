import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class manager {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manager window = new manager();
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
	public manager() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 485, 634);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblManager = new JLabel("Manager Platform");
		lblManager.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblManager.setBounds(71, 24, 316, 79);
		frame.getContentPane().add(lblManager);
		
		JButton btnClient = new JButton("Client ");
		btnClient.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClient.setBounds(125, 135, 211, 85);
		frame.getContentPane().add(btnClient);
		
		JButton btnVoiture = new JButton("Voiture");
		btnVoiture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnVoiture.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnVoiture.setBounds(125, 282, 211, 85);
		frame.getContentPane().add(btnVoiture);
		
		JButton btnEmployee = new JButton("Employee");
		btnEmployee.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEmployee.setBounds(125, 423, 211, 85);
		frame.getContentPane().add(btnEmployee);
	}
}
