package com.example.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1, button2;
    private Fragment fragment1, fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find views from layout
        button1 = findViewById(R.id.button_fragment1);
        button2 = findViewById(R.id.button_fragment2);

        // Initialize fragment instances
        fragment1 = new FragmentOne();
        fragment2 = new FragmentTwo();

        // Set button click listeners
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        // Load the first fragment by default
        loadFragment(fragment1);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == R.id.button_fragment1) {
            loadFragment(fragment1);
        } else if (viewId == R.id.button_fragment2) {
            loadFragment(fragment2);
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
