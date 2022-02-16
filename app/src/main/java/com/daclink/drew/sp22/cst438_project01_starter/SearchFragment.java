package com.daclink.drew.sp22.cst438_project01_starter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daclink.drew.sp22.cst438_project01_starter.adapters.SearchResultsAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentFirstBinding;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentSearchBinding;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentSecondBinding;
import com.daclink.drew.sp22.cst438_project01_starter.models.APIValues;
import com.daclink.drew.sp22.cst438_project01_starter.models.Search;
import com.daclink.drew.sp22.cst438_project01_starter.viewModels.SearchViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private @NonNull FragmentSearchBinding binding;
    private SearchViewModel viewModel;
    private SearchResultsAdapter adapter;

    private TextInputEditText keywordEditText, authorEditText;
    private Button searchButton, saveButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        //View view = inflater.inflate(R.layout.fragment_search, container, false);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        // int iter = 0;
        super.onViewCreated(view, savedInstanceState);

        adapter = new SearchResultsAdapter(getContext());

        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        viewModel.init();
        viewModel.getVolumesResponseLiveData().observe(getViewLifecycleOwner(), new Observer<APIValues>() {
            @Override
            public void onChanged(APIValues response) {
                if (response != null) {
                    Toast.makeText(getContext(), "hello " + response, Toast.LENGTH_SHORT).show();

                    adapter.setResults(response.getSearch());
                }
            }
        });
//        this.binding.fragmentSearchToFirstFragment.setOnClickListener(view1 -> NavHostFragment.findNavController(SearchFragment.this)
//                .navigate(R.id.action_SearchFragment_to_FirstFragment));

        RecyclerView recyclerView = view.findViewById(R.id.fragment_search_searchResultsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        keywordEditText = view.findViewById(R.id.fragment_search_keyword);
        searchButton = view.findViewById(R.id.fragment_search);
        saveButton = view.findViewById(R.id.fragment_search);

        searchButton.setOnClickListener(view3 -> performSearch());
        // commented out for future fixing
//        saveButton.setId(iter+1);
//        saveButton.setOnClickListener(view3 -> saveMovie());
    }

    // this is supposed to save the movie when the save button is clicked
    public void saveMovie(){
        List<Search> moviesList = viewModel.getVolumesResponseLiveData().getValue().getSearch();
        moviesList.get(saveButton.getId());

        // save movie clicked to the user's section in the db
    }

    public void performSearch() {
        String title = keywordEditText.getEditableText().toString();

        viewModel.searchVolumes(title);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}