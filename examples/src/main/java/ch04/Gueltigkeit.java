package ch04;

public class Gueltigkeit<T> {
	T variable;  // ok

	// nicht moeglich:
	// static T somevariable;

	class Innere {
		T hilfe;  // ok

		// ebenso nicht moeglich:
		// static T hilfe;
	}

	static <E> void someStaticMethod(E arg) {
		// statische Methode, generisch in E

		// nicht moeglich:
		// T gehtnicht;
	}

	static class StatischeInnere<Z> {
		Z variable;

		// geht nicht:
		// T gehtnicht;

		// geht auch nicht, da E nur bei someStaticMethod
		// E gehtauchnicht;
	}


}
