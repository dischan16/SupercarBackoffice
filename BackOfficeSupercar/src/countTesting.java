import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class countTesting {

	@Test
	void test() {
		
		JunitTesting test = new JunitTesting();
		int output = test.count("vouzotte");
		assertEquals(2, output);
		
	}

}
