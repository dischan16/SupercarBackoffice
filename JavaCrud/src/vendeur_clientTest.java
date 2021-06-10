import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;
import java.util.regex.Pattern;



import org.junit.jupiter.api.Test;

// JUNIT TEST SUR LE PRENOM DU CLIENT

class vendeur_clientTest {

@Test
void test() {
	
try (Scanner Scan = new Scanner(System.in)) {
final String PRENOM_REGEX = "^[A-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
final Pattern PRENOM_PATTERN = Pattern.compile(PRENOM_REGEX);
String prenom;

do {
System.out.print("Saississez un prénom (Xxx) : ");
prenom = Scan.next();
} while (PRENOM_PATTERN.matcher(prenom).matches() == false || prenom.equalsIgnoreCase(""));



String test = vendeur_client.testPrenom(prenom);
assertEquals("Khushveer", test);
}
}



}
