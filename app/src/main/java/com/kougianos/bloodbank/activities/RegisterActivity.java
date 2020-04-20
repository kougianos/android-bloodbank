package com.kougianos.bloodbank.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kougianos.bloodbank.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEt, cityEt, mobileEt, bloodgroupEt, passwordEt;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameEt = findViewById(R.id.name);
        cityEt = findViewById(R.id.city);
        passwordEt = findViewById(R.id.password);
        bloodgroupEt = findViewById(R.id.blood_group);
        mobileEt = findViewById(R.id.mobile);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, city, blood_group, password, mobile;
                name = nameEt.getText().toString();
                city = cityEt.getText().toString();
                blood_group = bloodgroupEt.getText().toString();
                password = passwordEt.getText().toString();
                mobile = mobileEt.getText().toString();

                // Hide keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                showMessage(name+"\n"+city+"\n"+blood_group+"\n"+password+"\n"+mobile);
            }
        });

    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
