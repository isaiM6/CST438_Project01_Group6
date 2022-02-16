package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;

public class LoginActivity extends AppCompatActivity{

    private String mUsername;
    private String mPassword;

    private EditText mUsernameField;
    private EditText mPasswordField;

    private Button mLoginBtn;
    private Button mCreateAccBtn;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "MyPrefs";
    private static final String KEY_USERNAME = "username";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameField = findViewById(R.id.enterUsername_edittext);
        mPasswordField = findViewById(R.id.enterPassword_edittext);
        mLoginBtn = findViewById(R.id.button_login);
        mCreateAccBtn = findViewById(R.id.button_register);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsername = mUsernameField.getText().toString();
                mPassword = mPasswordField.getText().toString();

                if(db.userDao().userExists(mUsername)){
                    if(db.userDao().getUserByUsername(mUsername).getPassword().equals(mPassword)){
                        Toast.makeText(getApplicationContext(), "Login Successful.", Toast.LENGTH_SHORT).show();
                        //SharedPreferences.Editor editor = sharedPreferences.edit();
                        //editor.putString(KEY_USERNAME,mUsername);
                        //editor.apply();

                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);


                        //openNewActivity();

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Login Unsuccessful.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Account not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCreateAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateAccountActivity.class);
                startActivity(intent);
            }
        });


    }


}