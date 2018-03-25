package ch02;

public class Felder {
	public static void main(String[] args) {
		int[] zs1 = {1, 2, 3};
		int[] zs2 = new int[] {1, 2, 3};
		int zs3[] = {1, 2, 3};

		for (int z : zs3) {
			z = 5;
		}
	}
}
