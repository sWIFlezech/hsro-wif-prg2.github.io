package ch03;

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

	@Override
	public void add(char c) {
		Element e = new Element(c, null, null);

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
		// noch nicht betrachtet; siehe späteres Kapitel.
		return 0;
	}
}
