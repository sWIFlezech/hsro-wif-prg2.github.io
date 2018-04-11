package ch04;

import java.util.Comparator;

class Sortieren {
	static <T extends Comparable<T>> void sort(T[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[j].compareTo(a[i]) < 0) {
					T h = a[i];
					a[i] = a[j];
					a[j] = h;
				}
			}
		}
	}

	static <T> void sort(T[] a, Comparator<T> comp) {
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				int c = comp.compare(a[j], a[i]);
				if (c < 0) {
					T h = a[i];
					a[i] = a[j];
					a[j] = h;
				}
			}
		}
	}
}
