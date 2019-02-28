package com.example.trendingbooks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class BooksListAdapter extends RecyclerView.Adapter<BooksListAdapter.ViewHolder> {

    private Context context;
    private List<Book> books = new ArrayList<>();

    public BooksListAdapter(Context context, List<Book> books) {
        super();
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public BooksListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Book book = books.get(viewHolder.getAdapterPosition());

        viewHolder.title.setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.book_title);
        }
    }
}
