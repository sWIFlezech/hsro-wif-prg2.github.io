---
title: "Set (Binärbaum)"
permalink: /03-tree-set/
mathjax: true
---

# Set: Menge (ohne Duplikate)

Letzte Woche hatten wir die [Liste als sequenzielle Datenstruktur]({{site.baseurl}}/02-linked-list/) behandelt.
Diese speichert Daten in der Reihenfolge ab, in der sie hinzugefügt werden:

```java
IntList xs = new IntListImpl();

xs.add(1);
xs.add(3);
xs.add(3);

System.out.println(xs); // Ausgabe: "[1, 3, 3]"
```

Das Beispiel zeigt aber auch, dass Duplikate enthalten sein können -- was je nach Anwendung aber unerwünscht sein kann.

Angenommen, wir möchten nun z.B. herausfinden, welche Buchstaben (`char`s) in einem langen `String` vorkommen.
Hätten wir eine Datenstruktur, welche Duplikate einfach unterbindet, so könnten wir folgendes machen:

```java
CharSet cs = new CharSetImpl();

String str = "In Ulm und um Ulm und um Ulm herum";

for (char c : str.toLowerCase().toCharArray()) {
	if (c == ' ') continue;
	cs.add(c)
}

System.out.println(cs);
// gewünschte Ausgabe:
// [i, n, u, l, m, d, h, e, r]
```

Ein _Set_ soll also jedes Element nur genau einmal enthalten, auch wenn man versucht es wieder einzufügen.

Das Interface dafür könnte so aussehen:

```java
interface CharSet {
	void add(char c);          // Element hinzufügen
	boolean contains(char c);  // prüfen ob bereits enthalten
	char remove(char c);       // Element entfernen
	int size();                // nicht "length", da keine Sequenz!
}
```

> Hinweis: Da ein Set keine sequenzielle Ordnung hat gibt es statt `length` eine `size` Methode, und die `remove` Methode nimmt keinen Index, sondern einen Wert!


## Innere Klassen

Wenn wir dieses nun analog zur Liste implementieren, so könnte das so aussehen:

```java
class CharSetImpl1 implements CharSet {
	class Element {
		char value;
		Element next;
		Element(char c, Element n) {
			value = c;
			next = n;
		}
	}

	private Element head;
	// ...
}
```

Man beachte: Hier ist die Klasse `Element` als _innere_ Klasse angelegt.
Innere Klassen sind in Java...

- sinnvoll, wenn sie nur innerhalb einer Klasse, also lokal verwendet werden.
- normal oder `static` definiert; normale innere Klassen können auf die Variablen der äusseren Instanz zugreifen, _statische_ innere Klassen können nur auf statische Variablen und Methoden der äusseren Klasse zugreifen.
- mit Sichtbarkeiten versehen wie Variablen und Methoden: _package_, `private`, `public`.


## Duplikate Vermeiden

Wir können nun Duplikate vermeiden, indem wir vor dem Einfügen prüfen, ob ein Element schon enthalten ist.
Dazu beginnen wir vorne, und hangeln uns bis hinten durch, wobei jedes Element auf (Wert-)Gleichheit prüfen.

```java
class CharSetImpl1 implements CharSet {
	// ...
	public boolean contains(char c) {
		if (head == null)
			return false;

		Element it = head;
		while (it != null) {
			if (it.value == c)
				return true;
			it = it.next;
		}

		return false;
	}
	// ...
}
```

