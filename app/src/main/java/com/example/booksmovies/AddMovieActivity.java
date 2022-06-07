package com.example.booksmovies;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class AddMovieActivity extends AppCompatActivity {
    EditText nameEditText, dateEditText, commentEditText;
    RatingBar ratingBar;
    FloatingActionButton add_btn;
    Button todayDateBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_movie_activity);
        nameEditText = findViewById(R.id.name_movie_edit_text);
        dateEditText = findViewById(R.id.date_movie_edit_text);
        commentEditText = findViewById(R.id.comment_movie_edit_text);
        ratingBar = findViewById(R.id.ratingBar_movie);

        todayDateBtn = findViewById(R.id.today_date_movie_btn);
        todayDateBtn.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            dateEditText.setText(day + "." + ++month + "." + year);
        });

        add_btn = findViewById(R.id.add_movie_final_btn);
        add_btn.setOnClickListener(view -> {
            DataBaseHelper helper = new DataBaseHelper(AddMovieActivity.this, "movies", null, 1);
            helper.add(nameEditText.getText().toString().trim(), "",
                    dateEditText.getText().toString().trim(),
                    commentEditText.getText().toString().trim(),
                    String.valueOf(ratingBar.getRating()).trim());
            finish();
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Add movie");
        }
    }
}
