package com.example.easyenglish.ui.dashboard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordbookViewModel extends AndroidViewModel {
    private WordbookRepository repository;
    private LiveData<List<Wordbook>> allWordbooks;

    public WordbookViewModel(@NonNull Application application) {
        super(application);
        repository = new WordbookRepository(application);
        allWordbooks = repository.getAllWordbooks();
    }

    public void insert(Wordbook wordbook) {
        repository.insert(wordbook);
    }

    public void update(Wordbook wordbook) {
        repository.update(wordbook);
    }

    public void delete(Wordbook wordbook) {
        repository.delete(wordbook);
    }

    public void deleteAllWordbooks() {
        repository.deleteAllWordbooks();
    }

    public LiveData<List<Wordbook>> getAllWordbooks() {
        return allWordbooks;
    }
}
