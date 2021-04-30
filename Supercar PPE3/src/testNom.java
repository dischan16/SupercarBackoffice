import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class testNom {

	@Test
	void test() {
		try (Scanner Scan = new Scanner(System.in)) {
			final String NOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
			final Pattern NOM_PATTERN = Pattern.compile(NOM_REGEX);
			String nomText;
			do {
				System.out.print("Saississez un nom (Xxx) : ");
				nomText = Scan.next();
			} while (NOM_PATTERN.matcher(nomText).matches() == false || nomText.equalsIgnoreCase(""));

			String test = employee.testNom(nomText);
			assertEquals("Armoogum", test);
		}
	}

}
