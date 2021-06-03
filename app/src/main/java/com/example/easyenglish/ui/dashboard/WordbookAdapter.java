package com.example.easyenglish.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyenglish.R;

import java.util.ArrayList;
import java.util.List;

public class WordbookAdapter extends RecyclerView.Adapter<WordbookAdapter.WordbookHolder> {
    private List<Wordbook> wordbooks = new ArrayList<>();

    @NonNull
    @Override
    public WordbookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wordbook_item, parent, false);
        return new WordbookHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordbookHolder holder, int position) {
        Wordbook currentWordbook = wordbooks.get(position);
        holder.textViewEnglishWord.setText(currentWordbook.getEnglishWord());
        holder.textViewRussianWord.setText(currentWordbook.getRussianWord());
    }

    @Override
    public int getItemCount() {
        return wordbooks.size();
    }

    public void setWordbooks(List<Wordbook> wordbooks) {
        this.wordbooks = wordbooks;
        notifyDataSetChanged();
    }

    public Wordbook getWorkbookAt(int position) {
        return wordbooks.get(position);
    }

    class WordbookHolder extends RecyclerView.ViewHolder {
        private TextView textViewEnglishWord;
        private TextView textViewRussianWord;


        public WordbookHolder(@NonNull View itemView) {
            super(itemView);
            textViewEnglishWord = itemView.findViewById(R.id.english_word_view);
            textViewRussianWord = itemView.findViewById(R.id.russian_word_view);
        }
    }
}
