package ch05;

import java.util.NoSuchElementException;

class ArrayList<T> implements List<T> {
	private T[] a;
	private int length;

	ArrayList(T[] init) {
		a = init;
	}

	class MyIterator implements java.util.Iterator<T> {
		int pos = 0;
		@Override
		public boolean hasNext() {
			return pos < length - 1;
		}
		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			T h = a[pos];
			pos = pos + 1;
			return h;
		}
	}

	public java.util.Iterator<T> iterator() {
		return new MyIterator();
	}

	@Override
	public void add(T o) {
		if (length == a.length)
			throw new ArrayIndexOutOfBoundsException();
		a[length++] = o;
	}

	@Override
	public T get(int i) {
		if (i >= length)
			throw new ArrayIndexOutOfBoundsException();
		return a[i];
	}

	@Override
	public int length() {
		return length;
	}

	@Override
	public T remove(int i) {
		throw new UnsupportedOperationException();
	}
}
