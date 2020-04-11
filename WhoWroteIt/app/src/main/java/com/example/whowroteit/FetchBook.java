package com.example.whowroteit;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchBook extends AsyncTask<String, Void, String> {

    private WeakReference<TextView> mTitleText;
    private WeakReference<TextView> mAuthorText;

    public FetchBook(TextView mTitleText, TextView mAuthorText) {
        this.mTitleText = new WeakReference<> (mTitleText);
        this.mAuthorText = new WeakReference<> (mAuthorText);
    }


    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            // Convert the response into a JSON object.
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");
            int i = 0;
            String title = null;
            String authors = null;

            // Look for results in the items array, exiting
            // when both the title and author
            // are found or when all items have been checked.
            while (i < itemsArray.length() &&
                    (authors == null && title == null)){
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeinfo = book.getJSONObject("volumeinfo");

                try{
                    title = volumeinfo.getString("title");
                    authors = volumeinfo.getString("authors");
                }catch (Exception e){
                    e.printStackTrace();
                }
                i++;
            }
            if (title != null && authors != null){
                mTitleText.get().setText(title);
                mAuthorText.get().setText(authors);
            }else {
                mTitleText.get().setText(R.string.no_results);
                mAuthorText.get().setText("");
            }
            // If onPostExecute does not receive a proper JSON string,
            // update the UI to show failed results.
        } catch (Exception e) {
            mTitleText.get().setText(R.string.no_results);
            mAuthorText.get().setText("");
            e.printStackTrace();
        }
    }
}
