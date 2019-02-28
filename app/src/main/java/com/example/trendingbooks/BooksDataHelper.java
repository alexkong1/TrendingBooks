package com.example.trendingbooks;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BooksDataHelper {

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

        if (json != null) result = new Gson().fromJson(json, new TypeToken<List<Book>>(){}.getType());

        return result;
    }
}
