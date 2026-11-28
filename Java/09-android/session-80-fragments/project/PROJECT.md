# پروژهٔ جلسه ۸۰ — سیستم چند Fragment

یک سیستم چند Fragment بسازید که:
- `MainActivity` با `FrameLayout` container
- `Fragment1` با `TextView` و `Button`
- `Fragment2` با `ListView`
- `FragmentTransaction` برای switch بین fragments
- `OnFragmentInteractionListener` برای communication
- `add`/`replace`/`hide`/`show` برای fragment management

نمونه:
```
[Button: Show Fragment 1] [Button: Show Fragment 2]
[Fragment Container]
→ Fragment 1: "Hello from Fragment 1"
→ Fragment 2: List of items
```

## صورت پروژه
پاسخ در `solution/` — MainActivity.java + Fragment1.java + Fragment2.java + layouts.
