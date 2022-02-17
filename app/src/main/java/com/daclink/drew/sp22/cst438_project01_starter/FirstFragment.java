package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentFirstBinding;
import com.daclink.drew.sp22.cst438_project01_starter.utilities.constants;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private String mUsername;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // testing to see if accessing shared preferences inside a fragment from MainActivity works
        sharedPreferences = getActivity().getSharedPreferences(constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        mUsername = sharedPreferences.getString(constants.KEY_USERNAME, null);
        binding.textviewFirst.setText("Username: \n" + mUsername);

        // logout button
        binding.buttonLogout.setOnClickListener(view1 -> logout(view, sharedPreferences));

        binding.toSearch.setOnClickListener(view1 -> NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_SearchFragment));

        binding.favoritesButton.setOnClickListener(view1 -> NavHostFragment.findNavController(FirstFragment.this)
                .navigate(R.id.action_FirstFragment_to_ListFragment));
    }

    // logs out user and returns to login page.
    public void logout(View v, SharedPreferences sp) {
        if (sp.getInt(constants.USER_ID_KEY, -1) != -1) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(constants.USER_ID_KEY, -1);
            editor.apply();
        }
        Toast.makeText(v.getContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(v.getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}