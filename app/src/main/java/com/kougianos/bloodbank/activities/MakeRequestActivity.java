package com.kougianos.bloodbank.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kougianos.bloodbank.R;

public class MakeRequestActivity extends AppCompatActivity {

    EditText messageText;
    TextView chooseImageText;
    ImageView postImage;
    Button submitButton;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_request);

        messageText = findViewById(R.id.message);
        chooseImageText = findViewById(R.id.choose_image);
        postImage = findViewById(R.id.post_image);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValid()) {
                    uploadRequest(messageText.getText().toString());
                }

            }
        });

        // Choose image
        chooseImageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 101);

            }
        });
    }

    private void uploadRequest(String message) {
        //code to upload message
    }

    private void uploadImage() {
        //code to upload image
    }

    // Handle image with specific request code
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            if (data != null) {
                imageUri = data.getData();
                // Set image uri with Glide
                Glide.with(getApplicationContext()).load(imageUri).into(postImage);
            }
        }
    }

    private boolean isValid() {
        if (messageText.getText().toString().isEmpty()) {
            showMessage("Your message is empty, please write something");
            return false;
        }

        return true;
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
