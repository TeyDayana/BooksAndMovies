package com.example.booksmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BooksCustomAdapter extends RecyclerView.Adapter<BooksCustomAdapter.MyViewHolder> {
    Activity activity;
    Context context;
    ArrayList<String> ids, names, authors, dates, comments, ratings;

    BooksCustomAdapter(Activity activity, Context context, ArrayList<String> ids, ArrayList<String> names, ArrayList<String> authors,
                       ArrayList<String> dates, ArrayList<String> comments, ArrayList<String> ratings) {
        this.activity = activity;
        this.context = context;
        this.ids = ids;
        this.names = names;
        this.authors = authors;
        this.dates = dates;
        this.comments = comments;
        this.ratings = ratings;
    }

    @NonNull
    @Override
    public BooksCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.books_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksCustomAdapter.MyViewHolder holder, int position) {
        holder.name.setText(String.valueOf(names.get(position)));
        holder.date.setText(String.valueOf(dates.get(position)));
        holder.rating.setText(String.valueOf(ratings.get(position)));
        holder.layout.setOnClickListener(view -> {
            Intent intent = new Intent(context, BookActivity.class);
            intent.putExtra("id",  String.valueOf(ids.get(holder.getAdapterPosition())));
            intent.putExtra("name",  String.valueOf(names.get(holder.getAdapterPosition())));
            intent.putExtra("author",  String.valueOf(authors.get(holder.getAdapterPosition())));
            intent.putExtra("date",  String.valueOf(dates.get(holder.getAdapterPosition())));
            intent.putExtra("comment",  String.valueOf(comments.get(holder.getAdapterPosition())));
            intent.putExtra("rating",  String.valueOf(ratings.get(holder.getAdapterPosition())));
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, date, rating;
        LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.book_name);
            date = itemView.findViewById(R.id.book_date);
            rating = itemView.findViewById(R.id.book_rating);
            layout = itemView.findViewById(R.id.books_layout);
        }
    }
}
