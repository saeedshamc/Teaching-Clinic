# پاسخ تمرین‌های جلسه ۸۰

## آسان

**۱ و ۲.**
```java
public class MyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }
}
```

## متوسط

**۳ و ۴.**
```java
FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
ft.replace(R.id.fragment_container, new MyFragment());
ft.commit();

public interface OnFragmentInteractionListener {
    void onFragmentButtonClick(String data);
}
```

## سخت

**۵ و ۶.**
```java
ft.add(R.id.container1, new Fragment1());
ft.add(R.id.container2, new Fragment2());
ft.hide(fragment1);
ft.show(fragment2);
ft.commit();

Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
```
