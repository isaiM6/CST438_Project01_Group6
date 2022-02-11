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

        AppDatabase db = AppDatabase.getInstance(getApplicationContext());

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserEntity user = getValuesFromDisplay();

                db.userDao().insertUser(user);
                Toast.makeText(getApplicationContext(), "Created account successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private UserEntity getValuesFromDisplay() {
        mUsername = mUsernameField.getText().toString();
        mPassword = mPasswordField.getText().toString();
        mPassword = mPasswordField.getText().toString();
        mPassword2 = mPassword2Field.getText().toString();
        mName = mNameField.getText().toString();

        if (!validatePasswords()) {
            Toast.makeText(CreateAccountActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();

        } else if(mUsername.equals(null)){
            Toast.makeText(CreateAccountActivity.this, "Username field cannot be empty.", Toast.LENGTH_SHORT).show();
        }

        UserEntity user = new UserEntity(mUsername,mPassword,mName);
        return user;
    }

    public boolean validatePasswords() {
        if(mPassword.equals(mPassword2)){
            return true;}
        else{
            return false;}
    }
}