# پاسخ تمرین‌های جلسه ۸۸

## آسان

**۱ و ۲.**
```kotlin
suspend fun fetchData(): String {
    delay(1000)
    return "Data"
}

lifecycleScope.launch {
    val result = fetchData()
}
```

## متوسط

**۳ و ۴.**
```kotlin
val deferred1 = async { fetchData1() }
val deferred2 = async { fetchData2() }
val result = deferred1.await() + deferred2.await()

fun getDataFlow(): Flow<String> = flow {
    emit("Data")
    delay(1000)
    emit("More data")
}
```

## سخت

**۵ و ۶.**
```kotlin
private val _state = MutableStateFlow<State>(State.Loading)
val state: StateFlow<State> = _state.asStateFlow()

viewModelScope.launch {
    _state.value = State.Success(data)
}
```
