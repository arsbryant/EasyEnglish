package com.example.easyenglish.ui.dashboard;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Wordbook.class, version = 1)
public abstract class WordbookDatabase extends RoomDatabase {

    private static WordbookDatabase instance;

    public abstract WordbookDao wordbookDao();

    public static synchronized WordbookDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    WordbookDatabase.class, "wordbook_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordbookDao wordbookDao;

        private PopulateDbAsyncTask(WordbookDatabase db) {
            wordbookDao = db.wordbookDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordbookDao.insert(new Wordbook("World", "Мир"));
            return null;
        }
    }
 }
