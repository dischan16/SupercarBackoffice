import java.awt.EventQueue;
import java.sql.*;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import net.proteanit.sql.DbUtils;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class vendeur_int {

	private JFrame frame;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String login) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vendeur_int window = new vendeur_int(login);
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
	public vendeur_int(String login) {
		initialize(login);
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JButton btnSortir;

 
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
	 
	  public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select * from voitures");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	 
	

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize(String login) {
		AdminAccount account = new AdminAccount();
		account.DatabaseConnexion(login, null, null, frame);
		System.out.print(account.getId());
		frame = new JFrame();
		frame.setBounds(100, 100, 1206, 591);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("STOCK");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(523, 0, 229, 79);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 77, 1166, 370);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnSortir = new JButton("Sortir");
		btnSortir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null, "Souhaitez-vous quitter la page 'Stock'?", "Warning",
						dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
				vendeur_int.this.frame.setVisible(false);
				login_connection.main(login);
				}
			}
		});
		btnSortir.setBounds(117, 460, 127, 52);
		frame.getContentPane().add(btnSortir);
			}
}
