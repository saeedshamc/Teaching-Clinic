// DetailFragment.java — detail fragment با arguments
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        TextView detailText = view.findViewById(R.id.detail_text);
        Bundle args = getArguments();
        if (args != null) {
            int itemId = args.getInt("itemId", 0);
            detailText.setText("Item ID: " + itemId);
        }

        return view;
    }
}
