package ch03;

import java.util.NoSuchElementException;

class CharSetImpl1 implements CharSet {
	class Element {
		char value;
		Element next;
		Element(char c, Element n) {
			value = c;
			next = n;
		}
	}

	private Element head;

	@Override
	public boolean contains(char c) {
		if (head == null)
			return false;

		Element it = head;
		while (it != null) {
			if (it.value == c)
				return true;
			it = it.next;
		}

		return false;
	}

	@Override
	public void add(char c) {
		if (head == null) {
			head = new Element(c, null);
			return;
		}else if (contains(c)) {
			return;
		}

		// letztes Element finden, dazu
		// ein Element voraus schauen
		Element it = head;
		while (it.next != null) {
			it = it.next;
		}

		it.next = new Element(c, null);
	}

	@Override
	public char remove(char c) {
		if (head.value == c) {
			Element rem = head;
			head = rem.next;
			return rem.value;
		}

		// so lange eins vorausschauen bis Treffer
		// oder Liste zu Ende
		Element it = head;
		while (it.next != null) {
			if (it.next.value == c) {
				Element rem = it.next;
				it.next = rem.next;
				return rem.value;
			}
			it = it.next;
		}

		// Element nicht gefunden!
		throw new NoSuchElementException();
	}

	@Override
	public int size() {
		int len = 0;
		Element it = head;
		while (head != null) {
			len++;
			it = it.next;
		}

		return len;
	}

	@Override
	public String toString() {
		if (head == null)
			return "[]";

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
