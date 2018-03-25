package ch01;

public class Ware {
	private String name;
	private int priceInCent;
	private int steuersatz;

	public Ware(String name, int priceInCent, int steuersatz) {
		Steuersatz.steuersatz(steuersatz);

		// this for self-reference
		this.name = name;
		this.priceInCent = priceInCent;
		this.steuersatz = steuersatz;
	}

	public int price() {
		return priceInCent;
	}

	public int priceTaxed() {
		return priceInCent + tax();
	}

	public int tax() {
		// cast required: long -> int
		return (int) Math.round(Steuersatz.steuersatz(steuersatz) * priceInCent);
	}

	public String toString() {
		return name + " (" + price() + " + " + Steuersatz.steuersatz(steuersatz) + "";
	}
}
