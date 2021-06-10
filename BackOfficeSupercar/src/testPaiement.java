import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class testPaiement {

	@Test
	void test() {
		
		final String PAIEMENT_REGEX = "^[0-9]+([',. -])*$";
	    
	    final Pattern PAIEMENT_PATTERN = Pattern.compile(PAIEMENT_REGEX);
	    
	    String paiementText;
	    do {
	    	Scanner sc = new Scanner(System.in);
			System.out.println("Entrer le paiement du fournisseur : ");
			paiementText = sc.next();
			System.out.println(paiementText);
	    }while (PAIEMENT_PATTERN.matcher(paiementText).matches() == false || paiementText.equalsIgnoreCase(""));
	    
	    String test = InterFournisseur.testPaiement(paiementText);
	    assertEquals("24",test);
	}

}
