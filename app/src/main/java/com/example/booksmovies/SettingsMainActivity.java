package com.example.booksmovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsMainActivity extends AppCompatActivity {
    String theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_main_activity);
        ToggleButton themeBtn = findViewById(R.id.theme_btn);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            theme = bundle.getString("prev_theme");
            themeBtn.setChecked(!theme.equals("sunny day"));
        }

        themeBtn.setOnClickListener(v -> {
            if (themeBtn.isChecked()) {
                theme = "warm evening";
            } else {
                theme = "sunny day";
            }

            Intent intent = new Intent();
            intent.putExtra("theme", theme);
            setResult(RESULT_OK, intent);
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Settings");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }
}
