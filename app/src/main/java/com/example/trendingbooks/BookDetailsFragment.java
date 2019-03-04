package com.example.trendingbooks;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;


public class BookDetailsFragment extends Fragment {

    public static final String ARG_BOOK_DATA = "book_data";

    public static BookDetailsFragment newInstance(Book book) {
        BookDetailsFragment frag = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_BOOK_DATA, new Gson().toJson(book));
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_book_details, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUi(view);
    }

    private void initializeUi(View root) {
        if (getArguments() != null) {
            Book book = new Gson().fromJson(getArguments().getString(ARG_BOOK_DATA), Book.class);

            ((TextView) root.findViewById(R.id.book_details_title)).setText(book.getTitle());
            ((TextView) root.findViewById(R.id.book_details_author)).setText(book.getAuthorLastname());
            if (book.getMarketingMessage() != null)
                ((TextView) root.findViewById(R.id.book_details_marketing)).setText(book.getTitle());
            else root.findViewById(R.id.book_details_marketing).setVisibility(View.GONE);
            ((TextView) root.findViewById(R.id.book_details_synopsis)).setText(book.getSynopsis());
        }
    }
}
