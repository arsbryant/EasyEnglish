package com.example.easyenglish.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordbookDao {

    @Insert
    void insert (Wordbook wordbook);

    @Delete
    void delete(Wordbook wordbook);

    @Update
    void update(Wordbook wordbook);

    @Query("DELETE FROM wordbook_table")
    void deleteAllWordbooks();

    @Query("SELECT * FROM wordbook_table")
    LiveData<List<Wordbook>> getAllWordbooks();

}
