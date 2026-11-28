# جلسه ۹۴ — Android Final Project Prep

## اهداف یادگیری
- معماری کامل اپ Android را برنامه‌ریزی کنید
- ترکیب Jetpack + Legacy را برای hybrid app بفهمید
- `build.gradle`, `AndroidManifest`, `res/` structure را organize کنید
- Testing strategy را برای Android app یاد بگیرید

## پیش‌نیازها
- جلسات ۷۶–۹۳

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### معماری کامل اپ

```
app/
├── src/main/
│   ├── java/com/example/myapp/
│   │   ├── data/
│   │   │   ├── local/ (Room)
│   │   │   ├── remote/ (Retrofit)
│   │   │   └── repository/ (Repository)
│   │   ├── ui/
│   │   │   ├── activities/
│   │   │   ├── fragments/
│   │   │   ├── viewmodels/
│   │   │   └── adapters/
│   │   ├── di/ (Hilt)
│   │   ├── service/ (Services)
│   │   ├── receiver/ (BroadcastReceiver)
│   │   └── utils/
│   ├── res/
│   │   ├── layout/
│   │   ├── navigation/
│   │   ├── values/
│   │   └── drawable/
│   └── AndroidManifest.xml
└── build.gradle
```

### Tech Stack Recommendation

| Layer | Technology |
|---|---|
| UI | Jetpack Compose یا XML + Navigation |
| Architecture | MVVM + Repository |
| Local DB | Room |
| Remote API | Retrofit + OkHttp |
| DI | Hilt |
| Async | Coroutines + Flow |
| Background | WorkManager |
| Preferences | DataStore |
| Notifications | NotificationCompat |

### Testing Strategy

- **Unit tests** — ViewModel, Repository, UseCase
- **Integration tests** — Room, Retrofit (with MockWebServer)
- **UI tests** — Espresso یا Compose Testing

## کد مهم

`src/build.gradle` — dependencies.
`src/AppStructure.java` — architecture overview.

## جمع‌بندی
- معماری کامل با MVVM + Repository + Room + Retrofit + Hilt.
- Testing با unit + integration + UI tests.
- Project structure برای maintainability.

**جلسهٔ بعد:** Android Capstone — پروژهٔ نهایی.
