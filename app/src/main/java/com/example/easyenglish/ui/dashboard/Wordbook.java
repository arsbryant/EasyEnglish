package com.example.easyenglish.ui.dashboard;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wordbook_table")
public class Wordbook {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String englishWord;
    private String russianWord;

    public Wordbook(String englishWord, String russianWord) {
        this.englishWord = englishWord;
        this.russianWord = russianWord;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public String getRussianWord() {
        return russianWord;
    }


}
