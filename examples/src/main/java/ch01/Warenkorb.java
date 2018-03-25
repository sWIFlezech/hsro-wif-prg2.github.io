package ch01;

public class Warenkorb {
	private Ware[] waren;

	public Warenkorb(Ware[] waren) {
		this.waren = waren;
	}

	public int price() {
		int p = 0;
		for (Ware w : waren)
			if (w != null)
				p += w.price();
		return p;
	}

	public int tax() {
		int t = 0;
		for (Ware w : waren)
			if (w != null)
				t += w.tax();
		return t;
	}

	public int priceTaxed() {
		return price() + tax();
	}

	public boolean qualifiesFreeShipping() {
		return price() >= 300;
	}

	public Ware getWare(int i) {
		if (waren == null || waren.length > i)
			throw new IllegalArgumentException(i + ": invalid index");
		return waren[i];
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Ware w : waren)
			if (w != null)
				sb.append(w + "\n");
		return sb.toString();
	}
}
