package ch02;

import java.util.Arrays;

public class IntListImpl2 implements IntList {
	public static final int BS = 4;
	private int[] zs = new int [BS];
	private int l = 0;

	@Override
	public int get(int i) {
		if (i >= l)
			throw new ArrayIndexOutOfBoundsException();
		return zs[i];
	}

	@Override
	public void put(int i, int v) {
		if (i >= l)
			throw new ArrayIndexOutOfBoundsException();
		zs[i] = v;
	}

	@Override
	public void add(int v) {
		if (l < zs.length) {
			zs[l++] = v;  // mitzaehlen!
			return;
		}

		int[] neu = new int [zs.length + BS];
		System.arraycopy(zs, 0, neu, 0, zs.length);
		neu[l++] = v;  // weiterzaehlen!
		zs = neu;
	}

	@Override
	public int remove(int i) {
		int r = zs[i];

		// ab i alle eins nach links schieben
		for (int j = i+1; j < l; ++j)
			zs[j-1] = zs[j];

		l--;  // mitzaehlen!

		return r;
	}

	@Override
	public int length() {
		return l;
	}

	public String toString() {
		return Arrays.toString(zs) + "[" + l + "]";
	}
}
