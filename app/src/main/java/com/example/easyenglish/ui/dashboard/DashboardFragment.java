package com.example.easyenglish.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyenglish.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DashboardFragment extends Fragment {
    private WordbookViewModel wordbookViewModel;
    public static final int ADD_NOTE_REQUEST = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        WordbookAdapter wordbookAdapter = new WordbookAdapter();
        recyclerView.setAdapter(wordbookAdapter);

        wordbookViewModel = new ViewModelProvider(this).get(WordbookViewModel.class);
        wordbookViewModel.getAllWordbooks().observe(getViewLifecycleOwner(), new Observer<List<Wordbook>>() {
            @Override
            public void onChanged(List<Wordbook> wordbooks) {
                wordbookAdapter.setWordbooks(wordbooks);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                wordbookViewModel.delete(wordbookAdapter.getWorkbookAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        FloatingActionButton button = view.findViewById(R.id.button_add_workbook);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.addWordbookFragment);
            }
        });

        if (getArguments() != null && ADD_NOTE_REQUEST == AddWordbookFragment.ADD_NOTE_CONFIRM) {

            String englishWord = getArguments().getString(AddWordbookFragment.EXTRA_ENGLISH_WORD);
            String russianWord = getArguments().getString(AddWordbookFragment.EXTRA_RUSSIAN_WORD);

            Wordbook wordbook = new Wordbook(englishWord, russianWord);
            wordbookViewModel.insert(wordbook);

        }

    }


}