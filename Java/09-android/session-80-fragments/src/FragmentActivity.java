// FragmentActivity.java — activity با fragment
// نیازمند Android SDK
package com.example.myapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class FragmentActivity extends AppCompatActivity implements MyFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // ---------- Add fragment ----------
        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new MyFragment());
            ft.commit();
        }
    }

    @Override
    public void onFragmentButtonClick(String data) {
        TextView status = findViewById(R.id.status);
        status.setText("Fragment says: " + data);
    }
}
