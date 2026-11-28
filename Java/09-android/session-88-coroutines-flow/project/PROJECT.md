# پروژهٔ جلسه ۸۸ — اپ با Coroutines و Flow

یک اپ با Coroutines و Flow بسازید که:
- `ViewModel` با `StateFlow` برای UI state
- `suspend` functions برای async operations
- `Flow` برای reactive data streams
- `viewModelScope` برای lifecycle-aware coroutines
- `collect` برای UI updates
- `async` برای parallel operations

نمونه:
```
[Loading...] → [Success: Data loaded!] → [Counter: 5]
```

## صورت پروژه
پاسخ در `solution/` — ViewModel.kt + Activity.kt + layout.
