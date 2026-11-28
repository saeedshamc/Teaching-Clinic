# جلسه ۸۰ — Fragments

## اهداف یادگیری
- `Fragment` را بشناسید — reusable UI component
- `FragmentManager` و `FragmentTransaction` را بفهمید
- `add`, `replace`, `remove`, `show`, `hide` را به‌کار ببرید
- communication بین fragments و activity را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۷۹ (RecyclerView)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Fragment — reusable UI

```java
public class MyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }
}
```

### FragmentTransaction — add/replace

```java
FragmentManager fm = getSupportFragmentManager();
FragmentTransaction ft = fm.beginTransaction();
ft.replace(R.id.fragment_container, new MyFragment());
ft.commit();
```

### fragment_my.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Fragment Content"
        android:textSize="18sp" />

    <Button
        android:id="@+id/fragment_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Click" />
</LinearLayout>
```

### Communication — interface

```java
// In Fragment
public interface OnFragmentInteractionListener {
    void onFragmentButtonClick(String data);
}

private OnFragmentInteractionListener listener;

@Override
public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
        listener = (OnFragmentInteractionListener) context;
    }
}

// In Activity
public class MainActivity extends AppCompatActivity implements MyFragment.OnFragmentInteractionListener {
    @Override
    public void onFragmentButtonClick(String data) {
        // handle fragment event
    }
}
```

## کد مهم

`src/MyFragment.java` — fragment با interface.
`src/fragment_my.xml` — fragment layout.
`src/MainActivity.java` — fragment transaction.

## خطاهای رایج
- `getSupportFragmentManager` را فراموش کردن → crash.
- `commit()` را روی wrong thread صدا زدن → `IllegalStateException`.
- `onAttach` را بدون `super` صدا زدن → crash.
- `R.id.fragment_container` را در layout فراموش کردن → fragment نمایش داده نمی‌شود.

## جمع‌بندی
- Fragment برای reusable UI — می‌تواند در چند activity استفاده شود.
- `FragmentTransaction` برای add/replace/remove.
- Interface برای communication بین fragment و activity.

**جلسهٔ بعد:** Legacy Capstone.
