package ch03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharSetTest {

	@Test
	void testCharSetImpl() {
		CharSet cs = new CharSetImpl();

		String str = "In Ulm und um Ulm und um Ulm herum";

		for (char c : str.toLowerCase().toCharArray())
			cs.add(c);

		System.out.println(cs);
	}

}