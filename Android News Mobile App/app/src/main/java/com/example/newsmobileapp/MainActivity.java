package com.example.newsmobileapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newsmobileapp.api.APIClient;
import com.example.newsmobileapp.api.APIService;
import com.example.newsmobileapp.models.Article;
import com.example.newsmobileapp.models.GNewsResponse;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// MainActivity is the primary entry point of the News Mobile Application, responsible for displaying news articles
public class MainActivity extends AppCompatActivity {

    // declare UI components
    private EditText searchInput;   // input field for searching news articles
    private Button searchBtn;   // button to trigger search
    private RecyclerView recyclerView;  // list of news articles
    private ArticleAdapter articleAdapter;    // adapter for RecyclerView

    // API key and base URL for GNews API
    private static final String API_KEY = "b65bbda36c385d918a47272527d62408";
    private static final String BASE_URL = "https://gnews.io/api/v4/";

    // called when the activity is first created, it is used to initialize the activity's UI and setup event listeners
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the UI layout of the activity
        setContentView(R.layout.activity_main);

        // initialize UI components
        searchInput = findViewById(R.id.searchInput);
        searchBtn = findViewById(R.id.searchBtn);
        recyclerView = findViewById(R.id.recyclerView);

        // set layout manager for RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // initialize adapter for RecyclerView
        articleAdapter = new ArticleAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(articleAdapter);

        // fetch latest news articles by default
        fetchArticles("latest");

        // set click listener for search button
        searchBtn.setOnClickListener(v -> {
            // get search query from input field
            String query = searchInput.getText().toString().trim();
            // check if search query is not empty
            if (!TextUtils.isEmpty(query)) {
                fetchArticles(query);   // fetch news articles based on search query
            } else {
                // show toast message if search query is empty
                Toast.makeText(MainActivity.this, "Please enter a search term", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // method to fetch news articles from GNews API
    private void fetchArticles(String query) {
        // create API service instance
        APIService api = APIClient.getClient().create(APIService.class);

        // create API call to search articles
        Call<GNewsResponse> call = api.searchArticles(query, API_KEY, "10");   // max 10 results

        // enqueue API call to execute asynchronously
        call.enqueue(new Callback<GNewsResponse>() {
            @Override
            public void onResponse(Call<GNewsResponse> call, Response<GNewsResponse> response) {
                // check if API response is successful and not null
                if (response.isSuccessful() && response.body() != null) {
                    List<Article> articles = response.body().getArticles();   // get list of articles from API response
                    articleAdapter.setArticles(articles);   // update adapter with new list of articles
                } else {
                    // show toast message if no articles found
                    Toast.makeText(MainActivity.this, "No articles found", Toast.LENGTH_SHORT).show();
                }
            }

            // callback method that is called when the API call fails
            @Override
            public void onFailure(Call<GNewsResponse> call, Throwable t) {
                // show toast message with error details if API call fails
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
