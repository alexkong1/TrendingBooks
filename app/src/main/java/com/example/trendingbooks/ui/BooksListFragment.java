package com.example.trendingbooks.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trendingbooks.R;
import com.example.trendingbooks.data.Book;
import com.example.trendingbooks.data.BooksDataHelper;

import java.util.Collections;
import java.util.List;

public class BooksListFragment extends Fragment {

    private List<Book> books;

    public static BooksListFragment newInstance() {
        BooksListFragment fragment = new BooksListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_books_list, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUi(view);
    }

    private void initializeUi(View view) {
        books = BooksDataHelper.getBooks(getContext());

        RecyclerView recyclerView = view.findViewById(R.id.books_list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new BooksListAdapter(getContext(), books, ((MainActivity) getActivity())));

        TabLayout tabLayout = view.findViewById(R.id.books_list_sort);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 3:
                        Collections.sort(books, (o1, o2) ->
                                o1.getTitle().compareTo(o2.getTitle()));
                        break;
                    case 2:
                        Collections.sort(books, (o1, o2) -> {
                            if (o1.getAuthorLastname().contains(o2.getAuthorLastname()))
                                return o1.getTitle().compareTo(o2.getTitle());
                            else return o1.getAuthorLastname().compareTo(o2.getAuthorLastname());
                        });
                        break;
                    case 1:
                        Collections.sort(books, (o1, o2) -> {
                            if (o1.getMarketingMessage() == null && o2.getMarketingMessage() != null)
                                return 1;
                            else if (o2.getMarketingMessage() == null && o1.getMarketingMessage() != null)
                                return -1;
                            else return o1.getTitle().compareTo(o2.getTitle());
                        });
                        break;
                    case 0:
                    default:
                        books = BooksDataHelper.getBooks(getContext());
                        break;
                }
                if (recyclerView.getAdapter() != null)
                    ((BooksListAdapter) recyclerView.getAdapter()).updateBooks(books);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
