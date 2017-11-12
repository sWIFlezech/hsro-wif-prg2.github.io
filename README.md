# Programmieren 2

_Pflichtmodul im [Bachelorstudiengang Wirtschaftsinformatik](https://www.fh-rosenheim.de/technik/informatik-mathematik/wirtschaftsinformatik-bachelor/) an der [Hochschule Rosenheim](https://www.fh-rosenheim.de)._

## Organisatorisches

**Vorlesungstermin**: TBD

**Übungen**: TBD; Tutor: TBD

**Kommunikation**: via Mattermost (einschreiben)

**Wichtige Termine**:
- Prüfungsanmeldung (incl. PStA)
- Einführung in das Contestsystem der PStA (persönliche Anwesenheit erforderlich!)
- Testat zur PStA


## Leistungsnachweis
Der Leistungsnachweis ist aufgeteilt in eine 
- (unbenotete) praktische Studienarbeit (PStA), abzulegen während des Semesters.
- schriftliche Prüfung (SP, 90 Minuten) am Ende des Semesters


## Empfohlene Literatur


## Inhalt
- **Einführung ()**
	* Organisatorisches
	* Git und IntelliJ
- 1/12: 
	* Klassen: normale, innere, äussere, statische, anonyme
	* Getter und Setter
	* `@FunctionalInterface`, Lambda und Methodenreferenz
- 2/12:
	* Vererbung: Sichtbarkeiten und Überladung
	* Basisklasse Object: `equals`, `hashCode`, `toString`
	* Interfaces und abstrakte Basisklassen: Zustandsautomat
- 3/12: Datenstrukturen (1)
	* List: Array, ArrayList, LinkedList
	* Iterator
- 4/12: Datenstrukturen (2)
	+ Set; zuerst ueber Liste mit equals, dann als Binärbaum
	+ Map als Set von Pairs
	+ Iteration auf Bäumen: Agenda
- 5/12 Rekursion
	+ Iteration -> Rekursion (Agenda -> Rekursion)
	+ Baumtraversierung (DFS, BFS) -> Sortierter Iterator?
	+ Sortieren: insertion sort, merge sort (map-reduce), quicksort
	+ Binäre suche: Im Array, im Baum
- 6/12 Threading #1
	+ Runnable und Thread
	+ Executor
	+ Futures
- 7/12 Threading #2
	+ Deadlocks
	+ Synchronisierung
	+ Consumer-Producer
- 8/12 Datenströme: Iterator, Streams; Filter, Map, Reduce; group-by
- 9/12 Annotations und JUnit?
- 10/12 GUI/Android/JavaFX? Composite, Factory, Observer, Flyweight?
- 11/12 Generics
- 12/12 ???


- **Zusammenfassung und Rückblick**

## Übungen

Ausser der Zaehlung:
1. Git und IntelliJ
2. Einführung in die PStA
3. Testat (letzte Woche)

1. [Klassen, Objekte, Vererbung, Statisches](/uebung01/): Zoo-Spiel, Teil 1
2. [Equals, hashCoce und toString](/uebung02/): Zoo-Spiel: Erweitert
3. [Fehlerbehandlung, Exceptions und Tests](/uebung03/): Zoo-Spiel: Erweitert, jetzt mit Webfront-end; Zustandsautomat  --> als GUI?
4. [Comparable und Comparator](/uebung04/): Mit generics, sortieren nach Kriterien
5. [Warteschlange und PrioQueue](/uebung05/)
6. [Entwurfsmuster: Iterator](/uebung06/): Mit FilterIterator
7. [Listen, Sets und Maps](/uebung07/): mit `java.util.*`, Schwerpunkt auf Filtern, Sortieren und Abbilden
8. [Rekursion](/uebung08/)
9. [Parallele Verarbeitung 1](/uebung09/) Runnables und Futures
10. [Parallele Verarbeitung 2](/uebung10/) Consumer/Producer Problem


_Subscribe to [https://github.com/hsro-wif-prg2/hsro-wif-prg2.github.io](https://github.com/hsro-wif-prg2/hsro-wif-prg2.github.io) repository to follow updates._