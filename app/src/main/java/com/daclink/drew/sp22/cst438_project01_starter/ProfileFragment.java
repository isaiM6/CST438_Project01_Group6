/**
 * Author: Jose Barroso A.
 * Last Modified: 02/17/2022
 * Abstract: Fragment for user profile action with access to their list and other features
 */
package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentProfileBinding;
import com.daclink.drew.sp22.cst438_project01_starter.db.AppDatabase;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserDao;
import com.daclink.drew.sp22.cst438_project01_starter.db.UserEntity;
import com.daclink.drew.sp22.cst438_project01_starter.utilities.constants;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding mBinding;

    private int mUserId;

    private String mFullName;
    private String mUsername;
    private String mPassword;

    private TextView mFullNameTextView;
    private TextView mUserNameTextView;

    private EditText mFullNameEditText;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;

    private Button mMyListBtn;
    private Button mSearchHistoryBtn;
    private Button mChangePasswordBtn;
    private Button mLogoutBtn;

    private UserEntity mUser;
    private UserDao mUserDao;

    SharedPreferences mPrefs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentProfileBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // get user's shared preferences
        mPrefs = getActivity().getSharedPreferences(constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mUserId = mPrefs.getInt(constants.USER_ID_KEY, -1);

        // initialize user and user DAO
        mUserDao = getDatabase();
        mUser = mUserDao.getUserById(mUserId);

        // set up the UI
        wireUpDisplay(view);
        setDisplayValues();
        setOnClickListeners(view);
    }

    // get instance of database and return user DAO
    private UserDao getDatabase() {
        AppDatabase db = AppDatabase.getInstance(getContext().getApplicationContext());
        return db.userDao();
    }

    // wires up UI elements
    private void wireUpDisplay(View view) {
        mFullNameTextView = view.findViewById(R.id.profile_full_name_textview);
        mUserNameTextView = view.findViewById(R.id.profile_username_textview);

        mFullNameEditText = view.findViewById(R.id.profile_full_name_edittext);
        mUsernameEditText = view.findViewById(R.id.profile_username_edittext);
        mPasswordEditText = view.findViewById(R.id.profile_password_edittext);

        mMyListBtn = view.findViewById(R.id.favorites);
        mSearchHistoryBtn = view.findViewById(R.id.searchHistory);
        mChangePasswordBtn = view.findViewById(R.id.changePW);
        mLogoutBtn = view.findViewById(R.id.logoutBtn);
    }

    // replaces text views with user info
    private void setDisplayValues() {
        mFullName = mUser.getName();
        mUsername = mUser.getUsername();
        mPassword = mUser.getPassword();

        mFullNameTextView.setText(mFullName);
        mUserNameTextView.setText(mUsername);

        mFullNameEditText.setText(mFullName);
        mUsernameEditText.setText(mUsername);
        mPasswordEditText.setText(mPassword);
    }

    // sets button on click listeners
    private void setOnClickListeners(View view) {
        // switches from profile fragment to list fragment
        mMyListBtn.setOnClickListener(v -> NavHostFragment.findNavController(this)
                .navigate(R.id.action_ProfileFragment_to_ListFragment));

        // user log out
        mLogoutBtn.setOnClickListener(v -> logout(view));
    }

    // user log out
    private void logout(View view) {
        if (mPrefs.getInt(constants.USER_ID_KEY, -1) != -1) {
            // clear user from shared preferences
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putInt(constants.USER_ID_KEY, -1);
            editor.apply();
        }
        Toast.makeText(view.getContext(), "Logout Successful", Toast.LENGTH_SHORT).show();

        // go back to login screen
        Intent intent = new Intent(view.getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
