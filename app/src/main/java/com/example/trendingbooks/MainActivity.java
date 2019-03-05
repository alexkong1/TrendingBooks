package com.example.trendingbooks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements BooksListAdapter.BookSelector {

    private boolean showFavorites = false;

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

        setShowFavorites(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorites, menu);
        MenuItem favorite = menu.findItem(R.id.action_favorite);
        View favoriteView = favorite.getActionView();
        favorite.setVisible(showFavorites);
        favoriteView.setOnClickListener(v -> {
            favoriteView.setSelected(!favoriteView.isSelected());
        });
        return true;
    }

    public void setShowFavorites(boolean showFavorites) {
        this.showFavorites = showFavorites;
        invalidateOptionsMenu();
    }
}