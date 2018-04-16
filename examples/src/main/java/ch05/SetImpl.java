package ch05;

import java.util.Iterator;

public class SetImpl<T extends Comparable<T>> implements Set<T> {
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			List<Element> agenda = new LinkedList<>();

			// Defaultkonstruktor fuer anonyme innere Klassen
			{
				if (root != null)
					agenda.add(root);
			}

			@Override
			public boolean hasNext() {
				return agenda.length() > 0;
			}

			@Override
			public T next() {
				Element e = agenda.remove(0);
				if (e.left != null)
					agenda.add(e.left);
				if (e.right != null)
					agenda.add(e.right);
				return e.value;
			}
		};
	}

	private class Element {
		T value;
		Element left, right;
		Element(T c, Element le, Element re) {
			value = c;
			left = le;
			right = re;
		}

		int size() {
			int s = 1;
			if (left != null) s += left.size();
			if (right != null) s += right.size();
			return s;
		}
	}

	private Element root = null;

	@Override
	public boolean contains(T t) {
		if (root == null)
			return false;

		Element it = root;
		while (it != null) {
			int c = t.compareTo(it.value);
			if (c == 0)
				return true;
			else if (c < 0) {
				it = it.left;
			} else {
				it = it.right;
			}
		}

		// nicht gefunden!
		return false;
	}

	private void addElement(Element e) {
		if (e == null)
			return;

		if (root == null) {
			root = e;
			return;
		}

		Element it = root;
		while (it != null) {
			int c = e.value.compareTo(it.value);
			if (it.value == e.value)
				return;
			else if (c < 0) {
				if (it.left == null) {
					it.left = e;
					return;
				} else
					it = it.left;
			} else {
				if (it.right == null) {
					it.right = e;
					return;
				} else
					it = it.right;
			}
		}
	}

	@Override
	public void add(T c) {
		Element e = new Element(c, null, null);
		addElement(e);
	}

	@Override
	public int size() {
		if (root == null) {
			return 0;
		} else {
			return root.size();
		}
	}

}
