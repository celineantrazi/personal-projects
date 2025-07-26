package com.example.newsmobileapp.models;

import java.util.List;

// GNewsResponse class represents the response from the GNews API, it contains a list of articles retrieved from the API
public class GNewsResponse {

    private List<Article> articles;    // list of articles returned in the API response

    // getter method to access the list of articles
    public List<Article> getArticles() {
        return articles;     // returns the list of articles retrieved from the API
    }
}
