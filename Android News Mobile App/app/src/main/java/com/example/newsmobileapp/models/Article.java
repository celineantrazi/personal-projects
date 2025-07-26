package com.example.newsmobileapp.models;

// Article class represents a news article with its details
public class Article {

    private String id;   // identifier of the article
    private String title;   // title of the article
    private String description;    // brief description of the article
    private String content;    // content of the article
    private String url;    // URL of the article
    private String image;   // Image URL associated with the article
    private String publishedAt;    // date and time when the article was published
    private Source source;     // source of the article
    private String author;     // author of the article

    // getters to access article properties
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public Source getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    // Source class represents the source of an article
    public static class Source {
        private String id;    // identifier of the source
        private String name;    // name of the source
        private String url;    // URL of the source

        // getters to access source properties
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
}
