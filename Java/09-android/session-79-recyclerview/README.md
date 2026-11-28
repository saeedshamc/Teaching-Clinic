# جلسه ۷۹ — RecyclerView

## اهداف یادگیری
- `RecyclerView` و `Adapter` را بشناسید
- `ViewHolder` pattern را بفهمید
- `LinearLayoutManager` برای layout را به‌کار ببرید
- `notifyDataSetChanged` برای dynamic updates را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۷۸ (Layouts و XML)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### RecyclerView — لیست scrollable

```xml
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### Adapter — data binding

```java
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> items;

    public MyAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text);
        }
    }
}
```

### LinearLayoutManager

```java
RecyclerView recyclerView = findViewById(R.id.recyclerView);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(new MyAdapter(items));
```

### item_layout.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:padding="16dp">

    <TextView
        android:id="@+id/item_text"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:textSize="16sp" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Delete" />
</LinearLayout>
```

## کد مهم

`src/MyAdapter.java` — RecyclerView adapter.
`src/item_layout.xml` — item layout.
`src/MainActivity.java` — RecyclerView setup.

## خطاهای رایج
- `ViewHolder` را فراموش کردن → performance issues.
- `onBindViewHolder` را بدون `position` استفاده کردن → wrong data.
- `notifyDataSetChanged` را زیاد استفاده کردن → inefficient — `notifyItemChanged` بهتر است.
- `LayoutInflater` را فراموش کردن → null view.

## جمع‌بندی
- RecyclerView برای efficient lists — ViewHolder pattern.
- `Adapter` برای data binding، `LayoutManager` برای layout.
- `notifyDataSetChanged` برای updates.

**جلسهٔ بعد:** Fragments.
