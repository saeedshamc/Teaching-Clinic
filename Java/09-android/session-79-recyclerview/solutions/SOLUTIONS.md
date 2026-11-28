# پاسخ تمرین‌های جلسه ۷۹

## آسان

**۱ و ۲.**
```java
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ViewHolder(View itemView) { super(itemView); textView = itemView.findViewById(R.id.item_text); }
    }
}
```

## متوسط

**۳ و ۴.**
```java
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(new MyAdapter(items));

@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.textView.setText(items.get(position));
}
```

## سخت

**۵ و ۶.**
```java
items.add("New Item");
adapter.notifyDataSetChanged();

recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
```
