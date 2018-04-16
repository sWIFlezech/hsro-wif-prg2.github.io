package ch05;

import org.junit.jupiter.api.Test;

import java.util.Random;

class IterationTest {
	static int n = 1024*32;

	@Test
	void testLaufzeitIteration1() {
		Random r = new Random();
		List<Integer> li = new ArrayList<>(new Integer [n]);
		for (int i = 0; i < n; i++)
			li.add(r.nextInt());

		int s = 0;
		for (int i = 0; i < li.length(); i++)
			s += li.get(i);
	}

	@Test
	void testLaufzeitIteration2() {
		Random r = new Random();

		List<Integer> li = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			li.add(r.nextInt());
		}

		int s = 0;
		for (int i = 0; i < li.length(); i++)
			s += li.get(i);
	}

	@Test
	void testLaufzeitIteration3() {
		Random r = new Random();

		List<Integer> li = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			li.add(r.nextInt());
		}

		int s = 0;
		for (int v : li)
			s += v;
	}

	@Test
	void testIterationPerHand() {
		LinkedList<Integer> li = new LinkedList<>();

		LinkedList<Integer>.Element it = li.head;
		while (it != null) {
			System.out.println(it.value);
			it = it.next;
		}
	}

	@Test
	void testIterable() {
		List<Integer> li = new LinkedList<>();

		for (int i : li)
			System.out.println(i);
	}

	@Test
	void testSetIterable() {
		Set<Integer> set = new SetImpl<>();
		int[] a = {4, 2, 6, 1, 3, 5};

		for (int v : a)
			set.add(v);

		for (int v : set)
			System.out.println(v);
	}
}