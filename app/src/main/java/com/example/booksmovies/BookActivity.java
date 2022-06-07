package com.example.booksmovies;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookActivity extends AppCompatActivity {
    EditText nameEditText, authorEditText, dateEditText, commentEditText;
    RatingBar ratingBar;
    Button saveChangesBtn, deleteBtn, todayDateBtn;
    String id, name, author, date, comment, rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);
        nameEditText = findViewById(R.id.name_book_edit_text_final);
        authorEditText = findViewById(R.id.author_book_edit_text_final);
        dateEditText = findViewById(R.id.date_book_edit_text_final);
        commentEditText = findViewById(R.id.comment_book_edit_text_final);
        ratingBar = findViewById(R.id.ratingBar_book_final);
        saveChangesBtn = findViewById(R.id.save_changes_book_btn);
        deleteBtn = findViewById(R.id.delete_book_btn);
        todayDateBtn = findViewById(R.id.today_date_book_btn_final);
        setData();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(name);
        }

        saveChangesBtn.setOnClickListener(view -> {
            DataBaseHelper helper = new DataBaseHelper(BookActivity.this, "books", null, 1);
            name = nameEditText.getText().toString().trim();
            author = authorEditText.getText().toString().trim();
            date = dateEditText.getText().toString().trim();
            comment = commentEditText.getText().toString().trim();
            rating = String.valueOf(ratingBar.getRating()).trim();
            helper.update(id, name, author, date, comment, rating);
            finish();
        });

        deleteBtn.setOnClickListener(view -> {
            DataBaseHelper helper = new DataBaseHelper(BookActivity.this, "books", null, 1);
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
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("author") && getIntent().hasExtra("date")
            && getIntent().hasExtra("comment") && getIntent().hasExtra("rating")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            author = getIntent().getStringExtra("author");
            date = getIntent().getStringExtra("date");
            comment = getIntent().getStringExtra("comment");
            rating = getIntent().getStringExtra("rating");

            nameEditText.setText(name);
            authorEditText.setText(author);
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
