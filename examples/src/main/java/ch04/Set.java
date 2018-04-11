package ch04;

public interface Set<T extends Comparable<T>> {
	void add(T c);
	boolean contains(T c);
	int size();
}
