# پروژهٔ جلسه ۷۸ — داشبورد Android با Layouts

یک داشبورد Android بسازید که:
- `LinearLayout` برای ساختار اصلی (header, content, footer)
- `ConstraintLayout` برای centered elements
- `FrameLayout` برای overlay
- nested layouts برای complex UI
- `dp`/`sp` برای units
- `match_parent`/`wrap_content` برای sizing

نمونه:
```
[Header — LinearLayout horizontal]
[Sidebar | Content — LinearLayout horizontal]
[Footer — LinearLayout horizontal]
```

## صورت پروژه
پاسخ در `solution/` — activity_dashboard.xml + DashboardActivity.java.
