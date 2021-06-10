

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 
 * @author Khushveer
 *
 */

public class User_Login {

	private JFrame frame;
	private JTextField textLogin;
	private JPasswordField textPwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_Login window = new User_Login();
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
	public User_Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame
	 * 
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 673, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblTitle = new JLabel("LOGIN");
		lblTitle.setFont(new Font("Calibri Light", Font.BOLD, 38));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(198, 13, 249, 64);
		frame.getContentPane().add(lblTitle);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(40, 115, 88, 33);
		frame.getContentPane().add(lblLogin);

		JLabel lblPwd = new JLabel("Mot de passe");
		lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPwd.setBounds(46, 189, 112, 32);
		frame.getContentPane().add(lblPwd);
		textLogin = new JTextField();
		textLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					AdminAccount login = new AdminAccount();
					login.login = textLogin.getText();
					login.setPassword((String.copyValueOf(textPwd.getPassword())));
					login.setPassword(login.hashPassword(login.getPassword()));
					try {
						login.DatabaseConnexion(login.login, login.getPassword(), "login", User_Login.this.frame);
					} catch (Exception sqlException) {
						sqlException.printStackTrace();
					}
				}
			}
		});
		textLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textLogin.setBounds(168, 117, 359, 32);
		frame.getContentPane().add(textLogin);
		textLogin.setColumns(10);

		JButton btnConnexion = new JButton("LOGIN");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAccount login = new AdminAccount();
				login.login = textLogin.getText();
				login.setPassword((String.copyValueOf(textPwd.getPassword())));
				login.setPassword(login.hashPassword(login.getPassword()));
				try {
					login.DatabaseConnexion(login.login, login.getPassword(), "login", User_Login.this.frame);
				} catch (Exception sqlException) {
					sqlException.printStackTrace();
				}
			}
		});
		btnConnexion.setBounds(262, 265, 121, 44);
		frame.getContentPane().add(btnConnexion);
		textPwd = new JPasswordField();

		textPwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					AdminAccount login = new AdminAccount();
					login.login = textLogin.getText();
					login.setPassword((String.copyValueOf(textPwd.getPassword())));
					login.setPassword(login.hashPassword(login.getPassword()));
					try {
						login.DatabaseConnexion(login.login, login.getPassword(), "login", User_Login.this.frame);
					} catch (Exception sqlException) {
						sqlException.printStackTrace();
					}
				}
			}
		});
		textPwd.setBounds(168, 193, 359, 32);
		frame.getContentPane().add(textPwd);
	}
}
