package com.example.newsmobileapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.newsmobileapp.models.Article;
import java.util.List;

// ArticleAdapter is a custom adapter for RecyclerView that displays a list of news articles
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private Context context;    // context of the activity that uses this adapter
    private List<Article> articleList;    // list of articles to be displayed in the RecyclerView

    // constructor to initialize the adapter with context and article list
    public ArticleAdapter(Context context, List<Article> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    // called when RecyclerView needs a new ViewHolder
    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout for a single article item
        View view = LayoutInflater.from(context).inflate(R.layout.activity_news_item, parent, false);
        return new ArticleViewHolder(view);    // return a new ArticleViewHolder instance
    }

    // called when RecyclerView needs to display a ViewHolder at a specific position
    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);    // get the article at the specified position

        // bind article data to the ViewHolder's views
        holder.articleTitle.setText(article.getTitle());
        holder.articleAuthor.setText(article.getAuthor() != null ? "By " + article.getAuthor() : "Author unknown");
        holder.articleSource.setText(article.getSource() != null ? article.getSource().getName() : "Unknown source");
        holder.articlePublishedAt.setText("Published: " + article.getPublishedAt());
        holder.articleDescription.setText(article.getDescription());
        holder.articleContent.setText(article.getContent());

        // load article image using Glide if available
        if (article.getImage() != null && !article.getImage().isEmpty()) {
            Glide.with(context).load(article.getImage()).into(holder.articleImage);
        } else {
            // set a default color if no image is available
            holder.articleImage.setImageResource(android.R.color.darker_gray);
        }

        // set click listener to open article URL in browser
        holder.itemView.setOnClickListener(v -> {
            if (article.getUrl() != null && !article.getUrl().isEmpty()) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
                context.startActivity(browserIntent);
            }
        });
    }

    // method to update the article list and notify RecyclerView to refresh
    public void setArticles(List<Article> articles) {
        this.articleList = articles;
        notifyDataSetChanged();
    }

    // returns the number of articles in the list
    @Override
    public int getItemCount() {
        return articleList.size();
    }

    // ArticleViewHolder is a ViewHolder class that holds references to the views in the article item layout
    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        // views in the article item layout
        ImageView articleImage;
        TextView articleTitle, articleAuthor, articleSource, articlePublishedAt, articleDescription, articleContent;

        // constructor to initialize the ViewHolder with the article item layout
        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            // initialize views
            articleImage = itemView.findViewById(R.id.articleImage);
            articleTitle = itemView.findViewById(R.id.articleTitle);
            articleAuthor = itemView.findViewById(R.id.articleAuthor);
            articleSource = itemView.findViewById(R.id.articleSource);
            articlePublishedAt = itemView.findViewById(R.id.articlePublishedAt);
            articleDescription = itemView.findViewById(R.id.articleDescription);
            articleContent = itemView.findViewById(R.id.articleContent);
        }
    }
}
