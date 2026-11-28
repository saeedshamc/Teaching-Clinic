# پاسخ تمرین‌های بازی ماجراجویی

## آسان
جهان در `buildWorld()` — `room()` و `link()` اتاق‌ها را می‌سازد.
`takeItem()` آیتم‌ها را بر اساس اتاق فعلی می‌دهد.

## متوسط
**۴ و ۵.** `hasSword`/`hasKey` در `stats` نمایش داده می‌شوند.
`attack()` در اتاق «زیرزمین» با احتمال ۰.۶ جادوگر را شکست می‌دهد → `bossDefeated` → `victory()`.
**۶.** بدون شمشیر: `health -= 30` و پیام «بدون شمشیر».

## سخت

**۷. چشمه:**
```java
// اتاق جدید در buildWorld + link
Room spring = room("spring", "چشمهٔ جادو", "آب درخشان با بوی نعنا");
link("forest", "west", "spring"); link("spring", "east", "forest");
// در onEnterRoom:
case "spring" -> { health = Math.min(100, health + 50); System.out.println("💧 آب جادو drankti — +50 جان"); }
```

**۸. XP:**
```java
static int xp = 0;
// در attack موفق: xp += 50
// چاپ در stats: System.out.printf("... XP: %d%n", xp)
```

**۹. دو پایان:**
```java
static void victory() {
    if (gold > 100) System.out.println("🏆 پایان ثروتمند!");
    else System.out.println("⚔ پایان قهرمان!");
}
```