Das Einfügen (`add`) kann dann analog zur Liste realisiert werden, ebenso die Methoden `size`, `remove` und `toString` (siehe [Implementierung](https://github.com/hsro-wif-prg2/hsro-wif-prg2.github.io/examples/src/main/java/ch03/CharListImpl1.java)).


```java
class CharSetImpl1 implements CharSet {
	// ...
	public void add(char c) {
		// nicht einfügen, wenn bereits enthalten!
		if (contains(c)) {
			return;
		}

		// weiter wie bei Liste...
	}
	// ...
}
```


Wir wollen an dieser Stelle aber garnicht zu ausführlich werden, da die Implementierung des Sets als Liste hochgrading ineffizient ist: bei jedem `add` bzw. `contains` muss zuerst die gesamte Liste durchlaufen werden, um zu prüfen, ob das Element nicht bereits enthalten ist.
Betrachtet man also den _Aufwand_ dieser Methoden in $O$-Notation, so ist dieser linear in der Anzahl der enthaltenen Elemente: $O(n)$.
Das heisst praktisch gesehen: haben wir _doppelt so viele_ Elemente im Set, so dauert jeder Aufruf auch _doppelt so lang_.

Das geht natürlich besser:


## Binärbaum

Eine fundamentale Datenstruktur in der Informatik ist der _Binärbaum_.
Er unterscheidet sich von der Liste dahingehend, dass jedes Element nicht einen, sondern _zwei_ Nachfolger hat -- daher der Name: _binär_.

Ein Element zeigt also nicht _sequenziell_ auf das _nächste_ Element, sondern unterhält Referenzen auf einen _linken_ und _rechten Teilbaum_, welche dann nur _kleinere_ bzw. _größere_ Werte enthalten.

Fügt man nun ein Element ein, so steigt man vom Wurzelknoten (engl. _root_) so weit nach links oder rechts ab, bis man entweder den Wert gefunden hat, oder an der Stelle angekommen ist, wo der neue Wert einzufügen ist.

Aber ein Bild sagt mehr als 1000 Worte; hier ein Binärbaum für Zahlen (`IntSet`), in den der Reihe nach die Zahlen 4, 2, 3, 6, 1 eingefügt werden:

```java
IntSet is = new IntSet();  // analog zu CharSet
is.add(4);
is.add(2);
is.add(3);
is.add(6);
is.add(1);
```

![Baum 1/6]({{site.baseurl}}/03-tree-set/tree-od1.svg)

![Baum 2/6]({{site.baseurl}}/03-tree-set/tree-od2.svg)

![Baum 3/6]({{site.baseurl}}/03-tree-set/tree-od3.svg)

![Baum 4/6]({{site.baseurl}}/03-tree-set/tree-od4.svg)

![Baum 5/6]({{site.baseurl}}/03-tree-set/tree-od5.svg)

![Baum 6/6]({{site.baseurl}}/03-tree-set/tree-od6.svg)

> Die Metapher _Baum_ kommt von der Verästelung bzw. Verzweigung, auch wenn ein Baum ja eigentlich von der Wurzel nach Oben wächst.
> Aber Informatik zählen ja auch von 0 und nicht von 1...


Möchte man nun ein Element suchen (`contains`), so beginnt man am Wurzelknoten (`root`), prüft ob der Wert bereits dort vorhanden ist, und steigt ansonsten nach links bzw. rechts ab, je nachdem ob der gesuchte Wert kleiner oder größer ist.

Wie wird so ein Baum nun implementiert, und wie steigt man links oder rechts ab?
Beginnen wir mit der Struktur; ein Element hat nun also nicht einen Nachfolger `next`, sondern zwei: `left` und `right`.

```java
class CharSetImpl2 implements CharSet {
	class Element {
		char value;
		Element left, right;
		Element(char c, Element le, Element re) {
			value = c;
			left = le;
			right = re;
		}
	}
	// ...
}
```

Bei der `contains` Methode wird zwar ähnlich zur Liste iteriert, aber statt `it = it.next` muss unterschieden werden, ob man links oder rechts absteigen möchte:

```java
class CharSetImpl2 implements CharSet {
	// ...
	public boolean contains(char t) {
		if (root == null)
			return false;

		Element it = root;
		while (it != null) {
			if (t == it.value)
				return true;
			else if (t < it.value) {
				it = it.left;
			} else {
				it = it.right;
			}
		}

		// nicht gefunden!
		return false;
	}
	// ...
}
```

Beim Einfügen wird nun ähnlich vorgegangen, nur dass man hier statt ein Element voraus eben nach links und rechts sehen muss:

```java
class CharSetImpl2 implements CharSet {
	// ...
	public void add(char c) {
		Element e = new Element(c, null, null);

		if (root == null) {
			root = e;
			return;
		}

		Element it = root;  // beginne an der Wurzel
		while (it != null) {
			// schon vorhanden -> fertig!
			if (it.value == e.value)
				return;
			// links absteigen?
			else if (e.value < it.value) {
				// wir sind unten angekommen -> einfügen!
				if (it.left == null) {
					it.left = e;
					return;
				} else
					it = it.left;
			} else {
				// analog
				if (it.right == null) {
					it.right = e;
					return;
				} else
					it = it.right;
			}
		}
	}
	// ...
}
```


Den aufmerksamen Lesern ist bestimmt nicht entgangen, dass zur Vervollständigung von `CharSetImpl2` noch die Methoden `size` und `toString` fehlen -- doch wie kann man diese realisieren?

Zur Erinnerung: Bei der sequenziellen Liste konnte man im Prinzip folgendes machen:

```java
String s = "" + head.value;
Element it = head.next;
while (it != null) {
	s += ", " + it.value;
	it = it.next;
}
```

Das funktioniert, da es ja immer genau einen Nachfolger gibt.
Doch was nun bei mehr als einem Nachfolger, oder eben zwei wie beim Binärbaum?
Folgendes etwa deckt nur einen Teil ab:

```java
String s = "" + head.value;
Element it1 = head.left;
while (it1 != null) {
	s += ", " + it1.value;
	it1 = it1.left;
}
Element it2 = head.right;
while (it2 != null) {
	s += ", " + it2.value;
}
```

Würde man das auf dem obigen Beispielbaum (zur Erinnerung: 4, 2, 3, 6, 1) anwenden, so wäre das Ergebnis `[4, 2, 1, 6]` -- die 3 wäre verloren gegangen, da diese zwar im linken Teilbaum vom Wurzelknoten ist, dort aber in einem Rechten (`root.left.right.value`).


## Exkurs: Rekursion

Eine elegante Lösung für dieses Problem ist die [_Rekursion_](https://www.google.de/search?q=recursion), also der wiederholte Aufruf einer Funktion durch sich selbst.

![Google Recursion]({{site.baseurl}}/03-tree-set/google-recursion.png)
{: .figcenter}

Wir beginnen mit der `size` Methode, welche zurück geben soll, wie viele Elemente im Baum gespeichert sind.

Die Idee ist die Folgende: Vielleicht weiss ich als Baum nicht, wie man so absteigt, dass man alle Knoten besucht (und entsprechend mitzählt); aber wenn ich ein Baumelement habe, so weiss ich doch, dass ich mindestens Element habe, sowie dazu noch alle Elemente des linken und rechten Teilbaums.
Das kann man _rekursiv_ hinschreiben:

```java
class CharSetImpl2 implements CharSet {
	class Element {
		char value;
		Element left, right;
		int size() {
			int s = 1;  // schon mal mindestens ein Element.
			
			// gibts einen linken Teilbaum? Dann dessen Größe dazuaddieren.
			if (left != null) s += left.size();

			// analog.
			if (right != null) s += right.size();
		}
		// ...
	}

	Element root;

	public size() {
		if (root == null) return 0;
		else return root.size();
	}
	// ...
}
```

Die Methode `size` der Klasse `Element` ruft sich hier selbst auf (`left.size()` bzw. `right.size()`), sie ist daher _rekursiv_.
In der Klasse `CharSetImpl2.size()` kann jene Methode jetzt verwendet werden, um die Größe des Baums rekursiv zu bestimmen.

> Hinweis: Es ist zur Rekursion *genau* die selbe Methode aufzurufen, d.h. die Methode in der selben Klasse.
> Am obigen Beispiel ist die Methode `CharSet.size` *nicht* rekursiv, die Methode `Element.size` hingegen schon.

Dies geht analog für die Methode `toString`:

```java
class CharSetImpl2 implements CharSet {
	class Element {
		char value;
		Element left, right;
		public String toString() {
			String s = "" + value;  // schon mal dieses Element
			
			// gibts einen linken Teilbaum? Dann dessen String dazu
			if (left != null) s += ", " + left.toString();

			// analog.
			if (right != null) s += ", " + right.toString();
		}
	}

	Element root;

	public String toString() {
		if (root == null) return "[]";
		else return "[" + root.toString() + "]";
	}
	// ...
}
```

> Hinweis: Rekursion wird hier nur als Exkurs behandelt, 


## Komplexität

Warum nun das ganze Spektakel?
Man kann mathematisch-kombinatorisch beweisen, dass der mittlere Aufwand (Komplexität) zum Suchen bzw. Einfügen in einen Binärbaum $O(\log n)$; ist der Baum balanciert (also die Verzweigung ausgewogen), so ist der Aufwand sogar im _worst case_ $O(\log n)$.

Wir erinnern uns an den Eingang dieses Kapitels: Die naive Implementierung eines Sets als Liste hatte die Komplexität $O(n)$.
Das scheint beim Lesen nicht arg unterschiedlich, doch auch hier sagt ein Bild mehr als 1000 Worte:

![Komplexität]({{site.baseurl}}/03-tree-set/o-n-logn.png)
{: .figcenter}

Man sieht: bei linear steigenden $n$ ($x$-Achse) bleibt die logarithmische Steigung sehr schnell sehr weit unter der Linearen.
Die Baumstruktur ist also wesentlich effizienter!


## Zusammenfassung

- Ein **Set** ist im Gegensatz zu einer Liste **frei von Duplikaten**; in der Regel werden `add`, `remove`, `contains` und `size` unterstützt.
- Da ein **Set** eine **Menge ohne Ordnung** (Reihenfolge) ist, gibt es keinen Zugriff über den Index.
- Ein Set kann zwar mit einer Liste implementiert werden, ein **Binärbaum ist aber deutlich effizienter**.
- Bei komplizierter Struktur kann **Rekursion** oft eine elegante Lösung sein; hierbei ruft eine Methode sich selbst wieder auf.


<p style="text-align: right">&#8718;</p>
