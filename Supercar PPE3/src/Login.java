import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;


//import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login {

	private JFrame frame;
	private JTextField txtuser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
		Connect();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JPasswordField passwordField;
	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/backoffice", "root","");
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          
	        }
	        catch (SQLException ex) 
	        {
	        	 
	        }
	    }


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 654, 383);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblLogin.setBounds(280, 35, 90, 29);
		frame.getContentPane().add(lblLogin);
		
		JLabel lbluser = new JLabel("User");
		lbluser.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbluser.setBounds(95, 102, 95, 16);
		frame.getContentPane().add(lbluser);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPassword.setBounds(95, 165, 83, 16);
		frame.getContentPane().add(lblPassword);
//		
//		JLabel lblUserType = new JLabel("User Type");
//		lblUserType.setFont(new Font("Tahoma", Font.PLAIN, 17));
//		lblUserType.setBounds(95, 218, 95, 29);
//		frame.getContentPane().add(lblUserType);
		
		txtuser = new JTextField();
		txtuser.setBounds(243, 101, 193, 22);
		frame.getContentPane().add(txtuser);
		txtuser.setColumns(10);
		
//		JComboBox<String> comboBox = new JComboBox<String>();
//		comboBox.setBounds(243, 223, 193, 22);
//		frame.getContentPane().add(comboBox);
//		
//		comboBox.addItem("User");
//		comboBox.addItem("Admin");
//		comboBox.addItem("Vendeur");
//		comboBox.addItem("Comptable");
//		comboBox.addItem("RH");
//		comboBox.addItem("Manager");
//		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				String login;
				char[] password;
//				Object usertype;
			
				
						
				login = txtuser.getText();
				password = passwordField.getPassword();
//				usertype = comboBox.getSelectedItem();
				
				
				
							
				 try {
					 
					 
					pst = con.prepareStatement("insert into users(login,password,usertype)values(?,?,?)");
					pst.setString(1, login);
					pst.setString(2, new String(password));
//					pst.setString(3, (String) usertype);
					pst.executeUpdate();
					
					if(password.length < 12) {
						JOptionPane.showMessageDialog(null, "Password must contain at least 12 character!");
					}
					
//					if (login.isEmpty()) {
//						JOptionPane.showMessageDialog(null, "Enter Login & Password!");
//					}
					
					
						          
					txtuser.setText("");
					passwordField.setText("");
//					comboBox.setSelectedItem("");
					txtuser.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				
				
				
				
			}
			}
		});
		btnNewButton.setBounds(273, 231, 97, 42);
		frame.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(243, 164, 193, 22);
		frame.getContentPane().add(passwordField);
	}
}
