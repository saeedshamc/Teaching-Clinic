# جلسه ۸۸ — Coroutines و Flow

## اهداف یادگیری
- `kotlinx.coroutines` را برای async programming بشناسید
- `suspend` functions و `launch`, `async` را بفهمید
- `Flow` برای reactive streams را به‌کار ببرید
- `StateFlow`, `SharedFlow` را برای UI state یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۸۷ (Navigation)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Coroutines — lightweight threads

```kotlin
// build.gradle
dependencies {
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3'
}
```

### suspend functions

```kotlin
suspend fun fetchData(): String {
    delay(1000)   // suspend — doesn't block thread
    return "Data"
}
```

### launch — fire and forget

```kotlin
lifecycleScope.launch {
    val result = fetchData()
    // update UI
}
```

### async — deferred result

```kotlin
lifecycleScope.launch {
    val deferred1 = async { fetchData1() }
    val deferred2 = async { fetchData2() }
    val result1 = deferred1.await()
    val result2 = deferred2.await()
}
```

### Flow — reactive streams

```kotlin
fun getDataFlow(): Flow<List<Item>> = flow {
    while (true) {
        emit(fetchData())
        delay(5000)
    }
}

lifecycleScope.launch {
    getDataFlow().collect { data ->
        // update UI
    }
}
```

### StateFlow — for UI state

```kotlin
class MyViewModel : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            _state.value = State.Loading
            try {
                val data = fetchData()
                _state.value = State.Success(data)
            } catch (e: Exception) {
                _state.value = State.Error(e.message)
            }
        }
    }
}
```

## کد مهم

`src/CoroutinesExample.kt` — coroutines و Flow.
`src/MyViewModel.kt` — ViewModel با StateFlow.

## خطاهای رایج
- `suspend` را بدون `launch`/`async` صدا زدن → compile error.
- `Flow` را بدون `collect` استفاده کردن → data emit نمی‌شود.
- `StateFlow` را بدون `asStateFlow` expose کردن → mutable state leak.
- `delay` را روی main thread استفاده کردن → ANR — coroutines برای this.

## جمع‌بندی
- Coroutines برای lightweight async — `suspend`, `launch`, `async`.
- `Flow` برای reactive streams — `StateFlow` for UI state.
- `viewModelScope` for lifecycle-aware coroutines.

**جلسهٔ بعد:** Hilt و DataStore.
