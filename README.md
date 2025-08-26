# 📱 NotesApp

> **Modern Notes App built with Jetpack Compose** - UI design from Figma implemented using MCP
servers for seamless design-to-code workflow

![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)

## ✨ Features

- 🎨 **Modern UI Design** - Beautiful, colorful notes with custom themes
- 📋 **Note Management** - Create, edit, and organize your notes efficiently
- 🌈 **Color-Coded Categories** - Visual organization with vibrant note cards
- 📱 **Material 3 Design** - Following latest Android design guidelines
- ⚡ **High Performance** - Built with Jetpack Compose for smooth animations
- 🌙 **Dark Theme** - Eye-friendly interface with custom color scheme

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **Design System**: Material 3
- **Build System**: Gradle with Kotlin DSL
- **Target SDK**: Android 34

## 🎨 Design-to-Code Workflow

This project showcases a modern design-to-code workflow using:

- **Figma MCP Server** - Direct integration with Figma designs
- **GitHub MCP Server** - Automated repository management
- **Model Context Protocol (MCP)** - Seamless tool integration

The UI components were generated directly from Figma designs, demonstrating how MCP servers can
bridge the gap between design and development.

## 📦 Project Structure

```
app/
├── src/main/java/com/camachoyury/notesapp/
│   ├── MainActivity.kt              # Entry point
│   ├── ui/
│   │   ├── HomeScreen.kt           # Main notes interface
│   │   ├── data/Note.kt            # Note data model
│   │   └── theme/                  # Custom theming
│   │       ├── Color.kt            # Figma-extracted colors
│   │       ├── Theme.kt            # Material 3 theme
│   │       └── Type.kt             # Typography definitions
│   └── ...
└── ...
```

## 🎨 Color Palette

The app uses a vibrant color palette extracted directly from the Figma design:

- 🟣 **Purple**: `#fd99ff` - UI concepts
- 🩷 **Pink**: `#ff9e9e` - Book reviews
- 🟢 **Green**: `#91f48f` - Anime lists
- 🟡 **Yellow**: `#fff599` - Manga lists
- 🩵 **Cyan**: `#9effff` - Tweet collections
- 🟦 **Blue**: `#b69cff` - Open source apps

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog | 2023.1.1 or later
- Kotlin 1.9.0+
- Android SDK 34
- JDK 17+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/camachoyury/NotesApp.git
   cd NotesApp
   ```

2. **Open in Android Studio**
    - Launch Android Studio
    - Select "Open an Existing Project"
    - Navigate to the cloned directory

3. **Build and Run**
   ```bash
   ./gradlew assembleDebug
   ```
   Or use the Run button in Android Studio

## 🏗️ Architecture

This app follows Clean Architecture principles with:

- **UI Layer**: Jetpack Compose components
- **Domain Layer**: Business logic and use cases
- **Data Layer**: Repository pattern for data management

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 👨‍💻 Author

**Yury Camacho**

- 🏢 Staff Software Engineer at [Rappi](https://rappi.com)
- 🥇 Google Developer Expert for Android & Kotlin
- 🐦 Twitter: [@camachoyury](https://twitter.com/camachoyury)
- 💼 LinkedIn: [Yury Camacho](https://linkedin.com/in/camachoyury)

---

⭐ Don't forget to star this repo if you found it helpful!