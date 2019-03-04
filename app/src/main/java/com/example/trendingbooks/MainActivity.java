package com.example.trendingbooks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements BooksListAdapter.BookSelector {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initializeUi();
    }

    private void initializeUi() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, BooksListFragment.newInstance())
                .commit();
    }

    @Override
    public void selectBook(Book book) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_container, BookDetailsFragment.newInstance(book))
                .commit();
    }
}