# Programmieraufgabe: Bayes-Spam-Filter

Ziel der Aufgabe ist es, einen Bloom-Filter zu programmieren und die Funktionsweise darzustellen.

### Funktionsweise:

- https://en.wikipedia.org/wiki/Bloom_filter


### Funktionalitäten des Programms

Sie erstellen ein Java-Programm, was folgendes leistet:
- Bei einer gegebenen Anzahl n an zu erwartenden Elementen, die in der Datenstruktur gespeichert werden und einer Fehlerwahrscheinlichkeit p wird eine geeignete Filtergrösse m und die optimale Anzahl k an Hashfunktionen berechnet.
- Die Datenstruktur wird implementiert, mit den Methoden zum Einfügen von Strings und dem Test, ob ein String enthalten ist. Als Hash-Funktionen soll murmur3_128 (etwa in Guava enthalten: https://github.com/google/guava/wiki/Release19) verwendet werden mit jeweils einem anderen Seed.
- Lassen Sie das Programm, welches eine Fehlerwahrscheinlichkeit p als Eingabe erhält, die Wörter aus words.txt einlesen und in einen Boom-Filter geeigneter Grösse einfügen. Überprüfen Sie die Fehlerwahrscheinlichkeit, in dem Sie für eine grosse Anzahl an nicht enthaltenden Strings testen, ob sie enthalten sind. Die so experimentell bestimmte Fehlerwahrscheinlichkeit soll zusammen mit den Parametern der
Datenstruktur ausgegeben werden.
- Bitte räumen Sie ihr Projekt so auf, dass ich keine speziellen Pakete nachladen oder
komplizierte Pfadanpassungen machen muss.

Sie erstellen mit LATEX(https://www.latex-project.org/) eine Zusammenfassung von ein bis zwei Seiten, welche folgendes enthält:
- Idee des Bloom-Filters, mit Vor- und Nachteilen
- ein konkretes Beispiel aus der Praxis, wo der Bloom-Filter verwendet wird mit kurzer Beschreibung dieses Programms
- Eine Beschreibung, wie Sie die Fehlerwahrscheinlichkeit ihrer Datenstruktur getestet haben und welche Resultate dabei erzielt worden sind. Fügen Sie ein Screenshot der Ausgabe ein.


### Besonderheiten

- Sie können auch andere Programmiersprachen (Python, C/C++,C#, Matlab) verwenden
- Sie können in Gruppen bis zu drei Personen arbeiten
- Es ist nicht nötig, das Programm hinsichtlich Effizienz zu optimieren
- Das Programm sollte verständlich kommentiert sein

Abgabe: 08.12.2024


### Abgabe

Senden Sie mir bitte per Mail das Java-Projekt, den LATEX-Quellcode und das fertige PDF-Dokument zu.
