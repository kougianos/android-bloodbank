package com.kougianos.bloodbank.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kougianos.bloodbank.R;
import com.kougianos.bloodbank.models.RequestDataModel;
import com.kougianos.bloodbank.utils.BloodGroup;
import com.kougianos.bloodbank.utils.Endpoints;
import com.kougianos.bloodbank.utils.VolleySingleton;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final EditText etBloodGroup = findViewById(R.id.et_blood_group);
        final EditText etCity = findViewById(R.id.et_city);
        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bloodGroup = etBloodGroup.getText().toString();
                String city = etCity.getText().toString();
                if (isValid(bloodGroup, city)) {
                    getSearchResults(bloodGroup, city);
                }
            }
        });

    }

    private void getSearchResults(final String bloodGroup, final String city) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.searchDonors,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(SearchActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", Objects.requireNonNull(error.getMessage()));

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("city", city);
                params.put("bloodGroup", bloodGroup);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private boolean isValid(String blood_group, String city) {
        if (!BloodGroup.getBloodGroups().contains(blood_group.toUpperCase())) {
            showMsg("Blood group invalid, choose from " + BloodGroup.getBloodGroups());
            return false;
        } else if (city.isEmpty()) {
            showMsg("Enter city");
            return false;
        }
        return true;
    }

    private void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
