# جلسه ۷۸ — Layouts و XML

## اهداف یادگیری
- `LinearLayout`, `RelativeLayout`, `ConstraintLayout`, `FrameLayout` را بشناسید
- `match_parent`, `wrap_content`, `dp`, `sp` را بفهمید
- `id`, `layout_margin`, `padding` را به‌کار ببرید
- nested layouts برای complex UIs را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۷۷ (Activities و Intents)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### LinearLayout — عمودی/افقی

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Title" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Click" />
</LinearLayout>
```

### ConstraintLayout — flexible positioning

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:text="Centered Title" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toBottomOf="@id/title"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:text="Click" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

### FrameLayout — روی هم

```xml
<FrameLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:src="@drawable/background" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:text="Overlay" />
</FrameLayout>
```

### dp vs sp

- `dp` — density-independent pixels — برای dimensions
- `sp` — scale-independent pixels — برای text size (respects user font settings)

## کد مهم

`src/activity_main.xml` — LinearLayout با constraint.
`src/activity_complex.xml` — nested layouts.

## خطاهای رایج
- `match_parent` را برای width/height استفاده کردن → گاهی `wrap_content` بهتر است.
- `dp` را برای text size استفاده کردن → `sp` بهتر است.
- nested layouts را زیاد کردن → performance issues — ConstraintLayout بهتر است.
- `id` را فراموش کردن → `findViewById` کار نمی‌کند.

## جمع‌بندی
- LinearLayout برای simple، ConstraintLayout برای complex، FrameLayout برای overlay.
- `match_parent`/`wrap_content` برای sizing، `dp`/`sp` برای units.
- Nested layouts را minimize کنید — ConstraintLayout preferable.

**جلسهٔ بعد:** RecyclerView.
