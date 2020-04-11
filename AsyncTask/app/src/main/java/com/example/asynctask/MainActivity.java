package com.example.asynctask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private TextView pTextView;
    private static final String Text_STATE = "currentText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textview1);
        pTextView = findViewById(R.id.sleeptextview);

        // Setting the saved instance of the text
        if(savedInstanceState != null){
            mTextView.setText(savedInstanceState.getString(Text_STATE));
        }
    }

    public void startTask(View view) {
        mTextView.setText(R.string.napping);

        //execute method you pass parameters if specified to be received
        // in doBackground()
        new SimpleAsyncTask(mTextView,pTextView).execute();
    }

    @Override
    protected void onSaveInstanceState( Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save state of textview
        outState.putString(Text_STATE, mTextView.getText().toString());
    }
}
