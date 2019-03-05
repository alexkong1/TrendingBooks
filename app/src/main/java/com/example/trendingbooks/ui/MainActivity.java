package com.example.trendingbooks.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.trendingbooks.MainApplication;
import com.example.trendingbooks.R;
import com.example.trendingbooks.data.Book;
import com.example.trendingbooks.data.BooksDataHelper;

public class MainActivity extends AppCompatActivity implements BooksListAdapter.BookItemClicker {

    private boolean showFavorites = false;
    private String bookDetailsId;

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

        bookDetailsId = book.getId();
        setShowFavorites(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorites, menu);
        MenuItem favorite = menu.findItem(R.id.action_favorite);
        View favoriteView = favorite.getActionView();
        favorite.setVisible(showFavorites);
        favoriteView.setSelected(((MainApplication) getApplication()).getBooksHelper().isFavorited(bookDetailsId));
        favoriteView.setOnClickListener(v -> {
            favoriteView.setSelected(!favoriteView.isSelected());
            favoriteFromDetails(favoriteView.isSelected());
        });
        return true;
    }

    public void setShowFavorites(boolean showFavorites) {
        this.showFavorites = showFavorites;
        invalidateOptionsMenu();
    }

    @Override
    protected void onDestroy() {
        ((MainApplication) getApplication()).getBooksHelper().saveFavorites();
        super.onDestroy();
    }

    private void favoriteFromDetails(boolean isFavorite) {
        for (Fragment fragment: getSupportFragmentManager().getFragments()) {
            if (fragment instanceof BookDetailsFragment)
                ((BookDetailsFragment) fragment).updateFavorite(isFavorite);
        }
    }

    @Override
    public void favoriteBook(String bookId, boolean isFavorited) {
        getBooksHelper().updateFavorites(bookId, isFavorited);
    }

    @Override
    public boolean isFavorited(String bookId) {
        return getBooksHelper().isFavorited(bookId);
    }

    private BooksDataHelper getBooksHelper() {
        return ((MainApplication) getApplication()).getBooksHelper();
    }
}