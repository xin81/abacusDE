# ABacusDE
[English](README.md) | [Deutsch]  
Ein Java-basierter Emulator für einen traditionellen deutschen Abakus.

![Abacus Interface](Screenshot_de.png)


## Funktionen
* **Authentisches Erlebnis**: Ein typisch deutscher Abakus mit 100 Kugeln auf 10 Stäben, der traditionellen mathematischen Regeln und Logik folgt.
* **Duale Ergebnisanzeige**: Im Gegensatz zu einem physischen Abakus zeigt dieser Emulator oben zwei Arten von Ergebnissen an:
    * Positive Ergebnisse: Die Summe der aktiven Kugeln auf der rechten Seite.
    * Negative Ergebnisse: Berechnet nach Computer-Logik (z. B. wird -1 durch 9 aktive Kugeln rechts und 1 inaktive Kugel links dargestellt).
* Audio-Feedback: Bei jeder Bewegung eienr Kugel ertönt ein realistisches Klackgeräusch.

## Voraussetzungen
* **Java**: 21+
* **Maven Wrapper**: Im Projekt enthalten (`./mvnw`)

## Plattform-Kompatibilität
Diese Software wird automatisch über GitHub Actions für die folgenden Plattformen erstellt und paketiert:

- **macOS**: Universelle Unterstützung für Apple Silicon und Intel (erstellt unter macOS 15/26).
- **Windows**: Windows 11 (x64).
- **Linux**: Ubuntu 24.04/26.04 LTS (.deb Paket).

**Hinweis für Nutzer:**
Da ich ein unabhängiger Entwickler bin, verifiziere ich die **macOS-Version** persönlich auf meinem eigenen iMac. Die Windows- und Linux-Installer werden mit standardisierten, automatisierten Tools erstellt. Sollten Sie auf diesen Plattformen Probleme feststellen, eröffnen Sie bitte ein „GitHub Issue“, damit ich der Sache nachgehen kann!

Ich entwickle dieses Programm in meiner Freizeit. Bitte haben Sie Verständnis dafür, dass ich keinen professionellen Support für alle Betriebssysteme garantieren kann.

## Installation
Um das Programm sofort zu installieren, ohne es selbst zu kompilieren:
1. Lade die entsprechende Release-Version für dein Betriebssystem herunter (z. B. abacus-1.0.dmg für MacOS X).
2. Doppelklicke auf den Installer und folge den Anweisungen auf dem Bildschirm

## Kompilieren aus dem Quellcode
Um das Projekt manuell zu kompilieren und die JAR-Datei zu erstellen:
```bash
./mvnw clean package
```

## Bedienung:
* **Bewegung**: Nutze die Schaltflächen **plus 10ᵖ** or **minus 10ᵖ**, um die Kugeln zu bewegen; p ist hierbei der Exponent von 10 und entspricht gleichzeitig der Nummeriungen einzelner Stäbe.
    * Example: Ein Klick auf `10⁰` fügt eine Kugel mit dem Wert 1 hinzu oder entfernt sie.
    * Example: Ein Klick auf `10⁹` fügt eine Kugel mit dem Wert 1,000,000,000 hinzu oder entfernt sie.
* **Anpassung**: Über das Menü **Options** kannst du die "Ergebnisse -" oder die "Nummerierungen ausblenden".
* **Beenden**: Wähle **Datei > Schließen**, um das Programm zu beenden.

### MacOS X
Die Anwendung kann auch über den Terminal mit optional voreingestellter Spracheinstellung gestartet werden. Ohne vorheriger Spracheinstellung ist die Anwendungssprache deutsch; es sei denn, Ihr Betriebssystem ist englisch voreingestellt.
./Applications/abacus.app/Contents/MacOS/abacus --language en (für die englische Spracheinstellung)

### Linux
Die Anwendung kann auch über den Terminal mit optional voreingestellter Spracheinstellung gestartet werden. Ohne vorheriger Spracheinstellung ist die Anwendungssprache deutsch; es sei denn, Ihr Betriebssystem ist englisch voreingestellt.
./opt/abacus/bin/abacus --language en (für die englische Spracheinstellung)

### Windows
Die Anwendung kann auch über die Kommandozeile (cmd) mit optional voreingestellter Spracheinstellung gestartet werden. Ohne vorheriger Spracheinstellung ist die Anwendungssprache deutsch; es sei denn, Ihr Betriebssystem ist englisch voreingestellt.
C:\Program Files\abacus\abacus.exe --language en (für die englische Spracheinstellung)

## Sprachen
Derzeit werden nur deutsch (Standard) und englisch unterstützt.

## Lizenz
Veröffentlicht unter der MIT-Lizenz. Weitere Informationen finden Sie in der [englischen Originalfassung](LICENSE) oder der [deutschen Leseabschrift](LIZENZ).

## Mitwirkende & Copyright
* **Hauptentwickler**: Nguyen Viet Tan
* **Anbieter**: Ruan Yue Xin (xin81)
* Copyright (c) 2026 Ruan Yue Xin