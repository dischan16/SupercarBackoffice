import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author Khushveer
 *
 */

class AdminAccount extends Account {
	
	/**
	 * Les methodes pour generer les cles
	 */
	
	
	private Key masterKey;

	public Key getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(Key masterKey) {
		this.masterKey = masterKey;
	}

	public void decryptKey() throws IOException {
		String chaine = new String(Files.readAllBytes(Paths.get("key.cryp")));
		byte[] decodedKey = Base64.getDecoder().decode(chaine);
		setMasterKey(new SecretKeySpec(decodedKey, 0, decodedKey.length, "blowfish"));
	}

	public static byte[] encryptInByte(byte[] textClair, Key clef) throws Exception {

		Cipher chiffre = Cipher.getInstance("Blowfish");

		chiffre.init(Cipher.ENCRYPT_MODE, clef);

		return chiffre.doFinal(textClair); 
	}

	public static byte[] decryptInByte(byte[] textChiffre, Key clef) throws Exception {

		Cipher dechiffre = Cipher.getInstance("Blowfish");

		dechiffre.init(Cipher.DECRYPT_MODE, clef);

		byte[] textDechiffre = dechiffre.doFinal(textChiffre);

		return textDechiffre;
	}

	public static String encryptInString(String textClair, Key clef) throws Exception {

		byte[] chiffre = textClair.getBytes();

		chiffre = encryptInByte(chiffre, clef);

		return Base64.getEncoder().encodeToString(chiffre);

	}

	public static String decryptInString(String textChiffre, Key clef) throws Exception {

		byte[] dechiffre = Base64.getDecoder().decode(textChiffre);

		dechiffre = decryptInByte(dechiffre, clef);

		return new String(dechiffre);
	}
	
	/**
	 *  Connection avec la base de donnees
	 * @param accountLogin
	 * @param frame
	 * @throws Exception
	 */

	public void addUser(String accountLogin, JFrame frame) throws Exception {
		if (!verification()) {
			decryptKey();
			Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root",
					"");

			PreparedStatement st = (PreparedStatement) connection.prepareStatement(
					"INSERT INTO `login`(`name`, `surname`, `login`, `password`, `type`) VALUES (" + "'"
							+ name + "','" + surname + "','" + login + "','" + hashPassword(getPassword()) + "','"
							 + getAccountType() + "')");
			st.executeUpdate();
			JOptionPane.showMessageDialog(frame, "utilisateur ajouter");
			frame.setVisible(false);
			login_connection.main(accountLogin);
		}

	}

	public void DatabaseConnexion(String login, String password, String type, JFrame frame) {
		if (type == "login") {
			try {
				Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root",
						"");

				PreparedStatement st = (PreparedStatement) connection
						.prepareStatement("Select * from login where login=? and password=?");

				st.setString(1, login);
				st.setString(2, getPassword());

				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					setAccountType(rs.getString("type"));
					frame.setVisible(false);
					login_connection.main(login);
				} else {
					JOptionPane.showMessageDialog(frame, "erreur, login ou mot de passe invalide");
				}
			} catch (Exception e) {
				System.out.print(e);
				JOptionPane.showMessageDialog(frame, "erreur sql");
			}
		} else {
			try {
				Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root",
						"");

				PreparedStatement st = (PreparedStatement) connection
						.prepareStatement("Select * from login where login=?");

				st.setString(1, login);

				ResultSet rs = st.executeQuery();
				if (rs.next()) {
					setId(rs.getString("id"));
					setAccountType(rs.getString("type"));
					name = rs.getString("name");
					surname = rs.getString("surname");
					decryptKey();
	
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(frame, "erreur sql");
			}
		}
	}
}
