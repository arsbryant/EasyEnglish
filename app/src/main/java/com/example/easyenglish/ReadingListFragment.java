package com.example.easyenglish;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easyenglish.Article;
import com.example.easyenglish.Category;
import com.example.easyenglish.CategoryAdapter;
import com.example.easyenglish.R;

import java.util.ArrayList;
import java.util.List;

public class ReadingListFragment extends Fragment {

    private RecyclerView recyclerViewCategory;
    private CategoryAdapter categoryAdapter;


    public ReadingListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_reading_list, container, false);

        recyclerViewCategory = root.findViewById(R.id.recycler_view_category);
        categoryAdapter = new CategoryAdapter(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(linearLayoutManager);

        categoryAdapter.setData(getListCategory());
        recyclerViewCategory.setAdapter(categoryAdapter);
        return root;
    }

    private List<Category> getListCategory() {
        List<Category> listCategory = new ArrayList<>();
        List<Article> articleList = new ArrayList<>();
        articleList.add(new Article("How to cook apple pie", R.drawable.article_picture_1));
        articleList.add(new Article("If not now, then when?", R.drawable.article_picture_2));
        articleList.add(new Article("COVID      19", R.drawable.article_picture_3));
        articleList.add(new Article("Street style outfits 2021", R.drawable.article_picture_4));
        listCategory.add(new Category("", articleList));

        return listCategory;
    }
}