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


public class BooksListAdapter extends RecyclerView.Adapter<BooksListAdapter.BooksViewHolder> {

    private Context context;
    private List<Book> books = new ArrayList<>();

    public BooksListAdapter(Context context, List<Book> books) {
        super();
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, viewGroup, false);
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder viewHolder, int i) {
        Book book = books.get(viewHolder.getAdapterPosition());

        viewHolder.title.setText(book.getTitle());
        viewHolder.author.setText(context.getString(R.string.author_by, book.getAuthorLastname()));
        if (book.getMarketingMessage() != null) {
            viewHolder.marketing.setVisibility(View.VISIBLE);
            viewHolder.marketing.setText(context.getString(R.string.marketing_quotes, book.getMarketingMessage()));
        } else viewHolder.marketing.setVisibility(View.GONE);
        viewHolder.synopsis.setText(book.getSynopsis());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class BooksViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView author;
        TextView marketing;
        TextView synopsis;

        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.book_title);
            author = itemView.findViewById(R.id.book_author);
            marketing = itemView.findViewById(R.id.book_marketing);
            synopsis = itemView.findViewById(R.id.book_synopsis);
        }
    }
}
