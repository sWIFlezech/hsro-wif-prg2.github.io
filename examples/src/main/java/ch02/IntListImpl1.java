package ch02;

import java.util.Arrays;

public class IntListImpl1 implements IntList {
	private int[] zs = {};
	@Override
	public int get(int i) {
		return zs[i];
	}

	@Override
	public void put(int i, int v) {
		zs[i] = v;
	}

	@Override
	public void add(int v) {
		int[] neu = new int [zs.length + 1];
		System.arraycopy(zs, 0, neu, 0, zs.length);
		neu[zs.length] = v;
		zs = neu;
	}

	@Override
	public int remove(int i) {
		int r = zs[i];
		int[] neu = new int [zs.length - 1];
		for (int j = 0, k = 0; j < zs.length; j++) {
			if (j == i) continue;
			neu[k++] = zs[j];
		}
		zs = neu;
		return r;
	}

	@Override
	public int length() {
		return zs.length;
	}

	@Override
	public String toString() {
		return Arrays.toString(zs);
	}

	public static void main(String[] args) {
		IntList li = new IntListImpl1();  // dazu kommen wir gleich!
		li.add(11);
		li.add(22);
		li.add(33);
		// li: [11, 22, 33]

		System.out.println(li.length());
		System.out.println(li.get(1));  // "22"
		li.put(2, 44);  // li: [11, 22, 44]
		System.out.println(li.remove(1));  // li: [11, 44]
		System.out.println(li.get(1));  // "44"

		System.out.println(li);
	}
}
