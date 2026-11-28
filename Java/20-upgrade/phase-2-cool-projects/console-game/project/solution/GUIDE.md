# راهنمای پاسخ — ماجراجویی پیشرفته

## معما
```java
// در یک اتاق «معما»: عدد سه‌رقمی تصادفی → کاربر ۵ تلاش دارد
int secret = 100 + new Random().nextInt(900);
for (int tries = 0; tries < 5; tries++) {
    System.out.print("عدد را حدس بزن: ");
    int guess = Integer.parseInt(sc.nextLine());
    if (guess == secret) { inventory.add("treasure"); break; }
    System.out.println(guess < secret ? "بیشتر" : "کمتر");
}
```

## نبرد نوبتی
```java
static class Enemy { String name; int hp, maxHp, damage; }
// turn loop: player attack → enemy attack → potion/flee
// استفاده از Thread.sleep برای «نوبت»
```

## Save/Load
```java
// save.txt: health|gold|roomId|items(comma)|hasSword|hasKey
static void save() {
    try (PrintWriter w = new PrintWriter("save.txt")) {
        w.printf("%d|%d|%s|%s|%b|%b", health, gold, currentId,
            String.join(",", inventory), hasSword, hasKey);
    } catch (IOException e) {}
}
static void load() { /* parse برعکس */ }
```

## NPC دیالوگ
```java
// در یک اتاق: System.out.println("مرد پیری می‌گوید: 'شمشیر را در غار بیاب...'")
// با flag boolean talkedToNpc — فقط یک‌بار
```
