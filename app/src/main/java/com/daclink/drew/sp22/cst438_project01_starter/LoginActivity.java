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
import com.daclink.drew.sp22.cst438_project01_starter.util.SampleUsers;
import com.daclink.drew.sp22.cst438_project01_starter.utilities.constants;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity{
    private int mUserId;

    private String mUsername;
    private String mPassword;

    private EditText mUsernameField;
    private EditText mPasswordField;

    private Button mLoginBtn;
    private Button mCreateAccBtn;

    private AppDatabase mDb;

    private UserEntity mUser;

    SharedPreferences mSharedPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameField = findViewById(R.id.enterUsername_edittext);
        mPasswordField = findViewById(R.id.enterPassword_edittext);
        mLoginBtn = findViewById(R.id.button_login);
        mCreateAccBtn = findViewById(R.id.button_register);

        mSharedPrefs = getSharedPreferences(constants.SHARED_PREF_NAME, MODE_PRIVATE);

        mDb = AppDatabase.getInstance(getApplicationContext());

        sampleUsers();
        automaticLogin();

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsername = mUsernameField.getText().toString();
                mPassword = mPasswordField.getText().toString();

                if (mDb.userDao().userExists(mUsername)) {
                    mUser = mDb.userDao().getUserByUsername(mUsername);
                    mUserId = mUser.getUserId();
                    if (mUser.getPassword().equals(mPassword)) {
                        Toast.makeText(getApplicationContext(), "Login Successful.", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = mSharedPrefs.edit();
                        editor.putInt(constants.USER_ID_KEY, mUserId);
                        editor.apply();

                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getApplicationContext(), "Login Unsuccessful.", Toast.LENGTH_SHORT).show();
                    }
                } else {
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

    private void sampleUsers() {
        if (mDb.userDao().getAllUsers().size() == 0) {
            Executor executor = Executors.newSingleThreadExecutor();

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    mDb.userDao().insertUser(SampleUsers.getUsers().get(0),
                                            SampleUsers.getUsers().get(1));
                }
            });
        }
    }

    private void automaticLogin() {
        if (mSharedPrefs.getInt(constants.USER_ID_KEY, -1) != -1) {
            Intent intent = MainActivity.newIntent(getApplicationContext());
            startActivity(intent);
        }
    }
}