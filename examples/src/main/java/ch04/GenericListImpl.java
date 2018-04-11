package ch04;

import java.util.NoSuchElementException;

public class GenericListImpl implements GenericList {

	private class Element {
		Object value;
		Element next;
		Element(Object o, Element e) {
			value = o;
			next = e;
		}
	}

	private Element head;

	@Override
	public Object get(int i) {
		if (head == null)
			throw new NoSuchElementException();

		Element it = head;
		while (i-- > 0)
			it = it.next;
		return it.value;
	}

	public void addSafe(Object v) {
		// enforce typesafety
		if (head != null && v.getClass() != head.value.getClass())
			throw new IllegalArgumentException();
		add(v);
	}

	@Override
	public void add(Object v) {
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
