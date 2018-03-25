package ch02;

import java.util.NoSuchElementException;

public class IntListImpl3 implements IntList {
	private IntElement head;

	@Override
	public int get(int i) {
		if (head == null)
			throw new NoSuchElementException();

		IntElement it = head;
		while (i-- > 0)
			it = it.next;
		return it.value;
	}

	@Override
	public void add(int v) {
		if (head == null) {
			head = new IntElement(v, null);
			return;
		}

		IntElement it = head;
		while (it.next != null)
			it = it.next;
		it.next = new IntElement(v, null);
	}

	@Override
	public void put(int i, int v) {
		if (head == null)
			throw new NoSuchElementException();

		IntElement it = head;
		while (i-- > 0)
			it = it.next;

		it.value = v;
	}


	@Override
	public int remove(int i) {
		if (i == 0) {
			int r = head.value;
			head = head.next;
			return r;
		}

		IntElement it = head;
		while (--i > 0)
			it = it.next;

		IntElement rem = it.next;
		it.next = rem.next;
		return rem.value;
	}

	@Override
	public int length() {
		IntElement it = head;
		int n = 0;
		while (it != null) {
			n++;
			it = it.next;
		}
		return n;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		IntElement it = head;
		while (it != null) {
			sb.append(it.value);
			it = it.next;
			if (it != null)
				sb.append(", ");
		}

		return "[" + sb.toString() + "]";
	}
}
