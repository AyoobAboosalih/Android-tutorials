package com.example.clickimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        String message = "Order: " +
                intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textview = findViewById(R.id.order_textview);
        textview.setText(message);

        // Create the spinner.
        Spinner spinner = findViewById(R.id.label_spinner);
        if (spinner != null){
            spinner.setOnItemSelectedListener(this);
        }

        // Create ArrayAdapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Apply the adapter to the spinner
        if (spinner!= null){
            spinner.setAdapter(adapter);
        }

    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.sameday:
                if (checked) {
                    displayToast(getString(R.string.same_day_messenger_service));
                }
                break;

            case R.id.nextday:
                if (checked) {
                    displayToast(getString(R.string.next_day_messenger_service));
                }
                break;

            case R.id.pickup:
                if (checked) {
                    displayToast(getString(R.string.pick_up));
                }
                break;

            default:
                break;

        }

    }

    public String spinnerLabel;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       spinnerLabel = parent.getItemAtPosition(position).toString();
       displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
