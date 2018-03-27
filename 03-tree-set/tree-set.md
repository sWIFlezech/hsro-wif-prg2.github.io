---
title: "Set (Binärbaum)"
permalink: /03-tree-set/
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

for (char c : str.toLowerCase().toCharArray())
	cs.add(c)

System.out.println(cs);
// gewünschte Ausgabe:
// [i, n,  , u, l, m, d, h, e, r]
```













