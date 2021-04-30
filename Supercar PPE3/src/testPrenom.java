import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class testPrenom {

	@Test
	void test() {
		try (Scanner Scan = new Scanner(System.in)) {
			final String PRENOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
			final Pattern PRENOM_PATTERN = Pattern.compile(PRENOM_REGEX);
			String prenomText;
			do {
				System.out.print("Saississez un prénom (Xxx) : ");
				prenomText = Scan.next();
			} while (PRENOM_PATTERN.matcher(prenomText).matches() == false || prenomText.equalsIgnoreCase(""));

			String test = employee.testPrenom(prenomText);
			assertEquals("Dischan", test);
		}
	}

}
