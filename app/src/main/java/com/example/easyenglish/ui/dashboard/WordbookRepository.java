package com.example.easyenglish.ui.dashboard;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

public class WordbookRepository {
    private WordbookDao wordbookDao;
    private LiveData<List<Wordbook>> allWordbooks;

    public WordbookRepository(Application application) {
        WordbookDatabase wordbookDatabase = WordbookDatabase.getInstance(application);
        wordbookDao = wordbookDatabase.wordbookDao();
        allWordbooks = wordbookDao.getAllWordbooks();
    }

    public void insert(Wordbook wordbook) {
        new InsertWordbookAsyncTask(wordbookDao).execute(wordbook);
    }

    public void update(Wordbook wordbook) {
        new UpdateWordbookAsyncTask(wordbookDao).execute(wordbook);
    }

    public void delete(Wordbook wordbook) {
        new DeleteWordbookAsyncTask(wordbookDao).execute(wordbook);
    }

    public void deleteAllWordbooks() {
        new DeleteAllWordbooksWordbookAsyncTask(wordbookDao).execute();
    }

    public LiveData<List<Wordbook>> getAllWordbooks() {
        return allWordbooks;
    }

    private static class InsertWordbookAsyncTask extends AsyncTask<Wordbook, Void, Void> {
        private WordbookDao wordbookDao;

        private InsertWordbookAsyncTask(WordbookDao wordbookDao) {
            this.wordbookDao = wordbookDao;
        }

        @Override
        protected Void doInBackground(Wordbook... wordbooks) {
            wordbookDao.insert(wordbooks[0]);
            return null;
        }
    }

    private static class UpdateWordbookAsyncTask extends AsyncTask<Wordbook, Void, Void> {
        private WordbookDao wordbookDao;

        private UpdateWordbookAsyncTask(WordbookDao wordbookDao) {
            this.wordbookDao = wordbookDao;
        }

        @Override
        protected Void doInBackground(Wordbook... wordbooks) {
            wordbookDao.update(wordbooks[0]);
            return null;
        }
    }

    private static class DeleteWordbookAsyncTask extends AsyncTask<Wordbook, Void, Void> {
        private WordbookDao wordbookDao;

        private DeleteWordbookAsyncTask(WordbookDao wordbookDao) {
            this.wordbookDao = wordbookDao;
        }

        @Override
        protected Void doInBackground(Wordbook... wordbooks) {
            wordbookDao.delete(wordbooks[0]);
            return null;
        }
    }

    private static class DeleteAllWordbooksWordbookAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordbookDao wordbookDao;

        private DeleteAllWordbooksWordbookAsyncTask(WordbookDao wordbookDao) {
            this.wordbookDao = wordbookDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordbookDao.deleteAllWordbooks();
            return null;
        }
    }
}
