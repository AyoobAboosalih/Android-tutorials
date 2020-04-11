package com.example.whowroteit;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;


public class BookLoader extends AsyncTaskLoader<String> {

    private String mQueryString;

    public BookLoader(@NonNull Context context, String query) {
        super(context);
        mQueryString = query;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
