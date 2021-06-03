package com.example.easyenglish.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.easyenglish.R;

public class AddWordbookFragment extends Fragment {

    public static final String EXTRA_ENGLISH_WORD = "com.example.easyenglish.ui.dashboard.EXTRA_ENGLISH_WORD";
    public static final String EXTRA_RUSSIAN_WORD = "com.example.easyenglish.ui.dashboard.EXTRA_RUSSIAN_WORD";

    public static int ADD_NOTE_CONFIRM = 0;

    private EditText editTextEnglishWord;
    private EditText editTextRussianWord;
    private Button buttonSave;

    public static AddWordbookFragment newInstance() {
        return new AddWordbookFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.add_wordbook_fragment, container, false);

        editTextEnglishWord = root.findViewById(R.id.edit_text_english_word);
        editTextRussianWord = root.findViewById(R.id.edit_text_russian_word);

        ADD_NOTE_CONFIRM = 0;

        return root;
    }

    private void saveWorkbook(View view) {
        String englishWord = editTextEnglishWord.getText().toString();
        String russianWord = editTextRussianWord.getText().toString();

        if (englishWord.trim().isEmpty() || russianWord.trim().isEmpty()) {
            Toast.makeText(getContext(), "Please, insert both words", Toast.LENGTH_SHORT).show();
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_ENGLISH_WORD, englishWord);
        bundle.putString(EXTRA_RUSSIAN_WORD, russianWord);

        ADD_NOTE_CONFIRM = 1;

        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.navigation_dashboard, bundle);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonSave = view.findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWorkbook(view);
            }
        });
    }
}