// HomeFragment.java — home fragment با navigation
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button detailBtn = view.findViewById(R.id.detail_button);
        Button settingsBtn = view.findViewById(R.id.settings_button);

        detailBtn.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            Bundle args = new Bundle();
            args.putInt("itemId", 42);
            navController.navigate(R.id.action_home_to_detail, args);
        });

        settingsBtn.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_home_to_settings);
        });

        return view;
    }
}
