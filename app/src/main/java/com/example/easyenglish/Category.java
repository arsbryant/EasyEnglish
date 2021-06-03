package com.example.easyenglish;

import java.util.List;

public class Category {

    private String nameCategory;
    private List<Article> articles;

    public Category(String nameCategory, List<Article> articles) {
        this.nameCategory = nameCategory;
        this.articles = articles;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
