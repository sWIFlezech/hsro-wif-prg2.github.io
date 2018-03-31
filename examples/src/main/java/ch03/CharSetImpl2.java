package ch03;

import java.util.NoSuchElementException;

class CharSetImpl2 implements CharSet {
	class Element {
		char value;
		Element left, right;
		Element(char c, Element le, Element re) {
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

		public String toString() {
			String s = "" + value;
			if (left != null) s += ", " + left.toString();
			if (right != null) s += ", " + right.toString();
			return s;
		}
	}

	private Element root = null;

	@Override
	public boolean contains(char t) {
		if (root == null)
			return false;

		Element it = root;
		while (it != null) {
			if (t == it.value)
				return true;
			else if (t < it.value) {
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
			if (it.value == e.value)
				return;
			else if (e.value < it.value) {
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
	public void add(char c) {
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

	@Override
	public String toString() {
		if (root == null) {
			return "[]";
		} else {
			return "[" + root.toString() + "]";
		}
	}

	@Override
	public char remove(char c) {
		if (root == null)
			throw new NoSuchElementException();

		// Spezialfall: Root Element loeschen
		if (root.value == c)
			return removeRoot();

		Element it = root;
		while (it != null) {
			if (c < it.value) {
				if (it.left != null && it.left.value == c)
					return removeElement(it, it.left);
				it = it.left;
			} else {
				if (it.right != null && it.right.value == c)
					return removeElement(it, it.right);
				it = it.right;
			}
		}

		throw new NoSuchElementException();
	}

	private char removeRoot() {
		assert(root != null);

		Element e = root;
		if (e.left == null && root.right == null) {
			// keine Kinder -> Baum leer
			root = null;
		} else if (e.left == null) {
			// nur ein rechtes Kind -> das ist der neue Baum
			root = e.right;
		} else if (e.right == null) {
			// dito, fuer Links
			root = e.left;
		} else {
			// eines wird root, anderes einfuegen
			root = e.left;
			addElement(e.right);
		}

		// Wert zurueck geben
		return e.value;
	}

	/**
	 *
	 * @param p Elternelement
	 * @param e zu loeschendes Element
	 */
	private char removeElement(Element p, Element e) {
		if (e == p.left) {
			p.left = null;  // links abgestiegen
		} else {
			p.right = null;  // sonst: rechts
		}

		// Kinder einfuegen
		addElement(e.left);
		addElement(e.right);

		return e.value;
	}
}
