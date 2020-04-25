package com.kougianos.bloodbank.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kougianos.bloodbank.R;
import com.kougianos.bloodbank.Utils.Endpoints;
import com.kougianos.bloodbank.Utils.VolleySingleton;
import com.kougianos.bloodbank.models.BloodGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEt, cityEt, mobileEt, bloodgroupEt, passwordEt;
    private Button submitButton;
    private ScrollView scrollView;
    private BloodGroup bloodGroup = new BloodGroup();

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

                if (isValid(name, city, bloodgroup, password, mobile)) {
                    register(name, city, bloodgroup, password, mobile);
                }

            }
        });

    }

    private void register(final String name, final String city, final String blood_group, final String password, final String mobile) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.registerUrl,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("Success")) {
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    RegisterActivity.this.finish();
                } else {
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", Objects.requireNonNull(error.getMessage()));

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("city", city);
                params.put("blood_group", blood_group);
                params.put("password", password);
                params.put("number", mobile);

                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private boolean isValid(String name, String city, String blood_group, String password, String mobile) {

        if (name.isEmpty()) {
            showMessage("Name is empty");
            return false;
        } else if (city.isEmpty()) {
            showMessage("City is empty");
            return false;
        } else if (!bloodGroup.getBloodGroup().contains(blood_group)) {
            showMessage("Invalid blood group, should be one of the following:" + bloodGroup.getBloodGroup().toString());
            return false;
        } else if (mobile.length() != 10) {
            showMessage("Mobile number should be 10 digits");
            return false;
        }

        return true;
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
