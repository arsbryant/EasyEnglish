package com.example.easyenglish;

public class Article {

    private String title;
    private final int imageResource;

    Article(String title, int imageResource) {
        this.title = title;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResource() {
        return imageResource;
    }
}
