import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * 
 * @author Khushveer
 *
 */

public class Account {
	private static final String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{12,30})";
	private String id;
	public String name;
	public String surname;
	public String login;
	private String password;
	private String accountType;


	// les methodes pour les insertions 
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String textPwd) {
		this.password = textPwd;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 *  La partie verifications des donnees inserer 
	 *
	 */
	
	public boolean verification() {
		boolean error = false;
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher;
		matcher = pattern.matcher(password);

		if (Pattern.matches("[a-z]*", surname.substring(1, surname.length())) == false
				|| Pattern.matches("[A-Z]", surname.substring(0, 1)) == false || surname.equalsIgnoreCase("")) {
			JFrame frame = new JFrame("error");
			JOptionPane.showMessageDialog(frame, "erreur,  nom invalide");
			error = true;
		}
		if (Pattern.matches("[a-z]*", name.substring(1, name.length())) == false
				|| Pattern.matches("[A-Z]", name.substring(0, 1)) == false || name.equalsIgnoreCase("")) {
			JFrame frame = new JFrame("error");
			JOptionPane.showMessageDialog(frame, "erreur, prenom invalide");
			error = true;
		}
		if (Pattern.matches("^[a-z0-9-.]+@[a-z0-9]+\\.[a-z0-9-.]+$", login) == false || login.equalsIgnoreCase("")) {
			error = true;
			JFrame frame = new JFrame("error");
			JOptionPane.showMessageDialog(frame, "erreur,login invalide");
		}
		if ((matcher.matches()) == false || password.equalsIgnoreCase("")) {
			error = true;
			JFrame frame = new JFrame("error");
			JOptionPane.showMessageDialog(frame,
					"erreur mot de passe invalide (au moins un chiffre, une lettre en capitale et un charactere special et min 12 characteres");
		}
		
		return error;
	}
	
	/**
	 *  Hashage au niveau du mots de passe
	 * @param chaine
	 * @return
	 */

	public String hashPassword(String chaine) {
		try {
			byte[] donneeOctet = chaine.getBytes();
			MessageDigest monHash = MessageDigest.getInstance("SHA");
			monHash.update(donneeOctet);
			byte[] condenser = monHash.digest();
			chaine = new BigInteger(condenser).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chaine;
	}

}


