package ch03;

interface CharSet {
	void add(char c);
	boolean contains(char c);
	int size();
	char remove(char c);
}
