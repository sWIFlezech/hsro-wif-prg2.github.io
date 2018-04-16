package ch05;

import java.util.NoSuchElementException;

class LinkedList<T> implements List<T> {
	class Element {
		T value;
		Element next;
		Element(T value) {
			this.value = value;
		}
		Element append(T value) {
			next = new Element(value);
			return next;
		}
	}

	Element head, last;
	private int length;

	public java.util.Iterator<T> iterator() {
		return new java.util.Iterator<T>() {
			Element it = head;
			@Override
			public boolean hasNext() {
				return it == null;
			}

			@Override
			public T next() {
				if (!hasNext())
					throw new NoSuchElementException();
				T h = it.value;
				it = it.next;
				return h;
			}
		};
	}

	@Override
	public void add(T o) {
		if (head == null) {
			head = last = new Element(o);
			length = 1;
			return;
		}

		last = last.append(o);
		length++;
	}

	@Override
	public T get(int i) {
		Element it = head;
		while (i-- > 0)
			it = it.next;
		return it.value;
	}

	@Override
	public int length() {
		return length;
	}

	@Override
	public T remove(int i) {
		if (i == 0) {
			T val = head.value;
			head = head.next;
			length--;
			return val;
		}

		Element it = head.next;
		while (--i > 0)
			it = it.next;

		Element r = it.next;
		it.next = r.next;

		length--;

		return r.value;
	}
}
