# Programmieraufgabe: Bayes-Spam-Filter

Ziel der Aufgabe ist es, einen Bayes-Spam-Filter zu programmieren. Folgende Schritte werden erwartet:

### Funktionsweise:

- http://www.math.kit.edu/ianm4/~ritterbusch/seite/spam/de
- https://en.wikipedia.org/wiki/Naive_Bayes_spam_filtering.


### Funktionalitäten des Programms

- Die Emails aus ham-anlern.zip bzw. spam-anlern.zip werden nacheinander eingelesen und als Ham bzw. Spam markiert. Dabei wird für jedes Wort in einer Ham- bzw. Spam-Mail gezählt, in wie vielen Ham- bzw. Spam-Mails das Wort vorkommt. Ein Wort muss dabei kein sinnvolles Wort sein. Sie können also jeweils die gesamte Mail, inklusive Header, einlesen und ein Leerzeichen als Worttrennungssymbol verwenden. 
- Sie implementieren eine Funktion, die für eine gegebene Mail die Spamwahrscheinlichkeit gemäss der in der obigen Quelle hergeleiteten Formel berechnet. (Dabei können Sie alle Wörter (und nicht nur die signifikantesten Wörter) zur Berechnung hinzuziehen.) Falls ein Wort, etwa money in der Anlernphase nur in Ham-Mails vorkam, dann hat eine Mail, die das Wort money enthält, eine Spamwahrscheinlichkeit von 0 (Begründen Sie dies!), selbst dann, wenn die Mail auch Viagra, enlargement, Kenia, win,... enthält. Dies ist nat¨urlich unerwünscht. Fügen Sie deshalb in der Anlernphase jedes Wort, was in einer der Ham-Mails aber in keiner Spam-Mail vorkommt, mit einer «Anzahl» α (sehr klein (< 1)) in den Spam-Korpus ein und umgekehrt für jedes Wort, welches in einer Spam- aber keine Ham-Mail vorkommt.
- Bestimmen Sie geeignete Werte für den Schwellenwert, wann eine Mail als Spam klassifiziert werden soll und für obiges α, so dass ihr Spamfilter gut arbeitet. Nutzen Sie dazu die Mails in ham-kalibrierung.zip und spam-kalibrierung.zip.
- Geben Sie an, wie viel Prozent der Mails in ham-test.zip bzw. spam-test.zip korrekt klassifiziert wurden.

### Abgabe

Erwartet wird eine Abgabe, bei der nach dem Ausführen des Programms eine Zusammenfassung aller Werte (Schwellenwert, α, Erkennungsraten) angezeigt wird.
Senden Sie einen Screenshot dieser Ausgabe in der Mail mit ihrer Abgabe mit.
