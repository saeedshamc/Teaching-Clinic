# پاسخ تمرین‌های جلسه ۷۸

## آسان

**۱ و ۲.**
```xml
<LinearLayout android:orientation="vertical">
    <TextView android:layout_width="wrap_content" android:layout_height="wrap_content"/>
    <Button android:layout_width="match_parent" android:layout_height="wrap_content"/>
</LinearLayout>
```

## متوسط

**۳ و ۴.**
```xml
<androidx.constraintlayout.widget.ConstraintLayout>
    <TextView app:layout_constraintTop_toTopOf="parent"/>
</androidx.constraintlayout.widget.ConstraintLayout>

<FrameLayout>
    <ImageView android:layout_width="match_parent" android:layout_height="match_parent"/>
    <TextView android:layout_gravity="center"/>
</FrameLayout>
```

## سخت

**۵ و ۶.**
```xml
<LinearLayout android:orientation="vertical">
    <LinearLayout android:orientation="horizontal" android:layout_weight="1">
        <!-- sidebar -->
    </LinearLayout>
</LinearLayout>
<!-- dp برای dimensions, sp برای text -->
```
