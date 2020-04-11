package com.example.phonenumberaction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.phone_num);
        if (editText != null){
            editText.setOnEditorActionListener(
                    new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            boolean handled = false;
                            if (actionId == EditorInfo.IME_ACTION_SEND){
                                dialNumber();
                                handled = true;
                            }
                            return handled;
                        }

                    }
            );
        }
    }

    private void dialNumber() {

        // Find the editText_main view.
        EditText editText = findViewById(R.id.phone_num);

        // If the editText field is not null,
        // concatenate "tel: " with the phone number string
        String phonenum = null;
        if (editText != null){
            phonenum = "tel: " + editText.getText().toString();
        }

        // Optional: Log the concatenated phone number for dialing.
        String TAG = "abc";
        Log.d(TAG, "dialnumber: " + phonenum);

        // Specify the intent.
        // Set the data for the intent as the phone number.
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(phonenum));


        // If the intent resolves to a package (app),
        // start the activity with the intent.
        if(intent.resolveActivity(getPackageManager())!= null){
            startActivity(intent);
        } else{
            Log.d("ImplicitIntents","Can't handle this");
        }
    }
}
