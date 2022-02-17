package com.daclink.drew.sp22.cst438_project01_starter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;

/*
 * Class: CreateAccountActivity.java
 * Description: Creates bindings and interactable
 * aspects for creating an account on the app.
 * */

public class CreateAccountActivity extends AppCompatActivity {

    private String mUsername;
    private String mPassword;
    private String mPassword2;
    private String mName;

    private EditText mUsernameField;
    private EditText mPasswordField;
    private EditText mPassword2Field;
    private EditText mNameField;

    private Button mCreateBtn;
    private Button mCancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        wireUpDisplay();
    }

    private void wireUpDisplay() {
        mUsernameField = findViewById(R.id.createUsername_edittext);
        mPasswordField = findViewById(R.id.createPassword_edittext);
        mPassword2Field = findViewById(R.id.retypePassword_edittext);
        mNameField = findViewById(R.id.enterName_edittext);

        mCreateBtn = findViewById(R.id.button_create);
        mCancelBtn = findViewById(R.id.button_cancel);

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsername = mUsernameField.getText().toString();
                mPassword = mPasswordField.getText().toString();
                mPassword = mPasswordField.getText().toString();
                mPassword2 = mPassword2Field.getText().toString();
                mName = mNameField.getText().toString();

                if (!validatePasswords()) {
                    Toast.makeText(CreateAccountActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();

                }else if(mUsername.equals("")){
                    Toast.makeText(CreateAccountActivity.this, "Username field cannot be empty.", Toast.LENGTH_SHORT).show();
                }else if(db.userDao().getUserByUsername(mUsername) != null){
                    Toast.makeText(CreateAccountActivity.this, "Username already exists.", Toast.LENGTH_SHORT).show();
                }
                else{
                    UserEntity user = new UserEntity(mUsername,mPassword,mName);
                    db.userDao().insertUser(user);
                    Toast.makeText(getApplicationContext(), "Created account successfully", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(v.getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
                mUsername = mUsernameField.getText().toString();
            }
        });


    }

    public boolean validatePasswords() {
        if(mPassword.equals(mPassword2)){
            return true;}
        else{
            return false;}
    }
}