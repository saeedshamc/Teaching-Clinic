# پروژهٔ جلسه ۸۹ — اپ تنظیمات با Hilt و DataStore

یک اپ تنظیمات بسازید که:
- `@HiltAndroidApp` در Application
- `@AndroidEntryPoint` در Activity
- `@Inject` برای dependencies
- `SettingsRepository` با DataStore
- `Flow` برای reactive settings
- `setTheme`, `setLanguage`, `setNotifications` methods
- `SettingsActivity` برای UI

نمونه:
```
[Theme: Light ▼] [Language: English ▼] [Notifications: ON]
→ Settings saved to DataStore
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
