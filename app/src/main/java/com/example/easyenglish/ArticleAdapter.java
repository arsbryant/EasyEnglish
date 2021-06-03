package com.example.easyenglish;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<Article> mArticles;

    public void setData(List<Article> list) {
        this.mArticles = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.articles_item, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = mArticles.get(position);
        if (article == null) {
            return;
        }

        holder.imageArticle.setImageResource(article.getImageResource());
        holder.textViewTitle.setText(article.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_readingListFragment_to_readingArticleFragment);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mArticles != null) {
            return mArticles.size();
        }
        return 0;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageArticle;
        private TextView textViewTitle;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            imageArticle = itemView.findViewById(R.id.article_image);
            textViewTitle = itemView.findViewById(R.id.article_title);
        }
    }
}
