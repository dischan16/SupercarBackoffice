import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Haashim Potyram
 *
 */

public class LoginComptable {

	private JFrame frame;
	private JTextField textUsername;
	private JTextField textPassword;
	private int validationCounter = 0;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginComptable window = new LoginComptable();
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
	public LoginComptable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.YELLOW);
		frame.setBounds(100, 100, 750, 550);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenue Dans le departement Comptable");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(120, 25, 513, 69);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setForeground(Color.GREEN);
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Arial", Font.BOLD, 22));
		lblUsername.setBounds(39, 184, 139, 49);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(Color.GREEN);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 22));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(39, 306, 139, 43);
		frame.getContentPane().add(lblPassword);
		
		textUsername = new JTextField();
		textUsername.setBounds(262, 184, 297, 49);
		frame.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setBounds(262, 303, 297, 46);
		frame.getContentPane().add(textPassword);
		textPassword.setColumns(10);
		
		JButton btnSigIn = new JButton("Sign in");
		btnSigIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = textUsername.getText();
				String password = textPassword.getText();
				
				if (username.contains("haashim") && password.contains("1234")) {
					
					textUsername.setText(null);
					textPassword.setText(null);
					
					Comptable c = new Comptable();
					c.NewComptable();
				}
				
				else 
				{
					JOptionPane.showMessageDialog(null, "Invalid Login Details","Login Error",JOptionPane.ERROR_MESSAGE);
					textUsername.setText(null);
					textPassword.setText(null);
					
					///////////////Junit Testing/////////////////
					
					/*public boolean isValid(String username, String password,String confirm)
					throws EmptyException
					{
						if (username.length() == 0) throw new EmptyloginException();
						if (password.equals("") || confirm.equals("")) throw new EmptyPasswordException();
						if (username.length() < 3) return false;
						if (password != confirm) return false;
						validationCounter++;
						return true;										
						
					}
					
					public int getValidationCounter() {
						
						return validationCounter;
					}*/
					
				}
				
			}
		});
		btnSigIn.setForeground(Color.RED);
		btnSigIn.setFont(new Font("Arial Black", Font.BOLD, 22));
		btnSigIn.setBounds(39, 431, 155, 43);
		frame.getContentPane().add(btnSigIn);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textUsername.setText(null);
				textPassword.setText(null);
			}
		});
		btnReset.setForeground(Color.RED);
		btnReset.setFont(new Font("Arial Black", Font.BOLD, 22));
		btnReset.setBounds(298, 431, 139, 43);
		frame.getContentPane().add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "LoginSystem", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Arial Black", Font.BOLD, 22));
		btnExit.setForeground(Color.RED);
		btnExit.setBounds(524, 431, 121, 43);
		frame.getContentPane().add(btnExit);
	}
}