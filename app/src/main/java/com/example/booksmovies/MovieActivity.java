package com.example.booksmovies;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MovieActivity extends AppCompatActivity {
    EditText nameEditText, dateEditText, commentEditText;
    RatingBar ratingBar;
    Button saveChangesBtn, deleteBtn, todayDateBtn;
    String id, name, date, comment, rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_activity);
        nameEditText = findViewById(R.id.name_movie_edit_text_final);
        dateEditText = findViewById(R.id.date_movie_edit_text_final);
        commentEditText = findViewById(R.id.comment_movie_edit_text_final);
        ratingBar = findViewById(R.id.ratingBar_movie_final);
        saveChangesBtn = findViewById(R.id.save_changes_movie_btn);
        deleteBtn = findViewById(R.id.delete_movie_btn);
        todayDateBtn = findViewById(R.id.today_date_movie_btn_final);
        setData();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(name);
        }

        saveChangesBtn.setOnClickListener(view -> {
            DataBaseHelper helper = new DataBaseHelper(MovieActivity.this, "movies", null, 1);
            name = nameEditText.getText().toString().trim();
            date = dateEditText.getText().toString().trim();
            comment = commentEditText.getText().toString().trim();
            rating = String.valueOf(ratingBar.getRating()).trim();
            helper.update(id, name, "", date, comment, rating);
            finish();
        });

        deleteBtn.setOnClickListener(view -> {
            DataBaseHelper helper = new DataBaseHelper(MovieActivity.this, "movies", null, 1);
            helper.delete(id);
            finish();
        });

        todayDateBtn.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            dateEditText.setText(day + "." + ++month + "." + year);
        });
    }

    void setData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("date")
                && getIntent().hasExtra("comment") && getIntent().hasExtra("rating")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            date = getIntent().getStringExtra("date");
            comment = getIntent().getStringExtra("comment");
            rating = getIntent().getStringExtra("rating");

            nameEditText.setText(name);
            dateEditText.setText(date);
            commentEditText.setText(comment);
            ratingBar.setRating(Float.parseFloat(rating));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }
}
