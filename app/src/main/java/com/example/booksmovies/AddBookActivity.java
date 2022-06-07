package com.example.booksmovies;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class AddBookActivity extends AppCompatActivity {
    EditText nameEditText, authorEditText, dateEditText, commentEditText;
    RatingBar ratingBar;
    FloatingActionButton add_btn;
    Button todayDateBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book_activity);
        nameEditText = findViewById(R.id.name_book_edit_text);
        authorEditText = findViewById(R.id.author_book_edit_text);
        dateEditText = findViewById(R.id.date_book_edit_text);
        commentEditText = findViewById(R.id.comment_book_edit_text);
        ratingBar = findViewById(R.id.ratingBar_book);

        todayDateBtn = findViewById(R.id.today_date_book_btn);
        todayDateBtn.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            dateEditText.setText(day + "." + ++month + "." + year);
        });

        add_btn = findViewById(R.id.add_book_final_btn);
        add_btn.setOnClickListener(view -> {
            DataBaseHelper helper = new DataBaseHelper(AddBookActivity.this, "books", null, 1);
            helper.add(nameEditText.getText().toString().trim(),
                    authorEditText.getText().toString().trim(),
                    dateEditText.getText().toString().trim(),
                    commentEditText.getText().toString().trim(),
                    String.valueOf(ratingBar.getRating()).trim());
            finish();
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Add book");
        }
    }
}
