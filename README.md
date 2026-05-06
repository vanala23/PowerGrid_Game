# PowerGrid Simulator
Ein 2D-Top-Down-Spiel zur Simulation eines Stromnetzes, entwickelt in Java mit Swing.  
Ziel ist es, eine Stadt mit Strom zu versorgen, indem Kraftwerke, Transformatoren und Leitungen strategisch platziert werden.

---

## Gameplay
Du baust Schritt für Schritt ein Stromnetz auf:

- Kraftwerke erzeugen Strom  
- Strommasten (PowerPoles) dienen als Verbindungspunkte  
- PowerLines verbinden Objekte miteinander  
- Transformatoren verteilen/ändern Strom  
- Häuser verbrauchen Strom  

Zusätzlich erhält der Spieler **Tutorial-Erklärungen**, die auch die physikalischen Grundlagen erklären.

---

## Features
- Grid-basiertes System (20x20 Tiles)
- Platzierbare Objekte:
  - PowerPlant (Kraftwerk)
  - Transformer (Transformator)
  - House (Haus)
  - PowerPole (Strommast)
- PowerLines als Verbindungen zwischen Objekten
- Mausgesteuertes Bauen (Click-System)
- BuildMode-System (verschiedene Bau-Modi per Tastatur)
- Hover-System (Objekte werden beim Drüberfahren hervorgehoben + Infos)
- Info-Textbox (Klick auf Objekt zeigt Details)
- Tutorial-Textboxen:
  - erscheinen beim ersten Platzieren eines Objekts
  - erklären Gameplay + Physik
  - pausieren das Spiel
- Pause-System während Tutorials
- Delete-Modus zum Entfernen von Objekten
- Erste Stromlogik (`grid.updatePower()`)

---

## Ziel des Spiels
Baue ein funktionierendes Stromnetz, sodass möglichst viele Häuser mit Strom versorgt werden.  

Zusätzlich soll der Spieler verstehen:
- wie Strom verteilt wird
- warum Transformatoren nötig sind
- wie ein Stromnetz aufgebaut ist

---

## Steuerung
| Taste | Funktion |
|------|--------|
| `1` | PowerPole bauen |
| `2` | PowerLine erstellen |
| `3` | PowerPlant bauen |
| `4` | Transformer bauen |
| `5` | House bauen |
| `6` | Delete-Modus |
| `7` | Info-Modus (Objekte anklicken für Infos) |

---

## Bauen von PowerLines
1. Klicke auf ein erstes Objekt  
2. Klicke auf ein zweites Objekt  
→ Eine Verbindung wird erstellt  

---

## UI-Systeme

### Hover-Textbox
- erscheint beim Überfahren eines Objekts
- zeigt:
  - Objekttyp
  - kurze Infos

### Info-Textbox
- erscheint beim Klick im **Info-Modus**
- zeigt detaillierte Informationen (z.B. Werte, Status)

### Tutorial-Textbox
- erscheint **beim ersten Platzieren eines Objekts**
- erklärt:
  - Funktion des Objekts
  - physikalischen Hintergrund
- pausiert das Spiel
- verschwindet beim nächsten Klick

---

## Technische Umsetzung

### Architektur (MVC)
- **Model**
  - `Grid`
  - `GridObject`
  - `House`, `PowerPlant`, `Transformer`, `PowerPole`, `PowerLine`
- **View**
  - Swing (`JPanel`, Rendering mit `Graphics2D`)
  - `HoverTextBox`, `InfoTextBox`, `TutorialTextBox`
- **Controller**
  - `Game` (Hauptlogik, Input, BuildModes, UI-Steuerung)

---

### Grid-System
- 20x20 Grid
- Objekte werden über `GridObject` verwaltet
- `Grid` speichert alle Objekte in einer Liste
- Zugriff über:
  - `getObjectAt(x, y)`
  - `addObject(...)`
  - `deleteObjectAt(...)`

---

### Verbindungen (PowerLines)
- PowerLines verbinden zwei `GridObject`-Instanzen
- Speicherung:
  - `start`
  - `end`
- Rendering als Linie zwischen Tile-Mittelpunkten

---

### Spiel-Loop
- `update()`:
  - aktualisiert alle Objekte
  - berechnet Strom (`updatePower()`)
- `draw()`:
  - zeichnet Grid + Objekte + UI

---

### BuildMode-System
Der aktuelle Modus bestimmt das Verhalten beim Klicken:

- Platzieren von Objekten
- Verbinden von Objekten
- Löschen
- Anzeigen von Infos

---

### Pause-System
- Wird aktiviert bei Tutorial-Textboxen
- Währenddessen:
  - keine Updates (`update()` wird übersprungen)
- Klick beendet Tutorial und setzt Spiel fort
