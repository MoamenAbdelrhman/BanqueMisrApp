# Banque Misr Login UI (Jetpack Compose)

A simple Android implementation of a login screen inspired by Banque Misr design using Jetpack Compose.

## Features

- Login UI (Username & Password)
- Password visibility toggle
- Enable/disable login button based on input
- Arabic / English language switching
- RTL / LTR layout support
- Persistent language preference via SharedPreferences
- Built with modern Compose (state-based TextField)

## Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- `rememberTextFieldState` (state-based input)
- `SharedPreferences` for language persistence

## Key Implementation

### State-based TextFields

```kotlin
val usernameState = rememberTextFieldState()
val passwordState = rememberTextFieldState()
```

### Password Visibility Toggle

```kotlin
outputTransformation = if (passwordVisible) null else OutputTransformation {
    replace(0, length, "•".repeat(length))
}
```

### Language Switching (No Activity Recreation)

```kotlin
// Toggle and persist
LocaleManager.toggle(context)

// Restore on launch
LocaleManager.init(context) // called in MainActivity.onCreate()
```

## How to Run

1. Clone the repo:

```bash
git clone https://github.com/your-username/banque-misr-compose.git
```

2. Open in Android Studio
3. Run the app
