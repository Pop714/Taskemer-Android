# 🚀 Taskemer

**Taskemer** is a high-performance productivity and focus application built with modern Android development practices.
It goes beyond traditional task managers by combining offline-first project tracking with powerful system-level focus tools.

Designed with a premium **high-contrast noir aesthetic**, Taskemer delivers a smooth, immersive experience powered by custom Jetpack Compose components, fluid animations, and efficient local-to-remote data synchronization.

---

## 📱 Screenshots

<div align="center">
  <img src="https://github.com/user-attachments/assets/eea7a6a7-0931-4274-a682-fb09188d3eaf" alt="Dashboard Screen" width="220"/>
  &nbsp;&nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/e35c5fe9-4314-40b9-98b3-bd9a09ff6718" alt="Projects Screen" width="220"/>
  &nbsp;&nbsp;&nbsp;
  <img src="https://github.com/user-attachments/assets/2a95b9bd-c138-4b2b-8478-8f3f397a792c" alt="Focus Timer Screen" width="220"/>
</div>

---

## ✨ Features

### 📊 Smart Dashboard

* Project overviews
* Task distribution insights
* Custom productivity pulse chart

---

### 🎯 Deep Focus Mode

* Pomodoro-style focus system
* Runs via **Foreground Services** (works on lock screen)
* Integrates with **Do Not Disturb (DND)**
* Fully customizable sessions

---

### ⚡ Offline-First Experience

* Instant persistence with **Room Database**
* No loading delays
* Works fully offline

---

### 🎨 Adaptive Edge-to-Edge UI

* Transparent system UI
* Animated floating bottom navigation (auto-hide)
* Neon/glow effects
* Smooth scroll animations

---

### 🔐 Secure Authentication

* JWT-based login & registration
* Cached sessions via **DataStore**

---

### 🌐 Real-Time Connectivity

* Network monitoring using **Kotlin Flow**
* Reactive UI updates

---

## 🛠 Tech Stack

### 📱 Android Client

* Jetpack Compose (Material 3)
* MVVM + Clean Architecture
* Dagger Hilt
* Room Database
* Compose Navigation
* Coroutines & Flow
* Retrofit + OkHttp
* DataStore
* Foreground Services & Notifications

---

### 🖥 Backend (Taskemer Server)

* Java / Spring Boot
* Spring Security + JWT
* MySQL
* 3-Layer Architecture

---

## 🚀 Getting Started

### 📌 Prerequisites

* Android Studio Iguana or newer
* Min SDK: 28 (Android 9.0)
* Target SDK: 36 (Android 16)

---

### ⚙️ Installation

```bash
git clone https://github.com/Pop714/Taskemer-Android/.git
```

1. Open in Android Studio
2. Sync Gradle
3. Run the app

> ⚠️ For accurate DND testing, use a real device or emulator with Google Play Services.

---

## 🏗 Architecture

* UI observes ViewModels
* ViewModels expose Flow from Room
* Local DB = single source of truth
* Background sync handles remote updates

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome.
Feel free to open a pull request or check the issues page.
