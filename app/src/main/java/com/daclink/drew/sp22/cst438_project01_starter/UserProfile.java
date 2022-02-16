package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.auth.User;

public class UserProfile extends AppCompatActivity {
    Button logoutBtn;
    Button changePW;
    Button favoritesBtn;
    Button historyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        logoutBtn = (Button)findViewById(R.id.logoutBtn);
        changePW = (Button)findViewById(R.id.changePW);
        favoritesBtn = (Button)findViewById(R.id.favorites);
        historyBtn = (Button)findViewById(R.id.searchHistory);


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(UserProfile.this, MainActivity.class);
                startActivity(new Intent(UserProfile.this, MainActivity.class));
            }
        });

        changePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserProfile.this, ChangePasswordActivity.class));
            }
        });

        //NEED TO ADD NEW ACTIVITIES TO GO TO WHEN BUTTON IS CLICKED
        //

        favoritesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(UserProfile.this, .class));
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(UserProfile.this, .class));
            }
        });
    }
}