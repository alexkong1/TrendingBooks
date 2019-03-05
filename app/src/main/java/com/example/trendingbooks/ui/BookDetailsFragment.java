package com.example.trendingbooks.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trendingbooks.MainApplication;
import com.example.trendingbooks.R;
import com.example.trendingbooks.data.Book;
import com.google.gson.Gson;


public class BookDetailsFragment extends Fragment {

    public static final String ARG_BOOK_DATA = "book_data";
    private Book book;

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
            book = new Gson().fromJson(getArguments().getString(ARG_BOOK_DATA), Book.class);

            ((TextView) root.findViewById(R.id.book_details_title)).setText(book.getTitle());

            String author;
            if (book.getAuthorLastname().contains(" and ")) {
                String[] lastNames = book.getAuthorLastname().split(" and ");
                String[] firstNames = book.getAuthorFirstname().split(" and ");
                StringBuilder authorBuilder = new StringBuilder();

                for (int i = 0; i < lastNames.length; i++) {
                    authorBuilder.append(firstNames[i]);
                    authorBuilder.append(" ");
                    authorBuilder.append(lastNames[i]);
                    if (i != lastNames.length - 1) authorBuilder.append(" and ");
                }
                author = authorBuilder.toString();
            } else author = book.getAuthorFirstname() + " " + book.getAuthorLastname();

            ((TextView) root.findViewById(R.id.book_details_author)).setText(
                    getContext().getString(R.string.author_by, author));

            if (book.getMarketingMessage() != null) {
                root.findViewById(R.id.book_details_marketing).setVisibility(View.VISIBLE);
                ((TextView) root.findViewById(R.id.book_details_marketing))
                        .setText(getContext().getString(R.string.marketing_quotes,
                                book.getMarketingMessage()));
            } else root.findViewById(R.id.book_details_marketing).setVisibility(View.GONE);
            ((TextView) root.findViewById(R.id.book_details_synopsis)).setText(book.getSynopsis());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getActivity() != null) ((MainActivity) getActivity()).setShowFavorites(false);
    }

    public void updateFavorite(boolean isFavorited) {
        if (getActivity() != null && book != null) {
            ((MainApplication) getActivity().getApplication())
                    .getBooksHelper()
                    .updateFavorites(book.getId(), isFavorited);
        }
    }
}
