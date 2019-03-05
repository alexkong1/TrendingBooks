package com.example.trendingbooks;

import android.app.Application;

import com.example.trendingbooks.data.BooksDataHelper;

public class MainApplication extends Application {

    private BooksDataHelper booksHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        booksHelper = new BooksDataHelper(getApplicationContext());
    }

    public BooksDataHelper getBooksHelper() {
        return booksHelper;
    }
}
