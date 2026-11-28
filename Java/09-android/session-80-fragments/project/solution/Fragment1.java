// Fragment1.java — پاسخ پروژهٔ جلسه ۸۰
// نیازمند Android SDK
package com.example.myapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    public interface OnFragment1Listener {
        void onFragment1ButtonClick(String data);
    }

    private OnFragment1Listener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment1Listener) {
            listener = (OnFragment1Listener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        Button button = view.findViewById(R.id.f1_button);
        button.setOnClickListener(v -> {
            if (listener != null) listener.onFragment1ButtonClick("Hello from Fragment 1!");
        });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
