package ch01;

import ch01.Steuersatz;
import ch01.Ware;
import ch01.Warenkorb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class Warentests {
	@Test
	void testSteuersatz() {
		Assertions.assertEquals(0.19, Steuersatz.steuersatz(0));
		Assertions.assertEquals(0.07, Steuersatz.steuersatz(1));

		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				Steuersatz.steuersatz(-1);
			}
		});

		Assertions.assertThrows(IllegalArgumentException.class, () -> Steuersatz.steuersatz(-2));
	}

	@Test
	void testWare() {
		Ware w1 = new Ware("w1", 100, 0);
		Ware w2 = new Ware("w2", 100, 1);

		Assertions.assertEquals(119, w1.priceTaxed());
		Assertions.assertEquals(107, w2.priceTaxed());

		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				new Ware("ungueltig", 100, 3);
			}
		});
	}

	@Test
	void testWarenkorb() {
		Ware w1 = new Ware("w1", 100, 0);
		Ware w2 = new Ware("w2", 100, 1);

		Warenkorb wk = new Warenkorb(new Ware[] {w1, w2});

		Assertions.assertEquals(200, wk.price());
		Assertions.assertEquals(226, wk.priceTaxed());
		Assertions.assertFalse(wk.qualifiesFreeShipping());

		Ware w3 = new Ware("w2", 100, 1);
		Warenkorb fs = new Warenkorb(new Ware[] {w1, w2, w3});
		Assertions.assertTrue(fs.qualifiesFreeShipping());
	}
}
