package ch03;

public class CharSetImpl implements CharSet {
	private class Element {
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
