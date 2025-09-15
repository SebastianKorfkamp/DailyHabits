# Daily Habits 📱

Eine einfache und elegante Android-App zum Verfolgen von täglichen Gewohnheiten, entwickelt mit Jetpack Compose und Room Database.

## ✨ Features

- **Habit-Tracking**: Erstellen und verwalten Sie Ihre täglichen Gewohnheiten
- **Streak-System**: Verfolgen Sie Ihre Erfolgssträhnen über mehrere Tage
- **Persistente Speicherung**: Alle Daten bleiben mit Room Database erhalten
- **Intuitive UI**: Modernes Material 3 Design mit Jetpack Compose
- **Fortschritts-Dashboard**: Übersichtliche Darstellung Ihrer Gewohnheiten und Statistiken

## 🎯 Funktionen

### Habit-Verwaltung
- ✅ Neue Gewohnheiten hinzufügen
- ✅ Gewohnheiten als erledigt markieren
- ✅ Gewohnheiten löschen
- ✅ Tägliche Fortschritte zurücksetzen

### Streak-Tracking
- 🔥 Automatische Streak-Zählung bei aufeinanderfolgenden Tagen
- 📊 Intelligente Streak-Logik (Reset bei Unterbrechung)
- 🏆 Motivierende Fortschritts-Stufen

### Fortschritts-Übersicht
- 📈 Detaillierte Statistiken pro Gewohnheit
- 📊 Zusammenfassung aller Habits
- 🎨 Visuelle Fortschrittsbalken

## 🛠️ Technologie-Stack

- **Kotlin** - Moderne Android-Entwicklung
- **Jetpack Compose** - Deklarative UI
- **Room Database** - Lokale Datenspeicherung
- **MVVM Architecture** - Saubere Code-Struktur
- **Coroutines & Flow** - Asynchrone Datenverarbeitung
- **Material 3** - Modernes Design System

## 🏗️ Architektur

```
📁 com.example.dailyhabits/
├── 📁 data/              # Room Database (Entity, DAO, Database)
├── 📁 model/             # Domain Models
├── 📁 repository/        # Repository Pattern
├── 📁 view/              # Compose UI Screens
├── 📁 viewmodel/         # ViewModels & Business Logic
└── MainActivity.kt       # Entry Point
```

## 🚀 Installation

1. **Repository klonen**
   ```bash
   git clone [your-repo-url]
   ```

2. **Android Studio öffnen** und das Projekt importieren

3. **Build & Run** - Die App kompiliert und startet automatisch

## 📱 Screenshots

### Habit-Liste
- Übersichtliche Liste aller Gewohnheiten
- Schnelle Aktionen (Abhaken, Löschen)
- Streak-Anzeige mit Emoji-Indikatoren

### Fortschritts-Dashboard
- Detaillierte Statistiken
- Visuelle Fortschrittsbalken
- Motivierende Meilenstein-Texte

## 🎮 Verwendung

1. **Neue Gewohnheit hinzufügen**: Tippen Sie auf das ➕ Symbol
2. **Gewohnheit abhaken**: Tippen Sie auf den ✅ Button
3. **Fortschritt anzeigen**: Wechseln Sie zum "Fortschritt" Tab
4. **Gewohnheit löschen**: Tippen Sie auf das 🗑️ Symbol

## 🔧 Anforderungen

- **Android API Level 24+** (Android 7.0)
- **Kotlin 1.9+**
- **Compose BOM 2023.10.01+**

## 📝 Lizenz

Dieses Projekt ist unter der MIT Lizenz veröffentlicht.

## 🤝 Beiträge

Contributions sind willkommen! Feel free to:
- 🐛 Bugs melden
- 💡 Features vorschlagen  
- 🔧 Pull Requests erstellen

## 📧 Kontakt

Bei Fragen oder Anregungen können Sie gerne ein Issue erstellen.

---

**Happy Habit Tracking! 🎯✨**
