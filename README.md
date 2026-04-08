# PowerGrid Simulator

Ein 2D-Top-Down-Spiel zur Simulation eines Stromnetzes, entwickelt in Java mit Swing.  
Ziel ist es, eine Stadt mit Strom zu versorgen, indem Kraftwerke, Transformatoren und Leitungen strategisch platziert werden.

---

## Gameplay

Du startest mit einer leeren Karte und musst:

- Kraftwerke bauen, um Strom zu erzeugen  
- Strommasten (PowerPoles) platzieren  
- Verbindungen (PowerLines) zwischen Objekten erstellen  
- Häuser mit Strom versorgen  

Je besser dein Netzwerk aufgebaut ist, desto mehr Häuser erhalten Strom.

---

## Features

- Grid-basiertes System (20x20)
- Platzierbare Objekte:
  - PowerPlant (Kraftwerk)
  - Transformer (Transformator)
  - House (Haus)
  - PowerPole (Strommast)
- PowerLines als eigenständige Verbindungen zwischen Objekten
- Mausgesteuertes Bauen
- BuildMode-System
- (Geplant) Stromfluss-Simulation
- (Geplant) Geld- und Upgrade-System

---

## Ziel des Spiels

Versorge möglichst viele Häuser mit Strom, indem du ein effizientes Stromnetz baust.  
Später sollen Faktoren wie Effizienz, Verluste und Kosten eine Rolle spielen.

---

## Steuerung

| Taste | Funktion |
|------|--------|
| `1` | PowerPole bauen |
| `2` | PowerLine erstellen |
| `3` | PowerPlant bauen |
| `4` | Transformer bauen |
| `5` | House bauen |

### PowerLine bauen

1. Klicke auf ein erstes Objekt  
2. Klicke auf ein zweites Objekt  
→ Eine Verbindung wird erstellt

---

## Technische Umsetzung

Das Projekt basiert auf einem einfachen **MVC-Ansatz**:

- **Model**
  - `Grid`
  - `GridObject`
  - `House`, `PowerPlant`, `Transformer`, `PowerPole`, `PowerLine`

- **View**
  - `PaintArea` (Swing JPanel)

- **Controller**
  - `Game` (Spiel-Logik und Input)

### Verbindungen

- PowerLines verbinden zwei `GridObject`-Instanzen
- Stromfluss wird später über diese Verbindungen berechnet

---

## Geplante Features

- Stromfluss (BFS/DFS über PowerLines)
- Häuser werden gelb, wenn sie Strom haben
- Transformator-Verluste
- Geldsystem & Punkte
- Upgrades (bessere Kraftwerke, effizientere Leitungen)
- UI / Info-Boxen
- Sprites & Animationen
1. Repository klonen:
```bash
git clone <repo-url>
