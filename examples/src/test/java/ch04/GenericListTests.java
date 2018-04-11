package ch04;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class GenericListTests {
	@Test
	void testGenericList() {
		GenericList gl = new GenericListImpl();

		gl.add("Hallo");
		gl.add("Peter");
		gl.add('!');
		gl.add(4);

		for (int i = 0; i < gl.length(); i++) {
			System.out.println(gl.get(i));
		}

		String h = (String) gl.get(0);
		assertThrows(ClassCastException.class, () -> { String s = (String) gl.get(3); });
		int i = (Integer) gl.get(3);
	}
}
