import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Pattern;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

class testEmail {

	@Test
	void test() {
		final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*" +
	            "@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
		
		String emailText;
		do {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Entrer l'email du fournisseur : ");
			emailText = sc.next();
			System.out.println(emailText);
			
		} while (EMAIL_PATTERN.matcher(emailText).matches() == false || emailText.equalsIgnoreCase(""));
		
		String test = InterFournisseur.testEmail(emailText);
		assertEquals("has34@gmail.com",test);
	}

}
