# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

HencoderPlus is an Android learning project containing course notes and sample implementations. It's a multi-module Gradle project organized into educational modules covering Android development topics from basic HTTP networking to advanced UI components and Jetpack libraries.

## Build Commands

**Note**: On Windows, use `gradlew` instead of `./gradlew` for all commands below.

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
- `./gradlew :coroutine:01_introduce:testDebugUnitTest --tests "com.hsicen.a01_introduce.ExampleTest"` - Run a single test class

### Working with Specific Modules
- `./gradlew :compose:todo:assembleDebug` - Build a specific module
- `./gradlew :hencoder:10_drawing:test` - Run tests for a specific module
- `./gradlew projects` - List all available modules

## Architecture

### Module Organization

The project is organized into 5 main categories:

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
   - Note: coroutine modules use Compose for UI (not traditional Views)

### Build Configuration

The project uses a centralized build configuration system via composite build in `gradle/build-logic/`:

- **Dependencies.kt** (`gradle/build-logic/src/main/kotlin/Dependencies.kt`) - All dependency versions and library definitions
  - `Versions` object: SDK versions and library version numbers
  - `Deps` object: Library dependencies
  - `TestDeps` object: Testing dependencies
  - `Mavens` object: Repository URLs (uses Aliyun mirrors)

- **Convention Plugins** in `gradle/build-logic/src/main/kotlin/comm/`:
  - `app-module.gradle.kts` - Standard Android application module config
  - `lib-module.gradle.kts` - Standard Android library module config
  - `app-compose-module.gradle.kts` - Compose application module config
  - `lib-compose-module.gradle.kts` - Compose library module config
  - `kotlin-lib.gradle.kts` - Pure Kotlin/Java library module config

All modules reference these convention plugins for consistent configuration. The build logic is included via `includeBuild("gradle/build-logic")` in `settings.gradle.kts`.

### Module Types

- **Standard Android apps**: Use `comm.app-module` plugin (ViewBinding enabled)
- **Compose apps**: Use `comm.app-compose-module` plugin (Compose enabled)
- **Android libraries**: Use `comm.lib-module` plugin
- **Compose libraries**: Use `comm.lib-compose-module` plugin
- **Pure Kotlin modules**: Use `comm.kotlin-lib` plugin (for `hencoder/1_http` and similar)
- **Hencoder plugin wrapper**: `comm.hencoder-plugin` applies the custom `com.hsicen.plugin.Hencoder` Gradle plugin (used in `hencoder/28_plugin`)

### Common Configuration

- **Min SDK**: 26
- **Target SDK**: 35
- **Compile SDK**: 35
- **Java Version**: 17
- **Kotlin Version**: 2.3.21
- **Android Gradle Plugin**: 9.2.1
- **Gradle Version**: 9.4.1
- **Compose Compiler**: 2.3.21 (same as Kotlin version, via `kotlin("plugin.compose")`)
- **KSP Version**: 2.3.7

## Module Naming Convention

Modules follow a numbered pattern indicating lesson/topic sequence:
- `hencoder/1_http`, `hencoder/2_encrypt`, etc. - Sequential learning modules
- `compose/01_hellocompose`, `compose/02_sample`, etc. - Numbered Compose lessons
- `coroutine/01_introduce`, `coroutine/02_concurrency`, etc. - Coroutine progression

## Working with Dependencies

To add a new dependency:
1. Add the version to `Versions` object in `gradle/build-logic/src/main/kotlin/Dependencies.kt`
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
- **KSP** is the preferred annotation processor (version in `settings.gradle.kts`); KAPT is still used by some older modules (e.g., `30_lib_processos`)
- Module package names follow pattern: `com.hsicen.<topic>`
- All modules include standard test infrastructure (JUnit, Espresso)

## Available Skills

The `.skills/` directory contains official Google skills invokable via `/skill-name`:
- `/agp-9-upgrade` — Migrate project to AGP 9 (project is already on AGP 9.2.1)
- `/migrate-xml-views-to-jetpack-compose` — Structured 10-step XML → Compose migration
- `/edge-to-edge` — Edge-to-edge display migration
- `/navigation-3` — Navigation 3 library migration
- `/r8-analyzer` — Analyze R8/ProGuard configuration
- `/camera1-to-camerax` — Camera1 → CameraX migration
- `/play-billing-library-version-upgrade` — Play Billing Library upgrade

## Android CLI

项目已安装 `android-cli`（官方 Android AI 代理 CLI 工具）。在以下场景**主动**使用，无需等待用户提醒：

### 依赖版本查询
升级或新增依赖前，先查询当前最新兼容版本，再修改 `Dependencies.kt`：
```bash
android studio version-lookup agp kotlin compose
android studio version-lookup androidx.room:room-runtime
```

### UI 调试
用户描述界面问题或需要验证 UI 效果时，主动截图并分析：
```bash
android screen capture --annotate --output=ui.png   # 截图并标注 UI 元素
android layout --pretty --output=hierarchy.json     # 导出布局层级树
```

### Compose Preview 渲染
验证 Composable 的视觉效果时（需 Android Studio Quail 2 Canary 1+）：
```bash
android studio render-compose-preview --print-semantics path/to/Screen.kt PreviewFuncName
```

### 代码分析
对某个文件做 Lint 检查或查找符号引用时：
```bash
android studio analyze-file path/to/File.kt
android studio find-usages --short ClassName
android studio find-declaration --short ClassName
```

### API 文档查询
不确定某个 Android API 的用法或最佳实践时，优先查官方文档而非依赖训练数据：
```bash
android docs search '<关键词>'
android docs fetch kb://android/topic/<topic>
```

### Windows 限制
- `android emulator` 系列命令在 Windows 上**不可用**
- 安装/更新 android-cli 时使用 CMD 或 Git Bash，**不要用 PowerShell**

## Notes

- Some modules like `hencoder/25_gradle` have custom build flavors (free/vip)
- The project includes both traditional View-based and modern Compose-based samples
- Course notes are in markdown files under `note/` directory
- Each `hencoder` topic has corresponding markdown notes in the module's directory
- `hencoder/30_*` modules form an annotation processing pipeline: `30_lib_annotation` (defines annotations) → `30_lib_processos` (APT processor) → `30_lib` (runtime binding) → `30_annotation` / `30_annotation_reflection` (demo apps)
- `hencoder/28_plugin` and `hencoder/28_plugin_lib` are paired: the lib module defines the Gradle plugin, the app module demonstrates its use
