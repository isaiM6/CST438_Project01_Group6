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

        mPrefs = getActivity().getSharedPreferences(constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mUserId = mPrefs.getInt(constants.USER_ID_KEY, -1);

        mUserDao = getDatabase();
        mUser = mUserDao.getUserById(mUserId);

        wireUpDisplay(view);
        setDisplayValues();
        setOnClickListeners(view);
    }

    private UserDao getDatabase() {
        AppDatabase db = AppDatabase.getInstance(getContext().getApplicationContext());
        return db.userDao();
    }

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

    private void setOnClickListeners(View view) {
        mMyListBtn.setOnClickListener(v -> NavHostFragment.findNavController(this)
                .navigate(R.id.action_ProfileFragment_to_ListFragment));

        mLogoutBtn.setOnClickListener(v -> logout(view));
    }

    private void logout(View view) {
        if (mPrefs.getInt(constants.USER_ID_KEY, -1) != -1) {
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putInt(constants.USER_ID_KEY, -1);
            editor.apply();
        }
        Toast.makeText(view.getContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(view.getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}
