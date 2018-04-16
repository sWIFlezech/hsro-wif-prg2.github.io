package ch05;

interface Set<T> extends Iterable<T> {
	void add(T c);
	boolean contains(T c);
	int size();
}
