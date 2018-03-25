package ch02;

public interface IntList {
	// entsprechend dem []-Operator:
	int get(int i);
	void put(int i, int v);

	// die Länge betreffend:
	void add(int v);
	int remove(int i);

	int length();
}
