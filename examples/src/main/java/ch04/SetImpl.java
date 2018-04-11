package ch04;

public class SetImpl<T extends Comparable<T>> implements Set<T> {
	class Element {
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
