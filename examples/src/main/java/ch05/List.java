package ch05;

interface List<T> extends Iterable<T> {
	void add(T o);
	T get(int i);
	int length();
	T remove(int i);
}
