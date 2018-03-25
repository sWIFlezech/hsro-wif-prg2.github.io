package ch01;

public class Steuersatz {
	public static double steuersatz(int i) {
		switch (i) {
			case 0: return 0.19;
			case 1: return 0.07;
			default: throw new IllegalArgumentException(i + " ist kein gültiger ch01.Steuersatz");
		}
	}
}
