package ch02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntListTest {
	@Test
	void testIntListImpl1() {
		IntList li = new IntListImpl1();  // dazu kommen wir gleich!
		li.add(11);
		li.add(22);
		li.add(33);
		// li: [11, 22, 33]

		assertEquals(3, li.length());
		assertEquals(22, li.get(1));

		li.put(2, 44);  // li: [11, 22, 44]
		li.remove(1);       // li: [11, 44]

		// Index 2 existiert nicht mehr!
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> li.get(2));
		assertEquals(44, li.get(1));
		assertEquals("[11, 44]", li.toString());
	}

	@Test
	void testIntListImpl2() {
		IntList li = new IntListImpl2();  // dazu kommen wir gleich!

		for (int i = 0; i < 6; i++)
			li.add(i);

		assertEquals(6, li.length());
		assertEquals(3, li.get(3));

		li.put(2, 44);  // li: [0, 1, 44, 3, ...]
		li.remove(1);       // li: [0, 44, 3, ...]

		assertEquals(44, li.get(1));

		System.out.println(li);
	}

	@Test
	void testIntListImpl3() {
		IntList li = new IntListImpl3();  // dazu kommen wir gleich!

		for (int i = 0; i < 6; i++)
			li.add(i);

		assertEquals(6, li.length());
		assertEquals(3, li.get(3));

		li.put(2, 44);  // li: [0, 1, 44, 3, ...]
		System.out.println(li);

		li.remove(1);       // li: [0, 44, 3, ...]
		System.out.println(li);

		assertEquals(44, li.get(1));
	}
}