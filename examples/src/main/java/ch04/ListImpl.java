package ch04;

import java.util.NoSuchElementException;

public class ListImpl<T> implements List<T> {
	private class Element {
		T value;
		Element next;
		Element(T o, Element e) {
			value = o;
			next = e;
		}
	}

	private Element head;

	@Override
	public T get(int i) {
		if (head == null)
			throw new NoSuchElementException();

		Element it = head;
		while (i-- > 0)
			it = it.next;
		return it.value;
	}

	@Override
	public void add(T v) {
		if (head == null) {
			head = new Element(v, null);
			return;
		}

		Element it = head;
		while (it.next != null)
			it = it.next;
		it.next = new Element(v, null);
	}

	@Override
	public int length() {
		Element it = head;
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
		Element it = head;
		while (it != null) {
			sb.append(it.value);
			it = it.next;
			if (it != null)
				sb.append(", ");
		}

		return "[" + sb.toString() + "]";
	}
}
