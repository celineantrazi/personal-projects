package com.example.newsmobileapp.api;

import com.example.newsmobileapp.models.GNewsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// APIService interface defines the API endpoints for the GNews API
// Retrofit uses this interface to generate the API requests
public interface APIService {

    // defines the endpoint to search for articles, it uses the @GET annotation to specify the HTTP method and endpoint path
    @GET("search")
    // Call<GNewsResponse> represents the API response, which is a list of articles
    Call<GNewsResponse> searchArticles(
            @Query("q") String query,   // specifies the query parameter for the search term
            @Query("apikey") String apiKey,    // specifies the API key parameter
            @Query("max") String max // specifies the maximum number of results parameter
    );
}
