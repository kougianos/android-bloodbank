package com.kougianos.bloodbank.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.kougianos.bloodbank.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEt, cityEt, mobileEt, bloodgroupEt, passwordEt;
    private Button submitButton;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        scrollView = findViewById(R.id.scroll_view);
        nameEt = findViewById(R.id.name);
        cityEt = findViewById(R.id.city);
        passwordEt = findViewById(R.id.password);
        bloodgroupEt = findViewById(R.id.blood_group);
        mobileEt = findViewById(R.id.mobile);
        submitButton = findViewById(R.id.submit_button);

        passwordEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    View lastChild = scrollView.getChildAt(scrollView.getChildCount() - 1);
                    int bottom = lastChild.getBottom() + scrollView.getPaddingBottom();
                    int sy = scrollView.getScrollY();
                    int sh = scrollView.getHeight();
                    int delta = bottom - (sy + sh);
                    scrollView.smoothScrollBy(0, delta);
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, city, bloodgroup, password, mobile;
                name = nameEt.getText().toString();
                city = cityEt.getText().toString();
                bloodgroup = bloodgroupEt.getText().toString();
                password = passwordEt.getText().toString();
                mobile = mobileEt.getText().toString();

                // Hide keyboard
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (NullPointerException e) {
                    // Do nothing
                }

                showMessage(name+"\n"+city+"\n"+bloodgroup+"\n"+password+"\n"+mobile);
            }
        });

    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
