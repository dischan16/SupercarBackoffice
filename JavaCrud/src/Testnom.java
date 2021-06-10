import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Khushveer
 *
 */

//JUNIT test sur l'insertion du nom

class Testnom {

	@Test
	void test() {

		try (Scanner Scan = new Scanner(System.in)) {
			
			// Le REGEX pour le nom avec le premier alphabet en capital
			
			final String NOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
			final Pattern NOM_PATTERN = Pattern.compile(NOM_REGEX);
			String nom;

			// Saisi du nom et comparaison
			do {
				System.out.print("Inserer un nom : ");
				nom = Scan.next();
			} while (NOM_PATTERN.matcher(nom).matches() == false || nom.equalsIgnoreCase(""));

			String test = vendeur_client.testPrenom(nom);
			// La valeur 'Sujeebun' est utilise pour la comparaison des noms durant l'insertion. 
			assertEquals("Sujeebun", test);
		}
	}

}
