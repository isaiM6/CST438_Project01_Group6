package com.daclink.drew.sp22.cst438_project01_starter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daclink.drew.sp22.cst438_project01_starter.adapters.MovieSearchResultsAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentFirstBinding;
import com.daclink.drew.sp22.cst438_project01_starter.databinding.FragmentMovieSearchBinding;
import com.daclink.drew.sp22.cst438_project01_starter.models.VolumesResponse;
import com.daclink.drew.sp22.cst438_project01_starter.viewmodels.MovieSearchViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class MovieSearchFragment extends Fragment {

    private FragmentMovieSearchBinding binding;
    private MovieSearchViewModel viewModel;
    private MovieSearchResultsAdapter adapter;

    private TextInputEditText keywordEditText;
    private Button searchButton;

    public MovieSearchFragment(FragmentMovieSearchBinding binding) {
        this.binding = binding;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new MovieSearchResultsAdapter();

        viewModel = ViewModelProviders.of(this).get(MovieSearchViewModel.class);
        viewModel.init();
        viewModel.getVolumesResponseLiveData().observe(this, new Observer<VolumesResponse>() {
            @Override
            public void onChanged(VolumesResponse volumesResponse) {
                if (volumesResponse != null) {
                    adapter.setResults(volumesResponse.getItems());
                }
            }
        });
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fragmentToFirstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MovieSearchFragment.this)
                        .navigate(R.id.action_FirstFragment_to_movieSearchFragment);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_search, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_booksearch_searchResultsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        keywordEditText = view.findViewById(R.id.fragment_moviesearch_title);
        searchButton = view.findViewById(R.id.action_movieSearch);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });

        return view;
    }

    public void performSearch() {
        String title = keywordEditText.getEditableText().toString();

        viewModel.searchVolumes(title);
    }
}