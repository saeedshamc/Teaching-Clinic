# Android Task Manager — پروژهٔ واقعی اندروید

اپلیکیشن مدیریت وظایف با **Jetpack Compose + Room + ViewModel + Navigation**.
پروژهٔ Gradle کامل — در Android Studio باز و روی emulator اجرا می‌شود.

## اجرا
1. پوشهٔ `app/` را در Android Studio باز کنید
2. `Sync Project with Gradle Files`
3. یک emulator بسازید و Run کنید

## ساختار

```
app/
├── build.gradle.kts              # وابستگی‌ها: Compose, Room, Navigation, Hilt(اختیاری)
├── src/main/
│   ├── AndroidManifest.xml
│   └── java/com/example/taskmanager/
│       ├── MainActivity.kt           # Compose + Navigation host
│       ├── data/
│       │   ├── Task.kt               # entity
│       │   ├── TaskDao.kt            # Room DAO
│       │   ├── TaskDatabase.kt       # Room database
│       │   └── TaskRepository.kt     # repository
│       ├── ui/
│       │   ├── TaskViewModel.kt      # StateFlow + ViewModel
│       │   ├── TaskListScreen.kt     # لیست + FAB + checkbox
│       │   ├── TaskEditScreen.kt     # افزودن/ویرایش
│       │   └── theme/Theme.kt
│       └── navigation/NavGraph.kt
```

## تمرین‌ها و پروژه
`exercises/`, `solutions/`, `project/` — طبق ساختار استاندارد.
