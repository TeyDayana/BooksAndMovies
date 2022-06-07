package com.example.booksmovies;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout layout;
    String theme;
    Button booksBtn, moviesBtn;

    ActivityResultLauncher<Intent> themeSettingsResult = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    theme = intent.getStringExtra("theme");
                    if (theme.equals("sunny day")) {
                        layout.setBackgroundResource(R.drawable.sunnydaybackground);
                    } else {
                        layout.setBackgroundResource(R.drawable.warmeveningbackground);
                    }
                }
            });

    ActivityResultLauncher<Intent> themeAfterView = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(), result -> {
                if (theme.equals("sunny day")) {
                    layout.setBackgroundResource(R.drawable.sunnydaybackground);
                } else {
                    layout.setBackgroundResource(R.drawable.warmeveningbackground);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.layout_main);

        booksBtn = findViewById(R.id.books_btn);
        booksBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, BooksViewActivity.class);
            if (theme == null) {
                theme = "sunny day";
            }
            intent.putExtra("theme", theme);
            themeAfterView.launch(intent);
        });

        moviesBtn = findViewById(R.id.movies_btn);
        moviesBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MoviesViewActivity.class);
            if (theme == null) {
                theme = "sunny day";
            }
            intent.putExtra("theme", theme);
            themeAfterView.launch(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings_main_menu_item) {
            Intent intent = new Intent(this, SettingsMainActivity.class);
            if (theme == null) {
                theme = "sunny day";
            }
            intent.putExtra("prev_theme", theme);
            themeSettingsResult.launch(intent);
        }

        if (item.getItemId() == R.id.info_main_menu_item) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setMessage("Home Assignment\r\n\nПроект 'Books&Movies'\r\n\n" +
                    "База данных с возможностью записи просмотренных фильмов и прочитанных книг, " +
                    "даты прочтения/просмотра, своего рейтинга.\r\n\n" +
                    "Автор: Тей Даяна.\r\n\nГруппа: БПИ-208.");

            dialog.setNeutralButton("OK", (dialog1, which) -> dialog1.dismiss());
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}