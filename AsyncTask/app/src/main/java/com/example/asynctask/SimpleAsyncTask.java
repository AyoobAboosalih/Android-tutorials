package com.example.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void , Integer, String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<TextView> pTextView;

    Random r = new Random();
    int n = r.nextInt(11);
    int s = n * 200;

    public SimpleAsyncTask(TextView tv, TextView pv) {
        mTextView = new WeakReference<>(tv);
        pTextView = new WeakReference<>(pv);
    }

    @Override
    protected String doInBackground(Void... voids) {
        publishProgress(s);
        try{
            Thread.sleep(s);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        return "Awake at last after sleeping for " + s + " miliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        pTextView.get().setText("Sleep time is " + s + " milliseconds");
    }

    protected void onPostExecute(String result){
        mTextView.get().setText(result);
    }
}
