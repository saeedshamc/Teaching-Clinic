// Fragment2.java — پاسخ پروژهٔ جلسه ۸۰
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        ListView listView = view.findViewById(R.id.f2_list);
        List<String> items = new ArrayList<>();
        items.add("Item 1"); items.add("Item 2"); items.add("Item 3");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            requireContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        return view;
    }
}
