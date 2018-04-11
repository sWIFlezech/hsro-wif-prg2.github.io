package ch04;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class SortierenTest {

	@Test
	void testSort() {
		Integer[] a = {1, 4, 2, 5, 6};

		Sortieren.sort(a);
		System.out.println(Arrays.toString(a));

		Integer[] b = {1, 5, 3, 2, 6};
		Sortieren.sort(a, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		});
	}

}