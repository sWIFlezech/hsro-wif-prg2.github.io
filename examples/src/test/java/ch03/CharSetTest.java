package ch03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharSetTest {
	static void testCharSet(CharSet impl, boolean remove) {
		String str = "In Ulm und um Ulm und um Ulm herum";

		for (char c : str.toLowerCase().toCharArray()) {
			if (c == ' ') continue;
			impl.add(c);
		}

		System.out.println(impl);

		assertTrue(impl.contains('u'));
		assertTrue(impl.contains('l'));
		assertTrue(impl.contains('m'));

		if (!remove)
			return;

		impl.remove('u');
		impl.remove('l');
		impl.remove('m');

		System.out.println(impl);

		assertFalse(impl.contains('u'));
		assertFalse(impl.contains('l'));
		assertFalse(impl.contains('m'));
	}
	@Test
	void testCharSet1() {
		testCharSet(new CharSetImpl1(), true);
	}

	@Test
	void testCharSet2() {
		testCharSet(new CharSetImpl2(), false);
	}
}