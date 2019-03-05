package com.example.trendingbooks.data;

import android.content.Context;
import android.util.Log;

import com.example.trendingbooks.data.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BooksDataHelper {

    private Context context;
    private HashSet<String> favorites = new HashSet<>();
    private final static String FAVORITES_FILE = "favorites.set";

    public BooksDataHelper(Context context) {
        this.context = context;
        loadFavorites();
    }

    public static List<Book> getBooks(Context context, int setNumber) {

        String path;
        String json = null;
        List<Book> result = new ArrayList<>();

        switch (setNumber) {
            case 3:
                path = "trending-books-3.json";
                break;
            case 2:
                path = "trending-books-2.json";
                break;
            case 1:
            default:
                path = "trending-books-1.json";
                break;
        }

        try {
            InputStream is = context.getAssets().open(path);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            Log.e("LOADING BOOKS", e.getMessage());
        }

        if (json != null) result = new Gson().fromJson(json, new TypeToken<List<Book>>() {
        }.getType());

        return result;
    }

    public void loadFavorites() {
        File storedhashset = new File(context.getFilesDir(), FAVORITES_FILE);
        FileInputStream inputStream;
        try {
            inputStream = context.openFileInput(FAVORITES_FILE);
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            favorites = (HashSet<String>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (favorites == null) favorites = new HashSet<>();
    }

    public void saveFavorites() {
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(FAVORITES_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(favorites);
            oos.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isFavorited(String bookId) {
        if (favorites != null) return favorites.contains(bookId);
        return false;
    }

    public void updateFavorites(String bookId, boolean addToFaves) {
        if (addToFaves) favorites.add(bookId);
        else favorites.remove(bookId);
        saveFavorites();
    }
}
