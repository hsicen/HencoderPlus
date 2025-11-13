# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

HencoderPlus is an Android learning project containing course notes and sample implementations. It's a multi-module Gradle project organized into educational modules covering Android development topics from basic HTTP networking to advanced UI components and Jetpack libraries.

## Build Commands

### Basic Build Operations
- `./gradlew build` - Build all modules
- `./gradlew clean` - Clean build outputs
- `./gradlew assemble` - Assemble all variants without running tests
- `./gradlew assembleDebug` - Build debug variants
- `./gradlew assembleRelease` - Build release variants

### Testing
- `./gradlew test` - Run all unit tests
- `./gradlew testDebugUnitTest` - Run debug unit tests
- `./gradlew connectedAndroidTest` - Run instrumented tests on connected devices
- `./gradlew lint` - Run lint checks
- `./gradlew lintDebug` - Run lint on debug variant

### Working with Specific Modules
- `./gradlew :compose:todo:assembleDebug` - Build a specific module
- `./gradlew :hencoder:10_drawing:test` - Run tests for a specific module
- `./gradlew projects` - List all available modules

## Architecture

### Module Organization

The project is organized into 4 main categories:

1. **hencoder/** - Core Android training modules covering:
   - Network (HTTP, Retrofit, OkHttp, TCP/IP)
   - Encryption and authentication
   - UI (custom views, drawing, animations, layouts, touch handling)
   - Threading and concurrency (RxJava, multithreading)
   - Advanced topics (Gradle, JVM, hotfix, annotations, generics)

2. **compose/** - Jetpack Compose learning modules:
   - Basic concepts (hello world, state management)
   - UI components (animations, modifiers, custom components)
   - Integration (coroutines, view interop)
   - Theory and internals

3. **Jetpack/** - Android Jetpack components:
   - Architecture components (ViewModel, LiveData, Lifecycle)
   - Data persistence (Room, DataStore)
   - Background work (WorkManager, Coroutines)
   - UI (ViewBinding)
   - Initialization (App Startup)

4. **Inject/** - Dependency injection frameworks:
   - Dagger
   - Koin
   - Hilt (commented out in settings.gradle.kts)

5. **coroutine/** - Kotlin Coroutines deep dive:
   - Introduction and basics
   - Concurrency patterns
   - Scopes and contexts
   - Channels and flows
   - Cooperative cancellation

### Build Configuration

The project uses a centralized build configuration system via `buildSrc/`:

- **Dependencies.kt** - All dependency versions and library definitions
  - `Versions` object: SDK versions and library version numbers
  - `Deps` object: Library dependencies
  - `TestDeps` object: Testing dependencies
  - `Mavens` object: Repository URLs (uses Aliyun mirrors)

- **Convention Plugins** in `buildSrc/src/main/kotlin/comm/`:
  - `app-module.gradle.kts` - Standard Android application module config
  - `lib-module.gradle.kts` - Standard Android library module config
  - `app-compose-module.gradle.kts` - Compose application module config
  - `lib-compose-module.gradle.kts` - Compose library module config
  - `kotlin-lib.gradle.kts` - Pure Kotlin/Java library module config

All modules reference these convention plugins for consistent configuration.

### Module Types

- **Standard Android apps**: Use `comm.app-module` plugin (ViewBinding enabled)
- **Compose apps**: Use `comm.app-compose-module` plugin (Compose enabled)
- **Android libraries**: Use `comm.lib-module` plugin
- **Pure Kotlin modules**: Use `comm.kotlin-lib` plugin (for `hencoder/1_http` and similar)

### Common Configuration

- **Min SDK**: 26
- **Target SDK**: 34
- **Compile SDK**: 34
- **Java Version**: 17
- **Kotlin Version**: 2.0.20
- **Gradle Version**: 8.6.0
- **Compose Compiler**: 1.5.14

## Module Naming Convention

Modules follow a numbered pattern indicating lesson/topic sequence:
- `hencoder/1_http`, `hencoder/2_encrypt`, etc. - Sequential learning modules
- `compose/01_hellocompose`, `compose/02_sample`, etc. - Numbered Compose lessons
- `coroutine/01_introduce`, `coroutine/02_concurrency`, etc. - Coroutine progression

## Working with Dependencies

To add a new dependency:
1. Add the version to `Versions` object in `buildSrc/src/main/kotlin/Dependencies.kt`
2. Add the dependency string to `Deps` or `TestDeps` object
3. Reference it in module's `build.gradle.kts` using `Deps.dependencyName`

Example:
```kotlin
dependencies {
  implementation(Deps.retrofit)
  implementation(Deps.coroutinesCore)
}
```

## Repository Configuration

The project uses Aliyun Maven mirrors for faster dependency downloads in China:
- `maven.aliyun.com/repository/public` - Central & JCenter mirror
- `maven.aliyun.com/repository/google` - Google Maven mirror
- `maven.aliyun.com/repository/gradle-plugin` - Gradle Plugin Portal mirror
- `jitpack.io` - GitHub project builds

## Key Technical Patterns

- **ViewBinding** is enabled by default in all Android modules
- **Compose** modules use Material 3 and include common Compose dependencies
- **KAPT** is configured for modules that need annotation processing
- Module package names follow pattern: `com.hsicen.<topic>`
- All modules include standard test infrastructure (JUnit, Espresso)

## Notes

- Some modules like `hencoder/25_gradle` have custom build flavors (free/vip)
- The project includes both traditional View-based and modern Compose-based samples
- Course notes are in markdown files under `note/` directory
- Each `hencoder` topic has corresponding markdown notes in the module's directory