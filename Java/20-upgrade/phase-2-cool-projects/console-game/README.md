# Text Adventure — بازی ماجراجویی متنی

بازی کنسول چندمرحله‌ای: پیدا کردن شمشیر و کلید در غار، ورود به قلعه،
شکست جادوگر. بدون dependency — فقط `java.util`.

## اجرا
```bash
javac -d out src/TextAdventure.java
java -cp out TextAdventure
```

## جهان
```
دهکده(start) → جنگل(forest) → غار(cave) — گوهر +50 طلا
                     ↓
                  قلعه(castle) → زیرزمین(dungeon) — جادوگر
```

## دستورها
`north/south/east/west` حرکت · `look` · `take sword|key` · `inventory` · `attack` · `stats` · `quit`

## توسعه
`project/PROJECT.md` — نسخهٔ پیشرفته‌تر با معما و چند پایان.
