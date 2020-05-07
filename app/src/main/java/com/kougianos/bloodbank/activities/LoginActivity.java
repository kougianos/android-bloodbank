package com.kougianos.bloodbank.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kougianos.bloodbank.R;
import com.kougianos.bloodbank.utils.Endpoints;
import com.kougianos.bloodbank.utils.VolleySingleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private EditText mobileEt;
    private EditText passwordEt;
    private Button submitButton;
    private TextView signUp;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobileEt = findViewById(R.id.mobile);
        passwordEt = findViewById(R.id.password);
        submitButton = findViewById(R.id.submit_button);
        signUp = findViewById(R.id.sign_up);
        progressBar = findViewById(R.id.progressBar_cyclic);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mobileEt.setError(null);
                passwordEt.setError(null);
                String mobile = mobileEt.getText().toString();
                String password = passwordEt.getText().toString();

                if (isValid(mobile, password)) {
                    progressBar.setVisibility(View.VISIBLE);
                    login(mobile, password);
                }

            }
        });

    }

    private void login(final String mobile, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.loginUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (!response.equals("Invalid credentials")) {
                            Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
                                    .putString("number", mobile).apply();
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
                                    .putString("city", response).apply();
                            LoginActivity.this.finish();
                        } else {
                            Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", Objects.requireNonNull(error.getMessage()));

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("password", password);
                params.put("number", mobile);

                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private boolean isValid(String number, String password) {

        if (number.isEmpty()) {
            showMessage("Empty Mobile Number");
            mobileEt.setError("Empty Mobile Number");
            mobileEt.requestFocus();
            return false;
        } else if (password.isEmpty()) {
            showMessage("Empty Password");
            passwordEt.setError("Empty Password");
            passwordEt.requestFocus();
            return false;
        }

        return true;
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
